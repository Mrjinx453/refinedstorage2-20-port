package com.refinedmods.refinedstorage2.platform.fabric.internal.network.node.importer;

import com.refinedmods.refinedstorage2.api.network.node.importer.ImporterSource;
import com.refinedmods.refinedstorage2.api.network.node.importer.ImporterTransferStrategy;
import com.refinedmods.refinedstorage2.api.network.node.importer.ImporterTransferStrategyImpl;
import com.refinedmods.refinedstorage2.api.storage.channel.StorageChannelType;
import com.refinedmods.refinedstorage2.platform.api.network.node.importer.ImporterTransferStrategyFactory;
import com.refinedmods.refinedstorage2.platform.api.upgrade.UpgradeState;
import com.refinedmods.refinedstorage2.platform.common.content.Items;

import java.util.function.Function;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;

public class StorageImporterTransferStrategyFactory<T, P> implements ImporterTransferStrategyFactory {
    private final BlockApiLookup<Storage<P>, Direction> lookup;
    private final StorageChannelType<T> storageChannelType;
    private final Function<P, T> fromPlatformMapper;
    private final Function<T, P> toPlatformMapper;
    private final long singleAmount;

    public StorageImporterTransferStrategyFactory(final BlockApiLookup<Storage<P>, Direction> lookup,
                                                  final StorageChannelType<T> storageChannelType,
                                                  final Function<P, T> fromPlatformMapper,
                                                  final Function<T, P> toPlatformMapper,
                                                  final long singleAmount) {
        this.lookup = lookup;
        this.storageChannelType = storageChannelType;
        this.fromPlatformMapper = fromPlatformMapper;
        this.toPlatformMapper = toPlatformMapper;
        this.singleAmount = singleAmount;
    }

    @Override
    public ImporterTransferStrategy create(final ServerLevel level,
                                           final BlockPos pos,
                                           final Direction direction,
                                           final UpgradeState upgradeState) {
        final ImporterSource<T> source = new StorageImporterSource<>(
            lookup,
            fromPlatformMapper,
            toPlatformMapper,
            level,
            pos,
            direction
        );
        return new ImporterTransferStrategyImpl<>(
            source,
            storageChannelType,
            upgradeState.hasUpgrade(Items.INSTANCE.getStackUpgrade()) ? singleAmount * 64 : singleAmount
        );
    }
}

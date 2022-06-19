package com.refinedmods.refinedstorage2.platform.common.block.entity.storage;

import com.refinedmods.refinedstorage2.api.network.node.storage.StorageNetworkNode;
import com.refinedmods.refinedstorage2.api.storage.InMemoryStorageImpl;
import com.refinedmods.refinedstorage2.api.storage.limited.LimitedStorageImpl;
import com.refinedmods.refinedstorage2.api.storage.tracked.InMemoryTrackedStorageRepository;
import com.refinedmods.refinedstorage2.api.storage.tracked.TrackedStorageImpl;
import com.refinedmods.refinedstorage2.api.storage.tracked.TrackedStorageRepository;
import com.refinedmods.refinedstorage2.platform.api.resource.ItemResource;
import com.refinedmods.refinedstorage2.platform.apiimpl.resource.ItemResourceType;
import com.refinedmods.refinedstorage2.platform.apiimpl.storage.LimitedPlatformStorage;
import com.refinedmods.refinedstorage2.platform.apiimpl.storage.PlatformStorage;
import com.refinedmods.refinedstorage2.platform.apiimpl.storage.channel.StorageChannelTypes;
import com.refinedmods.refinedstorage2.platform.apiimpl.storage.type.ItemStorageType;
import com.refinedmods.refinedstorage2.platform.common.Platform;
import com.refinedmods.refinedstorage2.platform.common.containermenu.storage.block.ItemStorageBlockContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.content.BlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import static com.refinedmods.refinedstorage2.platform.common.util.IdentifierUtil.createTranslation;

public class ItemStorageBlockBlockEntity extends StorageBlockBlockEntity<ItemResource> {
    private final ItemStorageType.Variant variant;
    private final Component displayName;

    public ItemStorageBlockBlockEntity(BlockPos pos, BlockState state, ItemStorageType.Variant variant) {
        super(
                BlockEntities.INSTANCE.getItemStorageBlocks().get(variant).get(),
                pos,
                state,
                new StorageNetworkNode<>(getEnergyUsage(variant), StorageChannelTypes.ITEM),
                ItemResourceType.INSTANCE
        );
        this.variant = variant;
        this.displayName = createTranslation("block", String.format("%s_storage_block", variant.getName()));
    }

    private static long getEnergyUsage(ItemStorageType.Variant variant) {
        return switch (variant) {
            case ONE_K -> Platform.INSTANCE.getConfig().getStorageBlock().get1kEnergyUsage();
            case FOUR_K -> Platform.INSTANCE.getConfig().getStorageBlock().get4kEnergyUsage();
            case SIXTEEN_K -> Platform.INSTANCE.getConfig().getStorageBlock().get16kEnergyUsage();
            case SIXTY_FOUR_K -> Platform.INSTANCE.getConfig().getStorageBlock().get64kEnergyUsage();
            case CREATIVE -> Platform.INSTANCE.getConfig().getStorageBlock().getCreativeEnergyUsage();
        };
    }

    @Override
    protected PlatformStorage<ItemResource> createStorage(Runnable listener) {
        TrackedStorageRepository<ItemResource> trackingRepository = new InMemoryTrackedStorageRepository<>();
        if (!variant.hasCapacity()) {
            return new PlatformStorage<>(
                    new TrackedStorageImpl<>(new InMemoryStorageImpl<>(), trackingRepository, System::currentTimeMillis),
                    ItemStorageType.INSTANCE,
                    trackingRepository,
                    listener
            );
        }
        return new LimitedPlatformStorage<>(
                new LimitedStorageImpl<>(
                        new TrackedStorageImpl<>(new InMemoryStorageImpl<>(), trackingRepository, System::currentTimeMillis),
                        variant.getCapacity()
                ),
                ItemStorageType.INSTANCE,
                trackingRepository,
                listener
        );
    }

    @Override
    public Component getDisplayName() {
        return displayName;
    }

    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new ItemStorageBlockContainerMenu(
                syncId,
                player,
                resourceFilterContainer,
                this
        );
    }
}
package com.refinedmods.refinedstorage2.platform.common.block;

import com.refinedmods.refinedstorage2.platform.common.block.entity.storage.FluidStorageBlockBlockEntity;
import com.refinedmods.refinedstorage2.platform.common.block.ticker.NetworkNodeBlockEntityTicker;
import com.refinedmods.refinedstorage2.platform.common.content.BlockEntities;
import com.refinedmods.refinedstorage2.platform.common.internal.storage.type.FluidStorageType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FluidStorageBlock extends AbstractStorageBlock<FluidStorageBlockBlockEntity> {
    private final FluidStorageType.Variant variant;

    public FluidStorageBlock(final FluidStorageType.Variant variant) {
        super(
            BlockConstants.PROPERTIES,
            new NetworkNodeBlockEntityTicker<>(() -> BlockEntities.INSTANCE.getFluidStorageBlock(variant))
        );
        this.variant = variant;
    }

    @Override
    public BlockEntity newBlockEntity(final BlockPos pos, final BlockState state) {
        return new FluidStorageBlockBlockEntity(pos, state, variant);
    }
}

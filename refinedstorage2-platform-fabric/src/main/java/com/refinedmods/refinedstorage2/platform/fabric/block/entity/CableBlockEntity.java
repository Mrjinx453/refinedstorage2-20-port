package com.refinedmods.refinedstorage2.platform.fabric.block.entity;

import com.refinedmods.refinedstorage2.api.network.node.CableNetworkNode;
import com.refinedmods.refinedstorage2.api.network.node.NetworkNodeImpl;
import com.refinedmods.refinedstorage2.platform.fabric.Rs2Config;
import com.refinedmods.refinedstorage2.platform.fabric.Rs2Mod;
import com.refinedmods.refinedstorage2.platform.fabric.api.util.Positions;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class CableBlockEntity extends NetworkNodeBlockEntity<NetworkNodeImpl> {
    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(Rs2Mod.BLOCK_ENTITIES.getCable(), pos, state);
    }

    @Override
    protected NetworkNodeImpl createNode(BlockPos pos, NbtCompound tag) {
        return new CableNetworkNode(
                Positions.ofBlockPos(pos),
                Rs2Config.get().getCable().getEnergyUsage()
        );
    }
}
package com.refinedmods.refinedstorage2.platform.api.grid;

import com.refinedmods.refinedstorage2.api.grid.service.GridServiceFactory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.apiguardian.api.API;

@API(status = API.Status.STABLE, since = "2.0.0-milestone.2.6")
@FunctionalInterface
public interface GridInsertionStrategyFactory {
    GridInsertionStrategy create(
        AbstractContainerMenu containerMenu,
        Player player,
        GridServiceFactory gridServiceFactory
    );
}

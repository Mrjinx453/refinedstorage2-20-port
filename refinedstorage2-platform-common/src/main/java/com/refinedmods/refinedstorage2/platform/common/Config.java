package com.refinedmods.refinedstorage2.platform.common;

import com.refinedmods.refinedstorage2.api.grid.view.GridSortingDirection;
import com.refinedmods.refinedstorage2.api.grid.view.GridSortingType;
import com.refinedmods.refinedstorage2.platform.apiimpl.grid.GridSize;
import com.refinedmods.refinedstorage2.platform.apiimpl.grid.GridSynchronizationType;

public interface Config {
    Grid getGrid();

    Controller getController();

    DiskDrive getDiskDrive();

    Cable getCable();

    StorageBlock getStorageBlock();

    FluidStorageBlock getFluidStorageBlock();

    interface Grid {
        boolean isLargeFont();

        int getMaxRowsStretch();

        boolean isPreventSortingWhileShiftIsDown();

        boolean isDetailedTooltip();

        boolean isRememberSearchQuery();

        long getEnergyUsage();

        boolean isSmoothScrolling();

        boolean isAutoSelected();

        void setAutoSelected(boolean autoSelected);

        GridSynchronizationType getSynchronizationType();

        void setSynchronizationType(GridSynchronizationType synchronizationType);

        GridSortingDirection getSortingDirection();

        void setSortingDirection(GridSortingDirection sortingDirection);

        GridSortingType getSortingType();

        void setSortingType(GridSortingType sortingType);

        GridSize getSize();

        void setSize(GridSize size);
    }

    interface DiskDrive {
        long getEnergyUsage();

        long getEnergyUsagePerDisk();
    }

    interface Cable {
        long getEnergyUsage();
    }

    interface Controller {
        long getEnergyCapacity();
    }

    interface StorageBlock {
        long get1kEnergyUsage();

        long get4kEnergyUsage();

        long get16kEnergyUsage();

        long get64kEnergyUsage();

        long getCreativeEnergyUsage();
    }

    interface FluidStorageBlock {
        long get64bEnergyUsage();

        long get256bEnergyUsage();

        long get1024bEnergyUsage();

        long get4096bEnergyUsage();

        long getCreativeEnergyUsage();
    }
}
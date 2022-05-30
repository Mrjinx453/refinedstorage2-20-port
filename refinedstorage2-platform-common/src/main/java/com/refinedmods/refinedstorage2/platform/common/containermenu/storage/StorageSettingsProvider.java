package com.refinedmods.refinedstorage2.platform.common.containermenu.storage;

import com.refinedmods.refinedstorage2.api.core.filter.FilterMode;
import com.refinedmods.refinedstorage2.api.storage.AccessMode;
import com.refinedmods.refinedstorage2.platform.api.network.node.RedstoneMode;

public interface StorageSettingsProvider {
    int getPriority();

    void setPriority(int priority);

    FilterMode getFilterMode();

    void setFilterMode(FilterMode filterMode);

    boolean isExactMode();

    void setExactMode(boolean exactMode);

    AccessMode getAccessMode();

    void setAccessMode(AccessMode accessMode);

    RedstoneMode getRedstoneMode();

    void setRedstoneMode(RedstoneMode redstoneMode);
}
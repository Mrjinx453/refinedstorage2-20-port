package com.refinedmods.refinedstorage2.platform.api.grid;

import com.refinedmods.refinedstorage2.api.grid.service.GridExtractMode;
import com.refinedmods.refinedstorage2.api.grid.service.GridInsertMode;
import com.refinedmods.refinedstorage2.platform.api.resource.FluidResource;

public interface FluidGridEventHandler {
    void onInsert(GridInsertMode insertMode);

    void onTransfer(int slotIndex);

    void onExtract(FluidResource fluidResource, GridExtractMode mode, boolean cursor);
}
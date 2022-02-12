package com.refinedmods.refinedstorage2.platform.forge.render.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class DiskDriveModelLoader implements IModelLoader<DiskDriveModelGeometry> {
    @Override
    public DiskDriveModelGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new DiskDriveModelGeometry();
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        // no op
    }
}

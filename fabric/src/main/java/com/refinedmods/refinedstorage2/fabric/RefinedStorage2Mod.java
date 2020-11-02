package com.refinedmods.refinedstorage2.fabric;

import com.refinedmods.refinedstorage2.core.RefinedStorage2ApiFacade;
import com.refinedmods.refinedstorage2.fabric.coreimpl.FabricRefinedStorage2ApiFacade;
import com.refinedmods.refinedstorage2.fabric.init.RefinedStorage2BlockEntities;
import com.refinedmods.refinedstorage2.fabric.init.RefinedStorage2Blocks;
import com.refinedmods.refinedstorage2.fabric.init.RefinedStorage2Items;
import com.refinedmods.refinedstorage2.fabric.packet.c2s.StorageDiskInfoRequestPacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RefinedStorage2Mod implements ModInitializer {
    private static final Logger LOGGER = LogManager.getLogger(RefinedStorage2Mod.class);

    public static final String ID = "refinedstorage2";

    public static final RefinedStorage2ApiFacade API = new FabricRefinedStorage2ApiFacade();

    public static final RefinedStorage2Blocks BLOCKS = new RefinedStorage2Blocks();
    public static final RefinedStorage2Items ITEMS = new RefinedStorage2Items();
    public static final RefinedStorage2BlockEntities BLOCK_ENTITIES = new RefinedStorage2BlockEntities();

    private static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(ID, "general"), () -> new ItemStack(BLOCKS.getCable()));

    @Override
    public void onInitialize() {
        BLOCKS.register();
        ITEMS.register(BLOCKS, ITEM_GROUP);
        BLOCK_ENTITIES.register(BLOCKS);

        ServerSidePacketRegistry.INSTANCE.register(StorageDiskInfoRequestPacket.ID, new StorageDiskInfoRequestPacket());

        LOGGER.info("Refined Storage 2 has loaded.");
    }
}
package com.refinedmods.refinedstorage2.platform.forge.packet;

import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.CraftingGridClearPacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.CraftingGridRecipeTransferPacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.DetectorAmountChangePacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.GridExtractPacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.GridInsertPacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.GridScrollPacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.PropertyChangePacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.ResourceFilterSlotAmountChangePacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.ResourceFilterSlotChangePacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.c2s.StorageInfoRequestPacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.s2c.ControllerEnergyInfoPacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.s2c.GridActivePacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.s2c.GridClearPacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.s2c.GridUpdatePacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.s2c.ResourceFilterSlotUpdatePacket;
import com.refinedmods.refinedstorage2.platform.forge.packet.s2c.StorageInfoResponsePacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import static com.refinedmods.refinedstorage2.platform.common.util.IdentifierUtil.createIdentifier;

public class NetworkManager {
    private static final String PROTOCOL_VERSION = "1";

    private final ResourceLocation channel = createIdentifier("main_channel");
    private final SimpleChannel handler = NetworkRegistry.ChannelBuilder
        .named(channel)
        .clientAcceptedVersions(PROTOCOL_VERSION::equals)
        .serverAcceptedVersions(PROTOCOL_VERSION::equals)
        .networkProtocolVersion(() -> PROTOCOL_VERSION)
        .simpleChannel();

    public NetworkManager() {
        int id = 0;
        handler.registerMessage(
            id++,
            ControllerEnergyInfoPacket.class,
            ControllerEnergyInfoPacket::encode,
            ControllerEnergyInfoPacket::decode,
            ControllerEnergyInfoPacket::handle
        );
        handler.registerMessage(
            id++,
            PropertyChangePacket.class,
            PropertyChangePacket::encode,
            PropertyChangePacket::decode,
            PropertyChangePacket::handle
        );
        handler.registerMessage(
            id++,
            StorageInfoRequestPacket.class,
            StorageInfoRequestPacket::encode,
            StorageInfoRequestPacket::decode,
            StorageInfoRequestPacket::handle
        );
        handler.registerMessage(
            id++,
            StorageInfoResponsePacket.class,
            StorageInfoResponsePacket::encode,
            StorageInfoResponsePacket::decode,
            StorageInfoResponsePacket::handle
        );
        handler.registerMessage(
            id++,
            ResourceFilterSlotUpdatePacket.class,
            ResourceFilterSlotUpdatePacket::encode,
            ResourceFilterSlotUpdatePacket::decode,
            ResourceFilterSlotUpdatePacket::handle
        );
        handler.registerMessage(
            id++,
            GridActivePacket.class,
            GridActivePacket::encode,
            GridActivePacket::decode,
            GridActivePacket::handle
        );
        handler.registerMessage(
            id++,
            GridClearPacket.class,
            (packet, buf) -> {
            },
            buf -> new GridClearPacket(),
            (buf, ctx) -> GridClearPacket.handle(ctx)
        );
        handler.registerMessage(
            id++,
            GridUpdatePacket.class,
            GridUpdatePacket::encode,
            GridUpdatePacket::decode,
            GridUpdatePacket::handle
        );
        handler.registerMessage(
            id++,
            GridInsertPacket.class,
            GridInsertPacket::encode,
            GridInsertPacket::decode,
            GridInsertPacket::handle
        );
        handler.registerMessage(
            id++,
            GridExtractPacket.class,
            GridExtractPacket::encode,
            GridExtractPacket::decode,
            GridExtractPacket::handle
        );
        handler.registerMessage(
            id++,
            GridScrollPacket.class,
            GridScrollPacket::encode,
            GridScrollPacket::decode,
            GridScrollPacket::handle
        );
        handler.registerMessage(
            id++,
            ResourceFilterSlotAmountChangePacket.class,
            ResourceFilterSlotAmountChangePacket::encode,
            ResourceFilterSlotAmountChangePacket::decode,
            ResourceFilterSlotAmountChangePacket::handle
        );
        handler.registerMessage(
            id++,
            ResourceFilterSlotChangePacket.class,
            ResourceFilterSlotChangePacket::encode,
            ResourceFilterSlotChangePacket::decode,
            ResourceFilterSlotChangePacket::handle
        );
        handler.registerMessage(
            id++,
            CraftingGridClearPacket.class,
            CraftingGridClearPacket::encode,
            CraftingGridClearPacket::decode,
            CraftingGridClearPacket::handle
        );
        handler.registerMessage(
            id++,
            CraftingGridRecipeTransferPacket.class,
            CraftingGridRecipeTransferPacket::encode,
            CraftingGridRecipeTransferPacket::decode,
            CraftingGridRecipeTransferPacket::handle
        );
        handler.registerMessage(
            id++,
            DetectorAmountChangePacket.class,
            DetectorAmountChangePacket::encode,
            DetectorAmountChangePacket::decode,
            DetectorAmountChangePacket::handle
        );
    }

    public void send(final ServerPlayer player, final Object message) {
        handler.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public void send(final Object message) {
        handler.sendToServer(message);
    }
}

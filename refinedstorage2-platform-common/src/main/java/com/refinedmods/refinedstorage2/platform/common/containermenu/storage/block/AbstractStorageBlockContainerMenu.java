package com.refinedmods.refinedstorage2.platform.common.containermenu.storage.block;

import com.refinedmods.refinedstorage2.platform.api.storage.channel.PlatformStorageChannelType;
import com.refinedmods.refinedstorage2.platform.common.containermenu.slot.ResourceFilterSlot;
import com.refinedmods.refinedstorage2.platform.common.containermenu.storage.AbstractStorageContainerMenu;
import com.refinedmods.refinedstorage2.platform.common.containermenu.storage.StorageAccessor;
import com.refinedmods.refinedstorage2.platform.common.containermenu.storage.StorageConfigurationContainer;
import com.refinedmods.refinedstorage2.platform.common.internal.resource.filter.FilteredResourceFilterContainer;
import com.refinedmods.refinedstorage2.platform.common.internal.resource.filter.ResourceFilterContainer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;

public abstract class AbstractStorageBlockContainerMenu extends AbstractStorageContainerMenu
    implements StorageAccessor {
    private static final int FILTER_SLOT_X = 8;
    private static final int FILTER_SLOT_Y = 20;

    private long stored;
    private long capacity;

    protected <T> AbstractStorageBlockContainerMenu(final MenuType<?> type,
                                                    final int syncId,
                                                    final Player player,
                                                    final FriendlyByteBuf buf,
                                                    final PlatformStorageChannelType<T> storageChannelType) {
        super(type, syncId);
        this.stored = buf.readLong();
        this.capacity = buf.readLong();
        addSlots(player, new FilteredResourceFilterContainer<>(9, storageChannelType));
        initializeResourceFilterSlots(buf);
    }

    protected AbstractStorageBlockContainerMenu(final MenuType<?> type,
                                                final int syncId,
                                                final Player player,
                                                final ResourceFilterContainer resourceFilterContainer,
                                                final StorageConfigurationContainer configContainer) {
        super(type, syncId, player, configContainer);
        addSlots(player, resourceFilterContainer);
    }

    private void addSlots(final Player player, final ResourceFilterContainer resourceFilterContainer) {
        for (int i = 0; i < resourceFilterContainer.size(); ++i) {
            addSlot(createFilterSlot(resourceFilterContainer, i));
        }
        addPlayerInventory(player.getInventory(), 8, 141);

        transferManager.addFilterTransfer(player.getInventory());
    }

    private Slot createFilterSlot(final ResourceFilterContainer resourceFilterContainer, final int i) {
        final int x = FILTER_SLOT_X + (18 * i);
        return new ResourceFilterSlot(resourceFilterContainer, i, x, FILTER_SLOT_Y);
    }

    @Override
    public double getProgress() {
        if (capacity == 0) {
            return 0;
        }
        return (double) getStored() / (double) getCapacity();
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    @Override
    public long getStored() {
        return stored;
    }
}

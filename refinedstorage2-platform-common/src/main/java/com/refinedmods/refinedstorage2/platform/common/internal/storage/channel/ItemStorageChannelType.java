package com.refinedmods.refinedstorage2.platform.common.internal.storage.channel;

import com.refinedmods.refinedstorage2.api.grid.view.GridResource;
import com.refinedmods.refinedstorage2.api.resource.ResourceAmount;
import com.refinedmods.refinedstorage2.api.resource.list.ResourceList;
import com.refinedmods.refinedstorage2.api.resource.list.ResourceListImpl;
import com.refinedmods.refinedstorage2.platform.api.resource.ItemResource;
import com.refinedmods.refinedstorage2.platform.api.resource.filter.FilteredResource;
import com.refinedmods.refinedstorage2.platform.api.resource.list.FuzzyResourceList;
import com.refinedmods.refinedstorage2.platform.api.resource.list.FuzzyResourceListImpl;
import com.refinedmods.refinedstorage2.platform.api.storage.channel.AbstractPlatformStorageChannelType;
import com.refinedmods.refinedstorage2.platform.api.storage.channel.FuzzyStorageChannelImpl;
import com.refinedmods.refinedstorage2.platform.common.Platform;
import com.refinedmods.refinedstorage2.platform.common.internal.grid.view.ItemGridResource;
import com.refinedmods.refinedstorage2.platform.common.internal.resource.filter.item.ItemFilteredResource;
import com.refinedmods.refinedstorage2.platform.common.screen.TextureIds;
import com.refinedmods.refinedstorage2.platform.common.util.PacketUtil;

import java.util.Optional;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

import static com.refinedmods.refinedstorage2.platform.common.util.IdentifierUtil.createTranslation;

class ItemStorageChannelType extends AbstractPlatformStorageChannelType<ItemResource> {
    ItemStorageChannelType() {
        super(
            "ITEM",
            () -> {
                final ResourceList<ItemResource> list = new ResourceListImpl<>();
                final FuzzyResourceList<ItemResource> fuzzyList = new FuzzyResourceListImpl<>(list);
                return new FuzzyStorageChannelImpl<>(fuzzyList);
            },
            createTranslation("misc", "storage_channel_type.item"),
            TextureIds.ICONS,
            0,
            128
        );
    }

    @Override
    public void toBuffer(final ItemResource resource, final FriendlyByteBuf buf) {
        PacketUtil.writeItemResource(buf, resource);
    }

    @Override
    public ItemResource fromBuffer(final FriendlyByteBuf buf) {
        return PacketUtil.readItemResource(buf);
    }

    @Override
    public Optional<GridResource> toGridResource(final ResourceAmount<?> resourceAmount) {
        return Platform.INSTANCE.getItemGridResourceFactory().apply(resourceAmount);
    }

    @Override
    public Optional<FilteredResource<ItemResource>> toFilteredResource(final ResourceAmount<?> resourceAmount) {
        if (resourceAmount.getResource() instanceof ItemResource itemResource) {
            return Optional.of(new ItemFilteredResource(itemResource, resourceAmount.getAmount()));
        }
        return Optional.empty();
    }

    @Override
    public boolean isGridResourceBelonging(final GridResource gridResource) {
        return gridResource instanceof ItemGridResource;
    }

    @Override
    public long normalizeAmount(final double amount) {
        return (long) amount;
    }

    @Override
    public CompoundTag toTag(final ItemResource resource) {
        return ItemResource.toTag(resource);
    }

    @Override
    public Optional<ItemResource> fromTag(final CompoundTag tag) {
        return ItemResource.fromTag(tag);
    }
}

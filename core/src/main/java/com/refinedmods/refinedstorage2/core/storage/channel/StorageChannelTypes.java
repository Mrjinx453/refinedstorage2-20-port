package com.refinedmods.refinedstorage2.core.storage.channel;

import com.refinedmods.refinedstorage2.core.list.item.StackListImpl;
import com.refinedmods.refinedstorage2.core.stack.item.Rs2ItemStack;
import com.refinedmods.refinedstorage2.core.stack.item.Rs2ItemStackIdentifier;
import com.refinedmods.refinedstorage2.core.storage.composite.CompositeStorage;

public final class StorageChannelTypes {
    private StorageChannelTypes() {
    }

    public static final StorageChannelType<Rs2ItemStack> ITEM = () -> new StorageChannelImpl<>(
            StackListImpl::createItemStackList,
            new StorageTracker<>(Rs2ItemStackIdentifier::new, System::currentTimeMillis),
            CompositeStorage.emptyItemStackStorage()
    );
}
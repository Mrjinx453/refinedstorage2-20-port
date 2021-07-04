package com.refinedmods.refinedstorage2.core.network.component;

import com.refinedmods.refinedstorage2.core.network.Network;
import com.refinedmods.refinedstorage2.core.network.node.container.NetworkNodeContainer;

import java.util.Set;

public interface NetworkComponent {
    default void onContainerAdded(NetworkNodeContainer<?> container) {

    }

    default void onContainerRemoved(NetworkNodeContainer<?> container) {

    }

    default void onNetworkRemoved() {

    }

    default void onNetworkSplit(Set<Network> networks) {

    }

    default void onNetworkMerge(Network network) {

    }
}

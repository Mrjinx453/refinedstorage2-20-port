package com.refinedmods.refinedstorage2.api.network.impl.node.grid;

import com.refinedmods.refinedstorage2.api.core.Action;
import com.refinedmods.refinedstorage2.api.grid.GridWatcher;
import com.refinedmods.refinedstorage2.api.grid.service.GridInsertMode;
import com.refinedmods.refinedstorage2.api.grid.service.GridService;
import com.refinedmods.refinedstorage2.api.network.Network;
import com.refinedmods.refinedstorage2.api.resource.ResourceAmount;
import com.refinedmods.refinedstorage2.api.storage.Actor;
import com.refinedmods.refinedstorage2.api.storage.EmptyActor;
import com.refinedmods.refinedstorage2.api.storage.InMemoryStorageImpl;
import com.refinedmods.refinedstorage2.api.storage.channel.StorageChannel;
import com.refinedmods.refinedstorage2.api.storage.limited.LimitedStorageImpl;
import com.refinedmods.refinedstorage2.api.storage.tracked.TrackedResource;
import com.refinedmods.refinedstorage2.api.storage.tracked.TrackedStorageImpl;
import com.refinedmods.refinedstorage2.network.test.AddNetworkNode;
import com.refinedmods.refinedstorage2.network.test.InjectNetwork;
import com.refinedmods.refinedstorage2.network.test.InjectNetworkStorageChannel;
import com.refinedmods.refinedstorage2.network.test.NetworkTest;
import com.refinedmods.refinedstorage2.network.test.NetworkTestFixtures;
import com.refinedmods.refinedstorage2.network.test.SetupNetwork;
import com.refinedmods.refinedstorage2.network.test.nodefactory.AbstractNetworkNodeFactory;
import com.refinedmods.refinedstorage2.network.test.util.FakeActor;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@NetworkTest
@SetupNetwork
@SetupNetwork(id = "other")
class GridNetworkNodeTest {
    @AddNetworkNode(properties = {
        @AddNetworkNode.Property(key = AbstractNetworkNodeFactory.PROPERTY_ENERGY_USAGE, longValue = 5)
    })
    GridNetworkNode sut;

    @BeforeEach
    void setUp(
        @InjectNetworkStorageChannel final StorageChannel<String> storage,
        @InjectNetworkStorageChannel(networkId = "other") final StorageChannel<String> otherStorageChannel
    ) {
        storage.addSource(new TrackedStorageImpl<>(new LimitedStorageImpl<>(1000), () -> 2L));
        storage.insert("A", 100, Action.EXECUTE, EmptyActor.INSTANCE);
        storage.insert("B", 200, Action.EXECUTE, EmptyActor.INSTANCE);

        otherStorageChannel.addSource(new TrackedStorageImpl<>(new InMemoryStorageImpl<>(), () -> 3L));
    }

    @Test
    void testInitialState() {
        // Assert
        assertThat(sut.getEnergyUsage()).isEqualTo(5);
    }

    @Test
    void shouldRetrieveResources() {
        // Act
        final List<GridNetworkNode.GridResource<String>> resources = sut.getResources(
            NetworkTestFixtures.STORAGE_CHANNEL_TYPE,
            EmptyActor.class
        );

        // Assert
        assertThat(resources).usingRecursiveFieldByFieldElementComparator().containsExactly(
            new GridNetworkNode.GridResource<>(
                new ResourceAmount<>("A", 100),
                new TrackedResource("Empty", 2L)
            ),
            new GridNetworkNode.GridResource<>(
                new ResourceAmount<>("B", 200),
                new TrackedResource("Empty", 2L)
            )
        );
    }

    @Test
    void shouldNotifyWatchersOfActivenessChanges() {
        // Arrange
        final GridWatcher watcher = mock(GridWatcher.class);

        // Act
        sut.addWatcher(watcher, EmptyActor.class);
        sut.setActive(true);
        sut.setActive(false);
        sut.removeWatcher(watcher);
        sut.setActive(true);
        sut.setActive(false);

        // Assert
        verify(watcher).onActiveChanged(true);
        verify(watcher).onActiveChanged(false);
        verifyNoMoreInteractions(watcher);
    }

    @Test
    void shouldNotifyWatchersOfStorageChanges(
        @InjectNetworkStorageChannel final StorageChannel<String> networkStorage
    ) {
        // Arrange
        final GridWatcher watcher = mock(GridWatcher.class);
        sut.addWatcher(watcher, FakeActor.class);

        // Act
        networkStorage.insert("A", 10, Action.EXECUTE, EmptyActor.INSTANCE);
        networkStorage.insert("A", 1, Action.EXECUTE, FakeActor.INSTANCE);
        sut.removeWatcher(watcher);
        networkStorage.insert("A", 1, Action.EXECUTE, FakeActor.INSTANCE);

        // Assert
        final ArgumentCaptor<String> resources = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<TrackedResource> trackedResources = ArgumentCaptor.forClass(TrackedResource.class);
        verify(watcher, times(2)).onChanged(
            eq(NetworkTestFixtures.STORAGE_CHANNEL_TYPE),
            resources.capture(),
            anyLong(),
            trackedResources.capture()
        );

        assertThat(resources.getAllValues()).containsExactly("A", "A");
        assertThat(trackedResources.getAllValues())
            .usingRecursiveFieldByFieldElementComparator()
            .containsExactly(null, new TrackedResource("Fake", 2));

        verifyNoMoreInteractions(watcher);
    }

    @Test
    void shouldNotBeAbleToRemoveUnknownWatcher() {
        // Arrange
        final GridWatcher watcher = mock(GridWatcher.class);

        // Act & assert
        assertThrows(IllegalArgumentException.class, () -> sut.removeWatcher(watcher));
    }

    @Test
    void shouldNotBeAbleToAddDuplicateWatcher() {
        // Arrange
        final GridWatcher watcher = mock(GridWatcher.class);
        final Class<? extends Actor> actorType1 = EmptyActor.class;
        final Class<? extends Actor> actorType2 = FakeActor.class;

        sut.addWatcher(watcher, actorType1);

        // Act & assert
        assertThrows(IllegalArgumentException.class, () -> sut.addWatcher(watcher, actorType1));
        assertThrows(IllegalArgumentException.class, () -> sut.addWatcher(watcher, actorType2));
    }

    @Test
    void shouldDetachWatchersFromOldNetworkAndReattachToNewNetwork(
        @InjectNetwork("other") final Network otherNetwork,
        @InjectNetwork final Network network,
        @InjectNetworkStorageChannel final StorageChannel<String> storageChannel,
        @InjectNetworkStorageChannel(networkId = "other") final StorageChannel<String> otherStorageChannel
    ) {
        // Arrange
        final GridWatcher watcher = mock(GridWatcher.class);
        sut.addWatcher(watcher, FakeActor.class);

        // Act
        otherStorageChannel.insert("C", 10, Action.EXECUTE, FakeActor.INSTANCE);

        sut.setNetwork(otherNetwork);
        network.removeContainer(() -> sut);
        otherNetwork.addContainer(() -> sut);

        otherStorageChannel.insert("A", 10, Action.EXECUTE, FakeActor.INSTANCE);
        otherStorageChannel.insert("D", 10, Action.EXECUTE, EmptyActor.INSTANCE);
        storageChannel.insert("B", 10, Action.EXECUTE, FakeActor.INSTANCE);
        storageChannel.insert("D", 10, Action.EXECUTE, EmptyActor.INSTANCE);

        // Assert
        verify(watcher, times(1)).onNetworkChanged();

        final ArgumentCaptor<TrackedResource> trackedResources1 = ArgumentCaptor.forClass(TrackedResource.class);
        verify(watcher, times(1)).onChanged(
            eq(NetworkTestFixtures.STORAGE_CHANNEL_TYPE),
            eq("A"),
            eq(10L),
            trackedResources1.capture()
        );
        assertThat(trackedResources1.getAllValues())
            .hasSize(1)
            .allMatch(t -> FakeActor.INSTANCE.getName().equals(t.getSourceName()));

        verify(watcher, times(1)).onChanged(
            eq(NetworkTestFixtures.STORAGE_CHANNEL_TYPE),
            eq("D"),
            eq(10L),
            isNull()
        );

        verifyNoMoreInteractions(watcher);
    }

    @Test
    void shouldCreateService(
        @InjectNetworkStorageChannel final StorageChannel<String> networkStorage
    ) {
        // Arrange
        final GridService<String> service = sut.create(
            NetworkTestFixtures.STORAGE_CHANNEL_TYPE,
            FakeActor.INSTANCE,
            r -> 5L,
            1
        );

        final InMemoryStorageImpl<String> source = new InMemoryStorageImpl<>();
        source.insert("Z", 10, Action.EXECUTE, EmptyActor.INSTANCE);

        // Act
        service.insert("Z", GridInsertMode.SINGLE_RESOURCE, source);
        final Collection<ResourceAmount<String>> afterSingle = networkStorage
            .getAll()
            .stream()
            .map(ra -> new ResourceAmount<>(ra.getResource(), ra.getAmount()))
            .toList();

        service.insert("Z", GridInsertMode.ENTIRE_RESOURCE, source);
        final Collection<ResourceAmount<String>> afterEntire = networkStorage
            .getAll()
            .stream()
            .map(ra -> new ResourceAmount<>(ra.getResource(), ra.getAmount()))
            .toList();

        // Assert
        assertThat(afterSingle).usingRecursiveFieldByFieldElementComparator().contains(
            new ResourceAmount<>("Z", 1)
        );
        assertThat(afterEntire).usingRecursiveFieldByFieldElementComparator().contains(
            new ResourceAmount<>("Z", 6)
        );
    }
}

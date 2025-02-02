package com.refinedmods.refinedstorage2.api.network.impl.node.exporter;

import com.refinedmods.refinedstorage2.api.core.Action;
import com.refinedmods.refinedstorage2.api.network.impl.node.exporter.scheduling.RandomExporterSchedulingMode;
import com.refinedmods.refinedstorage2.api.network.node.exporter.scheduling.ExporterSchedulingMode;
import com.refinedmods.refinedstorage2.api.network.node.exporter.strategy.ExporterTransferStrategy;
import com.refinedmods.refinedstorage2.api.resource.ResourceAmount;
import com.refinedmods.refinedstorage2.api.storage.EmptyActor;
import com.refinedmods.refinedstorage2.api.storage.InMemoryStorageImpl;
import com.refinedmods.refinedstorage2.api.storage.Storage;
import com.refinedmods.refinedstorage2.api.storage.channel.StorageChannel;
import com.refinedmods.refinedstorage2.network.test.InjectNetworkStorageChannel;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomExporterNetworkNodeTest extends AbstractExporterNetworkNodeTest {
    @Override
    protected ExporterSchedulingMode createSchedulingMode() {
        return new RandomExporterSchedulingMode(new RandomExporterSchedulingMode.Randomizer() {
            @Override
            @SuppressWarnings("unchecked")
            public <T> void shuffle(final List<T> list) {
                list.clear();
                list.add((T) "A");
                list.add((T) "B");
            }
        });
    }

    @Test
    void shouldTransfer(@InjectNetworkStorageChannel final StorageChannel<String> storageChannel) {
        // Arrange
        storageChannel.addSource(new InMemoryStorageImpl<>());
        storageChannel.insert("A", 100, Action.EXECUTE, EmptyActor.INSTANCE);
        storageChannel.insert("B", 100, Action.EXECUTE, EmptyActor.INSTANCE);

        final Storage<String> destination = new InMemoryStorageImpl<>();
        final ExporterTransferStrategy strategy = createTransferStrategy(destination, 5);

        sut.setTransferStrategy(strategy);
        sut.setFilterTemplates(List.of("B", "A"));

        // Act & assert
        sut.doWork();

        assertThat(storageChannel.getAll()).usingRecursiveFieldByFieldElementComparator().containsExactly(
            new ResourceAmount<>("A", 95),
            new ResourceAmount<>("B", 100)
        );
        assertThat(destination.getAll()).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrder(
            new ResourceAmount<>("A", 5)
        );

        sut.doWork();

        assertThat(storageChannel.getAll()).usingRecursiveFieldByFieldElementComparator().containsExactly(
            new ResourceAmount<>("A", 90),
            new ResourceAmount<>("B", 100)
        );
        assertThat(destination.getAll()).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrder(
            new ResourceAmount<>("A", 10)
        );
    }

    @Test
    void shouldUseNextResourceIfFirstOneIsNotAvailableInSameCycle(
        @InjectNetworkStorageChannel final StorageChannel<String> storageChannel
    ) {
        // Arrange
        storageChannel.addSource(new InMemoryStorageImpl<>());
        storageChannel.insert("B", 7, Action.EXECUTE, EmptyActor.INSTANCE);

        final Storage<String> destination = new InMemoryStorageImpl<>();
        final ExporterTransferStrategy strategy = createTransferStrategy(destination, 10);

        sut.setTransferStrategy(strategy);
        sut.setFilterTemplates(List.of("A", "B"));

        // Act & assert
        sut.doWork();

        assertThat(storageChannel.getAll()).isEmpty();
        assertThat(destination.getAll()).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrder(
            new ResourceAmount<>("B", 7)
        );

        sut.doWork();

        assertThat(storageChannel.getAll()).isEmpty();
        assertThat(destination.getAll()).usingRecursiveFieldByFieldElementComparator().containsExactlyInAnyOrder(
            new ResourceAmount<>("B", 7)
        );
    }
}

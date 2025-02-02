package com.refinedmods.refinedstorage2.api.storage.composite;

import com.refinedmods.refinedstorage2.api.storage.Storage;

import org.apiguardian.api.API;

/**
 * Represents the parent storage that a {@link CompositeAwareChild} can use to propagate changes to.
 *
 * @param <T> the type of resource
 */
@API(status = API.Status.STABLE, since = "2.0.0-milestone.1.4")
public interface ParentComposite<T> {
    /**
     * Called by the {@link CompositeAwareChild} to notify to this parent composite storage
     * that a source has been added.
     *
     * @param source the source
     */
    void onSourceAddedToChild(Storage<T> source);

    /**
     * Called by the {@link CompositeAwareChild} to notify to this parent composite storage
     * that a source has been removed.
     *
     * @param source the source
     */
    void onSourceRemovedFromChild(Storage<T> source);

    /**
     * Adds a resource to the composite storage cache.
     * Useful for the {@link ConsumingStorage} to propagate changes manually.
     *
     * @param resource the resource
     * @param amount   the amount
     */
    void addToCache(T resource, long amount);

    /**
     * Removes a resource from the composite storage cache.
     * Useful for the {@link ConsumingStorage} to propagate changes manually.
     *
     * @param resource the resource
     * @param amount   the amount
     */
    void removeFromCache(T resource, long amount);
}

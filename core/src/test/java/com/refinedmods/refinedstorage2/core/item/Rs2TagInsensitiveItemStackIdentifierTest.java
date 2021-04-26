package com.refinedmods.refinedstorage2.core.item;

import com.refinedmods.refinedstorage2.core.Rs2Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

@Rs2Test
class Rs2TagInsensitiveItemStackIdentifierTest {
    @Test
    void Test_equals_hashcode() {
        // Assert
        EqualsVerifier.forClass(Rs2TagInsensitiveItemStackIdentifier.class).verify();
    }
}

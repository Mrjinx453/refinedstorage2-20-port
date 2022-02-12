package com.refinedmods.refinedstorage2.platform.common.util;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public final class IdentifierUtil {
    public static final String MOD_ID = "refinedstorage2";

    private IdentifierUtil() {
    }

    public static ResourceLocation createIdentifier(String value) {
        return new ResourceLocation(MOD_ID, value);
    }

    public static String createTranslationKey(String category, String value) {
        return String.format("%s.%s.%s", category, MOD_ID, value);
    }

    public static TranslatableComponent createTranslation(String category, String value, Object... args) {
        return new TranslatableComponent(createTranslationKey(category, value), args);
    }
}

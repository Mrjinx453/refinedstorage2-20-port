package com.refinedmods.refinedstorage2.api.network.extension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AddDiskDrive {
    long baseEnergyUsage() default 0L;

    long energyUsagePerDisk() default 0L;

    String networkId() default "default";
}
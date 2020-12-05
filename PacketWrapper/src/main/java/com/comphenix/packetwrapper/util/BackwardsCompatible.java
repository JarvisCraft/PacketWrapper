package com.comphenix.packetwrapper.util;

import java.lang.annotation.*;

/**
 * Marker indicating the target's backwards compatibility.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface BackwardsCompatible {

    /**
     * Gets the target's {@link BackwardsCompatibility backwards compatibility}.
     *
     * @return the target's backwards compatibility.
     */
    BackwardsCompatibility value() default BackwardsCompatibility.FULL;
}

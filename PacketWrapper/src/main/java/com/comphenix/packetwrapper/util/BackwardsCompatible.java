package com.comphenix.packetwrapper.util;

import java.lang.annotation.*;

/**
 * Marker indicating the target is backwards compatible.
 * If values are not set
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface BackwardsCompatible {

    /**
     * Gets the minimal supported minor minecraft version.
     *
     * @return minimal supported minor minecraft version
     */
    int sinceMinor() default 8;

    /**
     * Gets the maximal supported minor minecraft version.
     *
     * @return maximal supported minor minecraft version
     */
    int untilMinor() default Integer.MAX_VALUE;
}

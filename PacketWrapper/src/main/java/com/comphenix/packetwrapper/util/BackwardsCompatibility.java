package com.comphenix.packetwrapper.util;

/**
 * Level of backwards compatibility relative to Minecraft version <i>1.7</i>.
 */
public enum BackwardsCompatibility {
    /**
     * The target is fully backwards compatible.
     */
    FULL,
    /**
     * The target is partially backwards compatible.
     */
    PARTIAL,
    /**
     * The target is not backwards compatible.
     */
    NONE
}

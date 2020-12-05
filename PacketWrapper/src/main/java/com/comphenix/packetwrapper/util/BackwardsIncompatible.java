package com.comphenix.packetwrapper.util;

import java.lang.annotation.*;

/**
 * Marker indicating the target is not backwards compatible.
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface BackwardsIncompatible {}

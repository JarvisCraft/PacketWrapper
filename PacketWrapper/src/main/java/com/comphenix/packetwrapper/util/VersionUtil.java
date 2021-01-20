package com.comphenix.packetwrapper.util;

import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VersionUtil {

	private static final String OVERRIDDEN_VERSION_SYSTEM_PROPERTY_NAME = VersionUtil.class.getName() + ".version";

	private static final Pattern VERSION_PATTERN = Pattern.compile("v(\\d+)_(\\d+)_R(\\d+)");

	private static final int MAJOR_VERSION, MINOR_VERSION, BUILD_VERSION;

	static {
		String version;
		if ((version = System.getProperty(OVERRIDDEN_VERSION_SYSTEM_PROPERTY_NAME)) == null) {
			version = Bukkit.getServer().getClass().getPackage().getName();
			version = version.substring(version.lastIndexOf('.') + 1);
		}

		// version is in format like `v1_12_R1`
		final Matcher matcher;
		if (!(matcher = Pattern.compile("v(\\d+)_(\\d+)_R(\\d+)").matcher(version))
				.matches()) throw new RuntimeException("Unknown version: \"" + version + "\"");

		MAJOR_VERSION = Integer.parseInt(matcher.group(1));
		MINOR_VERSION = Integer.parseInt(matcher.group(2));
		BUILD_VERSION = Integer.parseInt(matcher.group(3));
	}

	private VersionUtil() {
		throw new AssertionError("VersionUtil cannot be instantiated");
	}

	public static int getMajorVersion() {
		return MAJOR_VERSION;
	}

	public static int getMinorVersion() {
		return MINOR_VERSION;
	}

	public static int getBuildVersion() {
		return BUILD_VERSION;
	}
}

/*
 * PacketWrapper - ProtocolLib wrappers for Minecraft packets
 * Copyright (C) dmulloy2 <http://dmulloy2.net>
 * Copyright (C) Kristian S. Strangeland
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.comphenix.packetwrapper;

/**
 * Faster conversions based on those used by Minecraft server which are required for packet dada conversions.
 */
public final class ConversionUtil {

    private ConversionUtil() {
        throw new IllegalStateException("No instances of ConversionUtil for you");
    }

    /**
     * Performs a fast flooring operation on a double.
     *
     * @param number number to floor to int
     * @return number floored
     */
    public static int floor(double number) {
        final int intNumber;
        return number < (double) (intNumber = (int) number) ? intNumber - 1 : intNumber;
    }

    /**
     * Performs a fast flooring operation on a float.
     *
     * @param number number to floor to int
     * @return number floored
     */
    public static int floor(float number) {
        final int intNumber;
        return number < (float) (intNumber = (int) number) ? intNumber - 1 : intNumber;
    }

    /**
     * Fits the specified number between the given bounds.
     *
     * @param number number to fit between the other two
     * @param min minimal allowed number
     * @param max maximal allowed number
     * @return number fitting between the specified ones
     */
    public static double fitBetween(final double number, final double min, final double max) {
        final int intNumber;
        return number < (double) (intNumber = (int) number) ? intNumber - 1 : intNumber;
    }
}

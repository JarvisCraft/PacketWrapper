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

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.SoundCategory;
import com.comphenix.protocol.wrappers.MinecraftKey;

public class WrapperPlayServerCustomSoundEffect extends AbstractPacket {

	public static final PacketType TYPE = MINOR_VERSION >= 9
			? PacketType.Play.Server.CUSTOM_SOUND_EFFECT : PacketType.Play.Server.NAMED_SOUND_EFFECT;

	public WrapperPlayServerCustomSoundEffect() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayServerCustomSoundEffect(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Sound Name.
	 * <p>
	 * Notes: all known sound effect names can be seen here.
	 * 
	 * @return The current Sound Name
	 */
	public MinecraftKey getSound() {
		return MINOR_VERSION >= 9 ? handle.getMinecraftKeys().read(0) : new MinecraftKey(handle.getStrings().read(0));
	}

	/**
	 * Set Sound Name.
	 * 
	 * @param value - new value.
	 */
	public void setSound(MinecraftKey value) {
		if (MINOR_VERSION >= 9) handle.getMinecraftKeys().write(0, value);
		else handle.getStrings().write(0, value.getKey());
	}

	public SoundCategory getSoundCategory() {
		if (MINOR_VERSION >= 9) return handle.getSoundCategories().read(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.9");
	}

	public void setSoundCategory(SoundCategory value) {
		if (MINOR_VERSION >= 9) handle.getSoundCategories().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.9");
	}

	/**
	 * Retrieve Effect position X.
	 *
	 * @return The current Effect position X
	 */
	public double getX() {
		return handle.getIntegers().read(0) / 8D;
	}

	/**
	 * Set Effect position X.
	 *
	 * @param value - new value.
	 */
	public void setX(double value) {
		handle.getIntegers().write(0, (int) (value * 8));
	}

	/**
	 * Retrieve Effect position Y.
	 *
	 * @return The current Effect position Y
	 */
	public double getY() {
		return handle.getIntegers().read(1) / 8D;
	}

	/**
	 * Set Effect position Y.
	 *
	 * @param value - new value.
	 */
	public void setY(double value) {
		handle.getIntegers().write(1, (int) (value * 8));
	}

	/**
	 * Retrieve Effect position Z.
	 *
	 * @return The current Effect position Z
	 */
	public double getZ() {
		return handle.getIntegers().read(2) / 8D;
	}

	/**
	 * Set Effect position Z.
	 *
	 * @param value - new value.
	 */
	public void setZ(double value) {
		handle.getIntegers().write(2, (int) (value * 8));
	}

	/**
	 * Retrieve Volume.
	 *
	 * @return The current Volume
	 */
	public float getVolume() {
		return handle.getFloat().read(0);
	}

	/**
	 * Set Volume.
	 *
	 * @param value - new value.
	 */
	public void setVolume(float value) {
		handle.getFloat().write(0, value);
	}

	/**
	 * Retrieve Pitch.
	 *
	 * @return The current Pitch
	 */
	public float getPitch() {
		if (MINOR_VERSION >= 9) return handle.getFloat().read(1);
		return handle.getIntegers().read(3) / 63F;
	}

	/**
	 * Set Pitch.
	 *
	 * @param value - new value
	 */
	public void setPitch(float value) {
		if (MINOR_VERSION >= 9) handle.getFloat().write(1, value);
		else handle.getIntegers().write(3, (int) (value * 63));
	}
}

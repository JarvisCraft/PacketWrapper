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

import com.comphenix.packetwrapper.util.BackwardsCompatible;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.MinecraftKey;

@BackwardsCompatible(sinceMinor = 14)
public class WrapperPlayClientSetJigsaw extends AbstractPacket {

	public static final PacketType TYPE = PacketType.Play.Client.SET_JIGSAW;

	public WrapperPlayClientSetJigsaw() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientSetJigsaw(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Location.
	 * <p>
	 * Notes: block entity location
	 *
	 * @return The current Location
	 */
	public BlockPosition getLocation() {
		return handle.getBlockPositionModifier().read(0);
	}

	/**
	 * Set Location.
	 *
	 * @param value - new value.
	 */
	public void setLocation(BlockPosition value) {
		handle.getBlockPositionModifier().write(0, value);
	}

	/**
	 * Retrieve name.
	 *
	 * @return The current Attachment type
	 */
	@BackwardsCompatible(sinceMinor = 16)
	public MinecraftKey getName() {
		if (MINOR_VERSION >= 16) return handle.getMinecraftKeys().read(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.16");
	}

	/**
	 * Set name.
	 *
	 * @param value - new value.
	 */
	@BackwardsCompatible(sinceMinor = 16)
	public void setName(MinecraftKey value) {
		if (MINOR_VERSION >= 16) handle.getMinecraftKeys().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.16");
	}

	/**
	 * Retrieve target.
	 *
	 * @return The current Attachment type
	 */
	@BackwardsCompatible(sinceMinor = 16)
	public MinecraftKey getTarget() {
		if (MINOR_VERSION >= 16) return handle.getMinecraftKeys().read(1);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.16");
	}

	/**
	 * Set target.
	 *
	 * @param value - new value.
	 */
	@BackwardsCompatible(sinceMinor = 16)
	public void setTarget(MinecraftKey value) {
		if (MINOR_VERSION >= 16) handle.getMinecraftKeys().write(1, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.16");
	}

	/**
	 * Retrieve Attachment type.
	 *
	 * @return The current Attachment type
	 */
	@BackwardsCompatible(untilMinor = 15)
	public MinecraftKey getAttachmentType() {
		if (MINOR_VERSION <= 15) return handle.getMinecraftKeys().read(0);
		throw new UnsupportedOperationException("Unsupported on versions higher than 1.15");
	}

	/**
	 * Set Attachment type.
	 *
	 * @param value - new value.
	 */
	@BackwardsCompatible(untilMinor = 15)
	public void setAttachmentType(MinecraftKey value) {
		if (MINOR_VERSION <= 15) handle.getMinecraftKeys().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions higher than 1.15");
	}

	/**
	 * Retrieve Target pool.
	 *
	 * @return The current Target pool
	 */
	public MinecraftKey getTargetPool() {
		return handle.getMinecraftKeys().read(MINOR_VERSION >= 16 ? 2 : 1);
	}

	/**
	 * Set Target pool.
	 *
	 * @param value - new value.
	 */
	public void setTargetPool(MinecraftKey value) {
		handle.getMinecraftKeys().write(MINOR_VERSION >= 16 ? 2 : 1, value);
	}

	/**
	 * Retrieve Final state.
	 * <p>
	 * Notes: "Turns into" on the GUI, final_state in NBT
	 *
	 * @return The current Final state
	 */
	public String getFinalState() {
		return handle.getStrings().read(0);
	}

	/**
	 * Set Final state.
	 *
	 * @param value - new value.
	 */
	public void setFinalState(String value) {
		handle.getStrings().write(0, value);
	}

	@BackwardsCompatible(sinceMinor = 16)
	public JointType getJointType() {
		if (MINOR_VERSION >= 16) return handle
				.getEnumModifier(JointType.class, MinecraftReflection.getMinecraftClass("TileEntityJigsaw$JointType"))
				.readSafely(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.16");
	}

	@BackwardsCompatible(sinceMinor = 16)
	public void setJointType(JointType value) {
		if (MINOR_VERSION >= 16) handle
				.getEnumModifier(JointType.class, MinecraftReflection.getMinecraftClass("TileEntityJigsaw$JointType"))
				.writeSafely(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.16");
	}

	public enum JointType {
		ROLLABLE,
		ALIGNED;
	}

}

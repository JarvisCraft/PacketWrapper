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
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

@BackwardsCompatible
public class WrapperPlayClientUpdateSign extends AbstractPacket {
	public static final PacketType TYPE = PacketType.Play.Client.UPDATE_SIGN;

	public WrapperPlayClientUpdateSign() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientUpdateSign(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Location.
	 * <p>
	 * Notes: block Coordinates
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
	 * Retrieve this sign's lines of text.
	 *
	 * @return The current lines
	 */
	public String[] getLines() {
		if (MINOR_VERSION >= 9) return handle.getStringArrays().read(0);

		final WrappedChatComponent[] chatComponents = handle.getChatComponentArrays().read(0);
		assert chatComponents.length == 4 : "expected to have exactly 4 lines";

		return new String[]{
				chatComponents[0].getJson(),
				chatComponents[1].getJson(),
				chatComponents[2].getJson(),
				chatComponents[3].getJson()
		};
	}

	/**
	 * Set this sign's lines of text.
	 *
	 * @param value - Lines, must be 4 elements long
	 */
	public void setLines(String[] value) {
		if (value == null) throw new IllegalArgumentException("value cannot be null!");
		if (value.length != 4) throw new IllegalArgumentException("value must have  4 elements!");

		if (MINOR_VERSION >= 9) handle.getStringArrays().write(0, value);
		else handle.getChatComponentArrays().write(0, new WrappedChatComponent[]{
				WrappedChatComponent.fromText(value[0]),
				WrappedChatComponent.fromText(value[1]),
				WrappedChatComponent.fromText(value[2]),
				WrappedChatComponent.fromText(value[3])
		});
	}

	/**
	 * Retrieve this sign's lines of text.
	 *
	 * @return The current lines
	 */
	public WrappedChatComponent[] getLinesChatComponents() {
		if (MINOR_VERSION <= 8) return handle.getChatComponentArrays().read(0);

		final String[] lines = handle.getStringArrays().read(0);
		assert lines.length == 4 : "expected to have exactly 4 lines";

		return new WrappedChatComponent[]{
				WrappedChatComponent.fromText(lines[0]),
				WrappedChatComponent.fromText(lines[1]),
				WrappedChatComponent.fromText(lines[2]),
				WrappedChatComponent.fromText(lines[3])
		};
	}

	/**
	 * Set this sign's lines of text.
	 *
	 * @param value - Lines, must be 4 elements long
	 */
	public void setLinesChatComponents(WrappedChatComponent[] value) {
		if (value == null) throw new IllegalArgumentException("value cannot be null!");
		if (value.length != 4) throw new IllegalArgumentException("value must have  4 elements!");

		if (MINOR_VERSION <= 8) handle.getChatComponentArrays().write(0, value);
		else handle.getStringArrays().write(0, new String[]{
				value[0].getJson(),
				value[1].getJson(),
				value[2].getJson(),
				value[3].getJson()
		});
	}
}

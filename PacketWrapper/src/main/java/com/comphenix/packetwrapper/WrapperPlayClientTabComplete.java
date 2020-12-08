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

public class WrapperPlayClientTabComplete extends AbstractPacket {
	public static final PacketType TYPE = PacketType.Play.Client.TAB_COMPLETE;

	public WrapperPlayClientTabComplete() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientTabComplete(PacketContainer packet) {
		super(packet, TYPE);
	}

	public String getInput() {
		return handle.getStrings().read(0);
	}

	public void setInput(String value) {
		handle.getStrings().write(0, value);
	}

	@BackwardsCompatible(sinceMinor = 13)
	public int getTransactionId() {
		if (MINOR_VERSION >= 13) return handle.getIntegers().read(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.13");
	}

	@BackwardsCompatible(sinceMinor = 13)
	public void setTransactionId(int value) {
		if (MINOR_VERSION >= 13) handle.getIntegers().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.13");
	}

	@BackwardsCompatible(sinceMinor = 9, untilMinor = 12)
	public boolean getAssumeCommand() {
		if (MINOR_VERSION >= 9 && MINOR_VERSION <= 12) return handle.getBooleans().read(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.9 or higher than 1.12");
	}

	@BackwardsCompatible(sinceMinor = 9, untilMinor = 12)
	public void setAssumeCommand(boolean value) {
		if (MINOR_VERSION >= 9 && MINOR_VERSION <= 12) handle.getBooleans().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.9 or higher than 1.12");
	}

	/**
	 * Retrieve Location.
	 * <p>
	 * Notes: block entity location
	 *
	 * @return The current Location
	 */
	@BackwardsCompatible(untilMinor = 12)
	public BlockPosition getLocation() {
		if (MINOR_VERSION <= 12) return handle.getBlockPositionModifier().read(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.12");
	}

	/**
	 * Set Location.
	 *
	 * @param value - new value.
	 */
	@BackwardsCompatible(untilMinor = 12)
	public void setLocation(BlockPosition value) {
		if (MINOR_VERSION <= 12) handle.getBlockPositionModifier().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.12");
	}

}

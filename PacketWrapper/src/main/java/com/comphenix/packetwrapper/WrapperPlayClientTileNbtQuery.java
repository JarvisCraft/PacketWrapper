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

@BackwardsCompatible(sinceMinor = 13)
public class WrapperPlayClientTileNbtQuery extends AbstractPacket {

	public static final PacketType TYPE = PacketType.Play.Client.TILE_NBT_QUERY;

	public WrapperPlayClientTileNbtQuery() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientTileNbtQuery(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Transaction ID.
	 * <p>
	 * Notes: an incremental ID so that the client can verify that the response matches.
	 *
	 * @return The current Transaction ID
	 */
	public int getTransactionId() {
		return handle.getIntegers().read(0);
	}

	/**
	 * Set Transaction ID.
	 *
	 * @param value - new value.
	 */
	public void setTransactionId(int value) {
		handle.getIntegers().write(0, value);
	}

	/**
	 * Retrieve Location.
	 * <p>
	 * Notes: the location of the block to check.
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

}

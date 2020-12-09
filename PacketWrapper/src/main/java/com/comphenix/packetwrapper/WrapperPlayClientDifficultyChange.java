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
import com.comphenix.protocol.wrappers.EnumWrappers.Difficulty;

@BackwardsCompatible(sinceMinor = 14)
public class WrapperPlayClientDifficultyChange extends AbstractPacket {

	public static final PacketType TYPE = PacketType.Play.Client.DIFFICULTY_CHANGE;

	public WrapperPlayClientDifficultyChange() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientDifficultyChange(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve New difficulty.
	 * <p>
	 * Notes: 0: peaceful, 1: easy, 2: normal, 3: hard
	 *
	 * @return The current New difficulty
	 */
	public Difficulty getNewDifficulty() {
		return handle.getDifficulties().read(0);
	}

	/**
	 * Set New difficulty.
	 *
	 * @param value - new value.
	 */
	public void setNewDifficulty(Difficulty value) {
		handle.getDifficulties().write(0, value);
	}

}

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
import com.comphenix.protocol.wrappers.EnumWrappers.Hand;

@BackwardsCompatible
public class WrapperPlayClientArmAnimation extends AbstractPacket {
	public static final PacketType TYPE = PacketType.Play.Client.ARM_ANIMATION;

	public WrapperPlayClientArmAnimation() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientArmAnimation(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Hand.
	 *
	 * @return The current Hand
	 */
	@BackwardsCompatible(sinceMinor = 9)
	public Hand getHand() {
		if (MINOR_VERSION >= 9) return handle.getHands().read(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.9");
	}

	/**
	 * Set Hand.
	 *
	 * @param value - new value.
	 */
	@BackwardsCompatible(sinceMinor = 9)
	public void setHand(Hand value) {
		if (MINOR_VERSION >= 9) handle.getHands().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.9");
	}

	/**
	 * Retrieve timestamp.
	 *
	 * @return The current timestamp
	 */
	@BackwardsCompatible(untilMinor = 8)
	public long getTimestamp() {
		if (MINOR_VERSION <= 8) return handle.getLongs().read(0);
		throw new UnsupportedOperationException("Unsupported on versions higher than 1.8");
	}

	/**
	 * Set timestamp.
	 *
	 * @param value - new value.
	 */
	@BackwardsCompatible(untilMinor = 8)
	public void setTimestamp(long value) {
		if (MINOR_VERSION <= 8) handle.getLongs().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions higher than 1.8");
	}
}

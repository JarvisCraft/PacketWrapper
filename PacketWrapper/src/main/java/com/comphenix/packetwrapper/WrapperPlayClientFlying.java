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

@BackwardsCompatible
public class WrapperPlayClientFlying extends AbstractPacket {
	public static final PacketType TYPE = PacketType.Play.Client.FLYING;

	public WrapperPlayClientFlying() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientFlying(PacketContainer packet) {
		super(packet, TYPE);
	}

	public double getX() {
		return handle.getDoubles().read(0);
	}

	public void setX(double value) {
		handle.getDoubles().write(0, value);
	}

	public double getY() {
		return handle.getDoubles().read(1);
	}

	public void setY(double value) {
		handle.getDoubles().write(1, value);
	}

	public double getZ() {
		return handle.getDoubles().read(2);
	}

	public void setZ(double value) {
		handle.getDoubles().write(2, value);
	}

	public double getYaw() {
		return handle.getFloat().read(0);
	}

	public void setYaw(float value) {
		handle.getFloat().write(0, value);
	}

	public double getPitch() {
		return handle.getFloat().read(1);
	}

	public void setPitch(float value) {
		handle.getFloat().write(1, value);
	}

	/**
	 * Retrieve On Ground.
	 * <p>
	 * Notes: true if the client is on the ground, False otherwise
	 *
	 * @return The current On Ground
	 */
	public boolean getOnGround() {
		return handle.getBooleans().read(0);
	}

	/**
	 * Set On Ground.
	 *
	 * @param value - new value.
	 */
	public void setOnGround(boolean value) {
		handle.getBooleans().write(0, value);
	}

	public boolean getHasPos() {
		return handle.getBooleans().read(1);
	}

	public void setHasPos(boolean value) {
		handle.getBooleans().write(1, value);
	}

	public boolean getHasLook() {
		return handle.getBooleans().read(2);
	}

	public void setHasLook(boolean value) {
		handle.getBooleans().write(2, value);
	}

}

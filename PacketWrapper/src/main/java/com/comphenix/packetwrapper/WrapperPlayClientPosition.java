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
public class WrapperPlayClientPosition extends WrapperPlayClientFlying {
	public static final PacketType TYPE = PacketType.Play.Client.POSITION;

	public WrapperPlayClientPosition() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientPosition(PacketContainer packet) {
		super(packet, TYPE);
	}

	@Override
	public double getX() {
		return super.getX();
	}

	@Override
	public void setX(double value) {
		super.setX(value);
	}

	@Override
	public double getY() {
		return super.getY();
	}

	@Override
	public void setY(double value) {
		super.setY(value);
	}

	@Override
	public double getZ() {
		return super.getZ();
	}

	@Override
	public void setZ(double value) {
		super.setZ(value);
	}

}

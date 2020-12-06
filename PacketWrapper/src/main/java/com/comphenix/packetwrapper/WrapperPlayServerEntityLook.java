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
public class WrapperPlayServerEntityLook extends WrapperPlayServerEntity {
	public static final PacketType TYPE = PacketType.Play.Server.ENTITY_LOOK;

	public WrapperPlayServerEntityLook() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayServerEntityLook(PacketContainer packet) {
		super(packet, TYPE);
	}

	@Override
	public float getYaw() {
		return super.getYaw();
	}

	@Override
	public void setYaw(float value) {
		super.setYaw(value);
	}

	@Override
	public float getPitch() {
		return super.getPitch();
	}

	@Override
	public void setPitch(float value) {
		super.setPitch(value);
	}

	@Override
	public boolean getOnGround() {
		return super.getOnGround();
	}

	@Override
	public void setOnGround(boolean value) {
		super.setOnGround(value);
	}
}

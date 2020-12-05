/**
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

import org.bukkit.World;
import org.bukkit.entity.Entity;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

public class WrapperPlayServerEntity extends AbstractPacket {
	public static final PacketType TYPE = PacketType.Play.Server.ENTITY;

	protected WrapperPlayServerEntity(PacketContainer handle, PacketType type) {
		super(handle, type);
	}

	public WrapperPlayServerEntity() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayServerEntity(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Entity ID.
	 * <p>
	 * Notes: entity's ID
	 * 
	 * @return The current Entity ID
	 */
	public int getEntityID() {
		return handle.getIntegers().read(0);
	}

	/**
	 * Set Entity ID.
	 * 
	 * @param value - new value.
	 */
	public void setEntityID(int value) {
		handle.getIntegers().write(0, value);
	}

	/**
	 * Retrieve the entity of the painting that will be spawned.
	 * 
	 * @param world - the current world of the entity.
	 * @return The spawned entity.
	 */
	public Entity getEntity(World world) {
		return handle.getEntityModifier(world).read(0);
	}

	/**
	 * Retrieve the entity of the painting that will be spawned.
	 * 
	 * @param event - the packet event.
	 * @return The spawned entity.
	 */
	public Entity getEntity(PacketEvent event) {
		return getEntity(event.getPlayer().getWorld());
	}

	/**
	 * Retrieve DX.
	 *
	 * @return The current DX
	 */
	protected double getDx() {
		if (MINOR_VERSION > 8) return handle.getIntegers().read(1) / 4096D;
		return handle.getBytes().read(0) / 32D;
	}

	/**
	 * Set DX.
	 *
	 * @param value - new value.
	 */
	protected void setDx(double value) {
		if (MINOR_VERSION > 8) handle.getIntegers().write(1, (int) (value * 4096));
		else handle.getBytes().write(0, (byte) (value * 32));
	}

	/**
	 * Retrieve DY.
	 *
	 * @return The current DY
	 */
	protected double getDy() {
		if (MINOR_VERSION > 8) return handle.getIntegers().read(2) / 4096D;
		return handle.getBytes().read(1) / 32D;
	}

	/**
	 * Set DY.
	 *
	 * @param value - new value.
	 */
	protected void setDy(double value) {
		if (MINOR_VERSION > 8) handle.getIntegers().write(2, (int) (value * 4096));
		else handle.getBytes().write(1, (byte) (value * 32));
	}

	/**
	 * Retrieve DZ.
	 *
	 * @return The current DZ
	 */
	protected double getDz() {
		if (MINOR_VERSION > 8) return handle.getIntegers().read(3) / 4096D;
		return handle.getBytes().read(2) / 32D;
	}

	/**
	 * Set DZ.
	 *
	 * @param value - new value.
	 */
	protected void setDz(double value) {
		if (MINOR_VERSION > 8) handle.getIntegers().write(3, (int) (value * 4096));
		else handle.getBytes().write(2, (byte) (value * 32));
	}

	/**
	 * Retrieve the yaw of the current entity.
	 *
	 * @return The current Yaw
	 */
	protected float getYaw() {
		return (handle.getBytes().read(MINOR_VERSION > 8 ? 0 : 3) * 360.F) / 256.0F;
	}

	/**
	 * Set the yaw of the current entity.
	 *
	 * @param value - new yaw.
	 */
	protected void setYaw(float value) {
		handle.getBytes().write(MINOR_VERSION > 8 ? 0 : 3, (byte) (value * 256.0F / 360.0F));
	}

	/**
	 * Retrieve the pitch of the current entity.
	 *
	 * @return The current pitch
	 */
	protected float getPitch() {
		return (handle.getBytes().read(MINOR_VERSION > 8 ? 1 : 4) * 360.F) / 256.0F;
	}

	/**
	 * Set the pitch of the current entity.
	 *
	 * @param value - new pitch.
	 */
	protected void setPitch(float value) {
		handle.getBytes().write(MINOR_VERSION > 8 ? 1 : 4, (byte) (value * 256.0F / 360.0F));
	}

	/**
	 * Retrieve On Ground.
	 *
	 * @return The current On Ground
	 */
	protected boolean getOnGround() {
		return handle.getBooleans().read(0);
	}

	/**
	 * Set On Ground.
	 *
	 * @param value - new value.
	 */
	protected void setOnGround(boolean value) {
		handle.getBooleans().write(0, value);
	}
}

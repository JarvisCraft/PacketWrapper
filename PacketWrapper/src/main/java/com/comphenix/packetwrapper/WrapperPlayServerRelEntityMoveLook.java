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

public class WrapperPlayServerRelEntityMoveLook extends WrapperPlayServerEntity {
	public static final PacketType TYPE =
			PacketType.Play.Server.REL_ENTITY_MOVE_LOOK;

	public WrapperPlayServerRelEntityMoveLook() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayServerRelEntityMoveLook(PacketContainer packet) {
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

	@Override
	public double getDx() {
		return super.getDx();
	}

	@Override
	public void setDx(double value) {
		super.setDx(value);
	}

	@Override
	public double getDy() {
		return super.getDy();
	}

	@Override
	public void setDy(double value) {
		super.setDy(value);
	}

	@Override
	public double getDz() {
		return super.getDz();
	}

	@Override
	public void setDz(double value) {
		super.setDz(value);
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

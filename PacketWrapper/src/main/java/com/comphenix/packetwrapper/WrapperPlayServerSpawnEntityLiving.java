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
import com.comphenix.packetwrapper.util.EntityUtil;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.PacketConstructor;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.UUID;

@BackwardsCompatible
public class WrapperPlayServerSpawnEntityLiving extends AbstractPacket {
	public static final PacketType TYPE = PacketType.Play.Server.SPAWN_ENTITY_LIVING;

	private static PacketConstructor entityConstructor;

	public WrapperPlayServerSpawnEntityLiving() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayServerSpawnEntityLiving(PacketContainer packet) {
		super(packet, TYPE);
	}

	public WrapperPlayServerSpawnEntityLiving(Entity entity) {
		super(fromEntity(entity), TYPE);
	}

	// Useful constructor
	private static PacketContainer fromEntity(Entity entity) {
		if (entityConstructor == null) entityConstructor = protocolManager().createPacketConstructor(TYPE, entity);
		return entityConstructor.createPacket(entity);
	}

	/**
	 * Retrieve entity ID.
	 *
	 * @return The current EID
	 */
	public int getEntityID() {
		return handle.getIntegers().read(0);
	}

	/**
	 * Retrieve the entity that will be spawned.
	 *
	 * @param world - the current world of the entity.
	 * @return The spawned entity.
	 */
	public Entity getEntity(World world) {
		return handle.getEntityModifier(world).read(0);
	}

	/**
	 * Retrieve the entity that will be spawned.
	 *
	 * @param event - the packet event.
	 * @return The spawned entity.
	 */
	public Entity getEntity(PacketEvent event) {
		return getEntity(event.getPlayer().getWorld());
	}

	public UUID getUniqueId() {
		if (MINOR_VERSION >= 9) return handle.getUUIDs().read(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.9");
	}

	public void setUniqueId(UUID value) {
		if (MINOR_VERSION >= 9) handle.getUUIDs().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.9");
	}

	/**
	 * Set entity ID.
	 *
	 * @param value - new value.
	 */
	public void setEntityID(int value) {
		handle.getIntegers().write(0, value);
	}

	/**
	 * Retrieve the type of mob.
	 *
	 * @return The current Type
	 */
	public EntityType getType() {
		return EntityUtil.getEntityTypeById(handle.getIntegers().read(1));
	}

	/**
	 * Set the type of mob.
	 *
	 * @param value - new value.
	 */
	public void setType(EntityType value) {
		handle.getIntegers().write(1, EntityUtil.getTypeId(value));
	}

	/**
	 * Retrieve the x position of the object.
	 * <p>
	 * Note that the coordinate is rounded off to the nearest 1/32 of a meter.
	 *
	 * @return The current X
	 */
	public double getX() {
		return MINOR_VERSION >= 9 ? handle.getDoubles().read(0) : handle.getIntegers().read(2) / 32.0D;
	}

	/**
	 * Set the x position of the object.
	 *
	 * @param value - new value.
	 */
	public void setX(double value) {
		if (MINOR_VERSION >= 9) handle.getDoubles().write(0, value);
		else handle.getIntegers().write(2, ConversionUtil.floor(value * 32.0D));
	}

	/**
	 * Retrieve the y position of the object.
	 * <p>
	 * Note that the coordinate is rounded off to the nearest 1/32 of a meter.
	 *
	 * @return The current y
	 */
	public double getY() {
		return MINOR_VERSION >= 9 ? handle.getDoubles().read(1) : handle.getIntegers().read(3) / 32.0D;
	}

	/**
	 * Set the y position of the object.
	 *
	 * @param value - new value.
	 */
	public void setY(double value) {
		if (MINOR_VERSION >= 9) handle.getDoubles().write(1, value);
		else handle.getIntegers().write(3, ConversionUtil.floor(value * 32.0D));
	}

	/**
	 * Retrieve the z position of the object.
	 * <p>
	 * Note that the coordinate is rounded off to the nearest 1/32 of a meter.
	 *
	 * @return The current z
	 */
	public double getZ() {
		return MINOR_VERSION >= 9 ? handle.getDoubles().read(2) : handle.getIntegers().read(4) / 32.0D;
	}

	/**
	 * Set the z position of the object.
	 *
	 * @param value - new value.
	 */
	public void setZ(double value) {
		if (MINOR_VERSION >= 9) handle.getDoubles().write(2, value);
		else handle.getIntegers().write(4, ConversionUtil.floor(value * 32.0D));
	}

	/**
	 * Retrieve the yaw.
	 *
	 * @return The current Yaw
	 */
	public float getYaw() {
		return (handle.getBytes().read(0) * 360.F) / 256.0F;
	}

	/**
	 * Set the yaw of the spawned mob.
	 *
	 * @param value - new yaw.
	 */
	public void setYaw(float value) {
		handle.getBytes().write(0, (byte) (int) (value * 256.0F / 360.0F));
	}

	/**
	 * Retrieve the pitch.
	 *
	 * @return The current pitch
	 */
	public float getPitch() {
		return (handle.getBytes().read(1) * 360.F) / 256.0F;
	}

	/**
	 * Set the pitch of the spawned mob.
	 *
	 * @param value - new pitch.
	 */
	public void setPitch(float value) {
		handle.getBytes().write(1, (byte) (int) (value * 256.0F / 360.0F));
	}

	/**
	 * Retrieve the yaw of the mob's head.
	 *
	 * @return The current yaw.
	 */
	public float getHeadPitch() {
		return (handle.getBytes().read(2) * 360.F) / 256.0F;
	}

	/**
	 * Set the yaw of the mob's head.
	 *
	 * @param value - new yaw.
	 */
	public void setHeadPitch(float value) {
		handle.getBytes().write(2, (byte) (int) (value * 256.0F / 360.0F));
	}

	/**
	 * Retrieve the velocity in the x axis.
	 *
	 * @return The current velocity X
	 */
	public double getVelocityX() {
		return handle.getIntegers().read(MINOR_VERSION >= 9 ? 2 : 5) / 8000.0D;
	}

	/**
	 * Set the velocity in the x axis.
	 *
	 * @param value - new value.
	 */
	public void setVelocityX(double value) {
		handle.getIntegers().write(MINOR_VERSION >= 9 ? 2 : 5, (int) (ConversionUtil.fitBetween(value, -3.9, 3.9) * 8000.0D));
	}

	/**
	 * Retrieve the velocity in the y axis.
	 *
	 * @return The current velocity y
	 */
	public double getVelocityY() {
		return handle.getIntegers().read(MINOR_VERSION >= 9 ? 3 : 6) / 8000.0D;
	}

	/**
	 * Set the velocity in the y axis.
	 *
	 * @param value - new value.
	 */
	public void setVelocityY(double value) {
		handle.getIntegers().write(MINOR_VERSION >= 9 ? 3 : 6, (int) (ConversionUtil.fitBetween(value, -3.9, 3.9) * 8000.0D));
	}

	/**
	 * Retrieve the velocity in the z axis.
	 *
	 * @return The current velocity z
	 */
	public double getVelocityZ() {
		return handle.getIntegers().read(MINOR_VERSION >= 9 ? 4 : 7) / 8000.0D;
	}

	/**
	 * Set the velocity in the z axis.
	 *
	 * @param value - new value.
	 */
	public void setVelocityZ(double value) {
		handle.getIntegers().write(MINOR_VERSION >= 9 ? 4 : 7, (int) (ConversionUtil.fitBetween(value, -3.9, 3.9) * 8000.0D));
	}

	/**
	 * Retrieve the data watcher.
	 * <p>
	 * Content varies by mob, see Entities.
	 *
	 * @return The current Metadata
	 */
	@BackwardsCompatible(untilMinor = 14)
	public WrappedDataWatcher getMetadata() {
		if (MINOR_VERSION >= 15) throw new UnsupportedOperationException("Unsupported on versions greater than 1.14");
		return handle.getDataWatcherModifier().read(0);
	}

	/**
	 * Set the data watcher.
	 *
	 * @param value - new value.
	 */
	@BackwardsCompatible(untilMinor = 14)
	public void setMetadata(WrappedDataWatcher value) {
		if (MINOR_VERSION >= 15) throw new UnsupportedOperationException("Unsupported on versions greater than 1.14");
		handle.getDataWatcherModifier().write(0, value);
	}
}

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
import org.bukkit.potion.PotionEffectType;

@BackwardsCompatible(sinceMinor = 13)
public class WrapperPlayClientBeacon extends AbstractPacket {

	public static final PacketType TYPE = PacketType.Play.Client.BEACON;

	public WrapperPlayClientBeacon() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientBeacon(PacketContainer packet) {
		super(packet, TYPE);
	}

	/**
	 * Retrieve Primary Effect.
	 * <p>
	 * Notes: a Potion ID. (Was a full Integer for the plugin message)
	 * @return The current Primary Effect
	 */
	public int getPrimaryEffectId() {
		return handle.getIntegers().read(0);
	}

	/**
	 * Retrieve Primary Effect.
	 * @return The current Primary Effect
	 */
	@SuppressWarnings("deprecation")
	public PotionEffectType getPrimaryEffect() {
		return PotionEffectType.getById(getPrimaryEffectId());
	}

	/**
	 * Set Primary Effect.
	 * @param value - new value.
	 */
	public void setPrimaryEffectId(int value) {
		handle.getIntegers().write(0, value);
	}

	/**
	 * Set Primary Effect.
	 * @param value - new value.
	 */
	public void setPrimaryEffect(PotionEffectType value) {
		setPrimaryEffectId(value.getId());
	}

	/**
	 * Retrieve Secondary Effect.
	 * <p>
	 * Notes: a Potion ID. (Was a full Integer for the plugin message)
	 * @return The current Secondary Effect
	 */
	public int getSecondaryEffectId() {
		return handle.getIntegers().read(1);
	}

	/**
	 * Retrieve Secondary Effect.
	 * @return The current Secondary Effect
	 */
	@SuppressWarnings("deprecation")
	public PotionEffectType getSecondaryEffect() {
		return PotionEffectType.getById(getSecondaryEffectId());
	}

	/**
	 * Set Secondary Effect.
	 * @param value - new value.
	 */
	public void setSecondaryEffectId(int value) {
		handle.getIntegers().write(1, value);
	}

	/**
	 * Set Secondary Effect.
	 * @param value - new value.
	 */
	@SuppressWarnings("deprecation")
	public void setSecondaryEffect(PotionEffectType value) {
		setPrimaryEffectId(value.getId());
	}
}

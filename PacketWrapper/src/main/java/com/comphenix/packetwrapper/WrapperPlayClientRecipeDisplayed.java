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

@BackwardsCompatible(sinceMinor = 13)
// TODO: this packet is present on 1.12 but uses IRecipe
// TODO: layout is too different on different versions
public class WrapperPlayClientRecipeDisplayed extends AbstractPacket {
	public static final PacketType TYPE = PacketType.Play.Client.RECIPE_DISPLAYED;

	public WrapperPlayClientRecipeDisplayed() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientRecipeDisplayed(PacketContainer packet) {
		super(packet, TYPE);
	}

	public Status getStatus() {
		return handle.getEnumModifier(Status.class, 0).readSafely(0);
	}

	public void setStatus(Status value) {
		handle.getEnumModifier(Status.class, 0).writeSafely(0, value);
	}

	// Modifier for recipe can be created upon request

	public boolean isBookOpen() {
		return handle.getBooleans().read(0);
	}

	public void setBookOpen(boolean value) {
		handle.getBooleans().write(0, value);
	}

	public boolean isFilterActive() {
		return handle.getBooleans().read(1);
	}

	public void setFilterActive(boolean value) {
		handle.getBooleans().write(1, value);
	}

	public enum Status {
		SHOWN,
		SETTINGS;
	}
}

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

import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.inventory.ItemStack;

@BackwardsCompatible(sinceMinor = 13)
public class WrapperPlayClientBookEdit extends AbstractPacket {

    public static final PacketType TYPE = PacketType.Play.Client.B_EDIT;
    
    public WrapperPlayClientBookEdit() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }
    
    public WrapperPlayClientBookEdit(PacketContainer packet) {
        super(packet, TYPE);
    }
    
    /**
     * Retrieve New book.
     * @return The current New book
     */
    public ItemStack getNewBook() {
        return handle.getItemModifier().read(0);
    }
    
    /**
     * Set New book.
     * @param value - new value.
     */
    public void setNewBook(ItemStack value) {
        handle.getItemModifier().write(0, value);
    }
    
    /**
     * Retrieve Is signing.
     * <p>
     * Notes: true if the player is signing the book; false if the player is saving a draft.
     * @return The current Is signing
     */
    public boolean getIsSigning() {
        return handle.getBooleans().read(0);
    }
    
    /**
     * Set Is signing.
     * @param value - new value.
     */
    public void setIsSigning(boolean value) {
        handle.getBooleans().write(0, value);
    }

    public EnumWrappers.Hand getHand() {
        return MINOR_VERSION >= 16
                ? handle.getIntegers().read(0) == 0 ? EnumWrappers.Hand.MAIN_HAND : EnumWrappers.Hand.OFF_HAND
                : handle.getHands().read(0);
    }

    public void setHand(EnumWrappers.Hand value) {
        if (MINOR_VERSION >= 16) handle.getIntegers().write(0, value == EnumWrappers.Hand.MAIN_HAND ? 0 : 1);
        else handle.getHands().write(0, value);
    }
}

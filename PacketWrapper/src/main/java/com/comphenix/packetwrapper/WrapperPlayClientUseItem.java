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
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.AutoWrapper;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.BukkitConverters;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.EnumWrappers.Direction;
import com.comphenix.protocol.wrappers.EnumWrappers.Hand;
import org.bukkit.util.Vector;

/*
 * The packet layout is a bit complicated:
 * on versions prior to 1.14 it has sparse fields for data:
 * {BlockPosition position, Direction face, float x, float y, float z}
 * on 1.14 and later the fields got packed into a separate class which should be proxied here
 */
@BackwardsCompatible(sinceMinor = 9)
public class WrapperPlayClientUseItem extends AbstractPacket {

	public static final PacketType TYPE = PacketType.Play.Client.USE_ITEM;

	public WrapperPlayClientUseItem() {
		super(new PacketContainer(TYPE), TYPE);
		handle.getModifier().writeDefaults();
	}

	public WrapperPlayClientUseItem(PacketContainer packet) {
		super(packet, TYPE);
	}

	public MovingObjectPosition getPosition() {
		if (MINOR_VERSION >= 14) return movingObjectPositionModifier().read(0);

		final float x, y, z;
		{
			final StructureModifier<Float> floatModifier = handle.getFloat();
			x = floatModifier.read(0);
			y = floatModifier.read(1);
			z = floatModifier.read(2);
		}

		return new MovingObjectPosition(
				new Vector(x, y, z),
				handle.getDirections().read(0),
				handle.getBlockPositionModifier().read(0),
				false /* undefined */, false /* undefined */
		);
	}

	public void setPosition(MovingObjectPosition position) {
		if (MINOR_VERSION >= 14) movingObjectPositionModifier().write(0, position);
		else {
			handle.getBlockPositionModifier().write(0, position.getLocation());
			handle.getDirections().write(0, position.getFace());
			{
				final StructureModifier<Float> floatModifier = handle.getFloat();
				// cached into a field not to access non-final field repeatedly
				final Vector cursorPosition = position.getCursorPosition();
				floatModifier.write(0, (float) cursorPosition.getX());
				floatModifier.write(1, (float) cursorPosition.getY());
				floatModifier.write(2, (float) cursorPosition.getZ());
			}
			// insideBlock is undefined
		}
	}

	/**
	 * Retrieve Location.
	 * <p>
	 * Notes: block position
	 *
	 * @return The current Location
	 */
	public BlockPosition getLocation() {
		return MINOR_VERSION <= 13
				? handle.getBlockPositionModifier().read(0)
				: movingObjectPositionModifier().read(0).getLocation();
	}

	/**
	 * Set Location.
	 *
	 * @param value - new value.
	 */
	public void setLocation(BlockPosition value) {
		if (MINOR_VERSION <= 13) handle.getBlockPositionModifier().write(0, value);
		else {
			final StructureModifier<MovingObjectPosition> modifier = movingObjectPositionModifier();
			final MovingObjectPosition modified = modifier.read(0);
			modified.setLocation(value);
			modifier.write(0, modified);
		}
	}

	public Direction getFace() {
		return MINOR_VERSION <= 13
				? handle.getDirections().read(0)
				: movingObjectPositionModifier().read(0).getFace();
	}

	public void setFace(Direction value) {
		if (MINOR_VERSION <= 13) handle.getDirections().write(0, value);
		else {
			final StructureModifier<MovingObjectPosition> modifier = movingObjectPositionModifier();
			final MovingObjectPosition modified = modifier.read(0);
			modified.setFace(value);
			modifier.write(0, modified);
		}
	}

	/**
	 * Retrieve Cursor Position X.
	 * <p>
	 * Notes: the position of the crosshair on the block, from 0 to 15
	 * increasing from west to east
	 *
	 * @return The current Cursor Position X
	 */
	public float getCursorPositionX() {
		return MINOR_VERSION <= 13
				? handle.getFloat().read(0)
				: movingObjectPositionModifier().read(0).getCursorPositionX();
	}

	/**
	 * Set Cursor Position X.
	 *
	 * @param value - new value.
	 */
	public void setCursorPositionX(float value) {
		if (MINOR_VERSION <= 13) handle.getFloat().write(0, value);
		else {
			final StructureModifier<MovingObjectPosition> modifier = movingObjectPositionModifier();
			final MovingObjectPosition modified = modifier.read(0);
			modified.setCursorPositionX(value);
			modifier.write(0, modified);
		}
	}

	/**
	 * Retrieve Cursor Position Y.
	 * <p>
	 * Notes: the position of the crosshair on the block, from 0 to 15
	 * increasing from bottom to top
	 *
	 * @return The current Cursor Position Y
	 */
	public float getCursorPositionY() {
		return MINOR_VERSION <= 13
				? handle.getFloat().read(1)
				: movingObjectPositionModifier().read(0).getCursorPositionY();
	}

	/**
	 * Set Cursor Position Y.
	 *
	 * @param value - new value.
	 */
	public void setCursorPositionY(float value) {
		if (MINOR_VERSION <= 13) handle.getFloat().write(1, value);
		else {
			final StructureModifier<MovingObjectPosition> modifier = movingObjectPositionModifier();
			final MovingObjectPosition modified = modifier.read(0);
			modified.setCursorPositionY(value);
			modifier.write(0, modified);
		}
	}

	/**
	 * Retrieve Cursor Position Z.
	 * <p>
	 * Notes: the position of the crosshair on the block, from 0 to 15
	 * increasing from north to south
	 *
	 * @return The current Cursor Position Z
	 */
	public float getCursorPositionZ() {
		return MINOR_VERSION <= 13
				? handle.getFloat().read(2)
				: movingObjectPositionModifier().read(0).getCursorPositionZ();
	}

	/**
	 * Set Cursor Position Z.
	 *
	 * @param value - new value.
	 */
	public void setCursorPositionZ(float value) {
		if (MINOR_VERSION <= 13) handle.getFloat().write(2, value);
		else {
			final StructureModifier<MovingObjectPosition> modifier = movingObjectPositionModifier();
			final MovingObjectPosition modified = modifier.read(0);
			modified.setCursorPositionZ(value);
			modifier.write(0, modified);
		}
	}

	public Hand getHand() {
		return handle.getHands().read(0);
	}

	public void setHand(Hand value) {
		handle.getHands().write(0, value);
	}

	public long getTimestamp() {
		if (MINOR_VERSION >= 10) return handle.getLongs().read(0);
		throw new UnsupportedOperationException("Unsupported on versions less than 1.10");
	}

	public void setTimestamp(long value) {
		if (MINOR_VERSION >= 10) handle.getLongs().write(0, value);
		else throw new UnsupportedOperationException("Unsupported on versions less than 1.10");
	}


	/**
	 * Gets a {@link MovingObjectPosition} modifier for {@link #handle}
	 * @return {@link MovingObjectPosition} modifier for {@link #handle}
	 */
	protected StructureModifier<MovingObjectPosition> movingObjectPositionModifier() {
		return handle.getModifier().withType(
				MovingObjectPosition.AutoWrapperContainer.POSITION_CLASS,
				MovingObjectPosition.AutoWrapperContainer.AUTO_WRAPPER
		);
	}

	public static final class MovingObjectPosition {

		// representation of cursor offset on the block
		// for some reason native layout uses double-based object internally although serializing to floats
		private Vector cursorPosition; // previously (float x, float y, float z) in packet layout
		private Direction face;
		private BlockPosition location;
		// TODO enums instead of booleans to support `undefined` state
		private boolean miss;
		private boolean insideBlock;

		public MovingObjectPosition(Vector cursorPosition, Direction face, BlockPosition location,
		                            boolean miss, boolean insideBlock) {
			this.cursorPosition = cursorPosition;
			this.face = face;
			this.location = location;
			this.miss = miss;
			this.insideBlock = insideBlock;
		}

		public Direction getFace() {
			return face;
		}

		public void setFace(Direction face) {
			this.face = face;
		}

		public BlockPosition getLocation() {
			return location;
		}

		public void setLocation(BlockPosition location) {
			this.location = location;
		}

		public Vector getCursorPosition() {
			return cursorPosition;
		}

		public void setCursorPosition(Vector cursorPosition) {
			this.cursorPosition = cursorPosition;
		}

		public float getCursorPositionX() {
			return (float) cursorPosition.getX();
		}

		public void setCursorPositionX(final float value) {
			cursorPosition.setX(value);
		}

		public float getCursorPositionY() {
			return (float) cursorPosition.getX();
		}

		public void setCursorPositionY(final float value) {
			cursorPosition.setY(value);
		}

		public float getCursorPositionZ() {
			return (float) cursorPosition.getZ();
		}

		public void setCursorPositionZ(final float value) {
			cursorPosition.setZ(value);
		}

		public boolean getMiss() {
			return miss;
		}

		public void setMiss(boolean miss) {
			this.miss = miss;
		}

		public boolean isInsideBlock() {
			return insideBlock;
		}

		public void setInsideBlock(boolean insideBlock) {
			this.insideBlock = insideBlock;
		}

		/**
		 * Container for {@link MovingObjectPosition}'s {@link AutoWrapper} used to have it lazily initialized.
		 */
		protected static final class AutoWrapperContainer {

			public static final Class<?> POSITION_CLASS
					= MinecraftReflection.getMinecraftClass("MovingObjectPositionBlock");

			private static final AutoWrapper<MovingObjectPosition> AUTO_WRAPPER = AutoWrapper
					.wrap(MovingObjectPosition.class, POSITION_CLASS /* null on versions before 1.14 */ )
					.field(0, BukkitConverters.getVectorConverter())
					.field(1, EnumWrappers.getDirectionConverter())
					.field(2, BlockPosition.getConverter());

			//<editor-fold desc="Prohibited constructor" defaultstate="collapsed">
			private AutoWrapperContainer() {
				throw new AssertionError("AutoWrapperContainer cannot be instantiated");
			}
			//</editor-fold>
		}
	}
}

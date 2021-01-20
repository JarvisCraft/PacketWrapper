package com.comphenix.packetwrapper.util;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utilities related to entities.
 */
@SuppressWarnings("deprecation") // legacy methods
public final class EntityUtil {

	private static final int MINOR_VERSION;
	private static final Map<@NotNull EntityType, @NotNull Integer> ENTITY_TYPE_IDS;
	private static final Map<@NotNull Integer, @NotNull EntityType> ENTITY_TYPES;

	static {
		{
			final int majorVersion;
			if ((majorVersion = VersionUtil.getMajorVersion()) != 1) throw new Error(
					"Major Minecraft version " + majorVersion + " is unsupported"
			);
		}
		MINOR_VERSION = VersionUtil.getMinorVersion();

		if (MINOR_VERSION >= 13) {
			final Collection<EntityType> entityTypes = EnumSet.allOf(EntityType.class);
			entityTypes.remove(EntityType.UNKNOWN);
			entityTypes.remove(EntityType.PLAYER);
			entityTypes.remove(EntityType.FISHING_HOOK);

			final int[] id = {0};
			ENTITY_TYPE_IDS = entityTypes
					.stream()
					.map(Enum::name)
					.sorted()
					.collect(Collectors.toMap(EntityType::valueOf, name -> id[0]++, (left, right) -> {
						throw new AssertionError("Enums cannot have duplicate names");
					}, () -> new EnumMap<>(EntityType.class)));
			ENTITY_TYPE_IDS.put(EntityType.PLAYER, id[0]++);
			ENTITY_TYPE_IDS.put(EntityType.FISHING_HOOK, id[0]++);
			ENTITY_TYPE_IDS.put(EntityType.UNKNOWN, -1);
		} else ENTITY_TYPE_IDS = Arrays.stream(EntityType.values())
				.collect(Collectors.toMap(Function.identity(), type -> (int) type.getTypeId(), (left, right) -> {
					throw new AssertionError("Enums cannot have duplicate names");
				}, () -> new EnumMap<>(EntityType.class)));

		ENTITY_TYPES = ENTITY_TYPE_IDS.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
	}

	private EntityUtil() {
		throw new AssertionError("EntityUtil cannot be instantiated");
	}

	/**
	 * Gets the type ID of the given entity.
	 * Note that {@link EntityType#getTypeId()} is incorrect since 1.13 as it represents legacy IDs.
	 * It seems that sine 1.13 IDs are ordered alphabetically with some minor exceptions.
	 *
	 * @param entityType type for which to get the ID
	 * @return type ID of the given entity type
	 */
	public static int getTypeId(final EntityType entityType) {
		if (entityType == null) return -1;

		return ENTITY_TYPE_IDS.get(entityType); // the value is always present
	}

	/**
	 * Gets the entity type by the given ID.
	 * Note that {@link EntityType#fromId(int)} is incorrect since 1.13 as it is based on legacy IDs.
	 * It seems that sine 1.13 IDs are ordered alphabetically with some minor exceptions.
	 *
	 * @param id entity type ID
	 * @return entity with the given type ID or {@link EntityType#UNKNOWN} if there is none
	 */
	public static @NotNull EntityType getEntityTypeById(final int id) {
		return ENTITY_TYPES.getOrDefault(id, EntityType.UNKNOWN);
	}
}

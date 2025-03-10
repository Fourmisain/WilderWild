/*
 * Copyright 2023 FrozenBlock
 * This file is part of Wilder Wild.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, see <https://www.gnu.org/licenses/>.
 */

package net.frozenblock.wilderwild.misc;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.nio.file.Path;
import java.util.Map;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WilderSharedConstants {
	public static final String PROJECT_ID = "Wilder Wild";
	public static final String MOD_ID = "wilderwild";
	public static final Logger LOGGER = LoggerFactory.getLogger(PROJECT_ID);
	public static final int DATA_VERSION = 15;
	// MEASURING
	public static final Map<Object, Long> INSTANT_MAP = new Object2ObjectOpenHashMap<>();
	/**
	 * Used for features that may be unstable and crash in public builds.
	 * <p>
	 * It's smart to use this for at least registries.
	 */
	public static boolean UNSTABLE_LOGGING = FabricLoader.getInstance().isDevelopmentEnvironment();
	public static boolean MC_LIVE_TENDRILS = false;

	// LOGGING
	public static void log(String string, boolean shouldLog) {
		if (shouldLog) {
			WilderSharedConstants.LOGGER.info(string);
		}
	}

	public static void logWild(String string, boolean shouldLog) {
		if (shouldLog) {
			WilderSharedConstants.LOGGER.info(string + " " + WilderSharedConstants.MOD_ID);
		}
	}

	public static void startMeasuring(@NotNull Object object) {
		long started = System.nanoTime();
		String name = object.getClass().getName();
		WilderSharedConstants.LOGGER.info("Started measuring {}", name.substring(name.lastIndexOf(".") + 1));
		INSTANT_MAP.put(object, started);
	}

	public static void stopMeasuring(Object object) {
		if (INSTANT_MAP.containsKey(object)) {
			String name = object.getClass().getName();
			WilderSharedConstants.LOGGER.info("{} took {} nanoseconds", name.substring(name.lastIndexOf(".") + 1), System.nanoTime() - INSTANT_MAP.get(object));
			INSTANT_MAP.remove(object);
		}
	}

	@NotNull
	public static ResourceLocation id(@NotNull String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	@NotNull
	public static ResourceLocation vanillaId(@NotNull String path) {
		return new ResourceLocation("minecraft", path);
	}

	@NotNull
	public static String string(@NotNull String path) {
		return WilderSharedConstants.id(path).toString();
	}

	public static String safeString(String path) {
		return MOD_ID + "_" + path;
	}

	/**
	 * @return A text component for use in a Config GUI
	 */
	public static Component text(String key) {
		return Component.translatable("option." + MOD_ID + "." + key);
	}

	/**
	 * @return A tooltip component for use in a Config GUI
	 */
	public static Component tooltip(String key) {
		return Component.translatable("tooltip." + MOD_ID + "." + key);
	}

	public static Path configPath(String name, boolean json5) {
		return Path.of("./config/" + MOD_ID + "/" + name + "." + (json5 ? "json5" : "json"));
	}
}

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

package net.frozenblock.wilderwild.config.gui;

import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.frozenblock.lib.config.api.instance.Config;
import net.frozenblock.lib.config.clothconfig.FrozenClothConfig;
import net.frozenblock.wilderwild.config.ItemConfig;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import static net.frozenblock.wilderwild.misc.WilderSharedConstants.text;
import static net.frozenblock.wilderwild.misc.WilderSharedConstants.tooltip;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public final class ItemConfigGui {
	private ItemConfigGui() {
		throw new UnsupportedOperationException("ItemConfigGui contains only static declarations.");
	}

	public static void setupEntries(@NotNull ConfigCategory category, @NotNull ConfigEntryBuilder entryBuilder) {
		var config = ItemConfig.get(true);
		Class<? extends ItemConfig> clazz = config.getClass();
		Config<? extends ItemConfig> configInstance = ItemConfig.INSTANCE;
		var modifiedConfig = ItemConfig.getWithSync();
		var defaultConfig = ItemConfig.INSTANCE.defaultInstance();
		var ancientHorn = config.ancientHorn;
		var modifiedAncientHorn = modifiedConfig.ancientHorn;
		var projectileLandingSounds = config.projectileLandingSounds;
		var modifiedProjectileLandingSounds = modifiedConfig.projectileLandingSounds;
		category.setBackground(WilderSharedConstants.id("textures/config/item.png"));

		var summonsWarden = FrozenClothConfig.syncedEntry(
			entryBuilder.startBooleanToggle(text("ancient_horn_can_summon_warden"), modifiedAncientHorn.ancientHornCanSummonWarden)
				.setDefaultValue(defaultConfig.ancientHorn.ancientHornCanSummonWarden)
				.setSaveConsumer(newValue -> ancientHorn.ancientHornCanSummonWarden = newValue)
				.setTooltip(tooltip("ancient_horn_can_summon_warden"))
				.build(),
			config.ancientHorn.getClass(),
			"ancientHornCanSummonWarden",
			configInstance
		);

		var lifespan = FrozenClothConfig.syncedEntry(
			entryBuilder.startIntSlider(text("ancient_horn_lifespan"), modifiedAncientHorn.ancientHornLifespan, 0, 1000)
				.setDefaultValue(defaultConfig.ancientHorn.ancientHornLifespan)
				.setSaveConsumer(newValue -> ancientHorn.ancientHornLifespan = newValue)
				.setTooltip(tooltip("ancient_horn_lifespan"))
				.build(),
			config.ancientHorn.getClass(),
			"ancientHornLifespan",
			configInstance
		);

		var mobDamage = FrozenClothConfig.syncedEntry(
			entryBuilder.startIntSlider(text("ancient_horn_mob_damage"), modifiedAncientHorn.ancientHornMobDamage, 0, 50)
				.setDefaultValue(defaultConfig.ancientHorn.ancientHornMobDamage)
				.setSaveConsumer(newValue -> ancientHorn.ancientHornMobDamage = newValue)
				.setTooltip(tooltip("ancient_horn_mob_damage"))
				.build(),
			config.ancientHorn.getClass(),
			"ancientHornMobDamage",
			configInstance
		);

		var playerDamage = FrozenClothConfig.syncedEntry(
			entryBuilder.startIntSlider(text("ancient_horn_player_damage"), modifiedAncientHorn.ancientHornPlayerDamage, 0, 50)
				.setDefaultValue(defaultConfig.ancientHorn.ancientHornPlayerDamage)
				.setSaveConsumer(newValue -> ancientHorn.ancientHornPlayerDamage = newValue)
				.setTooltip(tooltip("ancient_horn_player_damage"))
				.build(),
			config.ancientHorn.getClass(),
			"ancientHornPlayerDamage",
			configInstance
		);

		var shattersGlass = FrozenClothConfig.syncedEntry(
			entryBuilder.startBooleanToggle(text("ancient_horn_shatters_glass"), modifiedAncientHorn.ancientHornShattersGlass)
				.setDefaultValue(defaultConfig.ancientHorn.ancientHornShattersGlass)
				.setSaveConsumer(newValue -> ancientHorn.ancientHornShattersGlass = newValue)
				.setTooltip(tooltip("ancient_horn_shatters_glass"))
				.build(),
			config.ancientHorn.getClass(),
			"ancientHornShattersGlass",
			configInstance
		);

		var sizeMultiplier = FrozenClothConfig.syncedEntry(
			entryBuilder.startFloatField(text("ancient_horn_size_multiplier"), modifiedAncientHorn.ancientHornSizeMultiplier)
				.setDefaultValue(defaultConfig.ancientHorn.ancientHornSizeMultiplier)
				.setSaveConsumer(newValue -> ancientHorn.ancientHornSizeMultiplier = newValue)
				.setTooltip(tooltip("ancient_horn_size_multiplier"))
				.build(),
			config.ancientHorn.getClass(),
			"ancientHornSizeMultiplier",
			configInstance
		);

		var ancientHornCategory = FrozenClothConfig.createSubCategory(entryBuilder, category, text("ancient_horn"),
			false,
			tooltip("ancient_horn"),
			summonsWarden, lifespan, mobDamage, playerDamage, shattersGlass, sizeMultiplier
		);

		var snowballLandingSounds = FrozenClothConfig.syncedEntry(
			entryBuilder.startBooleanToggle(text("snowball_landing_sounds"), modifiedProjectileLandingSounds.snowballLandingSounds)
				.setDefaultValue(defaultConfig.projectileLandingSounds.snowballLandingSounds)
				.setSaveConsumer(newValue -> projectileLandingSounds.snowballLandingSounds = newValue)
				.setTooltip(tooltip("snowball_landing_sounds"))
				.build(),
			config.projectileLandingSounds.getClass(),
			"snowballLandingSounds",
			configInstance
		);

		var eggLandingSounds = FrozenClothConfig.syncedEntry(
			entryBuilder.startBooleanToggle(text("egg_landing_sounds"), modifiedProjectileLandingSounds.eggLandingSounds)
				.setDefaultValue(defaultConfig.projectileLandingSounds.eggLandingSounds)
				.setSaveConsumer(newValue -> projectileLandingSounds.eggLandingSounds = newValue)
				.setTooltip(tooltip("egg_landing_sounds"))
				.build(),
			config.projectileLandingSounds.getClass(),
			"eggLandingSounds",
			configInstance
		);

		var enderPearlLandingSounds = FrozenClothConfig.syncedEntry(
			entryBuilder.startBooleanToggle(text("ender_pearl_landing_sounds"), modifiedProjectileLandingSounds.enderPearlLandingSounds)
				.setDefaultValue(defaultConfig.projectileLandingSounds.enderPearlLandingSounds)
				.setSaveConsumer(newValue -> projectileLandingSounds.enderPearlLandingSounds = newValue)
				.setTooltip(tooltip("ender_pearl_landing_sounds"))
				.build(),
			config.projectileLandingSounds.getClass(),
			"enderPearlLandingSounds",
			configInstance
		);

		var potionLandingSounds = FrozenClothConfig.syncedEntry(
			entryBuilder.startBooleanToggle(text("potion_landing_sounds"), modifiedProjectileLandingSounds.potionLandingSounds)
				.setDefaultValue(defaultConfig.projectileLandingSounds.potionLandingSounds)
				.setSaveConsumer(newValue -> projectileLandingSounds.potionLandingSounds = newValue)
				.setTooltip(tooltip("potion_landing_sounds"))
				.build(),
			config.projectileLandingSounds.getClass(),
			"potionLandingSounds",
			configInstance
		);

		var projectileLandingSoundsCategory = FrozenClothConfig.createSubCategory(entryBuilder, category, text("projectile_landing_sounds"),
			false,
			tooltip("projectile_landing_sounds"),
			snowballLandingSounds, eggLandingSounds, enderPearlLandingSounds, potionLandingSounds
		);

		var projectileBreakParticles = category.addEntry(
			FrozenClothConfig.syncedEntry(
					entryBuilder.startBooleanToggle(text("projectile_break_particles"), modifiedConfig.projectileBreakParticles)
						.setDefaultValue(defaultConfig.projectileBreakParticles)
						.setSaveConsumer(newValue -> config.projectileBreakParticles = newValue)
						.setTooltip(tooltip("projectile_break_particles"))
						.build(),
					clazz,
					"projectileBreakParticles",
					configInstance
				)
		);

		var restrictInstrumentSound = category.addEntry(
			FrozenClothConfig.syncedEntry(
					entryBuilder.startBooleanToggle(text("restrict_instrument_sound"), modifiedConfig.restrictInstrumentSound)
						.setDefaultValue(defaultConfig.restrictInstrumentSound)
						.setSaveConsumer(newValue -> config.restrictInstrumentSound = newValue)
						.setTooltip(tooltip("restrict_instrument_sound"))
						.build()	,
					clazz,
					"restrictInstrumentSound",
					configInstance
				)
		);
	}
}

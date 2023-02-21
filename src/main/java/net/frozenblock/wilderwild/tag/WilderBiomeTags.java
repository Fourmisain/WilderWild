/*
 * Copyright 2022-2023 FrozenBlock
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

package net.frozenblock.wilderwild.tag;

import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public final class WilderBiomeTags {
	private WilderBiomeTags() {
		throw new UnsupportedOperationException("WilderBiomeTags contains only static declarations.");
	}

    public static final TagKey<Biome> FIREFLY_SPAWNABLE_DURING_DAY = bind("firefly_spawnable_during_day");
    public static final TagKey<Biome> FIREFLY_SPAWNABLE_CAVE = bind("firefly_spawnable_cave");
    public static final TagKey<Biome> FIREFLY_SPAWNABLE = bind("firefly_spawnable");
    public static final TagKey<Biome> ABANDONED_CABIN_HAS_STRUCTURE = bind("has_structure/abandoned_cabin");
    public static final TagKey<Biome> HAS_JELLYFISH = bind("has_jellyfish");
    public static final TagKey<Biome> PEARLESCENT_JELLYFISH = bind("pearlescent_jellyfish");
	public static final TagKey<Biome> HAS_TUMBLEWEED_ENTITY = bind("has_tumbleweed_entity");
	public static final TagKey<Biome> HAS_TUMBLEWEED_PLANT = bind("has_tumbleweed_plant");
    public static final TagKey<Biome> NO_POOLS = bind("no_pools");
	public static final TagKey<Biome> NO_SNOW_BLANKET = bind("no_snow_blanket");
    public static final TagKey<Biome> NON_FROZEN_PLAINS = bind("non_frozen_plains");
    public static final TagKey<Biome> SWAMP_TREES = bind("swamp_trees");
    public static final TagKey<Biome> SHORT_TAIGA = bind("short_taiga");
    public static final TagKey<Biome> TALL_PINE_TAIGA = bind("tall_pine_taiga");
    public static final TagKey<Biome> TALL_SPRUCE_TAIGA = bind("tall_spruce_taiga");
    public static final TagKey<Biome> GROVE = bind("grove");
    public static final TagKey<Biome> NORMAL_SAVANNA = bind("normal_savanna");
    public static final TagKey<Biome> WINDSWEPT_SAVANNA = bind("windswept_savanna");
    public static final TagKey<Biome> SNOWY_PLAINS = bind("snowy_plains");
    public static final TagKey<Biome> WINDSWEPT_HILLS = bind("windswept_hills");
    public static final TagKey<Biome> WINDSWEPT_FOREST = bind("windswept_forest");
	public static final TagKey<Biome> RAINFOREST = bind("rainforest");
    public static final TagKey<Biome> HAS_FALLEN_BIRCH_TREES = bind("has_fallen_birch_trees");
	public static final TagKey<Biome> HAS_FALLEN_OAK_AND_BIRCH_TREES = bind("has_fallen_oak_and_birch_trees");
	public static final TagKey<Biome> HAS_FALLEN_OAK_AND_SPRUCE_TREES = bind("has_fallen_oak_and_spruce_trees");
	public static final TagKey<Biome> HAS_MOSSY_FALLEN_MIXED_TREES = bind("has_mossy_fallen_mixed_trees");
	public static final TagKey<Biome> HAS_MOSSY_FALLEN_OAK_AND_BIRCH = bind("has_mossy_fallen_oak_and_birch");
    public static final TagKey<Biome> DARK_FOREST = bind("dark_forest");
    public static final TagKey<Biome> MEADOW = bind("meadow");
	public static final TagKey<Biome> FOREST_GRASS = bind("forest_grass");
	public static final TagKey<Biome> HAS_SMALL_SPONGE = bind("has_small_sponge");
	public static final TagKey<Biome> HAS_SMALL_SPONGE_RARE = bind("has_small_sponge_rare");
	public static final TagKey<Biome> HAS_DATURA = bind("has_datura");
	public static final TagKey<Biome> HAS_CARNATION = bind("has_carnation");
	public static final TagKey<Biome> HAS_CATTAIL = bind("has_cattail");
	public static final TagKey<Biome> HAS_SEEDING_DANDELION = bind("has_seeding_dandelion");
	public static final TagKey<Biome> HAS_MILKWEED = bind("has_milkweed");
	public static final TagKey<Biome> HAS_PALMS = bind("has_palms");
	public static final TagKey<Biome> HAS_SHORT_SPRUCE = bind("has_short_spruce");
	public static final TagKey<Biome> HAS_SHORT_MEGA_SPRUCE = bind("has_short_mega_spruce");
	public static final TagKey<Biome> HAS_SHORT_MEGA_SPRUCE_SNOWY = bind("has_short_mega_spruce_snowy");
	public static final TagKey<Biome> HAS_BIG_SHRUB = bind("has_big_shrub");
	public static final TagKey<Biome> HAS_POLLEN = bind("has_pollen");
	public static final TagKey<Biome> HAS_FIELD_FLOWERS = bind("has_field_flowers");
	public static final TagKey<Biome> HAS_RED_SHELF_FUNGUS = bind("has_red_shelf_fungus");
	public static final TagKey<Biome> HAS_BROWN_SHELF_FUNGUS = bind("has_brown_shelf_fungus");
	public static final TagKey<Biome> HAS_GLORY_OF_THE_SNOW = bind("has_glory_of_the_snow");
	public static final TagKey<Biome> HAS_FLOWERING_WATER_LILY = bind("has_flowering_water_lily");
	public static final TagKey<Biome> HAS_BERRY_PATCH = bind("has_berry_patch");
	public static final TagKey<Biome> HAS_LARGE_FERN_AND_GRASS = bind("has_large_fern_and_grass");
	public static final TagKey<Biome> HAS_LARGE_FERN_AND_GRASS_RARE = bind("has_large_fern_and_grass_rare");
	public static final TagKey<Biome> HAS_NEW_RARE_GRASS = bind("has_new_rare_grass");
	public static final TagKey<Biome> HAS_DECORATIVE_MUD = bind("has_decorative_mud");
	public static final TagKey<Biome> HAS_PACKED_MUD_ORE = bind("has_packed_mud_ore");
	public static final TagKey<Biome> HAS_COARSE_DIRT_PATH = bind("has_coarse_dirt_path");
	public static final TagKey<Biome> HAS_COARSE_DIRT_PATH_SMALL = bind("has_coarse_dirt_path_small");
	public static final TagKey<Biome> HAS_PACKED_MUD_PATH_BADLANDS = bind("has_packed_mud_path_badlands");
	public static final TagKey<Biome> HAS_SANDSTONE_PATH = bind("has_sandstone_path");
	public static final TagKey<Biome> HAS_DRY_SAND = bind("has_dry_sand");
	public static final TagKey<Biome> HAS_TAIGA_FOREST_ROCK = bind("has_taiga_forest_rock");
	public static final TagKey<Biome> HAS_MOSS_PATH = bind("has_moss_path");
	public static final TagKey<Biome> HAS_MOSS_LAKE = bind("has_moss_lake");
	public static final TagKey<Biome> HAS_PACKED_MUD_PATH = bind("has_packed_mud_path");
	public static final TagKey<Biome> HAS_CLAY_PATH = bind("has_clay_path");

	public static final TagKey<Biome> GRAVEL_BEACH = bind("gravel_beaches");
	public static final TagKey<Biome> SAND_BEACHES = bind("sand_beaches");
	public static final TagKey<Biome> MULTI_LAYER_SAND_BEACHES = bind("multi_layer_sand_beaches");

    private static TagKey<Biome> bind(String path) {
        return TagKey.create(Registry.BIOME_REGISTRY, WilderSharedConstants.id(path));
    }
}

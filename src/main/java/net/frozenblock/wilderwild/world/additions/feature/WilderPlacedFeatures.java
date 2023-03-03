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

package net.frozenblock.wilderwild.world.additions.feature;

import net.frozenblock.wilderwild.misc.WilderSharedConstants;
<<<<<<< HEAD
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
=======
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.AquaticFeatures;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import static net.minecraft.data.worldgen.placement.AquaticPlacements.seagrassPlacement;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.*;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
>>>>>>> dev
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class WilderPlacedFeatures {
	private WilderPlacedFeatures() {
		throw new UnsupportedOperationException("ResourceKey<PlacedFeatures contains only static declarations.");
	}

    //FALLEN TREES
    public static final ResourceKey<PlacedFeature> FALLEN_TREES_MIXED_PLACED = key("fallen_trees_mixed_placed");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_AND_SPRUCE_PLACED = key("fallen_oak_and_spruce_placed");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_AND_BIRCH_PLACED = key("fallen_oak_and_birch_placed");
	public static final ResourceKey<PlacedFeature> FALLEN_OAK_AND_BIRCH_PLACED_2 = key("fallen_oak_and_birch_placed_2");
    public static final ResourceKey<PlacedFeature> FALLEN_OAK_AND_CYPRESS_PLACED = key("fallen_oak_and_cypress_placed");
    public static final ResourceKey<PlacedFeature> FALLEN_BIRCH_PLACED = key("fallen_birch_placed");
    public static final ResourceKey<PlacedFeature> FALLEN_SPRUCE_PLACED = key("fallen_spruce_placed");
	public static final ResourceKey<PlacedFeature> FALLEN_BIRCH_AND_SPRUCE_PLACED = key("fallen_birch_and_spruce_placed");
	public static final ResourceKey<PlacedFeature> FALLEN_BIRCH_AND_SPRUCE_PLACED_2 = key("fallen_birch_and_spruce_placed_2");

    //TREES
    public static final ResourceKey<PlacedFeature> TREES_PLAINS = key("trees_plains");
    public static final ResourceKey<PlacedFeature> TREES_BIRCH_AND_OAK = key("trees_birch_and_oak");
	public static final ResourceKey<PlacedFeature> TREES_FLOWER_FIELD = key("flower_field_trees");
    public static final ResourceKey<PlacedFeature> TREES_FLOWER_FOREST = key("trees_flower_forest");
    public static final ResourceKey<PlacedFeature> DARK_FOREST_VEGETATION = key("dark_forest_vegetation");
	public static final ResourceKey<PlacedFeature> OLD_GROWTH_DARK_FOREST_VEGETATION = key("old_growth_dark_forest_vegetation");
    public static final ResourceKey<PlacedFeature> TREES_BIRCH_PLACED = key("trees_birch");
    public static final ResourceKey<PlacedFeature> BIRCH_TALL_PLACED = key("birch_tall");
    public static final ResourceKey<PlacedFeature> SPRUCE_PLACED = key("spruce_placed");
    public static final ResourceKey<PlacedFeature> SHORT_SPRUCE_PLACED = key("short_spruce_placed");
    public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_PINE_TAIGA = key("trees_old_growth_pine_taiga");
    public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_SPRUCE_TAIGA = key("trees_old_growth_spruce_taiga");
    public static final ResourceKey<PlacedFeature> TREES_SNOWY = key("trees_snowy");
	public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_SNOWY_PINE_TAIGA = key("old_growth_snowy_pine_taiga_trees");
	public static final ResourceKey<PlacedFeature> TREES_OLD_GROWTH_BIRCH_TAIGA = key("trees_old_growth_birch_taiga");
	public static final ResourceKey<PlacedFeature> DARK_BIRCH_FOREST_VEGETATION = key("dark_birch_forest_vegetation");
    public static final ResourceKey<PlacedFeature> TREES_GROVE = key("trees_grove");
    public static final ResourceKey<PlacedFeature> TREES_WINDSWEPT_HILLS = key("trees_windswept_hills");
    public static final ResourceKey<PlacedFeature> TREES_WINDSWEPT_FOREST = key("trees_windswept_forest");
    public static final ResourceKey<PlacedFeature> TREES_MEADOW = key("trees_meadow");
    public static final ResourceKey<PlacedFeature> WINDSWEPT_SAVANNA_TREES = key("windswept_savanna_trees");
    public static final ResourceKey<PlacedFeature> SAVANNA_TREES = key("savanna_trees");
    public static final ResourceKey<PlacedFeature> TREES_SWAMP = key("trees_swamp");
	public static final ResourceKey<PlacedFeature> TREES_ARID_SAVANNA = key("arid_savanna_trees");
    public static final ResourceKey<PlacedFeature> MIXED_TREES = key("mixed_trees");
	public static final ResourceKey<PlacedFeature> BIRCH_TAIGA_TREES = key("birch_taiga_trees");
    public static final ResourceKey<PlacedFeature> CYPRESS_WETLANDS_TREES = key("cypress_wetlands_trees");
    public static final ResourceKey<PlacedFeature> CYPRESS_WETLANDS_TREES_WATER = key("cypress_wetlands_trees_water");
	public static final ResourceKey<PlacedFeature> TREES_PARCHED_FOREST = key("parched_forest_trees");
	public static final ResourceKey<PlacedFeature> TREES_ARID_FOREST = key("arid_forest_trees");
	public static final ResourceKey<PlacedFeature> TREES_SEMI_BIRCH_AND_OAK = key("semi_birch_forest_trees");
	public static final ResourceKey<PlacedFeature> TREES_BIRCH_JUNGLE = key("birch_jungle_trees");
	public static final ResourceKey<PlacedFeature> TREES_SPARSE_BIRCH_JUNGLE = key("sparse_birch_jungle_trees");
	public static final ResourceKey<PlacedFeature> BIG_SHRUB = key("big_shrub");
	public static final ResourceKey<PlacedFeature> PALM = key("palm_placed");
	public static final ResourceKey<PlacedFeature> PALM_JUNGLE = key("palm_jungle");
	public static final ResourceKey<PlacedFeature> PALMS_OASIS = key("palms_oasis");
	public static final ResourceKey<PlacedFeature> PALM_RARE = key("palm_rare");

    //MUSHROOMS
    public static final ResourceKey<PlacedFeature> BROWN_SHELF_FUNGUS_PLACED = key("brown_shelf_fungus_placed");
    public static final ResourceKey<PlacedFeature> RED_SHELF_FUNGUS_PLACED = key("red_shelf_fungus_placed");
    public static final ResourceKey<PlacedFeature> BROWN_MUSHROOM_PLACED = key("brown_mushroom_placed");
    public static final ResourceKey<PlacedFeature> HUGE_RED_MUSHROOM_PLACED = key("huge_red_mushroom_placed");
    public static final ResourceKey<PlacedFeature> HUGE_MUSHROOMS_SWAMP = key("huge_mushrooms_swamp");
    public static final ResourceKey<PlacedFeature> MUSHROOM_PLACED = key("mushroom_placed");
    public static final ResourceKey<PlacedFeature> MIXED_MUSHROOMS_PLACED = key("mixed_mushroom_placed");

    //GRASS AND FERNS
	public static final ResourceKey<PlacedFeature> OASIS_GRASS_PLACED = key("oasis_grass_placed");
	public static final ResourceKey<PlacedFeature> OASIS_BUSH_PLACED = key("oasis_bush_placed");
	public static final ResourceKey<PlacedFeature> DESERT_BUSH_PLACED = key("desert_bush_placed");
	public static final ResourceKey<PlacedFeature> OASIS_CACTUS_PLACED = key("oasis_cactus_placed");
	public static final ResourceKey<PlacedFeature> FLOWER_FIELD_BUSH_PLACED = key("flower_field_bush_placed");
	public static final ResourceKey<PlacedFeature> ARID_BUSH_PLACED = key("arid_bush_placed");
	public static final ResourceKey<PlacedFeature> TALL_CACTUS_PLACED = key("tall_cactus_placed");
	public static final ResourceKey<PlacedFeature> ARID_CACTUS_PLACED = key("arid_cactus_placed");

    public static final Holder<PlacedFeature> FALLEN_TREES_MIXED_PLACED = register("fallen_trees_mixed_placed",
            WilderConfiguredFeatures.FALLEN_TREES_MIXED, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> MOSSY_FALLEN_TREES_MIXED_PLACED = register("mossy_fallen_trees_mixed_placed",
			WilderConfiguredFeatures.MOSSY_FALLEN_TREES_MIXED, CountPlacement.of(2), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> MOSSY_FALLEN_TREES_OAK_AND_BIRCH_PLACED = register("mossy_fallen_trees_oak_and_birch_placed",
			WilderConfiguredFeatures.MOSSY_FALLEN_TREES_OAK_AND_BIRCH, CountPlacement.of(2), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> FALLEN_BIRCH_AND_SPRUCE_PLACED = register("fallen_birch_and_spruce_placed",
            WilderConfiguredFeatures.FALLEN_BIRCH_AND_SPRUCE, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> FALLEN_OAK_AND_SPRUCE_PLACED = register("fallen_oak_and_spruce_placed",
            WilderConfiguredFeatures.FALLEN_SPRUCE_AND_OAK, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> FALLEN_OAK_AND_BIRCH_PLACED = register("fallen_oak_and_birch_placed",
            WilderConfiguredFeatures.FALLEN_BIRCH_AND_OAK, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> FALLEN_OAK_AND_CYPRESS_PLACED = register("fallen_oak_and_cypress_placed",
            WilderConfiguredFeatures.FALLEN_CYPRESS_AND_OAK, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> FALLEN_BIRCH_PLACED = register("fallen_birch_placed",
            WilderConfiguredFeatures.FALLEN_BIRCH, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> FALLEN_SPRUCE_PLACED = register("fallen_spruce_placed",
            WilderConfiguredFeatures.FALLEN_SPRUCE, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> FALLEN_OAK_AND_BIRCH_PLACED_2 = register("fallen_oak_and_birch_placed_2",
			WilderConfiguredFeatures.FALLEN_BIRCH_AND_OAK, RarityFilter.onAverageOnceEvery(10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    //TREES
    public static final Holder<PlacedFeature> TREES_PLAINS = register("trees_plains", WilderConfiguredFeatures.TREES_PLAINS,
            PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

	public static final Holder<PlacedFeature> TREES_FLOWER_FIELD = register("trees_flower_field", WilderConfiguredFeatures.TREES_FLOWER_FIELD,
			PlacementUtils.countExtra(0, 0.1F, 1), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

	public static final Holder<PlacedFeature> TREES_BIRCH_AND_OAK = register("trees_birch_and_oak",
            WilderConfiguredFeatures.TREES_BIRCH_AND_OAK, treePlacement(PlacementUtils.countExtra(12, 0.1F, 1)));

	public static final Holder<PlacedFeature> TREES_SEMI_BIRCH_AND_OAK = register("trees_semi_birch_and_oak",
			WilderConfiguredFeatures.TREES_SEMI_BIRCH_AND_OAK, treePlacement(PlacementUtils.countExtra(11, 0.1F, 1)));

    public static final Holder<PlacedFeature> TREES_FLOWER_FOREST = register("trees_flower_forest",
            WilderConfiguredFeatures.TREES_FLOWER_FOREST, treePlacement(PlacementUtils.countExtra(8, 0.1F, 1)));

    public static final Holder<PlacedFeature> DARK_FOREST_VEGETATION = register("dark_forest_vegetation",
            WilderConfiguredFeatures.DARK_FOREST_VEGETATION, CountPlacement.of(16), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

	public static final Holder<PlacedFeature> OLD_GROWTH_DARK_FOREST_VEGETATION = register("old_growth_dark_forest_vegetation",
			WilderConfiguredFeatures.OLD_GROWTH_DARK_FOREST_VEGETATION, CountPlacement.of(17), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

	public static final Holder<PlacedFeature> DARK_BIRCH_FOREST_VEGETATION = register("dark_birch_forest_vegetation",
			WilderConfiguredFeatures.DARK_BIRCH_FOREST_VEGETATION, CountPlacement.of(14), InSquarePlacement.spread(), TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

	public static final Holder<PlacedFeature> TREES_BIRCH = register("trees_birch",
            WilderConfiguredFeatures.TREES_BIRCH, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

    public static final Holder<PlacedFeature> BIRCH_TALL = register("birch_tall",
            WilderConfiguredFeatures.TREES_BIRCH_TALL, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

    public static final Holder<PlacedFeature> SPRUCE_PLACED = register("spruce_placed",
            WilderConfiguredFeatures.TREES_TAIGA, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

    public static final Holder<PlacedFeature> SHORT_SPRUCE_PLACED = register("short_spruce_placed",
            WilderConfiguredFeatures.SHORT_TREES_TAIGA, treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));

	public static final Holder<PlacedFeature> SHORT_MEGA_SPRUCE_PLACED = register("short_mega_spruce_placed",
			WilderConfiguredFeatures.SHORT_MEGA_SPRUCE, treePlacement(RarityFilter.onAverageOnceEvery(9)));

	public static final Holder<PlacedFeature> SHORT_MEGA_SPRUCE_ON_SNOW_PLACED = register("short_mega_spruce_on_snow_placed",
			WilderConfiguredFeatures.SHORT_MEGA_SPRUCE_ON_SNOW, treePlacement(RarityFilter.onAverageOnceEvery(9)));

    public static final Holder<PlacedFeature> TREES_OLD_GROWTH_PINE_TAIGA = register("trees_old_growth_pine_taiga",
            WilderConfiguredFeatures.TREES_OLD_GROWTH_PINE_TAIGA, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

    public static final Holder<PlacedFeature> TREES_OLD_GROWTH_SPRUCE_TAIGA1 = register("trees_old_growth_spruce_taiga",
            WilderConfiguredFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

	public static final Holder<PlacedFeature> TREES_OLD_GROWTH_SNOWY_PINE_TAIGA = register("trees_old_growth_snowy_pine_taiga",
			WilderConfiguredFeatures.TREES_OLD_GROWTH_SNOWY_PINE_TAIGA, treePlacement(PlacementUtils.countExtra(8, 0.1F, 1)));

    public static final Holder<PlacedFeature> TREES_SNOWY = register("trees_snowy",
            WilderTreeConfigured.SPRUCE, treePlacement(PlacementUtils.countExtra(0, 0.1F, 1), Blocks.SPRUCE_SAPLING));

    public static final Holder<PlacedFeature> TREES_GROVE = register("trees_grove",
            WilderConfiguredFeatures.TREES_GROVE, treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

    public static final Holder<PlacedFeature> TREES_WINDSWEPT_HILLS = register("trees_windswept_hills",
            WilderConfiguredFeatures.TREES_WINDSWEPT_HILLS, treePlacement(PlacementUtils.countExtra(0, 0.1F, 1)));

    public static final Holder<PlacedFeature> TREES_WINDSWEPT_FOREST = register("trees_windswept_forest",
            WilderConfiguredFeatures.TREES_WINDSWEPT_HILLS, treePlacement(PlacementUtils.countExtra(3, 0.1F, 1)));

    public static final Holder<PlacedFeature> TREES_MEADOW = register("trees_meadow",
            WilderConfiguredFeatures.MEADOW_TREES, treePlacement(RarityFilter.onAverageOnceEvery(100)));

    public static final Holder<PlacedFeature> WINDSWEPT_SAVANNA_TREES = register("windswept_savanna_trees",
            WilderConfiguredFeatures.WINDSWEPT_SAVANNA_TREES, treePlacement(PlacementUtils.countExtra(2, 0.1F, 1)));

    public static final Holder<PlacedFeature> SAVANNA_TREES = register("savanna_trees",
            WilderConfiguredFeatures.SAVANNA_TREES, treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));

	public static final Holder<PlacedFeature> ARID_SAVANNA_TREES = register("arid_savanna_trees",
			WilderConfiguredFeatures.ARID_SAVANNA_TREES, treePlacement(RarityFilter.onAverageOnceEvery(12)));

	public static final Holder<PlacedFeature> WOODED_BADLANDS_TREES = register("wooded_badlands_trees",
			WilderConfiguredFeatures.WOODED_BADLANDS_TREES, treePlacement(PlacementUtils.countExtra(7, 0.1F, 1)));

    public static final Holder<PlacedFeature> TREES_SWAMP = register("trees_swamp", WilderTreeConfigured.SWAMP_TREE,
            PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(4), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultBlockState(), BlockPos.ZERO)));

    public static final Holder<PlacedFeature> MIXED_TREES = register("mixed_trees",
            WilderConfiguredFeatures.MIXED_TREES, treePlacement(PlacementUtils.countExtra(14, 0.1F, 1)));

	public static final Holder<PlacedFeature> TEMPERATE_RAINFOREST_TREES = register("temperate_rainforest_trees",
			WilderConfiguredFeatures.TEMPERATE_RAINFOREST_TREES, treePlacement(PlacementUtils.countExtra(13, 0.1F, 1)));

	public static final Holder<PlacedFeature> RAINFOREST_TREES = register("rainforest_trees",
			WilderConfiguredFeatures.RAINFOREST_TREES, treePlacement(PlacementUtils.countExtra(12, 0.1F, 1)));

    public static final Holder<PlacedFeature> BIRCH_TAIGA_TREES = register("birch_taiga_trees",
            WilderConfiguredFeatures.BIRCH_TAIGA_TREES, treePlacement(CountPlacement.of(3)));

	public static final Holder<PlacedFeature> OLD_GROWTH_BIRCH_TAIGA_TREES = register("old_growth_birch_taiga_trees",
			WilderConfiguredFeatures.OLD_GROWTH_BIRCH_TAIGA_TREES, treePlacement(CountPlacement.of(3)));

	public static final Holder<PlacedFeature> PARCHED_FOREST_TREES = register("parched_forest_trees",
			WilderConfiguredFeatures.PARCHED_FOREST_TREES, treePlacement(PlacementUtils.countExtra(4, 0.1f, 1)));

	public static final Holder<PlacedFeature> ARID_FOREST_TREES = register("arid_forest_trees",
			WilderConfiguredFeatures.ARID_FOREST_TREES, treePlacement(CountPlacement.of(3)));

	public static final Holder<PlacedFeature> BIRCH_JUNGLE_TREES = register("birch_jungle_trees",
			WilderConfiguredFeatures.BIRCH_JUNGLE_TREES, treePlacement(CountPlacement.of(29)));

	public static final Holder<PlacedFeature> SPARSE_BIRCH_JUNGLE_TREES = register("sparse_birch_jungle_trees",
			WilderConfiguredFeatures.SPARSE_BIRCH_JUNGLE_TREES, VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.1f, 1)));

    public static final Holder<PlacedFeature> CYPRESS_WETLANDS_TREES = register("cypress_wetlands_trees",
            WilderConfiguredFeatures.CYPRESS_WETLANDS_TREES, CountPlacement.of(28), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(RegisterBlocks.CYPRESS_SAPLING.defaultBlockState(), BlockPos.ZERO)));

    public static final Holder<PlacedFeature> CYPRESS_WETLANDS_TREES_WATER = register("cypress_wetlands_trees_water",
            WilderConfiguredFeatures.CYPRESS_WETLANDS_TREES_WATER, CountPlacement.of(20), SurfaceWaterDepthFilter.forMaxDepth(5), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(RegisterBlocks.CYPRESS_SAPLING.defaultBlockState(), BlockPos.ZERO)));

	public static final Holder<PlacedFeature> BIG_SHRUB = PlacementUtils.register("big_shrub",
			WilderConfiguredFeatures.BIG_SHRUBS, treePlacement(RarityFilter.onAverageOnceEvery(5)));

	public static final Holder<PlacedFeature> PALM = PlacementUtils.register("palm_placed",
			WilderConfiguredFeatures.PALMS, treePlacement(RarityFilter.onAverageOnceEvery(4)));

	public static final Holder<PlacedFeature> PALM_JUNGLE = PlacementUtils.register("palm_jungle",
			WilderConfiguredFeatures.PALMS_JUNGLE, treePlacement(PlacementUtils.countExtra(6, 0.5F, 2)));

	public static final Holder<PlacedFeature> PALMS_OASIS = PlacementUtils.register("palms_oasis",
			WilderConfiguredFeatures.PALMS_OASIS, treePlacement(RarityFilter.onAverageOnceEvery(3)));

	public static final Holder<PlacedFeature> PALM_RARE = PlacementUtils.register("palm_rare",
			WilderConfiguredFeatures.PALMS_OASIS, treePlacement(RarityFilter.onAverageOnceEvery(52)));
	//MUSHROOMS
    public static final Holder<PlacedFeature> BROWN_SHELF_FUNGUS_PLACED = register("brown_shelf_fungus_placed",
            WilderConfiguredFeatures.BROWN_SHELF_FUNGUS_CONFIGURED, RarityFilter.onAverageOnceEvery(1), CountPlacement.of(11), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, InSquarePlacement.spread(), SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 0, 128), BiomeFilter.biome());

    public static final Holder<PlacedFeature> RED_SHELF_FUNGUS_PLACED = register("red_shelf_fungus_placed",
            WilderConfiguredFeatures.RED_SHELF_FUNGUS_CONFIGURED, RarityFilter.onAverageOnceEvery(1), CountPlacement.of(11), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, InSquarePlacement.spread(), SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 0, 128), BiomeFilter.biome());

    public static final Holder<PlacedFeature> BROWN_MUSHROOM_PLACED = register("brown_mushroom_placed",
            VegetationFeatures.PATCH_BROWN_MUSHROOM, worldSurfaceSquaredWithCount(10));

	public static final Holder<PlacedFeature> RED_MUSHROOM_PLACED = register("red_mushroom_placed",
			VegetationFeatures.PATCH_RED_MUSHROOM, worldSurfaceSquaredWithCount(10));

	public static final Holder<PlacedFeature> DARK_FOREST_MUSHROOM_PLACED = register("dark_forest_mushroom_placed",
			WilderConfiguredFeatures.MUSHROOMS_DARK_FOREST, worldSurfaceSquaredWithCount(8));

    public static final Holder<PlacedFeature> HUGE_RED_MUSHROOM_PLACED = register("huge_red_mushroom_placed",
            TreeFeatures.HUGE_RED_MUSHROOM, RarityFilter.onAverageOnceEvery(90), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> HUGE_BROWN_MUSHROOM_PLACED = register("huge_brown_mushroom_placed",
			TreeFeatures.HUGE_BROWN_MUSHROOM, RarityFilter.onAverageOnceEvery(90), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> HUGE_MUSHROOMS_SWAMP = register("huge_mushrooms_swamp",
            VegetationFeatures.MUSHROOM_ISLAND_VEGETATION, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> MUSHROOM_PLACED = register("mushroom_placed",
            VegetationFeatures.MUSHROOM_ISLAND_VEGETATION, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> MIXED_MUSHROOMS_PLACED = register("mixed_mushroom_placed",
            VegetationFeatures.MUSHROOM_ISLAND_VEGETATION, RarityFilter.onAverageOnceEvery(75), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> RAINFOREST_MUSHROOMS_PLACED = register("rainforest_mushroom_placed",
			VegetationFeatures.MUSHROOM_ISLAND_VEGETATION, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	//GRASS AND FERNS
	public static final Holder<PlacedFeature> OASIS_GRASS_PLACED = register("oasis_grass_placed",
			WilderConfiguredFeatures.OASIS_GRASS, worldSurfaceSquaredWithCount(19));
>>>>>>> dev

	public static final ResourceKey<PlacedFeature> GRASS_PLACED = key("grass_placed");
    public static final ResourceKey<PlacedFeature> RARE_GRASS_PLACED = key("rare_grass_placed");
    public static final ResourceKey<PlacedFeature> TALL_GRASS = key("tall_grass");
    public static final ResourceKey<PlacedFeature> DENSE_TALL_GRASS_PLACED = key("dense_tall_grass_placed");
    public static final ResourceKey<PlacedFeature> DENSE_FERN_PLACED = key("dense_fern_placed");
    public static final ResourceKey<PlacedFeature> SEAGRASS_CYPRESS = key("seagrass_cypress");
    public static final ResourceKey<PlacedFeature> LARGE_FERN_AND_GRASS = key("large_fern_and_grass");
    public static final ResourceKey<PlacedFeature> LARGE_FERN_AND_GRASS_RARE = key("large_fern_and_grass_rare");
	public static final ResourceKey<PlacedFeature> FLOWER_FIELD_GRASS_PLACED = key("grass_flower_field_placed");
	public static final ResourceKey<PlacedFeature> PATCH_TALL_GRASS_FF = key("patch_tall_grass_ff");

<<<<<<< HEAD
    //FLOWERS
    public static final ResourceKey<PlacedFeature> SEEDING_DANDELION = key("seeding_dandelion");
    public static final ResourceKey<PlacedFeature> SEEDING_DANDELION_MIXED = key("seeding_dandelion_mixed");
    public static final ResourceKey<PlacedFeature> SEEDING_DANDELION_CYPRESS = key("seeding_dandelion_cypress");
    public static final ResourceKey<PlacedFeature> CARNATION = key("carnation");
    public static final ResourceKey<PlacedFeature> DATURA_BIRCH = key("datura_birch");
    public static final ResourceKey<PlacedFeature> FLOWER_PLAINS = key("flower_plains");
    public static final ResourceKey<PlacedFeature> DENSE_FLOWER_PLACED = key("dense_flower_placed");
    public static final ResourceKey<PlacedFeature> FLOWER_FOREST_FLOWERS = key(
            "flower_forest_flowers"
=======
	public static final Holder<PlacedFeature> DEAD_BUSH_PLACED = register("dead_bush_placed",
			VegetationFeatures.PATCH_DEAD_BUSH, CountPlacement.of(10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> DEAD_BUSH_AND_BUSH_PLACED = register("dead_bush_and_bush_placed",
			WilderConfiguredFeatures.DEAD_BUSH_AND_BUSH, CountPlacement.of(10), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> BUSH_AND_DEAD_BUSH_PLACED = register("bush_and_dead_bush_placed",
			WilderConfiguredFeatures.BUSH_AND_DEAD_BUSH, CountPlacement.of(5), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING), BiomeFilter.biome());

	public static final Holder<PlacedFeature> FLOWER_FIELD_BUSH_PLACED = register("flower_field_bush_placed",
			WilderConfiguredFeatures.FLOWER_FIELD_BUSH, RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> DESERT_BUSH_PLACED = register("desert_bush_placed",
			WilderConfiguredFeatures.DESERT_BUSH, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> BADLANDS_BUSH_SAND_PLACED = register("badlands_bush_sand_placed",
			WilderConfiguredFeatures.BADLANDS_BUSH_SAND, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> BADLANDS_BUSH_RARE_SAND_PLACED = register("badlands_bush_rare_sand_placed",
			WilderConfiguredFeatures.BADLANDS_BUSH_SAND, RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> BADLANDS_BUSH_TERRACOTTA_PLACED = register("badlands_bush_terracotta_placed",
			WilderConfiguredFeatures.BADLANDS_BUSH_TERRACOTTA, RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> WOODED_BADLANDS_BUSH_TERRACOTTA_PLACED = register("wooded_badlands_bush_terracotta_placed",
			WilderConfiguredFeatures.WOODED_BADLANDS_BUSH_TERRACOTTA, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> ARID_BUSH_PLACED = register("arid_bush_placed",
			WilderConfiguredFeatures.DESERT_BUSH, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> OASIS_CACTUS_PLACED = register("oasis_cactus_placed",
			WilderConfiguredFeatures.PATCH_CACTUS_OASIS, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> TALL_CACTUS_PLACED = register("tall_cactus_placed",
			WilderConfiguredFeatures.PATCH_CACTUS_TALL, RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> BADLANDS_TALL_CACTUS_PLACED = register("badlands_tall_cactus_placed",
			WilderConfiguredFeatures.PATCH_CACTUS_TALL_BADLANDS, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> ARID_CACTUS_PLACED = register("arid_cactus_placed",
			VegetationFeatures.PATCH_CACTUS, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> GRASS_PLACED = register("grass_placed",
            WilderConfiguredFeatures.FERN_AND_GRASS, worldSurfaceSquaredWithCount(20));

    public static final Holder<PlacedFeature> RARE_GRASS_PLACED = register("rare_grass_placed",
            VegetationFeatures.PATCH_GRASS_JUNGLE, worldSurfaceSquaredWithCount(8));

    public static final Holder<PlacedFeature> TALL_GRASS = register("tall_grass",
            VegetationFeatures.PATCH_TALL_GRASS, RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> DENSE_TALL_GRASS_PLACED = register("dense_tall_grass_placed",
            VegetationFeatures.PATCH_TALL_GRASS, worldSurfaceSquaredWithCount(1));

    public static final Holder<PlacedFeature> DENSE_FERN_PLACED = register("dense_fern_placed",
            VegetationFeatures.PATCH_LARGE_FERN, worldSurfaceSquaredWithCount(1));

    public static final Holder<PlacedFeature> SEAGRASS_CYPRESS = register("seagrass_cypress",
            AquaticFeatures.SEAGRASS_MID, seagrassPlacement(56));

    public static final Holder<PlacedFeature> LARGE_FERN_AND_GRASS = register("large_fern_and_grass",
            WilderConfiguredFeatures.LARGE_FERN_AND_GRASS, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final Holder<PlacedFeature> LARGE_FERN_AND_GRASS_RARE = register("large_fern_and_grass_rare",
            WilderConfiguredFeatures.LARGE_FERN_AND_GRASS, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> FLOWER_FIELD_GRASS_PLACED = register("flower_field_grass_placed",
			VegetationFeatures.PATCH_GRASS_JUNGLE, worldSurfaceSquaredWithCount(15));

	public static final Holder<PlacedFeature> PATCH_TALL_GRASS_FF = PlacementUtils.register("patch_tall_grass_ff", WilderConfiguredFeatures.LARGE_FERN_AND_GRASS_2, NoiseThresholdCountPlacement.of(-0.8, 0, 7), RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());


	//FLOWERS
    public static final Holder<PlacedFeature> SEEDING_DANDELION = register("seeding_dandelion",
            WilderConfiguredFeatures.SEEDING_DANDELION, RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> SEEDING_DANDELION_MIXED = register("seeding_dandelion_mixed",
            WilderConfiguredFeatures.SEEDING_DANDELION, RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> SEEDING_DANDELION_CYPRESS = register("seeding_dandelion_cypress",
            WilderConfiguredFeatures.SEEDING_DANDELION, RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> CARNATION = register("carnation",
            WilderConfiguredFeatures.CARNATION, RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> DATURA_BIRCH = register("datura_birch",
            WilderConfiguredFeatures.DATURA, RarityFilter.onAverageOnceEvery(9), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> FLOWER_PLAINS = register("flower_plains",
            WilderConfiguredFeatures.FLOWER_PLAINS, NoiseThresholdCountPlacement.of(-0.8, 15, 4), RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> DENSE_FLOWER_PLACED = register("dense_flower_placed",
            VegetationFeatures.FLOWER_DEFAULT, worldSurfaceSquaredWithCount(1));

    public static final Holder<PlacedFeature> FLOWER_FOREST_FLOWERS = register(
            "flower_forest_flowers",
            VegetationFeatures.FOREST_FLOWERS,
            RarityFilter.onAverageOnceEvery(7),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            CountPlacement.of(ClampedInt.of(UniformInt.of(-1, 3), 0, 3)),
            BiomeFilter.biome()
>>>>>>> dev
    );
    public static final ResourceKey<PlacedFeature> MILKWEED = key("milkweed");
    public static final ResourceKey<PlacedFeature> MILKWEED_CYPRESS = key("milkweed_cypress");
    public static final ResourceKey<PlacedFeature> GLORY_OF_THE_SNOW = key("glory_of_the_snow");
	public static final ResourceKey<PlacedFeature> FLOWER_FLOWER_FIELD = key("flower_flower_field");
	public static final ResourceKey<PlacedFeature> TALL_FLOWER_FLOWER_FIELD = key("tall_flower_field_flowers");
	public static final ResourceKey<PlacedFeature> PRICKLY_PEAR = key("prickly_pear");

    //VEGETATION
    public static final ResourceKey<PlacedFeature> POLLEN = key("pollen");
    public static final ResourceKey<PlacedFeature> PATCH_CATTAIL = key("cattail");
    public static final ResourceKey<PlacedFeature> PATCH_FLOWERED_WATERLILY = key("patch_flowered_waterlily");
    public static final ResourceKey<PlacedFeature> PATCH_ALGAE = key("patch_algae");
    public static final ResourceKey<PlacedFeature> PATCH_ALGAE_5 = key("patch_algae_5");
	public static final ResourceKey<PlacedFeature> TUMBLEWEED = key("tumbleweed");
    public static final ResourceKey<PlacedFeature> PATCH_BERRY_FOREST = key("patch_berry_forest");
    public static final ResourceKey<PlacedFeature> TERMITE = key("termite");
	public static final ResourceKey<PlacedFeature> BADLANDS_TALL_CACTUS_PLACED = key("badlands_tall_cactus_placed");
	public static final ResourceKey<PlacedFeature> BADLANDS_BUSH_SAND_PLACED = key("badlands_bush_sand_placed");
	public static final ResourceKey<PlacedFeature> BADLANDS_BUSH_TERRACOTTA_PLACED = key("badlands_bush_terracotta_placed");

<<<<<<< HEAD
	//JELLYFISH CAVES
    public static final ResourceKey<PlacedFeature> JELLYFISH_CAVES_BLUE_MESOGLEA = key("blue_mesoglea");
    public static final ResourceKey<PlacedFeature> JELLYFISH_CAVES_UPSIDE_DOWN_BLUE_MESOGLEA = key("upside_down_blue_mesoglea");
    public static final ResourceKey<PlacedFeature> JELLYFISH_CAVES_PURPLE_MESOGLEA = key("purple_mesoglea");
    public static final ResourceKey<PlacedFeature> JELLYFISH_CAVES_UPSIDE_DOWN_PURPLE_MESOGLEA = key("upside_down_purple_mesoglea");
	public static final ResourceKey<PlacedFeature> MESOGLEA_CLUSTER_BLUE = key("mesoglea_cluster_blue");
	public static final ResourceKey<PlacedFeature> MESOGLEA_CLUSTER_PURPLE = key("mesoglea_cluster_purple");
	public static final ResourceKey<PlacedFeature> LARGE_MESOGLEA_BLUE = key("large_mesoglea_blue");
	public static final ResourceKey<PlacedFeature> LARGE_MESOGLEA_PURPLE = key("large_mesoglea_purple");
    public static final ResourceKey<PlacedFeature> NEMATOCYST_BLUE = key("nematocyst_blue");
    public static final ResourceKey<PlacedFeature> NEMATOCYST_PURPLE = key("nematocyst_purple");
	public static final ResourceKey<PlacedFeature> SMALL_SPONGES = key("small_sponges");
	public static final ResourceKey<PlacedFeature> SMALL_SPONGES_RARE = key("small_sponges_rare");

    public static final Holder<PlacedFeature> MILKWEED_CYPRESS = register("milkweed_cypress",
            WilderConfiguredFeatures.MILKWEED, RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> GLORY_OF_THE_SNOW = register("glory_of_the_snow",
            WilderConfiguredFeatures.GLORY_OF_THE_SNOW, RarityFilter.onAverageOnceEvery(11), CountPlacement.of(2), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, InSquarePlacement.spread(), BiomeFilter.biome());

	public static final Holder<PlacedFeature> FLOWER_FLOWER_FIELD = register("flower_flower_field", WilderConfiguredFeatures.FLOWER_FLOWER_FIELD,
			CountPlacement.of(3), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> FLOWER_TEMPERATE_RAINFOREST = register("flower_temperate_rainforest", WilderConfiguredFeatures.FLOWERS_TEMPERATE_RAINFOREST,
			CountPlacement.of(2), RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> FLOWER_RAINFOREST = register("flower_rainforest", WilderConfiguredFeatures.FLOWERS_RAINFOREST,
			CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> MOSS_CARPET = register("moss_carpet", WilderConfiguredFeatures.MOSS_CARPET,
			RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature>  TALL_FLOWER_FIELD_FLOWERS = register("tall_flower_field_flowers", WilderConfiguredFeatures.TALL_FLOWER_FLOWER_FIELD,
			RarityFilter.onAverageOnceEvery(7), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, CountPlacement.of(ClampedInt.of(UniformInt.of(-1, 4), 0, 4)), BiomeFilter.biome());

	//VEGETATION
    public static final Holder<PlacedFeature> POLLEN_PLACED = register("pollen",
            WilderConfiguredFeatures.POLLEN_CONFIGURED, RarityFilter.onAverageOnceEvery(1), CountPlacement.of(2), PlacementUtils.HEIGHTMAP, InSquarePlacement.spread(), SurfaceRelativeThresholdFilter.of(Heightmap.Types.MOTION_BLOCKING, 0, 128), BiomeFilter.biome());

    public static final Holder<PlacedFeature> PATCH_CATTAIL =
            register("cattail", WilderConfiguredFeatures.CATTAIL,
                    RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

	public static final Holder<PlacedFeature> PATCH_CATTAIL_COMMON =
			register("cattail_common", WilderConfiguredFeatures.CATTAIL_06,
					InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> PATCH_FLOWERED_WATERLILY = register("patch_flowered_waterlily",
            WilderConfiguredFeatures.PATCH_FLOWERED_WATERLILY, worldSurfaceSquaredWithCount(1));

    public static final Holder<PlacedFeature> PATCH_ALGAE =
            register("patch_algae", WilderConfiguredFeatures.PATCH_ALGAE,
                    RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> PATCH_ALGAE_5 =
            register("patch_algae_5", WilderConfiguredFeatures.PATCH_ALGAE,
                    RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<PlacedFeature> PATCH_BERRY_FOREST =
            register("patch_berry_forest", VegetationFeatures.PATCH_BERRY_BUSH, RarityFilter.onAverageOnceEvery(28), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static final Holder<PlacedFeature> TERMITE_PLACED = register("termite_placed",
            WilderConfiguredFeatures.TERMITE_CONFIGURED, RarityFilter.onAverageOnceEvery(45), CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, InSquarePlacement.spread(), SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, 0, 128), BiomeFilter.biome());

	public static final Holder<PlacedFeature> TUMBLEWEED = register("tumbleweed",
			WilderConfiguredFeatures.TUMBLEWEED, RarityFilter.onAverageOnceEvery(9), CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> PRICKLY_PEAR = register("prickly_pear",
			WilderConfiguredFeatures.PRICKLY_PEAR, RarityFilter.onAverageOnceEvery(7), CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> PRICKLY_PEAR_RARE = register("prickly_pear_rare",
			WilderConfiguredFeatures.PRICKLY_PEAR, RarityFilter.onAverageOnceEvery(9), CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<PlacedFeature> JELLYFISH_CAVES_BLUE_MESOGLEA = register(
            "blue_mesoglea",
            WilderConfiguredFeatures.JELLYFISH_CAVES_BLUE_MESOGLEA,
            CountPlacement.of(9),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 1),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome());

    public static final Holder<PlacedFeature> JELLYFISH_CAVES_UPSIDE_DOWN_BLUE_MESOGLEA = register(
            "upside_down_blue_mesoglea",
            WilderConfiguredFeatures.UPSIDE_DOWN_BLUE_MESOGLEA,
            CountPlacement.of(9),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 1),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome());

    public static final Holder<PlacedFeature> JELLYFISH_CAVES_PURPLE_MESOGLEA = register(
            "purple_mesoglea",
            WilderConfiguredFeatures.JELLYFISH_CAVES_PURPLE_MESOGLEA,
            CountPlacement.of(9),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 1),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome());

    public static final Holder<PlacedFeature> JELLYFISH_CAVES_UPSIDE_DOWN_PURPLE_MESOGLEA = register(
            "upside_down_purple_mesoglea",
            WilderConfiguredFeatures.UPSIDE_DOWN_PURPLE_MESOGLEA,
            CountPlacement.of(9),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 1),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome());

	public static final Holder<PlacedFeature> NEMATOCYST_BLUE = PlacementUtils.register(
			"nematocyst_blue",
			WilderConfiguredFeatures.NEMATOCYST_BLUE,
			CountPlacement.of(ConstantInt.of(99)),
			InSquarePlacement.spread(),
			PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
			BiomeFilter.biome()
	);

	public static final Holder<PlacedFeature> NEMATOCYST_PURPLE = PlacementUtils.register(
			"nematocyst_purple",
			WilderConfiguredFeatures.NEMATOCYST_PURPLE,
			CountPlacement.of(ConstantInt.of(99)),
			InSquarePlacement.spread(),
			PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
			BiomeFilter.biome()
	);

	public static final Holder<PlacedFeature> MESOGLEA_CLUSTER_PURPLE = PlacementUtils.register(
			"mesoglea_cluster_purple",
			WilderConfiguredFeatures.MESOGLEA_CLUSTER_PURPLE,
			CountPlacement.of(UniformInt.of(9, 15)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

	public static final Holder<PlacedFeature> MESOGLEA_CLUSTER_BLUE = PlacementUtils.register(
			"mesoglea_cluster_blue",
			WilderConfiguredFeatures.MESOGLEA_CLUSTER_BLUE,
			CountPlacement.of(UniformInt.of(6, 13)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

	public static final Holder<PlacedFeature> LARGE_MESOGLEA_PURPLE = PlacementUtils.register(
			"large_mesoglea_purple",
			WilderConfiguredFeatures.LARGE_MESOGLEA_PURPLE,
			CountPlacement.of(UniformInt.of(1, 5)), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

	public static final Holder<PlacedFeature> LARGE_MESOGLEA_BLUE = PlacementUtils.register(
			"large_mesoglea_blue",
			WilderConfiguredFeatures.LARGE_MESOGLEA_BLUE,
			CountPlacement.of(UniformInt.of(1, 5)), RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());

	public static final Holder<PlacedFeature> SMALL_SPONGES = PlacementUtils.register(
			"small_sponges",
			WilderConfiguredFeatures.SMALL_SPONGE,
			CountPlacement.of(ConstantInt.of(82)),
			InSquarePlacement.spread(),
			PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
			BiomeFilter.biome()
	);

	public static final Holder<PlacedFeature> SMALL_SPONGES_RARE = PlacementUtils.register(
			"small_sponges_rare",
			WilderConfiguredFeatures.SMALL_SPONGE,
			CountPlacement.of(ConstantInt.of(42)),
			InSquarePlacement.spread(),
			PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
			BiomeFilter.biome()
	);

	public static ResourceKey<PlacedFeature> key(String path) {
		return ResourceKey.create(Registries.PLACED_FEATURE, WilderSharedConstants.id(path));
	}

    public static Holder<PlacedFeature> register(@NotNull String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, @NotNull List<PlacementModifier> modifiers) {
        return PlacementUtils.register(WilderSharedConstants.string(id), registryEntry, modifiers);
    }

    public static Holder<PlacedFeature> register(@NotNull String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, @NotNull PlacementModifier... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }
}

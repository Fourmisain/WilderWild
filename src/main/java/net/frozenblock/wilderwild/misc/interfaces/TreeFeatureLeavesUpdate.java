package net.frozenblock.wilderwild.misc.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public interface TreeFeatureLeavesUpdate {

	DiscreteVoxelShape wilderWild$updateLeaves(
		LevelAccessor level,
		@NotNull BoundingBox box,
		Set<BlockPos> rootPositions,
		Set<BlockPos> trunkPositions,
		Set<BlockPos> foliagePositions
	);
}

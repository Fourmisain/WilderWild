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

package net.frozenblock.wilderwild.mixin.sculk;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.util.Iterator;
import java.util.List;
import net.frozenblock.lib.sculk.api.BooleanPropertySculkBehavior;
import net.frozenblock.wilderwild.block.sculk_behavior.SlabWallStairSculkBehavior;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.frozenblock.wilderwild.registry.RegisterProperties;
import net.frozenblock.wilderwild.tag.WilderBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SculkBehaviour;
import net.minecraft.world.level.block.SculkSpreader;
import net.minecraft.world.level.block.SculkVeinBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SculkSpreader.ChargeCursor.class)
public class SculkSpreaderChargeCursorMixin {

	@Unique
	private boolean wilderWild$isWorldGen;

	@Inject(method = "isMovementUnobstructed", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;subtract(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/BlockPos;", shift = At.Shift.BEFORE), cancellable = true)
	private static void wilderWild$isMovementUnobstructed(LevelAccessor level, BlockPos startPos, BlockPos spreadPos, CallbackInfoReturnable<Boolean> info) {
		BlockState cheatState = level.getBlockState(spreadPos);
		if (cheatState.is(WilderBlockTags.SCULK_STAIR_REPLACEABLE) || cheatState.is(WilderBlockTags.SCULK_WALL_REPLACEABLE) || cheatState.is(WilderBlockTags.SCULK_SLAB_REPLACEABLE)) {
			info.setReturnValue(true);
		}
	}

	@Inject(method = "getValidMovementPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelAccessor;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
	private static void wilderWild$getValidMovementPos(LevelAccessor level, BlockPos pos, RandomSource random, CallbackInfoReturnable<BlockPos> info, BlockPos.MutableBlockPos mutable, BlockPos.MutableBlockPos mutable2, Iterator<Vec3i> var5, Vec3i vec3i) {
		boolean canReturn = false;
		BlockState state = level.getBlockState(mutable2);
		boolean isInTags = state.is(WilderBlockTags.SCULK_SLAB_REPLACEABLE) || state.is(WilderBlockTags.SCULK_WALL_REPLACEABLE) || state.is(WilderBlockTags.SCULK_STAIR_REPLACEABLE);
		if (isInTags && isMovementUnobstructed(level, pos, mutable2)) {
			mutable.set(mutable2);
			canReturn = true;
			if (SculkVeinBlock.hasSubstrateAccess(level, state, mutable2)) {
				info.cancel();
			}
		}

		if (canReturn) {
			info.setReturnValue(mutable.equals(pos) ? null : mutable);
		}
	}

	@Inject(method = "update", at = @At("HEAD"))
	private void wilderWild$newSculkBehaviour(LevelAccessor level, BlockPos pos, RandomSource random, SculkSpreader spreader, boolean spread, CallbackInfo info) {
		this.wilderWild$isWorldGen = spreader.isWorldGeneration();
	}

	@WrapOperation(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SculkSpreader$ChargeCursor;getBlockBehaviour(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/SculkBehaviour;"))
	private SculkBehaviour wilderWild$newSculkBehaviour(BlockState par1, Operation<SculkBehaviour> operation) {
		if (this.wilderWild$isWorldGen) {
			if (par1.is(WilderBlockTags.SCULK_WALL_REPLACEABLE_WORLDGEN) || par1.is(WilderBlockTags.SCULK_SLAB_REPLACEABLE_WORLDGEN) || par1.is(WilderBlockTags.SCULK_STAIR_REPLACEABLE_WORLDGEN)) {
				return new SlabWallStairSculkBehavior();
			} else if (par1.is(RegisterBlocks.STONE_CHEST)) {
				return new BooleanPropertySculkBehavior(RegisterProperties.HAS_SCULK, true);
			}
		} else {
			if (par1.is(WilderBlockTags.SCULK_WALL_REPLACEABLE) || par1.is(WilderBlockTags.SCULK_SLAB_REPLACEABLE) || par1.is(WilderBlockTags.SCULK_STAIR_REPLACEABLE)) {
				return new SlabWallStairSculkBehavior();
			}
		}
		return operation.call(par1);
	}

	@WrapOperation(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/SculkSpreader$ChargeCursor;getValidMovementPos(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)Lnet/minecraft/core/BlockPos;"))
	private BlockPos wilderWild$newValidMovementPos(LevelAccessor levelAccessor, BlockPos blockPos, RandomSource random, Operation<BlockPos> operation) {
		if (this.wilderWild$isWorldGen) {
			return wilderWild$getValidMovementPosWorldgen(levelAccessor, blockPos, random);
		} else {
			return operation.call(levelAccessor, blockPos, random);
		}
	}

	@Unique
	@NotNull
	private static boolean wilderWild$isMovementUnobstructedWorldgen(LevelAccessor level, @NotNull BlockPos fromPos, BlockPos toPos) {
		if (fromPos.distManhattan(toPos) == 1) {
			return true;
		}
		BlockState cheatState = level.getBlockState(toPos);
		if (cheatState.is(WilderBlockTags.SCULK_STAIR_REPLACEABLE_WORLDGEN) || cheatState.is(WilderBlockTags.SCULK_WALL_REPLACEABLE_WORLDGEN) || cheatState.is(WilderBlockTags.SCULK_SLAB_REPLACEABLE_WORLDGEN) || (cheatState.is(RegisterBlocks.STONE_CHEST) && !cheatState.getValue(RegisterProperties.HAS_SCULK))) {
			return true;
		}
		BlockPos blockPos = toPos.subtract(fromPos);
		Direction direction = Direction.fromAxisAndDirection(Direction.Axis.X, blockPos.getX() < 0 ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE);
		Direction direction2 = Direction.fromAxisAndDirection(Direction.Axis.Y, blockPos.getY() < 0 ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE);
		Direction direction3 = Direction.fromAxisAndDirection(Direction.Axis.Z, blockPos.getZ() < 0 ? Direction.AxisDirection.NEGATIVE : Direction.AxisDirection.POSITIVE);
		if (blockPos.getX() == 0) {
			return isUnobstructed(level, fromPos, direction2) || isUnobstructed(level, fromPos, direction3);
		}
		if (blockPos.getY() == 0) {
			return isUnobstructed(level, fromPos, direction) || isUnobstructed(level, fromPos, direction3);
		}
		return isUnobstructed(level, fromPos, direction) || isUnobstructed(level, fromPos, direction2);
	}

	@Unique
	@NotNull
	private static BlockPos wilderWild$getValidMovementPosWorldgen(LevelAccessor level, @NotNull BlockPos pos, RandomSource random) {
		BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
		BlockPos.MutableBlockPos mutableBlockPos2 = pos.mutable();
		for (Vec3i vec3i : getRandomizedNonCornerNeighbourOffsets(random)) {
			mutableBlockPos2.setWithOffset(pos, vec3i);
			BlockState blockState = level.getBlockState(mutableBlockPos2);
			boolean canReturn = false;
			BlockState state = level.getBlockState(mutableBlockPos2);
			boolean isInTags = state.is(WilderBlockTags.SCULK_SLAB_REPLACEABLE_WORLDGEN) || state.is(WilderBlockTags.SCULK_WALL_REPLACEABLE_WORLDGEN) || state.is(WilderBlockTags.SCULK_STAIR_REPLACEABLE_WORLDGEN) || (state.is(RegisterBlocks.STONE_CHEST) && !state.getValue(RegisterProperties.HAS_SCULK));
			if (isInTags && wilderWild$isMovementUnobstructedWorldgen(level, pos, mutableBlockPos2)) {
				mutableBlockPos.set(mutableBlockPos2);
				canReturn = true;
				if (SculkVeinBlock.hasSubstrateAccess(level, state, mutableBlockPos2)) {
					return mutableBlockPos.equals(pos) ? null : mutableBlockPos;
				}
			}

			if (canReturn) {
				return mutableBlockPos.equals(pos) ? null : mutableBlockPos;
			}
			if (!(blockState.getBlock() instanceof SculkBehaviour) || !isMovementUnobstructed(level, pos, mutableBlockPos2))
				continue;
			mutableBlockPos.set(mutableBlockPos2);
			if (!SculkVeinBlock.hasSubstrateAccess(level, blockState, mutableBlockPos2)) continue;
			break;
		}
		return mutableBlockPos.equals(pos) ? null : mutableBlockPos;
	}

	//SHADOWS
	@Shadow
	private static boolean isMovementUnobstructed(LevelAccessor level, BlockPos sourcePos, BlockPos targetPos) {
		throw new AssertionError("Mixin injection failed - WilderWild SculkSpreaderChargeCursorMixin.");
	}

	@Shadow
	private static List<Vec3i> getRandomizedNonCornerNeighbourOffsets(RandomSource random) {
		throw new AssertionError("Mixin injection failed - WilderWild SculkSpreaderChargeCursorMixin.");
	}

	@Shadow
	private static boolean isUnobstructed(LevelAccessor level, BlockPos pos, Direction direction) {
		throw new AssertionError("Mixin injection failed - WilderWild SculkSpreaderChargeCursorMixin.");
	}

}

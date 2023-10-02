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

package net.frozenblock.wilderwild.mixin.server.mesoglea_column;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import java.util.Optional;
import net.frozenblock.wilderwild.block.MesogleaBlock;
import net.frozenblock.wilderwild.config.BlockConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BubbleColumnBlock.class)
public abstract class BubbleColumnBlockMixin extends Block {

	public BubbleColumnBlockMixin(Properties properties) {
		super(properties);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void wilderWild$tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo info) {
		if (BlockConfig.get().mesoglea.mesogleaBubbleColumns) {
			MesogleaBlock.updateColumn(level, pos.above(), state);
		}
	}

	@Inject(method = "getColumnState", at = @At("HEAD"), cancellable = true)
	private static void wilderWild$getColumnState(BlockState blockState, CallbackInfoReturnable<BlockState> info) {
		if (BlockConfig.get().mesoglea.mesogleaBubbleColumns) {
			Optional<Direction> dragDirection = MesogleaBlock.getDragDirection(blockState);
			dragDirection.ifPresent(direction -> info.setReturnValue(
				Blocks.BUBBLE_COLUMN.defaultBlockState().setValue(BubbleColumnBlock.DRAG_DOWN, direction == Direction.DOWN)
			));
		}
	}

	@Inject(method = "updateShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;updateShape(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", shift = At.Shift.BEFORE))
	public void wilderWild$updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
		if (BlockConfig.get().mesoglea.mesogleaBubbleColumns) {
			if (MesogleaBlock.hasBubbleColumn(neighborState)) {
				level.scheduleTick(pos, BubbleColumnBlock.class.cast(this), 5);
			}
		}
	}

	@ModifyExpressionValue(
		method = "updateColumn(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;)V",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/BubbleColumnBlock;canExistIn(Lnet/minecraft/world/level/block/state/BlockState;)Z", ordinal = 1)
	)
	private static boolean wilderWild$updateColumnBooleanTweak(boolean original) {
		if (BlockConfig.get().mesoglea.mesogleaBubbleColumns) {
			return true;
		}
		return original;
	}

	@Inject(
		method = "updateColumn(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;)V",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", ordinal = 1, shift = At.Shift.BEFORE),
		cancellable = true,
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private static void wilderWild$updateColumnIfStatement(LevelAccessor level, BlockPos pos, BlockState fluid, BlockState state, CallbackInfo info, BlockState columnState, BlockPos.MutableBlockPos mutableBlockPos) {
		if (BlockConfig.get().mesoglea.mesogleaBubbleColumns) {
			BlockState mutableState = level.getBlockState(mutableBlockPos);
			if (!canExistIn(mutableState)) {
				MesogleaBlock.updateColumn(level, mutableBlockPos, state);
				info.cancel();
			}
		}
	}

	@Shadow
	private static boolean canExistIn(BlockState blockState) {
		throw new AssertionError("Mixin injection failed - Wilder Wild BubbleColumnBlockMixin.");
	}

}

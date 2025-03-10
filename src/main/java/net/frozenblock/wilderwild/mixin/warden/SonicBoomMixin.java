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

package net.frozenblock.wilderwild.mixin.warden;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import java.util.function.Consumer;
import net.frozenblock.wilderwild.block.EchoGlassBlock;
import net.frozenblock.wilderwild.entity.render.animations.WilderWarden;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.frozenblock.wilderwild.misc.interfaces.WilderSonicBoom;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.frozenblock.wilderwild.registry.RegisterSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.warden.SonicBoom;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.level.ClipBlockStateContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = SonicBoom.class, priority = 1001)
public class SonicBoomMixin implements WilderSonicBoom {

	@Unique
	private static SonicBoom wilderWild$currentBoom;
	@Unique
	private boolean wilderWild$particlesEnded = false;

	@ModifyVariable(
		method = "method_43265",
		at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/phys/Vec3;add(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;"),
		ordinal = 0
	)
	private static Vec3 wilderWild$modifyVec(Vec3 value, @Share("wilderWild$vec32") LocalRef<Vec3> vec32Ref) {
		vec32Ref.set(value);
		return value;
	}

	@ModifyVariable(method = "method_43265", at = @At(value = "CONSTANT", args = "intValue=1", shift = At.Shift.BY, by = 3))
	private static int wilderWild$modifyInt(int original, @Share("wilderWild$vec32") LocalRef<Vec3> vec32Ref) {
		return ((WilderSonicBoom) wilderWild$currentBoom).wilderWild$particlesEnded() ? Mth.floor(vec32Ref.get().length()) + 10 : original;
	}

	@Inject(
		method = "method_43265",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I", shift = At.Shift.BEFORE),
		locals = LocalCapture.CAPTURE_FAILHARD
	)
	private static void wilderWild$stopParticles(Warden warden, ServerLevel level, LivingEntity livingEntity, CallbackInfo info, Vec3 vec3, Vec3 vec32, Vec3 vec33, int i, Vec3 vec34) {
		BlockPos hitPos = wilderWild$isOccluded(level, vec3, vec34);
		if (hitPos != null) {
			((WilderSonicBoom) wilderWild$currentBoom).wilderWild$endParticles();
		}
	}

	@Inject(
		method = "method_43265",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"),
		locals = LocalCapture.CAPTURE_FAILHARD,
		cancellable = true
	)
	private static void wilderWild$tick(Warden warden, ServerLevel level, LivingEntity livingEntity, CallbackInfo info, Vec3 vec3, Vec3 vec32, Vec3 vec33) {
		for (int i = 1; i < Mth.floor(vec32.length()) + 7; ++i) {
			Vec3 vec34 = vec3.add(vec33.scale(i));
			BlockPos hitPos = wilderWild$isOccluded(level, vec3, vec34);
			if (hitPos != null) {
				i = Mth.floor(vec32.length()) + 10;
				info.cancel();
				if (level.getBlockState(hitPos).is(RegisterBlocks.ECHO_GLASS)) {
					EchoGlassBlock.damage(level, hitPos);
				}
			}
		}
	}

	@ModifyExpressionValue(
		method = "method_43265",
		at = @At(value = "FIELD", target = "Lnet/minecraft/sounds/SoundEvents;WARDEN_SONIC_BOOM:Lnet/minecraft/sounds/SoundEvent;", opcode = Opcodes.GETSTATIC)
	)
	private static SoundEvent wilderWild$modifySound(SoundEvent original, Warden warden) {
		return ((WilderWarden)warden).wilderWild$isStella() ? RegisterSounds.ENTITY_WARDEN_BRAP : original;
	}

	@ModifyArg(
		method = "tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/monster/warden/Warden;J)V",
		at = @At(value = "INVOKE", target = "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V", ordinal = 1)
	)
	private Consumer<? super LivingEntity> wilderWild$setCurrent(Consumer<? super LivingEntity> original) {
		return target -> {
			wilderWild$currentBoom = SonicBoom.class.cast(this);
			original.accept(target);
		};
	}

	@Inject(method = "stop(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/monster/warden/Warden;J)V", at = @At("TAIL"))
	private void wilderWild$reset(ServerLevel level, Warden entity, long gameTime, CallbackInfo info) {
		this.wilderWild$particlesEnded = false;
	}

	@Unique
	@Nullable
	private static BlockPos wilderWild$isOccluded(@NotNull Level level, @NotNull Vec3 start, @NotNull Vec3 end) {
		Vec3 vec3d = new Vec3((double) Mth.floor(start.x) + 0.5D, (double) Mth.floor(start.y) + 0.5D, (double) Mth.floor(start.z) + 0.5D);
		Vec3 vec3d2 = new Vec3((double) Mth.floor(end.x) + 0.5D, (double) Mth.floor(end.y) + 0.5D, (double) Mth.floor(end.z) + 0.5D);
		BlockPos hitPos = null;
		boolean blocked = true;
		for (Direction direction : Direction.values()) {
			Vec3 vec3d3 = vec3d.relative(direction, 9.999999747378752E-6D);
			BlockHitResult hit = level.isBlockInLine(new ClipBlockStateContext(vec3d3, vec3d2, (state) -> state.is(RegisterBlocks.ECHO_GLASS)));
			if (hit.getType() != HitResult.Type.BLOCK) {
				blocked = false;
			} else {
				hitPos = hit.getBlockPos();
			}
		}
		if (blocked) {
			WilderSharedConstants.log("Warden Sonic Boom Blocked @ " + hitPos, WilderSharedConstants.UNSTABLE_LOGGING);
			return hitPos;
		} else {
			return null;
		}
	}

	@Unique
	@Override
	public boolean wilderWild$particlesEnded() {
		return this.wilderWild$particlesEnded;
	}

	@Unique
	@Override
	public void wilderWild$endParticles() {
		this.wilderWild$particlesEnded = true;
	}

}

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

package net.frozenblock.wilderwild.mixin.client.wind;

import net.frozenblock.lib.wind.api.ClientWindManager;
import net.frozenblock.wilderwild.config.MiscConfig;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BaseAshSmokeParticle.class)
public abstract class BaseAshSmokeParticleMixin extends TextureSheetParticle {

	protected BaseAshSmokeParticleMixin(ClientLevel clientLevel, double d, double e, double f) {
		super(clientLevel, d, e, f);
	}

	@Inject(method = "tick", at = @At("HEAD"))
	public void wilderWild$tick(CallbackInfo info) {
		Vec3 wind = ClientWindManager.getWindMovement(this.level, BlockPos.containing(this.x, this.y, this.z), 1.5).scale(MiscConfig.get().getParticleWindIntensity());
		this.xd += wind.x * 0.0005;
		this.yd += wind.y * 0.000001;
		this.zd += wind.z * 0.0005;
	}

}

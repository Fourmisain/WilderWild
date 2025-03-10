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

package net.frozenblock.wilderwild.mixin.item.instrument;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.frozenblock.lib.sound.api.FrozenSoundPackets;
import net.frozenblock.wilderwild.config.ItemConfig;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InstrumentItem.class)
public final class InstrumentItemMixin {

	@WrapOperation(method = "play", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
	private static void wilderWild$playRestrictionSound(Level level, Player player, Entity entity, SoundEvent soundEvent, SoundSource soundSource, float volume, float pitch, Operation<Void> original) {
		if (ItemConfig.get().restrictInstrumentSound) {
			if (!level.isClientSide) {
				FrozenSoundPackets.createMovingRestrictionSound(level, player, soundEvent, soundSource, volume, pitch, WilderSharedConstants.id("instrument"), true);
			}
		} else {
			original.call(level, player, entity, soundEvent, soundSource, volume, pitch);
		}
	}

	@WrapOperation(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;addCooldown(Lnet/minecraft/world/item/Item;I)V"))
	private void wilderWild$bypassCooldown(ItemCooldowns itemCooldowns, Item item, int useDuration, Operation<Void> original) {
		if (!ItemConfig.get().restrictInstrumentSound) {
			original.call(itemCooldowns, item, useDuration);
		}
	}

}

package net.frozenblock.wilderwild.item;

import net.frozenblock.wilderwild.entity.AncientHornProjectileEntity;
import net.frozenblock.wilderwild.registry.RegisterItems;
import net.frozenblock.wilderwild.registry.RegisterSounds;
import net.minecraft.class_7430;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.LargeEntitySpawnHelper;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AncientCityGoatHorn extends class_7430 {

    public AncientCityGoatHorn(Settings settings) {
        super(settings);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        MutableText mutableText = Text.translatable("item.wilderwild.ancient_goat_horn.sound.0");
        tooltip.add(mutableText.formatted(Formatting.GRAY));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        world.playSoundFromEntity(user, user, RegisterSounds.ANCIENT_GOAT_HORN_CALL_0, SoundCategory.PLAYERS, 0.5F, 1.0F);
        user.getItemCooldownManager().set(RegisterItems.ANCIENT_GOAT_HORN, 2);
        if (world instanceof ServerWorld server) {
            AncientHornProjectileEntity projectileEntity = new AncientHornProjectileEntity(world, user.getX(), user.getEyeY(), user.getZ());
            projectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 0.2F, 0.0F);
            projectileEntity.setDamage(10D);
            server.spawnEntity(projectileEntity);
        }
        return TypedActionResult.consume(itemStack);
    }

    private static void trySpawnWarden(ServerWorld world, BlockPos pos) {
        if (world.getGameRules().getBoolean(GameRules.DO_WARDEN_SPAWNING)) {
            LargeEntitySpawnHelper.trySpawnAt(EntityType.WARDEN, SpawnReason.TRIGGERED, world, pos, 20, 5, 6).ifPresent((entity) -> {
                entity.playSound(SoundEvents.ENTITY_WARDEN_AGITATED, 16.0F, 1.0F);
            });
        }

    }

}

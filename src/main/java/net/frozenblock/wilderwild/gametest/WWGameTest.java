package net.frozenblock.wilderwild.gametest;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.frozenblock.lib.gametest.api.TrackedPosition;
import net.frozenblock.wilderwild.misc.WilderSharedConstants;
import net.frozenblock.wilderwild.registry.RegisterBlocks;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import java.util.List;

public class WWGameTest implements FabricGameTest {

	private static final String AXE_INTERACTION = WilderSharedConstants.MOD_ID + ":gametest/axe_interaction";

	@GameTest(template = AXE_INTERACTION)
	public void axeInteraction(GameTestHelper helper) {
		ServerPlayer player = helper.makeMockServerPlayerInLevel();
		player.setPos(helper.absoluteVec(new Vec3(7.5, 2.0, 0.5)));
		ItemStack stack = new ItemStack(Items.DIAMOND_AXE);

		// variable names reflect the end result after axe interaction
		TrackedPosition<Vec3> strippedOak = TrackedPosition.createRelative(helper, Blocks.OAK_LOG, new Vec3(9.5, 4.5, 0.5));
		TrackedPosition<Vec3> strippedSpruce = TrackedPosition.createRelative(helper, Blocks.SPRUCE_LOG, new Vec3(9.5, 4.5, 2.5));
		TrackedPosition<Vec3> strippedBirch = TrackedPosition.createRelative(helper, Blocks.BIRCH_LOG, new Vec3(9.5, 4.5, 4.5));
		TrackedPosition<Vec3> strippedJungle = TrackedPosition.createRelative(helper, Blocks.JUNGLE_LOG, new Vec3(9.5, 4.5, 6.5));
		TrackedPosition<Vec3> strippedAcacia = TrackedPosition.createRelative(helper, Blocks.ACACIA_LOG, new Vec3(9.5, 4.5, 8.5));
		TrackedPosition<Vec3> strippedDarkOak = TrackedPosition.createRelative(helper, Blocks.DARK_OAK_LOG, new Vec3(6.5, 4.5, 0.5));
		TrackedPosition<Vec3> strippedMangrove = TrackedPosition.createRelative(helper, Blocks.MANGROVE_LOG, new Vec3(6.5, 4.5, 2.5));
		TrackedPosition<Vec3> strippedBaobab = TrackedPosition.createRelative(helper, RegisterBlocks.BAOBAB_LOG, new Vec3(6.5, 4.5, 4.5));
		TrackedPosition<Vec3> strippedCypress = TrackedPosition.createRelative(helper, RegisterBlocks.CYPRESS_LOG, new Vec3(6.5, 4.5, 6.5));
		TrackedPosition<Vec3> strippedPalm = TrackedPosition.createRelative(helper, RegisterBlocks.PALM_LOG, new Vec3(6.5, 4.5, 8.5));
		TrackedPosition<Vec3> strippedCherry = TrackedPosition.createRelative(helper, Blocks.CHERRY_LOG, new Vec3(3.5, 4.5, 0.5));
		TrackedPosition<Vec3> strippedCrimson = TrackedPosition.createRelative(helper, Blocks.CRIMSON_STEM, new Vec3(3.5, 4.5, 2.5));
		TrackedPosition<Vec3> strippedWarped = TrackedPosition.createRelative(helper, Blocks.WARPED_STEM, new Vec3(3.5, 4.5, 4.5));
		List<TrackedPosition<Vec3>> strippedLogs = List.of(
			strippedOak, strippedSpruce, strippedBirch, strippedJungle,strippedAcacia,
			strippedDarkOak, strippedMangrove, strippedBaobab, strippedCypress, strippedPalm,
			strippedCherry, strippedCrimson, strippedWarped
		);

		TrackedPosition<Vec3> hollowedOak = TrackedPosition.createRelative(helper, Blocks.OAK_LOG, new Vec3(9.5, 2.5, 0.5));
		TrackedPosition<Vec3> strippedHollowedOak = TrackedPosition.createRelative(helper, Blocks.OAK_LOG, new Vec3(9.5, 2.5, 1.5));
		TrackedPosition<Vec3> hollowedSpruce = TrackedPosition.createRelative(helper, Blocks.SPRUCE_LOG, new Vec3(9.5, 2.5, 2.5));
		TrackedPosition<Vec3> strippedHollowedSpruce = TrackedPosition.createRelative(helper, Blocks.SPRUCE_LOG, new Vec3(9.5, 2.5, 3.5));
		TrackedPosition<Vec3> hollowedBirch = TrackedPosition.createRelative(helper, Blocks.BIRCH_LOG, new Vec3(9.5, 2.5, 4.5));
		TrackedPosition<Vec3> strippedHollowedBirch = TrackedPosition.createRelative(helper, Blocks.BIRCH_LOG, new Vec3(9.5, 2.5, 5.5));
		TrackedPosition<Vec3> hollowedJungle = TrackedPosition.createRelative(helper, Blocks.JUNGLE_LOG, new Vec3(9.5, 2.5, 6.5));
		TrackedPosition<Vec3> strippedHollowedJungle = TrackedPosition.createRelative(helper, Blocks.JUNGLE_LOG, new Vec3(9.5, 2.5, 7.5));
		TrackedPosition<Vec3> hollowedAcacia = TrackedPosition.createRelative(helper, Blocks.ACACIA_LOG, new Vec3(9.5, 2.5, 8.5));
		TrackedPosition<Vec3> strippedHollowedAcacia = TrackedPosition.createRelative(helper, Blocks.ACACIA_LOG, new Vec3(9.5, 2.5, 9.5));
		TrackedPosition<Vec3> hollowedDarkOak = TrackedPosition.createRelative(helper, Blocks.DARK_OAK_LOG, new Vec3(6.5, 2.5, 0.5));
		TrackedPosition<Vec3> strippedHollowedollowedDarkOak = TrackedPosition.createRelative(helper, Blocks.DARK_OAK_LOG, new Vec3(6.5, 2.5, 1.5));
		TrackedPosition<Vec3> hollowedMangrove = TrackedPosition.createRelative(helper, Blocks.MANGROVE_LOG, new Vec3(6.5, 2.5, 2.5));
		TrackedPosition<Vec3> strippedHollowedMangrove = TrackedPosition.createRelative(helper, Blocks.MANGROVE_LOG, new Vec3(6.5, 2.5, 3.5));
		TrackedPosition<Vec3> hollowedBaobab = TrackedPosition.createRelative(helper, RegisterBlocks.BAOBAB_LOG, new Vec3(6.5, 2.5, 4.5));
		TrackedPosition<Vec3> strippedHollowedBaobab = TrackedPosition.createRelative(helper, RegisterBlocks.BAOBAB_LOG, new Vec3(6.5, 2.5, 5.5));
		TrackedPosition<Vec3> hollowedCypress = TrackedPosition.createRelative(helper, RegisterBlocks.CYPRESS_LOG, new Vec3(6.5, 2.5, 6.5));
		TrackedPosition<Vec3> strippedHollowedCypress = TrackedPosition.createRelative(helper, RegisterBlocks.CYPRESS_LOG, new Vec3(6.5, 2.5, 7.5));
		TrackedPosition<Vec3> hollowedPalm = TrackedPosition.createRelative(helper, RegisterBlocks.PALM_LOG, new Vec3(6.5, 2.5, 8.5));
		TrackedPosition<Vec3> strippedHollowedPalm = TrackedPosition.createRelative(helper, RegisterBlocks.PALM_LOG, new Vec3(6.5, 2.5, 9.5));
		TrackedPosition<Vec3> hollowedCherry = TrackedPosition.createRelative(helper, Blocks.CHERRY_LOG, new Vec3(3.5, 2.5, 0.5));
		TrackedPosition<Vec3> strippedHollowedCherry = TrackedPosition.createRelative(helper, Blocks.CHERRY_LOG, new Vec3(3.5, 2.5, 1.5));
		TrackedPosition<Vec3> hollowedCrimson = TrackedPosition.createRelative(helper, Blocks.CRIMSON_STEM, new Vec3(3.5, 2.5, 2.5));
		TrackedPosition<Vec3> strippedHollowedCrimson = TrackedPosition.createRelative(helper, Blocks.CRIMSON_STEM, new Vec3(3.5, 2.5, 3.5));
		TrackedPosition<Vec3> hollowedWarped = TrackedPosition.createRelative(helper, Blocks.WARPED_STEM, new Vec3(3.5, 2.5, 4.5));
		TrackedPosition<Vec3> strippedHollowedWarped = TrackedPosition.createRelative(helper, Blocks.WARPED_STEM, new Vec3(3.5, 2.5, 5.5));
		List<TrackedPosition<Vec3>> hollowedLogs = List.of(
			hollowedOak, hollowedSpruce, hollowedBirch, hollowedJungle, hollowedAcacia,
			hollowedDarkOak, hollowedMangrove, hollowedBaobab, hollowedCypress, hollowedPalm,
			hollowedCherry, hollowedCrimson, hollowedWarped,
			strippedHollowedOak, strippedHollowedSpruce, strippedHollowedBirch, strippedHollowedJungle, strippedHollowedAcacia,
			strippedHollowedollowedDarkOak, strippedHollowedMangrove, strippedHollowedBaobab, strippedHollowedCypress, strippedHollowedPalm,
			strippedHollowedCherry, strippedHollowedCrimson, strippedHollowedWarped
		);

		helper.runAfterDelay(1L, () -> {
			for (TrackedPosition<Vec3> location : strippedLogs) {
				Vec3 absolute = location.absolute();

				player.setPos(absolute.add(-2, -2.5, 0));
				player.lookAt(EntityAnchorArgument.Anchor.EYES, absolute.add(0, 0, -0.5));
				BlockHitResult hitResult = (BlockHitResult) player.pick(2, 1.0F, false);
				UseOnContext context = new UseOnContext(player, InteractionHand.MAIN_HAND, hitResult);
				stack.useOn(context); // simulates a player using an axe on the log
			}
			for (TrackedPosition<Vec3> location : hollowedLogs) {
				Vec3 absolute = location.absolute();

				player.setPos(absolute.add(-2, -2.5, 0));
				player.lookAt(EntityAnchorArgument.Anchor.EYES, absolute.add(0, 0.5, 0));
				BlockHitResult hitResult = (BlockHitResult) player.pick(2, 1.0F, false);
				UseOnContext context = new UseOnContext(player, InteractionHand.MAIN_HAND, hitResult);
				stack.useOn(context); // simulates a player using an axe on the log
			}
		});
	}
}

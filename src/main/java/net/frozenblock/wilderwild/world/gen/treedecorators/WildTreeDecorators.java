package net.frozenblock.wilderwild.world.gen.treedecorators;

import com.mojang.serialization.Codec;
import net.frozenblock.wilderwild.WilderWild;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class WildTreeDecorators {
    public static TreeDecoratorType<ShelfFungusTreeDecorator> FUNGUS_TREE_DECORATOR = register("shelf_fungus_tree_decorator", ShelfFungusTreeDecorator.CODEC);

    public static void generateTreeDecorators() {
        //Just to ensure the class is loaded.
    }

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, Codec<P> codec) {
        return Registry.register(Registry.TREE_DECORATOR_TYPE, new Identifier(WilderWild.MOD_ID, id), new TreeDecoratorType<P>(codec));
    }
}

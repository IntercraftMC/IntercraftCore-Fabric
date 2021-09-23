package net.intercraft.intercraftcore.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.intercraft.intercraftcore.IntercraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

/**
 * Colour items and blocks for the client.
 */
public class ColorHandler
{
    private static final short
            totalLayers = 4,// Total layers a model can have, increase if you need more than 4 layers.
            totalLayerFlag = (short)(Math.pow(2,totalLayers) - 1);// 3=b111, 4=b1111, etc.

    public static final int[] LAYERS = new int[totalLayers];
    static {
        for (short i=0;i<totalLayers;i++) {
            LAYERS[i] = 1<<i;
        }
    }

    private static List<Item> items = new LinkedList<>();
    private static List<Block> blocks = new LinkedList<>();
    private static  List<Integer>
            colors = new LinkedList<>(),
            layers = new LinkedList<>();


    /**
     * Color item(s) and specify the layer to color.
     * @param color Color to apply.
     * @param layers Bit flag for layers to color, -1 for all layers.
     * @param items Item(s) to color.
     */
    public static void colorStaticItem(int color, int layers, Item...items)
    {
        for (Item item : items) {
            if (item != null) {
                ColorHandler.items.add(item);
                ColorHandler.colors.add(color);
                ColorHandler.layers.add(layers);
            }
        }
    }

    /**
     * Color block(s) and specify the layer to color.
     * @param color Color to apply.
     * @param layers Bit flag for layers to color, -1 for all layers.
     * @param blocks Block(s) to color.
     */
    public static void colorStaticBlock(int color, int layers, Block...blocks)
    {
        for (Block block : blocks) {
            if (block != null) {
                ColorHandler.blocks.add(block);
                ColorHandler.colors.add(color);
                ColorHandler.layers.add(layers);
            }
        }
    }

    /**
     * Registers color handlers for registered blocks and items.
     * Needs to happen in the registration order, otherwise colors will get out of
     * sync and will apply the wrong color to the block/item.
     */
    @Environment(EnvType.CLIENT)
    public static void register()
    {
        if (IntercraftCore.LOGGING)
            System.out.println("Registering color handlers");

        // Static block colors.
        for (int i=0;i<blocks.size();i++) {
            ColorProviderRegistry.BLOCK.register(new StaticBlockColorProvider(
                    colors.get(i),
                    layers.get(i)),
                    blocks.get(i));
        }

        if (IntercraftCore.LOGGING)
            System.out.println("[Block Colors] Done");

        // Static item colors.
        for (int i=0;i<items.size();i++) {
            ColorProviderRegistry.ITEM.register(new StaticItemColorProvider(
                    colors.get(blocks.size()+i),
                    layers.get(blocks.size()+i)),
                    items.get(i));
        }

        blocks = null;
        items = null;
        colors = null;
        layers = null;

        if (IntercraftCore.LOGGING)
            System.out.println("[Item Colors] Done");
    }

    @Environment(EnvType.CLIENT)
    private static class StaticBlockColorProvider implements BlockColorProvider
    {
        private final int tint, layers;
        /**
         * Create a block color provider for fixed colors.
         * @param tint The tint of the block.
         * @param layers Bit flag for the layer(s) to apply the tint to, -1 for all layers.
         */
        public StaticBlockColorProvider(int tint, int layers)
        {
            this.tint = tint;
            this.layers = layers;
        }
        @Override
        public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex)
        {
            if (layers == -1)
                return tint;
            return (totalLayerFlag & layers) == LAYERS[tintIndex] ? tint : 0xffffff;
        }
    }

    @Environment(EnvType.CLIENT)
    private static class StaticItemColorProvider implements ItemColorProvider
    {
        private final int tint, layers;
        /**
         * Create a item color provider for fixed colors.
         * @param tint The tint of the item.
         * @param layers Bit flag for the layer(s) to apply the tint to, -1 for all layers.
         */
        public StaticItemColorProvider(int tint, int layers)
        {
            this.tint = tint;
            this.layers = layers;
        }
        @Override
        public int getColor(ItemStack stack, int tintIndex)
        {
            if (layers == -1)
                return tint;
            return (totalLayerFlag & layers) == LAYERS[tintIndex] ? tint : 0xffffff;
        }
    }
}
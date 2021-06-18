package net.intercraft.intercraftcore.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.intercraft.intercraftcore.IntercraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.BlockItem;
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

    private static final List<Item> items = new LinkedList<>();
    private static final List<Block> blocks = new LinkedList<>();
    private static final List<BlockItem> blockItems = new LinkedList<>();


    private static final List<Integer>
            colors = new LinkedList<>(),
            layers = new LinkedList<>();
    static {
        for (short i=0;i<totalLayers;i++) {
            LAYERS[i] = 1<<i;
        }
    }

    /**
     * Color item(s) and specify the layer to color.
     * @param color Colour to apply.
     * @param layers Flag for layers to colour, -1 for all layers.
     * @param items Item(s) to colour.
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
    public static void colorStaticItem(int color, Item...items)
    {
        colorStaticItem(color,-1,items);
    }


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

    public static void colorStaticBlock(int color, Block...blocks)
    {
        colorStaticBlock(color,-1,blocks);
    }

    /**
     * Registers colour handlers for registered blocks and items.
     * Needs to happen in the registration order, otherwise colours will get out of
     * sync and will apply the wrong colour to the block/item.
     */
    @Environment(EnvType.CLIENT)
    public static void register()
    {
        if (IntercraftCore.LOGGING)
            System.out.println("Registering colour handlers");

        // Static block colours.
        for (int i=0;i<blocks.size();i++) {
            ColorProviderRegistry.BLOCK.register(new staticBlockColorProvider(colors.get(i),layers.get(i)),blocks.get(i));
        }
        if (IntercraftCore.LOGGING)
            System.out.println("[Block Colours] Done");

        // Static item colours.
        for (int i=0;i<items.size();i++) {
            ColorProviderRegistry.ITEM.register(new staticItemColorProvider(colors.get(blocks.size()+i),layers.get(blocks.size()+i)),items.get(i));
        }

        if (IntercraftCore.LOGGING)
            System.out.println("[Item Colours] Done");
    }

    @Environment(EnvType.CLIENT)
    private static class staticBlockColorProvider implements BlockColorProvider
    {
        private final int tint;
        private final int layers;
        /**
         * Create a block colour provider for fixed colours.
         * @param tint The tint of the block.
         * @param layers The layer to apply the tint to, -1 for all layers.
         */
        public staticBlockColorProvider(int tint, int layers)
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
    private static class staticItemColorProvider implements ItemColorProvider
    {
        private final int tint;
        private final int layers;
        /**
         * Create a item colour provider for fixed colours.
         * @param tint The tint of the item.
         * @param layers The layer to apply the tint to, -1 for all layers.
         */
        public staticItemColorProvider(int tint, int layers)
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
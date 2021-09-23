package net.intercraft.intercraftcore.common;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.intercraft.intercraftcore.client.ColorHandler;
import net.intercraft.intercraftcore.common.block.ElementBlock;
import net.intercraft.intercraftcore.common.block.TestBlock;
import net.intercraft.intercraftcore.common.element.BlockElementElementGroup;
import net.intercraft.intercraftcore.common.element.Elements;
import net.intercraft.intercraftcore.common.item.ModdedBlockItem;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;

import java.util.LinkedList;

public class Blocks
{
    // Need to register block color data before BlockItem data so we temporarily store them here.
    private static LinkedList<BlockItem> coloredBlockItems = new LinkedList<>();
    private static LinkedList<Integer> layersBlockItem = new LinkedList<>(), colorsBlockItem = new LinkedList<>();


    private static final FabricItemSettings itemGroup_RESOURCES = new FabricItemSettings().group(ItemGroups.RESOURCES);// Just put this here because I'm lazy.


    public static final Block TEST;

    public static final BlockElementElementGroup ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC;
    public static final BlockElementElementGroup CARBON, SILICON;
    public static final BlockElementElementGroup BRASS, BRONZE, ELECTRUM, STEEL;


    static {
        TEST = new TestBlock();


        ALUMINIUM = new BlockElementElementGroup(Elements.ALUMINIUM, ElementBlock.class);
        COPPER = new BlockElementElementGroup(Elements.COPPER, ElementBlock.class);
        GOLD = new BlockElementElementGroup(Elements.GOLD, ElementBlock.class);
        IRIDIUM = new BlockElementElementGroup(Elements.IRIDIUM, ElementBlock.class);
        IRON = new BlockElementElementGroup(Elements.IRON, ElementBlock.class);
        LEAD = new BlockElementElementGroup(Elements.LEAD, ElementBlock.class);
        LITHIUM = new BlockElementElementGroup(Elements.LITHIUM, ElementBlock.class);// TODO Reacts with water.
        MERCURY = new BlockElementElementGroup(Elements.MERCURY, ElementBlock.class);
        NICKEL = new BlockElementElementGroup(Elements.NICKEL, ElementBlock.class);
        SILVER = new BlockElementElementGroup(Elements.SILVER, ElementBlock.class);
        THORIUM = new BlockElementElementGroup(Elements.THORIUM, ElementBlock.class);
        TIN = new BlockElementElementGroup(Elements.TIN, ElementBlock.class);
        TITANIUM = new BlockElementElementGroup(Elements.TITANIUM, ElementBlock.class);
        TUNGSTEN = new BlockElementElementGroup(Elements.TUNGSTEN, ElementBlock.class);
        URANIUM = new BlockElementElementGroup(Elements.URANIUM, ElementBlock.class);// TODO Radioactive.
        ZINC = new BlockElementElementGroup(Elements.ZINC, ElementBlock.class);

        CARBON = new BlockElementElementGroup(Elements.CARBON, ElementBlock.class);
        SILICON = new BlockElementElementGroup(Elements.SILICON, ElementBlock.class);

        BRASS = new BlockElementElementGroup(Elements.BRASS, ElementBlock.class);
        BRONZE = new BlockElementElementGroup(Elements.BRONZE, ElementBlock.class);
        ELECTRUM = new BlockElementElementGroup(Elements.ELECTRUM, ElementBlock.class);
        STEEL = new BlockElementElementGroup(Elements.STEEL, ElementBlock.class);
    }

    protected static void initBlocks()
    {
        registerBlockAndItem(itemGroup_RESOURCES,TEST);

        register(itemGroup_RESOURCES, ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC);
        register(itemGroup_RESOURCES, CARBON, SILICON);
        register(itemGroup_RESOURCES, BRASS, BRONZE, ELECTRUM, STEEL);


        for (int i=0;i<coloredBlockItems.size();i++) {
            ColorHandler.colorStaticItem(colorsBlockItem.get(i),layersBlockItem.get(i),coloredBlockItems.get(i));
        }
        coloredBlockItems = null;
        layersBlockItem = null;
        colorsBlockItem = null;
    }

    /**
     * Register block(s).
     * @param blocks Block(s) to register.
     */
    private static void register(Block...blocks)
    {
        for (Block block : blocks) {
            if (block != null)
                RegistrationHandler.blocks.add(block);
        }
    }

    private static void register(FabricItemSettings settings, BlockElementElementGroup...blockElementGroups)
    {
        for (BlockElementElementGroup eg : blockElementGroups) {

            register(eg.BLOCK,eg.FRAME,eg.ORE_STONE,eg.ORE_DEEPSLATE,eg.RAW_BLOCK);

            // Applying color handler.
            ColorHandler.colorStaticBlock(eg.getElement().getColorRefined(),-1,
                    eg.BLOCK,eg.FRAME);
            ColorHandler.colorStaticBlock(eg.getElement().getColorUnrefined(), ColorHandler.LAYERS[1],
                    eg.ORE_STONE,eg.ORE_DEEPSLATE,eg.RAW_BLOCK);

            // Create ItemBlocks.
            registerColoredBlockItem(settings,eg.getElement().getColorRefined(),-1,
                    eg.BLOCK,eg.FRAME);
            registerColoredBlockItem(settings,eg.getElement().getColorUnrefined(),ColorHandler.LAYERS[1],
                    eg.ORE_STONE,eg.ORE_DEEPSLATE,eg.RAW_BLOCK);

            if (eg.ORE_STONE != null) {// TODO This causes a crash on servers, need to move to client initializer.
                BlockRenderLayerMap.INSTANCE.putBlock(eg.ORE_STONE, RenderLayer.getCutout());
            }
            if (eg.ORE_DEEPSLATE != null) {
                BlockRenderLayerMap.INSTANCE.putBlock(eg.ORE_DEEPSLATE, RenderLayer.getCutout());
            }
        }
    }

    /**
     * Register BlockItem(s) with color.
     * @param settings ItemBlock(s) item settings.
     * @param color Color of the ItemBlock(s).
     * @param layers Bit flags of layer(s) to color.
     * @param blocks Block(s) to create ItemBlock from.
     */
    private static void registerColoredBlockItem(FabricItemSettings settings, int color, int layers, Block...blocks)
    {
        BlockItem bi;
        for (Block block : blocks) {
            if (block != null) {
                bi = new ModdedBlockItem(block,settings) {
                    @Override
                    public String getRegistryName() {
                        return ((IRegistryName)block).getRegistryName();// I guess this can get a NullPointerException, but we will cross that gap when we get there
                    }
                };
                RegistrationHandler.blockItems.add(bi);
                coloredBlockItems.add(bi);
                layersBlockItem.add(layers);
                colorsBlockItem.add(color);
            }
        }
    }

    /**
     * Register block(s) + block item.
     * @param settings Item setting of the blocks' item.
     * @param blocks Block(s) to register.
     */
    private static void registerBlockAndItem(FabricItemSettings settings, Block...blocks)
    {
        for (Block block : blocks) {
            if (block != null) {
                RegistrationHandler.blocks.add(block);
                RegistrationHandler.blockItems.add(new ModdedBlockItem(block,settings) {
                    @Override
                    public String getRegistryName() {
                        return ((IRegistryName)block).getRegistryName();
                    }
                });
            }
        }
    }
}
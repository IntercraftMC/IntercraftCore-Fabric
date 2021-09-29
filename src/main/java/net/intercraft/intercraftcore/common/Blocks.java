package net.intercraft.intercraftcore.common;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.intercraft.intercraftcore.client.ColorHandler;
import net.intercraft.intercraftcore.common.block.ElementBlock;
import net.intercraft.intercraftcore.common.block.TestBlock;
import net.intercraft.intercraftcore.common.block.TreeTap;
import net.intercraft.intercraftcore.common.element.BlockElementGroup;
import net.intercraft.intercraftcore.common.element.Elements;
import net.intercraft.intercraftcore.common.item.ModdedBlockItem;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

import java.util.LinkedList;

public class Blocks
{
    // Need to register block color data before BlockItem data so we temporarily store them here.
    private static LinkedList<BlockItem> coloredBlockItems = new LinkedList<>();
    private static LinkedList<Integer> layersBlockItem = new LinkedList<>(), colorsBlockItem = new LinkedList<>();

    public static final LinkedList<Block> cutoutModels = new LinkedList<>();

    private static final FabricItemSettings// Just put this here because I'm lazy.
            itemGroup_RESOURCES = new FabricItemSettings().group(ItemGroups.RESOURCES),
            itemGroup_TOOLS = new FabricItemSettings().group(ItemGroup.TOOLS);


    public static final Block TEST;
    public static final Block TREETAP;

    public static final BlockElementGroup ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC;
    public static final BlockElementGroup CARBON, SILICON;
    public static final BlockElementGroup BRASS, BRONZE, ELECTRUM, STEEL;


    static {
        TEST = new TestBlock();
        TREETAP = new TreeTap();


        ALUMINIUM = new BlockElementGroup(Elements.ALUMINIUM, ElementBlock.class);
        COPPER = new BlockElementGroup(Elements.COPPER, ElementBlock.class);
        GOLD = new BlockElementGroup(Elements.GOLD, ElementBlock.class);
        IRIDIUM = new BlockElementGroup(Elements.IRIDIUM, ElementBlock.class);
        IRON = new BlockElementGroup(Elements.IRON, ElementBlock.class);
        LEAD = new BlockElementGroup(Elements.LEAD, ElementBlock.class);
        LITHIUM = new BlockElementGroup(Elements.LITHIUM, ElementBlock.class);// TODO Reacts with water.
        MERCURY = new BlockElementGroup(Elements.MERCURY, ElementBlock.class);
        NICKEL = new BlockElementGroup(Elements.NICKEL, ElementBlock.class);
        SILVER = new BlockElementGroup(Elements.SILVER, ElementBlock.class);
        THORIUM = new BlockElementGroup(Elements.THORIUM, ElementBlock.class);
        TIN = new BlockElementGroup(Elements.TIN, ElementBlock.class);
        TITANIUM = new BlockElementGroup(Elements.TITANIUM, ElementBlock.class);
        TUNGSTEN = new BlockElementGroup(Elements.TUNGSTEN, ElementBlock.class);
        URANIUM = new BlockElementGroup(Elements.URANIUM, ElementBlock.class);// TODO Radioactive.
        ZINC = new BlockElementGroup(Elements.ZINC, ElementBlock.class);

        CARBON = new BlockElementGroup(Elements.CARBON, ElementBlock.class);
        SILICON = new BlockElementGroup(Elements.SILICON, ElementBlock.class);

        BRASS = new BlockElementGroup(Elements.BRASS, ElementBlock.class);
        BRONZE = new BlockElementGroup(Elements.BRONZE, ElementBlock.class);
        ELECTRUM = new BlockElementGroup(Elements.ELECTRUM, ElementBlock.class);
        STEEL = new BlockElementGroup(Elements.STEEL, ElementBlock.class);
    }

    protected static void initBlocks()
    {
        registerBlockAndItem(itemGroup_RESOURCES,TEST);
        registerBlockAndItem(itemGroup_TOOLS,TREETAP);
        cutoutModels.add(TREETAP);

        register(itemGroup_RESOURCES, ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC);
        register(itemGroup_RESOURCES, CARBON, SILICON);
        register(itemGroup_RESOURCES, BRASS, BRONZE, ELECTRUM, STEEL);



        for (int i=0;i<coloredBlockItems.size();i++)
            ColorHandler.colorStaticItem(colorsBlockItem.get(i),layersBlockItem.get(i),coloredBlockItems.get(i));
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

    private static void register(FabricItemSettings settings, BlockElementGroup...blockElementGroups)
    {
        for (BlockElementGroup eg : blockElementGroups) {

            register(eg.BLOCK,eg.ORE_STONE,eg.ORE_DEEPSLATE,eg.RAW_BLOCK);

            // Applying color handler.
            ColorHandler.colorStaticBlock(eg.getElement().getColorRefined(),-1,
                    eg.BLOCK);
            ColorHandler.colorStaticBlock(eg.getElement().getColorUnrefined(), ColorHandler.LAYERS[1],
                    eg.ORE_STONE,eg.ORE_DEEPSLATE,eg.RAW_BLOCK);

            // Create ItemBlocks.
            registerColoredBlockItem(settings,eg.getElement().getColorRefined(),-1,
                    eg.BLOCK);
            registerColoredBlockItem(settings,eg.getElement().getColorUnrefined(),ColorHandler.LAYERS[1],
                    eg.ORE_STONE,eg.ORE_DEEPSLATE,eg.RAW_BLOCK);

            if (eg.RAW_BLOCK != null)
                cutoutModels.add(eg.RAW_BLOCK);
            if (eg.ORE_STONE != null)
                cutoutModels.add(eg.ORE_STONE);
            if (eg.ORE_DEEPSLATE != null)
                cutoutModels.add(eg.ORE_DEEPSLATE);
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
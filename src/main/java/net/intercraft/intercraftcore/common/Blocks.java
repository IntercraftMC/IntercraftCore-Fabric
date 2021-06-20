package net.intercraft.intercraftcore.common;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.intercraft.intercraftcore.client.ColorHandler;
import net.intercraft.intercraftcore.common.block.ElementBlock;
import net.intercraft.intercraftcore.common.block.ModdedBlock;
import net.intercraft.intercraftcore.common.block.TestBlock;
import net.intercraft.intercraftcore.common.element.BlockElementGroup;
import net.intercraft.intercraftcore.common.element.Elements;
import net.intercraft.intercraftcore.common.item.ModdedBlockItem;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemGroup;

public class Blocks
{
    private static final FabricItemSettings itemGroup_RESOURCES = new FabricItemSettings().group(ItemGroups.RESOURCES);// Just put this here because I'm lazy.


    public static final ModdedBlock TEST;

    public static final BlockElementGroup ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC;
    public static final BlockElementGroup CARBON, SILICON;
    public static final BlockElementGroup BRASS, BRONZE, ELECTRUM, STEEL;


    static {
        TEST = new TestBlock();



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
        registerBlockItem(itemGroup_RESOURCES,TEST);

        register(itemGroup_RESOURCES, ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC);
        register(itemGroup_RESOURCES, CARBON, SILICON);
        register(itemGroup_RESOURCES, BRASS, BRONZE, ELECTRUM, STEEL);
    }

    /**
     * Register block(s).
     * @param blocks Block(s) to register.
     */
    private static void register(ModdedBlock...blocks)
    {
        for (ModdedBlock block : blocks) {
            if (block != null)
                RegistrationHandler.blocks.add(block);
        }
    }

    private static void register(FabricItemSettings settings, BlockElementGroup...blockElementGroups)
    {
        for (BlockElementGroup eg : blockElementGroups) {

            registerBlockItem(settings,eg.BLOCK,eg.FRAME,eg.ORE_STONE,eg.ORE_DEEPSLATE,eg.RAW_BLOCK);

            // Applying colour handler.
            ColorHandler.colorStaticBlock(eg.getElement().getColorRefined(),
                    eg.BLOCK,eg.FRAME);
            ColorHandler.colorStaticBlock(eg.getElement().getColorUnrefined(), ColorHandler.LAYERS[1],
                    eg.ORE_STONE,eg.ORE_DEEPSLATE,eg.RAW_BLOCK);

            if (eg.ORE_STONE != null) {
                BlockRenderLayerMap.INSTANCE.putBlock(eg.ORE_STONE, RenderLayer.getCutout());
            }
            if (eg.ORE_DEEPSLATE != null) {
                BlockRenderLayerMap.INSTANCE.putBlock(eg.ORE_DEEPSLATE, RenderLayer.getCutout());
            }
        }
    }

    /**
     * Register block(s) + block item.
     * @param settings Item setting of the blocks' item.
     * @param blocks Block(s) to register.
     */
    private static void registerBlockItem(FabricItemSettings settings, ModdedBlock...blocks)
    {
        for (ModdedBlock block : blocks) {
            if (block != null) {
                RegistrationHandler.blocks.add(block);
                RegistrationHandler.blockItems.add(new ModdedBlockItem(block,settings) {
                    @Override
                    public String getRegistryName() {
                        return block.getRegistryName();
                    }
                });
            }
        }
    }
}
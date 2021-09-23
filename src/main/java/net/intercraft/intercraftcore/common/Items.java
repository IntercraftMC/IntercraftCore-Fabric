package net.intercraft.intercraftcore.common;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.intercraft.intercraftcore.client.ColorHandler;
import net.intercraft.intercraftcore.common.element.Elements;
import net.intercraft.intercraftcore.common.element.ItemElementElementGroup;
import net.intercraft.intercraftcore.common.item.ElementItem;
import net.intercraft.intercraftcore.common.item.TestItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Items
{

    public static final Item TEST;

    public static final ItemElementElementGroup ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC;
    public static final ItemElementElementGroup CARBON, SILICON;
    public static final ItemElementElementGroup BRASS, BRONZE, ELECTRUM, STEEL;


    static {
        TEST = new TestItem(new FabricItemSettings().group(ItemGroup.MISC));

        ALUMINIUM = new ItemElementElementGroup(Elements.ALUMINIUM, ElementItem.class);
        COPPER = new ItemElementElementGroup(Elements.COPPER, ElementItem.class);
        GOLD = new ItemElementElementGroup(Elements.GOLD, ElementItem.class);
        IRIDIUM = new ItemElementElementGroup(Elements.IRIDIUM, ElementItem.class);
        IRON = new ItemElementElementGroup(Elements.IRON, ElementItem.class);
        LEAD = new ItemElementElementGroup(Elements.LEAD, ElementItem.class);
        LITHIUM = new ItemElementElementGroup(Elements.LITHIUM, ElementItem.class);// TODO Reacts with water.
        MERCURY = new ItemElementElementGroup(Elements.MERCURY, ElementItem.class);
        NICKEL = new ItemElementElementGroup(Elements.NICKEL, ElementItem.class);
        SILVER = new ItemElementElementGroup(Elements.SILVER, ElementItem.class);
        THORIUM = new ItemElementElementGroup(Elements.THORIUM, ElementItem.class);
        TIN = new ItemElementElementGroup(Elements.TIN, ElementItem.class);
        TITANIUM = new ItemElementElementGroup(Elements.TITANIUM, ElementItem.class);
        TUNGSTEN = new ItemElementElementGroup(Elements.TUNGSTEN, ElementItem.class);
        URANIUM = new ItemElementElementGroup(Elements.URANIUM, ElementItem.class);// TODO Radioactive.
        ZINC = new ItemElementElementGroup(Elements.ZINC, ElementItem.class);

        CARBON = new ItemElementElementGroup(Elements.CARBON, ElementItem.class);
        SILICON = new ItemElementElementGroup(Elements.SILICON, ElementItem.class);

        BRASS = new ItemElementElementGroup(Elements.BRASS, ElementItem.class);
        BRONZE = new ItemElementElementGroup(Elements.BRONZE, ElementItem.class);
        ELECTRUM = new ItemElementElementGroup(Elements.ELECTRUM, ElementItem.class);
        STEEL = new ItemElementElementGroup(Elements.STEEL, ElementItem.class);
    }

    protected static void initItems()
    {
        register(TEST);
        register(ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC);
        register(CARBON, SILICON);
        register(BRASS, BRONZE, ELECTRUM, STEEL);
    }

    /**
     * Register item(s).
     * @param items Item(s) to register.
     */
    private static void register(Item...items)
    {
        for (Item item : items) {
            if (item != null) {
                RegistrationHandler.items.add(item);
            }
        }
    }

    /**
     * Register element group(s) of items.
     * @param itemElementGroups Group(s) of items to register.
     */
    private static void register(ItemElementElementGroup...itemElementGroups)
    {
        for (ItemElementElementGroup eg : itemElementGroups) {
            register(eg.INGOT, eg.NUGGET, eg.DUST, eg.DUST_SMALL, eg.PLATE, eg.GEAR, eg.ROD, eg.RAW);

            // Applying color handler.
            ColorHandler.colorStaticItem(eg.getElement().getColorRefined(),-1,
                    eg.INGOT, eg.NUGGET, eg.DUST, eg.DUST_SMALL, eg.PLATE, eg.GEAR, eg.ROD);
            ColorHandler.colorStaticItem(eg.getElement().getColorUnrefined(), ColorHandler.LAYERS[1],
                    eg.RAW);


        }
    }
}
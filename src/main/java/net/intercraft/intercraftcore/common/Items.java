package net.intercraft.intercraftcore.common;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.intercraft.intercraftcore.client.ColorHandler;
import net.intercraft.intercraftcore.common.element.Elements;
import net.intercraft.intercraftcore.common.element.ItemElementGroup;
import net.intercraft.intercraftcore.common.item.ElementItem;
import net.intercraft.intercraftcore.common.item.ModdedItem;
import net.intercraft.intercraftcore.common.item.TestItem;
import net.minecraft.item.ItemGroup;

public class Items
{

    public static final ModdedItem TEST;

    public static final ItemElementGroup ALUMINIUM, COPPER, GOLD, IRIDIUM, IRON, LEAD, LITHIUM, MERCURY, NICKEL, SILVER, THORIUM, TIN, TITANIUM, TUNGSTEN, URANIUM, ZINC;
    public static final ItemElementGroup CARBON, SILICON;
    public static final ItemElementGroup BRASS, BRONZE, ELECTRUM, STEEL;


    static {
        TEST = new TestItem(new FabricItemSettings().group(ItemGroup.MISC));

        ALUMINIUM = new ItemElementGroup(Elements.ALUMINIUM, ElementItem.class);
        COPPER = new ItemElementGroup(Elements.COPPER, ElementItem.class);
        GOLD = new ItemElementGroup(Elements.GOLD, ElementItem.class);
        IRIDIUM = new ItemElementGroup(Elements.IRIDIUM, ElementItem.class);
        IRON = new ItemElementGroup(Elements.IRON, ElementItem.class);
        LEAD = new ItemElementGroup(Elements.LEAD, ElementItem.class);
        LITHIUM = new ItemElementGroup(Elements.LITHIUM, ElementItem.class);// TODO Reacts with water.
        MERCURY = new ItemElementGroup(Elements.MERCURY, ElementItem.class);
        NICKEL = new ItemElementGroup(Elements.NICKEL, ElementItem.class);
        SILVER = new ItemElementGroup(Elements.SILVER, ElementItem.class);
        THORIUM = new ItemElementGroup(Elements.THORIUM, ElementItem.class);
        TIN = new ItemElementGroup(Elements.TIN, ElementItem.class);
        TITANIUM = new ItemElementGroup(Elements.TITANIUM, ElementItem.class);
        TUNGSTEN = new ItemElementGroup(Elements.TUNGSTEN, ElementItem.class);
        URANIUM = new ItemElementGroup(Elements.URANIUM, ElementItem.class);// TODO Radioactive.
        ZINC = new ItemElementGroup(Elements.ZINC, ElementItem.class);

        CARBON = new ItemElementGroup(Elements.CARBON, ElementItem.class);
        SILICON = new ItemElementGroup(Elements.SILICON, ElementItem.class);

        BRASS = new ItemElementGroup(Elements.BRASS, ElementItem.class);
        BRONZE = new ItemElementGroup(Elements.BRONZE, ElementItem.class);
        ELECTRUM = new ItemElementGroup(Elements.ELECTRUM, ElementItem.class);
        STEEL = new ItemElementGroup(Elements.STEEL, ElementItem.class);
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
    private static void register(ModdedItem...items)
    {
        for (ModdedItem item : items) {
            if (item != null) {
                RegistrationHandler.items.add(item);
            }
        }
    }

    private static void registerColor(ModdedItem...items)
    {
        for (ModdedItem item : items) {
            if (item != null)
                ColorHandler.items.add(item);
        }
    }

    /**
     * Register element group(s) of items.
     * @param itemElementGroups Group(s) of items to register.
     */
    private static void register(ItemElementGroup...itemElementGroups)
    {
        for (ItemElementGroup eg : itemElementGroups) {
            // TODO Color them.
            registerColor(eg.INGOT, eg.NUGGET, eg.DUST, eg.DUST_SMALL, eg.PLATE, eg.GEAR, eg.ROD, eg.RAW);
            register(eg.INGOT, eg.NUGGET, eg.DUST, eg.DUST_SMALL, eg.PLATE, eg.GEAR, eg.ROD, eg.RAW);

        }
    }
}

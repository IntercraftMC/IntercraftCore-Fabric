package net.intercraft.intercraftcore.common.element;

import net.intercraft.intercraftcore.common.ObjectElementGroup;
import net.intercraft.intercraftcore.common.item.ElementItem;

import java.lang.reflect.Constructor;

public class ItemElementElementGroup extends ObjectElementGroup<ElementItem>
{
    public final ElementItem INGOT, NUGGET, DUST, DUST_SMALL, PLATE, GEAR, ROD, RAW, VIAL_LIQUID, VIAL_GAS;
    //public final ModdedBlockItem BLOCK, FRAME, ORE_STONE, ORE_DEEPSLATE;

    /**
     * Create items from class T.
     * @param element Element config.
     * @param clazz Item Class to create items from.
     */
    public <T extends ElementItem> ItemElementElementGroup(Element element, Class<T> clazz)
    {
        super(element);
        final Constructor<?> c = createConstructor(clazz);

        INGOT       = createObject(c, Element.INGOT, "ingot");
        NUGGET      = createObject(c, Element.NUGGET, "nugget");
        DUST        = createObject(c, Element.DUST, "dust");
        DUST_SMALL  = createObject(c, Element.DUST_SMALL, "dust_small");
        PLATE       = createObject(c, Element.PLATE, "plate");
        GEAR        = createObject(c, Element.GEAR, "gear");
        ROD         = createObject(c, Element.ROD, "rod");
        RAW         = createObject(c, Element.RAW, "raw");
        VIAL_LIQUID = createObject(c, Element.RAW, "vial_liquid");
        VIAL_GAS    = createObject(c, Element.RAW, "vial_gas");
    }

    protected Constructor<?> createConstructor(Class<?> clazz)
    {
        try {
            return clazz.getConstructor(Element.class, String.class);
        } catch (NoSuchMethodException e) {
            printError(e);
        }
        return null;
    }
}
package net.intercraft.intercraftcore.common.element;

import net.intercraft.intercraftcore.common.ObjectGroup;
import net.intercraft.intercraftcore.common.item.ElementItem;
import net.intercraft.intercraftcore.common.item.ModdedItem;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ItemElementGroup extends ObjectGroup<ElementItem>
{
    public final ElementItem INGOT, NUGGET, DUST, DUST_SMALL, PLATE, GEAR, ROD, RAW, VIAL_LIQUID, VIAL_GAS;
    //public final ModdedBlockItem BLOCK, FRAME, ORE_STONE, ORE_DEEPSLATE;
    private final Element element;

    /**
     * Create items from class T.
     * @param element Element config.
     * @param clazz Item Class to create items from.
     */
    public <T extends ElementItem> ItemElementGroup(Element element, Class<T> clazz)
    {
        this.element = element;
        final Constructor<?> c = createConstructor(clazz);

        INGOT       = createObject(c, Element.INGOT, "ingot");
        NUGGET      = createObject(c, Element.NUGGET, "nugget");
        DUST        = createObject(c, Element.DUST, "dust");
        DUST_SMALL  = createObject(c, Element.DUST_SMALL, "dustsmall");
        PLATE       = createObject(c, Element.PLATE, "plate");
        GEAR        = createObject(c, Element.GEAR, "gear");
        ROD         = createObject(c, Element.ROD, "rod");
        RAW         = createObject(c, Element.RAW, "raw");
        VIAL_LIQUID = createObject(c, Element.RAW, "vial_liquid");
        VIAL_GAS    = createObject(c, Element.RAW, "vial_gas");
    }

    /**
     * Create new instance from constructor.
     * @param constructor Item constructor.
     * @param form Check if the item is enabled.
     * @param suffix The form of the item.
     */
    protected ElementItem createObject(Constructor<?> constructor, int form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                return (ElementItem) constructor.newInstance(new Object[] {element, suffix});
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                printError(e);
            }
        }
        return null;
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
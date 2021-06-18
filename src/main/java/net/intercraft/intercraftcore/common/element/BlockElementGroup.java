package net.intercraft.intercraftcore.common.element;

import net.intercraft.intercraftcore.common.ObjectGroup;
import net.intercraft.intercraftcore.common.block.ElementBlock;
import net.intercraft.intercraftcore.common.block.ModdedBlock;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BlockElementGroup extends ObjectGroup<ElementBlock>
{
    public final ElementBlock BLOCK, FRAME, ORE_STONE, ORE_DEEPSLATE, RAW_BLOCK;
    private final Element element;

    /**
     * Create blocks from class T.
     * @param element Element config.
     * @param clazz Block Class to create blocks from.
     */
    public <T extends ElementBlock> BlockElementGroup(Element element, Class<T> clazz)
    {
        this.element = element;
        final Constructor<?> c = createConstructor(clazz);

        BLOCK = createObject(c, Element.BLOCK, "block");
        FRAME = createObject(c, Element.FRAME, "frame");
        ORE_STONE = createObject(c, Element.ORE_STONE, "ore");
        ORE_DEEPSLATE = createObject(c, Element.ORE_DEEPSLATE, "deepslate_ore");
        RAW_BLOCK = createObject(c, Element.RAW_BLOCK, "raw_block");
    }

    public Element getElement()
    {
        return element;
    }

    /**
     * Create new instance from constructor.
     * @param constructor Item constructor.
     * @param form Check if the item is enabled.
     * @param suffix The form of the item.
     */
    protected ElementBlock createObject(Constructor<?> constructor, int form, String suffix)
    {
        if ((element.forms & form) == form) {
            try {
                return (ElementBlock) constructor.newInstance(new Object[] {element, suffix});
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
package net.intercraft.intercraftcore.common.element;

import net.intercraft.intercraftcore.common.ObjectElementGroup;
import net.intercraft.intercraftcore.common.block.ElementBlock;

import java.lang.reflect.Constructor;

public class BlockElementGroup extends ObjectElementGroup<ElementBlock>
{
    public final ElementBlock BLOCK, FRAME, ORE_STONE, ORE_DEEPSLATE, RAW_BLOCK;

    /**
     * Create blocks from class T.
     * @param element Element config.
     * @param clazz Block Class to create blocks from.
     */
    public <T extends ElementBlock> BlockElementGroup(Element element, Class<T> clazz)
    {
        super(element);
        final Constructor<?> c = createConstructor(clazz);

        BLOCK = createObject(c, Element.BLOCK, "block");
        FRAME = createObject(c, Element.FRAME, "frame");
        ORE_STONE = createObject(c, Element.ORE_STONE, "ore");
        ORE_DEEPSLATE = createObject(c, Element.ORE_DEEPSLATE, "deepslate_ore");
        RAW_BLOCK = createObject(c, Element.RAW_BLOCK, "raw_block");
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
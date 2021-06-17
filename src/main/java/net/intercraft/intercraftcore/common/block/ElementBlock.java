package net.intercraft.intercraftcore.common.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.intercraft.intercraftcore.common.element.Element;


public class ElementBlock extends ModdedBlock
{
    private final Element element;
    private final String suffix;

    public String getRegistryName()
    {
        return element.getName() + "_" + suffix;
    }

    /**
     * Generic auto-generated block from an element.
     * @param element Element to create block from.
     * @param suffix The shape of the element.
     */
    public ElementBlock(Element element, String suffix)
    {
        super(FabricBlockSettings.of(element.getMaterial()).strength(element.getStrength()));
        this.element = element;
        this.suffix = suffix;
    }
}
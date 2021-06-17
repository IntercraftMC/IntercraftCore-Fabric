package net.intercraft.intercraftcore.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.intercraft.intercraftcore.common.element.Element;
import net.minecraft.item.ItemGroup;

public class ElementBlockItem extends ModdedBlockItem
{
    private final Element element;
    private final String suffix;

    public String getRegistryName()
    {
        return element.getName() + "_" + suffix;
    }

    /**
     * Generic auto-generated item from an element.
     * @param element Element to create item from.
     * @param suffix The shape of the element.
     */
    public ElementBlockItem(Element element, String suffix)
    {
        super(null, new FabricItemSettings().group(ItemGroup.MISC));
        this.element = element;
        this.suffix = suffix;
    }
}
package net.intercraft.intercraftcore.common.item;

import net.intercraft.intercraftcore.common.IRegistryName;
import net.minecraft.item.Item;

public class TestItem extends Item implements IRegistryName
{
    public String getRegistryName()
    {
        return "test_item";
    }

    public TestItem(Settings settings)
    {
        super(settings);
    }
}
package net.intercraft.intercraftcore.common.item;

import net.intercraft.intercraftcore.common.IRegistryName;
import net.minecraft.item.Item;

public abstract class ModdedItem extends Item implements IRegistryName
{
    public ModdedItem(Settings settings)
    {
        super(settings);
    }
}

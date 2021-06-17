package net.intercraft.intercraftcore.common.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.intercraft.intercraftcore.common.IRegistryName;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public abstract class ModdedBlockItem extends BlockItem implements IRegistryName
{
    public ModdedBlockItem(Block block, FabricItemSettings settings)
    {
        super(block,settings);
    }
}

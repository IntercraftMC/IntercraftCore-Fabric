package net.intercraft.intercraftcore.common.block;

import net.intercraft.intercraftcore.common.IRegistryName;
import net.minecraft.block.Block;

public abstract class ModdedBlock extends Block implements IRegistryName
{
    public ModdedBlock(Settings settings)
    {
        super(settings);
    }
}
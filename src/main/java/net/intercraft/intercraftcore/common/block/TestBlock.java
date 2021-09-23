package net.intercraft.intercraftcore.common.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.intercraft.intercraftcore.common.IRegistryName;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class TestBlock extends Block implements IRegistryName
{
    public String getRegistryName() {
        return "test_block";
    }

    public TestBlock()
    {
        super(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    }
}
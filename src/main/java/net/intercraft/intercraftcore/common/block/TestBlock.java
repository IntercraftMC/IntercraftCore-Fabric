package net.intercraft.intercraftcore.common.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;

public class TestBlock extends ModdedBlock
{
    public String getRegistryName() {
        return "test_block";
    }

    public TestBlock()
    {
        super(FabricBlockSettings.of(Material.METAL).strength(4.0f));
    }
}
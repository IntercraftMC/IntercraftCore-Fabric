package net.intercraft.intercraftcore.common;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class ItemGroups
{
    public static final ItemGroup RESOURCES = FabricItemGroupBuilder.build(
            new Identifier(Reference.MODID,"resources"),
            () -> new ItemStack(Items.COPPER_INGOT)
    );
}

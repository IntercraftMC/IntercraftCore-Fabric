package net.intercraft.intercraftcore.common;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.common.entity.blockEntity.TreeTap;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BlockEntityTypes
{
    public static BlockEntityType<TreeTap> TREETAP;


    //public static final BlockEntity TREETAP;

    public static void initBlockEntityTypes()
    {
        //TREETAP = register("treetap_be",FabricBlockEntityTypeBuilder.create(TreeTap::new,Blocks.TREETAP));
        TREETAP = Registry.register(Registry.BLOCK_ENTITY_TYPE,IntercraftCore.MODID+"treetap_be",FabricBlockEntityTypeBuilder.create(TreeTap::new,Blocks.TREETAP).build(null));
    }


    /*private static <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder builder)
    {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, IntercraftCore.MODID+name, builder.build());
    }*/
}
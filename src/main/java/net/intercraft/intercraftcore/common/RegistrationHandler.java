package net.intercraft.intercraftcore.common;

import net.intercraft.intercraftcore.IntercraftCore;
import net.intercraft.intercraftcore.client.ColorHandler;
import net.intercraft.intercraftcore.common.block.ModdedBlock;
import net.intercraft.intercraftcore.common.item.ModdedBlockItem;
import net.intercraft.intercraftcore.common.item.ModdedItem;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedList;
import java.util.List;

public class RegistrationHandler
{
    protected static final List<ModdedBlock> blocks = new LinkedList<>();
    protected static final List<ModdedItem> items = new LinkedList<>();
    protected static final List<ModdedBlockItem> blockItems = new LinkedList<>();

    /**
     * Registers all objects. Needs to be in this order to prevent race condition.
     */
    public static void register()
    {


        Blocks.initBlocks();
        if (IntercraftCore.LOGGING)
            System.out.println(String.format("Preparing to register %s blocks + %s block items.",blocks.size(),blockItems.size()));
        for (ModdedBlock block : blocks)
            Registry.register(Registry.BLOCK, new Identifier(Reference.MODID,block.getRegistryName()),block);
        for (ModdedBlockItem item : blockItems)
            Registry.register(Registry.ITEM, new Identifier(Reference.MODID,item.getRegistryName()),item);

        Items.initItems();
        if (IntercraftCore.LOGGING)
            System.out.println("Preparing to register "+(items.size()+blockItems.size())+" items.");
        for (ModdedItem item : items)
            Registry.register(Registry.ITEM, new Identifier(Reference.MODID,item.getRegistryName()),item);


        //ColorHandler.register();

    }
}
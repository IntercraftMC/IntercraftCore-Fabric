package net.intercraft.intercraftcore.common;

import net.intercraft.intercraftcore.IntercraftCore;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedList;
import java.util.List;

public class RegistrationHandler
{
    protected static final List<Block> blocks = new LinkedList<>();
    protected static final List<Item> items = new LinkedList<>();
    protected static final List<BlockItem> blockItems = new LinkedList<>();

    /**
     * Registers all objects. Needs to be in this order to prevent race condition.
     */
    public static void register()
    {
        Blocks.initBlocks();
        if (IntercraftCore.LOGGING)
            System.out.println(String.format("Preparing to register %s blocks + %s block items.",blocks.size(),blockItems.size()));

        for (Block block : blocks) {
            if (block instanceof IRegistryName)
                Registry.register(Registry.BLOCK, new Identifier(IntercraftCore.MODID, ((IRegistryName) block).getRegistryName()), block);
            else if (IntercraftCore.LOGGING)
                System.out.println("Block didn't have a registry name!");
        }

        for (BlockItem item : blockItems) {
            if (item instanceof IRegistryName)
                Registry.register(Registry.ITEM, new Identifier(IntercraftCore.MODID, ((IRegistryName) item).getRegistryName()), item);
            else if (IntercraftCore.LOGGING)
                System.out.println("BlockItem didn't have a registry name!");
        }

        Items.initItems();
        if (IntercraftCore.LOGGING)
            System.out.println("Preparing to register "+items.size()+" items.");
        for (Item item : items) {
            if (item instanceof IRegistryName)
                Registry.register(Registry.ITEM, new Identifier(IntercraftCore.MODID, ((IRegistryName) item).getRegistryName()), item);
            else if (IntercraftCore.LOGGING)
                System.out.println("Item didn't have a registry name!");
        }

    }
}
package net.intercraft.intercraftcore.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.intercraft.intercraftcore.common.block.ModdedBlock;
import net.intercraft.intercraftcore.common.item.ElementItem;
import net.intercraft.intercraftcore.common.item.ModdedBlockItem;
import net.intercraft.intercraftcore.common.item.ModdedItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Colors items and blocks from the client.
 */
public class ColorHandler
{
    public static final List<ModdedBlock> blocks = new LinkedList<>();
    public static final List<ModdedItem> items = new LinkedList<>();
    public static final List<ModdedBlockItem> blockItems = new LinkedList<>();

    @Environment(EnvType.CLIENT)
    public static void register()
    {
        // TODO Make it more dynamic so that it uses tint index of the model file. Would be nice if it colored block particles too.
        for (ModdedItem item : items) {
            if (item instanceof ElementItem) {
                ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> ((ElementItem)item).getColorRefined()),item);
            }
        }
    }


}
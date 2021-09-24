package net.intercraft.intercraftcore.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.intercraft.intercraftcore.common.Blocks;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ClientHandler implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        for (Block block : Blocks.cutoutModels)
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());

        ColorHandler.register();
    }
}
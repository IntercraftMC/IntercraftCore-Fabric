package net.intercraft.intercraftcore.mixin;

import net.intercraft.intercraftcore.Reference;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class IntercraftCoreMixin
{
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info)
	{
		System.out.println("Initiating mixin '"+Reference.MODID+"'");



		System.out.println("Mixin Done.");
	}
}

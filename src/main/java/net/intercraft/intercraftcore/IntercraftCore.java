package net.intercraft.intercraftcore;

import net.fabricmc.api.ModInitializer;
import net.intercraft.intercraftcore.common.RegistrationHandler;


public class IntercraftCore implements ModInitializer
{
	public static final String
			MODID = "intercraftcore",
			NAME = "Intercraft Core",
			VERSION = "a0.1";

	public static boolean
			LOGGING = true,
			DEBUG = true;

	@Override
	public void onInitialize()
	{
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Initiating '"+IntercraftCore.MODID+"'");
		// TODO Create/Read configs.

		// Register all blocks, items, entities, etc.
		RegistrationHandler.register();


	}
}
package animalia.api;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class APILog 
{
	private static Logger animaliaLogger = Logger.getLogger("Animalia Mod API");
	
	static
	{
		animaliaLogger.setParent(FMLLog.getLogger());
	}
	
	public static void severe(String message)
	{
		animaliaLogger.severe(message);
	}
	
	public static void info(String message)
	{
		animaliaLogger.info(message);
	}
	
	public static void warning(String message)
	{
		animaliaLogger.warning(message);
	}
	
	public static void fine(String message)
	{
		animaliaLogger.fine(message);
	}
	
	public static void finer(String message)
	{
		animaliaLogger.finer(message);
	}
}

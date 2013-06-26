package animalia.common;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Config
{
	//Block Property
	public static Property fossilEPProp;
	public static Property fossilLPProp;
	public static Property fossilMesozoicProp;
	public static Property crystalOreProp;
	public static Property extractorProp;
	
	//Item Property
	public static Property crystalGemProp;
	public static Property extractorItemProp;
	
	public static void setUpConfig(Configuration config)
	{
		addBlockEntries(config);
		addItemEntries(config);
		addSettingsEntries(config);
	}

	private static void addBlockEntries(Configuration config) 
	{
		fossilEPProp = config.get(config.CATEGORY_BLOCK, "Early Paleozoic Fossil", 3000);
		fossilEPProp.comment = "Early Paleozoic Fossil Block ID";
		
		fossilLPProp = config.get(config.CATEGORY_BLOCK, "Late Paleozoic Fossil", 3001);
		fossilLPProp.comment = "Late Paleozoic Fossil Block ID";
		
		fossilMesozoicProp = config.get(config.CATEGORY_BLOCK, "Mesozoic Fossil", 3002);
		fossilMesozoicProp.comment = "Mesozoic Fossil Block ID";
		
		crystalOreProp = config.get(config.CATEGORY_BLOCK, "Crystal Ore", 3003);
		crystalOreProp.comment = "Crystal 4D Ore Block ID. The glowing version of this block takes this ID plus one, so be wary of this.";
		
		extractorProp = config.get(config.CATEGORY_BLOCK, "Extractor Block", 4000);
		extractorProp.comment = "Extractor Block ID. The On version of this block takes this ID plus one, so be wary of this.";
	}

	private static void addItemEntries(Configuration config) 
	{
		crystalGemProp = config.get(config.CATEGORY_ITEM, "Crystal 4D Gem", 5000);
		crystalGemProp.comment = "Crystal 4D Gem Item ID";
		
		extractorItemProp = config.get(config.CATEGORY_ITEM, "The Extractor Item", 6501);
		extractorItemProp.comment = "Extractor Item ID";
	}

	private static void addSettingsEntries(Configuration config) 
	{
		
	}
}

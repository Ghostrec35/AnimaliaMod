package animalia.common.config;

import java.io.File;
import java.util.logging.Level;

import animalia.common.ref.Reference;

import cpw.mods.fml.common.FMLLog;

import net.minecraftforge.common.Configuration;

public class ConfigHandler
{
    public static final String CATEGORY_TOOL = Configuration.CATEGORY_ITEM + Configuration.CATEGORY_SPLITTER + "tool";
    public static final String CATEGORY_GEM = Configuration.CATEGORY_ITEM + Configuration.CATEGORY_SPLITTER + "gem";
    
    public static Configuration config;
    
    public static void initConfig(File file)
    {
        config = new Configuration(file);
        
        try
        {
            config.load();
            
            addBlockEntries(config);
            addItemEntries(config);
            addSettingsEntries(config);
        }
        catch(Exception e)
        {
            FMLLog.log(Level.SEVERE, e,  Reference.MOD_NAME + " encountered a problem while loading its configuration file.");
        }
        finally
        {
            config.save();
        }
    }  
    
    private static void addBlockEntries(Configuration config) 
    {
        ConfigSettings.fossilEPProp = config.get(Configuration.CATEGORY_BLOCK, "Early Paleozoic Fossil", 3000);
        ConfigSettings.fossilEPProp.comment = "Early Paleozoic Fossil Block ID";
        
        ConfigSettings.fossilLPProp = config.get(Configuration.CATEGORY_BLOCK, "Late Paleozoic Fossil", 3001);
        ConfigSettings.fossilLPProp.comment = "Late Paleozoic Fossil Block ID";
        
        ConfigSettings.fossilMesozoicProp = config.get(Configuration.CATEGORY_BLOCK, "Mesozoic Fossil", 3002);
        ConfigSettings.fossilMesozoicProp.comment = "Mesozoic Fossil Block ID";
        
        ConfigSettings.crystalOreProp = config.get(Configuration.CATEGORY_BLOCK, "Crystal Ore", 3003);
        ConfigSettings.crystalOreProp.comment = "Crystal 4D Ore Block ID. The glowing version of this block takes this ID plus one, so be wary of this.";
        
        ConfigSettings.extractorProp = config.get(Configuration.CATEGORY_BLOCK, "Extractor Block", 4000);
        ConfigSettings.extractorProp.comment = "Extractor Block ID. The On version of this block takes this ID plus one, so be wary of this.";
        
        ConfigSettings.logsLPProp = config.get(Configuration.CATEGORY_BLOCK, "Late Paleozoic Logs", 3050);
        ConfigSettings.logsLPProp.comment = "Late Paleozoic Logs Block ID";
        
        ConfigSettings.leavesLPProp = config.get(Configuration.CATEGORY_BLOCK, "Late Paleozoic Leaves", 3051);
        ConfigSettings.leavesLPProp.comment = "Late Paleozoic Leaves Block ID";
        
        ConfigSettings.saplingLPProp = config.get(Configuration.CATEGORY_BLOCK, "Late Paleozoic Sapling", 3052);
        ConfigSettings.saplingLPProp.comment = "Late Paleozoic Sapling Block ID";
        
        ConfigSettings.planksLPProp = config.get(Configuration.CATEGORY_BLOCK, "Late Paleozoic Planks", 3053);
        ConfigSettings.planksLPProp.comment = "Late Paleozoic Planks Block ID";
        
        ConfigSettings.plantCalamitesProp = config.get(Configuration.CATEGORY_BLOCK, "Calamites Plant", 3062);
        ConfigSettings.plantCalamitesProp.comment = "Calamites Plant Block ID";
        
        ConfigSettings.blockCalamitesProp = config.get(Configuration.CATEGORY_BLOCK, "Calamites Block", 3056);
        ConfigSettings.blockCalamitesProp.comment = "Calamites Block ID";
        
        ConfigSettings.stairsSigProp = config.get(Configuration.CATEGORY_BLOCK, "Sigillaria Stairs", 3057);
        ConfigSettings.stairsSigProp.comment = "Sigillaria Stairs Block ID";
        
        ConfigSettings.stairsLepidoProp = config.get(Configuration.CATEGORY_BLOCK, "Lepidodendron Stairs", 3058);
        ConfigSettings.stairsLepidoProp.comment = "Lepidodendron Stairs Block ID";
        
        ConfigSettings.stairsCordProp = config.get(Configuration.CATEGORY_BLOCK, "Cordaites Stairs", 3059);
        ConfigSettings.stairsCordProp.comment = "Cordaites Stairs Block ID";
        
        ConfigSettings.slabLPProp = config.get(Configuration.CATEGORY_BLOCK, "Late Paleozoic Wood Slab", 3060);
        ConfigSettings.slabLPProp.comment = "Late Paleozoic Wood Slab Block ID";
        
        ConfigSettings.slabLPDoubleProp = config.get(Configuration.CATEGORY_BLOCK, "Late Paleozoic Wood Double Slab", 3061);
        ConfigSettings.slabLPDoubleProp.comment = "Late Paleozoic Wood Double Slab Block ID";
		
		ConfigSettings.olivineBlock = config.get(Configuration.CATEGORY_BLOCK, "Olivine Block", 3054);
        ConfigSettings.olivineBlock.comment = "Olivine Block ID";
		
    }

    private static void addItemEntries(Configuration config) 
    {
        ConfigSettings.crystalGemProp = config.get(CATEGORY_GEM, "Crystal 4D Gem", 5000);
        ConfigSettings.crystalGemProp.comment = "Crystal 4D Gem Item ID";
        
        ConfigSettings.extractorItemProp = config.get(CATEGORY_GEM, "The Extractor Item", 6501);
        ConfigSettings.extractorItemProp.comment = "Extractor Item ID";
        
        ConfigSettings.olivineGemProp = config.get(CATEGORY_GEM, "Olivine Gem", 6502);
        ConfigSettings.olivineGemProp.comment = "Olivine Gem Item ID";
        
        ConfigSettings.olivineGemProp = config.get(CATEGORY_GEM, "Calamites Item", 6503);
        ConfigSettings.olivineGemProp.comment = "Calamites Item ID";
    }

    private static void addSettingsEntries(Configuration config) 
    {
        
    }
}

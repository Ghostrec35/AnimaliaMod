package animalia.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import animalia.client.ClientTickHandler;
import animalia.common.block.Block4DCrystalOre;
import animalia.common.block.BlockEarlyPaleozoicFossil;
import animalia.common.block.BlockLatePaleozoicFossil;
import animalia.common.block.BlockMesozoicFossil;
import animalia.common.item.ItemCrystal4D;
import animalia.common.machine.extractor.BlockExtractor;
import animalia.common.network.PacketHandler;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IPickupNotifier;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "AnimaliaMod", name = "Animalia", version = "1.0.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "Animalia" }, packetHandler = PacketHandler.class)
public class Animalia
{
	// Retrieve the Constructed Mod Instance from Forge
	@Instance("AnimaliaMod")
	public static Animalia instance;

	// Retrieve Correct Proxy based on which Side this code is being run on.
	@SidedProxy(clientSide = "animalia.client.ClientProxy", serverSide = "animalia.common.CommonProxy")
	public static CommonProxy proxy;

	// Retrieve the ModMetadata Object from Forge
	@Metadata("AnimaliaMod")
	public static ModMetadata metadata;

	public static boolean isCurrentVersion;
	public static String latestModVersion;

	/*
	 * Creative Tabs TODO think about not having several tab but instead use the current ones for
	 * your generic blocks. Only use custom tabs for unique mod only content... Tool can be added to
	 * Vanilla too tab if there the same as normal tools
	 */
	public static AnimaliaCreativeTabs tabBlock;
	public static AnimaliaCreativeTabs tabMaterial;
	public static AnimaliaCreativeTabs tabTools;
	public static AnimaliaCreativeTabs tabWeapons;
	public static AnimaliaCreativeTabs tabArmors;
	public static AnimaliaCreativeTabs tabMachine;
	public static AnimaliaCreativeTabs tabDeco;

	/*
	 * EnumToolMaterial Values
	 */
	public static EnumToolMaterial OLIVINE = EnumHelper.addToolMaterial("OLIVINE", 3, 1100, 6F, 3, 16);

	/*
	 * EnumArmorMaterial Values
	 */
	public static EnumArmorMaterial OLIVINEARMOR = EnumHelper.addArmorMaterial("OLIVINE", 25, new int[] { 4, 5, 8, 6 }, 20);

	// EP is an Abbreviation for Early Paleozoic
	public static Block fossilEP;

	// LP is an Abbreviation for Late Paleozoic
	public static Block fossilLP;

	public static Block fossilMesozoic;

	// Crystal Ore
	public static Block crystal4DOre;
	public static Block crystal4DOreGlowing;

	// Item Crystal
	public static Item crystal4D;

	// Extractor Item
	public static Item extractorItem;

	/*
	 * Olivine Tools
	 */
	public static Item olivineGem;
	public static Item olivinePickaxe;
	public static Item olivineAxe;
	public static Item olivineShovel;
	public static Item olivineHoe;
	public static Item olivineSword;

	/*
	 * Olivine Armors
	 */
	public static Item olivineHelmet;
	public static Item olivineChestplate;
	public static Item olivineLeggings;
	public static Item olivineBoots;

	/*
	 * Machines
	 */
	public static Block extractorOff;
	public static Block extractorOn;

	@PreInit
	public void loadPre(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory() + "/Animalia.cfg"));
		config.load();
		Config.setUpConfig(config);
		config.save();
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		this.initObjects();
		this.initCreativeTabs();
		this.finishCreativeTabInit();
		this.registerBlocks();
		this.registerItems();
		this.registerLocalizations();
		this.registerRecipes();
		this.registerHarvestLevels();
		this.proxy.registerTileEntities();
		this.proxy.registerTextureInfo();
		this.proxy.registerRenders();
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);
		this.registerEventManager(new EventManager());
		this.registerTickHandlers();

		String[] currBuildStrings = metadata.version.split("\\.");
		String[] newBuildStrings = getCurrentRecommendedBuild().split("\\.");
		latestModVersion = getCurrentRecommendedBuild();
		isCurrentVersion = isSameVersion(new int[] { Integer.parseInt(currBuildStrings[0]), Integer.parseInt(currBuildStrings[1]), Integer.parseInt(currBuildStrings[2]), Integer.parseInt(currBuildStrings[3]) }, new int[] { Integer.parseInt(newBuildStrings[0]), Integer.parseInt(newBuildStrings[1]), Integer.parseInt(newBuildStrings[2]), Integer.parseInt(newBuildStrings[3]) });
	}

	@PostInit
	public void loadPost(FMLPostInitializationEvent event)
	{

	}

	private void initObjects()
	{
		fossilEP = new BlockEarlyPaleozoicFossil(Config.fossilEPProp.getInt(), Material.rock, "epFossil").setHardness(1F).setResistance(100).setUnlocalizedName("animalia:fossil_early_paleo");
		fossilLP = new BlockLatePaleozoicFossil(Config.fossilLPProp.getInt(), Material.rock, "lpFossil").setHardness(1F).setResistance(100).setUnlocalizedName("animalia:fossil_late_paleo");
		fossilMesozoic = new BlockMesozoicFossil(Config.fossilMesozoicProp.getInt(), Material.rock, "mesFossil").setHardness(1F).setResistance(100).setUnlocalizedName("animalia:fossil_meso");

		crystal4DOre = new Block4DCrystalOre(Config.crystalOreProp.getInt()).setHardness(1F).setResistance(100).setUnlocalizedName("animalia:crystal_ore");
		crystal4DOreGlowing = new Block4DCrystalOre(Config.crystalOreProp.getInt() + 1).setHardness(1F).setResistance(100).setUnlocalizedName("animalia:crystal_ore").setLightValue(1.0F);

		extractorOff = new BlockExtractor(Config.extractorProp.getInt(), false).setHardness(1F).setResistance(100);
		extractorOn = new BlockExtractor(Config.extractorProp.getInt() + 1, true).setHardness(1F).setResistance(100).setLightValue(1.0F);

		// Items
		crystal4D = new ItemCrystal4D(Config.crystalGemProp.getInt()).setUnlocalizedName("animalia:crystal");
		olivineGem = new Item(5006).setUnlocalizedName("animalia:olivine_gem");

		olivinePickaxe = new ItemPickaxe(5001, OLIVINE).setUnlocalizedName("animalia:tools/olivine_pickaxe");
		olivineAxe = new ItemAxe(5002, OLIVINE).setUnlocalizedName("animalia:tools/olivine_axe");
		olivineShovel = new ItemSpade(5003, OLIVINE).setUnlocalizedName("animalia:tools/olivine_spade");
		olivineHoe = new ItemHoe(5004, OLIVINE).setUnlocalizedName("animalia:tools/olivine_hoe");
		olivineSword = new ItemSword(5005, OLIVINE).setUnlocalizedName("animalia:weapons/olivine_sword");

		olivineHelmet = new ItemArmor(6000, OLIVINEARMOR, Constants.OLIVINE_ARMOR_RENDER, 0).setUnlocalizedName("animalia:armors/olivine_helmet");
		olivineChestplate = new ItemArmor(6001, OLIVINEARMOR, Constants.OLIVINE_ARMOR_RENDER, 1).setUnlocalizedName("animalia:armors/olivine_chestplate").setCreativeTab(tabArmors);
		olivineLeggings = new ItemArmor(6002, OLIVINEARMOR, Constants.OLIVINE_ARMOR_RENDER, 2).setUnlocalizedName("animalia:armors/olivine_leggings").setCreativeTab(tabArmors);
		olivineBoots = new ItemArmor(6003, OLIVINEARMOR, Constants.OLIVINE_ARMOR_RENDER, 3).setUnlocalizedName("animalia:armors/olivine_boots").setCreativeTab(tabArmors);

		extractorItem = new ItemReed(Config.extractorItemProp.getInt(), extractorOff).setUnlocalizedName("animalia:block_items/extractorItem").setCreativeTab(tabMachine);
	}

	private void initCreativeTabs()
	{
		tabBlock = new AnimaliaCreativeTabs(CreativeTabs.getNextID(), "animaliaBlocks").setIcon(fossilEP.blockID);
		tabMaterial = new AnimaliaCreativeTabs(CreativeTabs.getNextID(), "animaliaMaterials");
		tabTools = new AnimaliaCreativeTabs(CreativeTabs.getNextID(), "animaliaTools");
		tabWeapons = new AnimaliaCreativeTabs(CreativeTabs.getNextID(), "animaliaWeapons");
		tabArmors = new AnimaliaCreativeTabs(CreativeTabs.getNextID(), "animaliaArmors");
		tabMachine = new AnimaliaCreativeTabs(CreativeTabs.getNextID(), "animaliaMachines").setIcon(extractorItem.itemID);
		tabDeco = new AnimaliaCreativeTabs(CreativeTabs.getNextID(), "animaliaDecorations");
	}

	private void finishCreativeTabInit()
	{
		fossilEP.setCreativeTab(tabBlock);
		fossilLP.setCreativeTab(tabBlock);
		fossilMesozoic.setCreativeTab(tabBlock);

		crystal4DOre.setCreativeTab(tabBlock);

		crystal4D.setCreativeTab(tabMaterial);
		olivineGem.setCreativeTab(tabMaterial);

		olivinePickaxe.setCreativeTab(tabTools);
		olivineAxe.setCreativeTab(tabTools);
		olivineShovel.setCreativeTab(tabTools);
		olivineHoe.setCreativeTab(tabTools);

		olivineSword.setCreativeTab(tabWeapons);

		olivineHelmet.setCreativeTab(tabArmors);
		olivineChestplate.setCreativeTab(tabArmors);
		olivineLeggings.setCreativeTab(tabArmors);
		olivineBoots.setCreativeTab(tabArmors);

		extractorItem.setCreativeTab(tabMachine);
	}

	private void registerBlocks()
	{
		// Fossil Blocks
		this.registerBlock(fossilEP, "FossilEP");
		this.registerBlock(fossilLP, "FossilLP");
		this.registerBlock(fossilMesozoic, "FossilMesozoic");

		// Crystal Ore Blocks
		this.registerBlock(crystal4DOre, "CrystalOre");
		this.registerBlock(crystal4DOreGlowing, "CrystalOreGlowing");

		this.registerBlock(extractorOff, "ExtractorOff");
		this.registerBlock(extractorOn, "ExtractorOn");
	}

	private static void registerBlock(Block block, String name)
	{
		GameRegistry.registerBlock(block, ItemBlock.class, name, metadata.modId);
	}

	private void registerItems()
	{
		// Gems
		this.registerItem(crystal4D, "itemCrystal4D");
		this.registerItem(olivineGem, "itemOlivineGem");

		// Olivine Tools
		this.registerItem(olivineAxe, "itemOlivineAxe");
		this.registerItem(olivineHoe, "itemOlivineHoe");
		this.registerItem(olivinePickaxe, "itemOlivinePickaxe");
		this.registerItem(olivineShovel, "itemOlivineShovel");
		this.registerItem(olivineSword, "itemOlivineSword");

		// Olivine Armor
		this.registerItem(olivineHelmet, "itemOlivineHelmet");
		this.registerItem(olivineChestplate, "itemOlivineChestplate");
		this.registerItem(olivineLeggings, "itemOlivineLeggings");
		this.registerItem(olivineBoots, "itemOlivineBoots");
	}

	private static void registerItem(Item item, String name)
	{
		GameRegistry.registerItem(item, name, metadata.modId);
	}

	private void registerLocalizations()
	{
		// Block Localizations
		LanguageRegistry.addName(fossilEP, "Early Paleozoic Fossil");
		LanguageRegistry.addName(fossilLP, "Late Paleozoic Fossil");
		LanguageRegistry.addName(fossilMesozoic, "Mesozoic Fossil");

		LanguageRegistry.addName(crystal4DOre, "4D Crystal Ore");

		LanguageRegistry.addName(extractorOff, "Extractor");

		// Item Localizations
		LanguageRegistry.addName(crystal4D, "4D Crystal");

		LanguageRegistry.addName(olivineGem, "Olivine Gem");
		LanguageRegistry.addName(olivineAxe, "Olivine Axe");
		LanguageRegistry.addName(olivineHoe, "Olivine Hoe");
		LanguageRegistry.addName(olivinePickaxe, "Olivine Pickaxe");
		LanguageRegistry.addName(olivineShovel, "Olivine Shovel");
		LanguageRegistry.addName(olivineSword, "Olivine Sword");

		LanguageRegistry.addName(olivineHelmet, "Olivine Helmet");
		LanguageRegistry.addName(olivineChestplate, "Olivine Chestplate");
		LanguageRegistry.addName(olivineLeggings, "Olivine Leggings");
		LanguageRegistry.addName(olivineBoots, "Olivine Boots");

		// General Localizations
		LanguageRegistry.instance().addStringLocalization("itemGroup.animaliaBlocks", Language.ENGLISHUS.getLangCode(), "Animalia Blocks");
		LanguageRegistry.instance().addStringLocalization("itemGroup.animaliaMaterials", Language.ENGLISHUS.getLangCode(), "Animalia Materials");
		LanguageRegistry.instance().addStringLocalization("itemGroup.animaliaTools", Language.ENGLISHUS.getLangCode(), "Animalia Tools");
		LanguageRegistry.instance().addStringLocalization("itemGroup.animaliaWeapons", Language.ENGLISHUS.getLangCode(), "Animalia Weapons");
		LanguageRegistry.instance().addStringLocalization("itemGroup.animaliaArmors", Language.ENGLISHUS.getLangCode(), "Animalia Armors");
		LanguageRegistry.instance().addStringLocalization("itemGroup.animaliaMachines", Language.ENGLISHUS.getLangCode(), "Animalia Machines");
		LanguageRegistry.instance().addStringLocalization("itemGroup.animaliaDecorations", Language.ENGLISHUS.getLangCode(), "Animalia Decorations");
	}

	private void registerRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(olivineAxe), new Object[] { "XX ", "XS ", " S ", Character.valueOf('X'), olivineGem, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(olivineAxe), new Object[] { " XX", " SX", " S ", Character.valueOf('X'), olivineGem, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(olivineHoe), new Object[] { "XX", " S", " S", Character.valueOf('X'), olivineGem, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(olivineHoe), new Object[] { "XX", "S ", "S ", Character.valueOf('X'), olivineGem, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(olivinePickaxe), new Object[] { "XXX", " S ", " S ", Character.valueOf('X'), olivineGem, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(olivineShovel), new Object[] { "X", "S", "S", Character.valueOf('X'), olivineGem, Character.valueOf('S'), Item.stick });
		GameRegistry.addRecipe(new ItemStack(olivineSword), new Object[] { "X", "X", "S", Character.valueOf('X'), olivineGem, Character.valueOf('S'), Item.stick });
	}

	private void registerHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(fossilEP, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(fossilLP, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(fossilMesozoic, "pickaxe", 2);

		MinecraftForge.setBlockHarvestLevel(crystal4DOre, "pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(crystal4DOreGlowing, "pickaxe", 3);
	}

	private void registerTickHandlers()
	{
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		// TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
	}

	private void registerEventManager(EventManager eventManager)
	{
		if (eventManager instanceof ICraftingHandler)
			GameRegistry.registerCraftingHandler(eventManager);
		if (eventManager instanceof IFuelHandler)
			GameRegistry.registerFuelHandler(eventManager);
		if (eventManager instanceof IWorldGenerator)
			GameRegistry.registerWorldGenerator(eventManager);
		if (eventManager instanceof IPlayerTracker)
			GameRegistry.registerPlayerTracker(eventManager);
		if (eventManager instanceof IPickupNotifier)
			GameRegistry.registerPickupHandler(eventManager);
		if (eventManager.getClass().isAnnotationPresent(ForgeSubscribe.class))
			MinecraftForge.EVENT_BUS.register(eventManager);
	}

	public static boolean isNewestRecommendedBuild()
	{
		String currentRecBuild = getCurrentRecommendedBuild();
		String secDiv = "\\.";
		String[] strings = metadata.version.toString().split(secDiv);
		int[] currInstallVer = { Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]), Integer.parseInt(strings[3]) };
		int[] mostRecentVer = convertToIntArray(currentRecBuild.split(secDiv));
		boolean isMostRecentVer = isSameVersion(currInstallVer, mostRecentVer);
		return isMostRecentVer;
	}

	private static int[] convertToIntArray(String[] split)
	{
		int[] ints = new int[split.length];
		try
		{
			for (int index = 0; index < split.length; index++)
			{
				ints[index] = Integer.valueOf(split[index]);
			}
		}
		catch (NumberFormatException e)
		{
			ints = new int[] { 0 };
		}
		return ints;
	}

	private static boolean isSameVersion(int[] currInstallVer, int[] mostRecentVer)
	{
		for (int index = 0; index < currInstallVer.length; index++)
			if (!(currInstallVer[index] == mostRecentVer[index]))
				return false;
		return true;
	}

	public static String getCurrentRecommendedBuild()
	{
		StringBuilder buildableString = new StringBuilder();
		HttpURLConnection connection = null;
		try
		{
			connection = (HttpURLConnection) new URL("http://dl.dropbox.com/u/38453115/Animalia_Version.txt").openConnection();
			connection.connect();
			if (connection.getResponseCode() == 200)
			{
				BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
				while (bis.available() > 0)
				{
					buildableString.append(Character.valueOf((char) bis.read()));
				}
			}
			connection.disconnect();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e = new IOException(e.getLocalizedMessage() + " Unable to contact Update URL");
			e.printStackTrace();
		}
		finally
		{
			if (connection != null)
				connection.disconnect();
		}
		return buildableString.toString();
	}
}

package animalia.common.machine.extractor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

import com.google.common.collect.Maps;

public class ExtractorRecipes 
{	
	private static final ExtractorRecipes recipes = new ExtractorRecipes();

	private Map<Integer, ItemStack> extractingList = Maps.newHashMap();
	private Map<Integer, Float> experienceList = Maps.newHashMap();
	private HashMap<List<Integer>, ItemStack> metaExtractingList = Maps.newHashMap();
	private HashMap<List<Integer>, Float> metaExperience = Maps.newHashMap();
	
	
	public static final ExtractorRecipes extracting() 
	{
		return recipes;
	}

	private ExtractorRecipes() {}
	
	public void addRecipe(int itemID, ItemStack result, float experience)
	{
		this.extractingList.put(Integer.valueOf(itemID), result);
		this.experienceList.put(Integer.valueOf(itemID), experience);
	}
	
	public void addRecipe(int itemID, int metadata, ItemStack result, float experience)
	{
		this.metaExtractingList.put(Arrays.asList(itemID, metadata), result);
		this.metaExperience.put(Arrays.asList(itemID, metadata), experience);
	}
	
	public Map<Integer, ItemStack> getExtractingList()
	{
		return this.extractingList;
	}

	public Map<List<Integer>, ItemStack> getMetaExtractingList()
	{
		return this.metaExtractingList;
	}
	
	public Map<Integer, Float> getExperienceList()
	{
		return this.experienceList;
	}
	
	public Map<List<Integer>, Float> getMetaExperienceList()
	{
		return this.metaExperience;
	}
	
	public ItemStack getExtractingResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metaExtractingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)extractingList.get(Integer.valueOf(item.itemID));
    }

    public float getExperience(ItemStack item)
    {
        if (item == null || item.getItem() == null)
        {
            return 0;
        }
        float ret = item.getItem().getSmeltingExperience(item);
        if (ret < 0 && metaExperience.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
        {
            ret = metaExperience.get(Arrays.asList(item.itemID, item.getItemDamage()));
        }
        if (ret < 0 && experienceList.containsKey(item.itemID))
        {
            ret = ((Float)experienceList.get(item.itemID)).floatValue();
        }
        return (ret < 0 ? 0 : ret);
    }
}

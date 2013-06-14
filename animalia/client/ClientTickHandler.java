package animalia.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler 
{
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) 
	{
		if(type.equals(EnumSet.of(TickType.RENDER)))
			onRenderTick();
		else
		if(type.equals(EnumSet.of(TickType.CLIENT)))
		{
			Minecraft mc = Minecraft.getMinecraft();
			GuiScreen gui = mc.currentScreen;
			if(gui == null)
				onTickInGame(mc);
			else
				onTickInGui(mc, gui);
		}
	}

	private void onTickInGui(Minecraft mc, GuiScreen gui) 
	{
		
	}

	private void onTickInGame(Minecraft mc)
	{
		
	}

	private void onRenderTick() 
	{
		
	}

	@Override
	public EnumSet<TickType> ticks() 
	{	
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

	@Override
	public String getLabel() 
	{
		return "ClientTickHandler";
	}
}

package animalia.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler 
{
	public enum PacketType
	{
		UNSPECIFIED, 
		TILEENTITY;
		
		public static PacketType getPacketType(int typeOrdinal)
		{
			return PacketType.values()[typeOrdinal];
		}
	}
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
	{
		ByteArrayDataInput bais = ByteStreams.newDataInput(packet.data);
		PacketType packetType = PacketType.getPacketType(bais.readInt());
		
		int xCoord;
		int yCoord;
		int zCoord;
		
		if(packetType == PacketType.TILEENTITY)
		{
			xCoord = bais.readInt();
			yCoord = bais.readInt();
			zCoord = bais.readInt();
			
			World world = ((EntityPlayer)player).worldObj;
			
			if(world.getBlockTileEntity(xCoord, yCoord, zCoord) != null)
			{
				TileEntity te = world.getBlockTileEntity(xCoord, yCoord, zCoord);
				if(te instanceof IHandlePacket)
					((IHandlePacket)te).handlePacketData(manager, packet, (EntityPlayer)player);
				else
					this.handlePacketData(manager, packet, (EntityPlayer)player);
			}
		}
		else
			this.handlePacketData(manager, packet, (EntityPlayer)player);
	}

	private void handlePacketData(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player) 
	{
		
	}

	public static void sendTileEntityPacket(TileEntity tileEntity, String channel, Object... objects) 
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		try 
		{
			dos.writeInt(PacketType.TILEENTITY.ordinal());
			
			dos.writeInt(tileEntity.xCoord);
			dos.writeInt(tileEntity.yCoord);
			dos.writeInt(tileEntity.zCoord);
			
			for(Object object : objects)
			{
				if(object instanceof Integer)
				{
					dos.writeInt((Integer)object);
				}else
				if(object instanceof Double)
				{
					dos.writeDouble((Double)object);
				}else
				if(object instanceof Float)
				{
					dos.writeFloat((Float)object);
				}else
				if(object instanceof String)
				{
					dos.writeUTF((String)object);
				}
			}
			dos.flush();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = channel;
		packet.data = baos.toByteArray();
		packet.length = packet.data.length;
		PacketDispatcher.sendPacketToAllAround(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, 30, tileEntity.worldObj.provider.dimensionId, packet);
	}
}

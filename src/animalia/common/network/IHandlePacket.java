package animalia.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public interface IHandlePacket 
{
	public void handlePacketData(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player);
}

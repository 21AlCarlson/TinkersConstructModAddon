package henryandalex.tinkersaddonmod.Network;

import henryandalex.tinkersaddonmod.utils.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
	private static  SimpleNetworkWrapper INSTANCE;
	
	public static void init() {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	
		INSTANCE.registerMessage(MessageBeam.class, MessageBeam.class, 0, Side.SERVER);
	}
	
	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}
	
	
	
	
}

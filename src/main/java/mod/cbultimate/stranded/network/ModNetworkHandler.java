package mod.cbultimate.stranded.network;//Created by CBU on 18/1/2017.

import mod.cbultimate.stranded.StrandedMod;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModNetworkHandler {
    private static SimpleNetworkWrapper network;
    private static int discriminant;

    public static void init(){
        network = NetworkRegistry.INSTANCE.newSimpleChannel(StrandedMod.MODID);
        network.registerMessage(PacketToolCupboards.class, BlockPosPacket.class, discriminant++, Side.CLIENT);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player){
        network.sendTo(message, player);
    }
}

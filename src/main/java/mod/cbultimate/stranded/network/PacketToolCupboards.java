package mod.cbultimate.stranded.network;//Created by CBU on 18/1/2017.

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketToolCupboards implements IMessageHandler<BlockPosPacket, IMessage> {
    @Override
    public IMessage onMessage(BlockPosPacket message, MessageContext ctx) {
        return null;
    }
}

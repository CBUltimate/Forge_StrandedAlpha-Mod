package mod.cbultimate.stranded.network;//Created by CBU on 18/1/2017.

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public abstract class BlockPosPacket<REQ extends IMessage> extends AbstractPacket<REQ> {
    protected BlockPos pos;

    public BlockPosPacket(){}

    public BlockPosPacket(BlockPos blockpos){
        this.pos = blockpos;
    }

    @Override
    public void toBytes(ByteBuf buf){
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    @Override
    public void fromBytes(ByteBuf buf){
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();

        pos = new BlockPos(x, y, z);
    }

    public NetworkRegistry.TargetPoint getTargetPoint(World world){
        return getTargetPoint(world, 64);
    }

    public NetworkRegistry.TargetPoint getTargetPoint(World world, double updateDistance){
        return new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), updateDistance);
    }
}

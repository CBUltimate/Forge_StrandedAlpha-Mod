package mod.cbultimate.stranded.proxy;

import mod.cbultimate.stranded.item.ModItems;
import mod.cbultimate.stranded.block.ModBlocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by CBU on 13/1/2017.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
    }

    @Override
    public void init(FMLInitializationEvent event) {
        ModItems.registerRenders();
        ModBlocks.registerRenders();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }
}

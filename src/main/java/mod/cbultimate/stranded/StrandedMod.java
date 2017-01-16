package mod.cbultimate.stranded;

import mod.cbultimate.stranded.block.ModBlocks;
import mod.cbultimate.stranded.crafting.ModCrafting;
import mod.cbultimate.stranded.inv_tab.InvCreativeTab;
import mod.cbultimate.stranded.item.ModItems;
import mod.cbultimate.stranded.tileentity.ModTileEntities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import mod.cbultimate.stranded.proxy.CommonProxy;

/**
 * Created by CBU on 13/1/2017.
 */

@Mod(modid = StrandedMod.MODID, version = StrandedMod.VERSION, name = StrandedMod.MODNAME)
public class StrandedMod {
    public static final String MODID = "cbu_strandedmod";
    public static final String VERSION = "1.0";
    public static final String MODNAME = "Stranded Mod";
    private static final String LOGPREFIX = "["+MODNAME+"] >> ";

    @SidedProxy(clientSide = "ClientProxy", serverSide = "CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static StrandedMod instance;

    public static InvCreativeTab invCreativeTab;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        invCreativeTab = new InvCreativeTab(CreativeTabs.getNextID(), "stranded_mod");
        ModBlocks.preInit();
        ModItems.preInit();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        System.out.println(LOGPREFIX+ " Loading mod version: "+VERSION);
        System.out.println(LOGPREFIX+ " Mod made by CBUltimate.");
        ModCrafting.Init();
        ModBlocks.Init();
        ModTileEntities.Init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
        proxy.postInit(event);
    }

}

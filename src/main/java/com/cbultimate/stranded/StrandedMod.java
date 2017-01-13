package com.cbultimate.stranded;

import com.cbultimate.stranded.inv_tab.InvCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.cbultimate.stranded.proxy.CommonProxy;

/**
 * Created by CBU on 13/1/2017.
 */

@Mod(modid = StrandedMod.MODID, version = StrandedMod.VERSION, name = StrandedMod.MODNAME)
public class StrandedMod {
    public static final String MODID = "cbu_strandedmod";
    public static final String VERSION = "1.0";
    public static final String MODNAME = "Stranded Mod";
    private static final String LOGPREFIX = "["+MODNAME+"] >> ";

    @SidedProxy(clientSide = "com.cbultimate.stranded.proxy.ClientProxy", serverSide = "com.cbultimate.stranded.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static StrandedMod instance;

    public static InvCreativeTab inCreativeTab;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        inCreativeTab = new InvCreativeTab(CreativeTabs.getNextID(), "stranded mod");
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        System.out.println(LOGPREFIX+ " Loading mod version:"+VERSION);
        System.out.println(LOGPREFIX+ " Mod made by CBUltimate.");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}

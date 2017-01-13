package com.cbultimate.stranded;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * Created by CBU on 13/1/2017.
 */

@Mod(modid = StrandedMod.MODID, version = StrandedMod.VERSION, name = StrandedMod.MODNAME)
public class StrandedMod {
    public static final String MODID = "cbu_strandedmod";
    public static final String VERSION = "1.0";
    public static final String MODNAME = "Stranded Mod";
    private static final String LOGPREFIX = "["+MODNAME+"] >> ";


    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        System.out.println(LOGPREFIX+ " Loading mod version:"+VERSION);
        System.out.println(LOGPREFIX+ " Mod made by CBUltimate.");

    }
}

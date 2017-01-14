package com.cbultimate.stranded.item;

import net.minecraft.item.Item;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import com.cbultimate.stranded.StrandedMod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

/**
 * Created by CBU on 14/1/2017.
 */
public class ModItems {

    public static Item reinforcedIronBar;

    public static void preInit() {

        reinforcedIronBar = new ItemReinforcedIronBar("reinforced_iron_bar");
        registerItems();
    }

    public static void registerItems() {
        GameRegistry.register(reinforcedIronBar, new ResourceLocation(StrandedMod.MODID, "reinforced_iron_bar"));
    }

    public static void registerRenders(){
        registerRender(reinforcedIronBar);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(StrandedMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

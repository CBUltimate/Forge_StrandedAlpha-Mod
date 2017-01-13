package com.cbultimate.stranded.item;

import com.cbultimate.stranded.StrandedMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by CBU on 14/1/2017.
 */
public class ModItems {

    public static Item reinforcedIronItem;

    public static void preInit() {

        registerItems();
    }

    public static void registerItems() {
        GameRegistry.register(reinforcedIronItem, new ResourceLocation(StrandedMod.MODID, "reinforced_iron"));
    }

    public static void registerRenders(){
        registerRender(reinforcedIronItem);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(StrandedMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

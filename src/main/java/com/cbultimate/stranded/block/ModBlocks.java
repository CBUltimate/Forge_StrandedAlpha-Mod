package com.cbultimate.stranded.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.client.Minecraft;
import com.cbultimate.stranded.StrandedMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

/**
 * Created by CBU on 14/1/2017.
 */
public class ModBlocks {

    public static Block reinforcedIronBlock;

    public static void preInit() {
        reinforcedIronBlock = new Block_ReinforcedIron(Material.IRON, "reinforced_iron_block");
        registerBlocks();
    }

    public static void registerBlocks() {
        registerBlock(reinforcedIronBlock, "reinforced_iron_block");
    }

    public static void registerBlock(Block block, String name){
        GameRegistry.register(block, new ResourceLocation(StrandedMod.MODID, name));
        GameRegistry.register(new ItemBlock(block), new ResourceLocation(StrandedMod.MODID, name));
    }

    public static void registerRenders() {
        registerRender(reinforcedIronBlock);
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0 , new ModelResourceLocation(StrandedMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

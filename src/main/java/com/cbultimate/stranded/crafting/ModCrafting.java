package com.cbultimate.stranded.crafting;

import com.cbultimate.stranded.block.ModBlocks;
import com.cbultimate.stranded.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
/**
 * Created by CBU on 14/1/2017.
 */
public class ModCrafting {

    public static void Init() {
        GameRegistry.addSmelting(Blocks.DIRT, new ItemStack(ModItems.reinforcedDirt), 1.0F);
        GameRegistry.addSmelting(Items.IRON_INGOT, new ItemStack(ModItems.reinforcedIronBar), 1.0F);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.reinforcedDirtBlock, 4), new Object[] {"##", "##", '#', ModItems.reinforcedDirt});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.reinforcedIronBlock, 4), new Object[] {"##", "##", '#', ModItems.reinforcedIronBar});
    }
}

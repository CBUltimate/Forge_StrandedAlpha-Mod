package mod.cbultimate.stranded.block;

import mod.cbultimate.stranded.StrandedMod;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

/**
 * Created by CBU on 14/1/2017.
 */
public class ModBlocks {

    public static Block reinforcedIronBlock;
    public static Block reinforcedDirtBlock;
    public static Block woodenToolCupboard;

    public static void preInit() {
        reinforcedIronBlock = new BlockReinforcedIron(Material.IRON, "reinforced_iron_block");
        reinforcedDirtBlock = new BlockReinforcedDirt(Material.ROCK, "mud_brick_block");
        woodenToolCupboard = new BlockWoodenToolCupboard(Material.WOOD, "wooden_toolcupboard");
        registerBlocks();
    }


    public static void Init() {
        float woodenDoorHardness = 10.0F;
        float ironDoorHardness = 180.0F;

        Blocks.OAK_DOOR.setHardness(woodenDoorHardness);
        Blocks.ACACIA_DOOR.setHardness(woodenDoorHardness);
        Blocks.DARK_OAK_DOOR.setHardness(woodenDoorHardness);
        Blocks.BIRCH_DOOR.setHardness(woodenDoorHardness);
        Blocks.JUNGLE_DOOR.setHardness(woodenDoorHardness);
        Blocks.SPRUCE_DOOR.setHardness(woodenDoorHardness);
        Blocks.TRAPDOOR.setHardness(woodenDoorHardness);

        Blocks.IRON_DOOR.setHardness(ironDoorHardness);
        Blocks.IRON_TRAPDOOR.setHardness(ironDoorHardness);

        Blocks.PLANKS.setHardness(10.0F);

        Blocks.DIAMOND_BLOCK.setHardness(50.0F);
        Blocks.GOLD_BLOCK.setHardness(25.0F);
        Blocks.STONEBRICK.setHardness(75.0F);
        Blocks.BRICK_BLOCK.setHardness(100.0F);
        Blocks.IRON_BLOCK.setHardness(150.0F);

        reinforcedDirtBlock.setHardness(50.0F);
        reinforcedIronBlock.setHardness(200.0F);

        reinforcedDirtBlock.setResistance(30.0F);
        reinforcedIronBlock.setResistance(50.0F);
    }

    public static void registerBlocks() {
        registerBlock(reinforcedIronBlock, "reinforced_iron_block");
        registerBlock(reinforcedDirtBlock, "mud_brick_block");
        registerBlock(woodenToolCupboard, "wooden_toolcupboard");
    }

    public static void registerBlock(Block block, String name){
        GameRegistry.register(block, new ResourceLocation(StrandedMod.MODID, name));
        GameRegistry.register(new ItemBlock(block), new ResourceLocation(StrandedMod.MODID, name));
    }

    public static void registerRenders() {
        registerRender(reinforcedIronBlock);
        registerRender(reinforcedDirtBlock);
        registerRender(woodenToolCupboard);
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0 , new ModelResourceLocation(StrandedMod.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}

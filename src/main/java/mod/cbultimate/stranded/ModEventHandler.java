package mod.cbultimate.stranded;

import mod.cbultimate.stranded.block.ModBlocks;
import mod.cbultimate.stranded.tileentity.TileEntityToolCupboard;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

//Created by CBU on 14/1/2017.

public class ModEventHandler {
    private final String cupboard_dataIdentifier = "ToolCupboards";
    private static ArrayList<Block> protectedBlockList = new ArrayList<Block>();
    private static ArrayList<Item> blacklistedItems = new ArrayList<Item>();

    public static void Init(){
        protectedBlockList.add(Blocks.OAK_DOOR);
        protectedBlockList.add(Blocks.IRON_DOOR);
        protectedBlockList.add(Blocks.ACACIA_DOOR);
        protectedBlockList.add(Blocks.BIRCH_DOOR);
        protectedBlockList.add(Blocks.DARK_OAK_DOOR);
        protectedBlockList.add(Blocks.JUNGLE_DOOR);
        protectedBlockList.add(Blocks.SPRUCE_DOOR);
        protectedBlockList.add(Blocks.TRAPDOOR);
        protectedBlockList.add(Blocks.IRON_TRAPDOOR);
        protectedBlockList.add(Blocks.STONE_BUTTON);
        protectedBlockList.add(Blocks.WOODEN_BUTTON);
        protectedBlockList.add(Blocks.LEVER);

        blacklistedItems.add(Items.LAVA_BUCKET);
        blacklistedItems.add(Items.WATER_BUCKET);
        blacklistedItems.add(new ItemStack(Blocks.CHEST).getItem());
        blacklistedItems.add(new ItemStack(Blocks.TRAPPED_CHEST).getItem());
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event){
        World world = event.getWorld();
        ModWorldSavedData modWorldSavedData = (ModWorldSavedData) world.getPerWorldStorage().getOrLoadData(ModWorldSavedData.class, cupboard_dataIdentifier);

        if (modWorldSavedData == null) {
            modWorldSavedData = new ModWorldSavedData(cupboard_dataIdentifier);
        }

        for (int i=0; i < modWorldSavedData.ToolCupboards.size(); i ++){
            BlockPos toolPos = modWorldSavedData.ToolCupboards.get(i);
            if (world.isAirBlock(toolPos) || world.getBlockState(toolPos).getBlock() != ModBlocks.woodenToolCupboard){
                System.out.println(StrandedMod.LOGPREFIX + "Missing tool cupboard.");
                modWorldSavedData.ToolCupboards.remove(i);
            }
        }

        modWorldSavedData.markDirty();
    }

    @SubscribeEvent
    public void blockPlacedEvent(BlockEvent.PlaceEvent event){
        World world = event.getWorld();
        ModWorldSavedData modWorldSavedData = (ModWorldSavedData) world.getPerWorldStorage().getOrLoadData(ModWorldSavedData.class, cupboard_dataIdentifier);

        if (modWorldSavedData == null) {
            modWorldSavedData = new ModWorldSavedData(cupboard_dataIdentifier);
        }

        boolean cancelPlacement = false;

        for (int i=0; i < modWorldSavedData.ToolCupboards.size(); i ++){
            BlockPos currentPosition = modWorldSavedData.ToolCupboards.get(i);
            if (event.getPos().getDistance(currentPosition.getX(), currentPosition.getY(), currentPosition.getZ()) < 16){
                if (event.getPlacedBlock().getBlock().equals(ModBlocks.woodenToolCupboard)) {
                    cancelPlacement = true;
                    event.setCanceled(true);
                    event.getPlayer().addChatMessage(new TextComponentString("There is already a tool cupboard in this region."));
                    break;
                } else {
                    TileEntity cupboardEntity = world.getTileEntity(currentPosition);

                    if (cupboardEntity instanceof TileEntityToolCupboard){
                        boolean authorized = ((TileEntityToolCupboard) cupboardEntity).checkAuthorized(event.getPlayer().getName());
                        if (!authorized){
                            cancelPlacement = true;
                            event.setResult(Event.Result.DENY);
                            event.setCanceled(true);
                            event.getPlayer().addChatMessage(new TextComponentString("You are not authorized to build here."));
                        }
                    }
                }
            }
        }

        if (event.getPlacedBlock().getBlock().equals(ModBlocks.woodenToolCupboard)){
            if (!cancelPlacement){
                modWorldSavedData.ToolCupboards.add(event.getPos());
                world.getPerWorldStorage().setData(cupboard_dataIdentifier, modWorldSavedData);
                modWorldSavedData.markDirty();
            }
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event){
        World world = event.getWorld();
        ModWorldSavedData modWorldSavedData = (ModWorldSavedData) world.getPerWorldStorage().getOrLoadData(ModWorldSavedData.class, cupboard_dataIdentifier);

        if (modWorldSavedData == null) {
            modWorldSavedData = new ModWorldSavedData(cupboard_dataIdentifier);
        }

        for(int i=0; i<modWorldSavedData.ToolCupboards.size(); i++){
            if (event.getState().getBlock().equals(ModBlocks.woodenToolCupboard)){
                BlockPos currentCupboard = modWorldSavedData.ToolCupboards.get(i);
                if (event.getPos().equals(currentCupboard)){
                    modWorldSavedData.ToolCupboards.remove(i);
                    world.getPerWorldStorage().setData(cupboard_dataIdentifier, modWorldSavedData);
                    modWorldSavedData.markDirty();
                    break;
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event){
        String targetName = "";

        for (int p=0; p<protectedBlockList.size(); p++){
            if (event.getWorld().getBlockState(event.getPos()).getBlock().equals(protectedBlockList.get(p))){
                targetName = protectedBlockList.get(p).getLocalizedName();
                break;
            }
        }

        for (int q=0; q<blacklistedItems.size(); q++){
            if (event.getItemStack().getItem().equals(blacklistedItems.get(q))){
                targetName = event.getItemStack().getDisplayName();
                break;
            }
        }

        if (!targetName.equals("")){
            World world = event.getWorld();
            ModWorldSavedData modWorldSavedData = (ModWorldSavedData) world.getPerWorldStorage().getOrLoadData(ModWorldSavedData.class, cupboard_dataIdentifier);

            if (modWorldSavedData == null) {
                modWorldSavedData = new ModWorldSavedData(cupboard_dataIdentifier);
            }

            for (int i=0; i < modWorldSavedData.ToolCupboards.size(); i ++){
                BlockPos currentPosition = modWorldSavedData.ToolCupboards.get(i);
                if (event.getPos().getDistance(currentPosition.getX(), currentPosition.getY(), currentPosition.getZ()) < 16){
                    TileEntity cupboardEntity = world.getTileEntity(currentPosition);

                    if (cupboardEntity instanceof TileEntityToolCupboard){
                        if (!((TileEntityToolCupboard) cupboardEntity).checkAuthorized(event.getEntityPlayer().getName()) ){
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        String targetName = "";

        for (int p = 0; p < protectedBlockList.size(); p++) {
            if (event.getWorld().getBlockState(event.getPos()).getBlock().equals(protectedBlockList.get(p))) {
                targetName = protectedBlockList.get(p).getLocalizedName();
                break;
            }
        }

        for (int q = 0; q < blacklistedItems.size(); q++) {
            if (event.getItemStack().getItem().equals(blacklistedItems.get(q))) {
                targetName = event.getItemStack().getDisplayName();
                break;
            }
        }

        if (!targetName.equals("")) {
            World world = event.getWorld();
            ModWorldSavedData modWorldSavedData = (ModWorldSavedData) world.getPerWorldStorage().getOrLoadData(ModWorldSavedData.class, cupboard_dataIdentifier);

            if (modWorldSavedData == null) {
                modWorldSavedData = new ModWorldSavedData(cupboard_dataIdentifier);
            }

            for (int i = 0; i < modWorldSavedData.ToolCupboards.size(); i++) {
                BlockPos currentPosition = modWorldSavedData.ToolCupboards.get(i);
                if (event.getPos().getDistance(currentPosition.getX(), currentPosition.getY(), currentPosition.getZ()) < 16) {
                    TileEntity cupboardEntity = world.getTileEntity(currentPosition);

                    if (cupboardEntity instanceof TileEntityToolCupboard) {
                        if (!((TileEntityToolCupboard) cupboardEntity).checkAuthorized(event.getEntityPlayer().getName())) {
                            event.setCanceled(true);
                            event.getEntityPlayer().addChatMessage(new TextComponentString("You are not authorized to use " + targetName));
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onHurtEvent(LivingHurtEvent event){
        if (event.getSource() == DamageSource.fall){
            event.setAmount(event.getAmount()/1.5F);
        }
    }
}

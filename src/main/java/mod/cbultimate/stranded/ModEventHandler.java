package mod.cbultimate.stranded;

import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

//Created by CBU on 14/1/2017.

public class ModEventHandler {

    @SubscribeEvent
    public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
        System.out.println(event.player.getDisplayName() + " has logged in the server.");
    }

    private final String cupboard_dataIdentifier = "ToolCupboards";

    @SubscribeEvent
    public void blockPlacedEvent(BlockEvent.PlaceEvent event){
        World world = event.getWorld();
        ModWorldSavedData modWorldSavedData = (ModWorldSavedData) world.getPerWorldStorage().getOrLoadData(ModWorldSavedData.class, cupboard_dataIdentifier);

        if (modWorldSavedData == null) {
            modWorldSavedData = new ModWorldSavedData(cupboard_dataIdentifier);
        }

        if (event.getPlacedBlock().getBlock().getUnlocalizedName().equals("tile.wooden_toolcupboard")){
            ModWorldSavedData.ToolCupboards.add(new Vector3(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ()));
            world.getPerWorldStorage().setData(cupboard_dataIdentifier, modWorldSavedData);
            modWorldSavedData.markDirty();
            System.out.println("Tool Cupboards: " + ModWorldSavedData.ToolCupboards.size());
        }
    }

    @SubscribeEvent
    public void blockBreakEvent(BlockEvent.BreakEvent event){
        World world = event.getWorld();
        ModWorldSavedData modWorldSavedData = (ModWorldSavedData) world.getPerWorldStorage().getOrLoadData(ModWorldSavedData.class, cupboard_dataIdentifier);

        if (modWorldSavedData == null) {
            modWorldSavedData = new ModWorldSavedData(cupboard_dataIdentifier);
        }

        if (event.getState().getBlock().getUnlocalizedName().equals("tile.wooden_toolcupboard")){
            for(int i=0; i<ModWorldSavedData.ToolCupboards.size(); i++){
                Vector3 currentCupboard = ModWorldSavedData.ToolCupboards.get(i);
                if (event.getPos().getX() == currentCupboard.x && event.getPos().getY() == currentCupboard.y && event.getPos().getZ() == currentCupboard.z){
                    System.out.println("Cupboard removed.");
                    ModWorldSavedData.ToolCupboards.remove(i);
                    world.getPerWorldStorage().setData(cupboard_dataIdentifier, modWorldSavedData);
                    modWorldSavedData.markDirty();
                    break;
                }
            }
        }
    }
}

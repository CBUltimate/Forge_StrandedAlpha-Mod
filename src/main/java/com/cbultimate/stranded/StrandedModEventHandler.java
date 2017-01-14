package com.cbultimate.stranded;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by CBU on 14/1/2017.
 * file:///C:/Users/CBU/Downloads/forgeevents.html
 */
public class StrandedModEventHandler {

    @SubscribeEvent
    public void playerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
        System.out.println(event.player.getDisplayName() + " has joint the server.");
    }
}

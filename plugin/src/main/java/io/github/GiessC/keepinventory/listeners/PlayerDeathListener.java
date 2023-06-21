package io.github.GiessC.keepinventory.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private List<String> allowedPlayers;
    
    public PlayerDeathListener(List<String> allowedPlayers) {
        this.allowedPlayers = allowedPlayers;
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player target = event.getEntity();
        if (!allowedPlayers.contains(target.getName()))
            return;
            
        // Sets keep inventory and level to true
        event.setKeepInventory(true);
        event.setKeepLevel(true);
            
        // Remove dropped items and XP
        event.getDrops().clear();
        event.setDroppedExp(0);
    }
}

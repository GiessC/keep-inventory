package io.github.GiessC.keepinventory;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.GiessC.keepinventory.commands.CommandAddPlayer;
import io.github.GiessC.keepinventory.listeners.PlayerDeathListener;

public class Plugin extends JavaPlugin {    
    @Override
    public void onEnable() {        
        saveDefaultConfig();
        
        List<String> allowedPlayers = getAllowedPlayers();
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(allowedPlayers), this);

        this.getCommand("addPlayer").setExecutor(new CommandAddPlayer(this));
    }

    public List<String> getAllowedPlayers() {
        return this.getConfig().getStringList("allowedPlayers");
    }

    public void addAllowedPlayer(String username) {
        List<String> allowedPlayers = getAllowedPlayers();
        allowedPlayers.add(username);
        this.getConfig().set("allowedPlayers", allowedPlayers);
        this.saveConfig();
    }
}
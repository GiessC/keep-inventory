package io.github.GiessC.keepinventory.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.GiessC.keepinventory.Plugin;
import net.md_5.bungee.api.ChatColor;

public class CommandAddPlayer implements CommandExecutor {
    private Plugin plugin;
    
    public CommandAddPlayer(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && args.length == 1) {
            Player player = (Player) sender;
            if (!player.isOp()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }

            String targetPlayerUsername = args[0];
            if (plugin.getAllowedPlayers().contains(targetPlayerUsername)) {
                sender.sendMessage(ChatColor.RED + "Player " + targetPlayerUsername + " is already allowed to keep their inventory and XP.");
                return true;
            }

            plugin.addAllowedPlayer(targetPlayerUsername);
            sender.sendMessage(ChatColor.GREEN + "Player " + targetPlayerUsername + " will now keep their inventory and XP when they die.");
            return true;
        }
        
        return false;
    }
}

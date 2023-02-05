package me.noob.simplestarterkits;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getPlayer;

public class SimpleStarterKitsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*subcommands:
        save <player>, saves the player's inventory as the starter kit,
        give <player>, gives the player the starter kit,
        reload, reloads the config files,
        help, tells you this info,
        (no subcommand)/about; tells you about this plugin (creator, version, etc.)*/

        boolean success = true;
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "give" -> giveKit(sender, args[1]);
                case "save" -> saveKit(sender, args[1]);
            }
        }
        return success;
    }

    private void giveKit(@NotNull CommandSender sender, String playerName) {
        if (!sender.hasPermission("kits.give")) {
            sender.sendMessage("§c§lYou do not have permission to use this subcommand!");//place in config; red, bold
            return;
        }

        Player player = getPlayer(playerName);
        if (player == null) {
            sender.sendMessage("§cCould not find player '" + playerName + "'.");//red
            return;
        }

        SimpleStarterKits.giveKit(player);
    }

    private void saveKit(CommandSender sender, String playerName) {
        if (!sender.hasPermission("kits.save")) {
            sender.sendMessage("§c§lYou do not have permission to use this subcommand!");//place in config
            return;
        }

        Player player = getPlayer(playerName);
        if (player == null) {
            sender.sendMessage("§cCould not find player '" + playerName + "'.");//red
            return;
        }

        SimpleStarterKits.saveKit(player);
    }
}


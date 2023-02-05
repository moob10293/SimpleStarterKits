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
        savekit <player>, saves the player's inventory as the starter kit,
        givekit <player>, gives the player the starter kit,
        reload, reloads the config files (saves changes made to them, if you decided to edit them in the files instead of using savekit for whatever reason),
        help, tells you this info,
        (no subcommand)/about; tells you about this plugin (creator, version, etc.)*/

        boolean success = true;
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "givekit" -> giveKit(sender, args[1]);
                case "savekit" -> saveKit(sender, args[1]);
                case "reload" -> saveKit(sender, args[1]);
                case "help" -> saveKit(sender, args[1]);
                case default -> success=false;
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

    private void reload(CommandSender sender) {
        if (!sender.hasPermission("kits.reload")) {
            sender.sendMessage("§c§lYou do not have permission to use this subcommand!");//place in config
            return;
        }
        SimpleStarterKits.reload();
    }

    private void help(CommandSender sender) {
        sender.sendMessage("§c§lYou do not have permission to use this subcommand!");//place in config
        sender.sendMessage("§cCould not find player.");
    }
}


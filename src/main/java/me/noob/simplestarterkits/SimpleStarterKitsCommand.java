package me.noob.simplestarterkits;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.Bukkit.getPlayer;

public class SimpleStarterKitsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*subcommands:
        savekit <player> [kit]; saves the player's inventory as the kit specified/the default kit,
        givekit <player> [kit], gives the player the kit for the kit specified/the default kit,
        reload [config|kits], reloads configuration/kits (both if not specified),
        help, returns this info,
        (no subcommand)/about; tells you about this plugin*/

        boolean success=false;
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "savekit" -> success = saveKit(sender, args);
                case "givekit" -> success = giveKit(sender, args);
            }
        }
        return success;
    }

    private boolean giveKit(@NotNull CommandSender sender,String[] args) {
        if (!sender.hasPermission("kits.give")) {
            sender.sendMessage("You do not have permission to use this command.");//place in config
            return false;
        }

        Object[] objects = getPlayerAndKit(sender,args);
        if (objects == null) return false;
        String kit=(String) objects[0];
        Player player=(Player) objects[1];
        if (player == null) return false;

        return SimpleStarterKits.giveKit(player, "starter");
    }

    private boolean saveKit(@NotNull CommandSender sender,String[] args) {
        if (!sender.hasPermission("kits.save")) {
            sender.sendMessage("You do not have permission to use this command.");//place in config
            return false;
        }

        Object[] objects = getPlayerAndKit(sender,args);
        if (objects == null) return false;
        String kit=(String) objects[0];
        Player player=(Player) objects[1];
        if (player == null) return false;

        SimpleStarterKits.saveKit(player,kit);
        return true;
    }

    private Object @Nullable [] getPlayerAndKit(CommandSender sender, String @NotNull [] args){
        String kit;
        Player player = getPlayerFromArg(sender, args[2]);
        switch (args.length) {
            case 2 ->//just player
                    kit = "starter";
            case 3 -> kit = args[2];
            case default -> {
                sender.sendMessage("Wrong number of arguments!");
                return null;
            }
        }
        return new Object[]{kit, player};

    }

    private Player getPlayerFromArg(CommandSender sender, @NotNull String playerArg) {
        if (playerArg.equals("@s")) {
            return castToPlayer(sender);
        } else {
            return getPlayer(playerArg);
        }

    }

    private @Nullable Player castToPlayer(CommandSender sender) {
        if (sender instanceof Player){
            return (Player) sender;
        } else{
            sender.sendMessage("Only a player can use this command!");
            return null;

        }
    }
}


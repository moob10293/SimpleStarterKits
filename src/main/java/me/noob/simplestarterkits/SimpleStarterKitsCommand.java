package me.noob.simplestarterkits;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public class SimpleStarterKitsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*uses: savekit [(optional)world], which saves your inventory as the starter kit for the world specified or the one you are in if there is none,
        givekit [player] [(optional)world], which gives you the kit for the world specified or the one you are in if there is none,
        reload [config|kits], which reloads the config/kits (NOTE: you do NOT have to use this if you edited the kits using a command,
        help, which tells you this info, and about, which tells you about this plugin*/
        Logger logger = SimpleStarterKits.getInstance().getLogger();
        if (args.length > 0) {
            if (sender.hasPermission("simplestarterkits.command")) {
                if (args[0].equalsIgnoreCase("savekit")) {
                    Player player = castToPlayer(sender, logger);
                    if (player == null) return false;
                    String kit;

                    if (args.length == 1) {//just savekit
                        kit = "starter";
                    } else if (args.length == 2) {
                        kit = args[1];
                    } else {
                        sender.sendMessage("Wrong number of arguments!");
                        return false;
                    }
                    SimpleStarterKits.getInstance().saveKit(player,kit);
                } else if (args[0].equalsIgnoreCase("givekit")) {

                    Player player = castToPlayer(sender, logger);
                    if (player == null) return false;
                    SimpleStarterKits.getInstance().giveKit(player, "starter");
                }
            }else {
                sender.sendMessage("You do not have permission to use this command.");//place in config
                return false;
            }
        }
        return true;
    }


    @Nullable
    private Player castToPlayer(CommandSender sender, Logger logger) {
        World world;
        Player player;

        try {
            player = (Player) sender;
        } catch (ClassCastException e) {
            logger.info("Only a player can use this command!");
            sender.sendMessage("Only a player can use this command!");
            e.printStackTrace();
            return null;
        }
        return player;
    }
}


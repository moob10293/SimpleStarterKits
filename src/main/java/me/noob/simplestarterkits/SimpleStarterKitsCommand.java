package me.noob.simplestarterkits;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

import static me.noob.simplestarterkits.SimpleStarterKits.giveKit;

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
                    Player player = getPlayer(sender, logger);
                    if (player == null) return false;
                    World world;

                    if (args.length == 2) {//args.length isn't args[x]
                        world = player.getWorld();
                    } else if (args.length == 3) {
                        world = sender.getServer().getWorld(args[2]);
                    } else {

                        sender.sendMessage("Wrong number of arguments!");
                        return false;
                    }
                    return saveKit(player, world);
                } else if (args[0].equalsIgnoreCase("givekit")) {

                    Player player = getPlayer(sender, logger);
                    if (player == null) return false;
                    giveKit(player, SimpleStarterKits.getStarterKit());
                }
            }
            sender.sendMessage("You do not have permission to use this command.");//place in config
        }
        return false;
    }


    @Nullable
    private Player getPlayer(CommandSender sender, Logger logger) {
        World world;
        Player player;

        try {
            player = (Player) sender;
        } catch (ClassCastException e) {
            logger.info("Only a player can use savekit!");
            e.printStackTrace();
            return null;
        }
        return player;
    }

    private boolean saveKit(Player sender, World world) {
        //get the player's inventory, save it to a Kit, and put it in the kits file
        return true;
    }
}


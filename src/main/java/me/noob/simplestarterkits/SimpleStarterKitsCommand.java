package me.noob.simplestarterkits;


import lombok.SneakyThrows;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

import static org.bukkit.Bukkit.getPlayer;

public class SimpleStarterKitsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        /*subcommands:
        set [player], saves the player's inventory as the starter kit, or your own if you don't specify the player
        give [player], gives the player the starter kit, or you if you don't specify the player
        clear, removes the starter kit aka sets the kit to nothing
        reload, reloads the config files (saves changes made to them, if you decided to edit them in the files instead of using savekit for whatever reason),
        (no subcommand)/help, tells you this info,
        about; tells you about this plugin (creator, version, etc.)*/

        if (args.length == 0){
            help(sender);
            return true;
        }

        if (args.length >2){
            return false;
        }

        boolean success = true;
        switch (args[0].toLowerCase()) {
            case "give", "set" -> giveSet(sender, args[0].toLowerCase(), args);
            case "clear" -> clear(sender);
            case "reload" -> reload(sender);
            case "help" -> help(sender);
            case "about" -> about(sender);
            case default -> success = false;
        }
        return success;
    }

    @SneakyThrows
    private void giveSet(CommandSender sender, String giveSet, String[] args){
        String playerName;
        Player player;
        if (args.length==2){
            playerName = args[1];
            player = getPlayer(playerName);
            if (player == null) {
                sender.sendMessage("§cCould not find player '" + playerName + "'.");//red
                return;
            }
        } else {
            if (sender instanceof Player){
                playerName = sender.getName();
                player=(Player) sender;
            } else {
                sender.sendMessage("§c"+sender.getName()+" is not a player!");//red
                return;
            }
        }
        Method method = SimpleStarterKits.class.getMethod(giveSet+"Kit",Player.class);
        method.invoke(null,player);
        if (giveSet.equals("set")) {
            sender.sendMessage("§aSuccessfully saved " + playerName + "'s inventory as the starter kit!");//green
        } else {
            sender.sendMessage("§aSuccessfully gave " + playerName + " a starter kit!");//green
        }
    }

        SimpleStarterKits.saveKit(player);
        sender.sendMessage("§aSuccessfully saved " + playerName + "'s inventory as the starter kit.");//green
    }

    private void reload(CommandSender sender) {
        SimpleStarterKits.reload();
        sender.sendMessage("§aSuccessfully reloaded configs!");//green
    }

    private void help(CommandSender sender) {
        sender.sendMessage("§e§lUsage:");
        sender.sendMessage("§e/simplestarterkits [help|reload|set|give|about|clear]");
        sender.sendMessage("");
        sender.sendMessage("§e§lSubcommands:");
        sender.sendMessage("§eset [player], saves the player's inventory as the starter kit, or your own if you don't specify the player");
        sender.sendMessage("§egive [player], gives the player the starter kit, or you if you don't specify the player");
        sender.sendMessage("§eclear, removes the starter kit aka sets the kit to nothing");
        sender.sendMessage("§ereload, reloads the config files (saves changes made to them, if you decided to edit them in the files instead of using savekit for whatever reason),");
        sender.sendMessage("§eabout; tells you about this plugin (creator, version, etc.)");
        sender.sendMessage("§ehelp, tells you this info. It also tells you this info if you don't use a subcommand");
    }

    private void about(CommandSender sender) {
        sender.sendMessage("§e§lSimple Starter Kits");//yellow bold
        sender.sendMessage("§0------------------------------------------------------");//black
        sender.sendMessage("§eAuthor: Noob10293");
        sender.sendMessage("§eDiscord: ");
        sender.sendMessage("§eWebsite: ");
        sender.sendMessage("§aThis is the about command. For the help command, use \"/simplestarterkits help\"");
    }
}


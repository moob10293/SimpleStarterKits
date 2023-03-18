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
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        /*subcommands:
        set [player], saves the player's inventory as the starter kit, or your own if you don't specify the player
        give [player], gives the player the starter kit, or you if you don't specify the player
        clear, removes the starter kit aka sets the kit to nothing
        reload, reloads the config files (saves changes made to them, if you decided to edit them in the files instead of using savekit for whatever reason),
        (no subcommand)/help, tells you this info,
        about; tells you about this plugin (creator, version, etc.)*/

        if (args.length > 2) {
            return false;
        }

        if (args.length == 0) {
            help(sender);
            return true;
        }
        final String subcommand = args[0].toLowerCase();
        switch (args.length) {
            case 1 -> {
                boolean success = true;
                switch (subcommand) {
                    case "give", "set" -> giveSet(sender, subcommand);
                    case "clear" -> clear(sender);
                    case "reload" -> reload(sender);
                    case "help" -> help(sender);
                    case "about" -> about(sender);
                    default -> success = false;
                }
                return success;
            }
            case 2 -> {
                switch (subcommand) {
                    case "give", "set" -> {
                        giveSet(sender, subcommand, args[1]);
                        return true;
                    }
                    default -> {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @SneakyThrows
    private void giveSet(CommandSender sender, String giveSet, String playerName) {
        Player player = getPlayer(playerName);
        if (player == null) {
            sender.sendMessage("§cCould not find player '" + playerName + "'!");//red
            return;
        }
        giveSet(sender, giveSet, player, playerName);
    }

    @SneakyThrows
    private void giveSet(CommandSender sender, String giveSet) {
        if (sender instanceof Player) {
            String playerName = sender.getName();
            Player player = (Player) sender;
            giveSet(sender, giveSet, player, playerName);
        } else {
            sender.sendMessage("§c" + sender.getName() + " is not a player!");//red
        }
    }

    @SneakyThrows
    private void giveSet(CommandSender sender, @NotNull String giveSet, Player player, String playerName) {
        Method method = SimpleStarterKits.class.getMethod(giveSet + "Kit", Player.class);
        method.invoke(null, player);
        if (giveSet.equals("set")) {
            sender.sendMessage("§aSuccessfully saved " + playerName + "'s inventory as the starter kit.");//green
        } else if (giveSet.equals("give")) {
            sender.sendMessage("§aSuccessfully gave " + playerName + " a starter kit.");//green
        }
    }

    private void clear(@NotNull CommandSender sender) {
        SimpleStarterKits.clearKit();
        sender.sendMessage("§aSuccessfully cleared the starter kit.");//green
    }

    private void reload(@NotNull CommandSender sender) {
        SimpleStarterKits.reload();
        sender.sendMessage("§aSuccessfully reloaded configs.");//green
    }

    private void help(@NotNull CommandSender sender) {
        sender.sendMessage("§e§lUsage:");
        sender.sendMessage("§e/simplestarterkits [help|reload|set|give|about|clear]");
        sender.sendMessage("§eYou can also use /ssk instead of /simplestarterkits");
        sender.sendMessage("");
        sender.sendMessage("§e§lSubcommands:");
        sender.sendMessage("§eset [player], saves the player's inventory as the starter kit, or your own if you don't specify the player");
        sender.sendMessage("§egive [player], gives the player the starter kit, or you if you don't specify the player");
        sender.sendMessage("§eclear, removes the starter kit aka sets the kit to nothing");
        sender.sendMessage("§ereload, reloads the config files (saves changes made to them, if)");
        sender.sendMessage("§eabout; tells you about this plugin (creator, version, etc.)");
        sender.sendMessage("§ehelp, tells you this info. It is also the default subcommand (if you don't put any subcommand)");
    }

    private void about(@NotNull CommandSender sender) {
        sender.sendMessage("§e§lSimple Starter Kits");//yellow bold
        sender.sendMessage("§0------------------------------------------------------");//black
        sender.sendMessage("§eAuthor: Noob10293");
        sender.sendMessage("§eDiscord: ");
        sender.sendMessage("§eWebsite: ");
        sender.sendMessage("§aThis is the about command. For the help command, use \"/simplestarterkits help\"");
    }
}


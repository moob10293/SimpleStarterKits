package me.noob.simplestarterkits;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    private static final String[] COMMANDS = { "set", "give", "clear", "reload", "help", "about" };

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length==1) {
            StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
        } else if (args.length==2){
            List<String> players = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()){
                players.add(p.getName());
            }
            StringUtil.copyPartialMatches(args[0], players, completions);
        }
        completions.sort(null);
        return completions;
    }
}

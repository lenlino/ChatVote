package lenlino.com.chatvote;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public final class Chatvote extends JavaPlugin {

    int n = getConfig().getInt("n");
    int y = getConfig().getInt("y");
    ArrayList<String> player_list = new ArrayList<String>();
    ArrayList<String> yes_or_no = new ArrayList<String>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("votestart")) {
            y = 0;
            n = 0;
            player_list.clear();
            yes_or_no.clear();
            sender.sendMessage("投票開始");
            return true;
        }
        if (command.getName().equals("vote")) {
            if (!player_list.contains(sender.getName())){
                if (args[0].equals("y")) {
                    y =+ 1;
                    player_list.add(sender.getName());
                    yes_or_no.add("y");
                    sender.sendMessage("賛成票を入れました");
                    return true;
                } else if (args[0].equals("n")) {
                    n =+ 1;
                    player_list.add(sender.getName());
                    yes_or_no.add("n");
                    sender.sendMessage("反対票を入れました");
                    return true;
                } else {
                    sender.sendMessage("/vote y または n - 投票します");
                }
            } else {
                sender.sendMessage("既に投票済みです");
            }
        }
        if (command.getName().equals("voteresult")) {
            sender.sendMessage("賛成 "+y+" 反対 "+n);
        }
        if (command.getName().equals("votelist")) {
            sender.sendMessage("投票者"+player_list);
        }
        if (command.getName().equals("votecancel")) {
            sender.sendMessage("投票者"+player_list);
        }
        return false;
    }

    @Override
    public void onEnable() {
        getLogger().info("プラグインが開始しました");
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        player_list.addAll(config.getStringList("player_list"));
        yes_or_no.addAll(config.getStringList("yes_or_mo"));
    }

    @Override
    public void onDisable() {
        getLogger().info("プラグインが停止しました");
        getConfig().set("y",y);
        getConfig().set("n",n);
        getConfig().set("player_list",player_list);
        getConfig().set("yes_or_no",yes_or_no);
        saveConfig();
    }
}


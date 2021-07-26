package de.pyloger.betterjoinmessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PyloGER extends JavaPlugin implements Listener {

    String prefix = getConfig().getString("config.prefix");
    String suffix = getConfig().getString("config.suffix");

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();
        Bukkit.getPluginManager().registerEvents(this, this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        saveDefaultConfig();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        int online = Bukkit.getOnlinePlayers().size();
        String prefix = getConfig().getString("config.prefix");
        String suffix = getConfig().getString("config.suffix");

        if(player.hasPlayedBefore()) {
            if (getConfig().getBoolean("config.joinactivated", true)) {
                String message1 = getConfig().getString("config.joinmessage");
                message1 = message1.replace("%Player%", player.getName());
                message1 = message1.replace("%Online%", String.valueOf(online));
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', prefix + "§f " + message1 + "§f " + suffix));
            }
        } else if (getConfig().getBoolean("config.joinactivated", true)) {
            String message = getConfig().getString("config.firstjoin");
            message = message.replace("%Player%", player.getName());
            message = message.replace("%Online%", String.valueOf(online));
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&',prefix + "§f " + message + "§f " + suffix));
            } else {
            event.setJoinMessage(null);
        }
        }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        int online = Bukkit.getOnlinePlayers().size()-1;
        String prefix = getConfig().getString("config.prefix");
        String suffix = getConfig().getString("config.suffix");

        if (getConfig().getBoolean("config.quitactivated", true)) {
        String message = getConfig().getString("config.quitmessage");
        message = message.replace("%Player%", player.getName());
        message = message.replace("%Online%", String.valueOf(online));
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&',prefix + "§f " + message + "§f " + suffix));
      } else {
            event.setQuitMessage(null);
        }
    }
}
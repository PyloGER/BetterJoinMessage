package de.pyloger.betterjoinmessage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class PyloGER extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadConfig();

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
        String message = getConfig().getString("config.message");
        message = message.replace("[Player]", player.getName());
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}

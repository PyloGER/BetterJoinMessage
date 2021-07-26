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

        boolean jm = getConfig().getBoolean("config.joinactivate");
        boolean pm = getConfig().getBoolean("config.pmactivate");

        String prefix = getConfig().getString("config.prefixjoin");
        if (prefix != null) {
            prefix = prefix.replace("%Player%", player.getName());
            prefix = prefix.replace("%Online%", String.valueOf(online));
        }
        String suffix = getConfig().getString("config.suffixjoin");
        if (suffix != null) {
            suffix = suffix.replace("%Player%", player.getName());
            suffix = suffix.replace("%Online%", String.valueOf(online));
        }
        String prefix2 = getConfig().getString("config.prefixpm");
        if (prefix2 != null) {
            prefix2 = prefix2.replace("%Player%", player.getName());
            prefix2 = prefix2.replace("%Online%", String.valueOf(online));
        }
        String suffix2 = getConfig().getString("config.suffixpm");
        if (suffix2 != null) {
            suffix2 = suffix2.replace("%Player%", player.getName());
            suffix2 = suffix2.replace("%Online%", String.valueOf(online));
        }

        if (jm) {
            if (player.hasPlayedBefore()) {
                String message1 = getConfig().getString("config.joinmessage");
                if (message1 != null) {
                    message1 = message1.replace("%Player%", player.getName());
                    message1 = message1.replace("%Online%", String.valueOf(online));
                    event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', prefix + "§f " + message1 + "§f " + suffix));
                }

            } else {
                String message = getConfig().getString("config.firstjoin");
                if (message != null) {
                    message = message.replace("%Player%", player.getName());
                    message = message.replace("%Online%", String.valueOf(online));
                    event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', prefix + "§f " + message + "§f " + suffix));
                }
            }
        }
        if (!jm) {
            event.setJoinMessage(null);
        }
        if (pm) {
            if (player.hasPlayedBefore()) {
                String message2 = getConfig().getString("config.privatemsg");
                if (message2 != null) {
                    message2 = message2.replace("%Player%", player.getName());
                    message2 = message2.replace("%Online%", String.valueOf(online));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix2 + message2 + suffix2));
                } else {
                    String message3 = getConfig().getString("config.firstpm");
                    if (message3 != null) {
                        message3 = message3.replace("%Player%", player.getName());
                        message3 = message3.replace("%Online%", String.valueOf(online));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix2 + message3 + suffix2));
                    }
                }
            }
        }
    }
            @EventHandler
            public void onQuit (PlayerQuitEvent event){
                Player player = event.getPlayer();

                int online = Bukkit.getOnlinePlayers().size() - 1;

                boolean qm = getConfig().getBoolean("config.quitactivate");

                String prefix = getConfig().getString("config.prefixquit");
                if (prefix != null) {
                    prefix = prefix.replace("%Player%", player.getName());
                    prefix = prefix.replace("%Online%", String.valueOf(online));
                }

                String suffix = getConfig().getString("config.suffixquit");
                if (suffix != null) {
                    suffix = suffix.replace("%Player%", player.getName());
                    suffix = suffix.replace("%Online%", String.valueOf(online));
                }


                if (qm) {
                    String message = getConfig().getString("config.quitmessage");
                    if (message != null) {
                        message = message.replace("%Player%", player.getName());
                        message = message.replace("%Online%", String.valueOf(online));
                    }
                    event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', prefix + message + suffix));
                }
                if (!qm) {
                    event.setQuitMessage(null);
                }
            }
        }
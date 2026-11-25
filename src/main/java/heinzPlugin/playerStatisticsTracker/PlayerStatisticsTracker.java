package heinzPlugin.playerStatisticsTracker;

import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerStatisticsTracker extends JavaPlugin {

    private PlayerStatisticsManager manager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        manager = new PlayerStatisticsManager();
        getLogger().info("Plugin has been enabled!");
        getServer().getPluginManager().registerEvents(
                new StatisticsListener(manager), this
        );

        getCommand("stats").setExecutor(new StatsCommand(manager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

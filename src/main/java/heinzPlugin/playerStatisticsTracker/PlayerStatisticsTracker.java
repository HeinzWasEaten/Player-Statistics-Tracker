package heinzPlugin.playerStatisticsTracker;

import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerStatisticsTracker extends JavaPlugin {
    private PlayerStatisticsManager statisticsManager;


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("ON ENABLE TEST");

        statisticsManager = new PlayerStatisticsManager(this);
        getLogger().info("Plugin has been enabled!");
        getServer().getPluginManager().registerEvents(
                new StatisticsListener(statisticsManager), this
        );

        getCommand("stats").setExecutor(new StatsCommand(statisticsManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("ON DISABLE TEST");

        if (statisticsManager != null) {
            statisticsManager.saveStatistics();
        }

    }
}

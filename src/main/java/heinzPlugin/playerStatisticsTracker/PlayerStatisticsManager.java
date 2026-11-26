package heinzPlugin.playerStatisticsTracker;

import java.io.*;
import java.util.HashMap;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PlayerStatisticsManager {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final HashMap<String, Integer> blocksBroken = new HashMap<>();
    private final HashMap<String, Integer> blocksPlaced = new HashMap<>();
    private final File statsFile;

    private final PlayerStatisticsTracker plugin;
    public PlayerStatisticsManager(PlayerStatisticsTracker plugin) {
        this.plugin = plugin;
        this.statsFile = new File(plugin.getDataFolder(), "stats.json");
        plugin.getDataFolder().mkdirs();

        try {
            statsFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveStatistics() {
        plugin.getLogger().info("Saving player statistics...");
        try (FileWriter writer = new FileWriter(statsFile)){
            gson.toJson(blocksBroken, writer);
            gson.toJson(blocksPlaced, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadStatistics() {
        Type type = new  TypeToken<HashMap<String, Integer>>(){}.getType();

        try (FileReader reader = new FileReader(statsFile)){
           HashMap<String, Integer> blocksBrokenSavedStats = gson.fromJson(reader, type);
           HashMap<String, Integer> blocksPlacedSavedStats = gson.fromJson(reader, type);
           if (blocksBrokenSavedStats != null && blocksPlacedSavedStats != null) {
               blocksBroken.putAll(blocksBrokenSavedStats);
               blocksPlaced.putAll(blocksPlacedSavedStats);
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBlockBroken(String playerName) {
        blocksBroken.put(playerName, blocksBroken.getOrDefault(playerName, 0) + 1);
    }

    public void addBlockPlaced(String playerName) {
        blocksPlaced.put(playerName, blocksPlaced.getOrDefault(playerName, 0) + 1);
    }

    public Integer getBlockBrokenCount (String playerName) {
        return blocksBroken.getOrDefault(playerName, 0);
    }

    public Integer getBlockPlacedCount (String playerName) {
        return blocksPlaced.getOrDefault(playerName, 0);
    }

}

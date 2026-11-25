package heinzPlugin.playerStatisticsTracker;

import java.util.HashMap;

public class PlayerStatisticsManager {

    private final HashMap<String, Integer> blocksBroken = new HashMap<>();

    public void addBlockBroken(String playerName) {
        blocksBroken.put(playerName, blocksBroken.getOrDefault(playerName, 0) + 1);
    }

    public Integer getBlockCount(String playerName) {
        return blocksBroken.getOrDefault(playerName, 0);
    }

}

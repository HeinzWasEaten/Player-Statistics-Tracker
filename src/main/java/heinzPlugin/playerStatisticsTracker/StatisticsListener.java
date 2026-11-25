package heinzPlugin.playerStatisticsTracker;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class StatisticsListener implements Listener {

    private final PlayerStatisticsManager manager;
    public StatisticsListener(PlayerStatisticsManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        String playerName = player.getName();

        manager.addBlockBroken(playerName);

        Bukkit.getLogger().info(playerName + " broke " +  block.getType());
    }



    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        String playerName = player.getName();
        Bukkit.getLogger().info(playerName + " placed " +  event.getBlock().getType());
    }
}

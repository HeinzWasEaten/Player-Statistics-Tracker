package heinzPlugin.playerStatisticsTracker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StatsCommand implements CommandExecutor {

    private final PlayerStatisticsManager manager;
    public StatsCommand(PlayerStatisticsManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;
        String playerName = player.getName();

        int blocksBroken = manager.getBlockBrokenCount(playerName);
        int blocksPlaced = manager.getBlockPlacedCount(playerName);

        player.sendMessage(ChatColor.GOLD + "===== Your Statistics =====");
        player.sendMessage(ChatColor.YELLOW + "Blocks Broken: " + blocksBroken);
        player.sendMessage(ChatColor.YELLOW + "Blocks Placed: " + blocksPlaced);
        player.sendMessage(ChatColor.GOLD + "===========================");

        return true;

    }
}

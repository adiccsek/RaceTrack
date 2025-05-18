package hu.shiya.raceTrack.listeners;

import hu.shiya.raceTrack.RaceTrack;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;


public class FallCheck implements Listener {
    private final GameLogic instance;
    private final RaceTrack raceTrack;
    public FallCheck( GameLogic instance, RaceTrack raceTrack ) {
        this.instance = instance;
        this.raceTrack = raceTrack;
    }
    double landedY = 0; double fromY = 0;
    @EventHandler
    public void onFallEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!instance.getPlayers(player.getUniqueId())) {
            return;
        }
        fromY = event.getFrom().getY();
        @NotNull Material material = player.getLocation().subtract(0, 1, 0).getBlock().getType();
        if (material != Material.SLIME_BLOCK && material != Material.STONE_PRESSURE_PLATE) {
            if (player.isOnGround()) {
                landedY = player.getLocation().getY();
            }
        }
        double verticalDistance = landedY - fromY;
        int height = raceTrack.getConfig().getInt("max-height-fall");
        if (verticalDistance > height) { //config file value kell majd
            player.sendMessage("you fell");
            player.teleport(instance.getLocation(player.getUniqueId()));
        }
    }
}
package hu.shiya.raceTrack.listeners;

import hu.shiya.raceTrack.RaceTrack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Boosters implements Listener {
    private final GameLogic instance;
    public Boosters(GameLogic instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onSlimeJump(PlayerMoveEvent event) {
        long Time;
        Player player = event.getPlayer();
        if (!instance.getPlayers(player.getUniqueId())) {
            return;
        }
        @NotNull Material material = player.getLocation().subtract(0, 1, 0).getBlock().getType();
        if (material == Material.SLIME_BLOCK) {
            player.setVelocity(new Vector(player.getVelocity().getX(), 1.6, player.getVelocity().getZ()));
        }
        if (material == Material.DIAMOND_BLOCK) {
            player.addPotionEffect(new PotionEffect(
                            PotionEffectType.SPEED,
                            100,
                            1
                            )
            );
        }
    }
}
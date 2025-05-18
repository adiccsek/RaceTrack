package hu.shiya.raceTrack.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;


public class GameLogic implements Listener {
    private final HashMap<UUID, Long> gamePlayers = new HashMap<>();
    private final HashMap<UUID, Location> playerLocation = new HashMap<>();

    public boolean getPlayers(UUID uuid) {
        return gamePlayers.containsKey(uuid);
    }
    public Location getLocation(UUID uuid) {
        return playerLocation.get(uuid);
    }

    @EventHandler
    public void onPressurePlate(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (event.getClickedBlock() == null) return;
        @NotNull Material material = event.getClickedBlock().getType();
        long Time;
        Player player = event.getPlayer();

        if (action == Action.PHYSICAL && material == Material.STONE_PRESSURE_PLATE) {
            if (!gamePlayers.containsKey(player.getUniqueId())) {
                gamePlayers.put(player.getUniqueId(), System.currentTimeMillis());
                playerLocation.put(player.getUniqueId(), player.getLocation());
                System.out.println("The Game has started!!! in " );
                player.sendMessage("The Game has started!!!");
            } else {
                Time = gamePlayers.get(player.getUniqueId()) - System.currentTimeMillis();
                System.out.println("The game has ended!!! in " + Time / 1000 + " seconds");
                player.sendMessage("The Game has ended!!!" + Time / 1000 + " seconds");
                player.removePotionEffect(PotionEffectType.SPEED);
                gamePlayers.remove(player.getUniqueId());
                playerLocation.remove(player.getUniqueId());
            }
        }
    }
}


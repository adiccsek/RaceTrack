package hu.shiya.raceTrack;

import hu.shiya.raceTrack.listeners.Boosters;
import hu.shiya.raceTrack.listeners.FallCheck;
import hu.shiya.raceTrack.listeners.GameLogic;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class RaceTrack extends JavaPlugin {
    GameLogic gameLogic = new GameLogic();
    Boosters boosters = new Boosters( gameLogic, this );
    FallCheck fallCheck = new FallCheck( gameLogic, this  );

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(gameLogic, this);
        getServer().getPluginManager().registerEvents(boosters, this);
        getServer().getPluginManager().registerEvents(fallCheck, this);
    }
    public void onDisable() {
        HandlerList.unregisterAll( gameLogic );
        HandlerList.unregisterAll( boosters );
    }
}

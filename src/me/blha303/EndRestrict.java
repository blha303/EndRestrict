package me.blha303;

//import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Logger;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.EnderDragon;
//import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EndRestrict extends JavaPlugin implements Listener {

	public final Logger logger = Logger.getLogger("Minecraft");

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onEndChangeWorld(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		World w = event.getPlayer().getWorld();
		if (w.getEnvironment().toString() == "THE_END"
				&& !p.hasPermission("theend.allow")) {
			event.setCancelled(true);
		} else {
			return;
		}
	}

	@EventHandler
	public void onEnderDragonKilled(EntityDeathEvent event) {
		Player p = event.getEntity().getKiller();
		Entity e = event.getEntity();
		if (e instanceof EnderDragon) {
			logger.warning("[EndRestrict] "+p.getName()+" killed the Ender Dragon!");
		}
	}

/*	@EventHandler
	public void onEnderDragonSpawn(CreatureSpawnEvent event) {
		World world = event.getEntity().getWorld();
		List<Entity> entities = world.getEntities();
		List<Entity> dragons = new ArrayList<Entity>();
		for (Entity e : entities) {
			if (e instanceof EnderDragon) {
				dragons.add(e);
				return;
			}
		}
		if (dragons != null) {
			for (Entity e : dragons) {
				double x = e.getLocation().getX();
				double y = e.getLocation().getY();
				double z = e.getLocation().getZ();
				logger.warning("[EndRestrict] Ender Dragon spawned at x:" + x
						+ " y:" + y + " z:" + z + " on "
						+ e.getWorld().getName());
			}
		}
	} */
}

package net.justinthetrucker;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class FireballShooter extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("FireballShooter has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FireballShooter has been disabled!");
    }

    @EventHandler
    public void onPlayerSwing(PlayerAnimationEvent event) {
        if (event.getAnimationType() == PlayerAnimationType.ARM_SWING) {
            Player player = event.getPlayer();

            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (itemInHand == null || itemInHand.getType() != Material.STICK) {
                return;
            }

            Vector direction = player.getEyeLocation().getDirection();

            Fireball fireball = player.getWorld().spawn(
                    player.getEyeLocation().add(direction.multiply(2)),
                    Fireball.class
            );

            fireball.setDirection(direction);
            fireball.setYield(2.0f);
            fireball.setIsIncendiary(true);

            fireball.setShooter(player);
        }
    }
}
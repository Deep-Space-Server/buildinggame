package com.gmail.stefvanschiedev.buildinggame.events;

import com.gmail.stefvanschiedev.buildinggame.managers.arenas.ArenaManager;
import me.gnat008.perworldinventory.events.InventoryLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Cancel per world inventory editing the inventories when we're in-game
 *
 * @since 5.2.0
 */
public class PerWorldInventoryCancel implements Listener {

    /**
     * Listens for the inventory being edited and cancels it when needed
     *
     * @param e the event of the inventory being about to change
     */
    @EventHandler(ignoreCancelled = true)
    public void onInventoryLoad(InventoryLoadEvent e) {
        if (ArenaManager.getInstance().getArena(e.getPlayer()) != null)
            e.setCancelled(true);
    }
}
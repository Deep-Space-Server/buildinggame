package com.gmail.stefvanschiedev.buildinggame.events.player.gui.subjectmenu;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.gmail.stefvanschiedev.buildinggame.managers.arenas.ArenaManager;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.id.IDDecompiler;
import com.gmail.stefvanschiedev.buildinggame.utils.arena.Arena;

public class OpenSubjectMenu implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		Player player = e.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		Arena arena = ArenaManager.getInstance().getArena(player);
		
		if (arena == null) {
			return;
		}
		
		if (item == null)
			return;
		
		if (item.getType() != IDDecompiler.getInstance().decompile(config.getString("subject-gui.item.id")).getType()) {
			return;
		}
		
		if (!item.hasItemMeta()) {
			return;
		}
		
		if (!item.getItemMeta().getDisplayName().equals(messages.getString("subject-gui.item.name")
				.replaceAll("&", "�"))) {
			return;
		}
		
		arena.getSubjectMenu().open(player);
		e.setCancelled(true);
	}
}
package com.gmail.stefvanschiedev.buildinggame.events.player;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.stefvanschiedev.buildinggame.managers.arenas.ArenaManager;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.id.IDDecompiler;
import com.gmail.stefvanschiedev.buildinggame.managers.mainspawn.MainSpawnManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;
import com.gmail.stefvanschiedev.buildinggame.utils.arena.Arena;
import com.gmail.stefvanschiedev.buildinggame.utils.gameplayer.GamePlayerType;
import com.gmail.stefvanschiedev.buildinggame.utils.plot.Plot;

public class LeaveClick implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		Player player = e.getPlayer();
		
		if (ArenaManager.getInstance().getArena(player) == null)
			return;
		
		Arena arena = ArenaManager.getInstance().getArena(player);
		Plot plot = arena.getPlot(player);
		
		if (!IDDecompiler.getInstance().matches(config.getString("leave-item.id"), player.getInventory().getItemInMainHand()))
			return;
		
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		
		if (!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(MessageManager.translate(messages.getString("leave-item.name"))))
			return;
		
		if (plot.getGamePlayer(player).getGamePlayerType() == GamePlayerType.SPECTATOR) {
			plot.getGamePlayer(player).connect(MainSpawnManager.getInstance().getServer(), MainSpawnManager.getInstance().getMainSpawn());
			plot.removeSpectator(plot.getGamePlayer(player));
			MessageManager.getInstance().send(player, ChatColor.GREEN + "Stopped spectating");
		} else
			arena.leave(player);
	}
}
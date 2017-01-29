package com.gmail.stefvanschiedev.buildinggame.events.player.spectator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.gmail.stefvanschiedev.buildinggame.managers.arenas.ArenaManager;
import com.gmail.stefvanschiedev.buildinggame.managers.mainspawn.MainSpawnManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;
import com.gmail.stefvanschiedev.buildinggame.utils.gameplayer.GamePlayerType;
import com.gmail.stefvanschiedev.buildinggame.utils.plot.Plot;

public class LeaveClick implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		
		if (ArenaManager.getInstance().getArena(player) == null)
			return;
		
		Plot plot = ArenaManager.getInstance().getArena(player).getPlot(player);
		
		if (plot.getGamePlayer(player).getGamePlayerType() != GamePlayerType.SPECTATOR)
			return;
		
		if (player.getInventory().getItemInMainHand().getType() != Material.WATCH)
			return;
		
		if (!player.getInventory().getItemInMainHand().hasItemMeta())
			return;
		
		if (!player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Leave"))
			return;
		
		plot.getGamePlayer(player).connect(MainSpawnManager.getInstance().getServer());
		player.teleport(MainSpawnManager.getInstance().getMainSpawn());
		
		plot.removeSpectator(plot.getGamePlayer(player));
		
		MessageManager.getInstance().send(player, ChatColor.GREEN + "Stopped spectating");
	}
}
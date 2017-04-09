package com.gmail.stefvanschiedev.buildinggame.commands.subcommands;

import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.CommandResult;
import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.PlayerCommand;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.mainspawn.MainSpawnManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;

public class SetMainSpawn extends PlayerCommand {

	@Override
	public CommandResult onCommand(Player player, String[] args) {
		YamlConfiguration arenas = SettingsManager.getInstance().getArenas();
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		List<String> worlds = config.getStringList("scoreboards.main.worlds.enable");
		
		if (arenas.contains("main-spawn.world"))
			worlds.remove(arenas.getString("main-spawn.world"));
		
		worlds.add(player.getLocation().getWorld().getName());
		config.set("scoreboards.main.worlds.enable", worlds);
		
		arenas.set("main-spawn.server", player.getServer().getServerName());
		arenas.set("main-spawn.world", player.getLocation().getWorld().getName());
		arenas.set("main-spawn.x", player.getLocation().getBlockX());
		arenas.set("main-spawn.y", player.getLocation().getBlockY());
		arenas.set("main-spawn.z", player.getLocation().getBlockZ());
		SettingsManager.getInstance().save();
		
		MainSpawnManager.getInstance().setMainSpawn(player.getLocation());
		MessageManager.getInstance().send(player, messages.getStringList("setMainSpawn.succes"));
		
		return CommandResult.SUCCES;
	}

	@Override
	public String getName() {
		return "setmainspawn";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getInfo() {
		return "Set the main spawn";
	}

	@Override
	public String getPermission() {
		return "bg.setmainspawn";
	}

}

package com.gmail.stefvanschiedev.buildinggame.commands.subcommands.settings.commands.second;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.CommandResult;
import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.ConsoleCommand;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;

public class Remove extends ConsoleCommand {

	@Override
	public CommandResult onCommand(CommandSender sender, String[] args) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		
		if (args.length < 1) {
			MessageManager.getInstance().send(sender, ChatColor.RED + "Please specify a command");
			return CommandResult.ARGUMENTEXCEPTION;
		}
		
		StringBuilder command = new StringBuilder();
		
		for (String arg : args)
			command.append(arg).append(" ");
		
		config.set("commands.second", config.getStringList("commands.second").remove(command.toString().trim()));
		SettingsManager.getInstance().save();
		
		MessageManager.getInstance().send(sender, ChatColor.GREEN + "Removed " + command + " command");
		
		return CommandResult.SUCCES;
	}

	@Override
	public String getName() {
		return "remove";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getInfo() {
		return "Remove a command from the second commands";
	}

	@Override
	public String getPermission() {
		return "bg.setting.commands.second.remove";
	}
}
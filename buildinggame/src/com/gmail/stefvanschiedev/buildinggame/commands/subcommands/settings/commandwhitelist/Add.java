package com.gmail.stefvanschiedev.buildinggame.commands.subcommands.settings.commandwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.CommandResult;
import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.ConsoleCommand;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;

public class Add extends ConsoleCommand {

	@Override
	public CommandResult onCommand(CommandSender sender, String[] args) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		
		if (args.length < 1) {
			MessageManager.getInstance().send(sender, ChatColor.RED + "Please specify the command");
			return CommandResult.ARGUMENTEXCEPTION;
		}
		
		StringBuilder command = new StringBuilder();
		
		for (String argument : args)
			command.append(argument).append(" ");
		
		config.set("command-whitelist", config.getStringList("command-whitelist").add(command.toString().trim()));
		SettingsManager.getInstance().save();
		
		MessageManager.getInstance().send(sender, ChatColor.GREEN + "Command " + command + " added!");
		
		return CommandResult.SUCCES;
	}

	@Override
	public String getName() {
		return "add";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getInfo() {
		return "Add a command to the whitelist";
	}

	@Override
	public String getPermission() {
		return "bg.setting.commandwhitelist.add";
	}
}
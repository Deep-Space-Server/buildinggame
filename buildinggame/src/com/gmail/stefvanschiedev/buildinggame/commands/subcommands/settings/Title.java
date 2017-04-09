package com.gmail.stefvanschiedev.buildinggame.commands.subcommands.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.CommandResult;
import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.ConsoleCommand;
import com.gmail.stefvanschiedev.buildinggame.commands.commandutils.SubCommand;
import com.gmail.stefvanschiedev.buildinggame.commands.subcommands.settings.title.FadeIn;
import com.gmail.stefvanschiedev.buildinggame.commands.subcommands.settings.title.FadeOut;
import com.gmail.stefvanschiedev.buildinggame.commands.subcommands.settings.title.Stay;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;

public class Title extends ConsoleCommand {

	private final List<SubCommand> subCommands = new ArrayList<>();
	
	@Override
	public CommandResult onCommand(CommandSender sender, String[] args) {
		
		subCommands.add(new FadeIn());
		subCommands.add(new Stay());
		subCommands.add(new FadeOut());
		
		if (args.length == 0) {
			for (SubCommand sc : subCommands)
				MessageManager.getInstance().sendWithoutPrefix(sender, ChatColor.GREEN + "/bg setting title " + sc.getName() + " - " + sc.getInfo());
			
			return CommandResult.ARGUMENTEXCEPTION;
		}
		
		for (SubCommand subCommand : subCommands) {
			if (subCommand.getName().equalsIgnoreCase(args[0])) {
				if (sender.hasPermission(subCommand.getPermission())) {
					//remove first argument
				
					List<String> arguments = new ArrayList<>();
					arguments.addAll(Arrays.asList(args));
					arguments.remove(0);
					args = arguments.toArray(new String[arguments.size()]);

					return subCommand.onCommand(sender, args);
				}
			}
		}
		
		MessageManager.getInstance().send(sender, ChatColor.RED + "That's not a setting");
		return CommandResult.ERROR;
	}

	@Override
	public String getName() {
		return "title";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getInfo() {
		return "Change the title setting";
	}

	@Override
	public String getPermission() {
		return "bg.setting.title";
	}
}
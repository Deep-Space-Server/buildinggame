package com.gmail.stefvanschiedev.buildinggame.managers.messages;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;

public class MessageManager {

	private MessageManager() {}
	
	private static MessageManager instance = new MessageManager();
	
	public static MessageManager getInstance() {
		return instance;
	}
	
	public void send(CommandSender sender, String message) {
		if (message.equals("")) {
			return;
		}
		
		sender.sendMessage(translate(SettingsManager.getInstance().getMessages().getString("global.prefix"))
				+ translate(message));
	}
	
	public void send(CommandSender sender, List<String> messages) {
		for (String message : messages) {
			if (message.equals("")) {
				return;
			}
		
			sender.sendMessage(translate(SettingsManager.getInstance().getMessages().getString("global.prefix")) 
					+ translate(message));
		}
	}
	
	public void sendWithoutPrefix(CommandSender sender, String message) {
		if (message.equals("")) {
			return;
		}
		
		sender.sendMessage(translate(message));
	}
	
	public void sendWithoutPrefix(CommandSender sender, List<String> messages) {
		for (String message : messages) {
			if (message.equals("")) {
				return;
			}
			
			sender.sendMessage(translate(message));
		}
	}
	
	public static String translate(String s) {
		return s.replace("%:a%", "�")
		.replace("%:e%", "�")
		.replace("%:i%", "�")
		.replace("%:o%", "�")
		.replace("%:u%", "�")
		.replace("%:A%", "�")
		.replace("%:E%", "�")
		.replace("%:I%", "�")
		.replace("%:O%", "�")
		.replace("%:U%", "�")
		.replace("%/a%", "�")
		.replace("%/e%", "�")
		.replace("%/i%", "�")
		.replace("%/o%", "�")
		.replace("%/u%", "�")
		.replace("%ss%", "�")
		.replaceAll("&", "�");
	}
	
	public static List<String> translate(List<String> s) {
		List<String> list = new ArrayList<String>();
		
		for (String text : s) {
			list.add(translate(text));
		}
		
		return list;
	}
}
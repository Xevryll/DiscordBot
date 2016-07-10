package com.maeyrl.jinx.Commands;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class CleverBot implements MessageCreateListener {

	ChatterBotFactory factory = null;
	ChatterBot bot = null;
	ChatterBotSession session = null;
	ChatterBotFactory factory2 = null;
	ChatterBot bot2 = null;
	ChatterBotSession session2 = null;

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {

		if (message.getAuthor().equals(api.getYourself())) {
			return;
		}
		
		if (MuteCommand.muted.contains(message.getAuthor())) {
			message.delete();
			return;
		}

		String[] args = message.getContent().split(" ");
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				if (args[0].equalsIgnoreCase("/$talk")) {
					try {
						if (factory == null) {
							factory = new ChatterBotFactory();
							bot = factory.create(ChatterBotType.CLEVERBOT);
							session = bot.createSession();
							message.getChannelReceiver().sendMessage("CleverBot Session created.");
						}
						String aa = "";
						for (String g : args) {
							if (!g.equalsIgnoreCase("/$talk")) {
								aa += " " + g;
							}
						}
						String g = aa;
						g = session.think(g);
						message.getChannelReceiver().sendMessage("**Interpreting: **" + aa + "\n**Bot> **" + g);
					} catch (Exception e) {
						message.getChannelReceiver().sendMessage("Crashed");
					}
				} else if (args[0].equalsIgnoreCase("/$talkp")) {
					try {
						if (factory2 == null) {
							factory2 = new ChatterBotFactory();
							bot2 = factory2.create(ChatterBotType.PANDORABOTS, "n");
							session2 = bot2.createSession();
							message.getChannelReceiver().sendMessage("PandoraBot Session created.");
						}
						String aa = "";
						for (String g : args) {
							if (!g.equalsIgnoreCase("/$talkp")) {
								aa += " " + g;
							}
						}
						String g = aa;
						g = session2.think(g);
						message.getChannelReceiver().sendMessage("**Interpreting: **" + aa + "\n**PBot> **" + g);
					} catch (Exception e) {
						message.getChannelReceiver().sendMessage("Crashed");
					}
				}
			}

		}
	}

}
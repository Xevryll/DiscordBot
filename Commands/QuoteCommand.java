package com.maeyrl.jinx.Commands;

import java.util.Random;

import com.maeyrl.jinx.Data.JinxQuotes;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class QuoteCommand implements MessageCreateListener {

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
				if(args[0].equalsIgnoreCase("/$quote")) {
					int x = new Random().nextInt(JinxQuotes.quotes.length);
					String quote = JinxQuotes.quotes[x];
					message.getChannelReceiver().sendMessage("```"
							+ "\n\"" + quote + "\"\n"
							+ "```");
				}
			}
		}
		
	}

}

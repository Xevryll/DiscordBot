package com.maeyrl.jinx.Commands;

import java.util.Random;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class RandomNumberCommand implements MessageCreateListener {

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
				 if (args[0].equalsIgnoreCase("/$rnd")) {
					Random rand = new Random();
					message.getChannelReceiver()
							.sendMessage(message.getAuthor().getMentionTag() + " has rolled the random number "
							+ (rand.nextInt(Integer.valueOf(args[1]))) + " from 0 to " + args[1] + ".");
				 }
			}
		}
		
	}

}
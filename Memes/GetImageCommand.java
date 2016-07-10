package com.maeyrl.jinx.Memes;

import com.maeyrl.jinx.Commands.MuteCommand;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class GetImageCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$getimage")) {
					if (Memecatch.imageCache.containsKey(args[1])) {
						message.getChannelReceiver().sendFile(Memecatch.imageCache.get(args[1]));
					} else {
						message.getChannelReceiver().sendMessage("Image not found in system.");
					}
				} else if (args[0].equalsIgnoreCase("/$getgif")) {
					if (Memecatch.gifCache.containsKey(args[1])) {
						message.getChannelReceiver().sendMessage(Memecatch.gifCache.get(args[1]));
					} else {
						message.getChannelReceiver().sendMessage("Gif not found in system.");
					}
				} else if(args[0].equalsIgnoreCase("/$getvine")) {
					if(Memecatch.vineCache.containsKey(args[1])) {
						message.getChannelReceiver().sendMessage(Memecatch.vineCache.get(args[1]));
					} else {
						message.getChannelReceiver().sendMessage("Vine not found in system");
					}
				}
			}
		}

	}

}

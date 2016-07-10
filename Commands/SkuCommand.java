package com.maeyrl.jinx.Commands;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class SkuCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$sku")) {
					String temp = "";
					args[0] = "";
					for (int i = 0; i < args.length; i++) {
						if (i != 0 || i != 1) {
							if (args[i] != "")
								temp += args[i] + " ";
						}
					}
					String gg1 = temp.trim();
					String gg = gg1.replaceAll(" ", "+");
					message.getChannelReceiver().sendMessage(
							message.getAuthor().getMentionTag() + ": http://www.skunity.com/search?search=" + gg);

				} 
			}
		}
		
	}

}
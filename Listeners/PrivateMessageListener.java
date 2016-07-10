package com.maeyrl.jinx.Listeners;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class PrivateMessageListener implements MessageCreateListener {
	
	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {
		if(message.isPrivateMessage()) {
			if(!(message.getAuthor().isYourself())) {
				message.reply("Asuh dude.");
			}
		}
	}
	
}

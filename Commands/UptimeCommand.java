package com.maeyrl.jinx.Commands;

import java.util.Date;

import com.maeyrl.jinx.Data.DataHolder;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class UptimeCommand implements MessageCreateListener {

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
				if(args[0].equalsIgnoreCase("/$uptime")) {
					long seconds = (new Date().getTime() - DataHolder.startTime) / 1000;
					long minutes = seconds / 60;
					long hours = minutes / 60;
					long days = hours / 24;
					String time = days + " days, " + hours % 24 + " hours, " + minutes % 60 + " minutes and " + seconds % 60 + " seconds";
					message.getChannelReceiver().sendMessage("```My uptime is currently" + "\n" + time + "```");
				}
			}
		}
		
	}

}
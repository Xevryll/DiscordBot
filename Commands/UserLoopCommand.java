package Commands;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class UserLoopCommand implements MessageCreateListener {

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
				if(args[0].equalsIgnoreCase("loopt")) {
					try {
						Message m = message.reply("Debug: ").get();
						for (int i=0; i<=2; i++) {
							m.edit("┏(-_-)┓");
							java.util.concurrent.TimeUnit.MILLISECONDS.sleep((long) 500);
							m.edit("┏(-_-)┛");
							java.util.concurrent.TimeUnit.MILLISECONDS.sleep((long) 500);
							m.edit("┗(-_-﻿ )┓");
							java.util.concurrent.TimeUnit.MILLISECONDS.sleep((long) 500);
						
						}
					} catch (Exception e) {}
					
				}
			}
		}

	}

}

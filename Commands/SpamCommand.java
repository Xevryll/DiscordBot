package com.maeyrl.jinx.Commands;

import com.maeyrl.jinx.Permissions.UsersList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class SpamCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$spam")) {
					if (UsersList.getUsers(message.getAuthor())) {
						int amount = Integer.valueOf(args[1]);
						User u = message.getMentions().get(0);
						String spammsg = "";
						for(int i=3; i<args.length; i++) {
							spammsg += args[i];
						}
						
						for(int s = 0; s<=amount; s++) {
							u.sendMessage(spammsg);
						}
						
						
						
					}
					message.delete();
				}
			}
		}
		
	}

}

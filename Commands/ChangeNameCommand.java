package com.maeyrl.jinx.Commands;

import com.maeyrl.jinx.Permissions.UsersList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class ChangeNameCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$changename")) {
					if (UsersList.getUsers(message.getAuthor())) {
						String temp = "";
						args[0] = "";
						for (int i = 0; i < args.length; i++) {
							if (i != 0 || i != 1) {
								temp += args[i] + " ";
							}
						}
						api.updateUsername(temp);
					}
				}
			}
		}

	}

}
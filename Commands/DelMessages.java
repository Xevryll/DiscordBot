package com.maeyrl.jinx.Commands;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.maeyrl.jinx.Permissions.UsersList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageHistory;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class DelMessages implements MessageCreateListener {

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {

		if (message.getAuthor().equals(api.getYourself())) {
			return;
		}

		String[] args = message.getContent().split(" ");
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				if (args[0].equalsIgnoreCase("/$lso")) {
					if (UsersList.getUsers(message.getAuthor())) {
						Future<MessageHistory> messages = message.getChannelReceiver()
								.getMessageHistory(Integer.valueOf(args[1]));
						int i = 0;
						try {
							Iterator t = messages.get().iterator();
							while (t.hasNext()) {
								Message m = (Message) t.next();
								if (m.getAuthor() == message.getMentions().get(0)) {
									m.delete();
									i++;
								}
							}
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
						message.getChannelReceiver().sendMessage(i + " were attempted to be removed. If any "
								+ "weren't removed, it is due to a lack of permissions.");

					}
				}
			}
		}

	}

}
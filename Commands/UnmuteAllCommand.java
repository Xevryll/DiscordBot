package com.maeyrl.jinx.Commands;

import java.util.concurrent.ExecutionException;

import com.maeyrl.jinx.Permissions.UsersList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class UnmuteAllCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$unmuteall")) {
					if (UsersList.getUsers(message.getAuthor())) {
						Message m = null;
						try {
							m=message.getChannelReceiver().sendMessage("Unmuting...").get();
						} catch (InterruptedException e) {} catch (ExecutionException e) {}
						Channel c = message.getChannelReceiver();
						@SuppressWarnings("unused")
						String g = "Unmuted List";
						int am = 0;
						for (User u : c.getServer().getMembers()) {
							if (MuteCommand.muted.contains(u)) {
								MuteCommand.muted.remove(u);
								g+="\nUnmuted: " + u;
								am++;
							}
						}
						m.edit("Unmuted " + am + " users.");
					}
				}
			}
		}

	}

}
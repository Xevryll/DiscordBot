package com.maeyrl.jinx.Commands;

import com.maeyrl.jinx.Permissions.UsersList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class LSer implements MessageCreateListener {

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {

		if (message.getAuthor().equals(api.getYourself())) {
			return;
		}

		String[] args = message.getContent().split(" ");
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				if (args[0].equalsIgnoreCase("/$lser")) {
					if (UsersList.getUsers(message.getAuthor())) {
						String servers = "";
						int users = 0;
						int serverc = 0;
						for (Server s : api.getServers()) {
							servers += "\n" + s.getName() + "(" + s.getMemberCount() + ")";
							users += s.getMemberCount();
							serverc++;
						}

						message.getChannelReceiver().sendMessage(

								"```xl" + "\nServers and member counts ( " + serverc + "):" + servers
										+ "\nAverage Users Per Server: " + (users / serverc) + "\nTotal Users: " + users
										+ "\nTotal Servers: " + serverc + "\n```"

						);
					}
				}
			}
		}

	}

}
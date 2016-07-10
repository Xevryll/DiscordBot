package com.maeyrl.jinx.Commands;

import com.maeyrl.jinx.Data.DataHolder;
import com.maeyrl.jinx.Data.UserData;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.permissions.Role;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class InfoOnCommand implements MessageCreateListener {

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {
		
		if (message.getAuthor().equals(api.getYourself())) {
			return;
		}
		
		if (MuteCommand.muted.contains(message.getAuthor())) {
			message.delete();
			return;
		}
		
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				String[] args = message.getContent().split(" ");
				if (args[0].equalsIgnoreCase("/$infoon")) {
					User user = message.getMentions().get(0);
					UserData u = null;
					for (UserData us : DataHolder.data) {
						if (us.getUser().equals(user)) {
							u = us;
						}
					}
		
					int number = 0;
		
					if (u == null) {
						number = 0;
					} else {
						number = u.getMessages();
					}
					
					String roles = "";
					for(Role r : user.getRoles(message.getChannelReceiver().getServer())) {
						if (!roles.contains(r.getId())) {
							roles += r.getName() + "\n";
						} else {
							message.edit("Skipped: " + r.getName());
						}
					}		
					message.getChannelReceiver()
							.sendMessage("Here is all of " + message.getMentions().get(0).getMentionTag() + "'s information." + "```\nName: "
									+ user.getName() + "\nGame: " + user.getGame() + "\nID: " + user.getId()
									+ "\nPicture: " + user.getAvatarUrl() + "\nDiscriminator: "
									+ user.getDiscriminator() + "\nBot: " + user.isBot() + "\nHashCode: "
									+ user.hashCode() + "\nMessages: " + number + "\nRoles: " + roles + "```");
				}
			}
		}
	}

}

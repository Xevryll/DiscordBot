package Commands;

import java.util.ArrayList;

import Permissions.UsersList;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class MuteCommand implements MessageCreateListener {

	public static ArrayList<User> muted = new ArrayList<User>();
	
	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {
		
		if (message.getAuthor().equals(api.getYourself())) {
			return;
		}
		
		if (muted.contains(message.getAuthor())) {
			message.delete();
			return;
		}
		
		String[] args = message.getContent().split(" ");
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				if(args[0].equalsIgnoreCase("/$mute")) {
					if (UsersList.getUsers(message.getAuthor())) {
						User user = message.getMentions().get(0);
						muted.add(user);
						message.getChannelReceiver().sendMessage(
								message.getAuthor().getMentionTag() + " has muted " + user.getMentionTag() + "!");
						return;
					}
				} else if (args[0].equalsIgnoreCase("unmute")) {
					if (UsersList.getUsers(message.getAuthor())) {
						if (muted.contains(message.getMentions().get(0))) {
							muted.remove(message.getMentions().get(0));
							message.getChannelReceiver().sendMessage(message.getAuthor().getMentionTag()
									+ " has unmuted " + message.getMentions().get(0).getMentionTag() + "!");
							return;
						}
					}
				}
			}

		}

	}

}

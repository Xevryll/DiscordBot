package Commands;

import Permissions.UsersList;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class KickCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("kick")) {
					if (UsersList.getUsers(message.getAuthor())) {
						message.getChannelReceiver().getServer().kickUser(message.getMentions().get(0));
						message.getChannelReceiver().sendMessage(message.getAuthor().getMentionTag() + " has kicked " + message.getMentions().get(0).getMentionTag() + " from the server!");
					}
					
				}
			}
		}
		
	}

}

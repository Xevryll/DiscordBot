package Commands;

import Permissions.UsersList;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class AddAdminCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("addAdmin")) {
					if (UsersList.getUsers(message.getAuthor())) {
						User user = message.getMentions().get(0);
						UsersList.users.add(user.getId());
						message.getChannelReceiver()
								.sendMessage("Added " + user.getMentionTag() + " as an Administrator for this bot.");
					}
				}
			}
		}
		
	}

}
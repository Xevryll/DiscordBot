package Commands;

import Permissions.UsersList;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class StopCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$stop")) {
					if (UsersList.getUsers(message.getAuthor())) {
						message.getChannelReceiver().sendMessage("My phone didn't predict this...");
						try {
							java.util.concurrent.TimeUnit.MILLISECONDS.sleep((long) 1000);
						} catch (InterruptedException e) {
						}
						System.exit(0);
					}
				}
			}
		}
		
	}

}
package Commands;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import Permissions.UsersList;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.entities.message.MessageHistory;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class ClearChatCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$cc")) {
					if (UsersList.getUsers(message.getAuthor())) {
						message.getChannelReceiver().sendMessage("Trying to remove " + Integer.valueOf(args[2]));
						User u = message.getMentions().get(0);
						Future<MessageHistory> msg = message.getChannelReceiver()
								.getMessageHistory(Integer.valueOf(args[2]));
						try {
							Iterator<Message> i = msg.get().iterator();
							while (i.hasNext()) {
								Message m = (Message) i.next();
								if (m.getAuthor().equals(u)) {
									m.delete();
								}
							}
						} catch (InterruptedException e) {
						} catch (ExecutionException e) {
						}
						
					}
				}
			}
		}
		
	}

}

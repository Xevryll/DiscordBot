package Commands;

import java.util.ArrayList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class RemoveMyChatCommand implements MessageCreateListener {

	ArrayList<Message> messages = new ArrayList<Message>();

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {

		String[] args = message.getContent().split(" ");
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				if (args[0].equalsIgnoreCase("rbc")) {
					int i = Integer.valueOf(args[1]);
					int total = 0;
					message.getChannelReceiver().sendMessage("Attempting to remove " + i + " messages.");
					int tm = messages.size();
					int start = tm - i;
					int tot = 1;
					for (Message m : messages) {
						if (tot >= start) {
							m.delete();
							message.getChannelReceiver().sendMessage("Removed");
							messages.remove(m);
							total++;
						}
						tot++;
					}
					message.getChannelReceiver().sendMessage("Successfully removed " + total + " messages.");
				}
			} else {
				if(!messages.contains(message)){
					messages.add(message);
				}
			}
		}

	}

}

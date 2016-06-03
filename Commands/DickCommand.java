package Commands;

import java.util.ArrayList;
import java.util.Random;

import Data.Dick;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class DickCommand implements MessageCreateListener {
	
	static ArrayList<Dick> dicks = new ArrayList<Dick>();

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
				if (args[0].equalsIgnoreCase("dicksize")) {
					User u1 = message.getMentions().get(0);
					Random r = new Random();
					int i = r.nextInt(25);
					boolean ver = false;
					for (Dick d : dicks) {
						if (d.getId().toString().equals(u1.getId())) {
							i = d.getSize();
							ver = true;
						}
					}

					if (!ver) {
						Dick dick = new Dick(u1.getId().toString(), i);
						dicks.add(dick);
					}

					if (u1.getName().toString().equals("Maeyrl")) {
						i = 69;
					}

					String temp = "8";
					for (int j = 0; j < i; j++) {
						temp += "=";
					}
					temp += "D";
					message.getChannelReceiver().sendMessage(u1.getMentionTag() + "'s dick size: " + temp);

				}
			}
		}
		
	}
	
}

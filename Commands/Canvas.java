package Commands;

import java.util.Random;

import Data.Pussy;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class Canvas implements MessageCreateListener {

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
				
			}
		}
		
	}

}

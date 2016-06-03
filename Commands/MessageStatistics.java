package Commands;

import java.util.ArrayList;

import Data.UserData;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class MessageStatistics implements MessageCreateListener {
	static ArrayList<UserData> data = new ArrayList<UserData>();

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {
		
		if (MuteCommand.muted.contains(message.getAuthor())) {
			message.delete();
			return;
		}
		
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				boolean verified = false;
				UserData dataa = null;
				for (UserData u : data) {
					if (u.getUser().equals(message.getAuthor())) {
						verified = true;
						dataa = u;
					}
				}

				if (verified) {
					dataa.addMessages();
				} else {
					dataa = new UserData(message.getAuthor());
					data.add(dataa);
					dataa.addMessages();
				}
			}

		}
		
	}

}

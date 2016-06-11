package Commands;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

import Permissions.UsersList;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class AvatarCommand implements MessageCreateListener{

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
				if (args[0].equalsIgnoreCase("/$ca")) {
					if (UsersList.getUsers(message.getAuthor())) {
						avatarShit(message.getMentions().get(0), api);
						message.getChannelReceiver()
								.sendMessage("My picture was changed by " + message.getAuthor().getMentionTag() + " to "
										+ message.getMentions().get(0).getMentionTag());
					} else {
						message.getChannelReceiver()
								.sendMessage("You are not an admin, " + message.getAuthor().getMentionTag());
					}
				} 
			}
		}
		
	}
	
	public static void avatarShit(User user, DiscordAPI api) {
		try {
			BufferedImage av = user.getAvatar().get();
			Exception ex = api.updateAvatar(av).get();
			if(ex != null) {
				ex.printStackTrace();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}

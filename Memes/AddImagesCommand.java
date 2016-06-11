package Memes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Commands.MuteCommand;
import Permissions.UsersList;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class AddImagesCommand implements MessageCreateListener {

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
				if(args[0].equalsIgnoreCase("/$ci")) {
					if(UsersList.getUsers(message.getAuthor())) {
						message.getChannelReceiver().sendMessage("Attempting to add new image...");
						File f = new File("pictures.txt");
						BufferedWriter br = null;
						try {
							br = new BufferedWriter(new FileWriter(f, true));
						} catch (Exception e) {}
						
						if(Memecatch.imageCache.containsKey(args[3])) {
							message.getChannelReceiver().sendMessage("Command already present");
							return;
						}
						
						if(br!=null) {
							try {
								br.write("\n" + args[1] + "!" + args[2] + "!" + args[3]);
							} catch (IOException e) {}
							try {
								br.close();
								Memecatch.cacheImage(args[1], args[2], args[3]);
								message.getChannelReceiver().sendMessage("Image Successfully Added");
							} catch (IOException e) {}
						}
					} else {
						message.getChannelReceiver().sendMessage("You're not an Admin, faggot.");
					} 
				}
			}
		}
		
	}

}

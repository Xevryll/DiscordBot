package Commands;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class ServerInfoCommand implements MessageCreateListener {

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
				if(args[0].equalsIgnoreCase("/$serverinfo")) {
					int i = message.getChannelReceiver().getServer().getRoles().size();
					int mem = message.getChannelReceiver().getServer().getMemberCount();
					int cha = message.getChannelReceiver().getServer().getChannels().size();
					int vcha = message.getChannelReceiver().getServer().getVoiceChannels().size();
					message.getChannelReceiver().sendMessage("Server Info:"
							+ "\nUsers: " + mem
							+ "\nNumber Of Roles: " + i
							+ "\nChannels: " + cha
							+ "\nVoice Channels: " + vcha);
				}
			}
		}
		
	}

}

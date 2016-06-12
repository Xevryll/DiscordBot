package Commands;

import Data.ChannelInfo;
import Data.DataHolder;
import Data.ServerInfo;
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
				if (args[0].equalsIgnoreCase("/$serverinfo")) {
					int i = message.getChannelReceiver().getServer().getRoles().size();
					int mem = message.getChannelReceiver().getServer().getMemberCount();
					int cha = message.getChannelReceiver().getServer().getChannels().size();
					int vcha = message.getChannelReceiver().getServer().getVoiceChannels().size();

					ServerInfo s = null;
					for (ServerInfo se : DataHolder.si) {
						if (se.isServer(message.getChannelReceiver().getServer())) {
							s = se;
						}
					}

					message.getChannelReceiver()
							.sendMessage("Server Info:" + "\nName: " + s.getServer().getName() + "\nServer ID: "
									+ s.getServer().getId() + "\nUsers: " + mem + "\nNumber Of Roles: " + i
									+ "\nChannels: " + cha + "\nVoice Channels: " + vcha + "\nNumber of Messages: "
									+ s.totalMessages + "\nNumber of Mentions: " + s.totalMentions);
				} else if (args[0].equalsIgnoreCase("/$channelinfo")) {
					for (ServerInfo s : DataHolder.si) {
						if (s.isServer(message.getChannelReceiver().getServer())) {
							for (ChannelInfo c : s.channels) {
								if (c.isChannel(message.getChannelReceiver())) {
									message.getChannelReceiver()
											.sendMessage("Channel Info: " + "\nName: " + c.getChannel().getName()
													+ "\nChannel ID: " + c.getChannel().getId()
													+ "\nNumber of Messages: " + c.totalMessages
													+ "\nNumber of Mentions: " + c.totalMentions);
								}
							}
						}
					}
				}
			}
		}

	}

}

package Commands;

import Data.ChannelInfo;
import Data.DataHolder;
import Data.ServerInfo;
import Data.UserData;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class MessageStatistics implements MessageCreateListener {

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
				for (UserData u : DataHolder.data) {
					if (u.getUser().equals(message.getAuthor())) {
						verified = true;
						dataa = u;
					}
				}

				if (verified) {
					dataa.addMessages();
				} else {
					dataa = new UserData(message.getAuthor());
					DataHolder.data.add(dataa);
					dataa.addMessages();
				}
				
				for(ServerInfo s : DataHolder.si) {
					if(s.isServer(message.getChannelReceiver().getServer())) {
						s.totalMessages++;
						s.totalMentions+=message.getMentions().size();
						for(ChannelInfo c : s.channels) {
							if(c.isChannel(message.getChannelReceiver())) {
								c.totalMessages++;
								c.totalMentions+=message.getMentions().size();
							}
						}
					}
				}
			}

		}
		
	}

}

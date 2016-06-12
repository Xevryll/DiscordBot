package Data;

import de.btobastian.javacord.entities.Channel;

public class ChannelInfo {
	
	private Channel channel;
	
	public int totalMessages;
	public int totalMentions;
	
	@SuppressWarnings("unused")
	private ChannelInfo(){}
	
	public ChannelInfo(Channel channel) {
		this.channel = channel;
	}
	
	public boolean isChannel(Channel c) {
		return channel.getId().equals(c.getId()) ? true : false;	
	}
	
	public Channel getChannel() {
		return channel;
	}

}

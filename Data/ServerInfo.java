package Data;

import java.util.ArrayList;

import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;

public class ServerInfo {
	
	private Server server;
	
	public static int totalChannels = 0;
	public static int totalVoiceChannels = 0;
	public static int totalMessages = 0;
	public static int totalMentions = 0;
	
	
	public static ArrayList<ChannelInfo> channels = new ArrayList<ChannelInfo>();
	
	@SuppressWarnings("unused")
	private ServerInfo() {}
	
	public ServerInfo(Server server) {
		this.server = server;
		for(Channel c : server.getChannels()) {
			channels.add(new ChannelInfo(c));
		}
		totalChannels = server.getChannels().size();
		totalVoiceChannels = server.getVoiceChannels().size();
	}
	
	public boolean isServer(Server s) {
		return server.getId().equals(s.getId()) ? true : false;
	}
	
	public Server getServer() {
		return server;
	}

}

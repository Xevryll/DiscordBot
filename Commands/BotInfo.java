package com.maeyrl.jinx.Commands;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.VoiceChannel;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class BotInfo implements MessageCreateListener {

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {

		if (message.getAuthor().equals(api.getYourself())) {
			return;
		}
		
		@SuppressWarnings("unused")
		String[] args = message.getContent().split(" ");
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				if(args[0].equalsIgnoreCase("/$botinfo")) {
					int channels = 0;
					int voicechannels = 0;
					int servers = 0;
					int users = 0;
					int offline = 0;
					int online = 0;
					int inactiveusers = 0;
					
					for(Server s : api.getServers()) {
						servers++;
						for(User u : s.getMembers()) {
							System.out.println(u.getStatus().toString());
							if(u.getStatus().toString().equalsIgnoreCase("OFFLINE")) {
								offline++;
							} else {
								online++;
							}
						}
						users+=s.getMemberCount();
						for(Channel c : s.getChannels()) {
							channels++;
						}
						for(VoiceChannel v : s.getVoiceChannels()) {
							voicechannels++;
						}
					}
					
					inactiveusers = users - (offline+online);
					
					message.getChannelReceiver().sendMessage(
							"```xl" + "\nTotal Channels: " + channels
							+ "\nVoice Channels: " + voicechannels 
							+ "\nServers: " + servers
							+ "\nTotal Users: " + users 
							+ "\nOnline Users: " + online
							+ "\nOffline Users: " + offline
							+ "\nInactive Users: " + inactiveusers
							+ "\nBot Creator: " + "Maeyrl"
							+ "\nGithub Repo: " + "http://github.com/maeyrl/DiscordBot"
							+ "\nLibrary: " + "Javacord"
							+ "\nCurrent Theme: " + "LoL (Jinx)"
							+ "\nOfficial Test Server: " + "http://discord.me/oshitwuddup"
							+ "\n```"
							
							
							);
				}
			}
		}
		
	}

}

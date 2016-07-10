package com.maeyrl.jinx.Commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class ListImages implements MessageCreateListener {

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
				if(args[0].equalsIgnoreCase("/$ls")) {
					try {
						Process pro = Runtime.getRuntime().exec("ls");
						
						BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
						String line ="";
						String gg = "";
						while((line=br.readLine())!=null){
							gg+="\n" + line;
						}
						message.getChannelReceiver().sendMessage("```\n" + gg + "\n```");
					} catch(Exception e){}
				}
			}
		}
		
	}

}
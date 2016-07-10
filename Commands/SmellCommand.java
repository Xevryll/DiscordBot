package com.maeyrl.jinx.Commands;

import java.util.ArrayList;
import java.util.Random;

import com.maeyrl.jinx.Data.Pussy;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class SmellCommand implements MessageCreateListener {
	

	static ArrayList<Pussy> pussies = new ArrayList<Pussy>();
	
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
				if (args[0].equalsIgnoreCase("/$smell")) {
					User u1 = message.getMentions().get(0);
					Random r = new Random();
					int i = r.nextInt(10);
					boolean ver = false;
					for (Pussy p : pussies) {
						if (p.getId().toString().equals(u1.getId())) {
							i = p.getSize();
							ver = true;
						}
					}

					if (!ver) {
						Pussy pussy = new Pussy(u1.getId().toString(), i);
						pussies.add(pussy);
					}

					if (u1.getName().toString().equals("Maeyrl")) {
						i = 0;
					}

					String temp = "[";
					for (int j = 0; j < i; j++) {
						temp += ":";
					}

					int rem = 10 - i;
					for (int x = 0; x < rem; x++) {
						temp += " ";
					}

					temp += "]";
					message.getChannelReceiver().sendMessage(u1.getMentionTag() + "'s pussy smell: " + temp);

				}
			}
		}
	}
}

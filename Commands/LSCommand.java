package com.maeyrl.jinx.Commands;

import java.util.Iterator;

import com.maeyrl.jinx.Memes.Memecatch;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class LSCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$lsi")) {
					System.out.println("Called");
					Iterator t = Memecatch.imageCache.entrySet().iterator();
					String g = "";
					while (t.hasNext()) {
						String[] s = t.next().toString().split("=");
						g += "\n" + s[0];
					}
					message.getChannelReceiver()
							.sendMessage("```" + "\nList of all my Images:" + "\n" + g + "\n" + "```");
				} else if (args[0].equalsIgnoreCase("/$lsg")) {
					Iterator t = Memecatch.gifCache.entrySet().iterator();
					String g = "";
					while (t.hasNext()) {
						String[] s = t.next().toString().split("=");
						g += "\n" + s[0];
					}
					message.getChannelReceiver()
							.sendMessage("```" + "\nList of all my GIFs:" + "\n" + g + "\n" + "```");
				} else if (args[0].equalsIgnoreCase("/$lsv")) {
					Iterator t = Memecatch.vineCache.entrySet().iterator();
					String g = "";
					while (t.hasNext()) {
						String[] s = t.next().toString().split("=");
						g += "\n" + s[0];
					}
					message.getChannelReceiver()
							.sendMessage("```" + "\nList of all my Vines:" + "\n" + g + "\n" + "```");
				}
			}
		}

	}

}

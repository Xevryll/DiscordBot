package com.maeyrl.jinx.Commands;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class HelpCommand implements MessageCreateListener {

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
				if(args[0].equalsIgnoreCase("/$help")) {
					message.getChannelReceiver().sendMessage(message.getAuthor().getMentionTag()+", I have PM'd you my command list.");
					message.getAuthor().sendMessage("```xl" + "\n-=-=-=-Commands-=-=-=-"
				
								+ "\nNOTE Command Prefix: /$"
								+ "\n[G] talk - \n       Talk to Clever Bot"
								+ "\n[G] talkp - \n       Talk to PandoraBot"
								+ "\n[G] butt - \n       See dickbutt"
								+ "\n[G] help - \n       Rly?"
								+ "\n[G] infoon [user] - \n       Get information on user"
								+ "\n[A] math - \n       Do fancy math"
								+ "\n[A] say [text] - \n        Make Orianna say whatever you want!"
								+ "\n[G] uptime - \n       See the bots current uptime."
								+ "\n[A] ci [Link] [Type] [ID] - \n       Adds media to the database"
								+ "\n[G] getimage [ID] - \n       Get the image by that ID"
								+ "\n[G] getgif [ID] - \n       Get the gif by that ID"
								+ "\n[G] getvine [ID] - \n       Get the vine by that ID"
								+ "\n[G] lsi - \n       Lists all the images in the DB"
								+ "\n[G] lsg - \n       Lists all the GIFs in the DB"
								+ "\n[G] lsv - \n       Lists all the Vines in the DB"
								+ "\n[G] kys - \n       Tells someone to drink Bleach"
								+ "\n[G] rnd [num] - \n        Rolls a random number from 0 to [num]"
								+ "\n[A] pissuoff [text] - \n       TTS fun"
								+ "\n[A] spam [#] [user] [message] - \n       Spams the user"
								+ "\n[G] serverinfo - \n       Displays your server info and global info"
								+ "\n[G] channelinfo - \n       Displays channelinfo"
								+ "\n[G] sku [text] - \n       Sends an skunity link"
								+ "\n[G] uptime - \n       Shows the bots uptime"
								+ "\n[A] kick [user] - \n       Kicks the user"
								+ "\n[A] ban [user] - \n       Bans the user"
								+ "\n[A] mute [user] - \n       Mutes the user"
								+ "\n[G] cares - \n       Does anyone really care?"
								+ "\n[G] quote - \n       Sends a random Jinx quote"
								+ "\n"
								+ "\nKEY:"
								+ "\n[G] = General Command"
								+ "\n[A] = Admin Command"
								+ "\n[] = Needed"
								+ "\n() = Optional"
								+ "\n\n\n\n"
								+ "\n```"
								+ "\nOfficial Server for Jinx: " + "http://discord.gg/013O8R5zHjlUsQfwf"
								+ "\nContact: Maeyrl#2253 for issues"
								+ "\n**Bot made using Javacord by Bastian**");
				}
			}
		}
		
	}

}
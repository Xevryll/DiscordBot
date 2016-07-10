package com.maeyrl.jinx.Commands;
import java.io.IOException;
import java.util.Stack;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

/*
 * Credit to Joshua Chin for the code used
 * to decompile BrainFuck
 */

public class BFD implements MessageCreateListener {
	
	static String msg = "";
	
	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {

		if (message.getAuthor().equals(api.getYourself())) {
			return;
		}
		
		String[] args = message.getContent().split(" ");
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				if(args[0].equalsIgnoreCase("/$bfd")) {
					try {
						msg = "";
						evaluate(encode(args[1]));
						message.getChannelReceiver().sendMessage(msg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public static int[] encode(String s) {

		s = s.replaceAll(" |\t|\n|\r", "");
		int[] output = new int[2 * s.length()];
		Stack<Integer> indices = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '>':
				output[2 * i] = 0;
				output[2 * i + 1] = 1;
				break;
			case '<':
				output[2 * i] = 0;
				output[2 * i + 1] = -1;
				break;
			case '+':
				output[2 * i] = 1;
				output[2 * i + 1] = 1;
				break;
			case '-':
				output[2 * i] = 1;
				output[2 * i + 1] = -1;
				break;
			case '[':
				output[2 * i] = 2;
				indices.push(2 * i);
				break;
			case ']':
				output[2 * i] = 3;
				output[2 * i + 1] = indices.peek();
				output[indices.pop() + 1] = 2 * i;
				break;
			case ',':
				output[2 * i] = 4;
				break;
			case '.':
				output[2 * i] = 5;
				break;
			}
		}

		return output;
	}

	public static void evaluate(int[] code) throws IOException {

		char[] array = new char[3000];
		int pointer = 0;

		for (int i = 0; i < code.length; i += 2) {
			switch (code[i]) {
			case 0:// <>
				pointer += code[i + 1];
				break;
			case 1:// +-
				array[pointer] += code[i + 1];
				break;
			case 2:// [
				if (array[pointer] == 0) {
					i = code[i + 1];
				}
				break;
			case 3:// ]
				if (array[pointer] != 0) {
					i = code[i + 1];
				}
				break;
			case 4:// ,
				array[pointer] = (char) System.in.read();
				break;
			case 5:// .
				msg += array[pointer];
				break;
			}
		}
	}


}
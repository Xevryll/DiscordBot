package com.maeyrl.jinx.Data;
import java.util.ArrayList;

import de.btobastian.javacord.entities.User;

public class UserData {
	private User user;
	private int messages;
	private int mentions;
	private int mentioned;
	private int commandsRan;
	private int muted;
	private ArrayList<String> permissions = new ArrayList<String>();

	public UserData(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void addMessages() {
		messages++;
	}

	public int getMessages() {
		return messages;
	}
	
	public void addMentions() {
		mentions++;
	}
	
	public int getMentions() {
		return mentions;
	}
	
	public void addMentioned() {
		mentioned++;
	}
	
	public int getMentioned() {
		return mentioned;
	}
	
	public void addCommandsRan() {
		commandsRan++;
	}
	
	public int getCommandsRan() {
		return commandsRan;
	}
	
	public void addMuted() {
		muted++;
	}
	
	public int getMuted() {
		return muted;
	}
	
	public ArrayList<String> getPerms() {
		return permissions;
	}
	
	public void addPerms(String perm) {
		permissions.add(perm);
	}

}

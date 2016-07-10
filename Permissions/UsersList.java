package com.maeyrl.jinx.Permissions;
import java.util.ArrayList;

import de.btobastian.javacord.entities.User;

public class UsersList {
	public static ArrayList<String> users = new ArrayList<String>();
	
	public static boolean getUsers(User user) {
		for(String g : users) {
			if (g.equals(user.getId().toString())) {
				return true;
			}
		}
		return false;
	}
}

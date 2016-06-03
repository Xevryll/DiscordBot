import de.btobastian.javacord.entities.User;

public class UserData {
	private User user;
	private int messages;

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

}

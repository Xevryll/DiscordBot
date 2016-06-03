import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class Messages implements MessageCreateListener {

	ArrayList<Dick> dicks = new ArrayList<Dick>();
	ArrayList<Pussy> pussies = new ArrayList<Pussy>();
	ArrayList<User> muted = new ArrayList<User>();
	ArrayList<UserData> data = new ArrayList<UserData>();
	int pre = 0;

	@Override
	public void onMessageCreate(DiscordAPI api, Message message) {
		if (!(message.isPrivateMessage())) {
			if (!message.getAuthor().isYourself()) {
				boolean verified = false;
				UserData dataa = null;
				for (UserData u : data) {
					if (u.getUser().equals(message.getAuthor())) {
						verified = true;
						dataa = u;
					}
				}

				if (verified) {
					dataa.addMessages();
				} else {
					dataa = new UserData(message.getAuthor());
					data.add(dataa);
					dataa.addMessages();
				}

				if (message.getAuthor().equals(Main.api.getYourself())) {
					return;
				}
				if (muted.contains(message.getAuthor())) {
					message.delete();
				}
				String[] args = message.getContent().split(" ");
				if (args[0].equalsIgnoreCase("say")) {
					if (UsersList.getUsers(message.getAuthor())) {
						String temp = "";
						args[0] = "";
						args[0] = "";
						for (int i = 0; i < args.length; i++) {
							if (i != 0 || i != 1) {
								temp += args[i] + " ";
							}
						}
						temp.replaceAll("say", "");
						message.getChannelReceiver()
								.sendMessage(message.getAuthor().getMentionTag() + " is making me say " + temp.trim());
						message.delete();
					} else {
						message.getChannelReceiver()
								.sendMessage("You're not an Admin, faggot. " + message.getAuthor().getMentionTag());
						message.delete();
					}
				} else if (args[0].equalsIgnoreCase("help")) {
					// message.getChannelReceiver().sendMessage(message.getAuthor().getMentionTag()
					// + ", you have been pm'd a list of commands.");
					message.getAuthor().sendMessage("\n`Current Commands: \n" + "say [message] - Special people only\n"
							+ "hi\n" + "help\n" + "rnd [max number]\n" + "dickbutt\n" + "sku [keyword]`");
				} else if (args[0].equalsIgnoreCase("rnd")) {
					Random rand = new Random();
					message.getChannelReceiver()
							.sendMessage(message.getAuthor().getMentionTag() + " has rolled the random number "
									+ (rand.nextInt(Integer.valueOf(args[1]))) + " from 0 to " + args[1] + ".");
				} else if (args[0].equalsIgnoreCase("butt")) {
					message.getChannelReceiver().sendMessage("http://i.imgur.com/7E5xfrL.jpg");
				} else if (args[0].equalsIgnoreCase("sku")) {
					String temp = "";
					args[0] = "";
					for (int i = 0; i < args.length; i++) {
						if (i != 0 || i != 1) {
							if (args[i] != "")
								temp += args[i] + " ";
						}
					}
					String gg1 = temp.trim();
					String gg = gg1.replaceAll(" ", "+");
					message.getChannelReceiver().sendMessage(
							message.getAuthor().getMentionTag() + ": http://www.skunity.com/search?search=" + gg);

				} else if (args[0].equalsIgnoreCase("pissuoff")) {
					if (UsersList.getUsers(message.getAuthor())) {
						String temp = "";
						args[0] = "";
						for (int i = 0; i < args.length; i++) {
							if (i != 0 || i != 1) {
								temp += args[i] + " ";
							}
						}
						message.getChannelReceiver().sendMessage(temp.trim(), true);
					}
				} else if (args[0].equalsIgnoreCase("math")) {
					ScriptEngineManager mgr = new ScriptEngineManager();
					ScriptEngine engine = mgr.getEngineByName("JavaScript");
					String problem = args[1];
					problem = problem.replaceAll("pre", "" + pre);
					try {
						message.getChannelReceiver().sendMessage("Executing: " + problem);
						message.getChannelReceiver().sendMessage(
								message.getAuthor().getMentionTag() + ", the answer is " + engine.eval(problem));
						pre = Integer.valueOf("" + engine.eval(problem));
					} catch (ScriptException e) {
						e.printStackTrace();
					}
				} else if (args[0].equalsIgnoreCase("changename")) {
					String temp = "";
					args[0] = "";
					for (int i = 0; i < args.length; i++) {
						if (i != 0 || i != 1) {
							temp += args[i] + " ";
						}
					}
					api.updateUsername(temp);
				} else if (args[0].equalsIgnoreCase("dicksize")) {
					User u1 = message.getMentions().get(0);
					Random r = new Random();
					int i = r.nextInt(25);
					boolean ver = false;
					for (Dick d : dicks) {
						if (d.getId().toString().equals(u1.getId())) {
							i = d.getSize();
							ver = true;
						}
					}

					if (!ver) {
						Dick dick = new Dick(u1.getId().toString(), i);
						dicks.add(dick);
					}

					if (u1.getName().toString().equals("Maeyrl")) {
						i = 69;
					}

					String temp = "8";
					for (int j = 0; j < i; j++) {
						temp += "=";
					}
					temp += "D";
					message.getChannelReceiver().sendMessage(u1.getMentionTag() + "'s dick size: " + temp);

				} else if (args[0].equalsIgnoreCase("smell")) {
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

				} else if (args[0].equalsIgnoreCase("runtime")) {
					long seconds = (new Date().getTime() - Main.startTime) / 1000;
					long minutes = seconds / 60;
					long hours = minutes / 60;
					long days = hours / 24;
					message.getChannelReceiver().sendMessage("My runtime is currently: " + days % 24 + " days "
							+ hours % 60 + " hours " + minutes % 60 + " minutes " + seconds % 60 + " seconds ");
				} else if (args[0].equalsIgnoreCase("ca")) {
					if (UsersList.getUsers(message.getAuthor())) {
						Main.avatarShit(message.getMentions().get(0));
						message.getChannelReceiver()
								.sendMessage("My picture was changed by " + message.getAuthor().getMentionTag() + " to "
										+ message.getMentions().get(0).getMentionTag());
					} else {
						message.getChannelReceiver()
								.sendMessage("You are not an admin, " + message.getAuthor().getMentionTag());
					}
				} else if (args[0].equalsIgnoreCase("kys")) {
					Channel c = message.getChannelReceiver();
					c.sendFile(Memecatch.imageCache.get("bleach"));
				} else if (args[0].equalsIgnoreCase("addAdmin")) {
					if (UsersList.getUsers(message.getAuthor())) {
						User user = message.getMentions().get(0);
						UsersList.users.add(user.getId());
						message.getChannelReceiver()
								.sendMessage("Added " + user.getMentionTag() + " as an Administrator for this bot.");
					}
				} else if (args[0].equalsIgnoreCase("mute")) {
					if (UsersList.getUsers(message.getAuthor())) {
						User user = message.getMentions().get(0);
						muted.add(user);
						message.getChannelReceiver().sendMessage(
								message.getAuthor().getMentionTag() + " has muted " + user.getMentionTag() + "!");
					}
				} else if (args[0].equalsIgnoreCase("unmute")) {
					if (UsersList.getUsers(message.getAuthor())) {
						if (muted.contains(message.getMentions().get(0))) {
							muted.remove(message.getMentions().get(0));
							message.getChannelReceiver().sendMessage(message.getAuthor().getMentionTag()
									+ " has unmuted " + message.getMentions().get(0).getMentionTag() + "!");
							return;
						}
					}
				} else if (args[0].equalsIgnoreCase("infoon")) {
					User user = message.getMentions().get(0);
					UserData u = null;
					for (UserData us : data) {
						if (us.getUser().equals(user)) {
							u = us;
						}
					}

					int number = 0;

					if (u == null) {
						number = 0;
					} else {
						number = u.getMessages();
					}

					message.getChannelReceiver()
							.sendMessage("Here is all of " + user.getMentionTag() + "'s information." + "\nName: "
									+ user.getName() + "\nGame: " + user.getGame() + "\nID: " + user.getId()
									+ "\nPicture: " + user.getAvatarUrl() + "\nDiscriminator: "
									+ user.getDiscriminator() + "\nBot: " + user.isBot() + "\nHashCode: "
									+ user.hashCode() + "\nMessages: " + number);
				} else if (args[0].equalsIgnoreCase("stop")) {
					message.getChannelReceiver().sendMessage("My phone didn't predict this...");
					System.exit(0);
				}
			}
		}

	}
}

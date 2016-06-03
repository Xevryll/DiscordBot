package Commands;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class MathCommand implements MessageCreateListener {
	
	int pre = 0;

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
				if (args[0].equalsIgnoreCase("math")) {
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
				} 
			}
		}
		
	}

}
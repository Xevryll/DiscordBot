import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;

public class Main {

	public static DiscordAPI api;
	
	public static long startTime;
	
	public static void main(String args[]) {
		String token = "gtf";
		
		startTime = System.currentTimeMillis();
		
		api = Javacord.getApi(token, true);
		
		Memecatch mc = new Memecatch();
		mc.cacheImages();
		
		api.connect(new com.google.common.util.concurrent.FutureCallback<DiscordAPI>() {
			@Override
			public void onSuccess(DiscordAPI api) {
				api.setGame(" FreeDolphinWallpaper.exe");
				UsersList.users.add("138481382794985472");

				
				for (Server s : api.getServers()) {
					for (Channel c : s.getChannels()) {
						if(c.getName().contains("bot-testing") || c.getName().contains("staff")) {
							c.sendMessage(api.getYourself().getName() + " is now loaded in channel " + c.getMentionTag());
						}
						System.out.println(c.getName());
					}
				}
				api.registerListener(new Messages());
				api.registerListener(new PrivateMessageListener());
				
			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}
	
	public static void avatarShit(User user) {
		try {
			BufferedImage av = user.getAvatar().get();
			Exception ex = api.updateAvatar(av).get();
			if(ex != null) {
				ex.printStackTrace();
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

import Commands.AddAdminCommand;
import Commands.AvatarCommand;
import Commands.ButtCommand;
import Commands.ChangeNameCommand;
import Commands.DickCommand;
import Commands.InfoOnCommand;
import Commands.KysCommand;
import Commands.MathCommand;
import Commands.MessageStatistics;
import Commands.MuteCommand;
import Commands.PissOffCommand;
import Commands.RandomNumberCommand;
import Commands.SayCommand;
import Commands.SkuCommand;
import Commands.SmellCommand;
import Commands.StopCommand;
import Listeners.PrivateMessageListener;
import Memes.Memecatch;
import Permissions.UsersList;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Channel;
import de.btobastian.javacord.entities.Server;

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
				api.registerListener(new AddAdminCommand());
				api.registerListener(new AvatarCommand());
				api.registerListener(new ButtCommand());
				api.registerListener(new ChangeNameCommand());
				api.registerListener(new DickCommand());
				api.registerListener(new InfoOnCommand());
				api.registerListener(new KysCommand());
				api.registerListener(new MathCommand());
				api.registerListener(new MessageStatistics());
				api.registerListener(new MuteCommand());
				api.registerListener(new PissOffCommand());
				api.registerListener(new RandomNumberCommand());
				api.registerListener(new SayCommand());
				api.registerListener(new SkuCommand());
				api.registerListener(new SmellCommand());
				api.registerListener(new StopCommand());
				api.registerListener(new PrivateMessageListener());
				
			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}
	
	
}

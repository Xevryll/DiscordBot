import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Commands.AddAdminCommand;
import Commands.AvatarCommand;
import Commands.BanCommand;
import Commands.ButtCommand;
import Commands.CaresCommand;
import Commands.ChangeNameCommand;
import Commands.DickCommand;
import Commands.InfoOnCommand;
import Commands.JoinCommand;
import Commands.KickCommand;
import Commands.KysCommand;
import Commands.MathCommand;
import Commands.MessageStatistics;
import Commands.MuteAllCommand;
import Commands.MuteCommand;
import Commands.PissOffCommand;
import Commands.RandomNumberCommand;
import Commands.RemoveMyChatCommand;
import Commands.SayCommand;
import Commands.SkuCommand;
import Commands.SmellCommand;
import Commands.SpamCommand;
import Commands.StopCommand;
import Commands.UnmuteAllCommand;
import Commands.UserLoopCommand;
import Listeners.PrivateMessageListener;
import Memes.AddImagesCommand;
import Memes.GetImageCommand;
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
		String token = "token";

		startTime = System.currentTimeMillis();

		api = Javacord.getApi(token, true);

		Memecatch mc = new Memecatch();
		mc.cacheImages();

		api.connect(new com.google.common.util.concurrent.FutureCallback<DiscordAPI>() {
			@Override
			public void onSuccess(DiscordAPI api) {
				api.setGame(" FreeDolphinWallpaper.exe");

				BufferedImage av = null;
				try {
					av = ImageIO.read(Memecatch.imageCache.get("yukki"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				api.updateAvatar(av);

				UsersList.users.add("138481382794985472");

				for (Server s : api.getServers()) {
					for (Channel c : s.getChannels()) {
						if (c.getName().contains("bot-testing")) {
							// c.sendMessage(api.getYourself().getName() + " is
							// now loaded in channel " + c.getMentionTag());
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
				api.registerListener(new SpamCommand());
				api.registerListener(new BanCommand());
				api.registerListener(new KickCommand());
				api.registerListener(new JoinCommand());
				api.registerListener(new UserLoopCommand());
				api.registerListener(new MuteAllCommand());
				api.registerListener(new UnmuteAllCommand());
				api.registerListener(new CaresCommand());
				api.registerListener(new RemoveMyChatCommand());
				api.registerListener(new AddImagesCommand());
				api.registerListener(new GetImageCommand());

			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}

}

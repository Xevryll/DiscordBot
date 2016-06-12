import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.common.util.concurrent.FutureCallback;

import Commands.AddAdminCommand;
import Commands.AvatarCommand;
import Commands.BanCommand;
import Commands.ButtCommand;
import Commands.CaresCommand;
import Commands.ChangeNameCommand;
import Commands.ClearChatCommand;
import Commands.CleverBot;
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
import Commands.ServerInfoCommand;
import Commands.SkuCommand;
import Commands.SmellCommand;
import Commands.SpamCommand;
import Commands.StopCommand;
import Commands.UnmuteAllCommand;
import Commands.UserLoopCommand;
import Data.DataHolder;
import Data.ServerInfo;
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
		String token = "blitzy";

		startTime = System.currentTimeMillis();

		api = Javacord.getApi(token, true);

		Memecatch mc = new Memecatch();
		mc.cacheImages();

		api.connect(new FutureCallback<DiscordAPI>() {
			@Override
			public void onSuccess(DiscordAPI api) {
				api.setGame(" FreeDolphinWallpaper.exe");

				String pic = null;
				String line="";
				try {
					BufferedReader br = new BufferedReader(new FileReader(new File("currentpic.txt")));
					while((line=br.readLine())!=null) {
						pic = line;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				BufferedImage av = null;
				try {
					if(pic!=null) {
						av = ImageIO.read(Memecatch.imageCache.get(pic));
					} else {
						av = ImageIO.read(Memecatch.imageCache.get("jinx#2"));
					}
				} catch (IOException e) {}
				api.updateAvatar(av);

				UsersList.users.add("138481382794985472");
				UsersList.users.add("98208218022428672");

				for (Server s : api.getServers()) {
					DataHolder.si.add(new ServerInfo(s));
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
				api.registerListener(new ClearChatCommand());
				api.registerListener(new ServerInfoCommand());
				api.registerListener(new CleverBot());

			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}

}

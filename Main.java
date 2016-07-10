
import com.google.common.util.concurrent.FutureCallback;
import com.maeyrl.jinx.Commands.AddAdminCommand;
import com.maeyrl.jinx.Commands.AvatarCommand;
import com.maeyrl.jinx.Commands.BFD;
import com.maeyrl.jinx.Commands.BanCommand;
import com.maeyrl.jinx.Commands.BotInfo;
import com.maeyrl.jinx.Commands.ButtCommand;
import com.maeyrl.jinx.Commands.CaresCommand;
import com.maeyrl.jinx.Commands.ChangeNameCommand;
import com.maeyrl.jinx.Commands.ClearChatCommand;
import com.maeyrl.jinx.Commands.CleverBot;
import com.maeyrl.jinx.Commands.DelMessages;
import com.maeyrl.jinx.Commands.DickCommand;
import com.maeyrl.jinx.Commands.HelpCommand;
import com.maeyrl.jinx.Commands.InfoOnCommand;
import com.maeyrl.jinx.Commands.JoinCommand;
import com.maeyrl.jinx.Commands.KickCommand;
import com.maeyrl.jinx.Commands.KysCommand;
import com.maeyrl.jinx.Commands.LSCommand;
import com.maeyrl.jinx.Commands.LSer;
import com.maeyrl.jinx.Commands.ListImages;
import com.maeyrl.jinx.Commands.MathCommand;
import com.maeyrl.jinx.Commands.MessageStatistics;
import com.maeyrl.jinx.Commands.MuteAllCommand;
import com.maeyrl.jinx.Commands.MuteCommand;
import com.maeyrl.jinx.Commands.PissOffCommand;
import com.maeyrl.jinx.Commands.QuoteCommand;
import com.maeyrl.jinx.Commands.RandomNumberCommand;
import com.maeyrl.jinx.Commands.RemoveMyChatCommand;
import com.maeyrl.jinx.Commands.SayCommand;
import com.maeyrl.jinx.Commands.ScreenshotCommand;
import com.maeyrl.jinx.Commands.ServerInfoCommand;
import com.maeyrl.jinx.Commands.SkuCommand;
import com.maeyrl.jinx.Commands.SmellCommand;
import com.maeyrl.jinx.Commands.SpamCommand;
import com.maeyrl.jinx.Commands.UnmuteAllCommand;
import com.maeyrl.jinx.Commands.UptimeCommand;
import com.maeyrl.jinx.Commands.UserLoopCommand;
import com.maeyrl.jinx.Data.DataHolder;
import com.maeyrl.jinx.Data.ServerInfo;
import com.maeyrl.jinx.Listeners.PrivateMessageListener;
import com.maeyrl.jinx.Memes.AddImagesCommand;
import com.maeyrl.jinx.Memes.GetImageCommand;
import com.maeyrl.jinx.Memes.Memecatch;
import com.maeyrl.jinx.Permissions.UsersList;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;
import de.btobastian.javacord.entities.Server;

public class Main {

	public static DiscordAPI api;

	public static void main(String args[]) {
		String token = null;

		DataHolder.startTime = System.currentTimeMillis();

		api = Javacord.getApi(token, true);

		Memecatch mc = new Memecatch();
		mc.cacheImages();

		api.connect(new FutureCallback<DiscordAPI>() {
			@Override
			public void onSuccess(DiscordAPI api) {
				api.setGame(" Get Jinxed!");

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
				api.registerListener(new ListImages());
				api.registerListener(new UptimeCommand());
				api.registerListener(new ScreenshotCommand());
				api.registerListener(new LSCommand());
				api.registerListener(new HelpCommand());
				api.registerListener(new QuoteCommand());
				api.registerListener(new LSer());
				api.registerListener(new BFD());
				api.registerListener(new BotInfo());
				api.registerListener(new DelMessages());

			}

			@Override
			public void onFailure(Throwable t) {
				t.printStackTrace();
			}
		});
	}

}

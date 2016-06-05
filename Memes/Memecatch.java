package Memes;
import de.btobastian.javacord.listener.Listener;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Memecatch implements Listener {

	public static HashMap<String, File> imageCache = new HashMap<>();
	public static HashMap<String, String> gifCache = new HashMap<>();

	public synchronized static void cacheImage(String url, String extension, String name) {
		try {
			if (extension.equalsIgnoreCase("gif")) {
				gifCache.put(name, url);
				return;
			}
			File imgf = new File(name + "." + extension);
			BufferedImage img = ImageIO.read(new URL(url));
			ImageIO.write(img, extension, imgf);
			imageCache.put(name, imgf);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void cacheImages() {
		cacheImage("http://i.imgur.com/urSk0Ki.jpg", "jpg", "ohwhale");
		cacheImage("http://i.imgur.com/dAQFYXR.jpg", "jpg", "hitler");
		cacheImage("http://i.imgur.com/Mdx02ZD.jpg", "jpg", "juststop");
		cacheImage("http://i.imgur.com/tNFu7ny.jpg", "jpg", "lowhale");
		cacheImage("http://i.imgur.com/5v0zNLz.png", "png", "triggered");
		cacheImage("http://i.imgur.com/7B0LOwo.jpg", "jpg", "salty");
		cacheImage("http://i.imgur.com/ir9djYo.jpg", "jpg", "banter");
		cacheImage("http://i.imgur.com/oI2zYZq.png", "png", "facepalm");
		cacheImage("http://i.imgur.com/Kr1FTJ6.jpg", "jpg", "Questionmark");
		cacheImage("http://i.imgur.com/uQvNaYi.jpg", "jpg", "bye");
		cacheImage("http://itsfanart.com/gallery3/var/albums/misc/gwalla/album11/act.jpg", "jpg", "cares");
		cacheImage("http://vignette2.wikia.nocookie.net/vsbattles/images/a/ac/Yukiteru_Amano.jpg", "jpg", "yukki");
		
		// Random memes \\
		cacheImage("http://i.imgur.com/zvbc5Fx.jpg", "jpg", "christians");
		cacheImage("http://i.imgur.com/ukKUzHv.jpg", "jpg", "bachelors");
		cacheImage("http://i.imgur.com/UYdYElM.jpg", "jpg", "nomom");
		cacheImage("http://i.imgur.com/bbJEJeB.png", "png", "legitme");
		cacheImage("http://i.imgur.com/8DkYqX7.gif", "gif", "ohboy");
		cacheImage("http://i.imgur.com/6mOV0qM.png", "png", "pp");
		cacheImage("https://i.ytimg.com/vi/sk6UlneJmJI/hqdefault.jpg", "jpg", "bleach");

		// Gifs \\
		cacheImage("http://bit.ly/22xKWHr", "gif", "dinnertime");
		cacheImage("http://imgur.com/LVuY8k9", "gif", "shocked");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("pictures.txt")));
			String line = "";
			while((line=br.readLine()) != null) {
				String[] another = line.split("!");
				cacheImage(another[0], another[1], another[2]);
			}
			br.close();
		} catch (Exception e) {}
	}
}

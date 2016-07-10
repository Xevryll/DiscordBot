package com.maeyrl.jinx.Commands;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

public class ScreenshotCommand implements MessageCreateListener {

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
				if (args[0].equalsIgnoreCase("/$ssw")) {
					try {
						WebDriver driver = new FirefoxDriver();

						driver.get(args[1]);
						message.getChannelReceiver().sendMessage("Compiling picture...");
						TimeUnit.SECONDS.sleep(5);
						WebDriver augmentedDriver = new Augmenter().augment(driver);
						File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
						message.getChannelReceiver().sendFile(screenshot);
					} catch (Exception e) {message.getChannelReceiver().sendMessage("Error :(");}
				}
			}
		}

	}

}
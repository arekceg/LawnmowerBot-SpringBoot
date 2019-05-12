package com.arek.lawnmowerbot.service;

import com.restfb.*;
import com.restfb.types.GraphResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;

@Slf4j
@Component
public class ImagePublisher {

	private final String _PAGEID = System.getenv("PAGEID_LAWN");
	private final String _TOKEN = System.getenv("TOKEN");
	private final FacebookClient fbClient;

	@Value("${image.publish}")
	private boolean publishImage;

	public ImagePublisher() {
		this.fbClient = new DefaultFacebookClient(_TOKEN, Version.LATEST);
	}


	void publishImage(File image, String description) throws Exception {
		if (publishImage) {

			GraphResponse imagePublishedResponse =
					fbClient.publish(_PAGEID + "/photos", GraphResponse.class,
							BinaryAttachment.with(image.getName(), Files.readAllBytes((image.toPath()))),
							Parameter.with("message", description));
			System.out.println("Image published.\n" +
					"ID: " + imagePublishedResponse.getId());
		} else {
			log.info("Image not published");
		}
	}
}

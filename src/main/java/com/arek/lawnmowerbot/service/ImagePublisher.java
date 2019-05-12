package com.arek.lawnmowerbot.service;

import com.restfb.*;
import com.restfb.types.GraphResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
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


	void publishImage(InputStream image, String description) throws Exception {
		if (publishImage) {

			GraphResponse imagePublishedResponse =
					fbClient.publish(_PAGEID + "/photos", GraphResponse.class,
							BinaryAttachment.with("lawnmowerImage",image.readAllBytes() ),
							Parameter.with("message", description));
			log.info("===================================");
			log.info("Image published.\n" +
					"ID: " + imagePublishedResponse.getId());
		} else {
			log.info("===================================");
			log.info("Image not published");
		}
	}
}

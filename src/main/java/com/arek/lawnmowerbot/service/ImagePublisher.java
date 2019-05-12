package com.arek.lawnmowerbot.service;

import com.restfb.*;
import com.restfb.types.GraphResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;

@Slf4j
@Component
public class ImagePublisher {

	@Value("${image.publish}")
	private boolean publishImage;

	//	private final String _PAGEID = System.getenv("PAGEID_LAWN");
	private static final String _PAGEID = "lawnmowerbot1";
	//	private final String _TOKEN = System.getenv("TOKEN");
	private final String _TOKEN = "EAACVS6jUj0QBAPi1ZAVc6PudfgQkZAlenY3PN17sGeDElCzUe07ZAA5nwqtXZCuxvuk2XqTpiz6YQmEnahgXrqiV4F2mrmxrifpUTHn2AqEuMuphREoBSZBJ8ZAKuatoYFehMAJnoVQsTp1Cf9Uwmke9mPeEZBiP4ZAVAXpfNK3p8gZDZD";
	private FacebookClient fbClient = new DefaultFacebookClient(_TOKEN, Version.LATEST);

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

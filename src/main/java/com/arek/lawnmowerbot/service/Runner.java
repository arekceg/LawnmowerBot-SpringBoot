package com.arek.lawnmowerbot.service;

import com.arek.lawnmowerbot.model.DescriptionGenerator;
import com.arek.lawnmowerbot.model.ImageGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;

@Slf4j
@Component
@AllArgsConstructor
public class Runner {
	private final DescriptionGenerator descriptionGenerator;
	private final ImageGenerator imageGenerator;
	private final ImagePublisher imagePublisher;

	@Scheduled(fixedRate = 3600000*4)
	public void run() throws Exception {
        log.info("===================================");
		log.info("Scheduled runner method called");
		imagePublisher.publishImage(imageGenerator.getRandomImage(), descriptionGenerator.makeDescription());
	}
}

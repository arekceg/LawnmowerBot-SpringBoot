package com.arek.lawnmowerbot.service;

import com.arek.lawnmowerbot.model.DescriptionGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Slf4j
@Component
@AllArgsConstructor
public class Runner {
	private final DescriptionGenerator descriptionGenerator;
	private final ImageGenerator imageGenerator;
	private final ImagePublisher imagePublisher;

	@PostConstruct
//	@Scheduled(fixedRate = 3600000)
	public void run() throws Exception {
        log.info("Runner method called");
		File randomImage = imageGenerator.getRandomImage();
		String randomDescription = descriptionGenerator.makeDescription();
		imagePublisher.publishImage(randomImage, randomDescription);
	}
}

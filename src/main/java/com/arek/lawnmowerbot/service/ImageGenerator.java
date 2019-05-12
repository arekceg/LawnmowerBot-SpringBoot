package com.arek.lawnmowerbot.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Component
@Slf4j
@AllArgsConstructor
public class ImageGenerator {
	private ResourceLoader resourceLoader;

	private Resource[] loadImages() throws IOException {
		return ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
				.getResources("classpath:static/img/*.jpg");
	}

	public File getRandomImage() {
		Random random = new Random();
		try {
			Resource[] allImages = loadImages();
			File randomImage = allImages[random.nextInt(allImages.length)].getFile();
			Path usedPath = Paths.get(randomImage.getParent() + "/used/" + randomImage.getName());
			return (Files.move(randomImage.toPath(), usedPath)).toFile();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

}

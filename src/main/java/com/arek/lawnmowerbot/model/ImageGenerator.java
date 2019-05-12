package com.arek.lawnmowerbot.model;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

	public InputStream getRandomImage() {
		Random random = new Random();
		try {
			Resource[] allImages = loadImages();
			Resource randomImage = allImages[random.nextInt(allImages.length)];
			InputStream randomImageStream = randomImage.getInputStream();
			return randomImageStream;
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return null;
	}

}

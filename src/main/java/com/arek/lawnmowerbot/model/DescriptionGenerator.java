package com.arek.lawnmowerbot.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

@Component
public class DescriptionGenerator {

	private Random rand = new Random();

	@Value("classpath:static/words/names.txt")
	private Resource names;

	@Value("classpath:static/words/adj.txt")
	private Resource adj;

	@Value("classpath:static/words/adv.txt")
	private Resource adv;

	@Value("classpath:static/words/nouns.txt")
	private Resource nouns;

	public String makeDescription() throws IOException {
		return ("Name: " + makeName() + "\n" +
				"Nickname in High School: " + makeNickname());
	}

	private String makeName() throws IOException {
		return pickFrom(names) + makeNameSuffix();
	}

	private String makeNameSuffix() throws IOException {
		if (Math.random() < 0.5) return "";
		else if (Math.random() < 0.5) return " the " + pickFrom(adj);
		else return " the " + pickFrom(adv) + pickFrom(adj);
	}

	private String makeNickname() throws IOException {
		StringBuilder sb = new StringBuilder();
		double chance = Math.random();

		if (chance <= 0.3) sb.append(pickFrom(adj)).append(pickFrom(nouns));
		else if (chance <= 0.7) sb.append(pickFrom(adj));
		else sb.append(pickFrom(nouns));

		return sb.toString();
	}

	private String pickFrom(Resource resource) throws IOException {
		List<String> namesList = Files.readAllLines(resource.getFile().toPath());
		return namesList.get(rand.nextInt(namesList.size()));
	}
}

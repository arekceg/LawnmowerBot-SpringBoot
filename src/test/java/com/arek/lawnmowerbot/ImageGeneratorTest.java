package com.arek.lawnmowerbot;

import com.arek.lawnmowerbot.service.ImageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageGeneratorTest {

	@Autowired
    private ImageGenerator imageGenerator;

	@Test
	public void contextLoads() throws Exception {
		File testImage = imageGenerator.getRandomImage();
		assert testImage!=null;
	}

}

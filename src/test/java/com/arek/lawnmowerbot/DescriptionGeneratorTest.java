package com.arek.lawnmowerbot;;

import com.arek.lawnmowerbot.model.DescriptionGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptionGeneratorTest {

	@Autowired
	private DescriptionGenerator descriptionGenerator;

	@Test
	public void contextLoads() throws Exception {
		String testDescription = descriptionGenerator.makeDescription();
		log.info("testDescription = {}",testDescription);
		assert testDescription!=null;
	}

}

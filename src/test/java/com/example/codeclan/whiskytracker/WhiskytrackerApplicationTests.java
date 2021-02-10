package com.example.codeclan.whiskytracker;

import com.example.codeclan.whiskytracker.models.Whisky;
import com.example.codeclan.whiskytracker.repositories.WhiskyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhiskytrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void canCreateAndSaveWhisky() {
		Whisky arran21 = new Whisky("Arran 21 Year Old", 2018, 21);
		whiskyRepository.save(arran21);
	}

}

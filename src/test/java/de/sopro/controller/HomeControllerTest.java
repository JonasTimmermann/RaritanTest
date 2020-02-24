package de.sopro.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	MockMvc mvc;

	// Value is set via injection from file application.properties
	@Value("${home.welcome}")
	String message;

	@Test
	public void testIfHomePageCanBeShown() throws Exception {

		// Check if home page can be retrieved and contains expected message

		ResultMatcher model = MockMvcResultMatchers.model()
			.attribute("message", message);

		mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(model);
	}

}

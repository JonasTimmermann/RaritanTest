package de.sopro.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

	@Autowired
	MockMvc mvc;

	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void testIfAdminCanAccessBooksPage() throws Exception {

		// Check if books page can be retrieved by authenticated users
		// and is resolved to the expected view

		mvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(view().name("books-list"));
	}

	@Test
	@WithAnonymousUser
	public void testIfGuestIsRedirectedToLoginFromBooksPage() throws Exception {

		// Check if anonymous users are redirected to the login page when
		// trying to access the books page

		mvc.perform(get("/books"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrlPattern("**/login"));
	}

}

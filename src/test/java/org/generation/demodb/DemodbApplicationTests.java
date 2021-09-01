package org.generation.demodb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class DemodbApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	} //contextLoads

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/api/users/5"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string( containsString("mollete")));

		this.mockMvc.perform(get("/api/user/6"))
				.andDo(print())
				.andExpect(status().is4xxClientError());
				//.andExpect(content().string( containsString("mollete")));
	}//shouldReturnDefaultMessage

} //class

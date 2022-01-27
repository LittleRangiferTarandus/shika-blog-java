package kokoroAme;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
 
@SpringBootTest
@AutoConfigureMockMvc
public class testMockMvc {
	@Autowired
	MockMvc mockMvc;
	@Test
	public void test() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/user/{id}",2)
				.header("Access-Control-Request-Headers","*")
				.header("Origin","*")
				.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andDo(MockMvcResultHandlers.print());
	}
}

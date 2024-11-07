package br.com.pior.filme.rest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class FilmeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllAPI() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/filmes").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.min").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].producer").value("JOEL SILVER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].interval").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].previousWin").value(1990))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].followingWin").value(1991))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].producer").value("MATTHEW VAUGHN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].interval").value(13))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].previousWin").value(2002))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].followingWin").value(2015));
    }

}

package com.rayton.gps;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GpsWebApplication.class) // 这里的Application是springboot的启动类名
@WebAppConfiguration
public class MvcTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void test1() throws Exception {
        // mvc.perform(MockMvcRequestBuilders.get("/data/getMarkers")
        //         .contentType(MediaType.APPLICATION_JSON_UTF8)
        //         .param("lat", "123.123")
        //         .param("lon", "456.456")
        //         .accept(MediaType.APPLICATION_JSON))
        //         .andExpect(MockMvcResultMatchers.status().isOk())
        //         .andDo(MockMvcResultHandlers.print())
        //         .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));

    }
}
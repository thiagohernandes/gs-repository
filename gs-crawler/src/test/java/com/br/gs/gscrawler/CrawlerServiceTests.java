package com.br.gs.gscrawler;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.br.gs.gscrawler.controller.CrawlerController;
import com.br.gs.gscrawler.service.CrawlerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CrawlerController.class)
public class CrawlerServiceTests {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private CrawlerService service;

    @Test
    public void getCelulares() throws Exception {
     mvc.perform(MockMvcRequestBuilders.get("/api/crawler/celular/1").accept(MediaType.APPLICATION_JSON))
    		 			    .andExpect(status().isOk()).andReturn();

     }
    
    @Test
    public void getNotebooks() throws Exception {
     mvc.perform(MockMvcRequestBuilders.get("/api/crawler/notebook/1").accept(MediaType.APPLICATION_JSON))
    		 			    .andExpect(status().isOk()).andReturn();

     }
    
    @Test
    public void celularJSON() throws Exception {
     mvc.perform(MockMvcRequestBuilders.post("/api/crawler/celular").accept(MediaType.APPLICATION_JSON))
    		 			    .andExpect(status().isOk()).andReturn();

     }
    
    @Test
    public void notebookJSON() throws Exception {
     mvc.perform(MockMvcRequestBuilders.post("/api/crawler/notebook").accept(MediaType.APPLICATION_JSON))
    		 			    .andExpect(status().isOk()).andReturn();

     }

}

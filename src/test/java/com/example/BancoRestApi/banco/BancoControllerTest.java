package com.example.BancoRestApi.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.example.BancoRestApi.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BancoControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void indexBanco() throws Exception {
        String uri = "/bancos";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Banco[] bancoList = super.mapFromJson(content, Banco[].class);
        assertTrue(bancoList.length == 4);
    }

    @Test
    public void createBanco() throws Exception {
        String uri = "/bancos";
        Banco banco = new Banco("Banco Teste");

        String inputJson = super.mapToJson(banco);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateBanco() throws Exception {
        String uri = "/bancos/4";
        Banco banco = new Banco("Banco Atualizado");

        String inputJson = super.mapToJson(banco);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateBancoInexistente() throws Exception {
        String uri = "/bancos/10";
        Banco banco = new Banco("Banco Inexistente");

        String inputJson = super.mapToJson(banco);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void deleteBanco() throws Exception {
        String uri = "/bancos/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteBancoInexistente() throws Exception {
        String uri = "/bancos/9";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }
}

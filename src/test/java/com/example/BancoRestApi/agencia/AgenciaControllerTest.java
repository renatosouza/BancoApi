package com.example.BancoRestApi.agencia;

import com.example.BancoRestApi.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AgenciaControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void indexAgencia() throws Exception {
        String uri = "/bancos/1/agencias";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Agencia[] agenciaList = super.mapFromJson(content, Agencia[].class);
        assertTrue(agenciaList.length > 0);
    }

    @Test
    public void indexAgenciaSemBanco() throws Exception {
        String uri = "/bancos/7/agencias";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void createAgencia() throws Exception {
        String uri = "/bancos/1/agencias";
        Agencia agencia = new Agencia("1003-0", "Rua Teste", "10010100", null);

        String inputJson = super.mapToJson(agencia);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateAgencia() throws Exception {
        String uri = "/bancos/1/agencias/1";
        Agencia agencia = new Agencia("1001-0", "Rua dos Testes", "00000000", null);

        String inputJson = super.mapToJson(agencia);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateAgenciaInexistente() throws Exception {
        String uri = "/bancos/1/agencias/20";
        Agencia agencia = new Agencia("1001-0", "Rua dos Testes", "00000000", null);

        String inputJson = super.mapToJson(agencia);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void deleteAgencia() throws Exception {
        String uri = "/bancos/1/agencias/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteAgenciaInexistente() throws Exception {
        String uri = "/bancos/1/agencias/11";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

}

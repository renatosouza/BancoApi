package com.example.BancoRestApi.cliente;

import com.example.BancoRestApi.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClienteControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void indexCliente() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Cliente[] clienteList = super.mapFromJson(content, Cliente[].class);
        assertTrue(clienteList.length > 0);
    }

    @Test
    public void indexClienteSemAgencia() throws Exception {
        String uri = "/bancos/1/agencias/11/clientes";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void indexClienteSemBanco() throws Exception {
        String uri = "/bancos/7/agencias/1/clientes";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void createCliente() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes";
        Cliente cliente = new Cliente("00000", "Tester", "", "", "", 0, null);

        String inputJson = super.mapToJson(cliente);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateCliente() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/1";
        Cliente cliente = new Cliente("01100", "Tester", "", "", "", 0, null);

        String inputJson = super.mapToJson(cliente);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void updateClienteInexistente() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/15";
        Cliente cliente = new Cliente("00000", "Tester", "", "", "", 0, null);

        String inputJson = super.mapToJson(cliente);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void deleteCliente() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteClienteInexistente() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/15";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .delete(uri)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

}

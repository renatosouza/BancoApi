package com.example.BancoRestApi.movimentacao;

import com.example.BancoRestApi.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovimentacaoControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void indexExtratoSemCliente() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/10/extrato";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void indexExtratoSemAgencia() throws Exception {
        String uri = "/bancos/1/agencias/10/clientes/1/extrato";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void indexExtratoSemBanco() throws Exception {
        String uri = "/bancos/10/agencias/1/clientes/1/extrato";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void saque() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/1/saque";

        String inputJson = "{\"valor\":\"4000\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void saqueAcima() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/1/saque";

        String inputJson = "{\"valor\":\"7000\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

    @Test
    public void deposito() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/1/deposito";

        String inputJson = "{\"valor\":\"2000\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void transferencia() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/3/transferencia";

        String inputJson = "{\"destinatario\":\"4\", \"valor\":\"1000\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void transferenciaAcima() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/2/transferencia";

        String inputJson = "{\"destinatario\":\"3\", \"valor\":\"8000\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

    @Test
    public void transferenciaDestinatarioInexistente() throws Exception {
        String uri = "/bancos/1/agencias/1/clientes/2/transferencia";

        String inputJson = "{\"destinatario\":\"10\", \"valor\":\"1000\"}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

}

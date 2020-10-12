package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestVenda {
    
    private void verificarCampoObrigatorio(
        String mensagemEsperada,
        Venda venda
    ) {
        try {
            venda.dadosVenda();
            assertEquals("RuntimeException", "pass");
        } catch (RuntimeException re) {
            assertEquals(mensagemEsperada, re.getMessage());
        }
    }

    String MSG_ERR_LOJA_INVALIDA = "Loja é um campo obrigatório. Insira uma loja válida.";
    String MSG_ERR_CCF_INVALIDO = "O CCF inserido não é válido.";
    String MSG_ERR_COO_INVALIDO = "O COO inserido não é válido.";
    String MSG_ERR_CCF = "O Contador de Cupom Fiscal (CCF) é obrigatório.";
    String MSG_ERR_COO = "O Contador de Ordem de Operação (COO) é obrigatório.";

    private Loja lojaSample = new Loja();
    private int COO = 123456;
    private int CCF = 123456;

    private Venda vendaSample = new Venda(
        lojaSample,
        CCF,
        COO
    );

    @Test
    public void validarLoja() {
        Venda lojaNula = vendaSample;
        lojaNula.setLoja(null);
        verificarCampoObrigatorio(MSG_ERR_LOJA_INVALIDA, lojaNula);
    }

    @Test
	public void validarCCF() {
        Venda ccfVazio = vendaSample;
        ccfVazio.setCcf(0);
        verificarCampoObrigatorio(MSG_ERR_CCF, ccfVazio);

        Venda ccfIncorreto = vendaSample;
        ccfIncorreto.setCcf(12345);
        verificarCampoObrigatorio(MSG_ERR_CCF_INVALIDO, ccfIncorreto);
    }

    @Test
	public void validarCOO() {
        Venda cooVazio = vendaSample;
        cooVazio.setCoo(0);
        verificarCampoObrigatorio(MSG_ERR_COO, cooVazio);

        Venda cooIncorreto = vendaSample;
        cooIncorreto.setCoo(12345);
        verificarCampoObrigatorio(MSG_ERR_COO_INVALIDO, cooIncorreto);
    }

    @Test
    public void validarDataHora() {
        Venda venda = vendaSample;
        String dataAtual = Venda.getDataAtual();
        assertEquals(
            dataAtual.substring(0, 15), 
            venda.getDataHora()
                 .substring(0, 15)
        );
    }
}
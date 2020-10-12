package com.example.project;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class Venda {
    private Loja loja;
    private String datahora;
    private int ccf;
    private int coo;

    public Venda(Loja loja, int ccf, int coo) {
        this.loja = loja;
        this.ccf = ccf;
        this.coo = coo;
        this.datahora = getDataAtual();
    }

    public Loja getLoja() {
        return this.loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public String getDataHora() {
        return this.datahora;
    }

    public void setDataHora() {
        this.datahora = getDataAtual();
    }

    public void setDataHora(String datahora) {
        this.datahora = datahora;
    }

    public static String getDataAtual() {
        LocalTime hora = java.time.LocalTime.now();
        String horaFormat = hora.toString()
                                .substring(0,8);
                                
        Date d = new Date();
        Locale brasil = new Locale("pt", "BR");

        DateFormat data = DateFormat.getDateInstance(
            DateFormat.SHORT,
            brasil
        );

        String dataFormat = data.format(d);
        return dataFormat + " " + horaFormat + "V";
    }

    public int getCcf() {
        return this.ccf;
    }

    public void setCcf(int ccf) {
        this.ccf = ccf;
    }

    public int getCoo() {
        return this.coo;
    }

    public void setCoo(int coo) {
        this.coo = coo;
    }

    public String dadosVenda() {

        validarCamposObrigatorios();

        String _ccf = " CCF: " + getCcf();
        String _coo = " COO: " + getCoo();
        return getDataHora() + _ccf + _coo;
    }

    public void validarCamposObrigatorios() {
        if(getCcf() == 0) {
            throw new RuntimeException("O Contador de Cupom Fiscal (CCF) é obrigatório.");
        }

        if(getCoo() == 0) {
            throw new RuntimeException("O Contador de Ordem de Operação (COO) é obrigatório.");
        }

        if(String.format("%d",getCcf()).length() < 6) {
            throw new RuntimeException("O CCF inserido não é válido.");
        }

        if(String.format("%d",getCoo()).length() < 6) {
            throw new RuntimeException("O COO inserido não é válido.");
        }

        if(!(getLoja() instanceof Loja)) {
            throw new RuntimeException("Loja é um campo obrigatório. Insira uma loja válida.");
        }
    }
}

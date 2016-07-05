/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

import java.util.List;

/**
 *
 * @author Ramon
 */
public class Recompensa {
    private int     id;
    private int     valor;
    private String  descricao;
    private int  status;
    private int     quantidade;
    private Projeto projeto;
    private List<Apoio> apoio;
    private int quantidadeApoio;
    
    public Recompensa(){
        
    }
    public Recompensa(int valor, String descricao, int status){
        this.valor     =  valor;
        this.descricao =  descricao;
        this.status    =  status;
    }
    public Recompensa(int valor, String descricao,int quantidade, int status,Projeto projeto){
        this.valor     =  valor;
        this.quantidade = quantidade;
        this.descricao =  descricao;
        this.status    =  status;
        this.projeto   = projeto;

    }
    public Recompensa(int valor, String descricao, int status,Projeto projeto){
        this.valor     =  valor;
        this.descricao =  descricao;
        this.status    =  status;
        this.projeto   = projeto;

    }
    public Recompensa(int valor, String descricao){
        this.valor         =  valor;
        this.descricao     =  descricao;
    }
    public Recompensa(int id, int valor, String descricao, int status,Projeto projeto){
        this.id=  id;
        this.valor         =  valor;
        this.descricao     =  descricao;
        this.status        =  status;
        this.projeto       =  projeto;
    }

    /**
     * @return the id_recompensa
     */
    public int getId() {
        return id;
    }

    /**
     * @param id_recompensa the id_recompensa to set
     */
    public void setId(int id_recompensa) {
        this.id = id_recompensa;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the projeto
     */
    public Projeto getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    /**
     * @return the apoio
     */
    public List<Apoio> getApoio() {
        return apoio;
    }

    /**
     * @param apoio the apoio to set
     */
    public void setApoio(List<Apoio> apoio) {
        this.apoio = apoio;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the quantidadeApoio
     */
    public int getQuantidadeApoio() {
        return quantidadeApoio;
    }

    /**
     * @param quantidadeApoio the quantidadeApoio to set
     */
    public void setQuantidadeApoio(int quantidadeApoio) {
        this.quantidadeApoio = quantidadeApoio;
    }



}

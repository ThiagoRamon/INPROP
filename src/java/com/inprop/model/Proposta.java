/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

import java.sql.Date;

/**
 *
 * @author Ramon
 */


public class Proposta {
    private int       id;
    private String    descricao;
    private String    recompensas;
    private String    links;
    private int       valor_de;
    private int       valor_ate;
    private  Double   valor;
    private int       status;
    private Date      data_emissao;
    private Categoria categoria;
    private Perfil    perfil;
    private Projeto   projeto;
    
public Proposta(){

}
public Proposta(String descricao,String recompensas,String links,int valor_de,int valor_ate,
                int status,Date data_emissao){
    this.descricao     = descricao;
    this.recompensas   = recompensas;
    this.links         = links;
    this.valor_de         = valor_de;
    this.valor_ate     = valor_ate;

    this.status        = status;
    this.data_emissao  = data_emissao;

}
public Proposta(String descricao,String recompensas,String links,int valor_de,
                int status,Date data_emissao,Categoria categoria , Perfil perfil){
    this.descricao     = descricao;
    this.recompensas   = recompensas;
    this.links         = links;
    this.valor_de         = valor_de;
    this.status        = status;
    this.data_emissao  = data_emissao;
    this.categoria     = categoria;
    this.perfil        = perfil;

}
public Proposta(int id, String descricao,String recompensas,String links,int valor_de,
                int status,Date data_emissao){
    this.id   = id;
    this.descricao     = descricao;
    this.recompensas   = recompensas;
    this.links         = links;
    this.valor_de         = valor_de;
    this.status        = status;
    this.data_emissao  = data_emissao;

}
public Proposta(int id, String descricao,String recompensas,String links,int valor_de,
                int status,Date data_emissao,Categoria categoria,Perfil perfil){
    this.id   = id;
    this.descricao     = descricao;
    this.recompensas   = recompensas;
    this.links         = links;
    this.valor_de         = valor_de;
    this.status        = status;
    this.data_emissao  = data_emissao;
    this.categoria     = categoria;
    this.perfil        = perfil;

}

    /**
     * @return the id_proposta
     */
    public int getId() {
        return id;
    }

    /**
     * @param id_proposta the id_proposta to set
     */
    public void setId(int id_proposta) {
        this.id = id_proposta;
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
     * @return the reconpensas
     */
    public String getRecompensas() {
        return recompensas;
    }

    /**
     * @param reconpensas the reconpensas to set
     */
    public void setRecompensas(String recompensas) {
        this.recompensas = recompensas;
    }

    /**
     * @return the links
     */
    public String getLinks() {
        return links;
    }

    /**
     * @param links the links to set
     */
    public void setLinks(String links) {
        this.links = links;
    }

    /**
     * @return the valor
     */
    public int getValor_de() {
        return valor_de;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor_de(int valor_de) {
        this.valor_de = valor_de;
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
     * @return the data_emissao
     */
    public Date getData_emissao() {
        return data_emissao;
    }

    /**
     * @param data_emissao the data_emissao to set
     */
    public void setData_emissao(Date data_emissao) {
        this.data_emissao = data_emissao;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
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
     * @return the valor_ate
     */
    public int getValor_ate() {
        return valor_ate;
    }

    /**
     * @param valor_ate the valor_ate to set
     */
    public void setValor_ate(int valor_ate) {
        this.valor_ate = valor_ate;
    }


}

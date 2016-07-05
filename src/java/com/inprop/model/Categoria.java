/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class Categoria {
    private int id;
    private String nome;
    private String status;
    private Date   dataCadastro;
    private List<Proposta> proposta;
    
    public Categoria(){}
    public Categoria(String nome_categoria){
        this.nome=nome_categoria;
    }
    public Categoria(String nome_categoria,String status){
        this.nome=nome_categoria;
        this.status=status;
    }
    public Categoria(String nome_categoria,String status,Date dataCadastro){
        this.nome=nome_categoria;
        this.status=status;
        this.dataCadastro = dataCadastro;
    }
    public Categoria(int id,String nome_categoria){
        this.id   = id;
        this.nome = nome_categoria;
    }
    public Categoria(String nome_categoria,List<Proposta> proposta){
        this.nome=nome_categoria;
        this.proposta=proposta;
    }
    public Categoria(int id, String nome_categoria,List<Proposta> proposta){
        this.nome=nome_categoria;
        this.proposta=proposta;
    }

    /**
     * @return the id_categoria
     */
    public int getId_categoria() {
        return getId();
    }

    /**
     * @param id_categoria the id_categoria to set
     */
    public void setId_categoria(int id_categoria) {
        this.setId(id_categoria);
    }

    /**
     * @return the proposta
     */
    public List<Proposta> getProposta() {
        return proposta;
    }

    /**
     * @param proposta the proposta to set
     */
    public void setProposta(List<Proposta> proposta) {
        this.setProposta(proposta);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the dataCadastro
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


   

}

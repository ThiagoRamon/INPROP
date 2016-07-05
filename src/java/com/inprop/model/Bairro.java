/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

import java.sql.Date;

/**
 *
 * @author Ramon
 * create table bairro(
    id           int primary key auto_increment,
    nome         varchar(50)not null unique,
    data_cadastro date not null,
    id_cidade_fk        int not null,
    foreign key (id_cidade_fk)references cidade(id)  ON DELETE CASCADE
);

 */
import java.util.List;
public class Bairro {
    private int id;
    private String nome;
    private String status;
    private Date dataCadastro;
    private Cidade cidade;
    private List<CEP> cep;

    public Bairro(){
    }
    public Bairro(String nome,String status, Cidade cidade){
        this.nome   = nome;
        this.status = status;
        this.cidade = cidade;
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

    /**
     * @return the cidade
     */
    public Cidade getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the cep
     */
    public List<CEP> getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(List<CEP> cep) {
        this.cep = cep;
    }
}

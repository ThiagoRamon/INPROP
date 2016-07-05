/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

/**
 *
 * @author Ramon
 *
 *
create table endereco(
    id                 int primary key auto_increment,
    logradouro         varchar(100)not null unique,
    numero             varchar(50)not null unique,
    complemento        varchar(50)not null unique,
    referencia         varchar(255)not null unique,
    data_cadastro date not null,
    id_cep_fk          int not null,
    foreign key (id_cep_fk)references cep(id) ON DELETE CASCADE
);


 * create table endereco(
    id                 int primary key auto_increment,
    tipo               varchar(20)not null unique,
    logradouro         varchar(100)not null unique,
    numero             varchar(50)not null unique,
    complemento        varchar(50)not null unique,
    referencia         varchar(255)not null unique,
    data_cadastro date not null,
    id_cep_fk          int not null,
    foreign key (id_cep_fk)references cep(id) ON DELETE CASCADE
);

 *
 */


import java.sql.Date;
public class Endereco {
    private int id;
    private String numero;
    private String complemento;
    private String referencia;
    private String status;
    private Date   dataCadastro;

    private CEP    cep;
    private Perfil perfil;

    public Endereco(){
        
    }
    public Endereco(String numero,
                     String complemento,String status,String referencia,CEP cep){
        this.numero      = numero;
        this.status      = status;
        this.complemento = complemento;
        this.referencia  = referencia;
        this.cep         = cep;

    }
    public Endereco(String numero,
                     String complemento,String referencia,CEP cep){
        this.numero      = numero;
        this.complemento = complemento;
        this.referencia  = referencia;
        this.cep         = cep;

    }
    public Endereco(String numero,
                     String complemento,String referencia,CEP cep,Perfil perfil){
        this.numero      = numero;
        this.complemento = complemento;
        this.referencia  = referencia;
        this.cep         = cep;
        this.perfil      = perfil;

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
     * @return the tipo
     */

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
     * @return the cep
     */
    public CEP getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(CEP cep) {
        this.cep = cep;
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
}

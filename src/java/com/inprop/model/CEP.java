/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

/**
 *
 * @author Ramon
 *
 * create table cep(
    id           int primary key auto_increment,
    numero         varchar(50)not null unique,
    data_cadastro date not null,
    id_bairro_fk        int not null,
    foreign key (id_bairro_fk)references bairro(id) ON DELETE CASCADE
);

 */
import java.sql.Date;
import java.util.List;
public class CEP {
    private int id;
    private String numero;

    private String tipo;
    private String logradouro;

    private Date   dataCadastro;
    private Bairro bairro;
    
    List<Endereco> endereco;
    
public CEP(){
}
public CEP(String numero,String tipo, String logradouro,   Bairro bairro){
    this.tipo = tipo;
    this.logradouro = logradouro;

    this.numero= numero;
    this.bairro = bairro;

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
     * @return the bairro
     */
    public Bairro getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

}

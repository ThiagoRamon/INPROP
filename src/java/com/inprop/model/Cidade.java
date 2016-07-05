/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

import java.util.List;

/**
 *
 * @author Ramon
 *
 * create table cidade(
id           int primary key auto_increment,
nome         varchar(50)not null unique,
data_cadastro date not null,
id_estado_fk        int not null,
foreign key (id_estado_fk)references estado(id)  ON DELETE CASCADE
);

 */
import java.sql.Date;
public class Cidade {
     private int    id;
     private String nome;
     private String status;
     private Date   dataCadastro;
     private Estado estado;

     private List<Bairro> bairro;

     

     public Cidade(){}
     public Cidade(int id,String nome_cidade){
         this.id = id;
         this.nome = nome_cidade;
     }
     public Cidade(String nome_cidade, String status, Estado estado){
         this.nome   = nome_cidade;
         this.status = status;
         this.estado = estado;
     }
     public Cidade(int id_cidade, String nome_cidade, Estado estado){
         this.id   = id_cidade;
         this.nome = nome_cidade;
         this.estado      = estado;
     }

     public Cidade(int id, String nome,String status,Date dataCadastro, Estado estado){
         this.id      = id;
         this.nome    = nome;
         this.status  = status;
         this.dataCadastro = dataCadastro;
         this.estado  = estado;

     }
     public Cidade(int id, String nome,String status,Date dataCadastro){
         this.id      = id;
         this.nome    = nome;
         this.status  = status;
         this.dataCadastro = dataCadastro;

     }


    /**
     * @return the id_cidade
     */
    public int getId() {
        return id;
    }

    /**
     * @param id_cidade the id_cidade to set
     */
    public void setId(int id_cidade) {
        this.id = id_cidade;
    }

    /**
     * @return the nome_cidade
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome_cidade the nome_cidade to set
     */
    public void setNome(String nome_cidade) {
        this.nome = nome_cidade;
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setStatus(String status){
       this.status = status;
    }
    public String getStatus(){
       return status;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

/**
 *
 * @author Ramon
 *
create table pais(
id           int primary key auto_increment,
nome         varchar(50)not null unique,
sigla        varchar(5)not null unique,
status       enum('A','I'),
data_cadastro date not null
);

 */
import java.util.List;
import java.sql.Date;

public class Pais {
    private int id;
    private String nome;
    private String sigla;
    private String status;
    private Date     dataCadastro;
    private List<Estado> estado;

    public static final String STATUS_A = "A";
    public static final String STATUS_I = "I";

    
   public Pais(){
       
   }
   public Pais(int id){
       this.id=id;
   }
   public Pais(int id,String nome, String sigla,String status,Date dataCadastro){
       this.id           = id;
       this.nome         = nome;
       this.sigla        = sigla;
       this.status       = status;
       this.dataCadastro = dataCadastro;
   }
   public Pais(String nome, String sigla,String status,Date dataCadastro){
       this.nome         = nome;
       this.sigla        = sigla;
       this.status       = status;
       this.dataCadastro = dataCadastro;
   }
   public Pais(String nome, String sigla,String status){
       this.nome         = nome;
       this.sigla        = sigla;
       this.status       = status;
   }
   public Pais(int id,String nome, String sigla,String status){
       this.id           =     id;
       this.nome         =   nome;
       this.sigla        =  sigla;
       this.status       = status;
   }

    public static void main(String args[]){
        Pais p = new Pais();
        System.out.println();
    }


public void setId(int id){this.id = id;}
public int getId(){return id;}
public void setNome(String nome){this.nome=nome;}
public String getNome(){return nome; }
  /**
     * @return the estado
     */
    public List<Estado> getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(List<Estado> estado) {
        this.estado = estado;
    }

/**
     * @return the sigla
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * @param sigla the sigla to set
     */
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    /**
     * @return the status
     */
  
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

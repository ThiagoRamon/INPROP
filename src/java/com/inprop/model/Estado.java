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
create table estado(
id           int primary key auto_increment,
nome         varchar(50)not null unique,
uf           varchar(5)not null unique,
data_cadastro date not null,
status       enum('A','I'),
id_pais_fk   int not null,
foreign key (id_pais_fk)references pais(id) ON DELETE CASCADE
);
 */
import java.util.List;
import java.sql.Date;
public class Estado {
    private int      id;
    private String   nome;
    private String   uf;
    private Date     dataCadastro;
    private String status  ;
    private Pais     pais;
    private List<Cidade> cidade;

    public Estado(String uf){this.uf=uf;}
    public Estado(){}
    public Estado(int id){this.id = id;}
    public Estado(int id,String nome,String uf,String status,Date dataCadastro,Pais pais){
        this.id   = id;
        this.nome =nome;
        this.uf   = uf;
        this.dataCadastro = dataCadastro;
        this.status = status;
        this.pais   = pais;

    }
    public Estado(int id, String nome, String status, String uf, Pais pais){
        this.id     = id;
        this.nome   = nome;
        this.uf     = uf;
        this.status = status;
        this.pais   = pais;

    }
//    public Estado(String nome_cidade){
//        this.nome=nome_cidade;
//    }
    public Estado(String nome_cidade,String uf,String status, Pais pais){
        this.nome=nome_cidade;
        this.uf   = uf;
        this.status =status;
        this.pais=pais;
    }
    public Estado(String nome_cidade,List<Cidade> cidade){
        this.nome = nome_cidade;
        this.cidade      =cidade  ;
    }
    public Estado(int id_estado,String nome_estado){
        this.id   = id_estado;
        this.nome = nome_estado;
    }
    public Estado(int id_estado,String nome_cidade,List<Cidade> cidade){
        this.id   = id_estado;
        this.nome = nome_cidade;
        this.cidade      = cidade  ;
    }

    /**
     * @return the id_estado
     */
    public int getId() {
        return id;
    }

    /**
     * @param id_estado the id_estado to set
     */
    public void setId(int id) {
        this.id = id;
    }

public void setPais(Pais pais){this.pais=pais;}
public Pais getPais(){return pais;}

    /**
     * @return the nome_estado
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome_estado the nome_estado to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the getCidade
     */
    public List<Cidade> getCidade() {
        return cidade;
    }

    /**
     * @param getCidade the getCidade to set
     */
    public void setCidade(List<Cidade> Cidade) {
        this.setCidade(Cidade);
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

import com.inprop.persistence.Contato;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class Perfil {
    private int              id;
    private String           foto;
    private String           biografia;
    private String           status;
    private Date             dataCadastro;
    private Usuario          usuario;
    private Endereco         endereco;
    private List<Proposta>   proposta;
    private List<Apoio>      apoioEfetuado;
    private List<Comentario> comentario;
    private Contato          contato;
    
    private int              meusApoios;




    public Perfil(){}
   
    public Perfil(int id,String foto, String biografia,String status, Date dataCadastro){
        this.id              = id;
        this.foto            = foto;
        this.biografia       = biografia;
        this.status          = status;
        this.dataCadastro = dataCadastro;

    }
    public Perfil(int id,String foto, String biografia,String status){
        this.id              = id;
        this.foto            = foto;
        this.biografia       = biografia;
        this.status          = status;
    }
    public Perfil(String foto, String biografia,String status){
        this.foto            = foto;
        this.biografia       = biografia;
        this.status          = status;
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
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the biografia
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * @param biografia the biografia to set
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
        this.proposta = proposta;
    }

    /**
     * @return the apoioEfetuado
     */
    public List<Apoio> getApoioEfetuado() {
        return apoioEfetuado;
    }

    /**
     * @param apoioEfetuado the apoioEfetuado to set
     */
    public void setApoioEfetuado(List<Apoio> apoioEfetuado) {
        this.apoioEfetuado = apoioEfetuado;
    }

    /**
     * @return the comentario
     */
    public List<Comentario> getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the contato
     */
    public Contato getContato() {
        return contato;
    }

    /**
     * @param contato the contato to set
     */
    public void setContato(Contato contato) {
        this.contato = contato;
    }

    /**
     * @return the meusApoios
     */
    public int getMeusApoios() {
        return meusApoios;
    }

    /**
     * @param meusApoios the meusApoios to set
     */
    public void setMeusApoios(int meusApoios) {
        this.meusApoios = meusApoios;
    }
    /**
     * @param meusApoios the meusApoios to set
     */
  

}

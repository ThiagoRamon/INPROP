/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inprop.model;
/**
 *
 * @author Ramon
 */
import java.util.List;
public class Comentario {
    private int id_comentario;
    private String texto;
    private String data;
    private String status;
    private Projeto projeto;
    private Perfil perfil;





    public Comentario(int id, String texto, String data,String status, Projeto projeto, Perfil perfil){
        this.id_comentario = id;
        this.texto         = texto;
        this.data          = data;
        this.projeto       = projeto;
        this.perfil        = perfil;
    }
    public Comentario(String texto, String data,String status, Projeto projeto, Perfil perfil){
        this.texto         = texto;
        this.data          = data;
        this.projeto       = projeto;
        this.perfil        = perfil;
    }
    public Comentario(int id , String texto, String data,String status){
        this.id_comentario = id;
        this.texto         = texto;
        this.data          = data;
    }
    public Comentario(){}
    /**
     * @return the id_comentario
     */
    public int getId_comentario() {
        return id_comentario;
    }

    /**
     * @param id_comentario the id_comentario to set
     */
    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
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

}

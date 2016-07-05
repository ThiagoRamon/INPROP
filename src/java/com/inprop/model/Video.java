/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

/**
 *
 * @author Ramon
 */
public class Video {
    private int id_video;
    private String link;
    private String  data_emissao;
    private Projeto projeto;
public Video(){}
public Video(int id_video,String link, String data_emissao, Projeto projeto){
this.id_video     = id_video;
this.link         = link;
this.data_emissao = data_emissao;
this.projeto       = projeto;
}
public Video(String link, String data_emissao, Projeto projeto){
this.link         = link;
this.data_emissao = data_emissao;
this.projeto       = projeto;
}
public Video(int id_video, String link, String data_emissao){
    this.id_video = id_video;
this.link         = link;
this.data_emissao = data_emissao;
}
public Video(String link, String data_emissao){
this.link         = link;
this.data_emissao = data_emissao;
}
    /**
     * @return the id_video
     */
    public int getId_video() {
        return id_video;
    }

    /**
     * @param id_video the id_video to set
     */
    public void setId_video(int id_video) {
        this.id_video = id_video;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the data_emissao
     */
    public String getData_emissao() {
        return data_emissao;
    }

    /**
     * @param data_emissao the data_emissao to set
     */
    public void setData_emissao(String data_emissao) {
        this.data_emissao = data_emissao;
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
    
}

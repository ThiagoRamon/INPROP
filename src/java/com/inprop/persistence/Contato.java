/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Perfil;
import java.sql.Date;

/**
 *
 * @author Ramon
 */
public class Contato {

    private int id;
    private String tel;
    private String cel;
    private String telComercial;
    private String fax;
    private String faceBook;
    private String twitter;
    private String canalYouTube;
    private String obs;
    private String status;
    private Date   dataCadastro;

    private Perfil perfil;

    public Contato(){}
    public Contato(String tel,String telComercial,String cel,String fax, String faceBook, String twitter, String canalYouTube, String obs){
        this.tel          = tel;
        this.telComercial = telComercial;
        this.cel          = cel;
        this.fax          = fax;
        this.faceBook     = faceBook;
        this.twitter      = twitter;
        this.canalYouTube = canalYouTube;
        this.obs          = obs;
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
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the cel
     */
    public String getCel() {
        return cel;
    }

    /**
     * @param cel the cel to set
     */
    public void setCel(String cel) {
        this.cel = cel;
    }

    /**
     * @return the telComercial
     */
    public String getTelComercial() {
        return telComercial;
    }

    /**
     * @param telComercial the telComercial to set
     */
    public void setTelComercial(String telComercial) {
        this.telComercial = telComercial;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the faceBook
     */
    public String getFaceBook() {
        return faceBook;
    }

    /**
     * @param faceBook the faceBook to set
     */
    public void setFaceBook(String faceBook) {
        this.faceBook = faceBook;
    }

    /**
     * @return the twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter the twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return the canalYouTube
     */
    public String getCanalYouTube() {
        return canalYouTube;
    }

    /**
     * @param canalYouTube the canalYouTube to set
     */
    public void setCanalYouTube(String canalYouTube) {
        this.canalYouTube = canalYouTube;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
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

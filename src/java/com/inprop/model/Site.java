/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

/**
 *
 * @author Ramon
 */
public class Site {
   private int id_site;
   private String link;
   private Perfil perfil;

    /**
     * @return the id_site
     */
    public int getId_site() {
        return id_site;
    }

    /**
     * @param id_site the id_site to set
     */
    public void setId_site(int id_site) {
        this.id_site = id_site;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

/**
 *
 * @author Ramon
 */
public class Apoio {
    private int id_apoio;
    private Perfil perfil;
    private Recompensa recompensa;
    private String status;
     public Apoio(){}
     public Apoio(Perfil perfil, Recompensa recompensa, String status){
         this.perfil    = perfil;
         this.recompensa = recompensa;
         this.status     = status;
     }
     public Apoio(int id_apoio, Perfil perfil, Recompensa recompensa, String status){
         this.id_apoio   = id_apoio;
         this.perfil    = perfil;
         this.recompensa = recompensa;
         this.status     = status;
     }
    /**
     * @return the id_apoio
     */
    public int getId_apoio() {
        return id_apoio;
    }

    /**
     * @param id_apoio the id_apoio to set
     */
    public void setId_apoio(int id_apoio) {
        this.id_apoio = id_apoio;
    }

    /**
     * @return the usuario
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the recompensa
     */
    public Recompensa getRecompensa() {
        return recompensa;
    }

    /**
     * @param recompensa the recompensa to set
     */
    public void setRecompensa(Recompensa recompensa) {
        this.recompensa = recompensa;
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

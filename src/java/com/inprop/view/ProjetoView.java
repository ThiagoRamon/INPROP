/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.view;

import com.inprop.business.ProjetoBusiness;
import com.inprop.model.Perfil;
import com.inprop.model.Projeto;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class ProjetoView {
    private int id;
    private Perfil perfil;

    
    public Projeto getRetornaProjetoEmAndamentoPorPerfil(){
            ProjetoBusiness projetoBusiness = new ProjetoBusiness();
         try{
                return projetoBusiness.retornaProjetoByPerfil(perfil);
         }catch(Exception e){
             System.out.println(e.getMessage()+""+e.getStackTrace());
         }
            return null;
    }

    public Projeto getRetornaUmProjetoPorId(){
            ProjetoBusiness projetoBusiness = new ProjetoBusiness();
         try{
                return projetoBusiness.retornaProjeto(id);
         }catch(Exception e){
             System.out.println(e.getMessage()+""+e.getStackTrace());
         }
            return null;
    }
    public List<Projeto> getLista(){
            ProjetoBusiness projetoBusiness = new ProjetoBusiness();
         try{
                return projetoBusiness.lista();
         }catch(Exception e){
             System.out.println(e.getMessage()+""+e.getStackTrace());
         }
            return null;
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

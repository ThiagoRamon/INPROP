/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Perfil;
import com.inprop.persistence.PerfilDao;

/**
 *
 * @author Ramon
 */
public class PerfilBusiness {

    Perfil    perfil    =  new Perfil();
    PerfilDao perfilDao =  new PerfilDao();
    public boolean updateSobre(Perfil perfil)throws Exception{
        return perfilDao.uploadSobre(perfil);
    }
    public Perfil cadastraPerfil1(int id, String genero)throws Exception{
        return perfilDao.insert1(id, genero);
    }
    public boolean uploadFoto(int id_perfil, String foto)throws Exception{
        return perfilDao.uploadFoto(foto,id_perfil);
    }
    public boolean cadastraPerfil(Perfil perfil)throws Exception{
        boolean retorno = false;
        try{

            if(perfilDao.insert(perfil)){
                retorno = true;
            }

        }catch(Exception e){


        }

        return retorno;
    }


}

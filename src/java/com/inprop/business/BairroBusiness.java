/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Bairro;
import com.inprop.model.Endereco;
import com.inprop.persistence.BairroDao;

/**
 *
 * @author Ramon
 */
public class BairroBusiness {

    public Bairro verifica(Bairro  bairro)throws Exception{
        BairroDao bairroDao =new BairroDao();
        return bairroDao.findByBane(bairro);
    }
    public Bairro insertBairroByUsuario(Bairro bairro)throws Exception{
           BairroDao bairroDao = new BairroDao();
        //verifica se existe sidade com uma sigla
          Bairro bair       = verifica(bairro);
        if( bair == null){
            return bairroDao.insertByUsuario(bairro);
        }

        return bair;
   }

}

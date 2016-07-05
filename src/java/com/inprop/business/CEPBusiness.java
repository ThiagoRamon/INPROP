/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.CEP;
import com.inprop.persistence.CEPDao;

/**
 *
 * @author Ramon
 */
public class CEPBusiness {
     public CEP verifica(CEP  cep)throws Exception{
        CEPDao cepDao =new CEPDao();
        return cepDao.FindByUsuario(cep);
    }

     public CEP insertCidade(CEP cep)throws Exception{
        CEPDao cepDao = new CEPDao();
        //verifica se existe sidade com uma sigla
        CEP    cp       = verifica(cep);
        if( cp == null){
            return cepDao.insertByUsuario(cep);
        }

        return cp ;
    }

}

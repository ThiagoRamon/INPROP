/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Pais;
import com.inprop.persistence.PaisDao;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class PaisBusiness {
    public List<Pais> findAll()throws Exception{
        PaisDao paisDao = new PaisDao();
        return paisDao.findAll();
    }
    public boolean insert(Pais pais)throws Exception{
        
        PaisDao paisDao = new PaisDao();
        if(pais.getNome() == null || pais.getSigla()== null ||
                                                  pais.getStatus()== null){
          //  System.out.println("   dentro do pais bussines dentro do if");
            return false;

        }else{
            
          //  System.out.println("   dentro do pais bussines else");
            return paisDao.insert(pais);
        }
    }

}

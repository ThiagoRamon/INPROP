/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.view;

import com.inprop.business.PaisBusiness;
import com.inprop.model.Pais;
import com.inprop.persistence.PaisDao;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class PaisView  {

   
    public List<Pais> getlista()throws Exception{
        PaisBusiness paisBusiness = new PaisBusiness();
        return paisBusiness.findAll();
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.view;

import com.inprop.business.EstadoBusiness;
import com.inprop.model.Estado;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class EstadoView {

    public List<Estado> getLista(){
        EstadoBusiness estadoBusiness = new EstadoBusiness();
        try{
            return estadoBusiness.findAll();
        }catch(Exception e){
           return null;
        }
    }

}

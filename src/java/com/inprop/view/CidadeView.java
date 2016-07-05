/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.view;

import com.inprop.business.CidadeBusiness;
import com.inprop.model.Cidade;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class CidadeView {

    public List<Cidade> getLista()throws Exception{
       CidadeBusiness cidadeBusiness = new CidadeBusiness();
        try{
           return cidadeBusiness.findAll();
       }catch(Exception e){
            System.out.println("Exceptio  na lista de cidades na class cidadeView");
            return null;
       }
    }
}

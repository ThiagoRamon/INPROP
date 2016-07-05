/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.persistence.Contato;
import com.inprop.persistence.ContatoDao;

/**
 *
 * @author Ramon
 */
public class ContatoBusiness {


    public boolean updateAll(Contato contato)throws Exception{
        ContatoDao contatoDao = new ContatoDao();
        return contatoDao.updateAll(contato);

    }


    public Contato cadContato(Contato contato){
       ContatoDao contatoDao = new ContatoDao();
       try{
          return contatoDao.insert(contato);
        }catch(Exception e){
            System.out.println(e.getMessage()+"\n"+e.getStackTrace());
          return null;
        }

    }

}

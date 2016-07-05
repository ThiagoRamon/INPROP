/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Endereco;
import com.inprop.persistence.EnderecoDao;

/**
 *
 * @author Ramon
 */
public class EnderecoBusiness {
    public Endereco cadEndereco(Endereco endereco){
        EnderecoDao enderecoDao = new EnderecoDao();
        try{
            return enderecoDao.insert1(endereco);
        }catch(Exception e){
           return null;
        }
    }
    public Endereco UpdateEndereco(Endereco endereco){
        EnderecoDao enderecoDao = new EnderecoDao();
        try{
            return enderecoDao.update(endereco);
        }catch(Exception e){
           return null;
        }

    }

}

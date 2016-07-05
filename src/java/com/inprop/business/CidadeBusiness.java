/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Cidade;
import com.inprop.model.Endereco;
import com.inprop.persistence.CidadeDao;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class CidadeBusiness {

    public Cidade verifica(Cidade  cidade)throws Exception{
        CidadeDao cidadeDao =new CidadeDao();
        return cidadeDao.findCidade(cidade);
    }
    public Cidade insertCidade(Cidade cidade)throws Exception{
        CidadeDao cidadeDao = new CidadeDao();
        //verifica se existe sidade com uma sigla
        Cidade    cid       = verifica(cidade);
        if( cid == null){
            return cidadeDao.insertByUsuario(cidade);
        }

        return cid;
    }
    public Cidade insertToUser(Endereco end)throws Exception{
        CidadeDao cidadeDao = new CidadeDao();
        //verifica se existe sidade com uma sigla
        Cidade    cid       = verifica(end.getCep().getBairro().getCidade());
        if( cid == null){
            return cidadeDao.insertReturnCidade(end);
        }

        return cid;
    }
    public boolean insert(Cidade cidade)throws Exception{
        CidadeDao cidadeDao = new CidadeDao();
        return cidadeDao.insert(cidade);
    }
    public List<Cidade> findAll()throws Exception{
        CidadeDao cidadeDao =new CidadeDao();
        return cidadeDao.findAll();
    }
}

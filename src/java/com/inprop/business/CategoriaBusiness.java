/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Categoria;
import com.inprop.persistence.CategoriaDao;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class CategoriaBusiness {
    CategoriaDao categoriaDao = new CategoriaDao();
    public boolean cadastro(Categoria categoria)throws  Exception{
        return categoriaDao.insert(categoria);
    }

    public List<Categoria> findAll()throws Exception{

        return categoriaDao.findAll();
    }

}

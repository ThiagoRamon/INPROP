/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.view;

import com.inprop.business.CategoriaBusiness;
import com.inprop.model.Categoria;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class CategoriaView {

    public List<Categoria> getCategoria()throws Exception{
        CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
       return categoriaBusiness.findAll();
    }

}

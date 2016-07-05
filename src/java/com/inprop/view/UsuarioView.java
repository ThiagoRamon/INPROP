/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.view;

import com.inprop.business.UsuarioBusiness;
import com.inprop.model.Usuario;
import com.sun.net.httpserver.HttpServer;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon
 */
public class UsuarioView {
    UsuarioBusiness uB = new UsuarioBusiness();
    String nome = "thiagoramon";
    public void setNome(String nome){this.nome= nome;}
    public String getNome(){return nome;}

    public List<Usuario> getLista()throws Exception{
       List resp = new ArrayList();
        for(int i =0; i< 10 ; i++){
            Usuario u = new Usuario();
            u.setId(i);
          resp.add(u);
        }
       return resp;
    }

      // metodo para que verifica se existe algum administrador
    
    public boolean isAdmin()throws Exception{
        return uB.isAdm();
    }


}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

/**
 *
 * @author Ramon
 */

import com.inprop.persistence.UsuarioDao;
import com.inprop.model.Usuario;
import com.inprop.persistence.PerfilDao;
public class UsuarioBusiness {
 /*   public static UsuarioBusiness instance;
    public static UsuarioBusiness getInstance(){
        if(instance != null){
            instance = new UsuarioBusiness();
        }
        return instance;
    }
   */
    public static void main(String args[]){
      Usuario u= new Usuario("nome","sobre nome","M","93thiagoramon_@hotmail.com", "thi-amuito", null, "I","BAS" );
          UsuarioBusiness ud = new UsuarioBusiness();

       try{
                 ud.cadastraUsuario(u);
        //  UsuarioBusiness.getInstance(insert(u);

       }catch(Exception e){
                  System.out.println(e.getMessage()+"\n"+e.getStackTrace());
            }
    }


    public boolean isAdm()throws Exception{
        UsuarioDao usuarioDao = new UsuarioDao();
        return  usuarioDao.isAdministrador();
    }
    public boolean verificaEmail(String email)throws Exception{
        UsuarioDao usuarioDao = new UsuarioDao();
        return  usuarioDao.isEmail(email);
    }
    public Usuario cadastraUsuario1(Usuario usuario)throws Exception{
        UsuarioDao usuarioDao = new UsuarioDao();

        return  usuarioDao.insert1(usuario);
    }
    public boolean cadastraUsuario(Usuario usuario)throws Exception{
        UsuarioDao usuarioDao = new UsuarioDao();
        
        return  usuarioDao.insert(usuario);
    }
    public Object login(Usuario usuario)throws Exception{
        UsuarioDao usuarioDao = new UsuarioDao();
           Usuario usu = new Usuario();
          System.out.println("--mt login-->  "+usuarioDao.isPErfilForFK(usuario));
        if(usuarioDao.isPErfilForFK(usuario)){
                 usu         =  usuarioDao.loginBasico1(usuario);
        }else{
                usu         =  usuarioDao.login(usuario);
        
        }
        return  usu;
    }

}

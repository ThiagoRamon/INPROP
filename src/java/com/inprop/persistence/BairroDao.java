/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Bairro;
import com.inprop.model.Endereco;

/**
 *
 * @author Ramon
 */
public class BairroDao extends Dao{

    private static final String INSERT       = "insert into bairro(id,nome,data_cadastro,id_cidade_fk) values(null,? ,sysdate(),?)";
    private static final String FIND_BY_NAME = "select id, nome, data_cadastro, id_cidade_fk from bairro where upper(nome) = upper(?)";
  
    public Bairro findByBane( Bairro bairro )throws Exception{
        
        open();
        try{
            stmt = con.prepareStatement(FIND_BY_NAME);
            stmt.setString(1, bairro.getNome());
            rs   = stmt.executeQuery();
            if(rs.next()){
                bairro.setId(rs.getInt("id"));
            }else{
               bairro = null;
            }
        }catch(Exception e){
            System.out.println(e.getMessage()+"\n"+e.getStackTrace());
        }finally{
           if(rs!=null )rs.close();
           if(stmt!=null )stmt.close();
           if(con!=null )con.close();
           close();
        }
        return bairro;
    }

    public Bairro insertByUsuario(Object o)throws Exception{
        Bairro bairro = (Bairro)o;
        open();
        try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, bairro.getNome());
           stmt.setInt(2, bairro.getCidade().getId());
           stmt.executeUpdate();
           rs  = stmt.getGeneratedKeys();
           if(rs.next()){
                 bairro.setId(rs.getInt(1));
           }

        }catch(Exception e){
              System.out.println("Exceptio class bairroDao / insert  bairro "+e.getMessage()+"\n"+e.getStackTrace() );
        }finally{

            if(rs!=null )rs.close();
            if(stmt!=null )stmt.close();
            if(con!=null )con.close();
            close();
        }

        return bairro;
    }


    public boolean insertToUsuario(Object o)throws Exception{
        Endereco endereco = (Endereco)o;
        open();
        try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, endereco.getCep().getBairro().getNome());
           stmt.setInt(2, endereco.getCep().getBairro().getCidade().getId());
           stmt.executeUpdate();
           rs  = stmt.getGeneratedKeys();
           if(rs.next()){
               endereco.getCep().getBairro().setId(rs.getInt(1));
                System.out.println("id da bairro cadastrada   =" + rs.getInt(1));
                   CEPDao cepDao = new CEPDao();
                   try{
                       if(!cepDao.insertToUsuario(endereco)){
                            System.out.println("Erro- insert de contato na class perfil");
                        }
                    }catch(Exception e){
                       System.out.println("Exceptio class cidadeDao / insert para cadastras novo bairro "+e.getMessage()+"\n"+e.getStackTrace() );

                    }
           }

        }catch(Exception e){
              System.out.println("Exceptio class bairroDao / insert  bairro "+e.getMessage()+"\n"+e.getStackTrace() );
        }finally{

            if(rs!=null )rs.close();
            if(stmt!=null )stmt.close();
            if(con!=null )con.close();
            close();
        }

        return true;
    }
}

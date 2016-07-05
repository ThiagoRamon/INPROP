/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.CEP;
import com.inprop.model.Endereco;

/**
 *
 * @author Ramon
 */
public class CEPDao extends Dao{
    private static final String INSERT ="insert into cep(id,numero,tipo,logradouro,data_cadastro, id_bairro_fk) values(null,?,?,?,sysdate(),?)";
    private static final String FIND_BY_USUARIO ="select id, numero, data_cadastro, id_bairro_fk from cep where numero =?";


    public CEP FindByUsuario(CEP cep)throws Exception{

         open();
        try{
           stmt = con.prepareStatement(FIND_BY_USUARIO);
           stmt.setString(1, cep.getNumero());
           rs=stmt.executeQuery();
           if(rs.next()){
                cep.setId(rs.getInt("id"));
             }else{
               cep =null;
             }
        }catch(Exception e){
           System.out.println("Exceptio class CepDao / insert  cep "+e.getMessage()+"\n"+e.getStackTrace() );
           return null;
       }finally{
            if(rs!=null )rs.close();
            if(stmt!=null )stmt.close();
            close();
       }



       return cep;
    }
    public CEP insertByUsuario(CEP cep)throws Exception{

         open();
        try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, cep.getNumero());

           stmt.setString(2, cep.getTipo());
           stmt.setString(3, cep.getLogradouro());

           stmt.setInt(4, cep.getBairro().getId());
           stmt.executeUpdate();
           rs = stmt.getGeneratedKeys();
           if(rs.next()){
               cep.setId(rs.getInt(1));
           }
        }catch(Exception e){
           System.out.println("Exceptio class CepDao / insert  cep "+e.getMessage()+"\n"+e.getStackTrace() );
           return null;
       }finally{
            if(rs!=null )rs.close();
            if(stmt!=null )stmt.close();
            close();
       }

       return cep;
    }


    public boolean insertToUsuario(Object o)throws Exception{
        Endereco endereco = (Endereco) o;
        open();
        try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, endereco.getCep().getNumero());
           stmt.setInt(2, endereco.getCep().getBairro().getId());
           stmt.executeUpdate();
           rs = stmt.getGeneratedKeys();
           if(rs.next()){
               endereco.getCep().setId(rs.getInt(1));
               System.out.println("id do cep  : "+rs.getInt(1));
           }
        }catch(Exception e){
           System.out.println("Exceptio class CepDao / insert  cep "+e.getMessage()+"\n"+e.getStackTrace() );
           return false;
       }finally{
            if(rs!=null )rs.close();
            if(stmt!=null )stmt.close();
            close();
       }

        return true;
    }

}

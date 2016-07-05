/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Endereco;

/**
 *
 * @author Ramon
 */
public class EnderecoDao extends Dao{
    private static final String INSERT = "insert into endereco(id, numero, complemento, referencia, status , data_cadastro,id_cep_fk,id_perfil_fk) values(null,?,?,?,?,sysdate(),?,?)";
    private static final String update = "update  endereco set  numero=?, complemento=?, referencia=?, status=? ,id_cep_fk=? where id =?";

    public  Endereco update(Endereco endereco)throws Exception {
        open();
        System.out.println("numero  id :"+endereco.getId());
        System.out.println("numero :"+endereco.getNumero());
        System.out.println("complemento :"+endereco.getComplemento());
        System.out.println("referencia :"+endereco.getReferencia());
        System.out.println(" id cep:> :"+endereco.getCep().getId());
        System.out.println("id perfil >:"+endereco.getPerfil().getId());
        try{
             stmt= con.prepareStatement(update);
             stmt.setString(1, endereco.getNumero());
             stmt.setString(2, endereco.getComplemento());
             stmt.setString(3, endereco.getReferencia());
             stmt.setString(4, endereco.getStatus());
             stmt.setInt(5, endereco.getCep().getId());
             stmt.setInt(6, endereco.getId());
             stmt.executeUpdate();

        }catch(Exception e){
            System.out.println(e.getMessage()+ " \n " + e.getStackTrace());
            return endereco;
        }finally{
            if(stmt!=null)stmt.close();
            if(con!=null)con.close();
            if(rs!=null)rs.close();
            close();
        }

       return endereco;
    }


    public  Endereco insert1(Endereco endereco)throws Exception {
        open();
        System.out.println("numero :"+endereco.getNumero());
        System.out.println("complemento :"+endereco.getComplemento());
        System.out.println("referencia :"+endereco.getReferencia());
        System.out.println(" id cep :"+endereco.getCep().getId());
        System.out.println("id perfil :"+endereco.getPerfil().getId());
        try{
             stmt= con.prepareStatement(INSERT);
             stmt.setString(1, endereco.getNumero());
             stmt.setString(2, endereco.getComplemento());
             stmt.setString(3, endereco.getReferencia());
             stmt.setString(4, endereco.getStatus());
             stmt.setInt(5, endereco.getCep().getId());
             stmt.setInt(6, endereco.getPerfil().getId());
             stmt.executeUpdate();
             rs = stmt.getGeneratedKeys();
             if(rs.next()){endereco.setId(rs.getInt(1));}

        }catch(Exception e){
            System.out.println(e.getMessage()+ " \n " + e.getStackTrace());
            return endereco;
        }finally{
            if(stmt!=null)stmt.close();
            if(con!=null)con.close();
            if(rs!=null)rs.close();
            close();
        }

       return endereco;
    }
    public  boolean insert(Endereco endereco)throws Exception {
        open();
        System.out.println("numero :"+endereco.getNumero());
        System.out.println("complemento :"+endereco.getComplemento());
        System.out.println("referencia :"+endereco.getReferencia());
        System.out.println(" id cep :"+endereco.getCep().getId());
        System.out.println("id perfil :"+endereco.getPerfil().getId());
        try{
             stmt= con.prepareStatement(INSERT);
             stmt.setString(1, endereco.getNumero());
             stmt.setString(2, endereco.getComplemento());
             stmt.setString(3, endereco.getReferencia());
             stmt.setInt(4, endereco.getCep().getId());
             stmt.setInt(5, endereco.getPerfil().getId());
             stmt.executeUpdate();

        }catch(Exception e){
            System.out.println(e.getMessage()+ " \n " + e.getStackTrace());
            return false;
        }finally{
            if(stmt!=null)stmt.close();
            if(con!=null)con.close();
            if(rs!=null)rs.close();
            close();
        }

       return true;
    }
    public  boolean insert(Endereco endereco, int id_perfil)throws Exception {
        open();
        System.out.println("numero :"+endereco.getNumero());
        System.out.println("complemento :"+endereco.getComplemento());
        System.out.println("referencia :"+endereco.getReferencia());
        System.out.println(" id cep :"+endereco.getCep().getId());
        System.out.println("id perfil :"+id_perfil);
        try{
             stmt= con.prepareStatement(INSERT);
             stmt.setString(1, endereco.getNumero());
             stmt.setString(2, endereco.getComplemento());
             stmt.setString(3, endereco.getReferencia());
             stmt.setInt(4, endereco.getCep().getId());
             stmt.setInt(5, id_perfil);
             stmt.executeUpdate();

        }catch(Exception e){
            System.out.println(e.getMessage()+ " \n " + e.getStackTrace());
            return false;
        }finally{
            if(stmt!=null)stmt.close();
            if(con!=null)con.close();
            if(rs!=null)rs.close();
            close();
        }

       return true;
    }

}

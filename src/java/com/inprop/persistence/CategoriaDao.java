/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Categoria;
import com.inprop.model.Proposta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class CategoriaDao  extends Dao{
private static final String INSERT="insert into categoria(id,nome,status,data_cadastro)values(null,?,?, sysdate())";
private static final String UPDATE="update  categoria set nome_categoria=? where id_categoria=?";
private static final String DELETE="delete  from categoria where id_categoria=?";
private static final String FIND="select * from categoria where id_categoria=?";
private static final String FINDALL="select * from categoria";

    public boolean insert(Object o) throws Exception {
         Categoria categoria =(Categoria)o;

        open();
        try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, categoria.getNome());
           stmt.setString(2, categoria.getStatus());
           stmt.executeUpdate();
        }catch(Exception e){
            System.out.println("Exception-CategoriaDao-insert");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
             return false;
        }
        finally{
           stmt.close();
           close();
           
        }
        return true;

    }

    public void updade(Object o) throws Exception {
           Categoria categoria =(Categoria)o;
       open();
           stmt = con.prepareStatement(UPDATE);
           stmt.setString(1, categoria.getNome());
           stmt.setInt(2, categoria.getId_categoria());
           stmt.executeUpdate();
           stmt.close();
       close();

    }

    public void delete(int id) throws Exception {
        open();
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();
       close();

    }

    public Object find(int id) throws Exception {
       open();
       stmt = con.prepareStatement(FIND);
       stmt.setInt(1, id);
       rs=stmt.executeQuery();
        Categoria categoria;
        PropostaDao propostaDao = new PropostaDao();
       if(rs.next()){
            categoria = new Categoria(rs.getInt("id_categoria"),rs.getString("nome_categoria"));
       return categoria;
       }
     return null;
    }

    public Object find1(int id) throws Exception {
       open();
       stmt = con.prepareStatement(FIND);
       stmt.setInt(1, id);
       rs   = stmt.executeQuery();
        Categoria categoria=null;
       if(rs.next()){
              categoria = new Categoria(rs.getInt("id"),rs.getString("nome"));
              categoria.setDataCadastro(rs.getDate("data_cadastro"));
       return categoria;
       }
     return categoria;
    }

    public List<Categoria> findAll() throws Exception {
       open();
       stmt = con.prepareStatement(FINDALL);
       List<Categoria> resp = new ArrayList<Categoria>();
       rs=stmt.executeQuery();
       while(rs.next()){
           Categoria  categoria = new Categoria(rs.getInt("id"),rs.getString("nome"));
              categoria.setDataCadastro(rs.getDate("data_cadastro"));
              categoria.setStatus(rs.getString("status"));

           resp.add(categoria);
       }
     return resp;
   }

    
//    public List<Categoria> findAll_mais_qt_projeto() throws Exception {
//       open();
//       stmt = con.prepareStatement(FINDALL);
//       List<Categoria> resp = new ArrayList<Categoria>();
//       rs=stmt.executeQuery();
//       while(rs.next()){
//           Categoria categoria = new Categoria(rs.getInt("id_categoria"),rs.getString("nome_categoria"));
//            PropostaDao propostaDao = new PropostaDao();
//            List resp_proposta = new ArrayList();
//            resp_proposta =  propostaDao.findAll_pela_categoria(categoria.getId_categoria());
//
//           categoria.setProposta(resp_proposta);
//
//           resp.add(categoria);
//       }
//     return resp;
//   }


}

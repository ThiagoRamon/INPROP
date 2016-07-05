/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

/**
 *
 * @author Ramon
 */
import com.inprop.model.Estado;
import com.inprop.model.Pais;
import java.util.*;

public class EstadoDao extends Dao {
  private static final String INSERT="insert into estado(id,nome,uf,status,data_cadastro,id_pais_fk) values(null,?,?,?,sysdate(),?)";
  private static final String DELETE="DELETE FROM estado WHERE id_estado=?";
  private static final String UPDATE="UPDATE estado SET nome_estado=? WHERE id_estado =?";

  private static final String FINDALL="select e.id as cod_estado,e.nome as estado, " +
                                      "       e.uf,e.status,e.data_cadastro," +
                                      "       p.nome as pais, p.sigla, " +
                                      "       p.id as cod_pais,p.status as status_pais" +
                                      " from estado e inner join pais p "+
                                      "  on p.id =e.id_pais_fk         ";

  private static final String FIND="SELECT * FROM estado WHERE id_estado = ?";

  private static final String RETURN_EST_AND_PAIS =" select p.id as cod_pais, p.nome as pais , p.sigla ,p.status status_pais, " +
                                                   "       e.id as cod_estado, e.nome as estado, e.uf, e.status as status_estado, e.data_cadastro " +
                                                   " from pais p inner join estado e on p.id = e.id_pais_fk   where upper(e.uf) = upper(?)";


//public static void main(String args[])throws Exception{
//Estado p =new Estado();
//p.setUf("rj");
//EstadoDao ed =new EstadoDao();
//p =  ed.cadEnderecoDoUsuario(p);
//System.out.print(p.getId());
//System.out.println(p.getNome());
//}


   public Estado atualiza( Estado est ) throws Exception {

       Pais pais     = null;
       Estado estado = null;
       open();
       try{
           stmt = con.prepareStatement(RETURN_EST_AND_PAIS);
           stmt.setString(1,est.getUf());
           rs=stmt.executeQuery();
           if(rs.next()){
                pais = new Pais(rs.getInt("cod_pais") , rs.getString("pais"),
                                    rs.getString("sigla"),
                                    rs.getString("status_pais")
                                    );

                estado = new Estado(rs.getInt("cod_estado"),
                                          rs.getString("estado"),
                                          rs.getString("uf"),
                                          rs.getString("status_estado"),
                                          rs.getDate("data_cadastro"), pais);

          }
       }catch(Exception e){
          System.out.println(e.getMessage());
       }finally{
         if(rs!=null)rs.close();
         if(stmt!=null)stmt.close();
         close();
       }
     return estado;
   }

   
   public Estado cadEnderecoDoUsuario( Estado est ) throws Exception {

       Pais pais     = null;
       Estado estado = null;
       open();
       try{
           stmt = con.prepareStatement(RETURN_EST_AND_PAIS);
           stmt.setString(1,est.getUf());
           rs=stmt.executeQuery();
           if(rs.next()){
                pais = new Pais(rs.getInt("cod_pais") , rs.getString("pais"),
                                    rs.getString("sigla"),
                                    rs.getString("status_pais")
                                    );

                estado = new Estado(rs.getInt("cod_estado"),
                                          rs.getString("estado"),
                                          rs.getString("uf"),
                                          rs.getString("status_estado"),
                                          rs.getDate("data_cadastro"), pais);
                
          }
       }catch(Exception e){
          System.out.println(e.getMessage());
       }finally{
         if(rs!=null)rs.close();
         if(stmt!=null)stmt.close();
         close();
       }
     return estado;
   }


    public boolean insert(Object o) throws Exception {
       Estado estado =(Estado)o;
       open();
       try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, estado.getNome());
           stmt.setString(2, estado.getUf() );
           stmt.setString(3, estado.getStatus());
           stmt.setInt(4, estado.getPais().getId());
           stmt.executeUpdate();
           stmt.close();
       }catch(Exception e){
          return false;
       }finally{
           if(stmt!=null)stmt.close();
           close();
       }
       return true;
       
    }


//     public static void main(String args[])throws Exception{
//        EstadoDao p = new EstadoDao();
//        System.out.println(p.findAll().size());
//    }
     public List<Estado> findAll() throws Exception {
       
       List<Estado> resp = new ArrayList<Estado>();         
       open();
       try{
           stmt = con.prepareStatement(FINDALL);
           rs=stmt.executeQuery();
           while(rs.next()){
               Pais pais = new Pais(rs.getInt("cod_pais") , rs.getString("pais"),
                                    rs.getString("sigla"),
                                    rs.getString("status_pais")
                                    );

               Estado estado = new Estado(rs.getInt("cod_estado"),
                                          rs.getString("estado"),
                                          rs.getString("uf"),
                                          rs.getString("status"),
                                          rs.getDate("data_cadastro"), pais);
              resp.add(estado);
          }
       }catch(Exception e){

       }finally{
         if(rs!=null)rs.close();
         if(stmt!=null)stmt.close();
         close();
       }
     return resp;
   }
     public List<Estado> findAllForPais(int id) throws Exception {

       List<Estado> resp = new ArrayList<Estado>();
       open();
       try{
           stmt = con.prepareStatement(FINDALL);
           stmt.setInt(1,id);
           rs=stmt.executeQuery();

           while(rs.next()){
               Pais pais = new Pais(rs.getInt("cod_pais") , rs.getString("pais"),
                                    rs.getString("sigla"),
                                    rs.getString("status_pais")
                                    );

               Estado estado = new Estado(rs.getInt("cod_estado"),
                                          rs.getString("estado"),
                                          rs.getString("uf"),
                                          rs.getString("status"),
                                          rs.getDate("data_cadastro"), pais);
              resp.add(estado);
          }
       }catch(Exception e){

       }finally{
         if(rs!=null)rs.close();
         if(stmt!=null)stmt.close();
         close();
       }
     return resp;
   }


    public void updade(Object o) throws Exception {
       Estado estado =(Estado)o;
        open();
            stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, estado.getNome());
            stmt.setInt(2, estado.getId());
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
       Estado estado = new Estado();
      
       if(rs.next()){
//           estado.setId_estado(rs.getInt("id_estado"));
//           estado.setNome_estado(rs.getString("nome_estado"));
       return estado;
       }
     return null;
    }

//    public List findAll() throws Exception {
//       open();
//       stmt = con.prepareStatement(FINDALL);
//
//       rs=stmt.executeQuery();
//       List resp = new ArrayList();
//       while(rs.next()){
//            Estado estado = new Estado();
////                estado.setId_estado(rs.getInt("id_estado"));
////                estado.setNome_estado(rs.getString("nome_estado"));
//           resp.add(estado);
//       }
//     return resp;
//   }


}

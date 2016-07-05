/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import java.util.*;
import com.inprop.model.*;

public class CidadeDao extends Dao{
   private static final String INSERT="insert into cidade(id,nome,status,data_cadastro,id_estado_fk) " +
                                      " values(null,?,?,sysdate(),?)";

   private static final String UPDATE="update cidade set nome_cidade=?, id_estado_fk=? where id_cidade=?";
   private static final String DELETE="delete from cidade where id_cidade=?";
   private static final String FIND="select * from cidade where id_cidade=?";
   private static final String FINDFK="select * from cidade where id_estado_fk=?";
   //private static final String FINDALL="select * from cidade";

   private static final String FINDALL="select c.id as cod_cidade, c.nome as cidade, c.status as status_cidade, c.data_cadastro as dt_cad_cidade," +
                                       "       e.id as cod_estado, e.nome as estado, e.status as status_estado, e.uf, " +
                                       "       p.id as cod_pais  , p.nome as pais  , p.status as status_pais, p.sigla " +
                                       "  from cidade c inner join estado e on e.id = c.id_estado_fk                  " +
                                       "                inner join pais   p on p.id = e.id_pais_fk                    " ;


   private static final String FINDCIDADE="select c.id as cod_cidade, c.nome as cidade, c.status as status_cidade, c.data_cadastro as dt_cad_estado" +
                                       "  from cidade c  where upper(c.nome) = upper(?) ";



public static void main(String[] args)throws Exception{
    CidadeDao cidadeDao =new CidadeDao();
    System.out.println(cidadeDao.findAll().size());
}

  public Cidade findCidade(Cidade cid) throws Exception {
      Cidade cidade =null;
      open();
       try{
           stmt = con.prepareStatement(FINDCIDADE);
           stmt.setString(1, cid.getNome());
           rs=stmt.executeQuery();
           if(rs.next()){
              cidade = new Cidade(rs.getInt("cod_cidade"),rs.getString("cidade"),rs.getString("status_cidade"),rs.getDate("dt_cad_estado"));
           }
       }catch(Exception e){
             System.out.println("Exceptio class cidadeDao / findAll "+e);
       }finally{
          if( rs != null )rs.close();
          if( stmt !=null )stmt.close();
          close();
       }
     return cidade;
   }

     public List<Cidade> findAll() throws Exception {
       List<Cidade> resp = new ArrayList<Cidade>();
       open();
       try{
           stmt = con.prepareStatement(FINDALL);
           rs=stmt.executeQuery();
           while(rs.next()){
              Pais   pais   = new   Pais(rs.getInt("cod_pais"),rs.getString("pais"),rs.getString("status_pais"),rs.getString("sigla"));
              Estado estado = new Estado(rs.getInt("cod_estado"),rs.getString("estado"),rs.getString("status_estado"),rs.getString("uf"),pais);
              Cidade cidade = new Cidade(rs.getInt("cod_cidade"),rs.getString("cidade"),rs.getString("status_cidade"),rs.getDate("dt_cad_cidade"),estado);
              
              resp.add(cidade);
           }
       }catch(Exception e){
             System.out.println("Exceptio class cidadeDao / findAll "+e);
       }finally{
          if( rs != null )rs.close();
          if( stmt !=null )stmt.close();
          close();
       }
     return resp;
   }

    public Cidade insertByUsuario(Object o) throws Exception {
       Cidade cidade =(Cidade)o;
       open();

         System.out.println("---  " + cidade.getNome());
         System.out.println("---  " + cidade.getStatus());
         System.out.println("---  " + cidade.getEstado().getId());
       try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, cidade.getNome());
           stmt.setString(2, cidade.getStatus());
           stmt.setInt(3, cidade.getEstado().getId());
           stmt.executeUpdate();
           rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    cidade.setId(rs.getInt(1));
                }
       }catch(Exception e){
           System.out.println("Exceptio class cidadeDao / insert "+e.getMessage()+"\n"+e.getStackTrace() );
           return null;
       }finally{
           if(stmt!=null)stmt.close();
           close();
       }
       return cidade;
    }
    public Cidade insertReturnCidade(Object o) throws Exception {
       Endereco endereco =(Endereco)o;
       open();

         System.out.println("---  " + endereco.getCep().getBairro().getCidade().getNome());
         System.out.println("---  " +endereco.getCep().getBairro().getCidade().getStatus());
         System.out.println("---  " +endereco.getCep().getBairro().getCidade().getEstado().getId());
       try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, endereco.getCep().getBairro().getCidade().getNome());
           stmt.setString(2, endereco.getCep().getBairro().getCidade().getStatus());
           stmt.setInt(3, endereco.getCep().getBairro().getCidade().getEstado().getId());
           stmt.executeUpdate();
           rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    endereco.getCep().getBairro().getCidade().setId(rs.getInt(1));
                    System.out.println("id da cidade cadastrada" + rs.getInt(1));
                    BairroDao bairroDao = new BairroDao();
                    try{
                       if(!bairroDao.insertToUsuario(endereco)){
                            System.out.println("Erro- insert de contato na class perfil");
                        }
                    }catch(Exception e){
                       System.out.println("Exceptio class cidadeDao / insert para cadastras novo bairro "+e.getMessage()+"\n"+e.getStackTrace() );

                    }
                }
       }catch(Exception e){
           System.out.println("Exceptio class cidadeDao / insert "+e.getMessage()+"\n"+e.getStackTrace() );
           return endereco.getCep().getBairro().getCidade();
       }finally{
           if(stmt!=null)stmt.close();
           close();
       }
       return endereco.getCep().getBairro().getCidade();
    }



    public boolean insert(Object o) throws Exception {
         Cidade cidade =(Cidade)o;
       open();
       try{
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, cidade.getNome());
           stmt.setString(2, cidade.getStatus());
           stmt.setInt(3, cidade.getEstado().getId());
           stmt.executeUpdate();

       }catch(Exception e){
           System.out.println("Exceptio class cidadeDao / insert "+e);
           return false;
       }finally{
           if(stmt!=null)stmt.close();
           close();
       }
       return true;
    }

    public void updade(Object o) throws Exception {
         Cidade cidade =(Cidade)o;
        open();
            stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getEstado().getId());
            stmt.setInt(3, cidade.getId());
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
         Cidade cidade;
         EstadoDao estadoDao=new EstadoDao();
       if(rs.next()){
             cidade = new Cidade(rs.getInt("id_cidade"),rs.getString("nome_cidade"));
             Estado estado =(Estado)  estadoDao.find(rs.getInt("id_estado_fk"));
            cidade.setEstado(estado);
       return cidade;
       }
     return null;
    }

//    public List findAll() throws Exception {
//       open();
//       stmt = con.prepareStatement(FINDALL);
//       List resp = new ArrayList();
//       rs=stmt.executeQuery();
//        EstadoDao estadoDao=new EstadoDao();
//       while(rs.next()){
//          Cidade cidade = new Cidade(rs.getInt("id_cidade"),rs.getString("nome_cidade"));
//          Estado estado =(Estado)  estadoDao.find(rs.getInt("id_estado_fk"));
//          cidade.setEstado(estado);
//          resp.add(cidade);
//       }
//     return resp;
//   }
    public List findfk(int id_estado) throws Exception {
       open();
       stmt = con.prepareStatement(FINDFK);
       stmt.setInt(1, id_estado);
       List resp = new ArrayList();
       rs=stmt.executeQuery();
       while(rs.next()){
          Cidade cidade = new Cidade(rs.getInt("id_cidade"),rs.getString("nome_cidade"));
          resp.add(cidade);
       }
     return resp;
   }


}

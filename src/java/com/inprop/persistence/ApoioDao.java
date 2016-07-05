/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Apoio;
import com.inprop.model.Perfil;
import com.inprop.model.Recompensa;
import com.mysql.jdbc.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class ApoioDao extends Dao{

    private static final String total_apoio="select count(*)as cnt from apoio where id_recompensa_fk=?";
   // private static final String TOTALBYPERFIL="select count(*)as cnt from apoio where id_perfil_fk=?";
    private static final String TOTALBYPERFIL="select count(a.id)as cnt from apoio a  inner join Recompensa r on r.id = a.id_recompensa_fk"+
                                               " inner join projeto p    on  p.id = r.id_projeto_fk  "+
                                               " where  p.id = ? and id_perfil_fk=?";
    
    private static final String INSERT="insert into apoio values(null,?,?,?)";
    private static final String FUNDALL_WHERE_RECOMPENSA="select * from apoio where id_recompensa_fk=?";

     public int  countApoioByPerfil(int idPerfil ,int id_projeto) throws Exception{
        open();
        int qt =0;
        try{
            stmt = con.prepareStatement(TOTALBYPERFIL);
            System.out.println("id projeto"+ id_projeto+"\n id perfil"+ idPerfil);
            stmt.setInt(1,id_projeto);
            stmt.setInt(2,idPerfil);
            rs   = stmt.executeQuery();
            if(rs.next()){
               qt = rs.getInt("cnt");
            }
        }finally{
            rs.close();
            stmt.close();
            close();
        }
         return qt;
   }

    public void insert(Object o)throws Exception{
        try {
            Apoio apoio = (Apoio)o;
               open();
                   stmt = con.prepareStatement(INSERT);
                   stmt.setInt(1, apoio.getRecompensa().getId());
//                   stmt.setInt(2, apoio.getPerfil().getId_perfil());
                   stmt.setString(3,apoio.getStatus());
                   stmt.executeUpdate();
                  stmt.close();
              // close();
        } finally{
              // stmt.close();
               close();
        }
   }



//   public List<Apoio>  findAllWhereRecompensa(Recompensa recompensa) throws Exception{
//        open();
//            stmt = con.prepareStatement(FUNDALL_WHERE_RECOMPENSA);
//            stmt.setInt(1,recompensa.getId());
//            rs   = stmt.executeQuery();
//            List<Apoio> resp = new ArrayList<Apoio>();
//            while(rs.next()){
//                Apoio apoio = new Apoio();
//                Perfil perfil = new Perfil();
////                perfil.setId_perfil(rs.getInt("id_perfil_fk")) ;
//                apoio.setStatus(rs.getString("status"));
//                apoio.setPerfil(perfil);
//                resp.add(apoio);
//            }
//         return resp;
//   }
   public List<Apoio>  findAllWhereRecompensa(Recompensa recompensa) throws Exception{
        open();
            stmt = con.prepareStatement(FUNDALL_WHERE_RECOMPENSA);
            stmt.setInt(1,recompensa.getId());
            rs   = stmt.executeQuery();
            List<Apoio> resp = new ArrayList<Apoio>();
            while(rs.next()){
                Apoio apoio = new Apoio();
                //Perfil perfil = new Perfil();
//                perfil.setId_perfil(rs.getInt("id_perfil_fk")) ;
                apoio.setStatus(rs.getString("status"));
              //  apoio.setPerfil(perfil);
                resp.add(apoio);
            }
         return resp;
   }

   public int  countApoio(Recompensa recompensa) throws Exception{
        open();
        int qt =0;
        try{
            stmt = con.prepareStatement(total_apoio);
            stmt.setInt(1,recompensa.getId());
            rs   = stmt.executeQuery();
            if(rs.next()){
               qt = rs.getInt("cnt");
            }
        }finally{
            rs.close();
            stmt.close();
            close();
        }
         return qt;
   }







   public int getTotalApoio(int id_recompensa)throws Exception{
       open();
           stmt = con.prepareStatement(total_apoio);
           stmt.setInt(1, id_recompensa);
           rs = stmt.executeQuery();
           if(rs.next()){
              return rs.getInt("count(*)");
           }
           return 0;
   }



//   public int valorArrecadado()throws Exception{
//            stmt = con.prepareStatement(FUNDALL_WHERE_RECOMPENSA);
//            stmt.setInt(1,recompensa.getId_recompensa());
//            rs   = stmt.executeQuery();
//            List<Apoio> resp = new ArrayList<Apoio>();
//            while(rs.next()){
//                Apoio apoio = new Apoio();
//                apoio.setStatus(rs.getString("status"));
//                resp.add(apoio);
//            }
//       return 0;
//   }

}
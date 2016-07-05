/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Comentario;
import com.inprop.model.Perfil;
import com.inprop.model.Projeto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class ComentarioDao extends Dao{
//    private static final String INSERT="insert into comentario values(null,?,?,?,?,?)";
//    private static final String DELETE="delete from comentario where id_comentario=?";
//    private static final String FINDALL_WHERE_PROJETO="select * from comentario where id_projeto_fk=?";
//
//
//    public void delete(int id)throws Exception{
//        open();
//        try{
//            stmt=con.prepareStatement(DELETE);
//            stmt.setInt(1,id);
//            stmt.executeUpdate();
//            stmt.close();
//
//        }finally{
//          close();
//        }
//    }
//    public void insert(Object o)throws Exception{
//        Comentario comentario = (Comentario)o;
//        open();
//        try{
//            stmt=con.prepareStatement(INSERT);
//            stmt.setString(1,comentario.getTexto());
//            stmt.setString(2, comentario.getData());
//            stmt.setString(3, comentario.getStatus());
//            stmt.setInt(4, comentario.getProjeto().getId_projeto());
//            stmt.setInt(5, comentario.getPerfil().getId_perfil());
//            stmt.executeUpdate();
//            stmt.close();
//
//        }finally{
//          close();
//        }
//    }
//    public List<Comentario> findAll_to_projeto(Projeto projeto)throws Exception{
//         open();
//         stmt= con.prepareStatement(FINDALL_WHERE_PROJETO);
//         stmt.setInt(1, projeto.getId_projeto());
//         rs  = stmt.executeQuery();
//         List<Comentario> resp = new ArrayList<Comentario>();
//         while(rs.next()){
//             PerfilDao perfilDao = new PerfilDao();
//             Perfil perfil=null;
//             try{
//                  perfil=perfilDao.find(rs.getInt("id_perfil_fk"));
//             }catch(Exception e){
//                 perfil=null;
//
//             }
//             Comentario comentario= new Comentario(rs.getInt("id_comentario"),
//                                                    rs.getString("texto"),
//                                                    rs.getString("data_emissao"),
//                                                    rs.getString("status"),projeto,perfil);
//             resp.add(comentario);
//         }
//         return resp;
//
//    }

}

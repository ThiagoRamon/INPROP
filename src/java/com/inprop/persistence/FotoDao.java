/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Foto;
import com.inprop.model.Projeto;
import com.inprop.model.Proposta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class FotoDao extends Dao {

     private int id_foto;
    private String foto;
    private String status;
    private Projeto projeto;

  private static final String INSERT="insert into  foto values(null,?,?,?,?)";
  private static final String DELETE="DELETE FROM foto WHERE id_foto=?";
  private static final String UPDATE="UPDATE foto SET foto=?, status=? WHERE id_foto=?";
  private static final String MOSTRAR_TUDO="SELECT * FROM foto";
  private static final String FINDALL="SELECT * FROM foto where id_projeto_fk=? ";
  private static final String FIND="SELECT * FROM foto WHERE id_foto = ?";

    public void insert(Object o) throws Exception {
       Foto foto =(Foto)o;
       open();
           stmt = con.prepareStatement(INSERT);
           stmt.setString(1, foto.getFoto());
           stmt.setString(2, foto.getStatus());
           stmt.setString(3, foto.getData_emissao());
           stmt.setInt(4, foto.getProjeto().getId());
           stmt.executeUpdate();
           stmt.close();
       close();
    }

    public void updade(Object o) throws Exception {
       Foto foto =(Foto)o;
       open();
            stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, foto.getFoto());
            stmt.setInt(2, foto.getId_foto());
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

    public List<Foto> findAll(Projeto projeto) throws Exception {
       open();
       stmt = con.prepareStatement(FINDALL);
       stmt.setInt(1,projeto.getId());
       rs=stmt.executeQuery();
       List<Foto> resp = new ArrayList<Foto>();
       while(rs.next()){
           Foto foto = new Foto();
           foto.setId_foto(rs.getInt("id_foto"));
           foto.setFoto(rs.getString("nome"));
           foto.setStatus(rs.getString("status"));
           foto.setProjeto(projeto);
           resp.add(foto);
       }
     return resp;
    }
//
//    public List findAll() throws Exception {
//       open();
//       stmt = con.prepareStatement(FINDALL);
//
//       rs=stmt.executeQuery();
//       List resp = new ArrayList();
//       while(rs.next()){
//            Estado estado = new Estado();
//                estado.setId_estado(rs.getInt("id_estado"));
//                estado.setNome_estado(rs.getString("nome_estado"));
//           resp.add(estado);
//       }
//     return resp;
//   }


}

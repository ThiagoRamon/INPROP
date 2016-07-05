/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

/**
 *
 * @author Ramon
 */
import com.inprop.model.*;
import java.util.ArrayList;
import java.util.List;
public class RecompensaDao extends Dao{
    private static final String INSERT="insert into recompensa(id,valor,descricao,quantidade, status, data_cadastro, id_projeto_fk) "+
                                       " values(null, ?, ?, ?, ?, sysdate(), ?)";

    private static final String FINDALL_TO_PROJECT="select * from recompensa where id_projeto_fk=?";
    private static final String FINDALL_TO_PROJECT1="select id_recompensa, valor from recompensa where id_projeto_fk=?";
    private static final String FIND="select * from recompensa where id_recompensa=?";
    private static final String FIND_valor="select valor from recompensa where id_recompensa=?";


    public Recompensa insert(Object o)throws Exception{
        Recompensa recompensa =(Recompensa)o;

        open();
        try{
            stmt = con.prepareStatement(INSERT);
            stmt.setInt(1, recompensa.getValor());
            stmt.setString(2, recompensa.getDescricao());
            stmt.setInt(3, recompensa.getQuantidade());
            stmt.setInt(4, recompensa.getStatus());
            stmt.setInt(5, recompensa.getProjeto().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next()){
                 recompensa.setId(rs.getInt(1));
            }
        }catch(Exception e){
            System.out.println(e.getMessage()+""+e.getStackTrace());
        }finally{
            rs.close();
            stmt.close();
            close();
             
         }
        return recompensa;
    }

    public  Recompensa find(int id)throws Exception{
        open();
        stmt = con.prepareStatement(FIND);
        stmt.setInt(1, id);
        rs   = stmt.executeQuery();
        Recompensa recompensa =null;
        if(rs.next()){
            recompensa = new Recompensa(rs.getInt("valor"),rs.getString("descricao"));

        }
    return recompensa ;

    }

         public List<Recompensa> findAll(Projeto projeto)throws Exception{
         open(); List<Recompensa> resp = new ArrayList<Recompensa>();
          try{
             stmt = con.prepareStatement(FINDALL_TO_PROJECT);
             stmt.setInt(1, projeto.getId());
             rs   = stmt.executeQuery();
             int cont=0;
            
             while(rs.next()){
                 ApoioDao apoioDao= new ApoioDao();
                 Recompensa rec = new Recompensa(rs.getInt("id"),rs.getInt("valor"),rs.getString("descricao"),rs.getInt("status"),projeto);
                 rec.setQuantidade(rs.getInt("quantidade"));
                 rec.setQuantidadeApoio(apoioDao.countApoio(rec));
                 resp.add(rec);

             }
          }finally{
            rs.close();
            stmt.close();
            close();
            }
         return resp;
     }


//         public int findAll_val_total(Projeto projeto)throws Exception{
//         open();
//             stmt = con.prepareStatement(FINDALL_TO_PROJECT1);
//             stmt.setInt(1, projeto.getId());
//             rs   = stmt.executeQuery();
//             int valor_total_arrecadado=0;
//             while(rs.next()){
//                 ApoioDao apoioDao= new ApoioDao();
//                 Recompensa rec = new Recompensa();
//                 rec.setValor(rs.getInt("valor"));
//                 rec.setId_recompensa(rs.getInt("id_recompensa"));
//                 valor_total_arrecadado = valor_total_arrecadado+(rec.getValor()* apoioDao.getTotalApoio(rec.getId_recompensa()));
//             }
//         return valor_total_arrecadado;
//     }

}

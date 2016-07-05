/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

/**
 *
 * @author Ramon
 */
import com.inprop.model.Pais;
import java.util.ArrayList;
import java.util.List;
public class PaisDao extends Dao {
    private static final String INSERT="insert into pais values(null,?,?,?,sysdate())";
    private static final String DELETE="delete from pais where id_pais=?";
    private static final String UPDATE="update pais set nome=? where id_pais=?";
    private static final String FIND="select id, nome, sigla,status,data_cadastro from pais where id_pais=?";
    private static final String FINDALL="select id, nome, sigla,status,data_cadastro from pais";
public static void main(String args[])throws Exception{
Pais p =new Pais("Brasil","BR","A");
PaisDao pd =new PaisDao();
pd.insert(p);
}



    public boolean insert(Pais pais)throws Exception{
        open();

        try{
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, pais.getNome());
            stmt.setString(2, pais.getSigla());
            stmt.setString(3, pais.getStatus());
            stmt.executeUpdate();

        }catch(Exception e){
            System.out.println(e.getMessage()+"\n"+ e.getStackTrace() );
            return false;


        }finally{
               
               stmt.close();
               close();
        }
        return true;
    }


    public List<Pais> findAll()throws Exception{
        open();
            List<Pais> resp = new ArrayList<Pais>();
        try{
            stmt = con.prepareStatement(FINDALL);
            rs   = stmt.executeQuery();
            while(rs.next()){
                Pais pais = new Pais(rs.getInt("id"),rs.getString("nome"),
                                     rs.getString("sigla"),rs.getString("status"),
                                     rs.getDate("data_cadastro"));
                resp.add(pais);
            }


        }catch(Exception e){
            System.out.println(e);

        }finally{
          if(rs != null)rs.close();
          if(stmt != null)stmt.close();
          close();
        }
          return resp;

    }

}

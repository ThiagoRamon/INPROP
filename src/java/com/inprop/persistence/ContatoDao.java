/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

/**
 *
 * @author Ramon
 */
public class ContatoDao extends Dao {

    private static final String INSERT ="insert into contato values(null,?,?,?,?,?,?,?,?,?,sysdate(),?)";
    private static final String UPDATE_ALL ="update contato set telefone=? ,  celular=? , facebook = ?, twitter=?, canal_youtube=?, status=? where id=? ";
    private static final String UPDATE_ALL1 =
" update contato set tel         = ? , " +
"                    cel         = ? , " +
"                    facebook    = ?,"+
"                    twitter     = ? , "+
"                  canal_youtube = ? , status=? where id=?; ";


    public boolean updateAll(Object o) throws Exception{
        Contato contato = (Contato)o;
        open();
        try{
            stmt = con.prepareStatement(UPDATE_ALL1);
            stmt.setString(1, contato.getTel());
            stmt.setString(2, contato.getCel());
            stmt.setString(3, contato.getFaceBook());
            stmt.setString(4, contato.getTwitter());
            stmt.setString(5, contato.getCanalYouTube());
            stmt.setString(6, contato.getStatus());
            stmt.setInt(7, contato.getId());
            stmt.executeUpdate();

        }catch(Exception e){
           System.out.println("class : ContatoDao\n"+e.getMessage()+"\n"+e.getStackTrace());
           return  false;
        }finally{
            stmt.close();
            close();

        }
        return true;

    }


    public Contato insert(Object  o)throws Exception{
        Contato contato = (Contato)o;
        open();
        try{
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, contato.getTel());
            stmt.setString(2, contato.getCel());
            stmt.setString(3, contato.getFax());
            stmt.setString(4, contato.getTelComercial());
            stmt.setString(5, contato.getFaceBook());
            stmt.setString(6, contato.getTwitter());
            stmt.setString(7, contato.getCanalYouTube());
            stmt.setString(8, contato.getObs());
            stmt.setString(9, contato.getStatus());

            stmt.setInt(10, contato.getPerfil().getId());
            System.out.println("--->   "+ contato.getPerfil().getId()+"  <--");
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if(rs.next()){
                contato.setId(rs.getInt(1));
            }
        }catch(Exception e){
           System.out.println("class : ContatoDao\n"+e.getMessage()+"\n"+e.getStackTrace());
           return  null;
        }finally{
            stmt.close();
            close();
        return contato;
        }
    }
    protected boolean insert1(Object  o)throws Exception{
        Contato contato = (Contato)o;
        boolean retorn = true;
        open();

        try{
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, contato.getTel());
            stmt.setString(2, contato.getCel());
            stmt.setString(3, contato.getFax());
            stmt.setString(4, contato.getTelComercial());
            stmt.setString(5, contato.getFaceBook());
            stmt.setString(6, contato.getTwitter());
            stmt.setString(7, contato.getCanalYouTube());
            stmt.setString(8, contato.getObs());
            stmt.setInt(9, contato.getPerfil().getId());
            stmt.executeUpdate();
        }catch(Exception e){
           System.out.println("class : ContatoDao\n"+e.getMessage()+"\n"+e.getStackTrace());
           retorn = false;
        }finally{
            stmt.close();
            close();
        return retorn;
        }
    }
}

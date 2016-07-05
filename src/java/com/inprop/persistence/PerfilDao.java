/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.business.EnderecoBusiness;
import com.inprop.model.Cidade;
import com.inprop.model.Endereco;
import com.inprop.model.Perfil;
import com.inprop.model.Proposta;
import com.inprop.model.Site;
import com.inprop.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class PerfilDao extends Dao{


    private static final String INSERT="insert into perfil values(null,?,?,?,sysdate(),?)";
    private static final String UPDATE_SORBRE="update usuario set biografia=?  status=? where id=?";
    private static final String UPDATE_STATUS="update usuario set status=? where id_usuario=?";
    private static final String UPDATE_TXT="update perfil set nome=?, biografia=? where id_perfil=?";
    private static final String UPDATE_FOTO="update perfil set foto=? where id=?";
    private static final String DELETE="delete from usuario where id_usuario=?";
    private static final String FINDALL="select * from usuario";
    private static final String FIND_TO_USUARIO = "select * from perfil where id_usuario_fk=?";

    private static final String FIND="select p.id_perfil, p.nome, p.foto, p.biografia, p.genero, p.data_nascimento, p.id_usuario_fk,p.id_cidade_fk, u.email from perfil p inner join usuario u  where p.id_perfil=? and u.id_usuario = p.id_usuario_fk";
    private static final String FIND1="select nome from perfil where id_perfil=?";
    private static final String FINDFK="select * from perfil where id_usuario_fk=?";
    private static final String IS_PERFIL_FOR_FK="select 1 from perfil where id_usuario_fk=?";


    public boolean uploadSobre(Object o)throws Exception{
        Perfil perfil = (Perfil) o;
        open();
           try{
                stmt  = con.prepareStatement(UPDATE_SORBRE);
                    stmt.setString(1,perfil.getBiografia());
                    stmt.setInt(2,perfil.getId());
                    stmt.executeUpdate();
           }catch(Exception e){
               System.out.println("class : perfilDao-uploadSobre\n"+e.getMessage()+"\n"+e.getStackTrace());
               return false;
           }finally{
            stmt.close();
            close();
            return true;
           }
    }


    public boolean uploadFoto(String nomeFoto,int idPerfil)throws Exception{
        Perfil perfil = new Perfil();
        open();

           try{
                stmt  = con.prepareStatement(UPDATE_FOTO);
                    stmt.setString(1,nomeFoto);
                    stmt.setInt(2,idPerfil);
                    stmt.executeUpdate();
           }catch(Exception e){
               System.out.println("class : perfilDao\n"+e.getMessage()+"\n"+e.getStackTrace());
               return false;
           }finally{
            stmt.close();
            close();
            return true;
           }
    }



    public Perfil insert1(int IdUsuario,String genero)throws Exception{
        Perfil perfil = new Perfil();
        open();
            perfil.setBiografia("Sobre mim");
            perfil.setStatus("A");
           try{
                stmt  = con.prepareStatement(INSERT);
                if(genero.equalsIgnoreCase("m")){
                    stmt.setString(1,"silueta-M.png");
                     perfil.setFoto("silueta-M.png");
                }else{
                    stmt.setString(1,"silueta-F.jpg");
                    perfil.setFoto("silueta-F.jpg");
                }

                stmt.setString(2," Sobre mim ");
                stmt.setString(3,"A");
                stmt.setInt(4,IdUsuario);
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    perfil.setId(rs.getInt(1));
                     System.out.println("id do perfil  --  "+rs.getInt(1));
                }
           }catch(Exception e){
               System.out.println("class : perfilDao\n"+e.getMessage()+"\n"+e.getStackTrace());
               return null;
           }finally{
            stmt.close();
            close();
            return perfil;
           }
    }
    public boolean insert(Object o)throws Exception{
        Perfil perfil = (Perfil)o;
        open();
           try{
                stmt  = con.prepareStatement(INSERT);
                stmt.setString(1,perfil.getFoto());
                stmt.setString(2,perfil.getBiografia());
                stmt.setString(3,perfil.getStatus());
                stmt.setInt(4, perfil.getUsuario().getId_usuario());
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();

                if(rs.next()){
                    perfil.setId(rs.getInt(1));
                    EnderecoDao enderecoDao =new EnderecoDao();
                    ContatoDao contatoDao = new ContatoDao();

                    try{
                        if(!enderecoDao.insert(perfil.getEndereco(),rs.getInt(1))){
                            System.out.println("Erro- insert de endereco na class perfil");
                        }
//                        if(!contatoDao.insert(perfil.getContato())){
//                            System.out.println("Erro- insert de contato na class perfil");
//                        }
                    }catch(Exception e){
                            System.out.println(" 1 class : perfilDao\n"+e.getMessage()+"\n"+e.getStackTrace());
                              return false;
                    }
                }
           }catch(Exception e){
               System.out.println("class : perfilDao\n"+e.getMessage()+"\n"+e.getStackTrace());
               return false;
           }finally{
            stmt.close();
            close();
            return true;
           }
    }
//    public void delete(int id)throws Exception{
//        open();
//            stmt  = con.prepareStatement(DELETE);
//            stmt.setInt(1,id);
//            stmt.close();
//        close();
//    }
////    public void update_status(Object o)throws Exception{
////        Perfil perfil = (Perfil)o;
////        open();
////            stmt  = con.prepareStatement(UPDATE_STATUS);
////            stmt.setString(1,usuario.getStatus());
////            stmt.setInt(2,usuario.getId_usuario());
////            stmt.close();
////        close();
////    }
//    public void update(Object o)throws Exception{
//        Perfil perfil = (Perfil)o;
//        open();
//            stmt  = con.prepareStatement(UPDATE_TXT);
//            stmt.setString(1,perfil.getNome_completo());
//            stmt.setString(2,perfil.getBiografia());
//            stmt.setInt(3, perfil.getId_perfil());
//            stmt.executeUpdate();
//            stmt.close();
//        close();
//    }
//    public void update_foto(int id_perfil, String img)throws Exception{
//        //Perfil perfil = (Perfil)o;
//        open();
//            stmt  = con.prepareStatement(UPDATE_FOTO);
//            stmt.setString(1,img);
//            stmt.setInt(2,id_perfil);
//            stmt.executeUpdate();
//            stmt.close();
//        close();
//    }
////    public void update(Object o)throws Exception{
////        Perfil perfil = (Perfil)o;
////        open();
////            stmt  = con.prepareStatement(UPDATE);
////            stmt.setString(1,perfil.getNome_completo());
////            stmt.setString(2,perfil.getBiografia());
////            stmt.setString(3,perfil.getData_nascimento());
////            stmt.setInt(4, perfil.getCidade().getId_cidade());
////            stmt.setInt(5, perfil.getId_perfil());
////            stmt.executeUpdate();
////            stmt.close();
////        close();
////    }
//
//    public Perfil find(int id)throws Exception{
//        // este metodo so mostra os dados do usuario para o admin
//        open();
//            stmt = con.prepareStatement(FIND);
//            stmt.setInt(1, id);
//            rs   = stmt.executeQuery();
//            Perfil perfil;
//            if(rs.next()){
//                UsuarioDao usuariDao= new UsuarioDao();
//                Usuario usuario = new Usuario();
//                usuario.setEmail("u.email");
//                CidadeDao  cidadeDao =  new CidadeDao();
//                Cidade     cidade    = (Cidade)  cidadeDao.find(rs.getInt("p.id_cidade_fk"));
//
//                perfil = new Perfil(rs.getInt("p.id_perfil"),rs.getString("p.nome"),
//                                    rs.getString("p.foto"),rs.getString("p.biografia"),rs.getString("p.genero"),
//                                    rs.getString("p.data_nascimento"),usuario,cidade);
//
//                return perfil;
//            }
//        return null;
//    }
//    public Perfil find_site_aberto(int id)throws Exception{
//        // este metodo so mostra os dados do usuario para o admin
//        open();
//            stmt = con.prepareStatement(FIND);
//            stmt.setInt(1, id);
//            rs   = stmt.executeQuery();
//            Perfil perfil;
//            if(rs.next()){
//               // UsuarioDao usuariDao= new UsuarioDao();
//                //Usuario usuario = usuariDao.finds(rs.getInt("id_usuario_fk"));
//                CidadeDao  cidadeDao =  new CidadeDao();
//                Cidade     cidade    = (Cidade)  cidadeDao.find(rs.getInt("id_cidade_fk"));
//                //Usuario usuario=null;
//              //         SiteDao siteDao = new SiteDao();
//                perfil = new Perfil(rs.getInt("p.id_perfil"),rs.getString("p.nome"),
//                                    rs.getString("p.foto"),rs.getString("p.biografia"),rs.getString("p.genero"),
//                                    rs.getString("p.data_nascimento"),cidade);
//                return perfil;
//            }
//        return null;
//    }
//    public Perfil findFK(int id_usuario)throws Exception{
//        // este metodo resgata os dados do perfil pelo id do usuario
//        open();
//            stmt = con.prepareStatement(FINDFK);
//            stmt.setInt(1, id_usuario);
//            rs   = stmt.executeQuery();
//            Perfil perfil =null;
//            if(rs.next()){
//               //UsuarioDao usuariDao= new UsuarioDao();
//               //Usuario usuario = usuariDao.find(rs.getInt("id_usuario_fk"));
//                CidadeDao  cidadeDao =  new CidadeDao();
//                Cidade     cidade    = (Cidade)  cidadeDao.find(rs.getInt("id_cidade_fk"));
//
//                perfil = new Perfil(rs.getInt("id_perfil"),rs.getString("nome"),
//                                    rs.getString("foto"),rs.getString("biografia"),rs.getString("genero"),
//                                    rs.getString("data_nascimento"));//,usuario,cidade);
//                perfil.setCidade(cidade);
//                PropostaDao propostaDao = new PropostaDao();
//                int id_perfil = perfil.getId_perfil();
//                List<Proposta> resp1 = new ArrayList<Proposta>();
//                resp1 =  propostaDao.findAll_TO_PERFIL(id_perfil);
//               perfil.setProposta(resp1);
//
//                return perfil;
//            }
//        return perfil;
//    }
//
//
//
//
//
//
//
//
//
//
//
//    public Perfil findFF(int id_perfil)throws Exception{
//        // este metodo resgata os dados do perfil pelo id do usuario
//        open();
//            stmt = con.prepareStatement(FIND);
//            stmt.setInt(1, id_perfil);
//            rs   = stmt.executeQuery();
//            Perfil perfil;
//            if(rs.next()){
//                UsuarioDao usuariDao= new UsuarioDao();
//                Usuario usuario = usuariDao.find(rs.getInt("id_usuario_fk"));
//                CidadeDao  cidadeDao =  new CidadeDao();
//                Cidade     cidade    = (Cidade)  cidadeDao.find(rs.getInt("id_cidade_fk"));
//
//                perfil = new Perfil(rs.getInt("id_perfil"),rs.getString("nome"),
//                                    rs.getString("foto"),rs.getString("biografia"),rs.getString("genero"),
//                                    rs.getString("data_nascimento"));//,usuario,cidade);
//                perfil.setCidade(cidade);
//                perfil.setUsuario(usuario);
//
//                return perfil;
//            }
//        return null;
//    }
//    public List findAll(int id)throws Exception{
//        // este metodo so mostra os dados do usuario para o admin
//        open();
//            stmt = con.prepareStatement(FIND);
//            stmt.setInt(1, id);
//            rs   = stmt.executeQuery();
//            List<Perfil> resp= new ArrayList<Perfil>();
//            while(rs.next()){
//
//                UsuarioDao usuariDao= new UsuarioDao();
//                Usuario usuario = usuariDao.find(rs.getInt("id_usuario"));
//                CidadeDao  cidadeDao =  new CidadeDao();
//                Cidade     cidade    = (Cidade)  cidadeDao.find(rs.getInt("id_cidade"));
//
//             Perfil perfil = new Perfil(rs.getInt("id_perfil"),rs.getString("nome"),
//                                    rs.getString("foto"),rs.getString("biografia"),rs.getString("genero"),
//                                    rs.getString("data_nascimento"),usuario,cidade);
//
//
//                resp.add(perfil);
//            }
//        return resp;
//    }
}

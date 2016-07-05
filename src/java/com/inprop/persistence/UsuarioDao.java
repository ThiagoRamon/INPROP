/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

/**
 *
 * @author Ramon
 */
import com.inprop.model.Bairro;
import com.inprop.model.CEP;
import com.inprop.model.Cidade;
import com.inprop.model.Endereco;
import com.inprop.model.Estado;
import com.inprop.model.Pais;
import com.inprop.model.Perfil;
import com.inprop.model.Proposta;
import com.inprop.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao extends Dao{


/*	private static UsuarioDao instance;

	public static UsuarioDao getInstance() {
		if (instance == null) {
			instance = new UsuarioDao();
		}
		return instance;
	}
*/

//
//     usuario = new Usuario(rs.getInt("id"),
//                                      rs.getString("email"),
//                                      rs.getString("senha"),
//                                      rs.getString("status"),
//                                      rs.getString("data_nascimento"));
//                usuario.setNome(rs.getString("nome"));
//                usuario.setSobreNome(rs.getString("sobre_nome"));
//                usuario.setTipo(rs.getString("tipo"));

        private static final String IS_ADM         = "select 1 from usuario  where  tipo=upper('adm')";
        private static final String IS_PERFIL_LOGIN         = "select 1 from perfil where id_usuario_fk = (select id from usuario  where email=? and senha=? )";
        private static final String LOGIN         = "select id,email,senha,status,data_nascimento,nome,sobre_nome,tipo from usuario where email=? and senha=?";

    private static final String INSERT        = "insert into usuario values(null,?,?,?,?,?,?,?,?,sysdate(),sysdate())";
    private static final String UPDATE_STATUS = "update usuario set status=? where id_usuario=?)";
    private static final String UPDATE        = "update usuario set email=?,senha=? where id_usuario=?)";
    private static final String DELETE        = "delete from usuario where id_usuario=?)";
    private static final String FINDALL       = "select * from usuario";
    private static final String FIND          = "select * from usuario where id_usuario=?";
    private static final String FIND_USU      = "select * from usuario u inner join perfil p  where u.id_usuario=?";
    private static final String VERIFICA_ADMIN= "select * from usuario where status='a'";
    private static final String IS_EMAIL       = "select 1 from usuario where email=?";


    private static final String LOGIN_BASICO1      = "select u.id,u.email,u.senha,u.status, "+
                                                    "       u.data_nascimento, "+
                                                    "        u.nome,u.sobre_nome, "+
                                                    "        u.tipo,p.id,p.foto,p.biografia, "+
                                                    "        p.status,p.data_cadastro " +
                                                    " from  usuario u inner join perfil p on p.id_usuario_fk = u.id " +
                                                    " where email=? and senha=? ";

    private static final String LOGIN_BASICO       = "select u.id,u.email,u.senha,u.status, "+
                                                    "       u.data_nascimento, "+
                                                    "        u.nome,u.sobre_nome, "+
                                                    "        u.tipo,p.id,p.foto,p.biografia, "+
                                                    "        p.status,p.data_cadastro, " +
                                                    "  c.id as id_contato,  c.tel,c.cel,c.faceBook,c.twitter,c.canal_youtube,c.obs,c.status as status_contato, "+
                                                    "  e.id as id_endereco, e.numero, e.complemento, e.referencia, e.status as status_endereco, " +
                                                    "cp.id as id_cep,cp.numero as numero_cep,cp.tipo as tp,cp.logradouro,"+
                                                    "   b.id as id_bairro ,b.nome as bairro, "+
                                                    "    cid.id as id_cidade,cid.nome as cidade, "+
                                                    "    est.id as id_estado, est.nome as estado, est.uf , "+
                                                    "    pas.id as id_pais ,pas.nome as pais , pas.sigla "+
                                                    " from  usuario u inner join perfil p on p.id_usuario_fk = u.id " +
                                                    "                  inner join contato c on c.id_perfil_fk = p.id" +
                                                    "                  inner join endereco e on e.id_perfil_fk = p.id" +
                                                    "  inner join cep      cp on cp.id = e.id_cep_fk       "+
                                                    "  inner join bairro   b  on b.id   = cp.id_bairro_fk   "+
                                                    "  inner join cidade   cid on cid.id = b.id_cidade_fk   "+
                                                    "  inner join estado   est on est.id = cid.id_estado_fk "+
                                                    "  inner join pais     pas  on pas.id = est.id_pais_fk  "+
                                                    " where email=? and senha=? ";



    public static void main (String args[]){

        UsuarioDao u = new UsuarioDao();

        try{
            System.out.println(u.isAdministrador());
        }catch(Exception e ){
            System.out.println(e.getMessage() );
        }
    }

    //login basico é o login com dados do usuario e do perfil
    public boolean isAdministrador()throws Exception{
        // este metodo so mostra os dados do usuario para o admin
        boolean retorno = false;
        try{
        open();

            stmt = con.prepareStatement(IS_ADM);
            rs   = stmt.executeQuery();
            if(rs.next()){
                retorno = true;
            }
        }catch(Exception e){
             System.out.println(e.getMessage()+"\n"+e.getStackTrace());
        }finally{
          stmt.close();
          close();
          return  retorno;
        }

    }


    public Usuario loginBasico1(Object o)throws Exception{
        // este metodo so mostra os dados do usuario para o admin
        Usuario usuario = (Usuario)o;
        open();
            stmt = con.prepareStatement(LOGIN_BASICO);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            rs   = stmt.executeQuery();
            if(rs.next()){
                usuario = new Usuario(rs.getInt("u.id"),
                                      rs.getString("email"),
                                      rs.getString("senha"),
                                      rs.getString("status"),
                                      rs.getString("data_nascimento"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobreNome(rs.getString("sobre_nome"));
                usuario.setTipo(rs.getString("tipo"));
                System.out.println("nome do usuario Logado --: "  +  usuario.getNome());
                Perfil perfil = new Perfil(rs.getInt("p.id"), rs.getString("foto"),
                                     rs.getString("biografia"), rs.getString("p.status"),
                                     rs.getDate("data_cadastro"));
                perfil.setUsuario(usuario);

                Contato conta = new Contato();
                conta.setId(rs.getInt("id_contato"));
                conta.setTel(rs.getString("tel"));
                conta.setCel(rs.getString("cel"));
                conta.setTwitter(rs.getString("facebook"));
                conta.setFaceBook(rs.getString("twitter"));
                conta.setCanalYouTube(rs.getString("canal_youtube"));
                conta.setStatus(rs.getString("obs"));
                conta.setStatus(rs.getString("status_contato"));
                conta.setPerfil(perfil);

               
               Endereco endereco = new Endereco();
               endereco.setId(rs.getInt("id_endereco"));
               endereco.setNumero(rs.getString("numero"));
               endereco.setComplemento(rs.getString("complemento"));
               endereco.setReferencia(rs.getString("referencia"));
               endereco.setStatus(rs.getString("status_endereco"));
               endereco.setPerfil(perfil);

               
               CEP cep = new CEP();
               cep.setId(rs.getInt("id_cep"));
               cep.setNumero(rs.getString("numero_cep"));
               cep.setTipo(rs.getString("tp"));
               cep.setLogradouro(rs.getString("logradouro"));

               Bairro bairro = new Bairro();
               bairro.setId(rs.getInt("id_bairro"));
               bairro.setNome(rs.getString("bairro"));

               Cidade cidade =new Cidade();
               cidade.setId(rs.getInt("id_cidade"));
               cidade.setNome(rs.getString("cidade"));

               Estado estado = new Estado();
               estado.setId(rs.getInt("id_estado"));
               estado.setNome(rs.getString("estado"));
               estado.setUf(rs.getString("uf"));

               Pais pais = new Pais();
               pais.setId(rs.getInt("id_pais"));
               pais.setNome(rs.getString("pais"));
               pais.setSigla(rs.getString("sigla"));


               estado.setPais(pais);
               cidade.setEstado(estado);
               bairro.setCidade(cidade);
               cep.setBairro(bairro);
               perfil.setContato(conta);
               endereco.setCep(cep);
               perfil.setEndereco(endereco);
               usuario.setPerfil(perfil);

               /* PerfilDao  perfilDao = new PerfilDao();
                if(perfilDao.findFK(rs.getInt("id_usuario"))!=null){
                     Perfil perfil= new Perfil();
                     int id_usuario= usuario.getId_usuario();
                     perfil =(Perfil)perfilDao.findFK(id_usuario);
                     usuario.setPerfil(perfil);
                *
                }*/
                return usuario;
            }
        return null;
    }
    public Usuario loginBasico(Object o)throws Exception{
        // este metodo so mostra os dados do usuario para o admin
        Usuario usuario = (Usuario)o;
        open();
            stmt = con.prepareStatement(LOGIN_BASICO);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            rs   = stmt.executeQuery();
            if(rs.next()){
                usuario = new Usuario(rs.getInt("u.id"),
                                      rs.getString("email"),
                                      rs.getString("senha"),
                                      rs.getString("status"),
                                      rs.getString("data_nascimento"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobreNome(rs.getString("sobre_nome"));
                usuario.setTipo(rs.getString("tipo"));
                System.out.println("nome do usuario Logado --: "  +  usuario.getNome());
                Perfil perfil = new Perfil(rs.getInt("p.id"), rs.getString("foto"),
                                     rs.getString("biografia"), rs.getString("p.status"),
                                     rs.getDate("data_cadastro"));
                Contato conta = new Contato();
                conta.setTel(rs.getString("tel"));
                perfil.setContato(conta);
                usuario.setPerfil(perfil);

               /* PerfilDao  perfilDao = new PerfilDao();
                if(perfilDao.findFK(rs.getInt("id_usuario"))!=null){
                     Perfil perfil= new Perfil();
                     int id_usuario= usuario.getId_usuario();
                     perfil =(Perfil)perfilDao.findFK(id_usuario);
                     usuario.setPerfil(perfil);
                *
                }*/
                return usuario;
            }
        return null;
    }






    public boolean isPErfilForFK(Object o)throws Exception{
        // este metodo resgata os dados do perfil pelo id do usuario
        boolean retorno = false;
        Usuario usuario = (Usuario)o;
        open();
        try{
            stmt = con.prepareStatement(IS_PERFIL_LOGIN);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
              rs = stmt.executeQuery();
            if(rs.next()){
                retorno = true;
            }
        }catch(Exception e){
            System.out.println("\n"+ e.getMessage()+"\n"+e.getStackTrace());
            return retorno;
        }finally{
            close();
            return retorno;

        }
    }

    public boolean isEmail(String email)throws Exception{
        open();
            stmt = con.prepareStatement(IS_EMAIL);
            stmt.setString(1, email);
            rs   = stmt.executeQuery();
            Usuario usuario;
            if(rs.next()){
                return true;
            }
        return false;
    }
    public  Usuario insert1(Object o)throws Exception{
        Usuario usuario = (Usuario)o;
        open();
            try{
                stmt  = con.prepareStatement(INSERT);

                stmt.setString(1,usuario.getNome());
                stmt.setString(2,usuario.getSobreNome());
                stmt.setString(3,usuario.getGenero());
                stmt.setString(4,usuario.getEmail());
                stmt.setString(5,usuario.getSenha());
                stmt.setString(6,usuario.getStatus());
                stmt.setString(7,usuario.getTipo());
                stmt.setDate(8,usuario.getDataNascimento());
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    usuario.setId(rs.getInt(1));
                }
                stmt.close();
            }catch(Exception e){
                  System.out.println(e.getMessage()+"\n"+e.getStackTrace());
                  return null;
            }finally{
               close();
               return usuario;
            }
    }
    public  boolean insert(Object o)throws Exception{
        Usuario usuario = (Usuario)o;
        open();
            try{
                stmt  = con.prepareStatement(INSERT);

                stmt.setString(1,usuario.getNome());
                stmt.setString(2,usuario.getSobreNome());
                stmt.setString(3,usuario.getGenero());
                stmt.setString(4,usuario.getEmail());
                stmt.setString(5,usuario.getSenha());
                stmt.setString(6,usuario.getStatus());
                stmt.setString(7,usuario.getTipo());
                stmt.setDate(8,usuario.getDataNascimento());
                stmt.executeUpdate();
    //            rs = stmt.getGeneratedKeys();

                stmt.close();
            }catch(Exception e){
                  System.out.println(e.getMessage()+"\n"+e.getStackTrace());
                  return false;
            }finally{
               close();
               return true;
            }
    }
//    public void insertUsuarioPerfil(Object usu,Object perf)throws Exception{
//        Usuario usuario  = (Usuario)usu;
//        Perfil  perfil   = (Perfil)perf;
//        PerfilDao perfilDao = new PerfilDao();
//        open();
//            stmt  = con.prepareStatement(INSERT);
//            stmt.setString(1,usuario.getEmail());
//            stmt.setString(2,usuario.getSenha());
//            stmt.setString(3,usuario.getStatus());
//            stmt.setString(4,usuario.getData_cadastro());
//            stmt.executeUpdate();
//            rs = stmt.getGeneratedKeys();
//            if(rs.next()){
//              usuario.setId_usuario(rs.getInt(1));
//              perfil.setUsuario(usuario);
//              perfilDao.insert(perfil);
//            }
//
//            stmt.close();
//        close();
//    }
    public void delete(int id)throws Exception{
        open();
            stmt  = con.prepareStatement(DELETE);
            stmt.setInt(1,id);
            stmt.close();
        close();
    }
    public void update_status(Object o)throws Exception{
        Usuario usuario = (Usuario)o;
        open();
            stmt  = con.prepareStatement(UPDATE_STATUS);
            stmt.setString(1,usuario.getStatus());
            stmt.setInt(2,usuario.getId_usuario());
            stmt.close();
        close();
    }
    public void update(Object o)throws Exception{
        Usuario usuario = (Usuario)o;
        open();
            stmt  = con.prepareStatement(UPDATE);
            stmt.setString(1,usuario.getEmail());
            stmt.setString(2,usuario.getSenha());
            stmt.setInt(3,usuario.getId_usuario());
            stmt.close();
        close();
    }

//    public Usuario find(int id)throws Exception{
//        // este metodo so mostra os dados do usuario para o admin
//        open();
//            stmt = con.prepareStatement(FIND);
//            stmt.setInt(1, id);
//            rs   = stmt.executeQuery();
//            Usuario usuario;
//            if(rs.next()){
//                usuario = new Usuario(rs.getInt("id_usuario"),rs.getString("email"),rs.getString("senha"),rs.getString("status"),rs.getString("data_cadastro"));
//                PerfilDao perfilDao = new PerfilDao();
//                Perfil perfil = perfilDao.findFK(usuario.getId_usuario());
//
//                usuario.setPerfil(perfil);
//
//
//                return usuario;
//            }
//        return null;
//    }
    public Usuario finds(int id)throws Exception{
        // este metodo so mostra os dados do usuario para o admin
        open();
            stmt = con.prepareStatement(FIND);
            stmt.setInt(1, id);
            rs   = stmt.executeQuery();
            Usuario usuario;
            if(rs.next()){
                usuario = new Usuario(rs.getInt("id_usuario"),rs.getString("email"),rs.getString("senha"),rs.getString("status"),rs.getString("data_cadastro"));


                return usuario;
            }
        return null;
    }

    public Usuario find_para_projeto(int id)throws Exception{
        // este metodo so mostra os dados do usuario para o admin
        open();
            stmt = con.prepareStatement(FIND);
            stmt.setInt(1, id);
            rs   = stmt.executeQuery();
            Usuario usuario;
            if(rs.next()){
                usuario = new Usuario(rs.getInt("id_usuario"),rs.getString("email"),rs.getString("senha"),rs.getString("status"),rs.getString("data_cadastro"));


                return usuario;
            }
        return null;
    }


    public Usuario login(Object o)throws Exception{
        // este metodo so mostra os dados do usuario para o admin
        Usuario usuario = (Usuario)o;
        open();
            stmt = con.prepareStatement(LOGIN);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            rs   = stmt.executeQuery();
            if(rs.next()){
                usuario = new Usuario(rs.getInt("id"),
                                      rs.getString("email"),
                                      rs.getString("senha"),
                                      rs.getString("status"),
                                      rs.getString("data_nascimento"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobreNome(rs.getString("sobre_nome"));
                usuario.setTipo(rs.getString("tipo"));
                System.out.println("nome do usuario Logado : "  +  usuario.getNome());
                usuario.setPerfil(null);
               /* PerfilDao  perfilDao = new PerfilDao();
                if(perfilDao.findFK(rs.getInt("id_usuario"))!=null){
                     Perfil perfil= new Perfil();
                     int id_usuario= usuario.getId_usuario();
                     perfil =(Perfil)perfilDao.findFK(id_usuario);
                     usuario.setPerfil(perfil);
                *
                }*/
                return usuario;
            }
        return null;
    }


    public boolean verifica_admin()throws Exception{
        // este metodo so mostra os dados do usuario para o admin
        open();
            stmt = con.prepareStatement(VERIFICA_ADMIN);
            rs   = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
        return false;
    }
//    public List findAll()throws Exception{
//        // este metodo so mostra os dados do usuario para o admin
//        open();
//            stmt = con.prepareStatement(FINDALL);
//            rs   = stmt.executeQuery();
//            List<Usuario> resp= new ArrayList<Usuario>();
//
//            while(rs.next()){
//                Usuario usuario = new Usuario(rs.getInt("id_usuario"),rs.getString("email"),rs.getString("senha"),rs.getString("status"),rs.getString("data_cadastro") );
//                PerfilDao  perfilDao = new PerfilDao();
//
//                if(perfilDao.findFK(usuario.getId_usuario())!=null){
//                     Perfil perfil= new Perfil();
//                     perfil =(Perfil)perfilDao.findFK(usuario.getId_usuario());
//                     usuario.setPerfil(perfil);
//                }
//                resp.add(usuario);
//            }
//        return resp;
//    }

//    public Usuario find_USU(int id)throws Exception{
//        // este metodo so mostra os dados do usuario para o admin
//        open();
//            stmt = con.prepareStatement(FIND);
//            rs   = stmt.executeQuery();
//            stmt.setInt(1, id);
//            //List<Usuario> resp= new ArrayList<Usuario>();
//            Usuario usuario;
//            if(rs.next()){
//                usuario = new Usuario(rs.getInt("id_usuario"),rs.getString("email"),rs.getString("senha"),rs.getString("status"),rs.getString("data_cadastro") );
//                PerfilDao  perfilDao = new PerfilDao();
//
//                if(perfilDao.findFK(usuario.getId_usuario())!=null){
//                     Perfil perfil= new Perfil();
//                     perfil =(Perfil)perfilDao.findFK(usuario.getId_usuario());
//                     usuario.setPerfil(perfil);
//                }
//             return usuario;
//            }
//        return null;
//    }







//    public List findAllAdmin()throws Exception{
//        // este metodo so mostra os dados do usuario para o admin
//        open();
//            stmt = con.prepareStatement(FINDALL);
//            rs   = stmt.executeQuery();
//            List<Usuario> resp= new ArrayList<Usuario>();
//
//            while(rs.next()){
//                Usuario usuario = new Usuario(rs.getInt("id_usuario"),rs.getString("email"),rs.getString("senha"),rs.getString("status"),rs.getString("data_cadastro") );
//                PerfilDao  perfilDao = new PerfilDao();
//
//                if(perfilDao.findFK(usuario.getId_usuario())!=null){
//                     Perfil perfil= new Perfil();
//                     perfil =(Perfil)perfilDao.findFK(usuario.getId_usuario());
//                     usuario.setPerfil(perfil);
//                }
//                resp.add(usuario);
//            }
//        return resp;
//    }


}

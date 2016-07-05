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
import java.util.*;
public class PropostaDao extends Dao {
    private static final String INSERT         = "insert into proposta(id, descricao, recompensas, links, valor_de, valor_ate, data_cadastro,status,id_categoria_fk, id_perfil_fk)" +
                                                 "values(null, ?, ?, ?, ?, ?, sysdate(), ?, ?, ?)";

    private static final String DELETE                    = "delete from proposta where id_proposta=?";
    private static final String FINDALL                   = "select * from proposta ";
    private static final String FIND_WHERE_ID             = "select * from proposta where id_proposta=?";
    private static final String FINDALL_TO_PERFIL         = "select * from proposta where id_perfil_fk=?";
    private static final String FINDALL_PELO_STATUS       = "select * from proposta where status=? and id_perfil_fk=? ";
    private static final String FINDALL_PELA_CATEGORIA    = "select * from proposta where id_categoria_fk=? ";
    private static final String UPDATE                    = "update proposta set descricao=?, recompensas status=? where id_proposta=?";
    private static final String UPDATE_STATUS             = "update proposta set status=? where id=?";

    private static final String TOTAL_PROPOSTA_BY_PERFIL = "select count(1)as totalPropostas  from proposta where id_perfil_fk=?";
   // private static final String PROPOSTA_BY_PERFIL_AND_STATUS = "select count(1)as totalPropostas  from proposta where id_perfil_fk=? and status='0'";
    private static final String PROPOSTA_NAO_LIDA_BY_PERFIL = "select count(1)as totalPropostas  from proposta where id_perfil_fk=? and status='0'";
    private static final String PROPOSTAS_ACEITAS_BY_PERFIL = "select count(1)as totalPropostas  from proposta where id_perfil_fk=? and status='A'";
    private static final String PROPOSTAS_REGEITADAS_BY_PERFIL = "select count(1)as totalPropostas  from proposta where id_perfil_fk=? and status='R'";
    private static final String PROPOSTAS_BY_STATUS = "select count(1) as totalPropostas  from proposta where  status=?";
    private static final String PROPOSTAS_BY_PERFIL_AND_STATUS = "select count(1)as totalPropostas  from proposta where id_perfil_fk=? and status=?";
    private static final String TOTAL_PROPOSTAS = "select count(1)as totalPropostas  from proposta ";
    
    private static final String FINDALL_PROPOSTA_EXT_BY_STATUS =
" select p.id as id_proposta, p.valor_de, p.valor_ate,p.data_cadastro, p.status, p.descricao as descricao," +
"        c.id as id_categoria, c.nome as categoria, " +
"        per.foto , " +
"        u.id as id_usuario, u.nome as usuario, u.email " +
"   from proposta p inner join categoria c on c.id = p.id_categoria_fk " +
"                   inner join perfil per  on per.id =p.id_perfil_fk " +
"                   inner join  usuario u   on u.id  = per.id_usuario_fk " +
" where p.status =UPPER(?)";

    private static final String FIND_PROPOSTA_EXT_BY_ID =
" select p.id as id_proposta, p.valor_de, p.valor_ate,p.data_cadastro, p.status, p.descricao as descricao, p.recompensas,p.links, " +
"        c.id as id_categoria, c.nome as categoria, " +
"        per.foto , " +
"        u.nome as usuario, u.email " +
"   from proposta p inner join categoria c on c.id = p.id_categoria_fk " +
"                   inner join perfil per  on per.id =p.id_perfil_fk " +
"                   inner join  usuario u   on u.id  = per.id_usuario_fk " +
" where p.id =?";

    private static final String UPDATE_STADO="update proposta set status=? where id=?";

    private static final String FIND_PROPOSTA_DO_PERFIL_COM_STATUS =
" select p.id as id_proposta, p.valor_de, p.valor_ate,p.data_cadastro, p.status, p.descricao as descricao, p.recompensas,p.links, " +
"        c.id as id_categoria, c.nome as categoria, " +
"        per.foto , " +
"        u.nome as usuario, u.email " +
"   from proposta p inner join categoria c on c.id = p.id_categoria_fk " +
"                   inner join perfil per  on per.id =p.id_perfil_fk " +
"                   inner join  usuario u   on u.id  = per.id_usuario_fk " +
" where p.id_perfil_fk =? and p.status=upper(?)";

public boolean atualizaStatus(Proposta proposta)throws Exception{
    open();
    try{
      stmt = con.prepareStatement(UPDATE_STATUS);
      stmt.setInt(1, proposta.getStatus());
      stmt.setInt(2, proposta.getId());
      stmt.executeUpdate();
    }catch(Exception e){
        System.out.println(e.getMessage()+"\n"+e.getStackTrace());
        return false;
    }finally{
        
        if(stmt!=null)stmt.close();
        close();
    }
    return true;
}


public Proposta retorna_proposta_extranet_by_perfil_and_status(Perfil p,int status)throws Exception{
         open();
        Proposta proposta   = null;
        try{
            stmt = con.prepareStatement(FIND_PROPOSTA_DO_PERFIL_COM_STATUS);
            stmt.setInt(1, p.getId());
            stmt.setInt(2, status);
            rs   = stmt.executeQuery();

            Categoria categoria = null;
            Perfil    perfil    = null;
            Usuario usuario     = null;
           if(rs.next()){
                 proposta = new Proposta();
                 categoria = new Categoria();
                 perfil    = new Perfil();
                 usuario   = new Usuario();

                 proposta.setId(rs.getInt("id_proposta")); proposta.setValor_de(rs.getInt("valor_de"));
                 proposta.setValor_ate(rs.getInt("valor_ate"));    proposta.setData_emissao(rs.getDate("data_cadastro"));
                 proposta.setStatus(rs.getInt("status"));
                 proposta.setDescricao(rs.getString("descricao"));
                 proposta.setRecompensas(rs.getString("recompensas"));
                 proposta.setLinks(rs.getString("links"));

                 categoria.setId(rs.getInt("id_categoria")); categoria.setNome(rs.getString("categoria"));

                 proposta.setCategoria(categoria);
                 perfil.setFoto(rs.getString("foto"));
                 usuario.setNome(rs.getString("usuario"));
                 usuario.setEmail(rs.getString("email"));
                 usuario.setPerfil(perfil);

                 perfil.setUsuario(usuario);

                 proposta.setPerfil(perfil);

               // return rs.getInt("totalPropostas");

            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return null;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return  proposta;
    }
public Proposta retorna_proposta_extranet_by_id(int id)throws Exception{
         open();
        Proposta proposta   = null;
        try{
            stmt = con.prepareStatement(FIND_PROPOSTA_EXT_BY_ID);
            stmt.setInt(1, id);
            rs   = stmt.executeQuery();
            
            Categoria categoria = null;
            Perfil    perfil    = null;
            Usuario usuario     = null;
           if(rs.next()){
                 proposta = new Proposta();
                 categoria = new Categoria();
                 perfil    = new Perfil();
                 usuario   = new Usuario();

                 proposta.setId(rs.getInt("id_proposta")); proposta.setValor_de(rs.getInt("valor_de"));
                 proposta.setValor_ate(rs.getInt("valor_ate"));    proposta.setData_emissao(rs.getDate("data_cadastro"));
                 proposta.setStatus(rs.getInt("status"));
                 proposta.setDescricao(rs.getString("descricao"));
                 proposta.setRecompensas(rs.getString("recompensas"));
                 proposta.setLinks(rs.getString("links"));

                 categoria.setId(rs.getInt("id_categoria")); categoria.setNome(rs.getString("categoria"));

                 proposta.setCategoria(categoria);
                 perfil.setFoto(rs.getString("foto"));
                 usuario.setNome(rs.getString("usuario"));
                 usuario.setEmail(rs.getString("email"));
                 usuario.setPerfil(perfil);

                 perfil.setUsuario(usuario);

                 proposta.setPerfil(perfil);

               // return rs.getInt("totalPropostas");
                 
            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return null;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return  proposta;
    }
    
public List<Proposta> retorna_proposta_extranet_by_status(int status)throws Exception{
         open();
         List<Proposta> resp = new ArrayList<Proposta>();
        try{
            stmt = con.prepareStatement(FINDALL_PROPOSTA_EXT_BY_STATUS);
            stmt.setInt(1, status);
            rs   = stmt.executeQuery();
            Proposta proposta   = null;
            Categoria categoria = null;
            Perfil    perfil    = null;
            Usuario usuario     = null;
            while(rs.next()){
                 proposta = new Proposta();
                 categoria = new Categoria();
                 perfil    = new Perfil();
                 usuario   = new Usuario();

                 proposta.setId(rs.getInt("id_proposta")); proposta.setValor_de(rs.getInt("valor_de"));
                 proposta.setValor_ate(rs.getInt("valor_ate"));    proposta.setData_emissao(rs.getDate("data_cadastro"));
                 proposta.setStatus(rs.getInt("status"));
                 String[] arrDescricao  = rs.getString("descricao").split(" ");
                 String descricao = null;
                 if( arrDescricao.length != 0 ){
                     for(int i=0;i <arrDescricao.length; i++){
                         if(i<10){
                            if( i==0 ){
                                    descricao =  arrDescricao[i];
                             }else{
                                  descricao +=  arrDescricao[i];
                               }
                            }
                     }
                 }else{
                     descricao =  arrDescricao.toString();

                 }
                 
                 
                 proposta.setDescricao(descricao);

                 categoria.setId(rs.getInt("id_categoria")); categoria.setNome(rs.getString("categoria"));
                 
                 proposta.setCategoria(categoria);
                 perfil.setFoto(rs.getString("foto"));
                 usuario.setId(rs.getInt("id_usuario"));
                 usuario.setNome(rs.getString("usuario"));
                 usuario.setEmail(rs.getString("email"));
                 usuario.setPerfil(perfil);

                 perfil.setUsuario(usuario);

                 proposta.setPerfil(perfil);

               // return rs.getInt("totalPropostas");
                 resp.add(proposta);
            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return null;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return  resp;
    }




    public int totalPropostas()throws Exception{
         open();
        try{
            stmt = con.prepareStatement(TOTAL_PROPOSTAS);
            rs   = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("totalPropostas");
            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return 0;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return 0;
    }


    public int propostasByStatus(String status)throws Exception{
         open();
        try{
            stmt = con.prepareStatement(PROPOSTAS_BY_STATUS);

            stmt.setString(1,status);
            rs   = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("totalPropostas");
            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return 0;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return 0;
    }
    public int propostasByPerfilAndStatus(Perfil perfil,int status)throws Exception{
         open();
        try{
            stmt = con.prepareStatement(PROPOSTAS_BY_PERFIL_AND_STATUS);
            stmt.setInt(1,perfil.getId());
            stmt.setInt(2,status);
            rs   = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("totalPropostas");
            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return 0;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return 0;
    }
    public int totalPropostasRejeitadasByPerfil(Perfil perfil)throws Exception{
         open();
        try{
            stmt = con.prepareStatement(PROPOSTAS_REGEITADAS_BY_PERFIL);
            stmt.setInt(1,perfil.getId());
            rs   = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("totalPropostas");
            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return 0;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return 0;
    }

    public int totalPropostasAceitasByPerfil(Perfil perfil)throws Exception{
         open();
        try{
            stmt = con.prepareStatement(PROPOSTAS_ACEITAS_BY_PERFIL);
            stmt.setInt(1,perfil.getId());
            rs   = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("totalPropostas");
            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return 0;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return 0;

    }


    public int quntPropostaNaoLidasByPerfil(Perfil perfil)throws Exception{
         open();
        try{
            stmt = con.prepareStatement(TOTAL_PROPOSTA_BY_PERFIL);
            stmt.setInt(1,perfil.getId());
            rs   = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("totalPropostas");
            }
        }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+ e.getStackTrace() +"\n");
            return 0;
        }finally{
            stmt.close();
            rs.close();
            close();
        }
        return 0;

    }


    public boolean insert(Object o)throws Exception{
         Proposta proposta = (Proposta)o;
         open();
         try{
             stmt = con.prepareStatement(INSERT);
             stmt.setString(1, proposta.getDescricao());
             stmt.setString(2, proposta.getRecompensas());
             stmt.setString(3, proposta.getLinks());
             stmt.setInt(4, proposta.getValor_de());
             stmt.setInt(5, proposta.getValor_ate());

             stmt.setInt(6, proposta.getStatus());
             
             stmt.setInt(7, proposta.getCategoria().getId_categoria());
             stmt.setInt(8, proposta.getPerfil().getId());
             stmt.executeUpdate();
             System.out.println("\n-->"+proposta.getDescricao());
             System.out.println("\n-->"+proposta.getRecompensas() );
             System.out.println("\n-->"+proposta.getLinks() );
             System.out.println("\n-->"+proposta.getValor_de() );
             System.out.println("\n-->"+proposta.getValor_ate() );
             System.out.println("\n-->"+proposta.getStatus() );
             System.out.println("\n-->"+proposta.getCategoria().getId() );
             System.out.println("\n-->"+proposta.getPerfil().getId());
         }catch(Exception e){
             System.out.println("\n"+e.getMessage()+"\n"+e.getStackTrace());
             return false;
         }finally{
             stmt.close();
             close();
         }
         return true;
     }
//     public void UPDATE(Object o)throws Exception{
//         Proposta proposta = new Proposta();
//         open();
//             stmt = con.prepareStatement(INSERT);
//             stmt.setString(1, proposta.getDescricao());
//             stmt.setString(2, proposta.getRecompensas());
//             stmt.setString(3, proposta.getLinks());
//             stmt.setInt(4, proposta.getValor_de());
//             stmt.setString(5, proposta.getStatus());
//             stmt.setString(6, proposta.getData_emissao());
//             stmt.setInt(7, proposta.getCategoria().getId_categoria());
//             stmt.setInt(8, proposta.getPerfil().getId());
//             stmt.executeUpdate();
//             stmt.close();
//          close();
//
//     }
//     public void updateStatus(Object o)throws Exception{
//         Proposta proposta = (Proposta)o;
//         ProjetoDao projetoDao = new ProjetoDao();
//         open();
//             stmt = con.prepareStatement(UPDATE_STATUS);
//             if(projetoDao.find_status_proposta(proposta)){
//                  stmt.setString(1, "on");
//             }else{
//             stmt.setString(1, proposta.getStatus());
//             }
//             stmt.setInt(2, proposta.getId_proposta());
//             stmt.executeUpdate();
//             stmt.close();
//          close();
//
//     }
//     public void DELETE(Object o)throws Exception{
//         Proposta proposta = new Proposta();
//         open();
//             stmt = con.prepareStatement(DELETE);
//             stmt.setInt(1, proposta.getId_proposta());
//             stmt.executeUpdate();
//             stmt.close();
//          close();
//
//     }
//     public List<Proposta> mostra_tudo()throws Exception{
//         open();
//             stmt = con.prepareStatement(FINDALL);
//             rs   = stmt.executeQuery();
//             List<Proposta> resp = new ArrayList<Proposta>();
//             while(rs.next()){
//                 Proposta proposta = new  Proposta();
//                 resp.add(proposta);
//             }
//         return resp;
//     }
//
//
//     public List<Proposta> findAll_TO_PERFIL(int id_perfil)throws Exception{
//         open();
//             stmt = con.prepareStatement(FINDALL_TO_PERFIL);
//             stmt.setInt(1 , id_perfil);
//             rs   = stmt.executeQuery();
//             List<Proposta> resp = new ArrayList<Proposta>();
//
//             while(rs.next()){
//                 Proposta proposta = new  Proposta(rs.getInt("id_proposta"),
//                                          rs.getString("descricao"),
//                                          rs.getString("recompensas"),
//                                          rs.getString("links"),
//                                          rs.getString("valor"),
//                                          rs.getString("status"),
//                                          rs.getString("data_emissao"));
//                 CategoriaDao categoriaDao = new CategoriaDao();
//                 Categoria categoria = (Categoria)categoriaDao.find1(rs.getInt("id_categoria_fk"));
//                 proposta.setCategoria(categoria);
//
//                 ProjetoDao projetoDao = new ProjetoDao();
//                 Projeto projeto = projetoDao.find(proposta);
//                 if(projeto!=null){
//                    proposta.setProjeto(projeto);
//                 }
//
//                 resp.add(proposta);
//             }
//         return resp;
//     }
//
////     public List<Proposta> findAll()throws Exception{
////         open();
////             stmt = con.prepareStatement(FINDALL);
////             rs   = stmt.executeQuery();
////             List<Proposta> resp = new ArrayList<Proposta>();
////
////             while(rs.next()){
////                CategoriaDao categoriaDao = new CategoriaDao();
////                PerfilDao       perfilDao        = new PerfilDao();
////                 Categoria categoria = (Categoria)categoriaDao.find(rs.getInt("id_categoria_fk"));
////                 Perfil perfil       = (Perfil) perfilDao.findFF(rs.getInt("id_perfil_fk"));
////                 Proposta proposta = new  Proposta(rs.getInt("id_proposta"),
////                                          rs.getString("descricao"),
////                                          rs.getString("recompensas"),
////                                          rs.getString("links"),
////                                          rs.getString("valor"),
////                                          rs.getString("status"),
////                                          rs.getString("data_emissao"),
////                                          categoria,
////                                          perfil);
////
////                 resp.add(proposta);
////             }
////         return resp;
////     }
//     public int findAll_pelo_status(Perfil perfil, String status)throws Exception{
//         open();
//             stmt = con.prepareStatement(FINDALL_PELO_STATUS);
//             stmt.setString(1, status);
//             stmt.setInt(2, perfil.getId());
//             rs   = stmt.executeQuery();
//             int cont=0;
//             while(rs.next()){
//                  cont++;
//
//
//             }
//         return cont;
//     }
//     public Proposta findAll_pelo_status1(Perfil perfil, String status)throws Exception{
//         open();
//             stmt = con.prepareStatement(FINDALL_PELO_STATUS);
//             stmt.setString(1, status);
//             stmt.setInt(2, perfil.getId());
//             rs   = stmt.executeQuery();
//             //int cont=0;
//
//             if(rs.next()){
////                  cont++;
////                   Proposta  proposta = new  Proposta(rs.getInt("id_proposta"),
////                                              rs.getString("descricao"),
////                                              rs.getString("recompensas"),
////                                              rs.getString("links"),
////                                              rs.getString("valor"),
////                                              rs.getString("status"),
////                                              rs.getString("data_emissao")
////                                          );
////                     ProjetoDao projetoDao = new ProjetoDao();
////                     Projeto projeto = (Projeto)projetoDao.find(proposta);
////                     proposta.setProjeto(projeto);
//                     // RecompensaDao recDao= new RecompensaDao();
////                   if(recDao.findAll(proposta.getProjeto())!= null){
////                   }
//                   //ProjetoDao projetoDao = new ProjetoDao();
////                    proposta.setId_proposta(rs.getInt("id_proposta"));
//
//
//                 Proposta proposta = new  Proposta(rs.getInt("id_proposta"),
//                                          rs.getString("descricao"),
//                                          rs.getString("recompensas"),
//                                          rs.getString("links"),
//                                          rs.getString("valor"),
//                                          rs.getString("status"),
//                                          rs.getString("data_emissao"));
//                 CategoriaDao categoriaDao = new CategoriaDao();
//                 Categoria categoria = (Categoria)categoriaDao.find1(rs.getInt("id_categoria_fk"));
//                 proposta.setCategoria(categoria);
//
//                 ProjetoDao projetoDao = new ProjetoDao();
//                 Projeto projeto = projetoDao.find(proposta);
//                 if(projeto!=null){
//                    proposta.setProjeto(projeto);
//                 }
//
//                return proposta;
//             }
//         return null;
//     }
//     public List<Proposta> findAll_pela_categoria(int id_categoria)throws Exception{
//         open();
//             stmt = con.prepareStatement(FINDALL_PELA_CATEGORIA);
//             stmt.setInt(1, id_categoria);
//             rs   = stmt.executeQuery();
//             //int cont=0;
//             List<Proposta> resp = new ArrayList<Proposta>();
//             while(rs.next()){
//                  //cont++;
//                   Proposta  proposta = new  Proposta(rs.getInt("id_proposta"),
//                                              rs.getString("descricao"),
//                                              rs.getString("recompensas"),
//                                              rs.getString("links"),
//                                              rs.getString("valor"),
//                                              rs.getString("status"),
//                                              rs.getString("data_emissao")
//                                          );
//                     ProjetoDao projetoDao = new ProjetoDao();
//                     Projeto projeto = (Projeto)projetoDao.find(proposta);
//                     proposta.setProjeto(projeto);
//                     resp.add(proposta);
//             }
//         return resp;
//     }
//
//
//
//
//     public Object resgata_dados(Perfil perfil, String status)throws Exception{
//         open();
//             stmt = con.prepareStatement(FINDALL_PELO_STATUS);
//             stmt.setString(1, status);
//             stmt.setInt(2, perfil.getId());
//             rs   = stmt.executeQuery();
//             int cont=0;
//
//                    Proposta proposta=null;
//             if(rs.next()){
//                 CategoriaDao categoriaDao = new CategoriaDao();
//                 Categoria categoria = (Categoria)categoriaDao.find(rs.getInt("id_categoria_fk"));
//                 proposta = new  Proposta(rs.getInt("id_proposta"),
//                                          rs.getString("descricao"),
//                                          rs.getString("recompensas"),
//                                          rs.getString("links"),
//                                          rs.getString("valor"),
//                                          rs.getString("status"),
//                                          rs.getString("data_emissao"),
//                                          categoria,
//                                          perfil);
//
//             }
//         return proposta;
//     }
//
//     public Object resgate_proposta_pelo_id(int id)throws Exception{
//         open();
//             stmt = con.prepareStatement(FIND_WHERE_ID);
//             stmt.setInt(1, id);
//             rs   = stmt.executeQuery();
//             int cont=0;
//
//                    Proposta proposta=null;
//             if(rs.next()){
//                 CategoriaDao categoriaDao = new CategoriaDao();
//                 Categoria categoria = (Categoria)categoriaDao.find(rs.getInt("id_categoria_fk"));
//                 PerfilDao perfilDao   = new PerfilDao();
//                 Perfil perfil   = (Perfil)perfilDao.find_site_aberto(rs.getInt("id_perfil_fk"));
//               //  Perfil perfil   =null;
//                 proposta = new  Proposta(rs.getInt("id_proposta"),
//                                          rs.getString("descricao"),
//                                          rs.getString("recompensas"),
//                                          rs.getString("links"),
//                                          rs.getString("valor"),
//                                          rs.getString("status"),
//                                          rs.getString("data_emissao"),
//                                          categoria,
//                                          perfil);
//
//             }
//         return proposta;
//     }

//     public List<Proposta> findAll()throws ExecutionException}
}

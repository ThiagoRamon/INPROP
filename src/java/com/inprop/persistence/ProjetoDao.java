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
import com.inprop.model.Categoria;
import com.inprop.model.Cidade;
import com.inprop.model.Endereco;
import com.inprop.model.Estado;
import com.inprop.model.Foto;
import com.inprop.model.Pais;
import com.inprop.model.Perfil;
import com.inprop.model.Recompensa;
import com.inprop.model.Projeto;
import com.inprop.model.Proposta;
import com.inprop.model.Usuario;
import com.inprop.model.Video;
import java.util.ArrayList;
import java.util.List;





public class ProjetoDao extends Dao{


    private static final String INSERT=" insert into projeto(id, foto, url_video, titulo, apresentacao, descricao,   " +
                                       " data_inicial, data_final, data_cadastro, status,valor_total,id_proposta_fk) " +
                                       " values(null, ?,?,?,?,?,sysdate(),?,sysdate(),?,?,?);";


    private static final String FINDALL_TO_PROPOSTA="select * from projeto   where id_proposta_fk=?";
    private static final String FINDALL="select * from projeto where status=?";
    private static final String FIND_WHERE_ID="select * from projeto where id_projeto=?";
    private static final String FINDALL_STATUS_CATEGORIA="select * from projeto where status='em andamento' and id_proposta_fk=?";
    private static final String FINDALL_="select * from projeto p inner join proposta pro" +
            "                             perfil per where status='em andamento' and id_proposta_fk=?";


     private static final String UPDATE_STATUS_POR_PROJETO ="update projeto set status = ? where id = ?";


     public boolean updatePorIdDoProjeto(int idPerfil, int status_atual)throws Exception{
         open();
         try{
             stmt = con.prepareStatement(UPDATE_STATUS_POR_PROJETO);
             stmt.setInt(1, status_atual);
             stmt.setInt(2, idPerfil);
             stmt.executeUpdate();

         }catch(Exception e){
             System.out.println("Error : metodo update classe projetoDao");
             return false;
         }finally{

             stmt.close();
             con.close();
             close();

         }

         return true;
     }

//private static final String FINDALL_PROJECT =
//" select p.id as id_projeto, p.foto, p.apresentacao, p.titulo, p.data_inicial, p.data_final, p.status,p.valor_total, "+
// "       pro.id as id_proposta, " +
// "         cat.id as id_categoria,  cat.nome as categoria, "+
//"        per.id as id_perfil,u.id   as id_usuario,  u.nome as usuario, "+
//"        e.id   as id_endereco, "+
//"        cp.id  as id_cep, cp.numero, "+
//"        bar.id as id_bairro, bar.nome as bairro, "+
//"        cid.id as id_cidade, cid.nome as cidade," +
//        " est.id as id_estado, est.uf,             "+
//"        ps.id as id_pais ,ps.sigla   , sum(r.valor) as levantado                             "+
//  " from projeto p inner join proposta  pro        on pro.id          = p.id_proposta_fk   "+
//  "              inner join categoria cat          on cat.id          = pro.id_categoria_fk "+
//  "               inner join perfil    per         on per.id          =  pro.id_perfil_fk  "+
//  "               inner join usuario   u           on u.id            = per.id_usuario_fk  "+
//  "               inner join endereco  e           on e.id_perfil_fk  = per.id             "+
//  "               inner join cep       cp          on cp.id           = e.id_cep_fk        "+
//  "               inner join bairro    bar         on bar.id          = cp.id_bairro_fk    "+
//  "               inner join cidade    cid         on cid.id          = bar.id_cidade_fk   "+
//  "               inner join estado     est        on est.id          = cid.id_estado_fk   "+
//  "               inner join pais      ps          on ps.id           = est.id_pais_fk              " +
//  "               inner join recompensa r          on r.id_projeto_fk = p.id                         "+
//  "               inner join apoio      ap         on ap.id_recompensa_fk = r.id and ap.status = 0 group by p.id;   ";
private static final String FINDALL_PROJECT =
" select p.id as id_projeto, p.foto, p.apresentacao, p.titulo, p.data_inicial, p.data_final, p.status,p.valor_total, "+
 "       pro.id as id_proposta, " +
 "         cat.id as id_categoria,  cat.nome as categoria, "+
"        per.id as id_perfil,u.id   as id_usuario,  u.nome as usuario, "+
"        e.id   as id_endereco, "+
"        cp.id  as id_cep, cp.numero, "+
"        bar.id as id_bairro, bar.nome as bairro, "+
"        cid.id as id_cidade, cid.nome as cidade," +
        " est.id as id_estado, est.uf,             "+
"        ps.id as id_pais ,ps.sigla                            "+
  " from projeto p inner join proposta  pro        on pro.id          = p.id_proposta_fk   "+
  "              inner join categoria cat          on cat.id          = pro.id_categoria_fk "+
  "               inner join perfil    per         on per.id          =  pro.id_perfil_fk  "+
  "               inner join usuario   u           on u.id            = per.id_usuario_fk  "+
  "               inner join endereco  e           on e.id_perfil_fk  = per.id             "+
  "               inner join cep       cp          on cp.id           = e.id_cep_fk        "+
  "               inner join bairro    bar         on bar.id          = cp.id_bairro_fk    "+
  "               inner join cidade    cid         on cid.id          = bar.id_cidade_fk   "+
  "               inner join estado     est        on est.id          = cid.id_estado_fk   "+
  "               inner join pais      ps          on ps.id           = est.id_pais_fk    where pro.status=3          " ;

private static final String FIND_PROJECT =
" select p.id as id_projeto, p.foto, p.apresentacao, p.titulo,date_format(p.data_final,'%Y-%m-%d') as data_final,date_format(p.data_inicial,'%Y-%m-%d') as data_inicial, p.status,p.valor_total, p.descricao as descricao_projeto,p.url_video, "+
 "       pro.id as id_proposta, " +
 "         cat.id as id_categoria,  cat.nome as categoria, "+
"        per.id as id_perfil, per.foto as foto_perfil,u.id   as id_usuario,  u.nome as usuario, "+
"        e.id   as id_endereco, "+
"        cp.id  as id_cep, cp.numero, "+
"        bar.id as id_bairro, bar.nome as bairro, "+
"        cid.id as id_cidade, cid.nome as cidade," +
        " est.id as id_estado, est.uf,             "+
"        ps.id as id_pais ,ps.sigla                            "+
  " from projeto p inner join proposta  pro        on pro.id          = p.id_proposta_fk   "+
  "              inner join categoria cat          on cat.id          = pro.id_categoria_fk "+
  "               inner join perfil    per         on per.id          =  pro.id_perfil_fk  "+
  "               inner join usuario   u           on u.id            = per.id_usuario_fk  "+
  "               inner join endereco  e           on e.id_perfil_fk  = per.id             "+
  "               inner join cep       cp          on cp.id           = e.id_cep_fk        "+
  "               inner join bairro    bar         on bar.id          = cp.id_bairro_fk    "+
  "               inner join cidade    cid         on cid.id          = bar.id_cidade_fk   "+
  "               inner join estado     est        on est.id          = cid.id_estado_fk   "+
  "               inner join pais      ps          on ps.id           = est.id_pais_fk    where p.id = ?          " ;

private static final String FIND_PROJECT_BY_ID_PERFIL_AND_STATUS_PROJETO=
" select p.id as id_projeto, p.foto, p.apresentacao, p.titulo,date_format(p.data_final,'%Y-%m-%d') as data_final,date_format(p.data_inicial,'%Y-%m-%d') as data_inicial, p.status,p.valor_total, p.descricao as descricao_projeto,p.url_video, "+
 "       pro.id as id_proposta, " +
 "         cat.id as id_categoria,  cat.nome as categoria, "+
"        per.id as id_perfil, per.foto as foto_perfil,u.id   as id_usuario,  u.nome as usuario, "+
"        e.id   as id_endereco, "+
"        cp.id  as id_cep, cp.numero, "+
"        bar.id as id_bairro, bar.nome as bairro, "+
"        cid.id as id_cidade, cid.nome as cidade," +
        " est.id as id_estado, est.uf,             "+
"        ps.id as id_pais ,ps.sigla                            "+
  " from projeto p inner join proposta  pro        on pro.id          = p.id_proposta_fk   "+
  "              inner join categoria cat          on cat.id          = pro.id_categoria_fk "+
  "               inner join perfil    per         on per.id          =  pro.id_perfil_fk  "+
  "               inner join usuario   u           on u.id            = per.id_usuario_fk  "+
  "               inner join endereco  e           on e.id_perfil_fk  = per.id             "+
  "               inner join cep       cp          on cp.id           = e.id_cep_fk        "+
  "               inner join bairro    bar         on bar.id          = cp.id_bairro_fk    "+
  "               inner join cidade    cid         on cid.id          = bar.id_cidade_fk   "+
  "               inner join estado     est        on est.id          = cid.id_estado_fk   "+
  "               inner join pais      ps          on ps.id           = est.id_pais_fk    where pro.id_perfil_fk = ? and pro.status=? " ;



   public static void main (String args[]){
       ProjetoDao projetoD = new ProjetoDao();
       try{
           
           System.out.println(projetoD.mostrarProjetos().size());

       }catch(Exception e){
       }
    }

      public Projeto retornaProjetoByPerfil(int id_perfil, int status)throws Exception{
       open();
         Projeto projeto = new Projeto();
         try{
             stmt = con.prepareStatement(FIND_PROJECT_BY_ID_PERFIL_AND_STATUS_PROJETO);
             stmt.setInt(1,id_perfil);
             stmt.setInt(2,status);
             rs   = stmt.executeQuery();
             if(rs.next()){
                 Pais pais = new Pais();
                 pais.setId(rs.getInt("id_pais"));

                 Estado   estado = new Estado();
                 estado.setId(rs.getInt("id_estado"));
                 estado.setUf(rs.getString("uf"));

                 Cidade  cidade = new Cidade();
                 cidade.setId(rs.getInt("id_cidade"));
                 cidade.setNome(rs.getString("cidade"));

                 Bairro  bairro = new Bairro();
                 bairro.setId(rs.getInt("id_bairro"));
                 bairro.setNome(rs.getString("bairro"));

                  CEP   cep = new CEP();
                  cep.setId(rs.getInt("id_cep"));
                  cep.setNumero(rs.getString("numero"));


                  Endereco endereco = new Endereco();
                  endereco.setId(rs.getInt("id_endereco"));


                  Perfil perfil = new Perfil();
                  perfil.setId(rs.getInt("id_perfil"));
                  perfil.setFoto(rs.getString("foto_perfil"));
                   ApoioDao apoioDao    = new ApoioDao();
                     try{
                         perfil.setMeusApoios(apoioDao.countApoioByPerfil(perfil.getId(),projeto.getId()));
                     }catch(Exception e ){
                         System.out.println(" projetoDao - retornaProjeto/quntidade de apoio deste usuario "+e.getMessage()+""+e.getStackTrace());
                     }


                  Usuario usuario = new Usuario();
                  usuario.setNome(rs.getString("usuario"));

                  Proposta proposta = new Proposta();
                  proposta.setId(rs.getInt("id_proposta"));

                  Categoria categoria = new Categoria();
                  categoria.setId(rs.getInt("id_categoria"));
                  categoria.setNome(rs.getString("categoria"));


                  projeto.setId(rs.getInt("id_projeto"));
                  projeto.setApresentacao(rs.getString("apresentacao"));
                  projeto.setDescricao(rs.getString("descricao_projeto"));
                  projeto.setLink_video(rs.getString("url_video"));
                  projeto.setTitulo(rs.getString("titulo"));
                  projeto.setFoto_projeto(rs.getString("foto"));
                  projeto.setData_inicial(rs.getDate("data_inicial"));
                  projeto.setData_final(rs.getDate("data_final"));
                  projeto.setStatus(rs.getInt("status"));
   //               projeto.setLevantado(rs.getInt("levantado"));
                  projeto.setValor_total(rs.getInt("valor_total"));
//
//                   int atingido =4;
//                   System.out.print("------"+atingido);
//                   atingido   = (projeto.getLevantado()*100)/projeto.getValor_total();
//                  projeto.setAtingido(atingido);


//                  val_porcentagem= valor_total_arrecadado*100/projeto.getValor_total();//100;
//                   int val_p= val_porcentagem;
//                   if(val_p>=100){
//                      val_p=100;
//                   }


                   java.util.Date data_atual = new java.util.Date();
//                   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                   Date date_inicial = (Date)formatter.parse(projeto.getData_inicial());
//                   Date date_final = (Date)formatter.parse(projeto.getData_final());

                  long dias_para_o_fim=0;
                  dias_para_o_fim  =  (projeto.getData_final().getTime() - data_atual.getTime());
                  dias_para_o_fim = dias_para_o_fim/1000/60/60/24;

                  projeto.setDias_restantes(dias_para_o_fim);

                  estado.setPais(pais);
                   cidade.setEstado(estado);
                    bairro.setCidade(cidade);
                     cep.setBairro(bairro);
                      endereco.setCep(cep);
                  perfil.setEndereco(endereco);
                   perfil.setUsuario(usuario);
                  proposta.setPerfil(perfil);
                   proposta.setProjeto(projeto);
                   proposta.setCategoria(categoria);
                  projeto.setProposta(proposta);



             }
         }catch(Exception e){
             System.out.println("---"+e.getMessage()+"\n"+e.getStackTrace());
         }finally{
             rs.close();
             stmt.close();
             close();
         }
         RecompensaDao recDao = new RecompensaDao();


         try{

               projeto.setRecompensa(recDao.findAll(projeto));

               int levantado =  0 ;
               int atingido  =  0;
               int pessoasApoiando= 0;
               for(Recompensa r  : projeto.getRecompensa()){
                    levantado  += r.getQuantidadeApoio()* r.getValor();
                    pessoasApoiando = pessoasApoiando + r.getQuantidadeApoio();
               }
               projeto.setPessoasApoiando(pessoasApoiando);
               projeto.setLevantado(levantado);
               atingido = (projeto.getLevantado()*100)/projeto.getValor_total();

               projeto.setAtingido(atingido);
//                   System.out.print("------"+atingido);
//                   atingido   = (projeto.getLevantado()*100)/projeto.getValor_total();
//                  projeto.setAtingido(atingido);


//                  val_porcentagem= valor_total_arrecadado*100/projeto.getValor_total();//100;
//                   int val_p= val_porcentagem;
//                   if(val_p>=100){
//                      val_p=100;
//                   }



         }catch(Exception e){
                System.out.println(e.getMessage()+"\n"+e.getStackTrace());
         }
         return projeto;
     }



      public Projeto retornaProjetoById(int id)throws Exception{
       open();
         Projeto projeto = new Projeto();
          int pessoasApoiando=0;
         try{
             stmt = con.prepareStatement(FIND_PROJECT);
             stmt.setInt(1,id);
             rs   = stmt.executeQuery();
             if(rs.next()){
                 Pais pais = new Pais();
                 pais.setId(rs.getInt("id_pais"));

                 Estado   estado = new Estado();
                 estado.setId(rs.getInt("id_estado"));
                 estado.setUf(rs.getString("uf"));

                 Cidade  cidade = new Cidade();
                 cidade.setId(rs.getInt("id_cidade"));
                 cidade.setNome(rs.getString("cidade"));

                 Bairro  bairro = new Bairro();
                 bairro.setId(rs.getInt("id_bairro"));
                 bairro.setNome(rs.getString("bairro"));

                  CEP   cep = new CEP();
                  cep.setId(rs.getInt("id_cep"));
                  cep.setNumero(rs.getString("numero"));


                  Endereco endereco = new Endereco();
                  endereco.setId(rs.getInt("id_endereco"));


                  Perfil perfil = new Perfil();
                  perfil.setId(rs.getInt("id_perfil"));
                  perfil.setFoto(rs.getString("foto_perfil"));
                   ApoioDao apoioDao   = new ApoioDao();

                     try{
                         perfil.setMeusApoios(apoioDao.countApoioByPerfil(perfil.getId(),projeto.getId()));
                         
                     }catch(Exception e ){
                         System.out.println(" projetoDao - retornaProjeto/quntidade de apoio deste usuario "+e.getMessage()+""+e.getStackTrace());
                     }
                     

                  Usuario usuario = new Usuario();
                  usuario.setNome(rs.getString("usuario"));

                  Proposta proposta = new Proposta();
                  proposta.setId(rs.getInt("id_proposta"));

                  Categoria categoria = new Categoria();
                  categoria.setId(rs.getInt("id_categoria"));
                  categoria.setNome(rs.getString("categoria"));

                  
                  projeto.setId(rs.getInt("id_projeto"));
                  projeto.setApresentacao(rs.getString("apresentacao"));
                  projeto.setDescricao(rs.getString("descricao_projeto"));
                  projeto.setLink_video(rs.getString("url_video"));
                  projeto.setTitulo(rs.getString("titulo"));
                  projeto.setFoto_projeto(rs.getString("foto"));
                  projeto.setData_inicial(rs.getDate("data_inicial"));
                  projeto.setData_final(rs.getDate("data_final"));
                  projeto.setStatus(rs.getInt("status"));
   //               projeto.setLevantado(rs.getInt("levantado"));
                  projeto.setValor_total(rs.getInt("valor_total"));
//
//                   int atingido =4;
//                   System.out.print("------"+atingido);
//                   atingido   = (projeto.getLevantado()*100)/projeto.getValor_total();
//                  projeto.setAtingido(atingido);


//                  val_porcentagem= valor_total_arrecadado*100/projeto.getValor_total();//100;
//                   int val_p= val_porcentagem;
//                   if(val_p>=100){
//                      val_p=100;
//                   }


                   java.util.Date data_atual = new java.util.Date();
//                   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                   Date date_inicial = (Date)formatter.parse(projeto.getData_inicial());
//                   Date date_final = (Date)formatter.parse(projeto.getData_final());

                  long dias_para_o_fim=0;
                  dias_para_o_fim  =  (projeto.getData_final().getTime() - data_atual.getTime());
                  dias_para_o_fim = dias_para_o_fim/1000/60/60/24;

                  projeto.setDias_restantes(dias_para_o_fim);

                  estado.setPais(pais);
                   cidade.setEstado(estado);
                    bairro.setCidade(cidade);
                     cep.setBairro(bairro);
                      endereco.setCep(cep);
                  perfil.setEndereco(endereco);
                   perfil.setUsuario(usuario);
                  proposta.setPerfil(perfil);
                   proposta.setProjeto(projeto);
                   proposta.setCategoria(categoria);
                  projeto.setProposta(proposta);



             }
         }catch(Exception e){
             System.out.println(e.getMessage()+"\n"+e.getStackTrace());
         }finally{
             rs.close();
             stmt.close();
             close();
         }
         RecompensaDao recDao = new RecompensaDao();
        

         try{

               projeto.setRecompensa(recDao.findAll(projeto));

               int levantado =  0 ;
               int atingido  =  0;
              
               for(Recompensa r  : projeto.getRecompensa()){
                   ApoioDao apd= new ApoioDao();
                    levantado  += r.getQuantidadeApoio()* r.getValor();
                    pessoasApoiando = pessoasApoiando + apd.countApoio(r);
               }
               
               projeto.setPessoasApoiando(pessoasApoiando);
               projeto.setLevantado(levantado);
               atingido = (projeto.getLevantado()*100)/projeto.getValor_total();

               projeto.setAtingido(atingido);
//                   System.out.print("------"+atingido);
//                   atingido   = (projeto.getLevantado()*100)/projeto.getValor_total();
//                  projeto.setAtingido(atingido);


//                  val_porcentagem= valor_total_arrecadado*100/projeto.getValor_total();//100;
//                   int val_p= val_porcentagem;
//                   if(val_p>=100){
//                      val_p=100;
//                   }


            
         }catch(Exception e){
                System.out.println(e.getMessage()+"\n"+e.getStackTrace());
         }
         return projeto;
     }

      
      public List<Projeto> mostrarProjetos()throws Exception{
       open();
         List<Projeto> resp = new ArrayList<Projeto>();
         try{
             stmt = con.prepareStatement(FINDALL_PROJECT);
             rs   = stmt.executeQuery();
             while(rs.next()){
            Pais pais = new Pais();
                 pais.setId(rs.getInt("id_pais"));

                 Estado   estado = new Estado();
                 estado.setId(rs.getInt("id_estado"));
                 estado.setUf(rs.getString("uf"));

                 Cidade  cidade = new Cidade();
                 cidade.setId(rs.getInt("id_cidade"));
                 cidade.setNome(rs.getString("cidade"));

                 Bairro  bairro = new Bairro();
                 bairro.setId(rs.getInt("id_bairro"));
                 bairro.setNome(rs.getString("bairro"));

                  CEP   cep = new CEP();
                  cep.setId(rs.getInt("id_cep"));
                  cep.setNumero(rs.getString("numero"));


                  Endereco endereco = new Endereco();
                  endereco.setId(rs.getInt("id_endereco"));


                  Perfil perfil = new Perfil();
                  perfil.setId(rs.getInt("id_perfil"));


                  Usuario usuario = new Usuario();
                  usuario.setNome(rs.getString("usuario"));

                  Proposta proposta = new Proposta();
                  proposta.setId(rs.getInt("id_proposta"));
                  
                  Categoria categoria = new Categoria();
                  categoria.setId(rs.getInt("id_categoria"));
                  categoria.setNome(rs.getString("categoria"));

                  Projeto   projeto = new Projeto();
                  projeto.setId(rs.getInt("id_projeto"));
                  projeto.setApresentacao(rs.getString("apresentacao"));
                  projeto.setTitulo(rs.getString("titulo"));
                  projeto.setFoto_projeto(rs.getString("foto"));
                  projeto.setData_inicial(rs.getDate("data_inicial"));
                  projeto.setData_final(rs.getDate("data_final"));
                  projeto.setStatus(rs.getInt("status"));
   //               projeto.setLevantado(rs.getInt("levantado"));
                  projeto.setValor_total(rs.getInt("valor_total"));
//
//                   int atingido =4;
//                   System.out.print("------"+atingido);
//                   atingido   = (projeto.getLevantado()*100)/projeto.getValor_total();
//                  projeto.setAtingido(atingido);
                  

//                  val_porcentagem= valor_total_arrecadado*100/projeto.getValor_total();//100;
//                   int val_p= val_porcentagem;
//                   if(val_p>=100){
//                      val_p=100;
//                   }


                   java.util.Date data_atual = new java.util.Date();
//                   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                   Date date_inicial = (Date)formatter.parse(projeto.getData_inicial());
//                   Date date_final = (Date)formatter.parse(projeto.getData_final());

                  long dias_para_o_fim=0;
                  dias_para_o_fim  =  (projeto.getData_final().getTime() - data_atual.getTime());
                  dias_para_o_fim = dias_para_o_fim/1000/60/60/24;
                  
                  projeto.setDias_restantes(dias_para_o_fim);

                  estado.setPais(pais);
                   cidade.setEstado(estado);
                    bairro.setCidade(cidade);
                     cep.setBairro(bairro);
                      endereco.setCep(cep);
                  perfil.setEndereco(endereco);
                   perfil.setUsuario(usuario);
                  proposta.setPerfil(perfil);
                   proposta.setProjeto(projeto);
                   proposta.setCategoria(categoria);
                  projeto.setProposta(proposta);


                  resp.add(projeto);

             }
         }catch(Exception e){
             System.out.println(e.getMessage()+"\n"+e.getStackTrace());
         }finally{
             rs.close();
             stmt.close();
             close();
         }
         RecompensaDao recDao = new RecompensaDao();
         List<Projeto> resp2 = new ArrayList<Projeto>();

         try{
            for(Projeto p : resp){
               p.setRecompensa(recDao.findAll(p));

               int levantado =  0 ;
               int atingido  =  0;

               for(Recompensa r  : p.getRecompensa()){
                    levantado  += r.getQuantidadeApoio()* r.getValor();
               }
               p.setLevantado(levantado);
               atingido = (p.getLevantado()*100)/p.getValor_total();
             
               p.setAtingido(atingido);
//                   System.out.print("------"+atingido);
//                   atingido   = (projeto.getLevantado()*100)/projeto.getValor_total();
//                  projeto.setAtingido(atingido);


//                  val_porcentagem= valor_total_arrecadado*100/projeto.getValor_total();//100;
//                   int val_p= val_porcentagem;
//                   if(val_p>=100){
//                      val_p=100;
//                   }

               resp2.add(p);

            }
         }catch(Exception e){
                System.out.println(e.getMessage()+"\n"+e.getStackTrace());
         }
         return resp2;
     }






    public Projeto insert(Object o)throws Exception{
        Projeto projeto=(Projeto) o;
        open();
        try{
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, projeto.getFoto_projeto());
            stmt.setString(2, projeto.getLink_video());
            stmt.setString(3, projeto.getTitulo());
            stmt.setString(4, projeto.getApresentacao());
            stmt.setString(5, projeto.getDescricao());
            stmt.setDate(6, projeto.getData_final());
            stmt.setInt(7, projeto.getStatus());
            stmt.setInt(8, projeto.getValor_total());
            stmt.setInt(9, projeto.getProposta().getId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();

            if(rs.next()){
                projeto.setId(rs.getInt(1));
            }


        }catch(Exception e){
            System.out.println(e.getMessage()+"\n"+e.getStackTrace());
        }finally{
             rs.close();
             stmt.close();
             close();
        }
        return projeto;
            
    }

//             public Projeto find(Proposta proposta)throws Exception{
//         open();
//             stmt = con.prepareStatement(FINDALL_TO_PROPOSTA);
//             stmt.setInt(1, proposta.getId());
//             rs   = stmt.executeQuery();
//             int cont=0;
//             if(rs.next()){
//             Projeto   projeto = new Projeto(rs.getInt("id_projeto"), rs.getString("titulo"),
//                            rs.getString("descricao"),rs.getString("data_inicial"),
//                            rs.getString("data_final"), rs.getInt("status"), proposta);
//             projeto.setValor_total(rs.getInt("valor_total"));
//                 projeto.setFoto_projeto(rs.getString("foto_projeto"));
//                 RecompensaDao recompensaDao = new RecompensaDao();
//                 projeto.setRecompensa(recompensaDao.findAll(projeto));
//                 FotoDao  fotoDao= new FotoDao();
//                 projeto.setFoto(fotoDao.findAll(projeto));
//                 VideoDao videoDao = new VideoDao();
//                 Video video = (Video) videoDao.find_TO_PROJETO(rs.getInt("id_projeto"));
//                 projeto.setVideo(video);
//                return projeto;
//
//             }
//         return null;
//     }
////      public Projeto find(int id_projeto)throws Exception{
////         open();
////             stmt = con.prepareStatement(FIND_WHERE_ID);
////             stmt.setInt(1,id_projeto);
////             rs   = stmt.executeQuery();
////             int cont=0;
////             if(rs.next()){
////             PropostaDao propostaDao = new PropostaDao();
////             Proposta proposta= (Proposta) propostaDao.resgate_proposta_pelo_id(rs.getInt("id_proposta_fk"));
////
////             Projeto   projeto = new Projeto(rs.getInt("id_projeto"), rs.getString("titulo"),
////                            rs.getString("descricao"),rs.getString("data_inicial"),
////                            rs.getString("data_final"), rs.getString("status"),proposta);
////             projeto.setValor_total(rs.getInt("valor_total"));
////
////                 projeto.setFoto_projeto(rs.getString("foto_projeto"));
////                 RecompensaDao recompensaDao = new RecompensaDao();
////                 projeto.setRecompensa(recompensaDao.findAll(projeto));
////                 FotoDao  fotoDao= new FotoDao();
////                 projeto.setFoto(fotoDao.findAll(projeto));
////                 VideoDao videoDao = new VideoDao();
////                 Video video = (Video) videoDao.find_TO_PROJETO(rs.getInt("id_projeto"));
////                 projeto.setVideo(video);
////
////                return projeto;
////
////             }
////         return null;
////     }
//       public boolean find_status_proposta(Proposta proposta)throws Exception{
//           open();
//             stmt = con.prepareStatement(FINDALL_STATUS_CATEGORIA);
//             stmt.setInt(1, proposta.getId());
//             rs   = stmt.executeQuery();
//             int cont=0;
//             if(rs.next()){
//
//                return true;
//
//             }
//         return false;
//     }
//
//       //select * from projeto p , recompensa r , proposta pr where r.id_projeto_fk  =  p.id_projeto and r.id_projeto_fk  =  p.id_projeto
////             public List<Projeto> findALL01(String status)throws Exception{
////         open();
////             stmt = con.prepareStatement(FINDALL);
////             stmt.setString(1, status);
////             rs   = stmt.executeQuery();
////             int cont=0;
////             List<Projeto> resp = new ArrayList<Projeto>();
////             while(rs.next()){
////             Projeto   projeto = new Projeto(rs.getInt("p.id_projeto"), rs.getString("p.titulo"),
////                            rs.getString("p.descricao"),rs.getString("p.data_inicial"),
////                            rs.getString("p.data_final"), rs.getString("p.status"));
////                 projeto.setValor_total(rs.getInt("p.valor_total"));
////                 projeto.setFoto_projeto(rs.getString("p.foto_projeto"));
////
////                 proposta = new  Proposta(rs.getInt("pro.id_proposta"),
////                                          rs.getString("pro.descricao"),
////                                          rs.getString("pro.recompensas"),
////                                          rs.getString("pro.links"),
////                                          rs.getString("pro.valor"),
////                                          rs.getString("pro.status"),
////                                          rs.getString("pro.data_emissao"),
////                                          categoria,
////                                          perfil);
////
////                 PropostaDao propostaDao = new PropostaDao();
////                 Proposta proposta = (Proposta) propostaDao.resgate_proposta_pelo_id(rs.getInt("p.id_proposta_fk"));
////                 projeto.setProposta(proposta);
////                 resp.add(projeto);
////
////             }
////         return resp;
////     }
////    public static ArrayList<Projeto> findALL(String status)throws Exception{
////        openStatic();
////        try{
////             stmtStatic = conStatic.prepareStatement(FINDALL);
////             stmtStatic.setString(1, status);
////             rsStatic   = stmtStatic.executeQuery();
////             int cont=0;
////             ArrayList<Projeto> resp = new ArrayList<Projeto>();
////             while(rsStatic.next()){
////             Projeto   projeto = new Projeto(rsStatic.getInt("id_projeto"), rsStatic.getString("titulo"),
////                            rsStatic.getString("descricao"),rsStatic.getString("data_inicial"),
////                            rsStatic.getString("data_final"), rsStatic.getString("status"));
////                 projeto.setValor_total(rsStatic.getInt("valor_total"));
////                 projeto.setFoto_projeto(rsStatic.getString("foto_projeto"));
////
////                 RecompensaDao recompensaDao = new RecompensaDao();
////                 projeto.setTotal_recebido(recompensaDao.findAll_val_total(projeto));
////
////                 PropostaDao propostaDao = new PropostaDao();
////                 Proposta proposta = (Proposta) propostaDao.resgate_proposta_pelo_id(rsStatic.getInt("id_proposta_fk"));
////                 projeto.setProposta(proposta);
////                 resp.add(projeto);
////
////             }
////         return resp;
////        }catch(Exception e){
////        }finally{
////              closeStatic();
////        }
////        return null;
////     }
//
//
//    public void insert_Projeto_mais_recompensa(Proposta proposta,List<Recompensa> rec,String nome_img)throws Exception{
//        //Projeto projeto=(Projeto) o;
//        open();
//            stmt = con.prepareStatement(INSERT);
//            stmt.setString(1, nome_img);
//            stmt.setString(2, proposta.getProjeto().getTitulo());
//            stmt.setString(3, proposta.getProjeto().getDescricao());
//            stmt.setString(4, proposta.getProjeto().getData_inicial());
//            stmt.setString(5, proposta.getProjeto().getData_final());
//            stmt.setInt(6, proposta.getProjeto().getStatus());
//            stmt.setInt(7, proposta.getProjeto().getValor_total());
//            stmt.setInt(8, proposta.getId());
//            stmt.executeUpdate();
//            rs = stmt.getGeneratedKeys();
//            if(rs.next()){
//                Projeto p = new Projeto();
//                p.setId(rs.getInt(1));
//                List<Recompensa> resp=  rec;
//                for(int i=0 ; i < resp.size() ; i++){
//                    Recompensa recompensa = (Recompensa) resp.get(i);
//                    recompensa.setProjeto(p);
//                    RecompensaDao  recompensaDao   =  new RecompensaDao();
//                    recompensaDao.insert(recompensa);
//                }
//            }
//
//            PropostaDao propostaDao= new   PropostaDao();
//            proposta.setStatus("on");
////            propostaDao.updateStatus(proposta);
//            stmt.close();
//        close();
//    }
//
//
//

}

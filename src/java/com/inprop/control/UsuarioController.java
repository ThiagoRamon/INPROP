/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.business.BairroBusiness;
import com.inprop.business.CEPBusiness;
import com.inprop.business.CidadeBusiness;
import com.inprop.business.EnderecoBusiness;
import com.inprop.business.EstadoBusiness;
import com.inprop.business.PerfilBusiness;
import com.inprop.business.UsuarioBusiness;
import com.inprop.model.Bairro;
import com.inprop.model.CEP;
import com.inprop.model.Cidade;
import com.inprop.model.Endereco;
import com.inprop.model.Estado;
import com.inprop.model.Perfil;
import com.inprop.model.Usuario;
import com.inprop.persistence.Contato;
import com.inprop.util.MecanicaCriptografaUtil;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.inprop.util.ValidaCampo;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author Ramon
 */
public class UsuarioController extends HttpServlet{

    private static final  String    URL_PATH_DEFAULT = "/usuario";
    private static final  String    URL_AREA_DEFAULT = "/index.jsp";
    private static final  String    URL_AREA_USUARI = "/usuario/index.jsp";
    private static final  String[]  ACAO_CADASTRO_USUARIO={"ADM","BAS"};
    private static final  String    ACAO_LOGAR           ="login";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      //  response.setContentType("text/html;charset=UTF-8");
      //  PrintWriter out = response.getWriter();
         String acao = request.getParameter("acao");
try{
         if(ACAO_CADASTRO_USUARIO[0].equals(acao)){

                cadastrarUsuarioADM(request, response);
           }
         else if(ACAO_CADASTRO_USUARIO[1].equals(acao)){
             String  isAd =request.getParameter("isAd");
                if(isAd.equalsIgnoreCase("true")){
                    cadastrarUsuarioBAS(request, response);
                }else{
                    cadastrarUsuarioADM(request, response);
                }
                
           }
         else if(ACAO_LOGAR.equals(acao)){
                logarUsuario(request, response);
           }
}catch(Exception e){
    System.out.println("<<---->> "+e.getMessage()+"\n"+e.getStackTrace()+"\n");
}
    }



    private void logarUsuario(HttpServletRequest request, HttpServletResponse response)throws Exception {
        String email          = request.getParameter("email");
        String senha          = request.getParameter("senha");
        String url            =  URL_PATH_DEFAULT;
        UsuarioBusiness ud    = new UsuarioBusiness();
        try{
                     senha = MecanicaCriptografaUtil.getInstance().criptografaSenha(senha);
           Usuario usuario = new Usuario(email,senha);
                   usuario = (Usuario) ud.login(usuario);
                    request.getSession().invalidate();
                    request.getSession().removeAttribute("usuario");
            if(usuario != null){


                request.getSession().setAttribute("usuario", usuario);
                if(usuario.getTipo().equalsIgnoreCase("ADM")){
                   url = "/extranet";
                }else if(usuario.getTipo().equalsIgnoreCase("EMP")){
                    url= "/empresa";
                }

                 response.sendRedirect(request.getContextPath()+url) ;
            }else{
                 request.getSession().setAttribute("mensagem", "Error : login - invalido");
                 response.sendRedirect(request.getContextPath()) ;
            }

        }catch(Exception e){
            System.out.println(e.getMessage()+""+e.getStackTrace());
            response.sendRedirect(request.getContextPath()) ;
            
        }finally{
           email = null;
           senha = null;
        }
    }

    private void cadastrarUsuarioADM(HttpServletRequest request, HttpServletResponse response) throws Exception{

            String  url           = URL_AREA_DEFAULT;
            String nome           = null;
            String sobreNome      = null;
            String genero         = null;
            String email          = null;
            String senha          = null;
            String re_senha       = null;
            String status         = null;
            String tipo           = null;
            String dataNascimento = null;
                   nome           = request.getParameter("nome").equals("")==false ? request.getParameter("nome") : null;
                   sobreNome      = request.getParameter("sobreNome").equals("")==false ?request.getParameter("sobreNome"):null ;
                   genero         = request.getParameter("genero").equals("")==false ?request.getParameter("genero"):null;
                   email          = request.getParameter("email").equals("")==false ?request.getParameter("email"):null;
                   status         = "A";
                   tipo           = "ADM";
                   senha          = request.getParameter("senha").equals("")==false ?MecanicaCriptografaUtil.getInstance().criptografaSenha(request.getParameter("senha")):null;
                   re_senha       = request.getParameter("re_senha").equals("")==false ?MecanicaCriptografaUtil.getInstance().criptografaSenha(request.getParameter("re_senha")):null ;
             dataNascimento       = request.getParameter("dataNascimento").equals("")==false ?request.getParameter("dataNascimento").replace("/","").replace("-",""):"00000000";
           String[] arrString     = {nome,sobreNome,genero,
                                     email,senha,re_senha,
                                     status,tipo,dataNascimento};

            System.out.println(ValidaCampo.getInstance().isNull(arrString)+"\n------->\n"+nome+"\n"+sobreNome+"\n"+genero+"\n"+email+"\n"+senha+"\n"+re_senha+"\n"+status+"\n"+tipo+"\n"+dataNascimento);

        String dia  = null;
        String mes  = null;
        String ano  = null;
               dia     = dataNascimento.substring(0,2);
               mes     = dataNascimento.substring(2,4);
               ano     = dataNascimento.substring(4,8);

        dataNascimento = ano+"/"+mes+"/"+dia;
        Date dt_nascimento = new Date(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));

        Usuario usuario    = new Usuario(nome, sobreNome, genero, email, senha, dt_nascimento, status, tipo);
        UsuarioBusiness ud = new UsuarioBusiness();

        try{
            if(!ud.verificaEmail(email)){
                    if(ValidaCampo.getInstance().isNull(arrString)){
                       //flag 0 tem algum campo nulo no formulario de cadastro
                        if(ud.cadastraUsuario(usuario)){
                               url= URL_AREA_USUARI;
                               request.getSession().setAttribute("usuario", usuario);
                               response.sendRedirect(request.getContextPath()+"/extranet");
                            }else{
                                    request.setAttribute("flag"      , '1');
                                    request.setAttribute("pagina"    , "cadastro");
                                    request.setAttribute("mensagem"    , "Não foi possivel efetuar o seu cadastro, tente nova mente!s");

                           }

                        }else{
                                request.setAttribute("flag"      , '1');
                                request.setAttribute("pagina"    , "cadastro");
                                request.setAttribute("mensagem"    , "Error, alguns campos podem não ter sido preenchido!");
                                System.out.println("URL--> " +url);
                                response.sendRedirect(request.getContextPath()) ;

                            }

                }else{
                            request.setAttribute("flag"      , '1');
                            request.setAttribute("pagina"    , "cadastro");
                            request.setAttribute("mensagem"    , "Por favor, tente outro email, este ("+email+") já está cadastrado!");
                            response.sendRedirect(request.getContextPath()) ;

                }
        }catch(Exception e){
                // flag  1 ocoreu uma exception na hora de cadastrar o usaurio
                 request.setAttribute("flag"      , '1');
                 request.setAttribute("pagina"    , "cadastro");
                 request.setAttribute("mensagem"    , "ERRO DE EXCEPTION");
                 System.out.println("controller"+ e.getMessage()+"\n"+e.getStackTrace());
                 response.sendRedirect(request.getContextPath()) ;

        }finally{
             nome           = null;
             sobreNome      = null;
             genero         = null;
             email          = null;
             senha          = null;
             re_senha       = null;
             status         = null;
             tipo           = null;
             dataNascimento = null;
             response.sendRedirect(request.getContextPath());
        }
    }
    
    private void cadastrarUsuarioBAS(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String  url           = URL_AREA_DEFAULT;
        String nome           = null;
        String sobreNome      = null;
        String genero         = null;
        String email          = null;
        String senha          = null;
        String re_senha       = null;
        String status         = null;
        String tipo           = null;
        String dataNascimento = null;
               nome           = request.getParameter("nome").equals("")==false ? request.getParameter("nome") : null;
               sobreNome      = request.getParameter("sobreNome").equals("")==false ?request.getParameter("sobreNome"):null ;
               genero         = request.getParameter("genero").equals("")==false ?request.getParameter("genero"):null;
               email          = request.getParameter("email").equals("")==false ?request.getParameter("email"):null;
               status         = "I";
               tipo           = "BAS";
               senha          = request.getParameter("senha").equals("")==false ?MecanicaCriptografaUtil.getInstance().criptografaSenha(request.getParameter("senha")):null;
               re_senha       = request.getParameter("re_senha").equals("")==false ?MecanicaCriptografaUtil.getInstance().criptografaSenha(request.getParameter("re_senha")):null ;
         dataNascimento       = request.getParameter("dataNascimento").equals("")==false ?request.getParameter("dataNascimento").replace("/","").replace("-",""):"00000000";
       String[] arrString     = {nome,sobreNome,genero,
                                 email,senha,re_senha,
                                 status,tipo,dataNascimento};

        System.out.println(ValidaCampo.getInstance().isNull(arrString)+"\n------->\n"+nome+"\n"+sobreNome+"\n"+genero+"\n"+email+"\n"+senha+"\n"+re_senha+"\n"+status+"\n"+tipo+"\n"+dataNascimento);

        String dia  = null;
        String mes  = null;
        String ano  = null;
               dia     = dataNascimento.substring(0,2);
               mes     = dataNascimento.substring(2,4);
               ano     = dataNascimento.substring(4,8);

        dataNascimento = ano+"/"+mes+"/"+dia;
        Date dt_nascimento = new Date(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));

        Usuario usuario    = new Usuario(nome, sobreNome, genero, email, senha, dt_nascimento, status, tipo);
        UsuarioBusiness ud = new UsuarioBusiness();
    
        try{
                if(!ud.verificaEmail(email)){
                        if(ValidaCampo.getInstance().isNull(arrString)){
                           //flag 0 tem algum campo nulo no formulario de cadastro
                             usuario = ud.cadastraUsuario1(usuario);
                            if(usuario!=null){
                                   PerfilBusiness pb = new PerfilBusiness();
                                   usuario.setPerfil(pb.cadastraPerfil1(usuario.getId(),usuario.getGenero()));
                                   Contato contato = new Contato();
                                   contato.setTel("999");
                                   contato.setCel("999");
                                   contato.setFax("999");
                                   contato.setTelComercial("999");
                                   contato.setStatus("T");
                                   contato.setObs("999");
                                   contato.setCanalYouTube("999");
                                   contato.setTwitter("999");
                                   contato.setFaceBook("999");
                                   contato.setPerfil(usuario.getPerfil());

                                   ContatoBusiness contatoBusiness = new ContatoBusiness();

                                   usuario.getPerfil().setContato(contatoBusiness.cadContato(contato));

                                   EnderecoBusiness enderecoBusiness = new EnderecoBusiness();


                            Estado estado = new Estado("RJ");
                            Endereco  end = null;
                            EstadoBusiness estadoBusiness= new EstadoBusiness();
                            CidadeBusiness cidadeBusiness= new CidadeBusiness();
                            BairroBusiness bairroBusiness= new BairroBusiness();
                            CEPBusiness cepBusiness= new CEPBusiness();
                            try{

                               estado =  estadoBusiness.atualizaEstado(estado);
                               if( estado!=null  ){
                                     Cidade cidade = new Cidade("Rio de Janeiro","A",estado);
                                     cidade =cidadeBusiness.insertCidade(cidade);
                                     if(cidade!=null){
                                        Bairro bairro = new Bairro("Vila da Penha","A",cidade);
                                         bairro = bairroBusiness.insertBairroByUsuario(bairro);
                                         if( bairro != null ){
                                             CEP  cep = new CEP("21221-250","rua","Apia",bairro);
                                             cep      = cepBusiness.insertCidade(cep);
                                             if(cep != null){
                                                  end = new Endereco("999", "999","T","999", cep);
                                                  end.setPerfil(usuario.getPerfil());
                                                  enderecoBusiness.cadEndereco(end);
                                             }
                                         }
                                     }
                               }
                            }catch(Exception e){
                                 request.getSession().setAttribute("mensagem"    , "ERRO DE EXCEPTION ");
                                 System.out.println("controller   - CADASTRO BAS  :"+ e.getMessage()+"\n"+e.getStackTrace());
                                 response.sendRedirect(request.getContextPath());
                            }
                                   request.getSession().invalidate();
                                   usuario.getPerfil().setEndereco(end);
                                   url= URL_AREA_USUARI;
                                   request.getSession().setAttribute("usuario", usuario);
                                   response.sendRedirect(request.getContextPath()+"/usuario");

                                }else{
                                     request.getSession().setAttribute("mensagem"    , "ERRO DE EXCEPTION - Usuario não cadastrado");
                                     response.sendRedirect(request.getContextPath());
                               }


                        }else{
                              request.getSession().setAttribute("nome",nome);
                             request.getSession().setAttribute("sobreNome", sobreNome );
                             request.getSession().setAttribute("genero",genero);
                             request.getSession().setAttribute("email",email);
                             request.getSession().setAttribute("dataNascimento",dia+"/"+mes+"/"+ano);

                         request.getSession().setAttribute("flag"      , '1');
                         request.getSession().setAttribute("pagina"    , "cadastro");
                                request.getSession().setAttribute("mensagem", "ALGUNS CAMPOS ESTAO VAZIOS ");
                                response.sendRedirect(request.getContextPath());
                            }

                    }else{
                             request.getSession().setAttribute("nome",nome);
                             request.getSession().setAttribute("sobreNome", sobreNome );
                             request.getSession().setAttribute("genero",genero);
                             request.getSession().setAttribute("email",email);
                             request.getSession().setAttribute("dataNascimento",dia+"/"+mes+"/"+ano);

                         request.getSession().setAttribute("flag"      , '1');
                         request.getSession().setAttribute("pagina"    , "cadastro");
                         request.getSession().setAttribute("mensagem"    , "Por favor cadastre outro email.");
                         response.sendRedirect(request.getContextPath());
                    }
        }catch(Exception e){
                 request.getSession().setAttribute("nome",nome);
                 request.getSession().setAttribute("sobreNome", sobreNome );
                  request.getSession().setAttribute("genero",genero);
                             request.getSession().setAttribute("email",email);
                             request.getSession().setAttribute("dataNascimento",dia+"/"+mes+"/"+ano);

                         request.getSession().setAttribute("flag"      , '1');
                         request.getSession().setAttribute("pagina"    , "cadastro");

                 request.getSession().setAttribute("mensagem"    , "ERRO DE EXCEPTION : Tente de novo, se mesmo assim não conseguir efetuar o seu cadastro entre em contato conosco.");
                 System.out.println("controller   - CADASTRO BAS  :"+ e.getMessage()+"\n"+e.getStackTrace());
                 response.sendRedirect(request.getContextPath());
        }finally{
             nome           = null;
             sobreNome      = null;
             genero         = null;
             email          = null;
             senha          = null;
             re_senha       = null;
             status         = null;
             tipo           = null;
             dataNascimento = null;
        }

  
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Clique no sinal de + à esquerda para editar o código.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

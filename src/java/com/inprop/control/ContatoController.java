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
import com.inprop.model.Bairro;
import com.inprop.model.CEP;
import com.inprop.model.Cidade;
import com.inprop.model.Endereco;
import com.inprop.model.Estado;
import com.inprop.model.Usuario;
import com.inprop.persistence.Contato;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon
 */
public class ContatoController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

      private static final String ACAO_UPDATE="cad";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      //  response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();

        try {
            String acao =request.getParameter("acao");
             System.out.println(acao);
            if(acao.equalsIgnoreCase("cad")|acao.equalsIgnoreCase("cad1")){
                updateContato1(request, response);
            }else if(acao.equalsIgnoreCase("cadEndereco")){
                updateEndereco(request, response);
            }
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContatoController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContatoController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        }catch(Exception e){
            
        } finally { 
           // out.close();
        }
    } 
  private void  updateContato1(HttpServletRequest request,HttpServletResponse response)throws Exception{
      Usuario usuario =(Usuario) request.getSession().getAttribute("usuario");
            

      String tel = (request.getParameter("tel")!=null && request.getParameter("tel")!=""  ?request.getParameter("tel"):usuario.getPerfil().getContato().getTel());
      String cel = (request.getParameter("cel")!=null && request.getParameter("cel")!=""?request.getParameter("cel"):usuario.getPerfil().getContato().getCel());
      String fb = (request.getParameter("facebook")  !=null && request.getParameter("facebook")  !="" ?request.getParameter("facebook"):usuario.getPerfil().getContato().getFaceBook());
      String tw = (request.getParameter("twitter")  !=null && request.getParameter("twitter")!=""? request.getParameter("twitter"):usuario.getPerfil().getContato().getTwitter());
      String yt = (request.getParameter("canalYouTube")  !=null && request.getParameter("canalYouTube")  !="" ?request.getParameter("canalYouTube"):usuario.getPerfil().getContato().getCanalYouTube());
      Contato contato= new Contato();
      contato.setId(usuario.getPerfil().getContato().getId());
      contato.setPerfil(usuario.getPerfil());
      contato.setTel(tel);
      contato.setCel(cel);
      contato.setFaceBook(fb);
      contato.setTwitter(tw);
      contato.setCanalYouTube(yt);
      contato.setStatus("A");

      ContatoBusiness cb = new ContatoBusiness();
      try{
         if(cb.updateAll(contato)){
              if(request.getParameter("acao").equalsIgnoreCase("cad1")){

                 request.getSession().setAttribute("mensagem", "****Exception o servlet ContatoController-updateContato *****  ");
                 request.getSession().setAttribute("pagina", "info");
                 response.sendRedirect(request.getContextPath()+"/usuario");

             }
             usuario.getPerfil().setContato(contato);
             request.getSession().setAttribute("usuario", usuario);
             request.getSession().setAttribute("pagina", "info");
             response.sendRedirect(request.getContextPath()+"/usuario/sistema/page/form/form_contato.jsp");
         }else{

             if(request.getParameter("acao").equalsIgnoreCase("cad1")){
                     request.getSession().setAttribute("mensagem", "****Exception o servlet ContatoController-updateContato *****  ");
                     request.getSession().setAttribute("pagina", "info");
                     response.sendRedirect(request.getContextPath()+"/usuario");
              }

             request.getSession().setAttribute("mensagem", "Alguns dados não pode ser cadastrados tente novamente");
             request.getSession().setAttribute("pagina", "info");
             response.sendRedirect(request.getContextPath()+"/usuario/sistema/page/form/form_contato.jsp");

         }

      }catch(Exception e){
          if(request.getParameter("acao").equalsIgnoreCase("cad1")){
             request.getSession().setAttribute("mensagem", "****Exception o servlet ContatoController-updateContato *****  ");
             request.getSession().setAttribute("pagina", "info");
             response.sendRedirect(request.getContextPath()+"/usuario");

          }

             request.getSession().setAttribute("mensagem", "****Exception o servlet ContatoController-updateContato *****  ");
             request.getSession().setAttribute("pagina", "info");
             response.sendRedirect(request.getContextPath()+"/usuario/sistema/page/form/form_contato.jsp");
      }finally{

           tel = null;
           cel = null;
           fb  = null;
           tw  = null;
           yt  = null;


      }

  }
  private void  updateContato(HttpServletRequest request,HttpServletResponse response)throws Exception{
      Usuario usuario =(Usuario) request.getSession().getAttribute("usuario");

      String tel = (request.getParameter("tel")!=null && request.getParameter("tel")!=""  ?request.getParameter("tel"):usuario.getPerfil().getContato().getTel());
      String cel = (request.getParameter("cel")!=null && request.getParameter("cel")!=""?request.getParameter("cel"):usuario.getPerfil().getContato().getCel());
      String fb = (request.getParameter("facebook")  !=null && request.getParameter("facebook")  !="" ?request.getParameter("facebook"):usuario.getPerfil().getContato().getFaceBook());
      String tw = (request.getParameter("twitter")  !=null && request.getParameter("twitter")!=""? request.getParameter("twitter"):usuario.getPerfil().getContato().getTwitter());
      String yt = (request.getParameter("canalYouTube")  !=null && request.getParameter("canalYouTube")  !="" ?request.getParameter("canalYouTube"):usuario.getPerfil().getContato().getCanalYouTube());
      Contato contato= new Contato();
      contato.setId(usuario.getPerfil().getContato().getId());
      contato.setPerfil(usuario.getPerfil());
      contato.setTel(tel);
      contato.setCel(cel);
      contato.setFaceBook(fb);
      contato.setTwitter(tw);
      contato.setCanalYouTube(yt);
      contato.setStatus("A");

      ContatoBusiness cb = new ContatoBusiness();
      try{
         if(cb.updateAll(contato)){
             usuario.getPerfil().setContato(contato);
             request.getSession().setAttribute("usuario", usuario);
             request.getSession().setAttribute("pagina", "info");
             response.sendRedirect(request.getContextPath()+"/usuario");
         }else{

             request.getSession().setAttribute("mensagem", "Alguns dados não pode ser cadastrados tente novamente");
             request.getSession().setAttribute("pagina", "info");
             response.sendRedirect(request.getContextPath()+"/usuario");

         }

      }catch(Exception e){
             request.getSession().setAttribute("mensagem", "****Exception o servlet ContatoController-updateContato *****  ");
             request.getSession().setAttribute("pagina", "info");
             response.sendRedirect(request.getContextPath()+"/usuario");
      }finally{
          
           tel = null;
           cel = null;
           fb  = null;
           tw  = null;
           yt  = null;


      }

  }
public void updateEndereco(HttpServletRequest request, HttpServletResponse response)throws Exception{
    Usuario usuario      = (Usuario) request.getSession().getAttribute("usuario");

    String uf            = (request.getParameter("uf")          !=null && request.getParameter("uf")          !=""  ?request.getParameter("uf")          :usuario.getPerfil().getEndereco().getCep().getBairro().getCidade().getEstado().getUf());
    String nCidade       = (request.getParameter("cidade")      !=null && request.getParameter("cidade")      !=""  ?request.getParameter("cidade")      :usuario.getPerfil().getEndereco().getCep().getBairro().getCidade().getNome());
    String nBairro       = (request.getParameter("bairro")      !=null && request.getParameter("bairro")      !=""  ?request.getParameter("bairro")      :usuario.getPerfil().getEndereco().getCep().getBairro().getNome());
    String numCEP        = (request.getParameter("cep")         !=null && request.getParameter("cep")         !=""  ?request.getParameter("cep")         :usuario.getPerfil().getEndereco().getCep().getNumero());
    String tp_logradouro = (request.getParameter("tipo")        !=null && request.getParameter("tipo")        !=""  ?request.getParameter("tipo")        :usuario.getPerfil().getEndereco().getCep().getTipo());
    String logradouro    = (request.getParameter("logradouro")  !=null && request.getParameter("logradouro")  !=""  ?request.getParameter("logradouro")  :usuario.getPerfil().getEndereco().getCep().getLogradouro());
    String numero        = (request.getParameter("numero")      !=null && request.getParameter("numero")      !=""  ?request.getParameter("numero")      :usuario.getPerfil().getEndereco().getNumero());
    String complemento   = (request.getParameter("complemento") !=null && request.getParameter("complemento") !=""  ?request.getParameter("complemento") :usuario.getPerfil().getEndereco().getComplemento());
    String referencia    = (request.getParameter("referencia")  !=null && request.getParameter("referencia")  !=""  ?request.getParameter("referencia")  :usuario.getPerfil().getEndereco().getReferencia());

    System.out.println("\n"+uf);
    System.out.println(nCidade);
    System.out.println(nBairro);
    System.out.println(numCEP);
    System.out.println(tp_logradouro);
    System.out.println(logradouro);
    System.out.println(numero);
    System.out.println(complemento);
    System.out.println(referencia+"\n");

                    Estado estado = new Estado(uf);
                    Endereco  end = null;
                    EstadoBusiness estadoBusiness= new EstadoBusiness();
                    CidadeBusiness cidadeBusiness= new CidadeBusiness();
                    BairroBusiness bairroBusiness= new BairroBusiness();
                    CEPBusiness cepBusiness= new CEPBusiness();
                    EnderecoBusiness enderecoBusiness = new EnderecoBusiness();
                    try{

                       estado =  estadoBusiness.atualizaEstado(estado);
                       System.out.println(">>>>>"+estado.getNome()+"<<<<<<");
                       System.out.println(">>>>>"+estado.getUf()+"<<<<<<");
                       System.out.println(">>>>>"+estado.getId()+"<<<<<<");
                       if( estado!=null  ){
                             Cidade cidade = new Cidade(nCidade,"A",estado);
                             cidade =cidadeBusiness.insertCidade(cidade);
                             cidade.setEstado(estado);
                             if(cidade!=null){
                                Bairro bairro = new Bairro(nBairro,"A",cidade);
                                 bairro = bairroBusiness.insertBairroByUsuario(bairro);
                                 if( bairro != null ){
                                     CEP  cep = new CEP(numCEP,tp_logradouro,logradouro,bairro);
                                     cep      = cepBusiness.insertCidade(cep);
                                     if(cep != null){
                                          end = new Endereco(numero, complemento, referencia, cep);
                                          end.setId(usuario.getPerfil().getEndereco().getId());
                                          end.setPerfil(usuario.getPerfil());
                                          end.setStatus("A");
                                          end = enderecoBusiness.UpdateEndereco(end);
                                          if(end!=null){
                                             usuario.getPerfil().setEndereco(end);
                                                 request.getSession().setAttribute("mensagem", "endereço atualizado com sucesso.");
                                                 request.getSession().setAttribute("pagina", "info");
                                                 response.sendRedirect(request.getContextPath()+"/usuario/sistema/page/form/form_contato.jsp");

                                          }
                                     }
                                 }
                             }
                       }
                    }catch(Exception e){
                        System.out.println(e.getMessage()+"/n"+e.getStackTrace());
                         request.getSession().setAttribute("mensagem", "****Exception o servlet ContatoController-updateEndereco *****  ");
                         request.getSession().setAttribute("pagina", "info");
                     //    response.sendRedirect(request.getContextPath()+"/usuario");
                    }

 // System.out.println ("=====>>"+end.getCep().getId());


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

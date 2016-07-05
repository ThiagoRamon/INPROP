/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.business.PropostaBusiness;
import com.inprop.model.Categoria;
import com.inprop.model.Proposta;
import com.inprop.model.Usuario;
import com.inprop.util.Util;
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
public class PropostaController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ACAO_CAD_PROPOSTA = "cadProposta";
    private String URL;
            protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
            URL = request.getContextPath()+"/usuario";
            String pagina = null;
            String acao = request.getParameter("acao");
            System.out.println(acao);
        try {
            if(acao.equalsIgnoreCase(ACAO_CAD_PROPOSTA)){
                pagina="formCadastro/formProposta";
                cadastro(request, response);
            }else if(acao.equalsIgnoreCase("upStatus")){
                pagina ="formCadastro/formProposta";
                URL    = request.getContextPath()+"/extranet";
                updateStatus(request, response);

            }
        }catch(Exception e){
                request.getSession().setAttribute("pagina", pagina);
                request.getSession().setAttribute("mensagem", "Exception 0002");
                response.sendRedirect(URL);
        } finally { 
          //  out.close();
        }
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response)throws Exception{

        int status  = Integer.parseInt( request.getParameter("status") != null ? request.getParameter("status") :"0");
        int    id      = Integer.parseInt(request.getParameter("id"));
        PropostaBusiness  propostaBusiness = new PropostaBusiness();
        Proposta  proposta = new Proposta();
        proposta.setStatus(status);
        proposta.setId(id);
        try{
             if(propostaBusiness.updateStatus(proposta)){
                 request.getSession().setAttribute("pagina", "lista_proposta");
                 request.getSession().setAttribute("mensagem", "Status proposta atualizado com sucesso.");
                 response.sendRedirect(request.getContextPath()+"/extranet/sistema/page/find/view_proposta.jsp?id="+id);
             }else{
                 request.getSession().setAttribute("pagina", "lista_proposta");
                 request.getSession().setAttribute("mensagem", "Status proposta não atualizado.");
                 response.sendRedirect(request.getContextPath()+"/extranet/sistema/page/find/view_proposta.jsp?id="+id);

             }
        }catch(Exception e){

                 request.getSession().setAttribute("pagina", "lista_proposta");
                 request.getSession().setAttribute("mensagem", "***Exception***");
                 response.sendRedirect(request.getContextPath()+"/extranet/sistema/page/find/view_proposta.jsp?id="+id);

        }
    }


    private void cadastro(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String descricao   = request.getParameter("descricao");
        String recompensas = request.getParameter("recompensas");
        String links       = request.getParameter("links");
        String de          = request.getParameter("valor_de");
        String ate         = request.getParameter("valor_ate");

        String pesquisa    = request.getParameter("pesquisa");

        String[] arrEl = {descricao,recompensas,links,de.replace("-",""),ate.replace("-",""), pesquisa, request.getParameter("categoria")};
        Util util = new Util();
        if(!util.isNull(arrEl)){

            request.getSession().setAttribute("pagina", "formCadastro/formProposta");
            request.getSession().setAttribute("mensagem", "Por favor, preencha todos os campos.");
            response.sendRedirect(URL);

        }


       int idCategoria     = Integer.parseInt((request.getParameter("categoria")!=null?request.getParameter("categoria"):"0"));
       Proposta proposta   = new Proposta();
       Categoria categoria = new Categoria();

       categoria.setId_categoria(idCategoria);
      
       proposta.setValor_de(Integer.parseInt(de.replace("-","")));
       proposta.setValor_ate(Integer.parseInt(ate.replace("-","")));

       proposta.setCategoria(categoria);
       proposta.setDescricao(descricao);
       proposta.setStatus(0);
       proposta.setRecompensas(recompensas);
       proposta.setLinks(links);
       //proposta.setValor_de(valor);

       PropostaBusiness propostaBusiness = new PropostaBusiness();
       Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
       proposta.setPerfil(usuario.getPerfil());
       
       try{
             if(propostaBusiness.insert(proposta)){
                    request.getSession().setAttribute("mensagem", "Proposta enviada com sucesso.");
                    response.sendRedirect(URL);
             }else{
                    request.getSession().setAttribute("pagina", "formCadastro/formProposta");
                    request.getSession().setAttribute("mensagem", "Proposta não cadastrada, tente novamente.");
                    response.sendRedirect(URL);
             }

       }catch(Exception e){
                    request.getSession().setAttribute("pagina", "formCadastro/formProposta");
                    request.getSession().setAttribute("mensagem", "Exception 0002");
                    response.sendRedirect(URL);

       }
       
    }

   // private void cadastro(HttpServletRequest request,HttpServletResponse response)throws Exception{
   // }
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

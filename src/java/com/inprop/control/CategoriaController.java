/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.business.CategoriaBusiness;
import com.inprop.model.Categoria;
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
public class CategoriaController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String urlExtranet =null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
  //      PrintWriter out = response.getWriter();
        urlExtranet =request.getContextPath()+"/extranet";
        String acao = request.getParameter("acao");
        try {
            if(acao.equalsIgnoreCase("cadCategoria")){
                cadastro(request, response);
            }
        }catch(Exception e){

        } finally { 
//            out.close();
        }
    }

    public void cadastro(HttpServletRequest request,HttpServletResponse response)throws Exception{

        String nome       = (request.getParameter("nome")!=null?request.getParameter("nome") : "--" );
        String status     = request.getParameter("status");
        if(nome == null || status==null){
            request.getSession().setAttribute("pagina","form_categoria");
            request.getSession().setAttribute("mensagem", "Por favor preencha os campos corretamente .");
            response.sendRedirect(urlExtranet);
        }
        Categoria categoria = new Categoria(nome, status);

        CategoriaBusiness categoriaB = new CategoriaBusiness();
        try{ 
            if(categoriaB.cadastro(categoria)){
                request.getSession().setAttribute("pagina","form_categoria");
                request.getSession().setAttribute("mensagem", "categoria Cadastrada com sucesso.");
                response.sendRedirect(urlExtranet);

            }else{
                request.getSession().setAttribute("pagina","form_categoria");
                request.getSession().setAttribute("mensagem", "Por favor preencha os campos corretamente ou esta categoria já está cadastrada .");
                response.sendRedirect(urlExtranet);

            }
            
        }catch(Exception e){
            request.getSession().setAttribute("pagina","form_categoria");
            request.getSession().setAttribute("mensagem", "***Exception CategoriaDao-cadastro 00001***.");
            response.sendRedirect(urlExtranet);
        
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

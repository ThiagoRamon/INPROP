/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.business.PaisBusiness;
import com.inprop.model.Pais;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon
 */
public class PaisController extends HttpServlet {
   private static final String ACAO_INSERT ="CAD";
   private static final String PATH_DEFAULT ="/extranet";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        String acao = request.getParameter("acao");
        try {
           if(acao.equalsIgnoreCase(ACAO_INSERT)){
              cadastar(request, response);
           }else if(acao.equalsIgnoreCase("d")){
           }
        }catch(Exception e){

        }finally {
           
        }
    } 


    private void cadastar(HttpServletRequest request, HttpServletResponse response)throws Exception {
          response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

         String url = request.getContextPath()+PATH_DEFAULT;
        //String
        String nome = request.getParameter("nome")!=null ? request.getParameter("nome"): null;
        String sigla = request.getParameter("sigla")!=null ? request.getParameter("sigla"): null;
        String status = request.getParameter("status")!=null ? request.getParameter("status"): null;
        Pais pais = new Pais(nome,sigla,status);
        PaisBusiness paisBusiness = new PaisBusiness();
        String mensagem ="O pais  ("+nome+") foi cadastrado com sucesso.";
        try{
           if(!paisBusiness.insert(pais)){
              mensagem = "ERROR ao cadastrar o pais ("+nome+") tente mais tarde ";
           }
            
            System.out.print(url);
        }catch(Exception e){
               System.out.println("   ERROR    no servlet paisController "+e.getMessage()+"\n"+ e.getStackTrace() );
        }finally{
            nome = null;
            sigla = null;
            status = null;
        }
            out.println("<b style='color:red;'>"+mensagem +"</b>" );
            
        } finally {
            out.close();
        }
        //response.sendRedirect(url);
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
    throws ServletException, IOException   {
            processRequest(request, response);
    }


       @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}

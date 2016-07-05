/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.business.EstadoBusiness;
import com.inprop.model.Estado;
import com.inprop.model.Pais;
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
public class EstadoController extends HttpServlet {
     private static final String ACAO_CADASTRO        = "cad";
     private static final String ACAO_SELECT_SEM_JSON = "sl";

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       String acao = request.getParameter("acao");
      try{
       if(acao.equalsIgnoreCase(ACAO_CADASTRO)){

           cadastro(request, response);

       }else
       if(acao.equalsIgnoreCase(acao)){

           selectSemJson(request, response);

       }else{
           
       }
      }catch(Exception e){
           System.out.println("ERRO no servlet EstadoController "+e.getMessage()+"\n"+e.getStackTrace());
      }
    }

    private void selectSemJson(HttpServletRequest request, HttpServletResponse response)throws Exception {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        int id =Integer.parseInt( request.getParameter("") );
        try{
            try{
                out.println("<select>");
                out.println("</select>");

                }catch(Exception e){

                }
        }finally{

                 id  = 0;
                 out.close();

        }

    }
    private void cadastro(HttpServletRequest request, HttpServletResponse response)throws Exception{
         response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();

        String nome   = null;
        String uf     = null;
        String status = null;
        int   id      = 0;

        try {
              nome    = request.getParameter("nome");
              uf      = request.getParameter("uf");
              status  = request.getParameter("status");
              id      = Integer.parseInt(request.getParameter("pais"));
      
              
              Pais                     pais  = new Pais(id);
              Estado                  estado = new Estado(nome,uf,status,pais);
              EstadoBusiness  estadoBusiness = new EstadoBusiness();
              String msg                     = "O estado (" + nome + ") foi cadastrado com sucesso.";
              try{
                  if(!estadoBusiness.insert(estado))msg ="Não foi possivel cadastrar o estado ("+nome+") no momento tente mais tarde.";
              }catch(Exception e){
                  out.print(e);
              }


              out.println(msg);


             } finally {
                 nome   = null;
                 uf     = null;
                 status = null;
                 id      = 0;
                 out.close();
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

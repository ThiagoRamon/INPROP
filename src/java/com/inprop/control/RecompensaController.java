/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.model.Proposta;
import com.inprop.model.Recompensa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ramon
 */
public class RecompensaController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        try {
            if(acao.equalsIgnoreCase("gSession")){
               gravaNaSessao(request, response);
            }
         }catch(Exception e){

        } finally { 
            out.close();
        }
    }

    public void gravaNaSessao(HttpServletRequest request, HttpServletResponse response)throws Exception{
        if(request.getSession().getAttribute("propostaEmAndamento")!=null){
                Proposta proposta =(Proposta)request.getSession().getAttribute("propostaEmAndamento");
                
                String[] valor      = request.getParameterValues("nome[valor]");
                String[] descricao  = request.getParameterValues("nome[descricao]");
                String[] quantidade = request.getParameterValues("nome[quantidade]");
                String[] status     = request.getParameterValues("nome[status]");

                int sizeDescricao =request.getParameterValues("nome[descricao]").length;
                
                List<Recompensa> resp = new ArrayList<Recompensa>();
                int  val[]   = new int[25];
                int  quant[] = new int[25];
                int  st[] = new int[25];
                for(int i = 0;i< sizeDescricao;i++){
                    val[i]=Integer.parseInt(valor[i]);
                    quant[i]=Integer.parseInt(quantidade[i]);
                    st[i]=Integer.parseInt(status[i]);
                    if(val[i]!= 0 && descricao[i]!= null && quant[i]!=0 && st[i] !=0){
                        System.out.println("<br>22      :"+valor[i]);
                        Recompensa rec = new Recompensa(val[i],descricao[i],quant[i],st[i],proposta.getProjeto());
                        resp.add(rec);

                    }
                        System.out.println("<br>apoio de      :"+valor[i]);
                        System.out.println("<br>recompensa    :"+descricao[i]);
                        System.out.println("<br>status        :"+st[i]);
                        System.out.println("<br>------------------------<br>");

                }
                proposta.getProjeto().setRecompensa(resp);
            request.getSession().setAttribute("pagina", "cadProjeto");
            request.getSession().setAttribute("nTab", "2");
            request.getSession().setAttribute("mensagem", "Escolha uma FOTO e um VIDEO de apresentação sobre o projeto.");
            response.sendRedirect(request.getContextPath()+"/usuario");



                System.out.println(proposta.getProjeto().getRecompensa().size());
            }else{
            response.sendRedirect("../");
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

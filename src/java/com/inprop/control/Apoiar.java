/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URL;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.domain.Currency;
import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;

import com.inprop.model.Apoio;
import com.inprop.model.Recompensa;
import com.inprop.model.Usuario;
import com.inprop.persistence.ApoioDao;
import com.inprop.persistence.RecompensaDao;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Ramon
 */
public class Apoiar extends HttpServlet {
   
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
        HttpSession session = request.getSession(true);
      try {

   




// Instantiate a new payment request
        PaymentRequest paymentRequest = new PaymentRequest();

        // Sets the currency
        paymentRequest.setCurrency(Currency.BRL);

        // Add an item for this payment request
        paymentRequest.addItem("0001", "Notebook Prata", new Integer(1), new BigDecimal("0.06"),
                new Long(1000), null);

        // Add another item for this payment request
       // paymentRequest.addItem("0002", "Notebook Rosa", new Integer(2), new BigDecimal("2560.00"),
         //       new Long(750), null);

        // Sets a reference code for this payment request, it's useful to identify this payment in future notifications.
        paymentRequest.setReference("REF1234");

        // Sets shipping information for this payment request
       
        // Sets your customer information.
        paymentRequest.setSender("João Comprador", "comprador@uol.com.br", "11", "56273440");

        try {

            URL paymentURL = paymentRequest.register(new AccountCredentials("contato@thiagoramon.com",
                    "ACB9D758582E4CD39CB19D1A98F09856"));

            out.println(paymentURL);
        } catch (PagSeguroServiceException e) {
            out.println(e.toString());
        }


            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Apoiar</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Apoiar at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
       } finally {
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

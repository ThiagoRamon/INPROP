/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.business.ProjetoBusiness;
import com.inprop.business.PropostaBusiness;
import com.inprop.model.Foto;
import com.inprop.model.Projeto;
import com.inprop.model.Proposta;
import com.inprop.model.Usuario;
import com.inprop.persistence.ProjetoDao;
import com.inprop.util.FormatoData;
import com.inprop.view.PropostaView;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Ramon
 */
public class ProjetoController extends HttpServlet {
   
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
        System.out.println(acao);
        try {
            if(acao.equalsIgnoreCase("initSession")){
                iniciaSessaoCadProjeto(request, response);
            }
            else if(acao.equalsIgnoreCase("cadProjeto")){
                cadProjeto(request, response);
            }
        }catch(Exception e){

        } finally { 
            out.close();
        }
    } 
    private void cadFoto(HttpServletRequest request, HttpServletResponse response)throws Exception{
    }


    private void iniciaSessaoCadProjeto(HttpServletRequest request, HttpServletResponse response)throws Exception{
       FormatoData data = new FormatoData();
       data.setFormat("yyyy-MM-dd");

        Usuario usuario= (Usuario) request.getSession().getAttribute("usuario");
        String breveDescricao = request.getParameter("breveDescricao");
        String descricao      =      request.getParameter("descricao");
        String titulo         =         request.getParameter("titulo");
        String valor          =     request.getParameter("valorTotal");
        String lkVideo        =        request.getParameter("lkVideo");
        int    dias           =     Integer.parseInt(request.getParameter("dias"));


//         System.out.println("\n breve descrucai :"+breveDescricao);
//         System.out.println("\n descricao       :"+descricao);
//         System.out.println("\n titulo          :"+titulo);
//         System.out.println("\n valor           :"+valor);
//         System.out.println("\n dias            : "+dias);
         int status =2;

         PropostaView pv = new PropostaView();
         pv.setPerfil(usuario.getPerfil());
         pv.setStatus(status);
         Proposta proposta =pv.getPropostasByPerfilAndStatusE();
         int valorTotal =Integer.parseInt(valor);

         Projeto projeto   =  new Projeto();
         projeto.setLink_video(lkVideo);
         projeto.setTitulo(titulo);
         projeto.setApresentacao(breveDescricao);
         projeto.setDescricao(descricao);
         projeto.setStatus(status);
         projeto.setValor_total(valorTotal);
         projeto.setProposta(proposta);
         projeto.setDias(dias);
         projeto.calc_data_final(data.getData(), dias);
         proposta.setProjeto(projeto);

//System.out.println("titulo    : "+proposta.getProjeto().getTitulo()+"<br>");
//System.out.println("apresentacao : "+proposta.getProjeto().getApresentacao()+"<br>");
//System.out.println("descricao : "+proposta.getProjeto().getDescricao()+"<br>");
//System.out.println("valor          : "+proposta.getProjeto().getValor_total()+"<br>");
//System.out.println("status        : "+proposta.getProjeto().getStatus()+"<br>");
//System.out.println("id proposta    : "+proposta.getId()+"<br>");
//
//System.out.println("dias      : "+dias+"<br>");

//
//
//System.out.println("data inicio   : "+proposta.getProjeto().getData_inicial()+"<br>");
//System.out.println("data atual    : "+proposta.getProjeto().getData_final()+"<br>");
request.getSession().setAttribute("propostaEmAndamento",proposta);

            request.getSession().setAttribute("pagina", "cadProjeto");
            request.getSession().setAttribute("nTab", "1");
            request.getSession().setAttribute("mensagem", "Escolha as recompensas para o seu projeto.");
            response.sendRedirect(request.getContextPath()+"/usuario");


    }





         public BufferedImage createImage(BufferedImage img, final int width, final int height) {

        final BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics = image.createGraphics();
        graphics.drawImage(img, null, 100, 100);
        return image;
    }





        public void write(final String filename, final BufferedImage image) {
        final File file = new File(filename);
            String extenxao = new Foto().extencao(filename);

        try {
            final FileOutputStream output = new FileOutputStream(file);

            ImageIO.write(image, extenxao, output);
            //ImageIO.write(image, "jpg", output);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

       public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
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

    private void cadProjeto(HttpServletRequest request, HttpServletResponse response)throws Exception {
        Proposta proposta = (Proposta) request.getSession().getAttribute("propostaEmAndamento");
        Usuario usuario   = (Usuario)  request.getSession().getAttribute("usuario");
        List<Proposta> resp = new ArrayList<Proposta>();
        ProjetoBusiness projetoBusiness= new ProjetoBusiness();

        try{
                    proposta.setProjeto(projetoBusiness.insert(proposta));

            if( proposta.getProjeto() != null ){
               PropostaBusiness propostaBusiness = new PropostaBusiness();
               proposta.setStatus(3);
               propostaBusiness.updateStatus(proposta);
                usuario.getPerfil().setProposta(resp);
                request.getSession().setAttribute("usuario" , usuario);
                request.getSession().setAttribute("pagina"  , "inicio");
                request.getSession().setAttribute("mensagem", "Seu projeto foi cadastrado com sucesso, boa sorte.");
                response.sendRedirect(request.getContextPath()+"/usuario");
                  
            }else{

                request.getSession().setAttribute("pagina", "inicio");
                request.getSession().setAttribute("mensagem", "Seu projeto não pode ser cadastrado no momento, por favor tente mais tarde.");
                response.sendRedirect(request.getContextPath()+"/usuario");

            }
            

        }catch(Exception e){
                System.out.println(e.getMessage()+"\n"+e.getStackTrace());
                request.getSession().setAttribute("pagina", "inicio");
                request.getSession().setAttribute("mensagem", "Erro ao cadastrar seu projeto, tente mais tarde se não conseguir entre em contato com a equipe inprop.");
                response.sendRedirect(request.getContextPath()+"/usuario");

        }

    }

}

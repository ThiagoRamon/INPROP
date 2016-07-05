/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.model.Foto;
import com.inprop.model.Proposta;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UploadFotoProjetoController extends HttpServlet {
   
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
        try {
                 DiskFileItemFactory fileUpload = new DiskFileItemFactory();
                ServletFileUpload sfu          = new ServletFileUpload(fileUpload);
                String cmd                     = null;

                            try {
                                     String path  = null;
                                     String file  = null;
                                     long size    = 0;
                                     BufferedImage img;

                                    if(request.getContentType()==null){
                                        out.println("nem uma informacao foi enviada!<br>");
                                     }
                                    if(!request.getContentType().startsWith("multipart/form-data")){
                                        out.println("formulario sem o multpart/form-data!<br>");
                                     }
                                    try{
                                        path = getServletContext().getRealPath("../../web/site/sistema/midia/projeto/imagem");
                                        List lista= sfu.parseRequest(request);
                                        Iterator iterator = lista.iterator();
                                        while(iterator.hasNext()){
                                            FileItem item = (FileItem) iterator.next();
                                            if(!item.isFormField()){
                                                  file = new Foto().crip(item.getName())+"."+new Foto().extencao(item.getName());

                                                if((file!=null)&&(!file.equals(""))){
                                                    file=(new File(file)).getName();
                                                    item.write(new File(path + "/" +file));

                                                  }
                                               }


                                          }





                             try {

                                 img = ImageIO.read(new File(path + "/" +file));
                                 write(path +"/thumb/"+file,  resize(img, 150, 150));
                                 write(path +"/small/"+file,  resize(img, 400, 400));
                                 write(path +"/normal/"+file,  resize(img, 708, 398));
                                 write(path +"/big/"+file,  resize(img, 800, 400));
                            } catch (IOException ex) {
                                 out.println(ex.getMessage());
                            }

                     out.println("<br>"+file);


                    String dados_do_projeto=null;
                    if(request.getSession().getAttribute("propostaEmAndamento")!=null){
                          Proposta proposta = (Proposta) request.getSession().getAttribute("propostaEmAndamento");
                          proposta.getProjeto().setFoto_projeto(file);

                                request.getSession().setAttribute("propostaEmAndamento",proposta);
                                request.getSession().setAttribute("pagina", "cadProjeto");
                                request.getSession().setAttribute("nTab", "999");
                                request.getSession().setAttribute("mensagem", "RESUMO.");
                                response.sendRedirect(request.getContextPath()+"/usuario");


                    //
                    //      ProjetoDao  projetoDao= new ProjetoDao();
                    //  try{
                    //      List resp = proposta.getProjeto().getRecompensa();
                    //      projetoDao.insert_Projeto_mais_recompensa(proposta, resp,file);
                    //
                    //
                    //    if(projetoDao!=null){
                    //          request.getSession().setAttribute("msg", "Seu Projeto Foi Cadastrado com sucesso!!!");
                    //          response.sendRedirect("usuario/");
                    //          out.println("projeto cadastrado com sucesso!!");
                    //       }else{
                    //       out.println("projeto nao cadastrado com sucesso!!");
                    //       }
                    //      }catch(Exception exe){
                    //                 out.println(exe.getStackTrace()+"<br>"+exe.getMessage());
                    //
                    //      }


                    }else{
                        out.println("falho!!");
                    }



                    }catch(Exception e){
                                             out.println("erro!!!<br>"+e.getMessage()+"<br>"+e.getStackTrace());
                                          }
                }catch(Exception e){

                }

        } finally { 
            out.close();
        }
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

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;
import com.inprop.model.Foto;
import com.inprop.model.Projeto;
import com.inprop.model.Proposta;
import com.inprop.model.Recompensa;
import com.inprop.persistence.FotoDao;
import com.inprop.persistence.ProjetoDao;
import com.inprop.persistence.PropostaDao;
import com.inprop.util.FormatoData;
import com.inprop.util.MecanicaCriptografaUtil;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Ramon
 */
public class ControleUpLoad extends HttpServlet {
   
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
        HttpSession session  = request.getSession(true);

        DiskFileItemFactory fileUpload = new DiskFileItemFactory();
        ServletFileUpload sfu= new ServletFileUpload(fileUpload);

        
        String cmd=null;
        try {
            cmd= request.getParameter("cmd");
            if(cmd.equals("projeto")){
 //                session.getAttribute("");
                 String path =null;
                 String file=null;
                  BufferedImage img;
                        long size= 0;
                if(request.getContentType()==null){
                    out.println("nem uma informacao foi enviada!<br>");
                 }
                if(!request.getContentType().startsWith("multipart/form-data")){
                    out.println("formulario sem o multpart/form-data!<br>");
                 }
                try{
                    path = getServletContext().getRealPath("../../web/imgProjeto");
//                    path = getServletContext().getRealPath("../../web/imagem");
                    List lista= sfu.parseRequest(request);
                    Iterator iterator = lista.iterator();
                    while(iterator.hasNext()){
                        FileItem item = (FileItem) iterator.next();
                        if(!item.isFormField()){
                            file = item.getName();
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
             write(path +"/big/"+file,  resize(img, 800, 800));
        } catch (IOException ex) {
             out.println(ex.getMessage());
        }

 out.println("<br>"+file);


String dados_do_projeto=null;
if(session.getAttribute("proposta_projeto")!=null){
      Proposta proposta = (Proposta) session.getAttribute("proposta_projeto");
      ProjetoDao  projetoDao= new ProjetoDao();
  try{
      List resp = proposta.getProjeto().getRecompensa();
     // projetoDao.insert_Projeto_mais_recompensa(proposta, resp,file);
       

    if(projetoDao!=null){
          session.setAttribute("msg", "Seu Projeto Foi Cadastrado com sucesso!!!");
          response.sendRedirect("usuario/");
          out.println("projeto cadastrado com sucesso!!");
       }else{
       out.println("projeto nao cadastrado com sucesso!!");
       }
      }catch(Exception exe){
                 out.println(exe.getStackTrace()+"<br>"+exe.getMessage());

      }


}else{
    out.println("falho!!");
}







//                      int id_trabalho=Integer.parseInt(id);
//                       trabalho= new Trabalho(id_trabalho,titulo, nome_arquivo, descricao, link);
//                      out.println("<br>"+trabalho.getTitulo());
//                      out.println("<br>"+trabalho.getLink_para_o_site());
//                      out.println("<br>"+trabalho.getDescricao());
//                      out.println("<br>"+trabalho.getNome_img());
//                      out.println("<br>"+id);
//                      TrabalhoDao  trabalhoDao=new TrabalhoDao();
//                      try{
////                          trabalhoDao.update(trabalho);
////                          if(trabalhoDao!=null){
////                              response.sendRedirect("administracao_tr_web/index.jsp?page=portifolio");
////                          }else{
////                              out.print("ERROR!!!");
////                          }
//                      }catch(Exception e){
//                         out.println("erro!!!<br>"+e.getMessage()+"<br>"+e.getStackTrace());
//                      }

//                } catch (Exception e) {
//
//                             out.println(e.getMessage());
//                }
//
//
//


}catch(Exception e){
                         out.println("erro!!!<br>"+e.getMessage()+"<br>"+e.getStackTrace());
                      }













    }else if(cmd.equals("foto")){
        //                session.getAttribute("");
                 int id_projeto = Integer.parseInt(request.getParameter("projeto"));
                  String path =null;
                  String file=null;
                  String status ="a";
                  String data_atual = new FormatoData().data_atual();
                  BufferedImage img;
                  String extensao=null;
                  String nome_sem_extensao=null;
                  String nome_crip=null;
                  Foto f= new Foto();
                  MecanicaCriptografaUtil n=  MecanicaCriptografaUtil.getInstance();


                  long size= 0;
                        if(request.getContentType()==null){
                            out.println("nem uma informacao foi enviada!<br>");
                         }
                        if(!request.getContentType().startsWith("multipart/form-data")){
                            out.println("formulario sem o multpart/form-data!<br>");
                         }
                        try{
                            path = getServletContext().getRealPath("../../web/imgProjeto");
        //                    path = getServletContext().getRealPath("../../web/imagem");
                            List lista= sfu.parseRequest(request);
                            Iterator iterator = lista.iterator();
                            while(iterator.hasNext()){
                                FileItem item = (FileItem) iterator.next();
                                if(!item.isFormField()){
                                    nome_sem_extensao     = f.nome_sem_extencao(item.getName());
                                    extensao              = f.extencao(item.getName());
                                    nome_crip             = n.criptografaSenha(nome_sem_extensao);
                                    file = nome_crip+"."+extensao;
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
             write(path +"/big/"+file,  resize(img, 800, 800));
        } catch (IOException ex) {
             out.println(ex.getMessage());
        }


      Projeto projeto = new Projeto();
      projeto.setId(id_projeto);

      FotoDao fotoDao= new FotoDao();
      f.setData_emissao(data_atual);
      f.setStatus(status);
      f.setProjeto(projeto);
      f.setFoto(file);

  try{
      fotoDao.insert(f);
              if(fotoDao!=null){
                  session.setAttribute("msg", "Seu foto Foi Cadastrado com sucesso!!!");
                  response.sendRedirect("usuario/?page=projeto");
                  out.println("projeto cadastrado com sucesso!!");
               }else{
               out.println("foto nao cadastrado com sucesso!!");
               }
      }catch(Exception exe){
                 out.println(exe.getStackTrace()+"<br>"+exe.getMessage());

      }



}catch(Exception e){
                         out.println("erro!!!<br>"+e.getMessage()+"<br>"+e.getStackTrace());
                      }


    }else if(cmd.equals("video")){

    }

            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControleUpLoad</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControleUpLoad at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
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

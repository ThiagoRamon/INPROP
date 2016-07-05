/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.control;

import com.inprop.business.BairroBusiness;
import com.inprop.business.CEPBusiness;
import com.inprop.business.CidadeBusiness;
import com.inprop.business.EstadoBusiness;
import com.inprop.business.PerfilBusiness;
import com.inprop.model.Bairro;
import com.inprop.model.CEP;
import com.inprop.model.Cidade;
import com.inprop.model.Endereco;
import com.inprop.model.Estado;
import com.inprop.model.Foto;
import com.inprop.model.Perfil;
import com.inprop.model.Usuario;
import com.inprop.persistence.Contato;
import com.inprop.persistence.EstadoDao;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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
public class PerfilController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String CAD_PERFIL_COM_UPLOAD= "cadComFoto";
    private static final String CAD_PERFIL_SEM_UPLOAD= "cadSemFoto";
    private static final String CAD_SOBRE= "cadSobre";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
             String acao = request.getParameter("acao");
        try{

            if(acao.equalsIgnoreCase(CAD_SOBRE)){
                updateSobre(request,response);
            }
        //    if(acao.equalsIgnoreCase(CAD_PERFIL_COM_UPLOAD)){
               // cadastroPerfilComFoto(request, response);
          //  }else
            //    if(acao.equalsIgnoreCase(CAD_PERFIL_SEM_UPLOAD)){
            //    cadastroPerfilSemFoto(request, response);
            //}
        }catch(Exception e){
            
        }
  
    }

    private void updateSobre(HttpServletRequest request, HttpServletResponse response){
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        String sobre =(request.getParameter("sobre")!=null? request.getParameter("sobre") : usuario.getPerfil().getBiografia());
        Perfil perfil =usuario.getPerfil();
        perfil.setBiografia(sobre);
        PerfilBusiness perfilBusiness = new PerfilBusiness();
        try{
            if(perfilBusiness.updateSobre(perfil)){
                usuario.setPerfil(perfil);
               request.getSession().setAttribute("pagina", "info");
               response.sendRedirect(request.getContextPath()+ "/usuario");
            }else{

               request.getSession().setAttribute("pagina", "info");
               request.getSession().setAttribute("mensagem", "info");
               response.sendRedirect(request.getContextPath()+ "/usuario");
            }
            
        }catch(Exception e){
            request.getSession().setAttribute("pagina","info");
            request.getSession().setAttribute("mensagem","***Exception 0001***");
        }
    }
    private void cadastroPerfilSemFoto(HttpServletRequest request, HttpServletResponse response)throws  Exception{
           response.setContentType("text/html;charset=ISO-8859-1");
           PrintWriter out = response.getWriter();
           try{
           
               
               

             out.print("--------------------");
           }finally{
           out.close();
           }

    }

        private void cadastroPerfilComFoto(HttpServletRequest request, HttpServletResponse response)throws  Exception{

           response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();


        DiskFileItemFactory fileUpload = new DiskFileItemFactory();
        ServletFileUpload sfu= new ServletFileUpload(fileUpload);

        String biografia = null;

        String tel       = null;
        String cel       = null;
        String comercial = null;
        String fax       = null;
        String faceBook      = null;
        String twitter       = null;
        String canalYouTupe  = null;
        String obs  = null;

        String tp_logradouro    = null;
        String logradouro       = null;
        String numero           = null;
        String complemento      = null;
        String referencia       = null;


        String nome_bairro           = null;
        String nome_cidade           = null;
        String numCep                = null;
        String uf                    = null;




        String cmd=null;
        try {
       // Usuario usu = (Usuario) session.getAttribute("usuario");
//        out.println("id....:"+usuario.getId_usuario());
//        out.println("<br>");
//        out.println("email....:"+usuario.getEmail());
                 String path =null;
                 String file="b1.jpg";
                 BufferedImage img;
                 long size= 0;
                if(request.getContentType()==null){
                    out.println("nem uma informacao foi enviada!<br>");
                 }
                if(!request.getContentType().startsWith("multipart/form-data")){
                    out.println("formulario sem o multpart/form-data!<br>");
                 }
                try{
//                    path = getServletContext().getRealPath("../../web/imgProjeto");
                    path = getServletContext().getRealPath("../../web/usuario/media/imagem/normal");
                    System.out.println("path : "+path);
                    List lista= sfu.parseRequest(request);
                    Iterator iterator = lista.iterator();
                    while(iterator.hasNext()){
                        FileItem item = (FileItem) iterator.next();
                        if(!item.isFormField()){
                            if(!item.getName().equalsIgnoreCase(null) && item.getName()!=""){
                                 file = new Foto().crip(item.getName())+"."+new Foto().extencao(item.getName());

                                if((file!=null)&&(!file.equals(""))){
                                    file=(new File(file)).getName();
                                    item.write(new File(path + "/" +file));

                                  }
                              }

                           }
                         if(item.getFieldName().equals("cep")){
                               numCep= item.getString();
                           }
                         if(item.getFieldName().equals("uf")){
                               uf= item.getString();
                           }
                         if(item.getFieldName().equals("cidade")){
                               nome_cidade= item.getString();
                           }
                         if(item.getFieldName().equals("bairro")){
                               nome_bairro= item.getString();
                           }
                         if(item.getFieldName().equals("referencia")){
                               referencia= (item.getString()!=null?item.getString():"");
                           }
                         if(item.getFieldName().equals("complemento")){
                               complemento= (item.getString()!=null?item.getString():"");
                           }
                         if(item.getFieldName().equals("numero")){
                               numero= (item.getString()!=null?item.getString():"");
                           }
                         if(item.getFieldName().equals("logradouro")){
                               logradouro= (item.getString()!=null?item.getString():"");
                           }
                         if(item.getFieldName().equals("tp_logradouro")){
                               tp_logradouro= (item.getString()!=null?item.getString():"");
                           }

                         if(item.getFieldName().equals("biografia")){
                               biografia= (item.getString()!=null?item.getString():"");
                           }
                         if(item.getFieldName().equals("tel")){
                               tel= (item.getString()!=null?item.getString():"--");
                           }
                         if(item.getFieldName().equals("fax")){
                               fax= (item.getString()!=null?item.getString():"--");
                           }
                         if(item.getFieldName().equals("cel")){
                               cel= (item.getString()!=null?item.getString():"--");
                           }
                         if(item.getFieldName().equals("comercial")){
                               comercial = (item.getString()!=null?item.getString():"--");
                           }
                         if(item.getFieldName().equals("faceBook")){
                               faceBook = (item.getString()!=null?item.getString():"--");
                           }
                         if(item.getFieldName().equals("twitter")){
                               twitter = (item.getString()!=null?item.getString():"--");
                           }
                         if(item.getFieldName().equals("canalYouTube")){
                               canalYouTupe = (item.getString()!=null?item.getString():"--");
                           }
                         if(item.getFieldName().equals("obs")){
                               obs = (item.getString()!=null?item.getString():"--");
                           }

                      }

                    out.println(tp_logradouro);
                    out.println(logradouro);
                    out.println(numero);
                    out.println(complemento);
                    out.println(referencia);

                    out.println(nome_bairro);
                    out.println(nome_cidade);
                    out.println(uf);
                    out.println(numCep);



                    Estado estado = new Estado(uf);
                    Endereco  end = null;
                    EstadoBusiness estadoBusiness= new EstadoBusiness();
                    CidadeBusiness cidadeBusiness= new CidadeBusiness();
                    BairroBusiness bairroBusiness= new BairroBusiness();
                    CEPBusiness cepBusiness= new CEPBusiness();
                    try{

                       estado =  estadoBusiness.atualizaEstado(estado);
                       if( estado!=null  ){
                             Cidade cidade = new Cidade(nome_cidade,"A",estado);
                             cidade =cidadeBusiness.insertCidade(cidade);
                             if(cidade!=null){
                                Bairro bairro = new Bairro(nome_bairro,"A",cidade);
                                 bairro = bairroBusiness.insertBairroByUsuario(bairro);
                                 if( bairro != null ){
                                     CEP  cep = new CEP(numCep,tp_logradouro,logradouro,bairro);
                                     cep      = cepBusiness.insertCidade(cep);
                                     if(cep != null){
                                          end = new Endereco(numero, complemento, referencia, cep);
                                     }
                                 }
                             }
                       }
                    }catch(Exception e){
                        System.out.println(e.getMessage()+"/n"+e.getStackTrace());
                    }

  out.println ("=====>>"+end.getCep().getId());

                 Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                 Perfil   perfil = new Perfil(file, biografia,"A");
                 Contato contato = new Contato();
                 contato.setPerfil(perfil);
                 contato.setTel(tel);
                 contato.setCel(cel);
                 contato.setFax(fax);
                 contato.setTelComercial(comercial);
                 contato.setFaceBook(faceBook);
                 contato.setTwitter(twitter);
                 contato.setCanalYouTube(canalYouTupe);
                 contato.setObs(obs);

                 perfil.setContato(contato);
                 perfil.setUsuario(usuario);
                 perfil.setEndereco(end);

                 PerfilBusiness perfilBusiness = new PerfilBusiness();


                    try {
                          img = ImageIO.read(new File(path + "/" +file));
                          write(path +"/thumb/"+file,  resize(img, 100, 100));
                          write(path +"/small/"+file,  resize(img, 400, 400));
                          write(path +"/big/"+file,  resize(img, 800, 800));
                                   try{

                                        if(perfilBusiness.cadastraPerfil(perfil)){


                                            usuario.setPerfil(perfil);
                                            request.getSession().setAttribute("usuario", usuario);
                                            response.sendRedirect(request.getContextPath() + "/usuario");
                                        }else{
                                            response.sendRedirect(request.getContextPath()+ "/usuario");
                                        }

                                 }catch(Exception e){
                                      response.sendRedirect(request.getContextPath() + "/usuario");
                                       System.out.println( e.getMessage()+"\n"+e.getStackTrace() );

                                 }

                    } catch (IOException ex) {
                       // response.sendRedirect(request.getContextPath() + "/usuario");
                                       System.out.println( ex.getMessage()+"\n"+ex.getStackTrace() );

                    }




                }catch(Exception e){
                 System.out.println(e.getMessage());
                // response.sendRedirect(request.getContextPath() + "/usuario");
                 }
              }catch(Exception e){
                 System.out.println(e.getMessage());
                // response.sendRedirect(request.getContextPath() + "/usuario");
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

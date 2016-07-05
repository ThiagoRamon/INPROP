/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Ramon
 */
public class Util {
    
    public void soucerViewer(String[] a){
     
            try{
                URL url = new URL("http://localhost:8080/INPROPBeta/");
                InputStream in = url.openStream();
                in = new BufferedInputStream(in);
                Reader r = new InputStreamReader(in);
                int c;
                while((c = r.read()) != -1){
                  System.out.print((char)c);
                }
            }catch(Exception e){
            
            }
        }

    public boolean isNull( String[]  arrEl){

        for(String el : arrEl){
            System.out.print(el+"\n");
            if(el == null || el== ""){
                return false;
            }
        }
    return true;
    }

    public static void testHttpClient() throws URISyntaxException, IOException{
        HttpGet httpGet  = new HttpGet("http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=");
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("www.google.com").setPath("/search")
        .setParameter("q", "httpclient")
        .setParameter("btnG", "Google Search")
        .setParameter("aq", "f")
        .setParameter("oq", "");
        URI uri = builder.build();
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpGet.getURI());
        System.out.println(httpget.getURI());
        
        
        HttpClient httpClient = new DefaultHttpClient();
        builder = new URIBuilder();
        builder.setScheme("http").setHost("www.inprop.com.br/INPROPBeta").setPath("/UsuarioController")
                .setParameter("login" ,"ramon@hotmail.com.ig")
                .setParameter("senha" ,"ramon")
                .setParameter("acao" ,"login");
         uri = builder.build();
         httpGet = new HttpGet(uri);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity != null){
            InputStream in = entity.getContent();
            System.out.println(">>>>>"+EntityUtils.toString(entity));
        }

    }

    public static void buscaPorNome()throws Exception{

        HttpClient httpClient = new  DefaultHttpClient();
       URIBuilder builder    = new URIBuilder();


              List<NameValuePair> formparams = new ArrayList<NameValuePair>();
formparams.add(new BasicNameValuePair("acao", "consulta"));
formparams.add(new BasicNameValuePair("descOrigem","Juizados Especiais"));
formparams.add(new BasicNameValuePair("descComarca","--- Selecione ---"));
formparams.add(new BasicNameValuePair("descCompetencia","--- Selecione ---"));
formparams.add(new BasicNameValuePair("paginaAtual",""));
formparams.add(new BasicNameValuePair("privada","0"));
formparams.add(new BasicNameValuePair("origem","7"));
formparams.add(new BasicNameValuePair("tiposecinst","1"));
formparams.add(new BasicNameValuePair("anoInicial","2012"));
formparams.add(new BasicNameValuePair("anoFinal","2013"));
formparams.add(new BasicNameValuePair("nomeParte","thiago"));
UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
//       builder.setScheme("http").setHost("srv85.tjrj.jus.br").setPath("/consultaProcessoNome/WEB-INF/jsp/resultadoNome.jsp").setPort(7779)
//       .setParameter("acao", "consulta")
//       .setParameter("descOrigem","Juizados Especiais")
//       .setParameter("descComarca","--- Selecione ---")
//       .setParameter("descCompetencia","--- Selecione ---")
//       //.setParameter("novaTela","true")
//     //  .setParameter("comarca","TODAS")
//      // .setParameter("competencia","08")
//       .setParameter("origem","7")
//       .setParameter("anoInicial","2012")
//       .setParameter("anoFinal","2013")
//       .setParameter("nomeParte","thiago")
//       .setParameter("tiposecinst","1")
//       .setParameter("paginaAtual","")
//       .setParameter("privada","0");
       builder.setScheme("http").setHost("srv85.tjrj.jus.br").setPath("/consultaProcessoNome/ConsultaNome.do");
      

 URI uri               = builder.build();
 HttpPost post         = new HttpPost(uri);
 post.setEntity(entity);
 HttpResponse response = httpClient.execute(post);
 HttpEntity entityy     = post.getEntity();//response.getEntity();
       if(entityy !=null){
           System.out.println(EntityUtils.toString(entityy));
       }



////         <input type="hidden" name="paginaAtual" value="">
////                      <input type="hidden" name="privada" value="0">
//
//       URI uri               = builder.build();
//
       //HttpGet post         = new HttpGet(uri);
          ///    HttpPost post         = new HttpPost(uri);
//       System.out.println(post);
//       //HttpPost post         = new HttpPost(uri);
//       HttpResponse response = httpClient.execute(post);
//     //  HttpEntity
//       entityy     = response.getEntity();
//       if(entityy !=null){
//           System.out.println(EntityUtils.toString(entityy));
//       }


    }
    public static void main(String[] args) throws Exception, URISyntaxException, IOException{
        // Util.buscaPorNome();
        // Util.testHttpClient();
//       Util u  =new Util();
//       u.soucerViewer(args);


                     // File vPath= new File("c://google.com");

                      //System.out.println();

    }

}

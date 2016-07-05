/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

import com.inprop.util.MecanicaCriptografaUtil;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;



/**
 *
 * @author Ramon
 */
public class Foto {
    private int id_foto;
    private String foto;
    private String status;
    private String data_emissao;
    private Projeto projeto;

    public String crip(String nome){
        String nomeCrip=null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();

            md.update(nome.getBytes());

            //md.digest(nome.getBytes());
                  byte[] digest1 = md.digest();
          //  md.digest(nome.getBytes());
            BigInteger hash = new BigInteger(1,md.digest());
            nomeCrip = hash.toString(16);
             BASE64Encoder encoder = new BASE64Encoder ();
             return encoder.encode(digest1);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Foto.class.getName()).log(Level.SEVERE, null, ex);
        }

   return nomeCrip;
               
    }
    
    public String nome_sem_extencao(String nome){
        int sizeNome = nome.length();
        char n[] = new char[sizeNome];
        String nome_sem_ext="";
        for(int i= 0 ; i < (sizeNome-4);i++){
           n[i] = nome.charAt(i);
           nome_sem_ext +=  n[i];

        }
    
    return  crip(nome_sem_ext);
    }

    public String extencao(String im){
            int siziImg = im.length();
            char ext[] = new char[4];
            int cont = 4;
            String c= "" ;
            for(int i =0 ;i<siziImg;i++){
             if(im.charAt(siziImg-(i+1))== '.'){
                 break;
             }
             cont = cont-1;
             if(cont>=0 && cont<4){
              ext[cont]= im.charAt(siziImg-(i+1));
             }
            }
           // c =ext[2];
            c +=ext[0];
            c +=ext[1];
            c +=ext[2];
            if(ext[3]!=0){
             c +=ext[3];
            }
            //c=+ext[0];
            c=c.trim();

            String extencao = null;
        if(c.equalsIgnoreCase("jpg")){
                    extencao ="jpg";

        }else
            if(c.equalsIgnoreCase("png")){
                    extencao ="png";
        }else
            if(c.equalsIgnoreCase("gif")){
                    extencao ="gif";
        }else
            if(c.equalsIgnoreCase("jpeg")){
                    extencao ="jpeg";

            }
            return  extencao;


        }

    /**
     * @return the id_foto
     */
    public int getId_foto() {
        return id_foto;
    }

    /**
     * @param id_foto the id_foto to set
     */
    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }

    /**
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the projeto
     */
    public Projeto getProjeto() {
        return projeto;
    }

    /**
     * @param projeto the projeto to set
     */
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    /**
     * @return the data_emissao
     */
    public String getData_emissao() {
        return data_emissao;
    }

    /**
     * @param data_emissao the data_emissao to set
     */
    public void setData_emissao(String data_emissao) {
        this.data_emissao = data_emissao;
    }


    public static void main(String args[]) throws NoSuchAlgorithmException{
        Foto f= new Foto();
        MecanicaCriptografaUtil n=  MecanicaCriptografaUtil.getInstance();

        System.out.println(n.criptografaSenha("thi"));
        System.out.println(n.criptografaSenha("thiramon "));
        System.out.println(n.criptografaSenha("thi.jpg"));

        String nome ="thi0 nnnnnnnn 00a.png";
        System.out.println(f.nome_sem_extencao(nome));
        System.out.println(f.extencao(nome));
        System.out.println(new Foto().crip(nome));

       String nomec = new Foto().crip("thiagoramonruhgerftghnjmhgfdefvgbhnjmkjnhbgvfcdcfvgbhnfghjklkjhgfdfghjkjhgfghjhgffghhgfdfghg.jpg");
       System.out.println(nomec+"\n");
        System.out.println(nomec.length()+"\n");

    }

}

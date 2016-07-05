/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ramon
 */
public class MecanicaCriptografaUtil {

 private static MecanicaCriptografaUtil instance;
 private MecanicaCriptografaUtil(){}

//Padrao Singleton, para garantir apenas uma instancia dessa classe.
 public static MecanicaCriptografaUtil getInstance(){
 if(instance==null){
 instance = new MecanicaCriptografaUtil();
 }
 return instance;
 }

 //A Funcao
 public String criptografaSenha(String senha) throws NoSuchAlgorithmException{
 //Essa classe pega um valor de tamanho arbitrario e transforma em um valor de tamanho fixo
 MessageDigest md = MessageDigest.getInstance( "SHA" );


//Atualiza o valor com os bytes especificados
 md.update( senha.getBytes() );
//Conclui o cálculo de hash realizando operações finais, tais como  preenchimento.
 BigInteger hash = new BigInteger( 1, md.digest() );
//Retorna a representação String decimal deste BigInteger, com tamanho igual a 16.
 String retornaSenha = hash.toString( 16 );
 return retornaSenha;
 }
}


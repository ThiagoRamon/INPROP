/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

/**
 *
 * @author Ramon
 */
import java.util.regex.*;
public class ExpReg {

    public boolean  validaNome(String nome){
        Pattern padrao = Pattern.compile("[a-z A-Z].{3,10}+");
        Matcher m = padrao.matcher(nome);
        return m.matches();
    }
    public boolean  validaEmail(){

        return true;
    }
public static void main(String args[]){
    ExpReg e = new  ExpReg();
    System.out.println(e.validaNome("Th"));
}
}

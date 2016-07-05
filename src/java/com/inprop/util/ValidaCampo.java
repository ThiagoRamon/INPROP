/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.util;

/**
 *
 * @author Ramon
 */
public class ValidaCampo {
    
    private static ValidaCampo instance;
    public ValidaCampo(){}
    public static ValidaCampo  getInstance(){
          if(instance == null){
               instance = new ValidaCampo();
          }
          return instance;
    }

    public boolean isNull(String attr){
        boolean iSnull = true;
        if(attr == null){
             iSnull = false;
          }
        return iSnull;
    }
    public boolean isNull(String[] attr){
        boolean iSnull = true;
        for(int i=0; i < attr.length; i++){
            if(attr[i] == null){
                 iSnull = false;
            }

        }
        return iSnull;
    }

}

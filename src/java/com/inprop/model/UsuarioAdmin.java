/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

/**
 *
 * @author Ramon
 */
public class UsuarioAdmin {
    private int id_usuario;
    private String nome;
    private String email;
    private String senha;
    private String data_cadastro;
    public UsuarioAdmin(){
    }
    public UsuarioAdmin(String nome, String email, String senha,String data_cadastro){
        this.nome          = nome;
        this.email         = email;
        this.senha         = senha;
        this.data_cadastro = data_cadastro;
    }
    public UsuarioAdmin(int id, String nome, String email, String senha,String data_cadastro){
        this.id_usuario    = id;
        this.nome          = nome;
        this.email         = email;
        this.senha         = senha;
        this.data_cadastro = data_cadastro;
    }

}

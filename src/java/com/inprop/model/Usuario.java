/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.model;

import java.sql.Date;

/**
 *
 * @author Ramon
 */
public class Usuario {
    private int    id;
    private String nome;
    private String sobreNome;
    private String email;
    private String senha;
    private String status;
    private String genero;
    private String tipo;
    private String dataCadastro;
    private String dataConfirmacao;
    private Date dataNascimento;
    private Perfil perfil;

    public Usuario(){}
    public Usuario(String nome, String sobreNome, String genero, String email,
                         String senha,Date dataNascimento, String status, String tipo){
        this.nome      = nome;
        this.sobreNome = sobreNome;
        this.genero    = genero;
        this.email     = email;
        this.senha     = senha;
        this.dataNascimento = dataNascimento;
        this.status         = status;
        this.tipo           = tipo;
    }
    public Usuario(int id_usuario, String email){
        this.id=id_usuario;
        this.email=email;
    }
    public Usuario(String email,String senha){
        this.email=email;
        this.senha=senha;
    }

    public Usuario(int id_usuario, String email,Perfil perfil){
        this.id=id_usuario;
        this.email=email;
        this.perfil = perfil;
    }

    public Usuario(String email, String senha, String status, String data_cadastro){
        this.email=email;
        this.senha=senha;
        this.status=status;
        this.dataCadastro=data_cadastro;
    }

    public Usuario(int id_usuario,String email, String senha, String status, String data_cadastro,Perfil perfil){
         this.id=id_usuario;
        this.email=email;
        this.senha=senha;
        this.status=status;
        this.dataCadastro=data_cadastro;
        this.perfil = perfil;
    }
    public Usuario(int id_usuario,String email, String senha, String status, String data_cadastro){
        this.id=id_usuario;
        this.email=email;
        this.senha=senha;
        this.status=status;
        this.dataCadastro=data_cadastro;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return getId();
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.setId(id_usuario);
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
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
     * @return the data_cadastro
     */
    public String getData_cadastro() {
        return getDataCadastro();
    }

    /**
     * @param data_cadastro the data_cadastro to set
     */
    public void setData_cadastro(String data_cadastro) {
        this.setDataCadastro(data_cadastro);
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sobreNome
     */
    public String getSobreNome() {
        return sobreNome;
    }

    /**
     * @param sobreNome the sobreNome to set
     */
    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    /**
     * @return the dataConfirmacao
     */
    public String getDataConfirmacao() {
        return dataConfirmacao;
    }

    /**
     * @param dataConfirmacao the dataConfirmacao to set
     */
    public void setDataConfirmacao(String dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the dataCadastro
     */
    public String getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
}

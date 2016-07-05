/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inprop.model;

import com.inprop.util.FormatoData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class Projeto {//extends Proposta {
    private int        id;
    private String     foto_projeto;
    private String     link_video;
    private String     titulo;
    private String     apresentacao;
    private String     descricao;
    private Date       data_inicial;
    private Date       data_final  ;
    private int        status;
    private int        valor_total;
    private Proposta   proposta;
    private int        total_recebido;
    private int        dias;
    private long       dias_restantes;

    private int        levantado;
    private int        atingido;
    private int        pessoasApoiando;
    private int        quantidadeDeProjetosQueApoio;

    
    private List<Comentario> comentario;


    private List<Recompensa> recompensa;
    private List<Foto> foto;
    private Video video;

    
    public Projeto(){}
    public Projeto(String titulo, String descricao,
                   int status,int valor, Proposta proposta,String foto_projeto){
        this.foto_projeto  = foto_projeto;
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.status        = status;
        this.valor_total   = valor;
        this.proposta      = proposta;
    }
    public Projeto(String foto_projeto,String titulo, String descricao,
                   int status,int valor, Proposta proposta){
        this.foto_projeto  = foto_projeto;
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.status        = status;
        this.valor_total   = valor;
        this.proposta      = proposta;
    }
    public Projeto(String foto_projeto,String titulo, String descricao,
                   Date data_inicial, Date data_final,int status){
        this.foto_projeto  = foto_projeto;
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.data_inicial  = data_inicial;
        this.data_final    = data_final;
        this.status        = status;
    }
    public Projeto(String titulo, String descricao, Date data_inicial,
           Date data_final,int status, Proposta proposta){
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.data_inicial  = data_inicial;
        this.data_final    = data_final;
        this.status        = status;
        this.proposta      = proposta;
    }
    public Projeto(int id_projeto, String titulo, String descricao, 
           Date data_inicial, Date data_final,int status
           ){
        this.id    = id_projeto;
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.data_inicial  = data_inicial;
        this.data_final    = data_final;
        this.status        = status;
       
    }
    public Projeto(int id_projeto, String titulo, String descricao,
           Date data_inicial, Date data_final,int status,
           Proposta proposta){
        this.id    = id_projeto;
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.data_inicial  = data_inicial;
        this.data_final    = data_final;
        this.status        = status;
        this.proposta      = proposta;
    }
    public Projeto(int id_projeto, String titulo, String descricao,
           Date data_inicial, Date data_final,int status,
           Proposta proposta,List<Recompensa> rec, List<Foto> foto){
        this.id    = id_projeto;
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.data_inicial  = data_inicial;
        this.data_final    = data_final;
        this.status        = status;
        this.proposta      = proposta;
        this.foto          = foto;
        this.recompensa    = rec;
    }
    public Projeto(int id_projeto, String titulo, String descricao,
           Date data_inicial, Date data_final,int status,
           Proposta proposta, List<Foto> foto, Video video,List<Comentario> comentario){
        this.id    = id_projeto;
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.data_inicial  = data_inicial;
        this.data_final    = data_final;
        this.status        = status;
        this.proposta      = proposta;
        this.foto          = foto;
        this.video         = video;
        this.comentario    = comentario;
    }
    public Projeto(int id_projeto, String titulo, String descricao,
           Date data_inicial, Date data_final,int status,
           Proposta proposta,List<Recompensa> rec, List<Foto> foto, Video video,List<Comentario> comentario){
        this.id   = id_projeto;
        this.titulo        = titulo;
        this.descricao     = descricao;
        this.data_inicial  = data_inicial;
        this.data_final    = data_final;
        this.status        = status;
        this.proposta      = proposta;

        this.recompensa    = rec;
        this.foto          = foto;
        this.video         = video;
        this.comentario    = comentario;
    }

       public void calc_data_final(String data_inicial,int total_dias){
           java.util.Date data = new java.util.Date();
           DateFormat dff = new  SimpleDateFormat("yyyy-MM-dd");
          // System.out.println("Data de atual : "+dff.format(data));
           FormatoData fd     =  new FormatoData();
           String data_futura =  fd.getData_final(data, total_dias);
           this.data_inicial = Date.valueOf(data_inicial);
           this.data_final=  Date.valueOf(data_futura);
           System.out.println( this.data_inicial+"Data de futura : "+this.data_final);

       }


    public int getTotal_recebido(){
        return total_recebido;
    }
    public void setTotal_recebido(int total_recebido){
       this.total_recebido = total_recebido;
    }

      

    /**
     * @return the id_projeto
     */
    public int getId() {
        return id;
    }

    /**
     * @param id_projeto the id_projeto to set
     */
    public void setId(int id_projeto) {
        this.id = id_projeto;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the data_inicial
     */
    public Date getData_inicial() {
        return data_inicial;
    }

    /**
     * @param data_inicial the data_inicial to set
     */
    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    /**
     * @return the data_final
     */
    public Date getData_final() {
        return data_final;
    }

    /**
     * @param data_final the data_final to set
     */
    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the proposta
     */
    public Proposta getProposta() {
        return proposta;
    }

    /**
     * @param proposta the proposta to set
     */
    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    /**
     * @return the foto
     */
    public List<Foto> getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(List<Foto> foto) {
        this.foto = foto;
    }

    /**
     * @return the video
     */
    public Video getVideo() {
        return video;
    }

    /**
     * @param video the video to set
     */
    public void setVideo(Video video) {
        this.video = video;
    }

    /**
     * @return the comentario
     */
  public List<Comentario> getComentario(){return comentario;}

    public void setComentario(List<Comentario> comentario){
        this.comentario=comentario;
    }

    /**
     * @return the valor_total
     */
    public int getValor_total() {
        return valor_total;
    }

    /**
     * @param valor_total the valor_total to set
     */
    public void setValor_total(int valor_total) {
        this.valor_total = valor_total;
    }

    /**
     * @return the recompensa
     */
    public List<Recompensa> getRecompensa() {
        return recompensa;
    }

    /**
     * @param recompensa the recompensa to set
     */
    public void setRecompensa(List<Recompensa> recompensa) {
        this.recompensa = recompensa;
    }

    /**
     * @return the foto_projeto
     */
    public String getFoto_projeto() {
        return foto_projeto;
    }

    /**
     * @param foto_projeto the foto_projeto to set
     */
    public void setFoto_projeto(String foto_projeto) {
        this.foto_projeto = foto_projeto;
    }

    /**
     * @return the breveDescricao
     */
    public String getApresentacao() {
        return apresentacao;
    }

    /**
     * @param breveDescricao the breveDescricao to set
     */
    public void setApresentacao(String breveDescricao) {
        this.apresentacao = breveDescricao;
    }

    /**
     * @return the dias
     */
    public int getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(int dias) {
        this.dias = dias;
    }

    /**
     * @return the link_video
     */
    public String getLink_video() {
        return link_video;
    }

    /**
     * @param link_video the link_video to set
     */
    public void setLink_video(String link_video) {
        this.link_video = link_video;
    }

    /**
     * @return the dias_restantes
     */
    public long getDias_restantes() {
        return dias_restantes;
    }

    /**
     * @param dias_restantes the dias_restantes to set
     */
    public void setDias_restantes(long dias_restantes) {
        this.dias_restantes = dias_restantes;
    }

    /**
     * @return the levantado
     */
    public int getLevantado() {
        return levantado;
    }

    /**
     * @param levantado the levantado to set
     */
    public void setLevantado(int levantado) {
        this.levantado = levantado;
    }

    /**
     * @return the atingido
     */
    public int getAtingido() {
        return atingido;
    }

    /**
     * @param atingido the atingido to set
     */
    public void setAtingido(int atingido) {
        this.atingido = atingido;
    }

    /**
     * @return the pessoasApoiando
     */
    public int getPessoasApoiando() {
        return pessoasApoiando;
    }

    /**
     * @param pessoasApoiando the pessoasApoiando to set
     */
    public void setPessoasApoiando(int pessoasApoiando) {
        this.pessoasApoiando = pessoasApoiando;
    }

    /**
     * @return the quantidadeDeProjetosQueApoio
     */
    public int getQuantidadeDeProjetosQueApoio() {
        return quantidadeDeProjetosQueApoio;
    }

    /**
     * @param quantidadeDeProjetosQueApoio the quantidadeDeProjetosQueApoio to set
     */
    public void setQuantidadeDeProjetosQueApoio(int quantidadeDeProjetosQueApoio) {
        this.quantidadeDeProjetosQueApoio = quantidadeDeProjetosQueApoio;
    }


       



}







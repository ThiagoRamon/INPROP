/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.view;

import com.inprop.business.PropostaBusiness;
import com.inprop.model.Perfil;
import com.inprop.model.Proposta;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class PropostaView {

    private int status;
    private Perfil perfil;
    private PropostaBusiness propostaBusiness;
    private int id;

    public Proposta getPropostasByPerfilAndStatusE()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.retorna_proposta_extranet_by_perfil_and_statusE(perfil, status);
    }

    public Proposta getPropostasById()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.retorna_proposta_extranet_by_id(getId());
    }

    public List<Proposta> getPropostasEnviadaStatusN()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.retorna_proposta_extranet_by_status(status);
    }



    public int getCountPropost()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.countProposta();


    }


       public int getPropostasByPerfilAndStatus0()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByPerfilAndStatus(perfil,0 );
    }
    public int getPropostasByPerfilAndStatus1()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByPerfilAndStatus(perfil,1 );
    }
    public int getPropostasByPerfilAndStatus2()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByPerfilAndStatus(perfil,2 );
    }
    public int getPropostasByPerfilAndStatus3()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByPerfilAndStatus(perfil,3 );
    }
    public int getPropostasByPerfilAndStatus4()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByPerfilAndStatus(perfil,4 );
    }
    public int getPropostasByPerfilAndStatus5()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByPerfilAndStatus(perfil,5 );
    }
    public int getPropostasByPerfilAndStatus6()throws Exception{
         propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByPerfilAndStatus(perfil,6 );
    }
   



        public int getPropostaNaoLida()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByStatus("0");
    }

    public int getPropostaLida()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByStatus("1");
    }
    public int getPropostaAceitaCadastraProjeto()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByStatus("2");
    }
    public int getPropostaAceitaProjetoEmAndamento()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByStatus("3");
    }

    public int getPropostaBemSucedida()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByStatus("4");
    }
    public int getPropostaMalSucedida()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByStatus("5");
    }

    public int getPropostaRecusada()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.findPropostaByStatus("6");
    }

    public int getPropostasRejeitadasByPerfil()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.totalPropostasRejeitadasByPerfil(perfil);
    }
    public int getPropostasAceitasByPerfil()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.totalPropostasAceitasByPerfil(perfil);
    }
    public int getPropostasNaoLidasBuPerfil()throws Exception{
        propostaBusiness = new PropostaBusiness();
        return propostaBusiness.propostasNaoLidasByPerfil(perfil);
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
    



}

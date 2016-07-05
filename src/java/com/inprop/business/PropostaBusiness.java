/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Perfil;
import com.inprop.model.Proposta;
import com.inprop.persistence.PropostaDao;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class PropostaBusiness {

    public boolean updateStatus(Proposta proposta)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.atualizaStatus(proposta);
    }
    public boolean insert(Proposta proposta)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.insert(proposta);
    }


 public Proposta retorna_proposta_extranet_by_perfil_and_statusE(Perfil perfil, int status)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.retorna_proposta_extranet_by_perfil_and_status(perfil, status);
   }
  

   public Proposta retorna_proposta_extranet_by_id(int id)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.retorna_proposta_extranet_by_id(id);
   }
   public List<Proposta> retorna_proposta_extranet_by_status(int status)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.retorna_proposta_extranet_by_status(status);
   }
   public int countProposta()throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.totalPropostas();
   }

   public int findPropostaByStatus(String status)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.propostasByStatus(status);
   }
   public int findPropostaByPerfilAndStatus(Perfil perfil,int status)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.propostasByPerfilAndStatus(perfil,status);
   }
   public int totalPropostasRejeitadasByPerfil(Perfil perfil)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.totalPropostasRejeitadasByPerfil(perfil);
   }
   public int propostasNaoLidasByPerfil(Perfil perfil)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.quntPropostaNaoLidasByPerfil(perfil);
   }
   public int totalPropostasAceitasByPerfil(Perfil perfil)throws Exception{
        PropostaDao propostaDao = new PropostaDao();
       return  propostaDao.totalPropostasAceitasByPerfil(perfil);
   }

}


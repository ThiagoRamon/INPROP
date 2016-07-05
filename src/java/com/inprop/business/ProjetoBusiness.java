/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.control.RecompensaController;
import com.inprop.model.Perfil;
import com.inprop.model.Projeto;
import com.inprop.model.Proposta;
import com.inprop.persistence.ProjetoDao;
import com.inprop.persistence.RecompensaDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class ProjetoBusiness {
    ProjetoDao projetoDao;
    Projeto    projeto;

    public Projeto insert(Proposta proposta)throws Exception{
        projeto = new Projeto();
        projeto = proposta.getProjeto();
        projetoDao =new ProjetoDao();
        projeto.setProposta(proposta);
        projeto = projetoDao.insert(projeto);
        if( projeto != null ){
            RecompensaBusiness recompensaBusiness = new RecompensaBusiness();
            proposta.getProjeto().setRecompensa(recompensaBusiness.insertListaRecompensa(proposta.getProjeto().getRecompensa(),projeto));
            if(proposta.getProjeto() != null){
               return projeto;
            }
        }

        return null;
    }

    public Projeto retornaProjetoByPerfil(Perfil perfil)throws Exception{
        projetoDao = new ProjetoDao();
        return projetoDao.retornaProjetoByPerfil(perfil.getId(), 3);
    }

    public Projeto retornaProjeto(int id)throws Exception{
        projetoDao = new ProjetoDao();
        return projetoDao.retornaProjetoById(id);
    }

    public List<Projeto> lista()throws Exception{
        projetoDao = new ProjetoDao();
        List<Projeto> resp = projetoDao.mostrarProjetos();
        List<Projeto> newResp = new ArrayList<Projeto>();

        for(Projeto projeto : resp){
            /*System.out.println("Dias restantes : "+projeto.getDias_restantes()+" /  Status : "+projeto.getStatus() +
                               " / levantado : "+projeto.getLevantado() +" / valor total :"+ projeto.getValor_total());
             */
            if((projeto.getDias_restantes() == 0 && projeto.getStatus() == 1 ) && (projeto.getLevantado() >= projeto.getValor_total()) ){

                  projetoDao = new ProjetoDao();
                  projetoDao.updatePorIdDoProjeto(projeto.getId(), 2);
                  projeto.setStatus(2);

            }else if((projeto.getDias_restantes() == 0 && projeto.getStatus() == 1 ) && (projeto.getLevantado()<= projeto.getValor_total())){

                  projetoDao = new ProjetoDao();
                  projetoDao.updatePorIdDoProjeto(projeto.getId(), 3);
                  projeto.setStatus(3);
                  
            }
            newResp.add(projeto);
        }
        return newResp;
    }
}

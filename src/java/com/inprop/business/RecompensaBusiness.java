/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Projeto;
import com.inprop.model.Recompensa;
import com.inprop.persistence.RecompensaDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class RecompensaBusiness {
    Recompensa    recompensa;
    RecompensaDao recompensaDao;

    public List<Recompensa> insertListaRecompensa(List<Recompensa> listaRecompensa, Projeto projeto)throws Exception{
           List<Recompensa> resp = new ArrayList<Recompensa>();
           for(Recompensa rec : listaRecompensa){
               recompensaDao = new RecompensaDao();
               rec.setProjeto(projeto);
               resp.add(recompensaDao.insert(rec));
           }
        return resp;
    }

}

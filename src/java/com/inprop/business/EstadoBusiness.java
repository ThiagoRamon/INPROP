/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.business;

import com.inprop.model.Endereco;
import com.inprop.model.Estado;
import com.inprop.persistence.CidadeDao;
import com.inprop.persistence.EstadoDao;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class EstadoBusiness {
    public boolean insert(Estado estado)throws Exception{
        EstadoDao estadoDao = new EstadoDao();
        // = new Estado();

        return estadoDao.insert(estado);
    }
public static void main(String args[])throws Exception{
//Estado p =new Estado();
//p.setUf("rj");
//EstadoBusiness ed =new EstadoBusiness();
// ed.cadastraEnderecoUsuario(p);

}
    public Estado atualizaEstado(Estado estado)throws Exception{
        EstadoDao           estadoDao = new EstadoDao();
        return estadoDao.atualiza(estado) ;
    }
    public Endereco cadastraEnderecoUsuario(Endereco end)throws Exception{
        EstadoDao           estadoDao = new EstadoDao();
        CidadeBusiness cidadeBusiness = new CidadeBusiness();
        BairroBusiness bairroBusiness = new BairroBusiness();

     //   CidadeDao cidadeDao = new CidadeDao();
        try{
            end.getCep().getBairro().getCidade().setEstado( estadoDao.cadEnderecoDoUsuario(end.getCep().getBairro().getCidade().getEstado() ));
            try{
                if(cidadeBusiness.insertToUser(end) != null ){
                  System.out.println("Cidade Cadastrada  com sucesso ");
                }

            }catch(Exception e){
            }
        }catch(Exception e){

        }

         System.out.println("--<"+end.getCep().getBairro().getCidade().getEstado().getId());
         System.out.println("-->"+ end.getCep().getBairro().getCidade().getEstado().getPais().getNome());

        return end;
    }

    public List<Estado> findAll()throws Exception{
        EstadoDao estadoDao = new EstadoDao();
        return estadoDao.findAll();
    }
}

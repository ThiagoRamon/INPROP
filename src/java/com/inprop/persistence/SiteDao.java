/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Perfil;
import com.inprop.model.Site;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class SiteDao extends Dao {
    private static final String FINDALL_WHERE_ID_PERFIL="select * from site  where id_perfil_fk=?";
    public List<Site> findAllWhereIdPerfil(int id_perfil)throws Exception{
        open();
        stmt = con.prepareStatement(FINDALL_WHERE_ID_PERFIL);
        stmt.setInt(1, id_perfil);
        rs   = stmt.executeQuery();
        List<Site> r = new ArrayList<Site>();
        while(rs.next()){
           Site site = new Site();
           site.setId_site(rs.getInt("id_site"));
           site.setLink(rs.getString("link_site"));
           r.add(site);
        }
        return r;
    }
}

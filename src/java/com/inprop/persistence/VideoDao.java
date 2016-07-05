/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import com.inprop.model.Video;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon
 */
public class VideoDao extends Dao{


    private static String INSERT="insert into video values(null,?,?,?)";
    private static String FINDALL="select * from video";
    private static String FIND_WHERE_ID_PROJETO_FK="select * from video where id_projeto_fk=?";

    public void insert(Object o)throws Exception{
        open();
            Video video = (Video)o;
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, video.getLink());
            stmt.setString(2, video.getData_emissao());
            stmt.setInt(3, video.getProjeto().getId());
            stmt.executeUpdate();
            stmt.close();
        close();
    }

     public Video find_TO_PROJETO(int id_projeto)throws Exception{
        open();
            stmt = con.prepareStatement(FIND_WHERE_ID_PROJETO_FK);
            stmt.setInt(1, id_projeto);
            rs   = stmt.executeQuery();
            Video video = null;
            if(rs.next()){
                 video = new Video(rs.getInt("id_video"),rs.getString("link_video"),rs.getString("data_emissao"));
                return video;
            }
        return video;
    }


    public List<Video> findAll()throws Exception{
        open();
            stmt = con.prepareStatement(FINDALL);
            rs   = stmt.executeQuery();
            List<Video> resp  = new ArrayList<Video>();
            while(rs.next()){
                Video video = new Video(rs.getInt("id_video"),rs.getString("link"),rs.getString("data_emissao"));
            resp.add(video);
            }
            return resp;
    }

}

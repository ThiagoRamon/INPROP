/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.inprop.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
/**
 *
 * @author Ramon
 */
public class Dao {
   Connection con;
   ResultSet rs;
   PreparedStatement stmt;
   protected static Connection conStatic;
   protected static ResultSet rsStatic;
   protected static PreparedStatement stmtStatic;
//
//       private static final String DATABASE_NAME="inprop_db";
//       private static final String URI ="com.mysql.jdbc.Driver";
//       private static final String URL ="jdbc:mysql://localhost:3306/"+DATABASE_NAME;
//       private static final String USER ="root";
//       private static final String PASS ="";

//   private static final String DATABASE_NAME="inprop";
//   private static final String URI ="com.mysql.jdbc.Driver";
//   private static final String URL ="jdbc:mysql://64.15.158.68:3306/"+DATABASE_NAME;
//   private static final String USER ="inprop_root";
//   private static final String PASS ="#inprop#";
////
   private static final String DATABASE_NAME="inprop_db";
   private static final String URI ="com.mysql.jdbc.Driver";
   private static final String URL ="jdbc:mysql://inprop.com.br:3306/"+DATABASE_NAME;
   private static final String USER ="inprop_inprop01";
   private static final String PASS ="thi-amuito";

//
   public void open()throws Exception{
       Class.forName(URI);
       con = DriverManager.getConnection(URL, USER, PASS);
   }
   public void close()throws Exception{
      con.close();
   }
   public static void openStatic()throws Exception{
       Class.forName(URI);
       conStatic = DriverManager.getConnection(URL, USER, PASS);
   }
   public static void closeStatic()throws Exception{
      conStatic.close();
   }
}

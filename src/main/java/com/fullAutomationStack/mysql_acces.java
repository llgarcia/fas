package com.fullAutomationStack;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;

/**
 * Created by lleir on 2/4/18.
 */
public class mysql_acces {

    private static Connection c;
    private static Statement stmt = null;

    public void ini() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
        try {

            if(c == null)
                c = DriverManager.getConnection("jdbc:mysql://localhost/instagram_schema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=p4p4LL0n4");

            stmt = c.createStatement();
        }  catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

//    public void select (String select, String from, String where) {
//        String selectstr = "select "+select+ " ";
//        String fromstr = StringUtils.isNotEmpty(from) ? "from " + from + " ": "";
//        String wherestr = StringUtils.isNotEmpty(where) ? "where " + where + "" : "";
//    }

    public LinkedHashMap<Integer, List<String>> selectFromNotRegistrer() {
        ResultSet rs;
        LinkedHashMap<Integer, List<String>> mapres = new LinkedHashMap<Integer, List<String>>();

        try {
            String sql = "select * from usuario_no_registrado where registrado = 0";
            // System.out.println(sql);
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            rs = null;
        }

        int count = 0;

        try {
            while(rs.next()){

                List<String> tmp = new ArrayList<String>();

                tmp.add(Integer.toString(rs.getInt("idusuario_no_registrado")));
                tmp.add(rs.getString("nombre_completo"));
                tmp.add(rs.getString("usuario"));
                tmp.add(rs.getString("password"));
                tmp.add(rs.getString("email"));

                mapres.put(count, tmp);

                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mapres;
    }

    public LinkedHashMap<Integer, List<String>> selectFromRegistrer() {
        ResultSet rs;
        LinkedHashMap<Integer, List<String>> mapres = new LinkedHashMap<Integer, List<String>>();

        try {
            String sql = "select * from usuario_no_registrado where registrado = 1";
            // System.out.println(sql);
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
            rs = null;
        }

        int count = 0;

        try {
            while(rs.next()){

                List<String> tmp = new ArrayList<String>();

                tmp.add(Integer.toString(rs.getInt("idusuario_no_registrado")));
                tmp.add(rs.getString("nombre_completo"));
                tmp.add(rs.getString("usuario"));
                tmp.add(rs.getString("password"));
                tmp.add(rs.getString("email"));

                mapres.put(count, tmp);

                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mapres;
    }


    public void input(String nombreCompleto, String nombreUsuario, String password, String email) {
        try {
            String sql = "insert into usuario (nombre_completo, usuario, password, email) values ('"+ nombreCompleto +"', '"+ nombreUsuario +"', '"+ password + "', '" + email + "')";
            //System.out.println(sql);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inputUserNotRegistred(String nombreCompleto, String nombreUsuario, String password, String email) {
        try {
            String sql = "insert into usuario_no_registrado (nombre_completo, usuario, password, email, registrado) values ('"+ nombreCompleto +"', '"+ nombreUsuario +"', '"+ password + "', '" + email + "', 0)";
            //System.out.println(sql);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
       // try {
           // c.close();
       // } catch (SQLException e) {
        //    e.printStackTrace();
      //  }
    }

    public void update(int id) {
        try {
            String sql = "update usuario_no_registrado set registrado = 1 where idusuario_no_registrado = "+ id;
            //System.out.println(sql);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromNoRegistrado() {
        try {
            String sql = "delete FROM usuario_no_registrado where registrado = 0";
            //System.out.println(sql);
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

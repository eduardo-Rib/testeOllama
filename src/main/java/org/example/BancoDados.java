package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BancoDados {

    private static Connection con;

    public static void saveToDatabase(RG rg) throws SQLException {
        HashMap<String,String> attributes = rg.toHashMap();
        StringBuilder sql = new StringBuilder("INSERT INTO rg (");
        StringBuilder values = new StringBuilder("VALUES (");

        int count = 0;

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            if (count > 0) {
                sql.append(", ");
                values.append(", ");
            }

            sql.append(entry.getKey());
            values.append("\"");
            values.append(entry.getValue());
            values.append("\"");

            count++;
        }

        sql.append(") ");
        sql.append(values.append(")"));

        try {
            Conexao();
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql.toString());
            stmt.execute();
            stmt.close();
            Desconectar();
        }
        catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    private static void Conexao(){

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apirg","root","eduardo");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void Desconectar() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

}
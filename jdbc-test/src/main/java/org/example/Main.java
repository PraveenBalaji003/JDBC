package org.example;

import java.sql.*;

public class Main{
    public static void main(String[] args) throws Exception{
        Class.forName("org.postgresql.Driver");
        String url="jdbc:postgresql://localhost:5432/demo";
        String user = "postgres";
        String password = "postgres";
        Connection con = DriverManager.getConnection(url, user, password);
        System.out.println("Connection Established...");

        System.out.println("Statement.......................................");
        Statement st = con.createStatement();
        String sql1 = "insert into student values(11,'praveen',26)";
        st.execute(sql1);
        String sql2 = "update student set age ='27' where id =11;";
        st.execute(sql2);
        String sql3 = "delete from student where name='Rahul'";
        st.execute(sql3);
        String sql4 = "select * from student;";
        ResultSet rs = st.executeQuery(sql4);
        while(rs.next()){
            System.out.print(rs.getInt(1)+" - ");
            System.out.print(rs.getString("name")+" - ");
            System.out.println(rs.getInt(3));
        }
        System.out.println("VS");
        System.out.println("Prepared Statement.......................................");
        String sql5 = "insert into student values(?,?,?)";
        int id = 12;
        String name = "naveen";
        int age = 24;
        PreparedStatement pt1 = con.prepareStatement(sql5);
        pt1.setInt(1,id);
        pt1.setString(2,name);
        pt1.setInt(3,age);
        pt1.execute();
        String sql6 = "select * from student";
        PreparedStatement pt2 = con.prepareStatement(sql6);
        rs = pt2.executeQuery();
        while(rs.next()){
            System.out.print(rs.getInt(1)+" - ");
            System.out.print(rs.getString("name")+" - ");
            System.out.println(rs.getInt(3));
        }
        con.close();
        System.out.println("Connection Closed!!!");
    }
}
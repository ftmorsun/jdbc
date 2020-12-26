package org.example;

//1.Step
import java.sql.*;

public class JdbcExecuteQueryDt03 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //2.Step
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //3.Step
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr2", "oracle");

        //4.Step
        Statement st = con.createStatement();
        //1st Example
        //5.Step
        //Remove the students whose grades are 12 from the students table.
        //Print the remaining students on the console
        String sql1 = "DELETE FROM students WHERE std_grade = 12";
        int rs1 = st.executeUpdate(sql1);
        System.out.println(rs1 + " row(s) deleted");

        //6.Step
        String sql2 = "SELECT * FROM students";
        ResultSet rs2 = st.executeQuery(sql2);
        while(rs2.next()) {
            System.out.println(rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getString(3));
        }

        //2nd Example
        //Drop "workers" table
        //Check if the table is dropped or not by using JDBC
        //boolean rs3 = st.execute("DROP TABLE workers");
        //System.out.println(rs3);

        ResultSet rs4 = null;
        String sql3 = "";
        try {
            sql3 = "SELECT * FROM workers";
            rs4 = st.executeQuery(sql3);
            while(rs4.next()) {
                System.out.println(rs4.getString(1) + " " + rs4.getString(2) + " " + rs4.getString(3));
            }
        }catch(SQLException e) {
            System.out.println("No table to delete!");
        }

        //7.Step
        con.close();
        st.close();
        rs2.close();
        rs4.close();

    }

}
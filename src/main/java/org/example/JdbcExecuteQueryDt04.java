package org.example;
import java.sql.*;

public class JdbcExecuteQueryDt04 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr2", "oracle");

        Statement st = con.createStatement();

        //1st Example
        //Select the firstname, lastname, and salary of the employee whose salary is the highest from employees table
        //Print it on the console
        String sql1 = "SELECT first_name, last_name, salary "
                + "FROM employees "
                + "WHERE salary = (SELECT MAX(salary) FROM employees)";
        ResultSet rs1 = st.executeQuery(sql1);
        while(rs1.next()) {
            System.out.println(rs1.getString(1) + " " + rs1.getString(2) + " " + rs1.getInt(3));
        }

        System.out.println("===========================");

        //2nd Example
        //Select the firstname, lastname, and salary of the employees
        //whose salary is the second highest from employees table
        //Print it on the console

        //1.Way:
        String sql2 = "SELECT first_name, last_name, salary "
                + "FROM (SELECT * FROM employees WHERE salary < (SELECT MAX(salary) FROM employees) "
                + "ORDER BY salary DESC) "
                + "FETCH NEXT 3 ROW ONLY";

        ResultSet rs2 = st.executeQuery(sql2);
        while(rs2.next()) {
            System.out.println(rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getInt(3));
        }

        System.out.println("===========================");

        //2.Way:
        String sql3 = "SELECT first_name, last_name, salary \n"
                + "FROM employees\n"
                + "ORDER BY salary DESC\n"
                + "OFFSET 1 ROW\n"
                + "FETCH NEXT 1 ROW ONLY";

        ResultSet rs3 = st.executeQuery(sql2);
        while(rs3.next()) {
            System.out.println(rs3.getString(1) + " " + rs3.getString(2) + " " + rs3.getInt(3));
        }

    }

}
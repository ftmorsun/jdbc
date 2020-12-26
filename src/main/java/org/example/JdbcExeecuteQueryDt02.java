package org.example;
import java.sql.*;



public class JdbcExeecuteQueryDt02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //2.Step:
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //3.Step:
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr2", "oracle");

        //4.Step:
        Statement st = con.createStatement();

        //1st Example
        //5.Step:
        //executeQuery() is used to execute statements that returns tabular data(For example SELECT).
        //When you use executeQuery() method you should create a variable whose data type is ResultSet
        ResultSet rs1 = st.executeQuery("SELECT name FROM workers WHERE salary > 2000");

        //6.Step:
        while(rs1.next()) {
            System.out.println(rs1.getString(1));
        }


        //2nd Example
        //Create a table whose name is students which has std_id, std_name, std_grade columns
        //execute() is used for DDL Statements (CREATE, DROP, TRUNCATE).
       // boolean rs2 = st.execute("CREATE TABLE students(std_id CHAR(5), std_name VARCHAR2(50), std_grade NUMBER(2))");

        //When you print the value of rs2 in the console, you will get false.
        //Because,execute() method could not return any record. Table is empty.
        //System.out.println(rs2);

        //Insert 2 records into the table
        //executeUpdate() is used to execute statements such as "INSERT INTO", "UPDATE SET", "DELETE"(DML Statements).
        //It returns integer value which displays the number of rows which are affected
        //int rs3 = st.executeUpdate("INSERT INTO students VALUES('101', 'Ali Can', 11)");
        //int rs4 = st.executeUpdate("INSERT INTO students VALUES('102', 'Veli Han', 10)");
        //System.out.println(rs4);

        //Insert 3 records into students table by using INSERT ALL
        int rs6 = st.executeUpdate("INSERT ALL INTO students VALUES('103', 'Mary Star', 9) "
                + "INTO students VALUES('104', 'Angie Ocean', 12)"
                + "INTO students VALUES('105', 'Tom Hanks', 7)"
                + "SELECT * FROM DUAL");
        System.out.println(rs6);

        ResultSet rs5= st.executeQuery("SELECT * FROM students");
        while(rs5.next()) {
            System.out.println(rs5.getString(1) + " " + rs5.getString(2) + " " + rs5.getString(3));
        }

        //7.Step:Close Connection, Statement, ResultSet
        con.close();
        st.close();
        rs1.close();
        rs5.close();

    }
}

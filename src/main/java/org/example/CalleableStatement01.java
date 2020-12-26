package org.example;
import java.sql.*;
public class CalleableStatement01 {
    public static void main(String[] args)  throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr2", "oracle");

        Statement st = con.createStatement();
        //Create a table whose name is students which has std_id, std_name, std_grade columns
        //execute() is used for DDL Statements (CREATE, DROP, TRUNCATE).
        // boolean rs5 = st.execute("CREATE TABLE students(std_id CHAR(5), std_name VARCHAR2(50), std_grade NUMBER(2))");

        //When you print the value of rs2 in the console, you will get false.
        //Because,execute() method could not return any record. Table is empty.
        //System.out.println(rs2);
        String sql2 = "SELECT * FROM students";
        ResultSet rs1 = st.executeQuery(sql2);
        //Use PreparedStatement to update grades by using student ids
        String sql1 = "UPDATE students SET std_grade = ? WHERE std_id = ?";

        PreparedStatement pst1 = con.prepareStatement(sql1);

        pst1.setInt(1, 12);
        pst1.setString(2, "102");

        System.out.println(pst1.executeUpdate());



        while (rs1.next()) {
            System.out.println(rs1.getString("std_id") + " - " + rs1.getString("std_name") + " - " + rs1.getInt("std_grade"));

        }



        //Create a function which adds 2 numbers and returns the result, name the function as "addf"
        String sql3 = "CREATE OR REPLACE FUNCTION addf(num1 NUMBER, num2 NUMBER)\n"
                + "RETURN NUMBER IS\n"
                + "BEGIN\n"
                + "    RETURN num1 + num2;\n"
                + "END;";

        st.execute(sql3);
        System.out.println("addf function is created");

        CallableStatement cst1 = con.prepareCall("{? = call addf(?,?)}");

        cst1.registerOutParameter(1, Types.INTEGER);
        cst1.setInt(2, 15);
        cst1.setInt(3, 13);

        cst1.execute();

        System.out.println("The sum is " + cst1.getInt(1));


        int rs3 = st.executeUpdate("INSERT INTO students VALUES('101', 'Ali Can', 11)");
        int rs4 = st.executeUpdate("INSERT INTO students VALUES('102', 'Veli Han', 10)");

        //Create a function which returns the name of a student from students table when you enter student id
        String sql4 = "CREATE OR REPLACE FUNCTION getName(id CHAR)\n"
                + "RETURN VARCHAR IS\n"
                + "s_name students.std_name%TYPE;\n"
                + "BEGIN\n"
                + "    SELECT std_name\n"
                + "    INTO s_name\n"
                + "    FROM students\n"
                + "    WHERE std_id = id;\n"
                + "    RETURN s_name;\n"
                + "END;";
        st.execute(sql4);

        CallableStatement cst2 = con.prepareCall("{? = call getName(?)}");

        cst2.registerOutParameter(1, Types.VARCHAR);
        cst2.setString(2, "101");

        cst2.execute();

        System.out.println(cst2.getString(1));
    }

    }

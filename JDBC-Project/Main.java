import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);

        String sql1 = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";
        String sql2 = "SELECT * FROM students";
        String sql4 = "UPDATE students SET age = ? WHERE id = ?";
        String sql5 = "UPDATE students SET email = ? WHERE name = ?";
        String sql6 = "DELETE FROM students WHERE name = ?";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db",
                "root",
                "root");

        System.out.println("Connection successful");

        boolean run = true;
        while (run) {

            System.out.println("\n1:Insert 2:Show Table 3:Update Age by ID 4:Update Email by Name 5:Delete by Name 6:Exit");

            int option = sc.nextInt();
            sc.nextLine();

            if(option == 1){

                System.out.println("Enter student name:");
                String sname = sc.nextLine();

                System.out.println("Enter student age:");
                int sage = sc.nextInt();
                sc.nextLine();

                System.out.println("Enter student email:");
                String semail = sc.nextLine();

                PreparedStatement st = con.prepareStatement(sql1);
                st.setString(1, sname);
                st.setInt(2, sage);
                st.setString(3, semail);

                int rows = st.executeUpdate();

                if (rows > 0)
                    System.out.println("Row inserted successfully");
                else
                    System.out.println("Insertion failed");

                st.close();
            }else if(option == 2){

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql2);

                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String email = rs.getString("email");

                    System.out.println(id + " | " + name + " | " + age + " | " + email);
                }
                rs.close();
                st.close();
            }else if(option == 3) {

                System.out.println("Enter new age:");
                int newAge = sc.nextInt();

                System.out.println("Enter id:");
                int id = sc.nextInt();
                sc.nextLine();

                PreparedStatement st = con.prepareStatement(sql4);
                st.setInt(1, newAge);
                st.setInt(2, id);

                int rows = st.executeUpdate();

                if (rows > 0)
                    System.out.println("Age updated successfully");
                else
                    System.out.println("No record found");

                st.close();
            }else if(option == 4) {

                System.out.println("Enter new email:");
                String newEmail = sc.nextLine();

                System.out.println("Enter name:");
                String name = sc.nextLine();

                PreparedStatement st = con.prepareStatement(sql5);
                st.setString(1, newEmail);
                st.setString(2, name);

                int rows = st.executeUpdate();

                if (rows > 0)
                    System.out.println("Email updated successfully");
                else
                    System.out.println("No record found");

                st.close();
            }else if(option == 5){

                System.out.println("Enter name:");
                String name = sc.nextLine();

                PreparedStatement st = con.prepareStatement(sql6);
                st.setString(1, name);

                int rows = st.executeUpdate();

                if (rows > 0)
                    System.out.println("Record deleted successfully");
                else
                    System.out.println("No record found");

                st.close();
            }else{
                run = false;
            }
        }

        con.close();
        sc.close();
    }
}

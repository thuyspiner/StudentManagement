package QLSV;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class StudentManagement {
	
	 private List<Student> students;

    public StudentManagement() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void updateStudent(int id, String newName, int newAge) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.name = newName;
                student.age = newAge;
                break;
            }
        }
    }

    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null; 
    }

    public List<Student> getAllStudents() {
        return students;
    }
    public void saveStudentsToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Age: " + student.getAge());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   

       
		

		public void saveStudentsToDatabase(String url, String username, String password) {
			try (Connection connection = DriverManager.getConnection(url, username, password)) {
                String query = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                
                for (Student student : students) {
                    statement.setInt(1, student.getId());
                    statement.setString(2, student.getName());
                    statement.setInt(3, student.getAge());
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
			
		}

	

}


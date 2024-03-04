package QLSV;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

   
        System.out.println("1. Thêm sinh viên");
        System.out.println("2. Sửa thông tin sinh viên");
        System.out.println("3. Xóa sinh viên");
        System.out.println("4. Hiển thị thông tin sinh viên theo ID");
        System.out.println("5. Hiển thị danh sách sinh viên");
        System.out.println("6. Xuất thông tin danh sách");
        System.out.println("7. Lưu danh sách vào database");
        System.out.println("8................Thoát");
        
        int choice;
        do {
            System.out.print("\nChọn chức năng (1-8): ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Nhập Id: ");
                    int id = scanner.nextInt();
                    System.out.print("Nhập tên: ");
                    String name = scanner.next();
                    System.out.print("Nhập tuổi: ");
                    int age = scanner.nextInt();
                    management.addStudent(new Student(id, name, age));
                    System.out.println("Thêm sinh viên thành công.");
                    break;
                case 2:
                    System.out.print("Nhập ID của sinh viên cần sửa: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Nhập tên mới: ");
                    String newName = scanner.next();
                    System.out.print("Nhập tuổi mới: ");
                    int newAge = scanner.nextInt();
                    management.updateStudent(updateId, newName, newAge);
                    System.out.println("Sửa thông tin sinh viên thành công.");
                    break;
                case 3:
                    System.out.print("Nhập ID của sinh viên cần xóa: ");
                    int deleteId = scanner.nextInt();
                    management.deleteStudent(deleteId);
                    System.out.println("Xóa sinh viên thành công.");
                    break;
                case 4:
                    System.out.print("Nhập ID của sinh viên cần hiển thị: ");
                    int searchId = scanner.nextInt();
                    Student student = management.getStudentById(searchId);
                    if (student != null) {
                        System.out.println("Thông tin sinh viên:");
                        System.out.println(student);
                    } else {
                        System.out.println("Không tìm thấy sinh viên có ID: " + searchId);
                    }
                    break;
                case 5:
                    System.out.println("Danh sách sinh viên:");
                    List<Student> allStudents = management.getAllStudents();
                    for (Student s : allStudents) {
                        System.out.println(s);
                    }
                    break;
                case 6:
                    System.out.println("Lưu danh sách sinh viên:");
                    String filename = scanner.next();
                    management.saveStudentsToFile(filename);
                    System.out.println("Đã lưu thành công");
                    break;
                case 7:
                    System.out.println("Lưu danh sách sinh viên vào cơ sở dữ liệu:");
                    String url = "jdbc:mysql://localhost:3306/qlsv";
                    String username = "root";
                    String password = "123456";
                    management.saveStudentsToDatabase(url, username, password);
                    System.out.println("Đã lưu thành công.");
                    break;

                case 8:
                    System.out.println("Thoát.");
                    break;
                default:
                    System.out.println("Chức năng không hợp lệ.");
            }
        } while (choice !=8);

        scanner.close();
    }
}
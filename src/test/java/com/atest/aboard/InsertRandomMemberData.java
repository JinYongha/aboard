package com.atest.aboard;
import java.sql.*;
import java.util.Random;
import java.util.UUID;

public class InsertRandomMemberData {

    private static final String[] GENDERS = {"M", "W"};
    private static final String[] FIRST_NAME = {"김", "이", "박", "최", "정", "조", "강", "임", "한", "윤"};
    private static final String[] SECOND_NAME = {"민", "준", "지", "수", "영", "은", "현", "진", "성", "휘"};
    private static final String[] LAST_NAME = {"호", "태", "욱", "승", "현", "우", "진", "호", "영", "준"};

    private static Random rand = new Random();

    // 데이터베이스 연결 정보
    private static final String URL = "jdbc:mariadb://localhost:3306/bootex";
    private static final String USER = "bootuser";
    private static final String PASSWORD = "qwer1234";

    public static String generateRandomName() {
        String firstName = FIRST_NAME[rand.nextInt(FIRST_NAME.length)];
        String secondName = SECOND_NAME[rand.nextInt(SECOND_NAME.length)];
        String lastName = LAST_NAME[rand.nextInt(LAST_NAME.length)];
        return firstName + secondName + lastName;
    }

    public static String generateRandomId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8); // 랜덤 ID 생성
    }

    public static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            password.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return password.toString();
    }

    public static String generateRandomEmail(String id) {
        return id + "@example.com"; // 간단한 이메일 형식
    }

    public static int generateRandomAge() {
        return rand.nextInt(42) + 19; // 19세에서 60세 사이
    }

    public static String generateRandomContact() {
        return "010-" + (1000 + rand.nextInt(9000)) + "-" + (1000 + rand.nextInt(9000)); // 010-XXXX-XXXX 형식
    }

    public static String generateRandomGender() {
        return GENDERS[rand.nextInt(GENDERS.length)];
    }

    public static void insertMemberData(Connection conn) throws SQLException {
        String sql = "INSERT INTO member (id, name, userpw, email, age, gender, created_at, update_dt, contact) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW(), ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String id = generateRandomId();
            String name = generateRandomName();
            String userpw = generateRandomPassword();
            String email = generateRandomEmail(id);
            int age = generateRandomAge();
            String gender = generateRandomGender();
            String contact = generateRandomContact();

            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, userpw);
            stmt.setString(4, email);
            stmt.setInt(5, age);
            stmt.setString(6, gender);
            stmt.setString(7, contact);

            stmt.executeUpdate();
            System.out.println("Inserted member with ID: " + id);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            for (int i = 0; i < 300; i++) {
                insertMemberData(conn); // 300개 데이터 삽입
            }
            System.out.println("Inserted 300 random members successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

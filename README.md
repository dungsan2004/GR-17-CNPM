# Dung\_s\_Spring\_Boot — Hướng dẫn chạy trên Windows + VSCode

Project: Java Spring Boot (Maven). Tài liệu này hướng dẫn cài đặt công cụ, tải mã nguồn, cấu hình CSDL và chạy project bằng VSCode trên Windows.


## 1\. Yêu cầu môi trường

Cần cài đặt trước:

- Java JDK 17\+ (hoặc version phù hợp trong `pom.xml`)
- Git
- MySQL Server (hoặc MariaDB tương thích)
- VSCode cho Windows
- Maven **không bắt buộc** vì project đã có Maven Wrapper (`mvnw.cmd`)

### 1\.1\. Biến môi trường Java

- Cài JDK xong, thiết lập:
    - `JAVA_HOME` trỏ đến thư mục cài JDK
    - Thêm `%JAVA_HOME%\bin` vào `PATH`
- Kiểm tra:

  ```powershell
  java -version
<hr></hr>

2. Cài đặt extension cần thiết trong VSCode
   Trong VSCode, mở Extensions và cài:
   Extension Pack for Java
   Spring Boot Extension Pack
   Language Support for Java(TM) by Red Hat
   Debugger for Java
   Lombok Annotations Support for VS Code (nếu project dùng Lombok)
   Extension quản lý MySQL / SQL (tuỳ chọn)
<hr></hr>
3. Tải mã nguồn về máy
Mở Terminal (hoặc Git Bash / PowerShell):
git clone https://github.com/dungsan2004/GR-17-CNPM.git
cd GR-17-CNPM/Dung_s_Spring_Boot
<hr></hr>
4. Cấu hình cơ sở dữ liệu (MySQL)
Tạo database mới, ví dụ:
CREATE DATABASE dung_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
Chạy script cnpm.sql tại thư mục gốc project:
mysql -u your_user -p dung_db < cnpm.sql
Mở file cấu hình:
src/main/resources/application.properties
Sửa thông tin kết nối cho phù hợp với máy của bạn:
spring.datasource.url=jdbc:mysql://localhost:3306/dung_db
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
# server.port=8080 (tuỳ chỉnh nếu cần)
Kiểm tra thêm các cấu hình khác nếu có:
jwt.secret
Cấu hình bảo mật / CORS trong config/, security/ (nếu cần chỉnh)
<hr></hr>
5. Mở project trong VSCode
Mở VSCode → File → Open Folder...
Chọn thư mục Dung_s_Spring_Boot
Chờ VSCode:
Tự nhận diện Maven project
Tải về các dependency (xem tiến trình ở OUTPUT hoặc PROBLEMS nếu có lỗi)
<hr></hr>
6. Chạy project bằng VSCode
6.1. Cách 1: Dùng terminal và Maven Wrapper
Trong VSCode, mở terminal:
.\mvnw.cmd spring-boot:run
Đợi đến khi console báo ứng dụng đã start trên port (mặc định 8080).
6.2. Cách 2: Chạy trực tiếp class main
Trong cây thư mục, mở file:
src/main/java/org/example/dung_s_spring_boot/DungSSpringBootApplication.java
Nhấn nút Run ngay bên cạnh hàm main (hoặc dùng Run and Debug → chọn cấu hình Spring Boot).
<hr></hr>
7. Truy cập giao diện và API
Sau khi ứng dụng chạy thành công (mặc định http://localhost:8080):
7.1. Trang giao diện tĩnh
Các file HTML nằm ở src/main/resources/static/:
http://localhost:8080/index.html
http://localhost:8080/main.html
http://localhost:8080/login.html
http://localhost:8080/register.html
http://localhost:8080/diem.html
http://localhost:8080/sinhvien.html
http://localhost:8080/hocphan.html
http://localhost:8080/khoa.html
http://localhost:8080/lop.html
http://localhost:8080/nganh.html
CSS chính: src/main/resources/static/styles/white-bg.css
7.2. API (REST)
Đường dẫn gốc ví dụ:
http://localhost:8080/api/diem
http://localhost:8080/api/sinhvien
http://localhost:8080/api/hocphan
http://localhost:8080/api/khoa
http://localhost:8080/api/lop
http://localhost:8080/api/nganh
http://localhost:8080/api/auth (login / register) nếu có
(Xem chi tiết trong package controller/ và dto/.)
<hr></hr>
8. Build file *.jar và chạy độc lập
8.1. Build (bỏ qua test)
.\mvnw.cmd clean package -DskipTests
Sau khi build xong, file jar sẽ nằm ở thư mục target/, ví dụ:
target/Dung_s_Spring_Boot-<version>.jar
8.2. Chạy jar
java -jar target\Dung_s_Spring_Boot-<version>.jar
<hr></hr>
9. Chạy test
.\mvnw.cmd test
<hr></hr>
10. Debug trong VSCode
Đặt breakpoint trong file Java bất kỳ trong src/main/java/...
Mở Run and Debug (Ctrl+Shift+D)
Chọn cấu hình Java / Spring Boot tương ứng, nhấn Start Debugging
VSCode sẽ dừng tại breakpoint để debug.
<hr></hr>
11. Xử lý một số lỗi thường gặp
11.1. Không kết nối được MySQL
Kiểm tra:
spring.datasource.url, spring.datasource.username, spring.datasource.password
MySQL đã chạy chưa
Database dung_db đã được tạo và đã import cnpm.sql chưa
11.2. Trùng port 8080
Sửa trong src/main/resources/application.properties:
server.port=8081
11.3. VSCode không nhận project Java / báo thiếu JDK
Kiểm tra cài đặt JDK và biến môi trường JAVA_HOME
Mở Command Palette (Ctrl+Shift+P) → Java: Configure Java Runtime
11.4. Lỗi dependency Maven
Trong VSCode, mở terminal:
.\mvnw.cmd clean install
Hoặc dùng lệnh Maven: Reload project từ Command Palette.
<hr></hr>
12. Cấu trúc thư mục quan trọng
Mã nguồn chính:
src/main/java/org/example/dung_s_spring_boot/
File main:
src/main/java/org/example/dung_s_spring_boot/DungSSpringBootApplication.java
Cấu hình ứng dụng:
src/main/resources/application.properties
Giao diện tĩnh (HTML, CSS, ảnh):
src/main/resources/static/
Template (nếu dùng Thymeleaf):
src/main/resources/templates/
Script CSDL:
cnpm.sql
Test:
src/test/java/org/example/dung_s_spring_boot/
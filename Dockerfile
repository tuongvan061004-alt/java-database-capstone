# Step 1: Dùng image Java chính thức để build
FROM openjdk:17-jdk-slim

# Step 2: Tạo thư mục làm việc trong container
WORKDIR /app

# Step 3: Copy file jar của project vào container
# Giả sử bạn đã build xong file jar ở target/
COPY target/smartclinic-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Step 5: Lệnh chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]

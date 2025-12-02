# Real-Chat-App-Backend

A powerful backend system that helps users generate professional resumes using AI.
It is built with Spring Boot and connects to the OpenAI API to create smart, ATS-friendly resume content.
The system stores user information and resumes securely in a MySQL database.

```
# 🏗️ Project Structure

resume-ai-backend/
│── src/main/java/com/ai/resume/builder/
│   │
│   ├── controller/
│   │    ├── ResumeController.java
│   │
│   ├── service/
│   │    ├── ResumeService.java
│   │
│   ├── model/
│   │    ├── Resume.java
│   │
│   ├── repository/
│   │    ├── ResumeRepository.java
│   │
│   ├── dto/
│   │    ├── ResumeRequest.java
│   │
│   ├── exceptions/
│   │    ├── GlobalExceptionHandler.java
│   │
│   ├── ResumeAiBackendApplication.java
│
│── src/main/resources/
│   ├── application.properties
│
│── pom.xml
```

# ⚙️ Tech Stack
Component	     Technology
Backend       Framework	Spring Boot
Security	    Spring Security + JWT
AI Model	    OpenAI GPT-3.5 / GPT-4 API
Database	    MySQL
ORM	Spring    Data JPA
Build Tool	  Maven

▶️ How to Run
1️⃣ Clone the repository
git clone https://github.com/YOUR_USERNAME/AI-Resume-Builder.git
cd resume-ai-backend

2️⃣ Install dependencies
mvn clean install

3️⃣ Run the project
mvn spring-boot:run

4️⃣ Backend starts at:
http://localhost:8080



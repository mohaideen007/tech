# 🏫 School Homework Reminder & Submission System

A full-stack web app to manage school homework and share resources by date and subject.  
Students can view daily assignments and access uploaded files like PDFs and images.

---

## 📌 Features

### 👨‍🏫 Teacher
- Post homework with subject and date.
- Upload resources (PDFs/images) related to the homework.
- Files are stored in the `uploads/` directory; file paths are saved in the database.

### 👨‍🎓 Student
- View homework by selecting a date.
- View and download attached learning resources.

---

## 🧰 Tech Stack

| Layer       | Technology         |
|-------------|--------------------|
| Frontend    | React              |
| Backend     | Spring Boot        |
| Database    | MySQL              |
| Auth        | JWT (Role-based)   |
| File Storage| Local file system (`uploads/` folder) |

---

## 📁 Project Structure

project-root/
├── backend/
│ ├── src/
│ └── uploads/ <-- All uploaded resources are stored here
├── frontend/
│ └── src/
├── screenshots/ <-- Store screenshots for README
└── README.md



---

## 🚀 How to Run the Project

### 1. Backend (Spring Boot)
```bash
cd backend
./mvnw spring-boot:run


Make sure MySQL is running.

Update application.properties with your DB credentials.


### 2. Frontend (React)
bash
Copy
Edit
cd frontend
npm install
npm start


📦 API Endpoints (Sample)
POST /homework/add – Add new homework (teacher only).

GET /homework/get/{date} – Get homework by date.

GET /uploads/{filename} – Serve PDF/image file.


🔐 Authentication
JWT-based login.

Role-based access:

TEACHER: Can post homework and upload files.

STUDENT: Can view homework and download resources.



📸 Screenshots
👨‍🏫 Teacher role
![Teacher Posting Homework](screenshots/submithomework.png)
![Teacher uploading Resources](screenshots/uploadresources.png)

👨‍🎓 Student role
![Student Asking Homework](screenshots/viewhomeworkbydate.png)
![Student Viewing Homework](screenshots/homeworkpage.png)
![Student Getting Resources](screenshots/getresources.png)


🧑‍💻 Author
MOHAIDEEN.S

GitHub: mohaideen007

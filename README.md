# 🎓 Student Resource Hub

A centralized and user-friendly web platform for college students to access **semester exam materials**, **important notes**, and **placement preparation content** — including Java/React cheat sheets, aptitude PDFs, and even YouTube reference videos.  
Built with **Spring Boot**, **PostgreSQL**, and **React.js**, this system streamlines how students and faculty manage academic resources.

---

## 📌 Features

### 👥 Role-Based Access
- 👨‍🎓 **Students**: Browse, search, and download curated study materials
- 👩‍🏫 **Faculty/Admin**: Upload, edit, and manage resources
- 🔐 JWT-based secure authentication

### 📁 Resource Management
- Upload files (PDFs, cheat sheets, question papers, etc.)
- Categorize by semester, subject, or placement topic
- View, filter, search, and paginate resource listings

### 🧠 Smart Search & Filters
- Filter by category, subject, resource type
- Keyword-based search with real-time suggestions
- Pagination support for large datasets

### 💾 File Handling (Development Phase)
- Files are temporarily stored on the local machine (e.g., `/uploads/resources/`)
- Accessed via a secure endpoint (e.g., `/files/{filename}`)
- Cloud migration (AWS S3 / Firebase / Cloudinary) planned for production

### 🖥️ Modern Web UI (React + Material UI)
- Mobile-responsive dashboard
- Smooth upload forms and feedback toasts
- Graceful loading and error states
- “My Uploads” view for faculty

---

## 🛠️ Tech Stack

| Layer         | Technology             |
|---------------|------------------------|
| Frontend      | React.js + Material UI |
| Backend       | Spring Boot (Java)     |
| Database      | PostgreSQL             |
| Auth          | JWT                    |
| Storage       | Local (Dev), Cloud (Prod) |
| API Docs      | Swagger / OpenAPI      |

---

## 📐 Database Schema (Simplified)

- **Users**
  - `user_id`, `username`, `email`, `password`, `role`, `created_at`

- **Resources**
  - `resource_id`, `user_id`, `title`, `description`, `file_url`, `category_id`, `type_id`, `subject`, `created_at`

- **Resource Categories**
  - e.g., Semester 1, Coding, Aptitude

- **Resource Types**
  - e.g., Notes, Cheat Sheets, Interview Qs

---

## 🚀 Getting Started

### Backend Setup
```bash
cd backend
./mvnw spring-boot:run

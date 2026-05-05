# 🦆 PsyDuck Events Hub

PsyDuck Events Hub is a premium, high-fidelity campus event management platform designed to streamline event discovery, registration, and management for university students and administrators.

## 🚀 Key Features

### 👨‍🎓 For Students
- **Immersive Event Browsing**: Discover campus activities with a world-class magazine-style interface.
- **Detailed Timelines**: View granular event schedules with a bold vertical timeline UI.
- **Instant Registration**: Secure spots for high-demand events with a single click.
- **Smart Cancellation**: Opt-out of events seamlessly, automatically reopening spots for others.
- **Localized Context**: Supports Tamil university context (Anna University, MKU, PSG Tech) with professional Tanglish content.

### 🛡️ For Administrators
- **Comprehensive Dashboard**: Track attendance percentages and availability in real-time.
- **Rich Event Editor**: Manage schedules, detailed plans, and organizer contact info.
- **Export Capabilities**: Generate CSV reports of all events for external record-keeping.
- **Status Management**: Intelligent event state handling (Upcoming, Full, Past).

## 🛠️ Technology Stack
- **Backend**: Java 21, Spring Boot 3.2.5
- **Template Engine**: Thymeleaf (HTML5)
- **Database**: H2 In-Memory (JPA/Hibernate)
- **Security**: Spring Security (Role-based access control)
- **Styling**: TailwindCSS via CDN (Custom PsyDuck Design Tokens)
- **Icons**: Google Material Symbols

## 📂 Project Structure
```text
src/main/java/com/stitch/eventhub/
├── config/         # Security & Data Initialization
├── controller/     # Main, Admin, and Auth Controllers
├── entity/         # JPA Entities (Event, User, Registration)
├── repository/     # Spring Data Repositories
└── service/        # Business Logic (Registration, Event Mgmt)

src/main/resources/
├── templates/      # HTML Layouts & Fragments
└── application.properties # Configuration
```

## 🚦 Getting Started
1. **Prerequisites**: Ensure you have JDK 21 installed.
2. **Run the App**: Execute `.\run.bat` in the root directory.
3. **Access**: Navigate to `http://localhost:8080`.

**Default Credentials:**
- **Admin**: `admin` / `admin123`
- **Student**: `student` / `student123`

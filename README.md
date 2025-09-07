# 🧪 Lab App

A modular **Spring Boot** application for experimenting with clean architecture patterns, layered services, and feature-based packaging.  
This project is designed as a **lab playground** for learning and prototyping with modern Java practices.

---

## 📂 Project Structure

```bash
lab-app/
├── pom.xml                  # Maven build configuration
├── README.md                # Project documentation
├── src/
│   ├── main/
│   │   ├── java/com/example/labapp/
│   │   │   ├── LabAppApplication.java       # @SpringBootApplication entrypoint
│   │   │   ├── common/                      # Shared utilities, configs, DTOs, exceptions
│   │   │   │   ├── config/                  # Global configurations (e.g., SecurityConfig.java)
│   │   │   │   ├── dto/                     # Shared DTOs (e.g., BaseResponseDto.java)
│   │   │   │   ├── entity/                  # Shared entities (if cross-feature)
│   │   │   │   ├── exception/               # Global exceptions (e.g., NotFoundException.java)
│   │   │   │   └── util/                    # Helpers (MapperUtil, Constants, etc.)
│   │   │   ├── user/                        # User feature module
│   │   │   │   ├── UserModuleConfig.java    # Feature-specific config (optional)
│   │   │   │   ├── controllers/             # REST endpoints
│   │   │   │   │   ├── read/                # Read-only controllers (GET)
│   │   │   │   │   │   └── UserReadController.java
│   │   │   │   │   └── write/               # Write controllers (POST/PUT/DELETE)
│   │   │   │   │       └── UserWriteController.java
│   │   │   │   ├── dto/                     # User DTOs
│   │   │   │   │   ├── request/             # Input DTOs
│   │   │   │   │   └── response/            # Output DTOs
│   │   │   │   ├── entity/                  # JPA Entities (UserEntity.java)
│   │   │   │   ├── repository/              # Data access
│   │   │   │   │   └── impl/                # Fake implementations
│   │   │   │   └── services/                # Business logic layer
│   │   │   │       ├── facade/              # Orchestrator services
│   │   │   │       ├── read/                # Query services
│   │   │   │       └── write/               # Command services
│   │   │   └── product/                     # Product feature module (mirrors User structure)
│   │   │       ├── controllers/
│   │   │       │   ├── read/
│   │   │       │   │   └── ProductReadController.java
│   │   │       │   └── write/
│   │   │       │       └── ProductWriteController.java
│   │   │       ├── dto/
│   │   │       │   ├── request/
│   │   │       │   └── response/
│   │   │       ├── entity/
│   │   │       ├── repository/
│   │   │       │   └── impl/
│   │   │       └── services/
│   │   │           ├── facade/
│   │   │           ├── read/
│   │   │           └── write/
│   │   └── resources/
│   │       ├── application.properties       # Default configuration
│   │       └── application-lab.properties   # Lab profile with fake repos
│   └── test/
│       ├── java/                            # Unit and integration tests
│       │   └── com/example/labapp/user/services/read/UserReadServiceTest.java
│       └── resources/                       # Test-specific configs
└── target/                                  # Build output (ignored in Git)

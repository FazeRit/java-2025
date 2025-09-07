lab-app/
├── pom.xml                              # Maven build configuration
├── README.md                            # Project documentation
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/labapp/
│   │   │       ├── LabAppApplication.java  # Main class with @SpringBootApplication
│   │   │       ├── common/              # Shared utilities, exceptions, DTOs
│   │   │       │   ├── config/          # Global configurations (e.g., SecurityConfig.java)
│   │   │       │   ├── dto/             # Shared DTOs (e.g., BaseResponseDto.java)
│   │   │       │   ├── entity/          # Shared entities (prefer feature-specific)
│   │   │       │   ├── exception/       # Global exceptions (e.g., NotFoundException.java)
│   │   │       │   └── util/            # Helpers (e.g., MapperUtil.java, Constants.java)
│   │   │       ├── user/                # User feature module
│   │   │       │   ├── UserModuleConfig.java  # Feature-specific @Configuration
│   │   │       │   ├── controllers/      # REST controllers
│   │   │       │   │   ├── read/         # Read-only controllers (GET requests)
│   │   │       │   │   │   └── UserReadController.java
│   │   │       │   │   └── write/        # Write controllers (POST/PUT/DELETE)
│   │   │       │   │       └── UserWriteController.java
│   │   │       │   ├── dto/              # Feature-specific DTOs
│   │   │       │   │   ├── request/      # Input DTOs (e.g., UserCreateRequestDto.java)
│   │   │       │   │   └── response/     # Output DTOs (e.g., UserResponseDto.java)
│   │   │       │   ├── entity/           # Domain models (e.g., UserEntity.java with JPA)
│   │   │       │   ├── repository/       # Data access interfaces
│   │   │       │   │   ├── UserRepository.java
│   │   │       │   │   └── impl/         # Fake implementations for lab
│   │   │       │   │       └── FakeUserRepository.java
│   │   │       │   └── services/         # Business logic
│   │   │       │       ├── facade/       # Orchestrator (e.g., UserFacadeService.java)
│   │   │       │       ├── read/         # Query services (e.g., UserReadService.java, impl/)
│   │   │       │       └── write/        # Command services (e.g., UserWriteService.java, impl/)
│   │   │       └── product/              # Product feature module (mirrored structure)
│   │   │           ├── controllers/
│   │   │           │   ├── read/
│   │   │           │   │   └── ProductReadController.java
│   │   │           │   └── write/
│   │   │           │       └── ProductWriteController.java
│   │   │           ├── dto/
│   │   │           │   ├── request/
│   │   │           │   └── response/
│   │   │           ├── entity/
│   │   │           ├── repository/
│   │   │           │   └── impl/
│   │   │           └── services/
│   │   │               ├── facade/
│   │   │               ├── read/
│   │   │               └── write/
│   │   └── resources/
│   │       ├── application.properties     # Global config with profiles
│   │       └── application-lab.properties # Profile for fake repositories
│   └── test/
│       ├── java/                          # Tests mirror main structure
│       └── resources/                     # Test configurations
└── target/                                # Build output (ignored in Git)

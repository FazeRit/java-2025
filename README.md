lab-app/
├── pom.xml  (Maven build configuration)
├── README.md  (Project documentation)
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/labapp/
│   │   │       ├── LabAppApplication.java  (@SpringBootApplication for component scanning)
│   │   │       ├── common/                (Shared utilities, exceptions, and base DTOs)
│   │   │       │   ├── config/            (Global configurations, e.g., SecurityConfig.java)
│   │   │       │   ├── dto/               (Shared DTOs, e.g., BaseResponseDto.java)
│   │   │       │   ├── entity/            (Shared entities, if cross-feature; prefer per-feature)
│   │   │       │   ├── exception/         (Global exceptions, e.g., NotFoundException.java)
│   │   │       │   └── util/              (Helpers, e.g., MapperUtil.java, Constants.java)
│   │   │       ├── user/                  (User feature module)
│   │   │       │   ├── UserModuleConfig.java  (Optional @Configuration for feature-specific beans)
│   │   │       │   ├── controllers/       (Controllers for HTTP endpoints)
│   │   │       │   │   ├── read/          (Read-only controllers for GET requests)
│   │   │       │   │   │   └── UserReadController.java
│   │   │       │   │   └── write/         (Write controllers for POST/PUT/DELETE)
│   │   │       │   │       └── UserWriteController.java
│   │   │       │   ├── dto/               (Feature-specific DTOs)
│   │   │       │   │   ├── request/       (Input DTOs for API requests, e.g., UserCreateRequestDto.java)
│   │   │       │   │   └── response/      (Output DTOs for API responses, e.g., UserResponseDto.java)
│   │   │       │   ├── entity/            (Domain models, e.g., UserEntity.java with JPA)
│   │   │       │   ├── repository/        (Data access interfaces, e.g., UserRepository.java)
│   │   │       │   │   └── impl/          (Fake implementations, e.g., FakeUserRepository.java)
│   │   │       │   └── services/          (Business logic with facade)
│   │   │       │       ├── facade/        (Orchestrator, e.g., UserFacadeService.java)
│   │   │       │       ├── read/          (Query services, e.g., UserReadService.java, impl/)
│   │   │       │       └── write/         (Command services, e.g., UserWriteService.java, impl/)
│   │   │       └── product/               (Product feature module, mirrored structure)
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
│   │       ├── application.properties     (Global config with profiles)
│   │       └── application-lab.properties (Profile for lab with fake repos)
│   └── test/
│       ├── java/                          (Tests mirror main, e.g., com.example.labapp.user.services.read.UserReadServiceTest.java)
│       └── resources/                     (Test configurations)
└── target/                                (Build output, ignored in Git)

src/main/java/com/example/
├── config/                 # Configuration classes (@Configuration)
├── constants/              # Static constants
├── controllers/            # Controllers for handling HTTP requests (@RestController)
├── docs/                   # Swagger/OpenAPI specifications
├── dto/                    # Data Transfer Objects (DTOs)
│   ├── request/            # Input DTOs for request validation
│   └── response/           # Output DTOs for response serialization
├── entity/                 # Domain models (@Entity)
├── repository/             # Data access layer (@Repository, JpaRepository)
├── services/               # Business logic layer (@Service)
│   ├── facade/             # Facade services for orchestration (optional)
│   ├── read/               # Services for read operations (optional, CQRS-inspired)
│   └── write/              # Services for write operations (optional, CQRS-inspired)
├── factories/              # Factory classes for object creation (optional)
├── strategies/             # Strategy pattern implementations (optional)
├── commands/               # Command pattern implementations for CQRS (optional)
└── Application.java        # Main application class (@SpringBootApplication)

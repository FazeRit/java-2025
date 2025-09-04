src/main/java/com/example/
├── config/                 # Конфігураційні класи (@Configuration)
├── constants/              # Статичні константи
├── controllers/            # Контролери (@RestController)
├── docs/                   # Swagger/OpenAPI специфікації
├── dto/                    # Data Transfer Objects
│   ├── request/            # Вхідні DTO
│   └── response/           # Вихідні DTO
├── entity/                 # Доменні моделі (@Entity)
├── repository/             # Репозиторії (@Repository, JpaRepository)
├── services/               # Сервіси (@Service)
│   ├── facade/             # Фасад-сервіси (опціонально)
│   ├── read/               # Сервіси для читання (опціонально)
│   └── write/              # Сервіси для запису (опціонально)
├── factories/              # Фабрики (опціонально)
├── strategies/             # Стратегії (опціонально)
├── commands/               # Команди для CQRS (опціонально)
└── Application.java        # Головний клас (@SpringBootApplication)

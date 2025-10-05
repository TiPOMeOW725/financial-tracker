# Financial Tracker



A personal finance tracking web application built with Spring Boot and Thymeleaf.



## Features



- Add, edit, and delete transactions

- Manage income and expense categories

- View expense summary by category

- Real-time transaction tracking



## Technologies Used



- **Backend:** Spring Boot 3.x, Spring Data JPA

- **Database:** H2 (Persistent)

- **Frontend:** Thymeleaf, HTML5, CSS

- **Mapping:** MapStruct

- **Build Tool:** Maven



## How to Run



1. Clone the repository:

  ```bash

  git clone https://github.com/TiPOMeOW725/financial-tracker.git

  cd financial-tracker

  ```



2. Run the application:

  ```bash

  mvn spring-boot:run

  ```



3. Open browser and navigate to:

  ```

  http://localhost:8080

  ```



The application will start with pre-loaded sample data.



## Project Structure



```
.
├── data
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       ├── application.properties
│   │       └── data.sql
│   └── test
│       └── java
│           └── com
├── database-dump.sql
├── DESIGN.md
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

```



## Database



Sample data is automatically loaded on startup from `data.sql`.



For complete database schema, see `database-dump.sql`.



## Design Documentation



For detailed architecture and design decisions, see [DESIGN.md](DESIGN.md).


# Spring Boot 3.0 Security with JWT Implementation

<b>Author:</b> <a href="https://github.com/ali-bouali" target="_blank">Ali Bouali</a><br>
<b>Contributors:</b> <a href="https://github.com/spring-boot-nextjs" target="_blank">Jeffrey Spaan</a><br>
<b>Created:</b> 2022-12-29<br>
<b>Last updated:</b> 2024-02-05

This project demonstrates the implementation of security using Spring Boot 3.0 and JSON Web Tokens (JWT). It includes the following features:

## Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security
* Customized access denied handling
* Logout mechanism
* Refresh token

## Technologies
* [![](https://img.shields.io/badge/Spring%20Boot-8A2BE2)]() [![](https://img.shields.io/badge/release-Jan%2022,%202024-blue)]() [![](https://img.shields.io/badge/version-3.2.2-blue)]()
* [![](https://img.shields.io/badge/Spring%20Security-8A2BE2)]() [![](https://img.shields.io/badge/release-Dec%2019,%202023-blue)]() [![](https://img.shields.io/badge/version-6.2.1-blue)]()
* [![](https://img.shields.io/badge/JWT-8A2BE2)]() [![](https://img.shields.io/badge/release-Oct%2015,%202023-blue)]() [![](https://img.shields.io/badge/version-0.12.5-blue)]()
* [![](https://img.shields.io/badge/BCrypt-8A2BE2)]()
* [![](https://img.shields.io/badge/Maven-8A2BE2)]()
 
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* [![](https://img.shields.io/badge/JDK-8A2BE2)]() [![](https://img.shields.io/badge/version-21+-blue)]()
* [![](https://img.shields.io/badge/Maven-8A2BE2)]() [![](https://img.shields.io/badge/version-3.9.6%20and%20higher-blue)]()


### To build and run the project, follow these steps:

Open a new command line.

- Clone the repository:

```bash
git clone https://github.com/ali-bouali/spring-boot-3-jwt-security.git
```

- Navigate to the project directory:

```bash
cd spring-boot-security-jwt
```

- Build the project using Maven:

```bash
mvn clean install
```

- Run the <strong>Spring Boot</strong> application:

```bash
mvn spring-boot:run
```

-> The application will be available at ```http://localhost:8080```

## API Endpoints

To avoid as much manual work as possible, a Postman collection is provided for you to import within your Postman installation.<br>
This file can be found in ```src/main/resources/data/postman/import/collection-import.json```

<i>**NOTE:** Use the tokens, provided to you within the terminal with application startup, to access the secured endpoints:</i>
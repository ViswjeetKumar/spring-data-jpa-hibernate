# 🏆Spring Boot JPA Hibernate Practice

A hands-on Spring Boot project built to **master Spring Data JPA, Hibernate, JPQL, and MySQL**, with a strong focus on **real-world behavior, best practices, and interview readiness**.

This repository demonstrates how Spring Boot applications interact with databases using JPA and Hibernate, how queries are written safely, and how transactional behavior works in tests.

---

## 🎯 Project Objectives

* Understand the **JPA specification vs Hibernate implementation**
* Write **clean, safe, and optimized repository queries**
* Learn **JPQL, derived query methods, and native queries**
* Handle **entity lifecycle events and timestamps**
* Practice **transactional behavior in Spring Boot tests**
* Build a **resume-ready Spring Boot backend project**

---

## 🚵🏻‍♂️ Features Implemented

* Spring Boot 3.x application setup
* Spring Data JPA integration with Hibernate
* MySQL database connectivity
* Entity mapping with constraints and indexes
* JPQL queries using `@Query`
* Derived query methods
* Pagination and sorting support
* Automatic timestamp handling (`createdAt`, `updatedAt`)
* SQL-injection-safe parameter binding
* JUnit tests with `@SpringBootTest`
* Git & GitHub version control

---

## 🏗️ Tech Stack

| Technology      | Usage                     |
| --------------- | ------------------------- |
| Java 17         | Core programming language |
| Spring Boot     | Application framework     |
| Spring Data JPA | Repository abstraction    |
| Hibernate       | ORM implementation        |
| MySQL           | Relational database       |
| Maven           | Dependency management     |
| Lombok          | Boilerplate reduction     |
| JUnit 5         | Testing framework         |
| Git & GitHub    | Version control           |

---

## 🧠 Key Concepts Demonstrated

* Difference between **JPA and Hibernate**
* JPQL vs Native SQL queries
* Repository query derivation
* Transaction rollback behavior in tests
* Why `@Query` uses JPQL by default
* How Spring prevents SQL injection
* Best practices for JPA entities
* Timestamp handling using Hibernate annotations

---

## 📂 Project Structure

```
spring-data-jpa-hibernate
├── src/main/java
│   ├── entity
│   ├── repository
│   └── application
├── src/test/java
│   └── repository tests
├── pom.xml
└── README.md
```

---

## 🧪 Example Repository Query

```java
@Query("select p from ProductEntity p where p.price = :price and p.quantity = :quantity")
Optional<ProductEntity> findByPriceAndQuantity(
        BigDecimal price,
        Integer quantity
);
```

✔ Uses JPQL
✔ SQL injection safe
✔ Clean and readable

---

## ▶️ How to Run the Project

1. Clone the repository

   ```bash
   git clone https://github.com/ViswjeetKumar/spring-data-jpa-hibernate.git
   ```
2. Configure MySQL in `application.properties`
3. Run the Spring Boot application
4. Execute test cases to verify repository behavior

---

## 🧪 Running Tests

```bash
mvn test
```

Tests are written to:

* Validate repository queries
* Observe transaction rollback behavior
* Practice real-world database interaction

---

## 📌 What This Project Shows

* Strong understanding of Spring Data JPA & Hibernate
* Ability to write safe and optimized database queries
* Practical knowledge of transactional testing
* Clean project structure and version control discipline
* Hands-on experience with MySQL integration

---

## 🧠 Why This Project?

> This project was built to deeply understand how Spring Data JPA works internally with Hibernate, how JPQL differs from SQL, and how production-ready repository code is written and tested.

---

## 📬 Author

**Viswjeet Kumar**
GitHub: [https://github.com/ViswjeetKumar](https://github.com/ViswjeetKumar)

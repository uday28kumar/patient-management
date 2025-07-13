# Understanding @(Annotations) in SprintBoot Application
## `@Entity`
The `@Entity` annotation is a core part of the **Java Persistence API (JPA)**. It is used to map a Java class to a database table, enabling **Object-Relational Mapping (ORM)**.

When you annotate a class with @Entity, JPA (e.g., Hibernate) does the following:
- Maps the class to a database table.
- Maps fields to columns.
- Handles object-relational persistence logic like insert, update, delete, and queries.

## `@Repository`
The `@Repository` annotation is a Spring stereotype annotation used to indicate that a class is a Data Access Object (DAO) or a Repository—responsible for encapsulating storage, retrieval, and search behavior of domain objects.
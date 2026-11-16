# Java Assignment: Collections Framework + JDBC

A console-based **Student Management System** that demonstrates:

| Topic | Where |
|-------|-------|
| `ArrayList`, `LinkedList`, `HashSet`, `LinkedHashSet`, `TreeSet`, `HashMap`, `TreeMap`, `Queue`, `PriorityQueue`, `Deque`, `Iterator`, `Collections` utility | `collections/CollectionsDemo.java` |
| `Comparable`, `Comparator`, `equals`/`hashCode` | `model/Student.java`, `Main.java` |
| JDBC: `DriverManager`, `PreparedStatement`, `ResultSet`, try-with-resources, CRUD | `db/DBConnection.java`, `db/StudentDAO.java` |
| Collections + JDBC together (sorting, grouping, top-N) | `Main.java` (option 5) |

## Project structure
```
src/com/assignment/
  Main.java
  model/Student.java
  db/DBConnection.java
  db/StudentDAO.java
  collections/CollectionsDemo.java
sql/schema.sql
db.properties.example
```

## Requirements
- JDK 17 or above
- MySQL Server
- MySQL Connector/J jar (download from mysql.com) placed in the `lib/` folder

## Setup
1. Run `sql/schema.sql` in MySQL (creates `student_db` and sample data).
2. Copy `db.properties.example` to `db.properties` and enter your MySQL username/password.
3. Compile and run (replace the jar name with your version):

**Windows**
```
javac -cp "lib/*" -d out src/com/assignment/*.java src/com/assignment/*/*.java
java -cp "out;lib/*" com.assignment.Main
```
**Linux / macOS**
```
javac -cp "lib/*" -d out src/com/assignment/*.java src/com/assignment/*/*.java
java -cp "out:lib/*" com.assignment.Main
```

## Menu
1. Add student  2. View all  3. Update marks  4. Delete  5. Collections reports  6. Collections demo  0. Exit

## Author
Your Name | Roll No | Class

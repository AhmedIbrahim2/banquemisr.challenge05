"# banquemisr.challenge05" 


Task Management System
This is a Spring Boot-based Task Management System designed for efficient task management with secure, role-based access. 
The system provides email notifications for task updates, JWT-based authentication for secure API access, and history tracking of user actions.
The application has two main roles, Admin and Regular User, each with specific access permissions.

Features
1. User Roles and Authentication
Role-Based Access Control:
Admin: Has access to all tasks and can view the history of actions.
Regular User: Limited access to their own tasks.
JWT Authentication: Secure login for users with a JWT token generated upon successful authentication. The token is included in requests for authentication.
User Registration: New users can register with Admin or Regular User roles.
Role Management: Assign roles to users upon registration.
2. Task Management
Create, Update, and View Tasks: Users can create new tasks, update them, and retrieve task details.
Paginated Task Retrieval: Retrieve tasks with pagination.
Task Search: Search tasks using filters such as title, description, status, and due date.
3. Email Notifications
Task Notifications: When tasks are created or updated, the system sends email notifications to the assigned users, including details like the task title and due date.
Queued Email Dispatch: Notifications are sent asynchronously to reduce the API response time.
4. History Tracking
Action Logging: Every task-related action is logged in the history, which records actions like task creation, updates, and deletions.
Admin-Only Access: Only Admins can view the complete history of actions in the system
5. Exception Handling
Global Exception Handling: The application includes a centralized exception handler to manage validation errors gracefully for both tasks and users. It captures exceptions thrown by the API and returns appropriate HTTP responses.
Validation for Tasks and Users: Specific validations are performed to ensure data integrity. For example, ensuring that required fields are populated and that values meet expected formats.

Quick Start: How to Run the Project on Your Machine
1. Clone the Repository
  
   git clone  https://github.com/AhmedIbrahim2/banquemisr.challenge05.git
cd banquemisr.challenge05
2. Set Up Database and Email
Edit the src/main/resources/application.properties file:

properties
Copy code
# Database setup
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

# Email setup (SMTP)
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your_email@example.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true


3. Build and Run the Application
Run the following commands to build and start the application:

bash
Copy code
mvn clean install
mvn spring-boot:run

4. Access the Application
API Base URL: http://localhost:8080
Swagger UI: Open http://localhost:8080/swagger-ui.html to view API documentation and test endpoints.

5. Using the APIs
Register: POST request to /api/v1/auth/register
Login: POST request to /api/v1/auth/login (returns a JWT token to use in subsequent requests)
Access Protected APIs: Use the JWT token in the Authorization header as Bearer <token>

6. Email Notifications
The system sends email notifications for task-related events like task creation and approaching deadlines. Make sure your SMTP email settings are correctly configured to enable this feature.



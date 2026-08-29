# WEB TECHNOLOGY - UNIT IV (REPRESENTING WEB DATA)
## ASSESSMENT II: INDUSTRY PROBLEM SOLVING TASK
**Course Outcome**: CO4 &bull; Web Data Representation, JSP, and Java MVC Architecture  
**Scenario**: IT Service Request Management System  

---

## 📑 Assessment Overview & Rubric Solutions

### Question 1: JSP View Form (`serviceRequest.jsp`) &ndash; 7 Marks
#### Requirements:
Create a JSP page named `serviceRequest.jsp` to collect employee IT complaints with the following fields:
- **Employee ID** (`employeeId`) &ndash; text field
- **Employee Name** (`employeeName`) &ndash; text field
- **Department** (`department`) &ndash; dropdown selection
- **Problem Category** (`problemCategory`) &ndash; dropdown (Network, Software, Hardware, Account, Other)
- **Problem Description** (`problemDescription`) &ndash; multi-line textarea
- **Priority Level** (`priority`) &ndash; radio buttons (Low, Medium, High)
- **Submit Action** &ndash; HTTP POST to `ServiceRequestServlet`

---

### Question 2: Model Class (`ServiceRequest.java`) &ndash; 5 Marks
#### Requirements:
Create a pure Java Bean Model in package `com.it.model`:
- **Private Fields**: `employeeId`, `employeeName`, `department`, `problemCategory`, `problemDescription`, `priority`.
- **Constructors**: Default no-arg constructor and fully parameterized constructor.
- **Methods**: Public getters and setters for all private fields.
- **Independence**: Free of presentation logic and `javax.servlet` dependencies.

```java
package com.it.model;

import java.io.Serializable;

public class ServiceRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String employeeId;
    private String employeeName;
    private String department;
    private String problemCategory;
    private String problemDescription;
    private String priority;

    public ServiceRequest() {}

    public ServiceRequest(String employeeId, String employeeName, String department, 
                          String problemCategory, String problemDescription, String priority) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.department = department;
        this.problemCategory = problemCategory;
        this.problemDescription = problemDescription;
        this.priority = priority;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getProblemCategory() { return problemCategory; }
    public void setProblemCategory(String problemCategory) { this.problemCategory = problemCategory; }

    public String getProblemDescription() { return problemDescription; }
    public void setProblemDescription(String problemDescription) { this.problemDescription = problemDescription; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}
```

---

### Question 3: Controller Servlet (`ServiceRequestServlet.java`) &ndash; 10 Marks
#### Requirements:
Create an `HttpServlet` controller in package `com.it.controller`:
- **URL Mapping**: `/ServiceRequestServlet` and `/submitRequest`
- **Method**: `doPost()` to process form submissions.
- **Validation**: Checks for non-empty required fields; preserves user input if validation fails.
- **Model Instantiation**: Creates `ServiceRequest` object.
- **Ticket ID Generation**: Generates unique tracking ID (e.g. `SR-1001`).
- **Forwarding**: Binds attributes and forwards to `acknowledgement.jsp` using `RequestDispatcher`.

---

### Question 4: Acknowledgement View & MVC Explanation (`acknowledgement.jsp`) &ndash; 8 Marks
#### Requirements:
Create `acknowledgement.jsp` to:
- Extract `serviceRequest` Model and `requestNumber` attributes.
- Render formatted confirmation table with status banners and priority styling.
- Provide comprehensive MVC architecture explanation:
  - **Model**: `ServiceRequest.java` maintains state and entity data.
  - **View**: `serviceRequest.jsp` (input) and `acknowledgement.jsp` (output).
  - **Controller**: `ServiceRequestServlet.java` manages business control flow and lifecycle.

---

## 🚀 How to Run & Verify

1. **Direct Browser Interactive Mode**: Open [`index.html`](index.html) in any web browser.
2. **Apache Tomcat Deployment**:
   - Build WAR: `mvn clean package`
   - Copy `target/co4-it-service-request.war` into Tomcat `webapps/`
   - Access: `http://localhost:8080/co4-it-service-request/serviceRequest.jsp`

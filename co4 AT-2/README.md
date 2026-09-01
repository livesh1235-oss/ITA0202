# WEB TECHNOLOGY - UNIT IV (REPRESENTING WEB DATA)
## ASSESSMENT II: INDUSTRY PROBLEM SOLVING TASK
**Scenario:** IT Service Request Management System  
**Architecture:** Model-View-Controller (MVC) using JSP, Servlet, and Java Model  

---

## 📸 Application Screenshots

### 1. Input Form View (`serviceRequest.jsp`)
![Service Request Form](screenshots/serviceRequest_form.png)

### 2. Result View & MVC Breakdown (`acknowledgement.jsp`)
![Acknowledgement & MVC Flow](screenshots/acknowledgement_result.png)

---

## 📁 Directory Structure
```
co4 AT-2/
├── pom.xml
├── README.md
├── screenshots/
│   ├── serviceRequest_form.png
│   └── acknowledgement_result.png
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── it/
        │           ├── model/
        │           │   └── ServiceRequest.java          (Question 2: Model)
        │           └── controller/
        │               └── ServiceRequestServlet.java   (Question 3: Controller)
        └── webapp/
            ├── serviceRequest.jsp                       (Question 1: Input View)
            ├── acknowledgement.jsp                      (Question 4: Result View)
            └── WEB-INF/
                └── web.xml                              (Deployment Descriptor)
```

---

## 🏗️ MVC Component Mapping

| MVC Component | Application Component | Responsibility |
|---|---|---|
| **Model** | `ServiceRequest.java` | Encapsulates the request entity (employeeId, employeeName, department, problemCategory, problemDescription, priority) with getters & setters. No presentation code. |
| **View (Input)** | `serviceRequest.jsp` | Collects employee technical complaint details via an intuitive HTML form and sends a `POST` request. |
| **Controller** | `ServiceRequestServlet.java` | Intercepts `POST`, extracts and validates parameters, instantiates `ServiceRequest`, generates `SR-1001` ID, sets request attributes, and forwards. |
| **View (Result)** | `acknowledgement.jsp` | Extracts Model and Request ID attributes to display formatted ticket acknowledgement and provides an overview of the MVC architecture. |

---

## 🔄 Request Lifecycle & Flow

```
[Employee Browser]
       │
       ▼ (1. Form Input & Submit)
[serviceRequest.jsp]
       │
       ▼ (2. HTTP POST)
[ServiceRequestServlet]
  ├── (3. request.getParameter())
  ├── (4. Validate mandatory fields)
  ├── (5. new ServiceRequest(...))
  ├── (6. Generate Request ID: "SR-1001")
  ├── (7. request.setAttribute("serviceRequest", model))
  └── (8. RequestDispatcher.forward())
       │
       ▼ (9. Forward Request)
[acknowledgement.jsp]
       │
       ▼ (10. Render Output)
[Employee Confirmation View]
```

---

## 📋 Evaluation Rubric Checklist

- [x] **Question 1 (7 Marks): JSP View (`serviceRequest.jsp`)**
  - Employee ID, Employee Name, Department, Problem Category (Network, Software, Hardware, Account, Other), Problem Description (textarea), Priority (Low, Medium, High).
  - Proper form labels, submit button, and POST method.
- [x] **Question 2 (5 Marks): Model (`ServiceRequest.java`)**
  - All requested fields, parameterized and no-arg constructors, getters/setters, pure Java bean.
- [x] **Question 3 (10 Marks): Controller (`ServiceRequestServlet.java`)**
  - `doPost()` implementation, server-side validation, thread-safe local variables, request number generation (`SR-1001`), request attributes, forward to `acknowledgement.jsp`.
- [x] **Question 4 (8 Marks): Acknowledgement & MVC Explanation (`acknowledgement.jsp`)**
  - Display all submitted fields, confirmation message, MVC component identification, and full architectural flow breakdown.

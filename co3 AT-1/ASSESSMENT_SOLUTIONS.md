# CO3 – Assessment 1 – Experiments
## Complete Technical Documentation & Solutions

**Course Outcome**: CO3 &bull; Client-Side DOM & Server-Side Java Servlets  
**Assessment**: Assessment 1 &bull; Laboratory Experiments  

---

## 📑 Assessment Overview

This document provides the complete, faculty-grade implementation documentation and answers for **CO3 Assessment 1**.

---

### Experiment Question 1 – DOM-Based Interactive Student Registration Preview

#### Problem Statement:
A department wants a simple student registration webpage where students can enter their **Name**, **Register Number**, **Department**, and **Year of Study**. Before submitting the details, the webpage should immediately display the entered information as a formatted student profile without reloading the page. The user should also be able to remove the displayed profile using a button. The application must use DOM methods and suitable JavaScript events to perform these operations dynamically.

#### Task Requirements & Concepts Assessed:
- **DOM Selection**: Read form inputs using `document.getElementById()` or `document.querySelector()`.
- **Node Generation**: Generate dynamic elements using `document.createElement()` and `textContent`.
- **Event Handling**: Use `addEventListener()` to handle both **Display** and **Remove** actions.
- **Styling**: Use `classList` or `style` to visually distinguish the generated profile card.
- **Element Removal**: Remove the generated profile element cleanly using `.remove()`.

#### Solution Architecture (`q1_dom_profile_preview/`):
- **HTML Form**: Input fields for Name (`#studentName`), Register Number (`#regNumber`), Department (`#department`), and Year of Study (`#yearOfStudy`).
- **JavaScript Core Logic**:
  ```javascript
  // 1. Reading Form Values
  const name = document.getElementById('studentName').value.trim();
  const regNo = document.querySelector('#regNumber').value.trim();
  const dept = document.getElementById('department').value;
  const year = document.querySelector('#yearOfStudy').value;

  // 2. Generating DOM Nodes
  const card = document.createElement('div');
  card.classList.add('student-profile-card'); // Visually distinguish via classList

  const btnRemove = document.createElement('button');
  btnRemove.classList.add('btn-remove-profile');
  btnRemove.textContent = '✕ Remove Profile';

  // 3. Handling Remove Action via addEventListener
  btnRemove.addEventListener('click', function() {
      card.remove(); // Removes profile from live DOM
  });

  // 4. Handling Display Action via addEventListener
  btnDisplayProfile.addEventListener('click', displayStudentProfile);
  ```

---

### Experiment Question 2 – Servlet-Based Student Result Processing

#### Problem Statement:
A faculty member wants a server-side application that accepts a student's **Name**, **Register Number**, and **marks in three subjects** through an HTML form. The submitted data must be processed by a Java Servlet, which should calculate the **Total**, **Average**, **Highest Mark**, and **Pass/Fail status** and generate the result dynamically in the browser. The servlet must validate missing values and marks outside the range of 0 to 100. Request-specific student data must be maintained using local variables so that the servlet remains safe when multiple requests are processed.

#### Task Requirements & Concepts Assessed:
- **Architecture**: `HttpServlet`, HTML form using `POST` method, Servlet using `doPost()`.
- **Parameter Handling**: `request.getParameter()`.
- **Validation**:
  - Missing or empty Name/RegNo.
  - Missing or non-numeric marks for Subjects 1, 2, and 3.
  - Out of range marks (`< 0` or `> 100`).
- **Calculations**:
  - $\text{Total} = \text{mark}_1 + \text{mark}_2 + \text{mark}_3$ (out of 300)
  - $\text{Average} = \frac{\text{Total}}{3.0}$
  - $\text{Highest Mark} = \max(\text{mark}_1, \text{mark}_2, \text{mark}_3)$
  - $\text{Pass/Fail Status} = (\text{mark}_1 \ge 50 \land \text{mark}_2 \ge 50 \land \text{mark}_3 \ge 50) \implies \text{PASS else FAIL}$.
- **Concurrency Awareness**: All variables are declared as **local variables** within the `doPost()` method scope, guaranteeing thread isolation on the thread execution stack during concurrent container processing.
- **Dynamic Presentation**: Formatted response generated using `PrintWriter`.

#### Solution Architecture (`q2_servlet_result_processing/`):
- **Servlet Class**: `com.co3.assessment.StudentResultServlet`
- **Endpoints**: `/processResult` and `/result`
- **Core Servlet Processing Snippet**:
  ```java
  @WebServlet(name = "StudentResultServlet", urlPatterns = {"/processResult", "/result"})
  public class StudentResultServlet extends HttpServlet {
      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response) 
              throws ServletException, IOException {
          
          request.setCharacterEncoding("UTF-8");
          response.setContentType("text/html;charset=UTF-8");

          // Local Stack Variables (Thread-Safe Concurrency)
          String name = request.getParameter("studentName");
          String regNo = request.getParameter("regNumber");
          
          // Parse & validate marks (0 - 100)
          double mark1 = Double.parseDouble(request.getParameter("mark1"));
          double mark2 = Double.parseDouble(request.getParameter("mark2"));
          double mark3 = Double.parseDouble(request.getParameter("mark3"));

          // Calculate Metrics
          double total = mark1 + mark2 + mark3;
          double average = total / 3.0;
          double highest = Math.max(mark1, Math.max(mark2, mark3));
          boolean isPassed = (mark1 >= 50 && mark2 >= 50 && mark3 >= 50);

          try (PrintWriter out = response.getWriter()) {
              // Dynamically generate formatted HTML result table
          }
      }
  }
  ```

---

## 🚀 How to Run & Verify

1. **Question 1 (DOM Preview)**: Open [`q1_dom_profile_preview/index.html`](q1_dom_profile_preview/index.html) in your browser.
2. **Question 2 (Servlet Result)**:
   - Form UI: [`q2_servlet_result_processing/webapp/result_form.html`](q2_servlet_result_processing/webapp/result_form.html).
   - Build WAR: `mvn clean package` inside `q2_servlet_result_processing/`.
   - Deploy to Tomcat: Copy `target/co3-result-servlet.war` into Tomcat `webapps/` folder.

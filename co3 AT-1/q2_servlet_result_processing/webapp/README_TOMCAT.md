# Apache Tomcat Deployment Guide for CO3 Assessment 1 (Question 2)

This directory contains the Java Servlet implementation for **CO3 Assessment 1 &ndash; Question 2 (Student Result Processing)**.

---

## 📂 Servlet Details & Endpoints

| Detail | Specification |
| :--- | :--- |
| **Servlet Class** | `com.co3.assessment.StudentResultServlet` |
| **HTTP Method** | `POST` (`doPost()`) |
| **URL Mapping** | `/processResult` and `/result` |
| **HTML Form** | [`webapp/result_form.html`](result_form.html) |
| **Calculations** | Total (out of 300), Average (%), Highest Mark, Pass/Fail status |
| **Validation** | Missing inputs, non-numeric values, out of range (< 0 or > 100) |
| **Concurrency Safety** | All calculations performed using **local variables on thread stack** |

---

## 🚀 How to Build & Deploy to Apache Tomcat

### Step 1: Package as WAR using Maven
```bash
cd "/Users/m.s.liveshvetrivel/Desktop/co3 AT-1/q2_servlet_result_processing"
mvn clean package
```
*This produces `target/co3-result-servlet.war`.*

### Step 2: Deploy to Tomcat
```bash
cp target/co3-result-servlet.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh
```

### Step 3: Access the Application
* **HTML Form URL**: `http://localhost:8080/co3-result-servlet/result_form.html`
* **Servlet Endpoint**: `http://localhost:8080/co3-result-servlet/processResult`

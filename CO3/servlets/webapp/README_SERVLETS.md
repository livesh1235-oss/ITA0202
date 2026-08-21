# Apache Tomcat Deployment Guide for Experiments 6 to 10

This directory contains the Java Servlet implementations for Experiments 6 through 10.

---

## 📂 Servlet Structure & URL Mappings

| Exp No | Servlet Class | URL Pattern | Associated Form | Description |
| :--- | :--- | :--- | :--- | :--- |
| **Exp 6** | `Exp6DynamicContentServlet` | `/exp6` or `/welcome` | - | Dynamic content, student details & timestamp |
| **Exp 7** | `Exp7RegistrationServlet` | `/exp7` | `exp7_registration_form.html` | Form validation & POST acknowledgement |
| **Exp 8** | `Exp8ResultProcessingServlet` | `/exp8` | `exp8_result_form.html` | 5 subject marks processing, grade & pass/fail |
| **Exp 9** | `Exp9LifecycleServlet` | `/exp9` or `/lifecycle` | - | Lifecycle method execution monitor |
| **Exp 10** | `Exp10VisitorCounterServlet` | `/exp10` or `/counter` | - | Unsafe vs Thread-Safe Atomic counter |

---

## 🚀 How to Build & Deploy to Apache Tomcat

### Option A: Standard Apache Tomcat Deployment (WAR File)

1. **Build the WAR package** using Maven:
   ```bash
   cd /Users/m.s.liveshvetrivel/.gemini/antigravity/scratch/co3/servlets
   mvn clean package
   ```
   *This generates `target/co3-servlets.war`.*

2. **Deploy to Tomcat**:
   - Copy `target/co3-servlets.war` into your Tomcat `webapps/` folder:
     ```bash
     cp target/co3-servlets.war $CATALINA_HOME/webapps/
     ```
   - Start Tomcat:
     ```bash
     $CATALINA_HOME/bin/startup.sh    # On macOS / Linux
     ```

3. **Access the Servlets in your browser**:
   - Central Servlet Context: `http://localhost:8080/co3-servlets/`
   - **Exp 6**: `http://localhost:8080/co3-servlets/exp6`
   - **Exp 7 Form**: `http://localhost:8080/co3-servlets/exp7_registration_form.html`
   - **Exp 8 Form**: `http://localhost:8080/co3-servlets/exp8_result_form.html`
   - **Exp 9**: `http://localhost:8080/co3-servlets/exp9`
   - **Exp 10**: `http://localhost:8080/co3-servlets/exp10`

---

### Option B: Compiling with `javac` directly

If compiling manually without Maven:
```bash
cd /Users/m.s.liveshvetrivel/.gemini/antigravity/scratch/co3/servlets
mkdir -p WEB-INF/classes
javac -cp "$CATALINA_HOME/lib/servlet-api.jar" -d WEB-INF/classes src/main/java/com/co3/servlets/*.java
```

# Course Outcome 3 (CO3) Laboratory Manual & Codebase

This repository contains the complete implementation of **Experiments 1 through 10** for Course Outcome 3 (CO3), covering **Client-Side DOM & JavaScript Programming** (Experiments 1–5) and **Server-Side Java Servlets on Apache Tomcat** (Experiments 6–10).

---

## 📑 Table of Contents

| Exp No | Experiment Title | Tech / Key APIs | File Location |
| :--- | :--- | :--- | :--- |
| **01** | [DOM-Based Student Profile Manipulation](#experiment-1-dom-based-student-profile-manipulation) | `getElementById()`, `querySelector()`, `textContent`, `style`, `classList`, `setAttribute()` | [`exp1_dom_profile/`](exp1_dom_profile/) |
| **02** | [Dynamic Student Registration List Using DOM](#experiment-2-dynamic-student-registration-list-using-dom) | `createElement()`, `appendChild()`, `remove()`, `parentElement`, `children` | [`exp2_dynamic_registration/`](exp2_dynamic_registration/) |
| **03** | [Interactive Event Registration Using JS Events](#experiment-3-interactive-event-registration-using-javascript-events) | `onclick`, `change`, `input`, `focus`, `blur`, `mouseover`, `mouseout`, `submit`, `addEventListener()` | [`exp3_event_registration/`](exp3_event_registration/) |
| **04** | [Browser Information Dashboard Using Window Object](#experiment-4-browser-information-dashboard-using-window-object) | `window.location`, `window.innerWidth/innerHeight`, `window.scrollX/scrollY`, `navigator`, `screen`, `resize` | [`exp4_browser_dashboard/`](exp4_browser_dashboard/) |
| **05** | [Cross-Browser Compatible Interactive Webpage](#experiment-5-cross-browser-compatible-interactive-webpage) | Feature Detection, `localStorage` Fallback, `querySelector()`, CSS `@supports` | [`exp5_cross_browser/`](exp5_cross_browser/) |
| **06** | [Basic Java Servlet for Dynamic Content Generation](#experiment-6-basic-java-servlet-for-dynamic-content-generation) | `HttpServlet`, `doGet()`, `HttpServletRequest`, `HttpServletResponse`, `PrintWriter`, Servlet Mapping | [`servlets/.../Exp6DynamicContentServlet.java`](servlets/src/main/java/com/co3/servlets/Exp6DynamicContentServlet.java) |
| **07** | [Student Registration Form Processing Using Servlet](#experiment-7-student-registration-form-processing-using-servlet) | HTML Form, `doPost()`, `request.getParameter()`, Server-side validation, Table receipt | [`servlets/.../Exp7RegistrationServlet.java`](servlets/src/main/java/com/co3/servlets/Exp7RegistrationServlet.java) |
| **08** | [Online Student Result Processing Using Servlet](#experiment-8-online-student-result-processing-using-servlet) | 5 Subject marks processing, Total, Average, Highest/Lowest, Grade, Pass/Fail | [`servlets/.../Exp8ResultProcessingServlet.java`](servlets/src/main/java/com/co3/servlets/Exp8ResultProcessingServlet.java) |
| **09** | [Servlet Lifecycle Demonstration](#experiment-9-servlet-lifecycle-demonstration) | `Constructor`, `init(ServletConfig)`, `service()`, `doGet()`, `destroy()`, Count tracking | [`servlets/.../Exp9LifecycleServlet.java`](servlets/src/main/java/com/co3/servlets/Exp9LifecycleServlet.java) |
| **10** | [Thread-Safe Concurrent Visitor Counter Using Servlet](#experiment-10-thread-safe-concurrent-visitor-counter-using-servlet) | Multi-threading, `AtomicInteger`, `synchronized` blocks, Race condition analysis | [`servlets/.../Exp10VisitorCounterServlet.java`](servlets/src/main/java/com/co3/servlets/Exp10VisitorCounterServlet.java) |

---

## 🎯 Experiment Details & Execution

### Experiment 1: DOM-Based Student Profile Manipulation
- **Aim**: Develop a webpage to display and modify student profile details using standard DOM methods.
- **Key Concepts**:
  - `document.getElementById('profileCard')` & `document.querySelector('#studentHeading')`
  - Modifying text with `textContent`
  - Inline style changes via `style.color` and `style.backgroundColor`
  - Class toggling using `classList.toggle('dark-theme')`
  - Attribute updates via `setAttribute('src', url)` and `setAttribute('data-status', 'active')`
  - Visibility toggling with `style.display` (`block` vs `none`).
- **How to Run**: Open `exp1_dom_profile/index.html` in any web browser.

---

### Experiment 2: Dynamic Student Registration List Using DOM
- **Aim**: Create a dynamic student registration interface that allows adding, listing, and deleting student records using DOM tree traversal and node creation.
- **Key Concepts**:
  - `document.createElement('tr')` and `document.createElement('td')`
  - `node.appendChild()` to append rows to `<tbody>`
  - `node.parentElement` to traverse from the delete button to the enclosing row
  - `targetRow.remove()` to detach nodes from the live DOM
  - `tbody.children` to iterate through all active records, compute department counts, and clear all.
- **How to Run**: Open `exp2_dynamic_registration/index.html` in any web browser.

---

### Experiment 3: Interactive Event Registration Using JavaScript Events
- **Aim**: Develop an event-registration webpage comparing intrinsic HTML attribute event handling (`onclick="..."`) with modern DOM Level 2 event listeners (`addEventListener`).
- **Events Demonstrated**:
  - `onclick` - Button clicks & card selections
  - `input` - Real-time keystroke tracking
  - `focus` & `blur` - Form field focus state indicators
  - `change` - Dropdown and radio button changes
  - `mouseover` & `mouseout` - Hover animations and telemetry
  - `submit` - Form validation & submission with `e.preventDefault()`.
- **How to Run**: Open `exp3_event_registration/index.html` in any web browser.

---

### Experiment 4: Browser Information Dashboard Using Window Object
- **Aim**: Create a live dashboard displaying client and browser runtime metrics using the `window` and `navigator` objects.
- **Properties & APIs Used**:
  - `window.location` (`href`, `protocol`, `host`, `pathname`, `origin`)
  - `window.screen` (`width`, `height`, `availWidth`, `availHeight`, `colorDepth`)
  - `window.innerWidth` & `window.innerHeight` (Viewport tracking on `resize`)
  - `window.scrollX` & `window.scrollY` (Coordinate tracking on `scroll`)
  - `navigator` (`language`, `languages`, `cookieEnabled`, `hardwareConcurrency`, `onLine`).
- **How to Run**: Open `exp4_browser_dashboard/index.html` in any web browser.

---

### Experiment 5: Cross-Browser Compatible Interactive Webpage
- **Aim**: Implement robust feature detection, graceful degradation, and polyfill fallbacks for unsupported browser capabilities.
- **Fallbacks Implemented**:
  1. **LocalStorage**: Test-write detection with fallback to in-memory cookie storage wrapper.
  2. **querySelector**: Safe query wrapper with fallback to `getElementById` / `getElementsByTagName`.
  3. **CSS `@supports`**: Progressive enhancement with frosted glass backdrop-filter and fallback to solid border cards.
  4. **Fetch API**: Modern `fetch` with fallback to `XMLHttpRequest`.
- **How to Run**: Open `exp5_cross_browser/index.html` in any web browser.

---

### Experiment 6: Basic Java Servlet for Dynamic Content Generation
- **Aim**: Create an `HttpServlet` that dynamically renders a welcome message, student details, course title, and live server timestamp.
- **Servlet Class**: `com.co3.servlets.Exp6DynamicContentServlet`
- **URL Endpoint**: `/exp6` or `/welcome`
- **Key Concepts**: `doGet()`, `HttpServletRequest`, `HttpServletResponse`, `response.setContentType()`, `PrintWriter`, `LocalDateTime`.

---

### Experiment 7: Student Registration Form Processing Using Servlet
- **Aim**: Design an HTML student-registration form and process submissions via `doPost()` with input validation and acknowledgement receipt formatting.
- **Servlet Class**: `com.co3.servlets.Exp7RegistrationServlet`
- **HTML Form**: `servlets/webapp/exp7_registration_form.html`
- **URL Endpoint**: `/exp7`
- **Validation**: Empty check, null check, regex email format validation.

---

### Experiment 8: Online Student Result Processing Using Servlet
- **Aim**: Develop a servlet-based academic result processing system that accepts 5 subject marks and generates an analytical report table.
- **Servlet Class**: `com.co3.servlets.Exp8ResultProcessingServlet`
- **HTML Form**: `servlets/webapp/exp8_result_form.html`
- **URL Endpoint**: `/exp8`
- **Calculations**:
  - Total Score (out of 500)
  - Percentage Average
  - Highest & Lowest marks
  - Letter Grade (`O`, `A+`, `A`, `B+`, `B`, `F`)
  - Pass/Fail Status (Minimum 50 marks per subject required to pass).

---

### Experiment 9: Servlet Lifecycle Demonstration
- **Aim**: Track and display invocation counts for each stage of the Servlet lifecycle.
- **Servlet Class**: `com.co3.servlets.Exp9LifecycleServlet`
- **URL Endpoint**: `/exp9` or `/lifecycle`
- **Lifecycle Phases**:
  1. `Constructor()` - Executed once on instantiation.
  2. `init(ServletConfig)` - Executed once during initialization.
  3. `service()` & `doGet()` - Executed on every incoming client request.
  4. `destroy()` - Executed once when application is undeployed or container shuts down.

---

### Experiment 10: Thread-Safe Concurrent Visitor Counter Using Servlet
- **Aim**: Analyze concurrency issues in multi-threaded servlets, demonstrate race conditions on mutable instance variables, and implement thread-safe counters.
- **Servlet Class**: `com.co3.servlets.Exp10VisitorCounterServlet`
- **URL Endpoint**: `/exp10` or `/counter`
- **Key Concepts**:
  - **Unsafe Counter**: `int unsafeHitCount; count++` exhibits race conditions due to non-atomic read-modify-write interleaving.
  - **Thread-Safe Atomic**: `AtomicInteger` uses lock-free hardware CAS (Compare-And-Swap).
  - **Thread-Safe Synchronized**: `synchronized (lock)` enforces mutual exclusion.
  - **Stack Isolation**: Local variables inside `doGet()` are allocated on private thread stack frames and are inherently thread-safe.

---

## 🛠️ How to Deploy Java Servlets (Exp 6 to 10) to Apache Tomcat

1. **Build WAR with Maven**:
   ```bash
   cd servlets
   mvn clean package
   ```
2. **Deploy to Tomcat**:
   - Copy `target/co3-servlets.war` into `$CATALINA_HOME/webapps/`
   - Start Tomcat: `$CATALINA_HOME/bin/startup.sh`
3. **Open Portal**: `http://localhost:8080/co3-servlets/`

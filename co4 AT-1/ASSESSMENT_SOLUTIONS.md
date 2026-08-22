# WEB TECHNOLOGY (UNIT IV - REPRESENTING WEB DATA)
## ASSESSMENT I: DATA INTERPRETATION & EVALUATION KEY

**Course**: Web Technology  
**Unit**: Unit IV – Representing Web Data (XML, XPath, XSLT)  
**Duration**: 60 Minutes  
**Maximum Marks**: 30  
**Scenario**: University Course Enrollment Analysis  

---

## 📑 Complete Assessment Answers & Solutions

---

### Question 1: Interpret the XML Structure (5 Marks)

| Sub-Question | Requirement | Answer & Detailed Justification | Marks |
| :--- | :--- | :--- | :---: |
| **1.a** | **Identify the root element.** | **`<courses>`**<br>*Explanation*: The `<courses>` element is the top-level parent element that encloses all other child elements in the document hierarchy. | **1 Mark** |
| **1.b** | **Identify the repeating record element.** | **`<course>`**<br>*Explanation*: The `<course>` element repeats for every course entity entry in the dataset (5 course records: `C101` to `C105`). | **1 Mark** |
| **1.c** | **Identify the attribute used to uniquely identify each course.** | **`id`** (e.g., `id="C101"`, `id="C102"`, `id="C103"`, `id="C104"`, `id="C105"`)<br>*Explanation*: The `id` attribute defined on the `<course>` start tag provides a unique key identifier for each record. | **1 Mark** |
| **1.d** | **Identify the elements that represent numeric information.** | **`<students>`** and **`<credits>`**<br>*Explanation*: `<students>` stores integer enrollment counts (58, 72, 36, 64, 42) and `<credits>` stores course credit values (4, 4, 2, 4, 3). | **1 Mark** |
| **1.e** | **State whether the XML document is structurally well-formed and justify your answer.** | **Yes, the XML document is structurally well-formed.**<br>*Justifications*:<br>1. It begins with a valid XML prolog: `<?xml version="1.0" encoding="UTF-8"?>`.<br>2. It has exactly **one single root element** (`<courses>`) containing all content.<br>3. Every opening tag has a corresponding, properly nested matching closing tag (e.g., `<name>...</name>`).<br>4. XML tag names are case-sensitive and strictly closed in LIFO order (no overlapping tags).<br>5. All attribute values are properly quoted (e.g., `id="C101"`). | **1 Mark** |

**Total for Question 1: 5 / 5 Marks**

---

### Question 2: Apply XPath for Data Selection (10 Marks)

| Sub-Q | Data-Selection Requirement | Valid XPath Expression | Extracted Matches / Result | Marks |
| :--- | :--- | :--- | :--- | :---: |
| **2.a** | All course records | `/courses/course`<br>*(or `//course`)* | Returns all 5 `<course>` elements (`C101` – `C105`) | **1 Mark** |
| **2.b** | Names of all courses | `/courses/course/name`<br>*(or `//course/name`)* | `Web Technology`, `Artificial Intelligence`, `Web Technology Laboratory`, `Machine Learning`, `Database Systems` | **1 Mark** |
| **2.c** | Courses having more than 50 students | `/courses/course[students > 50]`<br>*(or `//course[number(students) > 50]`)* | Returns 3 courses: `C101` (58), `C102` (72), `C104` (64) | **1 Mark** |
| **2.d** | Courses carrying 4 credits | `/courses/course[credits = 4]`<br>*(or `//course[credits = 4]`)* | Returns 3 courses: `C101` (WEB301), `C102` (AI302), `C104` (ML304) | **1 Mark** |
| **2.e** | Courses whose type is Theory | `/courses/course[type = 'Theory']`<br>*(or `//course[type = 'Theory']`)* | Returns 4 courses: `C101`, `C102`, `C104`, `C105` | **1 Mark** |
| **2.f** | Names of Theory courses having more than 50 students | `/courses/course[type = 'Theory' and students > 50]/name`<br>*(or `//course[type = 'Theory' and students > 50]/name`)* | `Web Technology`, `Artificial Intelligence`, `Machine Learning` | **1 Mark** |
| **2.g** | Faculty members handling courses with at least 4 credits | `/courses/course[credits >= 4]/faculty`<br>*(or `//course[credits >= 4]/faculty`)* | `Dr. Arun`, `Dr. Meena`, `Dr. Priya` | **1 Mark** |
| **2.h** | The course whose id is C104 | `/courses/course[@id = 'C104']`<br>*(or `//course[@id = 'C104']`)* | Returns `<course id="C104">` (Machine Learning) | **1 Mark** |
| **2.i** | The first course available in the XML document | `/courses/course[1]`<br>*(or `(//course)[1]`)* | Returns `<course id="C101">` (Web Technology) | **1 Mark** |
| **2.j** | The last course available in the XML document | `/courses/course[last()]`<br>*(or `(//course)[last()]`)* | Returns `<course id="C105">` (Database Systems) | **1 Mark** |

**Total for Question 2: 10 / 10 Marks**

---

### Question 3: Apply XSLT for Data Presentation (10 Marks)

#### Requirements Checklist:
- [x] HTML Table with headers: Course Code, Course Name, Faculty, Students, Credits, Type
- [x] Display only courses having `students > 40`
- [x] Sorted in descending order of student enrollment (`order="descending" data-type="number"`)
- [x] Heading: "High Enrollment Courses"
- [x] Valid XSLT 1.0 syntax producing clean HTML

#### XSLT Stylesheet Code (`courses.xsl`):
```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" doctype-system="about:blank" indent="yes" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
        <head>
            <title>High Enrollment Courses</title>
            <style>
                table { width: 100%; border-collapse: collapse; font-family: sans-serif; }
                th, td { border: 1px solid #cbd5e1; padding: 8px 12px; text-align: left; }
                th { background-color: #1e293b; color: white; }
                tr:nth-child(even) { background-color: #f8fafc; }
            </style>
        </head>
        <body>
            <h2>High Enrollment Courses</h2>
            <table>
                <tr>
                    <th>Course Code</th>
                    <th>Course Name</th>
                    <th>Faculty</th>
                    <th>Students</th>
                    <th>Credits</th>
                    <th>Type</th>
                </tr>
                <!-- Filter: students > 40 -->
                <xsl:for-each select="/courses/course[students > 40]">
                    <!-- Sort: descending by student enrollment -->
                    <xsl:sort select="students" data-type="number" order="descending"/>
                    <tr>
                        <td><xsl:value-of select="code"/></td>
                        <td><xsl:value-of select="name"/></td>
                        <td><xsl:value-of select="faculty"/></td>
                        <td><xsl:value-of select="students"/></td>
                        <td><xsl:value-of select="credits"/></td>
                        <td><xsl:value-of select="type"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
```

#### Expected Transformed Output Table:
| Course Code | Course Name | Faculty | Students | Credits | Type |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **AI302** | Artificial Intelligence | Dr. Meena | **72** | 4 | Theory |
| **ML304** | Machine Learning | Dr. Priya | **64** | 4 | Theory |
| **WEB301** | Web Technology | Dr. Arun | **58** | 4 | Theory |
| **DB305** | Database Systems | Dr. Kumar | **42** | 3 | Theory |

*(Note: `WEB303` is excluded because enrollment is 36, which is not > 40).*

**Total for Question 3: 10 / 10 Marks**

---

### Question 4: Interpret the Extracted Data (5 Marks)

| Sub-Q | Interpretation Question | Extracted Answer & Academic Rationale | Marks |
| :--- | :--- | :--- | :---: |
| **4.a** | **Identify the course with the highest enrollment.** | **`AI302 - Artificial Intelligence`**<br>*Details*: Faculty: Dr. Meena | **Enrollment: 72 students** (Maximum in the dataset). | **1 Mark** |
| **4.b** | **Identify the course with the lowest enrollment.** | **`WEB303 - Web Technology Laboratory`**<br>*Details*: Faculty: Dr. Ravi | **Enrollment: 36 students** (Minimum in the dataset). | **1 Mark** |
| **4.c** | **Determine the number of Theory courses.** | **4 Theory Courses**<br>*List*:<br>1. `WEB301` - Web Technology (Theory)<br>2. `AI302` - Artificial Intelligence (Theory)<br>3. `ML304` - Machine Learning (Theory)<br>4. `DB305` - Database Systems (Theory)<br>*(Only `WEB303` is Practical).* | **1 Mark** |
| **4.d** | **Identify all courses having exactly 4 credits.** | **3 Courses** have exactly 4 credits:<br>1. **`C101 (WEB301 - Web Technology)`** (4 Credits)<br>2. **`C102 (AI302 - Artificial Intelligence)`** (4 Credits)<br>3. **`C104 (ML304 - Machine Learning)`** (4 Credits) | **1 Mark** |
| **4.e** | **If an additional teaching assistant is assigned to every course with more than 60 students, identify the courses that require additional support.** | **2 Courses Require Additional TA Support**:<br>1. **`AI302 (Artificial Intelligence)`** &rarr; **72 students** (> 60)<br>2. **`ML304 (Machine Learning)`** &rarr; **64 students** (> 60)<br><br>*Non-qualifying courses*: `WEB301` (58 &le; 60), `DB305` (42 &le; 60), `WEB303` (36 &le; 60). | **1 Mark** |

**Total for Question 4: 5 / 5 Marks**

---

## 📊 Summary Score Card

| Section | Topic | Maximum Marks | Scored |
| :--- | :--- | :---: | :---: |
| Question 1 | XML Document Structure & Well-Formedness | 5 | 5 |
| Question 2 | XPath Expressions & Query Selection | 10 | 10 |
| Question 3 | XSLT Transformation, Filtering & Sorting | 10 | 10 |
| Question 4 | Data Interpretation & Analytical Insights | 5 | 5 |
| **Grand Total** | **Unit IV Assessment I** | **30** | **30** |

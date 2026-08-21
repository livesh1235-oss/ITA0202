# Unit IV: Representing Web Data — Assessment I

This folder contains the complete solutions, XML dataset, XSLT stylesheet, and interactive XPath/XSLT evaluation workbench for **Web Technology (Unit IV - Representing Web Data) Assessment I: Data Interpretation**.

---

## 📂 File Organization

| File | Description |
| :--- | :--- |
| **[`ASSESSMENT_SOLUTIONS.md`](ASSESSMENT_SOLUTIONS.md)** | Complete written solutions for Questions 1, 2, 3, and 4 with explanations & rubrics (30/30 Marks). |
| **[`courses.xml`](courses.xml)** | The given XML dataset containing 5 course enrollment records (`C101` to `C105`). |
| **[`courses.xsl`](courses.xsl)** | XSLT Stylesheet transforming XML into an HTML table with `students > 40` filter and descending sort. |
| **[`index.html`](index.html)** | Interactive Web Dashboard featuring live in-browser XPath evaluator, XSLT preview, and solutions view. |
| **[`style.css`](style.css)** & **[`script.js`](script.js)** | UI styling and browser-native XPath / XSLT engine. |

---

## 📝 Solutions Summary

### 1. XML Structure Interpretation (5 Marks)
- **Root Element**: `<courses>`
- **Repeating Record Element**: `<course>`
- **Unique Identifier Attribute**: `id` (`C101` – `C105`)
- **Numeric Elements**: `<students>` (enrollment) & `<credits>` (credits)
- **Well-formedness**: Yes &mdash; single root, properly closed tags, strictly nested LIFO hierarchy, valid quotes on attributes, valid XML prolog.

### 2. XPath Expressions (10 Marks)
- a. All course records: `/courses/course`
- b. Names of all courses: `/courses/course/name`
- c. Courses having > 50 students: `/courses/course[students > 50]`
- d. Courses carrying 4 credits: `/courses/course[credits = 4]`
- e. Courses whose type is Theory: `/courses/course[type = 'Theory']`
- f. Names of Theory courses having > 50 students: `/courses/course[type = 'Theory' and students > 50]/name`
- g. Faculty members handling courses with at least 4 credits: `/courses/course[credits >= 4]/faculty`
- h. The course whose id is C104: `/courses/course[@id = 'C104']`
- i. First course: `/courses/course[1]`
- j. Last course: `/courses/course[last()]`

### 3. XSLT Transformation (10 Marks)
- Filters `/courses/course[students > 40]`
- Sorts `<xsl:sort select="students" data-type="number" order="descending"/>`
- Generates HTML table with columns: Course Code, Course Name, Faculty, Students, Credits, Type.

### 4. Data Interpretation (5 Marks)
- a. **Highest enrollment**: AI302 (Artificial Intelligence) – 72 students (Dr. Meena)
- b. **Lowest enrollment**: WEB303 (Web Tech Lab) – 36 students (Dr. Ravi)
- c. **Theory courses count**: 4 courses (WEB301, AI302, ML304, DB305)
- d. **4-credit courses**: C101 (WEB301), C102 (AI302), C104 (ML304)
- e. **Courses requiring additional TA (> 60 students)**: AI302 (72 students) & ML304 (64 students).

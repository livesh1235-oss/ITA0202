package com.co3.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Experiment 8: Online Student Result Processing Using Servlet
 * 
 * Objectives:
 * - Accept student details and 5 subject marks
 * - Validate missing, non-numeric, and out-of-range marks (0–100)
 * - Calculate Total, Average, Highest Mark, Lowest Mark, Grade, and Pass/Fail status
 * - Generate a dynamic, responsive HTML grade sheet table
 */
@WebServlet(name = "Exp8ResultProcessingServlet", urlPatterns = {"/exp8", "/processResult"})
public class Exp8ResultProcessingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Standard subject names
    private static final String[] SUBJECT_NAMES = {
        "Data Structures & Algorithms",
        "Object-Oriented Programming (Java)",
        "Database Management Systems",
        "Web Technology & Frameworks",
        "Computer Networks"
    };

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String studentName = request.getParameter("studentName");
        String regNumber = request.getParameter("regNumber");
        String department = request.getParameter("department");

        List<String> errors = new ArrayList<>();

        if (studentName == null || studentName.trim().isEmpty()) {
            errors.add("Student Name is required.");
        }
        if (regNumber == null || regNumber.trim().isEmpty()) {
            errors.add("Register Number is required.");
        }

        // Parse and validate 5 subject marks
        double[] marks = new double[5];
        for (int i = 0; i < 5; i++) {
            String markParam = request.getParameter("subject" + (i + 1));
            if (markParam == null || markParam.trim().isEmpty()) {
                errors.add("Mark for Subject " + (i + 1) + " (" + SUBJECT_NAMES[i] + ") is missing.");
            } else {
                try {
                    double markVal = Double.parseDouble(markParam.trim());
                    if (markVal < 0 || markVal > 100) {
                        errors.add("Mark for Subject " + (i + 1) + " must be between 0 and 100.");
                    } else {
                        marks[i] = markVal;
                    }
                } catch (NumberFormatException e) {
                    errors.add("Mark for Subject " + (i + 1) + " must be a valid numeric value.");
                }
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>Academic Result Sheet | Servlet Output</title>");
            out.println("    <style>");
            out.println("        body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #f1f5f9; color: #0f172a; padding: 2rem 1rem; }");
            out.println("        .container { max-width: 820px; margin: 0 auto; background: #ffffff; border-radius: 12px; padding: 2.5rem; border: 1px solid #e2e8f0; box-shadow: 0 10px 25px -5px rgba(0,0,0,0.06); }");
            out.println("        .header-title { font-size: 1.6rem; color: #1e293b; margin-bottom: 0.25rem; font-weight: 700; }");
            out.println("        .sub { color: #64748b; font-size: 0.9rem; margin-bottom: 1.5rem; }");
            out.println("        .student-info { background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 8px; padding: 1rem 1.5rem; display: grid; grid-template-columns: repeat(3, 1fr); gap: 1rem; margin-bottom: 1.5rem; }");
            out.println("        .info-block span { display: block; font-size: 0.8rem; color: #64748b; font-weight: 600; text-transform: uppercase; }");
            out.println("        .info-block strong { font-size: 1rem; color: #1e293b; }");
            out.println("        table { width: 100%; border-collapse: collapse; margin-bottom: 1.5rem; font-size: 0.9rem; }");
            out.println("        th { background: #f1f5f9; color: #475569; padding: 0.75rem 1rem; text-align: left; border-bottom: 2px solid #cbd5e1; font-weight: 600; }");
            out.println("        td { padding: 0.75rem 1rem; border-bottom: 1px solid #e2e8f0; color: #334155; }");
            out.println("        .score-grid { display: grid; grid-template-columns: repeat(4, 1fr); gap: 1rem; margin-bottom: 1.5rem; }");
            out.println("        .score-card { background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 8px; padding: 1rem; text-align: center; }");
            out.println("        .score-card .val { font-size: 1.4rem; font-weight: 700; color: #2563eb; }");
            out.println("        .score-card .lbl { font-size: 0.75rem; color: #64748b; text-transform: uppercase; font-weight: 600; }");
            out.println("        .status-badge { display: inline-block; padding: 6px 16px; border-radius: 20px; font-weight: 700; font-size: 0.95rem; }");
            out.println("        .badge-pass { background: #dcfce7; color: #166534; }");
            out.println("        .badge-fail { background: #fee2e2; color: #991b1b; }");
            out.println("        .btn-back { display: inline-block; background: #2563eb; color: white; padding: 0.6rem 1.25rem; border-radius: 6px; text-decoration: none; font-weight: 600; font-size: 0.9rem; }");
            out.println("        .error-card { background: #fef2f2; border: 1px solid #fecaca; border-radius: 8px; padding: 1.5rem; color: #991b1b; margin-bottom: 1.5rem; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class='container'>");

            if (!errors.isEmpty()) {
                // Validation Error View
                out.println("        <h1 class='header-title' style='color: #dc2626;'>⚠️ Mark Sheet Validation Errors</h1>");
                out.println("        <div class='error-card'>");
                out.println("            <ul style='padding-left: 1.25rem;'>");
                for (String err : errors) {
                    out.println("                <li>" + err + "</li>");
                }
                out.println("            </ul>");
                out.println("        </div>");
            } else {
                // Calculation logic
                double total = 0;
                double maxMark = marks[0];
                double minMark = marks[0];
                boolean isPassed = true;

                for (double mark : marks) {
                    total += mark;
                    if (mark > maxMark) maxMark = mark;
                    if (mark < minMark) minMark = mark;
                    if (mark < 50.0) { // Passing cutoff per subject is 50
                        isPassed = false;
                    }
                }

                double average = total / 5.0;

                // Grade calculation
                String grade;
                if (!isPassed) {
                    grade = "F (Reappear)";
                } else if (average >= 90) {
                    grade = "O (Outstanding)";
                } else if (average >= 80) {
                    grade = "A+ (Excellent)";
                } else if (average >= 70) {
                    grade = "A (Very Good)";
                } else if (average >= 60) {
                    grade = "B+ (Good)";
                } else {
                    grade = "B (Above Average)";
                }

                out.println("        <h1 class='header-title'>Academic Performance & Result Card</h1>");
                out.println("        <p class='sub'>Processed dynamically via <code>Exp8ResultProcessingServlet</code></p>");
                
                // Student info
                out.println("        <div class='student-info'>");
                out.println("            <div class='info-block'><span>Student Name</span><strong>" + escape(studentName) + "</strong></div>");
                out.println("            <div class='info-block'><span>Register Number</span><strong>" + escape(regNumber) + "</strong></div>");
                out.println("            <div class='info-block'><span>Department</span><strong>" + escape(department) + "</strong></div>");
                out.println("        </div>");

                // Subject marks table
                out.println("        <table>");
                out.println("            <thead>");
                out.println("                <tr><th>Subject Code</th><th>Subject Title</th><th>Max Marks</th><th>Marks Obtained</th><th>Result</th></tr>");
                out.println("            </thead>");
                out.println("            <tbody>");
                for (int i = 0; i < 5; i++) {
                    String subStatus = marks[i] >= 50 ? "<span style='color:#16a34a; font-weight:700;'>PASS</span>" : "<span style='color:#dc2626; font-weight:700;'>FAIL</span>";
                    out.println("                <tr>");
                    out.println("                    <td>CS30" + (i + 1) + "</td>");
                    out.println("                    <td>" + SUBJECT_NAMES[i] + "</td>");
                    out.println("                    <td>100</td>");
                    out.println("                    <td><strong>" + String.format("%.1f", marks[i]) + "</strong></td>");
                    out.println("                    <td>" + subStatus + "</td>");
                    out.println("                </tr>");
                }
                out.println("            </tbody>");
                out.println("        </table>");

                // Analytics Score Grid
                out.println("        <div class='score-grid'>");
                out.println("            <div class='score-card'><div class='val'>" + String.format("%.1f", total) + "/500</div><div class='lbl'>Total Score</div></div>");
                out.println("            <div class='score-card'><div class='val'>" + String.format("%.2f", average) + "%</div><div class='lbl'>Average (%)</div></div>");
                out.println("            <div class='score-card'><div class='val' style='color:#16a34a;'>" + String.format("%.1f", maxMark) + "</div><div class='lbl'>Highest Mark</div></div>");
                out.println("            <div class='score-card'><div class='val' style='color:#e11d48;'>" + String.format("%.1f", minMark) + "</div><div class='lbl'>Lowest Mark</div></div>");
                out.println("        </div>");

                // Final Grade & Status
                out.println("        <div style='display: flex; justify-content: space-between; align-items: center; background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 8px; padding: 1.25rem 1.5rem; margin-bottom: 2rem;'>");
                out.println("            <div><span style='color: #64748b; font-size: 0.85rem; font-weight: 600;'>FINAL GRADE:</span> <strong style='font-size: 1.2rem; color: #1e293b; margin-left: 0.5rem;'>" + grade + "</strong></div>");
                out.println("            <div><span class='status-badge " + (isPassed ? "badge-pass" : "badge-fail") + "'>" + (isPassed ? "OVERALL RESULT: PASS" : "OVERALL RESULT: FAIL") + "</span></div>");
                out.println("        </div>");
            }

            out.println("        <a href='exp8_result_form.html' class='btn-back'>&larr; Calculate Another Result</a>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect("exp8_result_form.html");
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}

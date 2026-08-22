package com.co3.assessment;

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
 * CO3 - Assessment 1 - Experiment Question 2
 * Servlet-Based Student Result Processing
 * 
 * Concepts Assessed:
 * - Java Servlet Architecture & HttpServlet
 * - HTTP POST method handling via doPost()
 * - Form parameter retrieval using request.getParameter()
 * - Validation for missing, non-numeric, and out-of-range (0–100) marks
 * - Dynamic HTML generation using PrintWriter
 * - Thread-Safe Concurrency: All request-specific data is strictly declared as
 *   LOCAL VARIABLES on the thread's execution stack frame (no shared instance state).
 */
@WebServlet(name = "StudentResultServlet", urlPatterns = {"/processResult", "/result"})
public class StudentResultServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Subject names constant array
    private static final String[] SUBJECTS = {
        "Subject 1: Web Technology",
        "Subject 2: Cloud Computing",
        "Subject 3: Software Engineering"
    };

    /**
     * Handles HTTP POST requests from result_form.html.
     * All student data is processed strictly inside local variables for thread safety.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Set request character encoding and response content type
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // -------------------------------------------------------------
        // CONCURRENCY SAFETY: Local Variables (Allocated on Thread Stack)
        // -------------------------------------------------------------
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();

        // 2. Read form parameters via request.getParameter()
        String studentName = request.getParameter("studentName");
        String regNumber = request.getParameter("regNumber");
        String mark1Str = request.getParameter("mark1");
        String mark2Str = request.getParameter("mark2");
        String mark3Str = request.getParameter("mark3");

        // 3. Validation Logic (strictly local list)
        List<String> validationErrors = new ArrayList<>();

        if (studentName == null || studentName.trim().isEmpty()) {
            validationErrors.add("Student Name is required.");
        }
        if (regNumber == null || regNumber.trim().isEmpty()) {
            validationErrors.add("Register Number is required.");
        }

        // Validate and parse 3 subjects
        double mark1 = 0, mark2 = 0, mark3 = 0;
        String[] markInputs = { mark1Str, mark2Str, mark3Str };
        double[] parsedMarks = new double[3];

        for (int i = 0; i < 3; i++) {
            String mStr = markInputs[i];
            if (mStr == null || mStr.trim().isEmpty()) {
                validationErrors.add("Mark for " + SUBJECTS[i] + " is missing.");
            } else {
                try {
                    double val = Double.parseDouble(mStr.trim());
                    if (val < 0.0 || val > 100.0) {
                        validationErrors.add("Mark for " + SUBJECTS[i] + " must be between 0 and 100 (Received: " + val + ").");
                    } else {
                        parsedMarks[i] = val;
                    }
                } catch (NumberFormatException e) {
                    validationErrors.add("Mark for " + SUBJECTS[i] + " must be a valid numeric value.");
                }
            }
        }

        // 4. Output Dynamic Response using PrintWriter
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>Academic Result Sheet | Servlet Output</title>");
            out.println("    <style>");
            out.println("        body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #0f172a; color: #f8fafc; padding: 2.5rem 1rem; margin: 0; }");
            out.println("        .container { max-width: 800px; margin: 0 auto; background: #1e293b; border-radius: 12px; padding: 2.5rem; border: 1px solid #334155; box-shadow: 0 15px 30px rgba(0,0,0,0.4); }");
            out.println("        .badge-header { display: inline-block; background: rgba(59, 130, 246, 0.2); color: #60a5fa; border: 1px solid rgba(59, 130, 246, 0.4); padding: 4px 12px; border-radius: 20px; font-size: 0.8rem; font-weight: 700; text-transform: uppercase; margin-bottom: 0.75rem; }");
            out.println("        h1 { font-size: 1.75rem; color: #ffffff; margin: 0 0 0.5rem 0; }");
            out.println("        .subtitle { color: #94a3b8; font-size: 0.9rem; margin-bottom: 1.75rem; }");
            out.println("        .info-card { background: #0b0f19; border: 1px solid #334155; border-radius: 8px; padding: 1.25rem 1.5rem; display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; margin-bottom: 1.5rem; }");
            out.println("        .info-item span { display: block; font-size: 0.75rem; color: #94a3b8; text-transform: uppercase; font-weight: 600; }");
            out.println("        .info-item strong { font-size: 1.05rem; color: #f8fafc; }");
            out.println("        table { width: 100%; border-collapse: collapse; margin-bottom: 1.5rem; font-size: 0.9rem; }");
            out.println("        th { background: #0b0f19; color: #94a3b8; padding: 0.75rem 1rem; text-align: left; border-bottom: 2px solid #334155; font-weight: 600; }");
            out.println("        td { padding: 0.75rem 1rem; border-bottom: 1px solid #334155; color: #e2e8f0; }");
            out.println("        .metrics-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 1rem; margin-bottom: 1.5rem; }");
            out.println("        .metric-box { background: #0b0f19; border: 1px solid #334155; border-radius: 8px; padding: 1.25rem; text-align: center; }");
            out.println("        .metric-val { font-size: 1.6rem; font-weight: 800; color: #38bdf8; font-family: monospace; }");
            out.println("        .metric-lbl { font-size: 0.75rem; color: #94a3b8; text-transform: uppercase; font-weight: 600; margin-top: 0.25rem; }");
            out.println("        .status-row { display: flex; justify-content: space-between; align-items: center; background: #0b0f19; border: 1px solid #334155; border-radius: 8px; padding: 1.25rem 1.5rem; margin-bottom: 1.5rem; }");
            out.println("        .status-badge { display: inline-block; padding: 6px 16px; border-radius: 20px; font-weight: 800; font-size: 0.95rem; }");
            out.println("        .badge-pass { background: rgba(16, 185, 129, 0.2); color: #34d399; border: 1px solid rgba(16, 185, 129, 0.4); }");
            out.println("        .badge-fail { background: rgba(239, 68, 68, 0.2); color: #f87171; border: 1px solid rgba(239, 68, 68, 0.4); }");
            out.println("        .concurrency-note { background: #172033; border: 1px solid #1e293b; border-radius: 8px; padding: 1rem 1.25rem; font-size: 0.82rem; color: #94a3b8; line-height: 1.5; margin-bottom: 1.5rem; }");
            out.println("        .concurrency-note code { color: #60a5fa; }");
            out.println("        .btn-back { display: inline-block; background: #2563eb; color: white; padding: 0.65rem 1.25rem; border-radius: 6px; text-decoration: none; font-weight: 600; font-size: 0.9rem; transition: background 0.2s; }");
            out.println("        .btn-back:hover { background: #1d4ed8; }");
            out.println("        .error-card { background: rgba(239, 68, 68, 0.15); border: 1px solid rgba(239, 68, 68, 0.3); border-radius: 8px; padding: 1.5rem; color: #fca5a5; margin-bottom: 1.5rem; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class='container'>");
            out.println("        <span class='badge-header'>Java Servlet 3.1 &bull; Assessment 1 Output</span>");

            if (!validationErrors.isEmpty()) {
                // Validation Errors View
                out.println("        <h1 style='color: #f87171;'>⚠️ Input Validation Failed</h1>");
                out.println("        <p class='subtitle'>The servlet identified invalid or missing parameters in your submission:</p>");
                out.println("        <div class='error-card'>");
                out.println("            <ul style='margin: 0; padding-left: 1.25rem;'>");
                for (String err : validationErrors) {
                    out.println("                <li style='margin-bottom: 0.35rem;'>" + err + "</li>");
                }
                out.println("            </ul>");
                out.println("        </div>");
            } else {
                // Calculations using Local Variables
                mark1 = parsedMarks[0];
                mark2 = parsedMarks[1];
                mark3 = parsedMarks[2];

                double total = mark1 + mark2 + mark3;
                double average = total / 3.0;
                double highestMark = Math.max(mark1, Math.max(mark2, mark3));

                // Passing criteria: Each subject >= 50
                boolean isPassed = (mark1 >= 50.0 && mark2 >= 50.0 && mark3 >= 50.0);
                String resultStatus = isPassed ? "PASS" : "FAIL (Reappear)";

                out.println("        <h1>Student Academic Result Card</h1>");
                out.println("        <p class='subtitle'>Processed dynamically by <code>StudentResultServlet</code> via HTTP POST.</p>");

                // Student Details
                out.println("        <div class='info-card'>");
                out.println("            <div class='info-item'><span>Student Name</span><strong>" + escape(studentName) + "</strong></div>");
                out.println("            <div class='info-item'><span>Register Number</span><strong>" + escape(regNumber) + "</strong></div>");
                out.println("        </div>");

                // Marks Table
                out.println("        <table>");
                out.println("            <thead>");
                out.println("                <tr><th>Subject Code</th><th>Subject Name</th><th>Max Marks</th><th>Marks Scored</th><th>Status</th></tr>");
                out.println("            </thead>");
                out.println("            <tbody>");
                for (int i = 0; i < 3; i++) {
                    double m = parsedMarks[i];
                    String subPass = (m >= 50.0) ? "<span style='color: #34d399; font-weight:700;'>PASS</span>" : "<span style='color: #f87171; font-weight:700;'>FAIL</span>";
                    out.println("                <tr>");
                    out.println("                    <td>CS30" + (i + 1) + "</td>");
                    out.println("                    <td>" + SUBJECTS[i] + "</td>");
                    out.println("                    <td>100</td>");
                    out.println("                    <td><strong>" + String.format("%.1f", m) + "</strong></td>");
                    out.println("                    <td>" + subPass + "</td>");
                    out.println("                </tr>");
                }
                out.println("            </tbody>");
                out.println("        </table>");

                // Summary Metrics Grid (Total, Average, Highest Mark)
                out.println("        <div class='metrics-grid'>");
                out.println("            <div class='metric-box'><div class='metric-val'>" + String.format("%.1f", total) + " / 300</div><div class='metric-lbl'>Total Marks</div></div>");
                out.println("            <div class='metric-box'><div class='metric-val'>" + String.format("%.2f", average) + "%</div><div class='lbl metric-lbl'>Average Score</div></div>");
                out.println("            <div class='metric-box'><div class='metric-val' style='color: #34d399;'>" + String.format("%.1f", highestMark) + "</div><div class='metric-lbl'>Highest Mark</div></div>");
                out.println("        </div>");

                // Overall Status Banner
                out.println("        <div class='status-row'>");
                out.println("            <div><span style='color: #94a3b8; font-size: 0.85rem; font-weight: 600;'>OVERALL RESULT:</span> <strong style='font-size: 1.15rem; color: #ffffff; margin-left: 0.5rem;'>" + resultStatus + "</strong></div>");
                out.println("            <div><span class='status-badge " + (isPassed ? "badge-pass" : "badge-fail") + "'>" + (isPassed ? "✓ PASSED" : "✕ FAILED") + "</span></div>");
                out.println("        </div>");

                // Servlet Concurrency Awareness Note
                out.println("        <div class='concurrency-note'>");
                out.println("            <strong>🔒 Thread-Safety Guarantee:</strong> All variables (<code>total</code>, <code>average</code>, <code>highestMark</code>) were computed as local stack variables on thread <code>" + threadName + " (ID: " + threadId + ")</code>. No shared instance state was mutated, ensuring 100% thread safety during concurrent requests.");
                out.println("        </div>");
            }

            out.println("        <a href='result_form.html' class='btn-back'>&larr; Calculate Another Result</a>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET calls back to the HTML form
        response.sendRedirect("result_form.html");
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}

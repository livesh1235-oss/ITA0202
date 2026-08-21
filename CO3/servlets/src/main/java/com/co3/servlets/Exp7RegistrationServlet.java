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
 * Experiment 7: Student Registration Form Processing Using Servlet
 * 
 * Objectives:
 * - Handle HTTP POST requests via doPost()
 * - Retrieve form parameters using request.getParameter()
 * - Validate empty/null/invalid inputs on server side
 * - Render dynamic acknowledgement receipt table
 */
@WebServlet(name = "Exp7RegistrationServlet", urlPatterns = {"/exp7", "/registerStudent"})
public class Exp7RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Set request encoding & response content type
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 2. Retrieve form parameters
        String studentName = request.getParameter("studentName");
        String regNumber = request.getParameter("regNumber");
        String email = request.getParameter("email");
        String department = request.getParameter("department");
        String semester = request.getParameter("semester");

        // 3. Server-side validation
        List<String> validationErrors = new ArrayList<>();

        if (studentName == null || studentName.trim().isEmpty()) {
            validationErrors.add("Student Full Name is required.");
        }
        if (regNumber == null || regNumber.trim().isEmpty()) {
            validationErrors.add("Register Number is required.");
        }
        if (email == null || email.trim().isEmpty()) {
            validationErrors.add("Email address is required.");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            validationErrors.add("Please provide a valid email format.");
        }
        if (department == null || department.trim().isEmpty()) {
            validationErrors.add("Department must be selected.");
        }
        if (semester == null || semester.trim().isEmpty()) {
            validationErrors.add("Semester must be selected.");
        }

        // 4. Generate response
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>Experiment 7: Registration Processing Result</title>");
            out.println("    <style>");
            out.println("        body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #f8fafc; color: #0f172a; padding: 2rem; }");
            out.println("        .card { max-width: 750px; margin: 0 auto; background: #ffffff; border-radius: 12px; padding: 2.5rem; box-shadow: 0 10px 25px -5px rgba(0,0,0,0.08); border: 1px solid #e2e8f0; }");
            out.println("        .success-badge { display: inline-block; background: #dcfce7; color: #166534; padding: 5px 12px; border-radius: 20px; font-weight: 700; font-size: 0.85rem; margin-bottom: 1rem; }");
            out.println("        .error-badge { display: inline-block; background: #fee2e2; color: #991b1b; padding: 5px 12px; border-radius: 20px; font-weight: 700; font-size: 0.85rem; margin-bottom: 1rem; }");
            out.println("        h1 { color: #1e293b; font-size: 1.6rem; margin-bottom: 0.5rem; }");
            out.println("        .receipt-table { width: 100%; border-collapse: collapse; margin-top: 1.5rem; font-size: 0.95rem; }");
            out.println("        .receipt-table th { background: #f1f5f9; color: #475569; text-align: left; padding: 0.75rem 1rem; border-bottom: 2px solid #cbd5e1; width: 35%; }");
            out.println("        .receipt-table td { padding: 0.75rem 1rem; border-bottom: 1px solid #e2e8f0; color: #1e293b; font-weight: 600; }");
            out.println("        .error-list { background: #fef2f2; border: 1px solid #fecaca; border-radius: 8px; padding: 1.25rem 1.5rem; color: #b91c1c; margin-top: 1.5rem; }");
            out.println("        .error-list li { margin-bottom: 0.4rem; }");
            out.println("        .btn-back { display: inline-block; margin-top: 2rem; background: #2563eb; color: white; padding: 0.6rem 1.25rem; border-radius: 6px; text-decoration: none; font-weight: 600; font-size: 0.9rem; }");
            out.println("        .btn-back:hover { background: #1d4ed8; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class='card'>");

            if (validationErrors.isEmpty()) {
                // Success Case
                out.println("        <span class='success-badge'>✓ Registration Form Processed</span>");
                out.println("        <h1>Student Registration Acknowledgement</h1>");
                out.println("        <p style='color: #64748b;'>The servlet has received and verified your registration parameters via HTTP POST.</p>");
                out.println("        <table class='receipt-table'>");
                out.println("            <tr><th>Student Name</th><td>" + escapeHtml(studentName) + "</td></tr>");
                out.println("            <tr><th>Register Number</th><td>" + escapeHtml(regNumber) + "</td></tr>");
                out.println("            <tr><th>Email Address</th><td>" + escapeHtml(email) + "</td></tr>");
                out.println("            <tr><th>Department</th><td>" + escapeHtml(department) + "</td></tr>");
                out.println("            <tr><th>Semester</th><td>Semester " + escapeHtml(semester) + "</td></tr>");
                out.println("            <tr><th>Submission Method</th><td>" + request.getMethod() + "</td></tr>");
                out.println("        </table>");
            } else {
                // Validation Failure Case
                out.println("        <span class='error-badge'>⚠️ Validation Failed</span>");
                out.println("        <h1>Unable to Process Registration</h1>");
                out.println("        <p style='color: #64748b;'>Please correct the following errors and submit again:</p>");
                out.println("        <ul class='error-list'>");
                for (String err : validationErrors) {
                    out.println("            <li>" + err + "</li>");
                }
                out.println("        </ul>");
            }

            out.println("        <a href='exp7_registration_form.html' class='btn-back'>&larr; Return to Registration Form</a>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests back to the form
        response.sendRedirect("exp7_registration_form.html");
    }

    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}

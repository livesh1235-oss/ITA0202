package com.it.controller;

import com.it.model.ServiceRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller Servlet for processing IT Service Requests.
 * Validates inputs, instantiates Model objects, and forwards to Views.
 */
@WebServlet(name = "ServiceRequestServlet", urlPatterns = {"/ServiceRequestServlet", "/submitRequest"})
public class ServiceRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Thread-safe counter for generating unique Request Numbers (e.g., SR-1001)
    private static final AtomicInteger requestCounter = new AtomicInteger(1001);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests directly to the form
        response.sendRedirect("serviceRequest.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Setting character encoding for standard text handling
        request.setCharacterEncoding("UTF-8");

        // Use local variables for all request-specific parameters (Thread-Safety)
        String employeeId = request.getParameter("employeeId");
        String employeeName = request.getParameter("employeeName");
        String department = request.getParameter("department");
        String problemCategory = request.getParameter("problemCategory");
        String problemDescription = request.getParameter("problemDescription");
        String priority = request.getParameter("priority");

        // Trim inputs where applicable
        if (employeeId != null) employeeId = employeeId.trim();
        if (employeeName != null) employeeName = employeeName.trim();
        if (department != null) department = department.trim();
        if (problemCategory != null) problemCategory = problemCategory.trim();
        if (problemDescription != null) problemDescription = problemDescription.trim();
        if (priority != null) priority = priority.trim();

        // Server-side validation of mandatory fields
        StringBuilder errorMessage = new StringBuilder();

        if (employeeId == null || employeeId.isEmpty()) {
            errorMessage.append("Employee ID is required.<br>");
        }
        if (employeeName == null || employeeName.isEmpty()) {
            errorMessage.append("Employee Name is required.<br>");
        }
        if (department == null || department.isEmpty()) {
            errorMessage.append("Department is required.<br>");
        }
        if (problemCategory == null || problemCategory.isEmpty()) {
            errorMessage.append("Problem Category must be selected.<br>");
        }
        if (problemDescription == null || problemDescription.isEmpty()) {
            errorMessage.append("Problem Description is required.<br>");
        }
        if (priority == null || priority.isEmpty()) {
            errorMessage.append("Priority must be selected.<br>");
        }

        // If validation errors exist, preserve entered data and forward back to form
        if (errorMessage.length() > 0) {
            request.setAttribute("errorMessage", errorMessage.toString());
            request.setAttribute("enteredEmployeeId", employeeId);
            request.setAttribute("enteredEmployeeName", employeeName);
            request.setAttribute("enteredDepartment", department);
            request.setAttribute("enteredCategory", problemCategory);
            request.setAttribute("enteredDescription", problemDescription);
            request.setAttribute("enteredPriority", priority);

            request.getRequestDispatcher("serviceRequest.jsp").forward(request, response);
            return;
        }

        // Create Model object using validated values
        ServiceRequest serviceRequest = new ServiceRequest(
                employeeId,
                employeeName,
                department,
                problemCategory,
                problemDescription,
                priority
        );

        // Generate unique service request number (Format: SR-1001)
        String requestNumber = "SR-" + requestCounter.getAndIncrement();

        // Store Model object and Request Number as request attributes
        request.setAttribute("serviceRequest", serviceRequest);
        request.setAttribute("requestNumber", requestNumber);

        // Forward to Result View (acknowledgement.jsp)
        request.getRequestDispatcher("acknowledgement.jsp").forward(request, response);
    }
}

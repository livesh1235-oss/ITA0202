<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.it.model.ServiceRequest" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Request Acknowledgement</title>
    <style>
        * {
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        body {
            background-color: #f0f2f5;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            max-width: 750px;
            width: 100%;
            padding: 35px 40px;
        }
        .success-banner {
            background-color: #ecfdf5;
            border-left: 5px solid #10b981;
            padding: 16px 20px;
            border-radius: 6px;
            margin-bottom: 25px;
            display: flex;
            align-items: center;
        }
        .success-icon {
            font-size: 28px;
            color: #10b981;
            margin-right: 15px;
        }
        .success-title {
            margin: 0 0 4px 0;
            color: #065f46;
            font-size: 18px;
            font-weight: 600;
        }
        .success-desc {
            margin: 0;
            color: #047857;
            font-size: 14px;
        }
        .header {
            margin-bottom: 20px;
        }
        .header h1 {
            margin: 0;
            color: #1a365d;
            font-size: 22px;
        }
        .details-card {
            border: 1px solid #e2e8f0;
            border-radius: 8px;
            overflow: hidden;
            margin-bottom: 25px;
        }
        .details-table {
            width: 100%;
            border-collapse: collapse;
        }
        .details-table th, 
        .details-table td {
            padding: 12px 18px;
            font-size: 14px;
            text-align: left;
            border-bottom: 1px solid #f1f5f9;
        }
        .details-table th {
            width: 32%;
            background-color: #f8fafc;
            color: #475569;
            font-weight: 600;
        }
        .details-table td {
            color: #1e293b;
        }
        .details-table tr:last-child th,
        .details-table tr:last-child td {
            border-bottom: none;
        }
        .badge-req {
            background-color: #1e40af;
            color: #ffffff;
            font-weight: 700;
            padding: 3px 10px;
            border-radius: 4px;
            font-size: 13px;
        }
        .priority-high {
            color: #b91c1c;
            font-weight: 600;
            background: #fee2e2;
            padding: 2px 8px;
            border-radius: 4px;
        }
        .priority-med {
            color: #b45309;
            font-weight: 600;
            background: #fef3c7;
            padding: 2px 8px;
            border-radius: 4px;
        }
        .priority-low {
            color: #15803d;
            font-weight: 600;
            background: #dcfce7;
            padding: 2px 8px;
            border-radius: 4px;
        }
        
        /* MVC Architecture Explanation Section */
        .mvc-section {
            background-color: #f8fafc;
            border-radius: 8px;
            border: 1px solid #cbd5e1;
            padding: 20px;
            margin-top: 25px;
        }
        .mvc-section h2 {
            margin-top: 0;
            margin-bottom: 12px;
            color: #0f172a;
            font-size: 16px;
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .mvc-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 12px;
            margin-bottom: 15px;
        }
        .mvc-box {
            background: #ffffff;
            padding: 12px;
            border-radius: 6px;
            border: 1px solid #e2e8f0;
        }
        .mvc-box h3 {
            margin: 0 0 6px 0;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        .mvc-box.model h3 { color: #0284c7; }
        .mvc-box.view h3 { color: #16a34a; }
        .mvc-box.controller h3 { color: #9333ea; }
        
        .mvc-box p {
            margin: 0;
            font-size: 13px;
            color: #334155;
        }
        .mvc-flow {
            font-size: 13px;
            color: #334155;
            line-height: 1.5;
            background: #ffffff;
            padding: 12px;
            border-radius: 6px;
            border: 1px solid #e2e8f0;
        }
        .mvc-flow ol {
            margin: 6px 0 0 0;
            padding-left: 20px;
        }
        .mvc-flow li {
            margin-bottom: 4px;
        }
        .btn-new {
            display: inline-block;
            text-align: center;
            background-color: #3b82f6;
            color: #ffffff;
            text-decoration: none;
            padding: 10px 18px;
            border-radius: 6px;
            font-weight: 600;
            font-size: 14px;
            margin-top: 20px;
            transition: background-color 0.2s;
        }
        .btn-new:hover {
            background-color: #2563eb;
        }
    </style>
</head>
<body>

<%
    // Retrieve Model and Request attributes from Controller Servlet
    ServiceRequest serviceRequest = (ServiceRequest) request.getAttribute("serviceRequest");
    String requestNumber = (String) request.getAttribute("requestNumber");

    // Fallback if accessed directly without controller dispatch
    if (serviceRequest == null) {
        response.sendRedirect("serviceRequest.jsp");
        return;
    }
%>

<div class="container">
    <!-- Success Message Banner -->
    <div class="success-banner">
        <div class="success-icon">✓</div>
        <div>
            <div class="success-title">Service Request Submitted Successfully!</div>
            <div class="success-desc">Your issue has been logged into the IT ticketing system and assigned to the support team.</div>
        </div>
    </div>

    <!-- Request Details -->
    <div class="header">
        <h1>Service Request Acknowledgement Details</h1>
    </div>

    <div class="details-card">
        <table class="details-table">
            <tr>
                <th>Service Request Number</th>
                <td><span class="badge-req"><%= requestNumber != null ? requestNumber : "N/A" %></span></td>
            </tr>
            <tr>
                <th>Employee ID</th>
                <td><strong><%= serviceRequest.getEmployeeId() %></strong></td>
            </tr>
            <tr>
                <th>Employee Name</th>
                <td><%= serviceRequest.getEmployeeName() %></td>
            </tr>
            <tr>
                <th>Department</th>
                <td><%= serviceRequest.getDepartment() %></td>
            </tr>
            <tr>
                <th>Problem Category</th>
                <td><%= serviceRequest.getProblemCategory() %></td>
            </tr>
            <tr>
                <th>Priority</th>
                <td>
                    <% 
                        String p = serviceRequest.getPriority();
                        String pClass = "priority-low";
                        if ("High".equalsIgnoreCase(p)) pClass = "priority-high";
                        else if ("Medium".equalsIgnoreCase(p)) pClass = "priority-med";
                    %>
                    <span class="<%= pClass %>"><%= p %></span>
                </td>
            </tr>
            <tr>
                <th>Problem Description</th>
                <td><%= serviceRequest.getProblemDescription() %></td>
            </tr>
        </table>
    </div>

    <!-- MVC Architecture Explanation & Component Mapping -->
    <div class="mvc-section">
        <h2>📌 MVC Architecture Flow & Component Mapping</h2>
        
        <div class="mvc-grid">
            <div class="mvc-box model">
                <h3>Model</h3>
                <p><strong>ServiceRequest.java</strong></p>
                <p style="font-size: 12px; color: #64748b; margin-top: 4px;">Encapsulates business data, fields, and getters/setters.</p>
            </div>
            <div class="mvc-box view">
                <h3>View</h3>
                <p><strong>serviceRequest.jsp & acknowledgement.jsp</strong></p>
                <p style="font-size: 12px; color: #64748b; margin-top: 4px;">Renders presentation, forms, and results to users.</p>
            </div>
            <div class="mvc-box controller">
                <h3>Controller</h3>
                <p><strong>ServiceRequestServlet.java</strong></p>
                <p style="font-size: 12px; color: #64748b; margin-top: 4px;">Handles requests, validates inputs, instantiates Model & forwards views.</p>
            </div>
        </div>

        <div class="mvc-flow">
            <strong>Lifecycle of the Request:</strong>
            <ol>
                <li><strong>Form Submission (View):</strong> The employee inputs data in <code>serviceRequest.jsp</code> and submits a POST request.</li>
                <li><strong>Validation & Processing (Controller):</strong> <code>ServiceRequestServlet</code> receives the parameters, validates mandatory fields, and handles errors.</li>
                <li><strong>Model Creation (Model):</strong> The Servlet instantiates the <code>ServiceRequest</code> Java bean and assigns a generated tracking ID (e.g. <code>SR-1001</code>).</li>
                <li><strong>Attribute Binding & Forwarding:</strong> The Servlet attaches the Model and Request ID as request attributes via <code>request.setAttribute()</code> and forwards via <code>RequestDispatcher</code>.</li>
                <li><strong>Final Acknowledgement (View):</strong> <code>acknowledgement.jsp</code> extracts and displays the processed Model data and confirmation to the employee.</li>
            </ol>
        </div>
    </div>

    <a href="serviceRequest.jsp" class="btn-new">← Submit Another Request</a>
</div>

</body>
</html>

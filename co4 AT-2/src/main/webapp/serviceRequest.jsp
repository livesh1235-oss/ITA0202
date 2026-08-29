<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IT Service Request Form</title>
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
            max-width: 650px;
            width: 100%;
            padding: 35px 40px;
        }
        .header {
            text-align: center;
            margin-bottom: 25px;
            border-bottom: 2px solid #e9ecef;
            padding-bottom: 15px;
        }
        .header h1 {
            margin: 0 0 8px;
            color: #1a365d;
            font-size: 24px;
        }
        .header p {
            margin: 0;
            color: #64748b;
            font-size: 14px;
        }
        .error-box {
            background-color: #fee2e2;
            color: #991b1b;
            padding: 12px 16px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 14px;
            border-left: 4px solid #ef4444;
        }
        .form-group {
            margin-bottom: 18px;
        }
        .form-row {
            display: flex;
            gap: 15px;
        }
        .form-row .form-group {
            flex: 1;
        }
        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
            color: #334155;
            font-size: 14px;
        }
        .required {
            color: #dc2626;
        }
        input[type="text"],
        select,
        textarea {
            width: 100%;
            padding: 10px 14px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            font-size: 14px;
            color: #1e293b;
            transition: border-color 0.2s, box-shadow 0.2s;
        }
        input[type="text"]:focus,
        select:focus,
        textarea:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        .radio-group {
            display: flex;
            gap: 20px;
            margin-top: 8px;
        }
        .radio-label {
            display: flex;
            align-items: center;
            font-weight: normal;
            cursor: pointer;
            font-size: 14px;
            color: #475569;
        }
        .radio-label input {
            margin-right: 6px;
            accent-color: #2563eb;
        }
        .btn-submit {
            background-color: #2563eb;
            color: #ffffff;
            border: none;
            padding: 12px 20px;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.2s, transform 0.1s;
            margin-top: 10px;
        }
        .btn-submit:hover {
            background-color: #1d4ed8;
        }
        .btn-submit:active {
            transform: scale(0.99);
        }
        .badge {
            display: inline-block;
            background: #e0f2fe;
            color: #0369a1;
            font-size: 12px;
            padding: 2px 8px;
            border-radius: 4px;
            font-weight: bold;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <span class="badge">MVC Architecture - View</span>
        <h1>IT Service Request Portal</h1>
        <p>Submit your hardware, software, network, or account support complaints</p>
    </div>

    <% 
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
        <div class="error-box">
            <%= errorMessage %>
        </div>
    <% } %>

    <%
        // Retain values if validation failed
        String empId = request.getAttribute("enteredEmployeeId") != null ? (String) request.getAttribute("enteredEmployeeId") : "";
        String empName = request.getAttribute("enteredEmployeeName") != null ? (String) request.getAttribute("enteredEmployeeName") : "";
        String dept = request.getAttribute("enteredDepartment") != null ? (String) request.getAttribute("enteredDepartment") : "";
        String cat = request.getAttribute("enteredCategory") != null ? (String) request.getAttribute("enteredCategory") : "";
        String desc = request.getAttribute("enteredDescription") != null ? (String) request.getAttribute("enteredDescription") : "";
        String priority = request.getAttribute("enteredPriority") != null ? (String) request.getAttribute("enteredPriority") : "Medium";
    %>

    <form action="ServiceRequestServlet" method="POST">
        <!-- Employee ID & Employee Name -->
        <div class="form-row">
            <div class="form-group">
                <label for="employeeId">Employee ID <span class="required">*</span></label>
                <input type="text" id="employeeId" name="employeeId" value="<%= empId %>" placeholder="e.g. EMP-4091" required>
            </div>
            <div class="form-group">
                <label for="employeeName">Employee Name <span class="required">*</span></label>
                <input type="text" id="employeeName" name="employeeName" value="<%= empName %>" placeholder="e.g. John Doe" required>
            </div>
        </div>

        <!-- Department -->
        <div class="form-group">
            <label for="department">Department <span class="required">*</span></label>
            <select id="department" name="department" required>
                <option value="">-- Select Department --</option>
                <option value="Engineering" <%= "Engineering".equals(dept) ? "selected" : "" %>>Engineering / Software</option>
                <option value="Human Resources" <%= "Human Resources".equals(dept) ? "selected" : "" %>>Human Resources (HR)</option>
                <option value="Finance" <%= "Finance".equals(dept) ? "selected" : "" %>>Finance & Accounts</option>
                <option value="Marketing" <%= "Marketing".equals(dept) ? "selected" : "" %>>Marketing & Sales</option>
                <option value="Operations" <%= "Operations".equals(dept) ? "selected" : "" %>>Operations & Logistics</option>
                <option value="Quality Assurance" <%= "Quality Assurance".equals(dept) ? "selected" : "" %>>Quality Assurance (QA)</option>
            </select>
        </div>

        <!-- Problem Category -->
        <div class="form-group">
            <label for="problemCategory">Problem Category <span class="required">*</span></label>
            <select id="problemCategory" name="problemCategory" required>
                <option value="">-- Select Category --</option>
                <option value="Network" <%= "Network".equals(cat) ? "selected" : "" %>>Network Connectivity</option>
                <option value="Software" <%= "Software".equals(cat) ? "selected" : "" %>>Software Installation / Issues</option>
                <option value="Hardware" <%= "Hardware".equals(cat) ? "selected" : "" %>>Hardware Malfunction</option>
                <option value="Account" <%= "Account".equals(cat) ? "selected" : "" %>>Account Access / Credentials</option>
                <option value="Other" <%= "Other".equals(cat) ? "selected" : "" %>>Other / Application Errors</option>
            </select>
        </div>

        <!-- Priority -->
        <div class="form-group">
            <label>Priority Level <span class="required">*</span></label>
            <div class="radio-group">
                <label class="radio-label">
                    <input type="radio" name="priority" value="Low" <%= "Low".equals(priority) ? "checked" : "" %>> Low
                </label>
                <label class="radio-label">
                    <input type="radio" name="priority" value="Medium" <%= ("Medium".equals(priority) || priority.isEmpty()) ? "checked" : "" %>> Medium
                </label>
                <label class="radio-label">
                    <input type="radio" name="priority" value="High" <%= "High".equals(priority) ? "checked" : "" %>> High
                </label>
            </div>
        </div>

        <!-- Problem Description -->
        <div class="form-group">
            <label for="problemDescription">Problem Description <span class="required">*</span></label>
            <textarea id="problemDescription" name="problemDescription" rows="4" placeholder="Describe the issue in detail..." required><%= desc %></textarea>
        </div>

        <!-- Submit Button -->
        <button type="submit" class="btn-submit">Submit Service Request</button>
    </form>
</div>

</body>
</html>

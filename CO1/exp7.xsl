<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <html lang="en">
        <head>
            <title>Experiment 7 - Student Data XML Document</title>
            <style>
                body {
                    font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
                    background-color: #fdf4ff;
                    color: #0f172a;
                    line-height: 1.6;
                    margin: 0;
                    padding: 20px;
                }
                .container {
                    max-width: 950px;
                    margin: 0 auto;
                    background: #ffffff;
                    padding: 40px;
                    border-radius: 12px;
                    box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1);
                    border: 1px solid #f0abfc;
                }
                .header-badge {
                    display: inline-block;
                    background: #fae8ff;
                    color: #a21caf;
                    padding: 4px 12px;
                    border-radius: 9999px;
                    font-size: 0.85rem;
                    font-weight: 600;
                    margin-bottom: 12px;
                    text-transform: uppercase;
                }
                .back-nav {
                    margin-bottom: 20px;
                }
                .back-nav a {
                    color: #c026d3;
                    text-decoration: none;
                    font-weight: 500;
                }
                table.xml-table {
                    width: 100%;
                    border-collapse: collapse;
                    margin: 20px 0;
                }
                table.xml-table th {
                    background: #c026d3;
                    color: white;
                    padding: 12px 10px;
                    text-align: left;
                }
                table.xml-table td {
                    padding: 10px;
                    border: 1px solid #f5d0fe;
                }
                table.xml-table tr:nth-child(even) {
                    background-color: #fdf4ff;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="back-nav">
                    <a href="index.html">&#8592; Back to Lab Experiments Portal</a>
                </div>
                <span class="header-badge">Experiment 07</span>
                <h1 style="margin-top:0; color:#0f172a;">XML Document for Student Data</h1>
                <p><strong>Rendered via XSLT Transformation:</strong> Below is the structured student database records parsed from XML:</p>

                <table class="xml-table">
                    <thead>
                        <tr>
                            <th>Student ID</th>
                            <th>Roll Number</th>
                            <th>Full Name</th>
                            <th>Department</th>
                            <th>Year</th>
                            <th>Email Address</th>
                            <th>Mobile Number</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="students/student">
                            <tr>
                                <td><xsl:value-of select="@id"/></td>
                                <td><strong><xsl:value-of select="rollno"/></strong></td>
                                <td><xsl:value-of select="name"/></td>
                                <td><xsl:value-of select="department"/></td>
                                <td><xsl:value-of select="year"/></td>
                                <td><xsl:value-of select="email"/></td>
                                <td><xsl:value-of select="mobile"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </div>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

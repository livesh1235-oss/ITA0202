<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" doctype-system="about:blank" indent="yes" encoding="UTF-8"/>

    <xsl:template match="/">
        <html lang="en">
        <head>
            <meta charset="UTF-8"/>
            <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
            <title>High Enrollment Courses | XSLT Transformation</title>
            <style>
                body {
                    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
                    background-color: #f8fafc;
                    color: #0f172a;
                    padding: 2.5rem 1rem;
                    margin: 0;
                }
                .container {
                    max-width: 900px;
                    margin: 0 auto;
                    background: #ffffff;
                    border: 1px solid #e2e8f0;
                    border-radius: 12px;
                    padding: 2.5rem;
                    box-shadow: 0 10px 25px -5px rgba(0, 0, 0, 0.05);
                }
                .header-badge {
                    display: inline-block;
                    background: #eff6ff;
                    color: #2563eb;
                    padding: 4px 12px;
                    border-radius: 20px;
                    font-size: 0.85rem;
                    font-weight: 700;
                    text-transform: uppercase;
                    margin-bottom: 0.75rem;
                }
                h2 {
                    font-size: 1.75rem;
                    font-weight: 800;
                    color: #1e293b;
                    margin: 0 0 0.5rem 0;
                }
                p.description {
                    color: #64748b;
                    font-size: 0.95rem;
                    margin: 0 0 2rem 0;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    font-size: 0.95rem;
                    margin-bottom: 1.5rem;
                }
                th {
                    background-color: #1e293b;
                    color: #ffffff;
                    text-align: left;
                    padding: 0.85rem 1rem;
                    font-weight: 600;
                    letter-spacing: 0.02em;
                }
                th:first-child { border-top-left-radius: 8px; }
                th:last-child { border-top-right-radius: 8px; }
                td {
                    padding: 0.85rem 1rem;
                    border-bottom: 1px solid #e2e8f0;
                    color: #334155;
                }
                tr:nth-child(even) {
                    background-color: #f8fafc;
                }
                tr:hover {
                    background-color: #f1f5f9;
                }
                .badge-theory {
                    background: #dbeafe;
                    color: #1d4ed8;
                    padding: 3px 8px;
                    border-radius: 6px;
                    font-size: 0.8rem;
                    font-weight: 600;
                }
                .badge-practical {
                    background: #fef3c7;
                    color: #b45309;
                    padding: 3px 8px;
                    border-radius: 6px;
                    font-size: 0.8rem;
                    font-weight: 600;
                }
                .students-count {
                    font-weight: 700;
                    color: #059669;
                    font-family: monospace;
                    font-size: 1.05rem;
                }
                .footer-summary {
                    background: #f8fafc;
                    border: 1px solid #e2e8f0;
                    border-radius: 8px;
                    padding: 1rem 1.25rem;
                    font-size: 0.85rem;
                    color: #64748b;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <span class="header-badge">Unit IV - XSLT Transformation Output</span>
                <h2>High Enrollment Courses</h2>
                <p class="description">Courses with enrollment greater than 40 students, sorted in descending order of student count.</p>

                <table>
                    <thead>
                        <tr>
                            <th>Course Code</th>
                            <th>Course Name</th>
                            <th>Faculty</th>
                            <th>Students</th>
                            <th>Credits</th>
                            <th>Type</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- XPath condition: select courses having students > 40 -->
                        <xsl:for-each select="/courses/course[students > 40]">
                            <!-- Sort in descending order of student enrollment -->
                            <xsl:sort select="students" data-type="number" order="descending"/>
                            <tr>
                                <td><strong><xsl:value-of select="code"/></strong></td>
                                <td><xsl:value-of select="name"/></td>
                                <td><xsl:value-of select="faculty"/></td>
                                <td class="students-count"><xsl:value-of select="students"/></td>
                                <td><xsl:value-of select="credits"/></td>
                                <td>
                                    <xsl:choose>
                                        <xsl:when test="type = 'Theory'">
                                            <span class="badge-theory"><xsl:value-of select="type"/></span>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <span class="badge-practical"><xsl:value-of select="type"/></span>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>

                <div class="footer-summary">
                    <strong>XSLT Logic Applied:</strong> 
                    Condition: <code>/courses/course[students &gt; 40]</code> | 
                    Sorting: <code>&lt;xsl:sort select="students" data-type="number" order="descending"/&gt;</code>
                </div>
            </div>
        </body>
        </html>
    </xsl:template>
</xsl:stylesheet>

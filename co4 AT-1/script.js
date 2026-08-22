/**
 * Unit IV: Representing Web Data
 * Client-Side XPath Evaluator & XSLT Transformation Processor
 */

// Embedded XML String for instant parsing
const xmlString = `<?xml version="1.0" encoding="UTF-8"?>
<courses>
    <course id="C101">
        <code>WEB301</code>
        <name>Web Technology</name>
        <faculty>Dr. Arun</faculty>
        <students>58</students>
        <credits>4</credits>
        <type>Theory</type>
    </course>
    <course id="C102">
        <code>AI302</code>
        <name>Artificial Intelligence</name>
        <faculty>Dr. Meena</faculty>
        <students>72</students>
        <credits>4</credits>
        <type>Theory</type>
    </course>
    <course id="C103">
        <code>WEB303</code>
        <name>Web Technology Laboratory</name>
        <faculty>Dr. Ravi</faculty>
        <students>36</students>
        <credits>2</credits>
        <type>Practical</type>
    </course>
    <course id="C104">
        <code>ML304</code>
        <name>Machine Learning</name>
        <faculty>Dr. Priya</faculty>
        <students>64</students>
        <credits>4</credits>
        <type>Theory</type>
    </course>
    <course id="C105">
        <code>DB305</code>
        <name>Database Systems</name>
        <faculty>Dr. Kumar</faculty>
        <students>42</students>
        <credits>3</credits>
        <type>Theory</type>
    </course>
</courses>`;

// Embedded XSLT String for instant in-browser transformation
const xslString = `<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <div>
            <h2 style="font-size: 1.5rem; color: #1e293b; margin-bottom: 0.5rem;">High Enrollment Courses</h2>
            <p style="color: #64748b; font-size: 0.9rem; margin-bottom: 1.25rem;">Courses with enrollment &gt; 40, sorted in descending order of students.</p>
            <table style="width: 100%; border-collapse: collapse; font-family: sans-serif; font-size: 0.9rem;">
                <thead>
                    <tr style="background: #1e293b; color: white;">
                        <th style="padding: 10px; text-align: left;">Course Code</th>
                        <th style="padding: 10px; text-align: left;">Course Name</th>
                        <th style="padding: 10px; text-align: left;">Faculty</th>
                        <th style="padding: 10px; text-align: left;">Students</th>
                        <th style="padding: 10px; text-align: left;">Credits</th>
                        <th style="padding: 10px; text-align: left;">Type</th>
                    </tr>
                </thead>
                <tbody>
                    <xsl:for-each select="/courses/course[students > 40]">
                        <xsl:sort select="students" data-type="number" order="descending"/>
                        <tr style="border-bottom: 1px solid #e2e8f0;">
                            <td style="padding: 10px; font-weight: bold;"><xsl:value-of select="code"/></td>
                            <td style="padding: 10px;"><xsl:value-of select="name"/></td>
                            <td style="padding: 10px;"><xsl:value-of select="faculty"/></td>
                            <td style="padding: 10px; font-weight: bold; color: #059669;"><xsl:value-of select="students"/></td>
                            <td style="padding: 10px;"><xsl:value-of select="credits"/></td>
                            <td style="padding: 10px;"><xsl:value-of select="type"/></td>
                        </tr>
                    </xsl:for-each>
                </tbody>
            </table>
        </div>
    </xsl:template>
</xsl:stylesheet>`;

let xmlDoc = null;
let xslDoc = null;

// Parse XML & XSL Documents on startup
function initDocuments() {
    const parser = new DOMParser();
    xmlDoc = parser.parseFromString(xmlString, 'text/xml');
    xslDoc = parser.parseFromString(xslString, 'text/xml');
    renderXSLT();
    evaluateXPath();
}

// Tab navigation handler
function switchTab(tabId) {
    document.querySelectorAll('.tab-pane').forEach(el => el.classList.remove('active'));
    document.querySelectorAll('.tab-btn').forEach(el => el.classList.remove('active'));

    const targetPane = document.getElementById(tabId);
    if (targetPane) {
        targetPane.classList.add('active');
    }

    // Set active button
    const buttons = document.querySelectorAll('.tab-btn');
    buttons.forEach(btn => {
        if (btn.getAttribute('onclick').includes(tabId)) {
            btn.classList.add('active');
        }
    });
}

// Preset XPath query helper
function setXPath(expr) {
    document.getElementById('xpathInput').value = expr;
    evaluateXPath();
}

// Evaluate XPath expression using DOM Level 3 XPath (document.evaluate)
function evaluateXPath() {
    if (!xmlDoc) return;

    const expression = document.getElementById('xpathInput').value.trim();
    const outputBox = document.getElementById('xpathOutputBox');
    const nodeCountSpan = document.getElementById('nodeCount');
    outputBox.innerHTML = '';

    if (!expression) {
        outputBox.innerHTML = '<div style="color: #94a3b8;">Please enter an XPath expression.</div>';
        nodeCountSpan.textContent = '0';
        return;
    }

    try {
        const result = xmlDoc.evaluate(
            expression,
            xmlDoc,
            null,
            XPathResult.ANY_TYPE,
            null
        );

        let nodes = [];
        let count = 0;

        if (result.resultType === XPathResult.NUMBER_TYPE) {
            outputBox.innerHTML = `<div class="result-node"><strong>Numeric Result:</strong> ${result.numberValue}</div>`;
            nodeCountSpan.textContent = '1';
            return;
        } else if (result.resultType === XPathResult.STRING_TYPE) {
            outputBox.innerHTML = `<div class="result-node"><strong>String Result:</strong> "${result.stringValue}"</div>`;
            nodeCountSpan.textContent = '1';
            return;
        } else if (result.resultType === XPathResult.BOOLEAN_TYPE) {
            outputBox.innerHTML = `<div class="result-node"><strong>Boolean Result:</strong> ${result.booleanValue}</div>`;
            nodeCountSpan.textContent = '1';
            return;
        }

        // Node Set Iterator
        let node = result.iterateNext();
        while (node) {
            count++;
            let nodeOutput = '';
            
            if (node.nodeType === Node.ELEMENT_NODE) {
                const serializer = new XMLSerializer();
                const xmlSnippet = serializer.serializeToString(node);
                nodeOutput = escapeHtml(xmlSnippet);
            } else if (node.nodeType === Node.ATTRIBUTE_NODE) {
                nodeOutput = `@${node.name}="${node.value}"`;
            } else if (node.nodeType === Node.TEXT_NODE) {
                nodeOutput = `Text: "${node.nodeValue.trim()}"`;
            } else {
                nodeOutput = node.textContent;
            }

            const itemDiv = document.createElement('div');
            itemDiv.className = 'result-node';
            itemDiv.innerHTML = `<span style="color: #60a5fa;">[Match ${count}]</span> ${nodeOutput}`;
            outputBox.appendChild(itemDiv);

            node = result.iterateNext();
        }

        nodeCountSpan.textContent = count;

        if (count === 0) {
            outputBox.innerHTML = '<div style="color: #f59e0b;">No matching nodes found for this XPath expression.</div>';
        }
    } catch (err) {
        outputBox.innerHTML = `<div style="color: #ef4444;"><strong>XPath Syntax Error:</strong> ${err.message}</div>`;
        nodeCountSpan.textContent = '0';
    }
}

// Live XSLT in-browser transformation using XSLTProcessor
function renderXSLT() {
    const target = document.getElementById('xsltRenderOutput');
    if (!target || !window.XSLTProcessor) return;

    try {
        const xsltProcessor = new XSLTProcessor();
        xsltProcessor.importStylesheet(xslDoc);
        const resultDocument = xsltProcessor.transformToFragment(xmlDoc, document);
        target.innerHTML = '';
        target.appendChild(resultDocument);
    } catch (e) {
        target.innerHTML = `<div style="color: #ef4444;">Error rendering XSLT: ${e.message}</div>`;
    }
}

function escapeHtml(str) {
    return str.replace(/&/g, '&amp;')
              .replace(/</g, '&lt;')
              .replace(/>/g, '&gt;')
              .replace(/"/g, '&quot;');
}

window.addEventListener('DOMContentLoaded', initDocuments);

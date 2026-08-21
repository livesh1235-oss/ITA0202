/**
 * Experiment 2: Dynamic Student Registration List Using DOM
 * Demonstrates:
 * - document.createElement()
 * - node.appendChild()
 * - node.remove() / node.removeChild()
 * - node.parentElement
 * - node.children
 */

// Form elements
const studentForm = document.getElementById('studentForm');
const studentNameInput = document.getElementById('studentName');
const regNumberInput = document.getElementById('regNumber');
const departmentSelect = document.getElementById('department');
const btnPrefill = document.getElementById('btnPrefill');
const btnClearAll = document.getElementById('btnClearAll');

// Table and DOM containers
const studentTable = document.getElementById('studentTable');
const studentTableBody = document.getElementById('studentTableBody');
const emptyState = document.getElementById('emptyState');
const totalStudentsCount = document.getElementById('totalStudentsCount');
const activeDeptsCount = document.getElementById('activeDeptsCount');
const domTreeInfo = document.getElementById('domTreeInfo');

let recordIndex = 1;

// Sample dataset for quick testing
const sampleData = [
    { name: 'Marcus Chen', reg: '21CS108', dept: 'Computer Science & Engineering' },
    { name: 'Priya Sharma', reg: '21IT142', dept: 'Information Technology' },
    { name: 'David Miller', reg: '21AI033', dept: 'Artificial Intelligence & Data Science' }
];
let samplePointer = 0;

// Updates table visibility, counters, and inspector using .children property
function updateTableState() {
    // Accessing all child row nodes via .children
    const rowCount = studentTableBody.children.length;
    
    totalStudentsCount.textContent = rowCount;
    btnClearAll.disabled = (rowCount === 0);

    if (rowCount === 0) {
        studentTable.style.display = 'none';
        emptyState.style.display = 'block';
        activeDeptsCount.textContent = '0';
    } else {
        studentTable.style.display = 'table';
        emptyState.style.display = 'none';

        // Calculate unique departments using DOM children traversal
        const depts = new Set();
        for (let i = 0; i < studentTableBody.children.length; i++) {
            const row = studentTableBody.children[i];
            const deptText = row.children[3].textContent.trim();
            depts.add(deptText);
        }
        activeDeptsCount.textContent = depts.size;
    }

    // Refresh row numbering (#) using children iteration
    for (let i = 0; i < studentTableBody.children.length; i++) {
        studentTableBody.children[i].children[0].textContent = i + 1;
    }

    // Update DOM Tree Inspector text
    domTreeInfo.innerHTML = `<code>studentTableBody.children.length: ${rowCount} | Parent Element: &lt;${studentTableBody.parentElement.tagName.toLowerCase()}&gt;</code>`;
}

// 1. Add Student record dynamically using createElement() and appendChild()
function addStudentRecord(name, regNo, dept) {
    // Create Table Row: <tr>
    const tr = document.createElement('tr');
    
    // Create Table Cells: <td>
    const tdIndex = document.createElement('td');
    tdIndex.textContent = studentTableBody.children.length + 1;

    const tdName = document.createElement('td');
    tdName.innerHTML = `<strong>${name}</strong>`;

    const tdReg = document.createElement('td');
    tdReg.textContent = regNo;

    const tdDept = document.createElement('td');
    const badge = document.createElement('span');
    badge.className = 'dept-badge';
    badge.textContent = dept;
    tdDept.appendChild(badge); // append child badge to tdDept

    const tdAction = document.createElement('td');
    tdAction.style.textAlign = 'right';

    // Create Delete Button
    const btnRemove = document.createElement('button');
    btnRemove.className = 'btn-delete';
    btnRemove.textContent = 'Remove';

    // 2. Demonstrate parentElement & remove() upon button click
    btnRemove.addEventListener('click', function(event) {
        // Find the target row using parentElement traversal
        const cell = this.parentElement;         // <td>
        const targetRow = cell.parentElement;     // <tr>
        
        // Remove the row element from DOM
        targetRow.remove();

        // Update state and refresh indices
        updateTableState();
    });

    tdAction.appendChild(btnRemove);

    // Append all cells to the row: <tr>
    tr.appendChild(tdIndex);
    tr.appendChild(tdName);
    tr.appendChild(tdReg);
    tr.appendChild(tdDept);
    tr.appendChild(tdAction);

    // Append new row to table body: <tbody>
    studentTableBody.appendChild(tr);

    // Update UI state
    updateTableState();
}

// Handle Form Submission
studentForm.addEventListener('submit', function(e) {
    e.preventDefault();

    const name = studentNameInput.value.trim();
    const reg = regNumberInput.value.trim();
    const dept = departmentSelect.value;

    if (!name || !reg || !dept) {
        alert('Please fill out all required fields.');
        return;
    }

    addStudentRecord(name, reg, dept);

    // Reset input fields
    studentForm.reset();
    studentNameInput.focus();
});

// 3. Clear All Option using DOM children loop / innerHTML clearing
btnClearAll.addEventListener('click', function() {
    if (confirm('Are you sure you want to clear all student records from the DOM?')) {
        // Alternatively demonstrated: removing children iteratively or resetting innerHTML
        while (studentTableBody.firstChild) {
            studentTableBody.removeChild(studentTableBody.firstChild);
        }
        updateTableState();
    }
});

// Prefill sample data helper
btnPrefill.addEventListener('click', function() {
    const item = sampleData[samplePointer % sampleData.length];
    samplePointer++;
    studentNameInput.value = item.name;
    regNumberInput.value = item.reg;
    departmentSelect.value = item.dept;
});

// Initialize on page load
updateTableState();

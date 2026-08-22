/**
 * CO3 - Assessment 1 - Question 1
 * DOM-Based Interactive Student Registration Preview
 * 
 * Concepts Assessed:
 * - DOM element selection via getElementById() & querySelector()
 * - Dynamic node generation via createElement() and textContent
 * - Event handling via addEventListener()
 * - Visual distinction using classList
 * - Element removal via DOM remove()
 */

// 1. Element Selection using getElementById & querySelector
const studentNameInput = document.getElementById('studentName');
const regNumberInput = document.querySelector('#regNumber');
const departmentSelect = document.getElementById('department');
const yearOfStudySelect = document.querySelector('#yearOfStudy');

const btnDisplayProfile = document.getElementById('btnDisplayProfile');
const btnClearForm = document.getElementById('btnClearForm');
const profileContainer = document.getElementById('profileContainer');
const emptyState = document.getElementById('emptyState');
const auditLog = document.getElementById('auditLog');

// Helper to log DOM operations
function logAudit(actionText) {
    const timestamp = new Date().toLocaleTimeString();
    auditLog.innerHTML = `<code>[${timestamp}] ${actionText}</code>`;
}

// 2. Function to Generate & Display the Student Profile in the DOM
function displayStudentProfile() {
    // Read form values using getElementById / querySelector value properties
    const name = studentNameInput.value.trim();
    const regNo = regNumberInput.value.trim();
    const dept = departmentSelect.value;
    const year = yearOfStudySelect.value;

    // Input Validation
    if (!name || !regNo || !dept || !year) {
        alert('Please fill out all required fields (Name, Register Number, Department, and Year of Study).');
        return;
    }

    // Clear existing profile or empty state
    profileContainer.innerHTML = '';

    // Create Main Profile Card Container using createElement()
    const card = document.createElement('div');
    // Visually distinguish using classList
    card.classList.add('student-profile-card');

    // Create Top Row: Avatar Initials + Remove Button
    const topRow = document.createElement('div');
    topRow.classList.add('profile-top-row');

    // Avatar Circle with initial
    const avatarBadge = document.createElement('div');
    avatarBadge.classList.add('profile-avatar-badge');
    avatarBadge.textContent = name.charAt(0).toUpperCase();

    // Remove Profile Action Button using createElement & addEventListener
    const btnRemove = document.createElement('button');
    btnRemove.classList.add('btn-remove-profile');
    btnRemove.textContent = '✕ Remove Profile';
    
    // Attach modern event listener to remove the profile card
    btnRemove.addEventListener('click', function() {
        // Remove the profile card from the DOM
        card.remove();
        // Restore empty state placeholder
        profileContainer.appendChild(emptyState);
        emptyState.style.display = 'block';
        logAudit('Profile removed via <code>card.remove()</code>.');
    });

    topRow.appendChild(avatarBadge);
    topRow.appendChild(btnRemove);

    // Create Student Name Heading
    const nameHeading = document.createElement('h3');
    nameHeading.classList.add('profile-name');
    nameHeading.textContent = name;

    // Create Register Number Badge
    const regParagraph = document.createElement('p');
    regParagraph.classList.add('profile-reg');
    regParagraph.textContent = `Reg No: ${regNo}`;

    // Create Details Grid
    const detailsGrid = document.createElement('div');
    detailsGrid.classList.add('profile-details-grid');

    // Detail Item: Department
    const deptItem = document.createElement('div');
    deptItem.classList.add('detail-item');
    const deptLabel = document.createElement('span');
    deptLabel.textContent = 'Department';
    const deptValue = document.createElement('strong');
    deptValue.textContent = dept;
    deptItem.appendChild(deptLabel);
    deptItem.appendChild(deptValue);

    // Detail Item: Year of Study
    const yearItem = document.createElement('div');
    yearItem.classList.add('detail-item');
    const yearLabel = document.createElement('span');
    yearLabel.textContent = 'Year of Study';
    const yearValue = document.createElement('strong');
    yearValue.textContent = year;
    yearItem.appendChild(yearLabel);
    yearItem.appendChild(yearValue);

    // Append items to grid
    detailsGrid.appendChild(deptItem);
    detailsGrid.appendChild(yearItem);

    // Assemble the complete profile card
    card.appendChild(topRow);
    card.appendChild(nameHeading);
    card.appendChild(regParagraph);
    card.appendChild(detailsGrid);

    // Inject into DOM container using appendChild
    profileContainer.appendChild(card);

    logAudit(`Generated profile for <strong>${name} (${regNo})</strong> via <code>createElement()</code> and <code>classList.add()</code>.`);
}

// 3. Attach Event Listeners using addEventListener()
btnDisplayProfile.addEventListener('click', displayStudentProfile);

btnClearForm.addEventListener('click', function() {
    studentNameInput.value = '';
    regNumberInput.value = '';
    departmentSelect.selectedIndex = 0;
    yearOfStudySelect.selectedIndex = 0;
    logAudit('Form inputs reset.');
});

// Initialize live preview on startup with prefilled values
window.addEventListener('DOMContentLoaded', displayStudentProfile);

/**
 * Experiment 1: DOM-Based Student Profile Manipulation
 * Demonstrates:
 * - document.getElementById()
 * - document.querySelector()
 * - element.textContent
 * - element.style
 * - element.classList (add, remove, toggle, contains)
 * - element.setAttribute() / getAttribute()
 */

// 1. Selecting DOM Elements using getElementById & querySelector
const profileCard = document.getElementById('profileCard');
const studentAvatar = document.getElementById('studentAvatar');
const statusBadge = document.getElementById('statusBadge');
const hiddenPlaceholder = document.getElementById('hiddenPlaceholder');
const domLog = document.getElementById('domLog');

// querySelector examples
const studentHeading = document.querySelector('#studentHeading');
const studentBio = document.querySelector('.student-bio');
const nameInput = document.querySelector('#nameInput');

// Buttons
const btnChangeHeading = document.getElementById('btnChangeHeading');
const btnToggleTheme = document.getElementById('btnToggleTheme');
const btnToggleBorder = document.getElementById('btnToggleBorder');
const btnChangeAvatar = document.getElementById('btnChangeAvatar');
const btnToggleStatus = document.getElementById('btnToggleStatus');
const btnToggleVisibility = document.getElementById('btnToggleVisibility');
const btnReset = document.getElementById('btnReset');
const btnClearLog = document.getElementById('btnClearLog');

// Helper function to log DOM actions to the on-screen console
function logAction(codeSnippet) {
    const logItem = document.createElement('p');
    logItem.className = 'log-item executed';
    const timestamp = new Date().toLocaleTimeString();
    logItem.textContent = `[${timestamp}] ${codeSnippet}`;
    domLog.appendChild(logItem);
    domLog.scrollTop = domLog.scrollHeight;
}

// 1. Modify Heading via textContent & querySelector
btnChangeHeading.addEventListener('click', () => {
    const newName = nameInput.value.trim();
    if (newName) {
        studentHeading.textContent = newName;
        logAction(`document.querySelector('#studentHeading').textContent = "${newName}";`);
    } else {
        alert('Please enter a valid student name.');
    }
});

// 2. Change Text Color using element.style
function changeTextColor(color) {
    studentHeading.style.color = color;
    studentBio.style.color = color;
    logAction(`element.style.color = "${color}";`);
}

// 3. Change Background Color using element.style.backgroundColor
function changeBgColor(color) {
    profileCard.style.backgroundColor = color;
    logAction(`profileCard.style.backgroundColor = "${color}";`);
}

// 4. Toggle Card Theme and Border using classList
btnToggleTheme.addEventListener('click', () => {
    profileCard.classList.toggle('dark-theme');
    const isDark = profileCard.classList.contains('dark-theme');
    btnToggleTheme.textContent = isDark ? 'Toggle Light Card' : 'Toggle Dark Card';
    logAction(`profileCard.classList.toggle('dark-theme'); // active: ${isDark}`);
});

btnToggleBorder.addEventListener('click', () => {
    profileCard.classList.toggle('accent-border');
    const hasBorder = profileCard.classList.contains('accent-border');
    logAction(`profileCard.classList.toggle('accent-border'); // active: ${hasBorder}`);
});

// 5. Update Attributes using setAttribute()
const avatarList = [
    'https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=200&auto=format&fit=crop&q=80',
    'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=200&auto=format&fit=crop&q=80',
    'https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=200&auto=format&fit=crop&q=80'
];
let currentAvatarIndex = 0;

btnChangeAvatar.addEventListener('click', () => {
    currentAvatarIndex = (currentAvatarIndex + 1) % avatarList.length;
    const newAvatarUrl = avatarList[currentAvatarIndex];
    studentAvatar.setAttribute('src', newAvatarUrl);
    studentAvatar.setAttribute('alt', `Student Avatar ${currentAvatarIndex + 1}`);
    logAction(`studentAvatar.setAttribute('src', '${newAvatarUrl.substring(0, 35)}...');`);
});

btnToggleStatus.addEventListener('click', () => {
    const currentStatus = profileCard.getAttribute('data-status');
    if (currentStatus === 'active') {
        profileCard.setAttribute('data-status', 'alumni');
        statusBadge.textContent = 'Alumni';
        statusBadge.className = 'badge badge-alumni';
        logAction(`profileCard.setAttribute('data-status', 'alumni');`);
    } else {
        profileCard.setAttribute('data-status', 'active');
        statusBadge.textContent = 'Active';
        statusBadge.className = 'badge badge-active';
        logAction(`profileCard.setAttribute('data-status', 'active');`);
    }
});

// 6. Show / Hide Profile using style.display
let isVisible = true;
btnToggleVisibility.addEventListener('click', () => {
    isVisible = !isVisible;
    if (isVisible) {
        profileCard.style.display = 'block';
        hiddenPlaceholder.style.display = 'none';
        btnToggleVisibility.textContent = 'Hide Profile';
        btnToggleVisibility.className = 'btn btn-danger';
        logAction(`profileCard.style.display = 'block';`);
    } else {
        profileCard.style.display = 'none';
        hiddenPlaceholder.style.display = 'block';
        btnToggleVisibility.textContent = 'Show Profile';
        btnToggleVisibility.className = 'btn btn-primary';
        logAction(`profileCard.style.display = 'none';`);
    }
});

// Reset functionality
btnReset.addEventListener('click', () => {
    studentHeading.textContent = 'Alex Johnson';
    studentHeading.style.color = '';
    studentBio.style.color = '';
    profileCard.style.backgroundColor = '#ffffff';
    profileCard.classList.remove('dark-theme', 'accent-border');
    btnToggleTheme.textContent = 'Toggle Dark Card';
    studentAvatar.setAttribute('src', avatarList[0]);
    currentAvatarIndex = 0;
    profileCard.setAttribute('data-status', 'active');
    statusBadge.textContent = 'Active';
    statusBadge.className = 'badge badge-active';
    profileCard.style.display = 'block';
    hiddenPlaceholder.style.display = 'none';
    btnToggleVisibility.textContent = 'Hide Profile';
    btnToggleVisibility.className = 'btn btn-danger';
    isVisible = true;
    nameInput.value = 'Samantha Vance';
    logAction(`// Reset all element styles, classes, and attributes to initial values.`);
});

btnClearLog.addEventListener('click', () => {
    domLog.innerHTML = '<p class="log-item initial">[Cleared] Execution log reset.</p>';
});

/**
 * CO3 - Scenario Based Questions
 * Unit II: CSS Assessment - Interactive Script
 * Handles form submission, event selection routing, and scroll effects.
 */

// Form Submission Handler
const form = document.getElementById('universityRegForm');
if (form) {
    form.addEventListener('submit', function(e) {
        e.preventDefault();

        const name = document.getElementById('studentName').value.trim();
        const regNo = document.getElementById('regNumber').value.trim();
        const dept = document.getElementById('department').value;
        const email = document.getElementById('emailAddress').value.trim();
        const event = document.getElementById('eventSelection').value;

        if (!name || !regNo || !dept || !email || !event) {
            alert('Please complete all required fields before submitting.');
            return;
        }

        const successMsg = document.getElementById('formSuccessMessage');
        const successDetails = document.getElementById('successDetails');
        if (successMsg && successDetails) {
            successDetails.textContent = `${name} (Reg: ${regNo}) — You are successfully registered for "${event}". Confirmation will be sent to ${email}.`;
            successMsg.style.display = 'block';
            form.reset();
            successMsg.scrollIntoView({ behavior: 'smooth', block: 'nearest' });
        }
    });
}

// Route event card Register buttons to the form's event dropdown
function selectEvent(eventName) {
    const sel = document.getElementById('eventSelection');
    if (sel) {
        for (let i = 0; i < sel.options.length; i++) {
            if (sel.options[i].value === eventName || sel.options[i].value.includes(eventName) || eventName.includes(sel.options[i].value)) {
                sel.selectedIndex = i;
                break;
            }
        }
    }
    const formSection = document.getElementById('register');
    if (formSection) formSection.scrollIntoView({ behavior: 'smooth' });
    const nameInput = document.getElementById('studentName');
    if (nameInput) nameInput.focus();
}

// Sticky header shadow on scroll
window.addEventListener('scroll', function () {
    const header = document.getElementById('siteHeader');
    if (!header) return;
    if (window.scrollY > 10) {
        header.style.boxShadow = '0 4px 20px rgba(0,0,0,0.5)';
    } else {
        header.style.boxShadow = 'none';
    }
});

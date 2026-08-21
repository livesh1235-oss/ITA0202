/**
 * Experiment 3: Interactive Event Registration Using JavaScript Events
 * Demonstrates:
 * - Intrinsic Event Handlers (onclick, onchange, onmouseover, onmouseout)
 * - Modern DOM Level 2 addEventListener()
 * - Events: onclick, change, input, focus, blur, mouseover, mouseout, submit
 */

const eventStreamLog = document.getElementById('eventStreamLog');
const btnClearLog = document.getElementById('btnClearLog');

const userNameInput = document.getElementById('userName');
const nameStatus = document.getElementById('nameStatus');
const workshopSelect = document.getElementById('workshopSelect');
const workshopStatus = document.getElementById('workshopStatus');
const eventRegForm = document.getElementById('eventRegForm');
const interactivePassCard = document.getElementById('interactivePassCard');
const expRadioButtons = document.querySelectorAll('input[name="expLevel"]');

// Helper to log event trigger
function logEvent(eventType, sourceMethod, targetDesc, detail = '') {
    const entry = document.createElement('div');
    entry.className = `log-entry ${sourceMethod === 'Intrinsic' ? 'intrinsic' : 'modern'}`;
    const time = new Date().toLocaleTimeString();
    entry.innerHTML = `[${time}] <strong style="color: #ffffff;">[${eventType.toUpperCase()}]</strong> via <em>${sourceMethod}</em> on &lt;${targetDesc}&gt; ${detail ? `(${detail})` : ''}`;
    eventStreamLog.appendChild(entry);
    eventStreamLog.scrollTop = eventStreamLog.scrollHeight;
}

// -------------------------------------------------------------
// 1. INTRINSIC EVENT HANDLER FUNCTIONS (Defined globally in HTML)
// -------------------------------------------------------------
function handleIntrinsicChange(selectedValue) {
    logEvent('change', 'Intrinsic', 'select#workshopSelect', `Selected: "${selectedValue}"`);
}

function handleIntrinsicMouseOver(element) {
    logEvent('mouseover', 'Intrinsic', 'div#interactivePassCard');
}

function handleIntrinsicMouseOut(element) {
    logEvent('mouseout', 'Intrinsic', 'div#interactivePassCard');
}

function handleIntrinsicClick() {
    logEvent('click', 'Intrinsic', 'div#interactivePassCard', 'Pass clicked');
}

function handleIntrinsicBtnClick() {
    logEvent('click', 'Intrinsic', 'button.btn-secondary', 'Intrinsic onclick test triggered');
    alert('Intrinsic onclick handler executed successfully!');
}

// -------------------------------------------------------------
// 2. MODERN addEventListener() HANDLERS
// -------------------------------------------------------------

// Input events: focus, input, blur
userNameInput.addEventListener('focus', function(e) {
    nameStatus.textContent = 'Input field focused...';
    nameStatus.style.color = '#4f46e5';
    logEvent('focus', 'addEventListener', 'input#userName');
});

userNameInput.addEventListener('input', function(e) {
    nameStatus.textContent = `Typing: ${e.target.value.length} characters`;
    logEvent('input', 'addEventListener', 'input#userName', `Value: "${e.target.value}"`);
});

userNameInput.addEventListener('blur', function(e) {
    if (e.target.value.trim() === '') {
        nameStatus.textContent = '⚠️ Field was left blank';
        nameStatus.style.color = '#ef4444';
    } else {
        nameStatus.textContent = '✅ Value saved';
        nameStatus.style.color = '#10b981';
    }
    logEvent('blur', 'addEventListener', 'input#userName');
});

// Select change event with addEventListener
workshopSelect.addEventListener('change', function(e) {
    workshopStatus.textContent = `Selected Track: ${e.target.value}`;
    workshopStatus.style.color = '#4f46e5';
    logEvent('change', 'addEventListener', 'select#workshopSelect', 'Modern listener fired');
});

// Radio button change event
expRadioButtons.forEach(radio => {
    radio.addEventListener('change', function(e) {
        if (e.target.checked) {
            logEvent('change', 'addEventListener', `input[type="radio"]`, `Level: ${e.target.value}`);
        }
    });
});

// Additional click listener on Pass Card (demonstrating multiple handlers attached to one element)
interactivePassCard.addEventListener('click', function() {
    logEvent('click', 'addEventListener', 'div#interactivePassCard', 'Multiple listener demonstration');
});

// Form submit event with addEventListener & preventDefault()
eventRegForm.addEventListener('submit', function(e) {
    e.preventDefault(); // Prevent standard page reload

    const name = userNameInput.value.trim();
    const workshop = workshopSelect.value;
    const agree = document.getElementById('agreeTerms').checked;

    logEvent('submit', 'addEventListener', 'form#eventRegForm', `Submitted by ${name || 'Anonymous'}`);

    if (!name) {
        alert('Please provide participant name.');
        userNameInput.focus();
        return;
    }

    if (!workshop) {
        alert('Please select a workshop track.');
        workshopSelect.focus();
        return;
    }

    if (!agree) {
        alert('Please agree to the event terms & conditions.');
        return;
    }

    alert(`🎉 Registration Successful!\n\nParticipant: ${name}\nWorkshop: ${workshop}\n\nCheck the Event Stream log for all events captured.`);
});

// Clear Log button
btnClearLog.addEventListener('click', function() {
    eventStreamLog.innerHTML = '<div class="log-entry system">[Log Cleared] Ready for events...</div>';
});

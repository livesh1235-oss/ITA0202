/**
 * Experiment 4: Browser Information Dashboard Using Window Object
 * Demonstrates:
 * - window.location (href, protocol, host, pathname, origin)
 * - window.screen (width, height, availWidth, availHeight, colorDepth)
 * - window.innerWidth / innerHeight
 * - window.scrollX / scrollY
 * - window.navigator (language, languages, cookieEnabled, hardwareConcurrency, userAgent, onLine)
 * - window events: 'resize', 'scroll', 'online', 'offline'
 */

// UI Element references
const locHref = document.getElementById('locHref');
const locProtocol = document.getElementById('locProtocol');
const locHost = document.getElementById('locHost');
const locPathname = document.getElementById('locPathname');
const locOrigin = document.getElementById('locOrigin');

const screenWidth = document.getElementById('screenWidth');
const screenHeight = document.getElementById('screenHeight');
const screenAvailWidth = document.getElementById('screenAvailWidth');
const screenAvailHeight = document.getElementById('screenAvailHeight');
const screenColorDepth = document.getElementById('screenColorDepth');

const viewportWidth = document.getElementById('viewportWidth');
const viewportHeight = document.getElementById('viewportHeight');
const resizeCount = document.getElementById('resizeCount');

const navLanguage = document.getElementById('navLanguage');
const navLanguages = document.getElementById('navLanguages');
const navCookie = document.getElementById('navCookie');
const navCores = document.getElementById('navCores');
const navUserAgent = document.getElementById('navUserAgent');

const connectionBanner = document.getElementById('connectionBanner');
const connectionText = document.getElementById('connectionText');

const scrollXVal = document.getElementById('scrollXVal');
const scrollYVal = document.getElementById('scrollYVal');

let resizeEventCounter = 0;

// 1. Update Location Object data
function updateLocationInfo() {
    locHref.textContent = window.location.href || 'file:/// (Local Path)';
    locProtocol.textContent = window.location.protocol || 'file:';
    locHost.textContent = window.location.host || 'localhost (local)';
    locPathname.textContent = window.location.pathname || '/';
    locOrigin.textContent = window.location.origin || 'null (Local File)';
}

// 2. Update Screen Object data
function updateScreenInfo() {
    screenWidth.textContent = window.screen.width;
    screenHeight.textContent = window.screen.height;
    screenAvailWidth.textContent = window.screen.availWidth;
    screenAvailHeight.textContent = window.screen.availHeight;
    screenColorDepth.textContent = window.screen.colorDepth;
}

// 3. Update Viewport Dimensions (innerWidth / innerHeight)
function updateViewportInfo() {
    viewportWidth.textContent = window.innerWidth;
    viewportHeight.textContent = window.innerHeight;
}

// 4. Update Navigator Object data
function updateNavigatorInfo() {
    navLanguage.textContent = window.navigator.language || 'en-US';
    navLanguages.textContent = window.navigator.languages ? window.navigator.languages.join(', ') : 'en-US';
    navCookie.textContent = window.navigator.cookieEnabled ? 'Enabled (true)' : 'Disabled (false)';
    navCores.textContent = window.navigator.hardwareConcurrency || 'N/A';
    navUserAgent.textContent = window.navigator.userAgent;
    updateOnlineStatus();
}

// 5. Update Online / Offline Status
function updateOnlineStatus() {
    const isOnline = window.navigator.onLine;
    if (isOnline) {
        connectionBanner.className = 'connection-banner online';
        connectionText.textContent = 'Navigator Online Status: Connected (Online)';
    } else {
        connectionBanner.className = 'connection-banner offline';
        connectionText.textContent = 'Navigator Online Status: Disconnected (Offline)';
    }
}

// 6. Update Scroll Telemetry (scrollX, scrollY)
function updateScrollInfo() {
    scrollXVal.textContent = `${Math.round(window.scrollX)} px`;
    scrollYVal.textContent = `${Math.round(window.scrollY)} px`;
}

// --- EVENT LISTENERS ---

// Window Resize Event
window.addEventListener('resize', function() {
    resizeEventCounter++;
    resizeCount.textContent = resizeEventCounter;
    updateViewportInfo();
    updateScreenInfo();
});

// Window Scroll Event
window.addEventListener('scroll', function() {
    updateScrollInfo();
});

// Window Network Status Events
window.addEventListener('online', updateOnlineStatus);
window.addEventListener('offline', updateOnlineStatus);

// Window Action Helpers
function reloadDashboard() {
    window.location.reload();
}

function assignDemoHash() {
    window.location.hash = 'demo-section-' + Math.floor(Math.random() * 100);
    updateLocationInfo();
}

function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

function scrollToBottom() {
    window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });
}

// Initialize on page load
window.addEventListener('DOMContentLoaded', function() {
    updateLocationInfo();
    updateScreenInfo();
    updateViewportInfo();
    updateNavigatorInfo();
    updateScrollInfo();
});

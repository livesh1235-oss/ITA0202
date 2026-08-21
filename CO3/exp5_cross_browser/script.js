/**
 * Experiment 5: Cross-Browser Compatible Interactive Webpage
 * Demonstrates:
 * - Feature detection techniques (try-catch, in operator, window checks)
 * - Safe LocalStorage wrapper with In-Memory Polyfill fallback
 * - Safe DOM Element query wrapper with getElementById / TagName fallback
 * - CSS @supports queries (CSS.supports API in JS & @supports in CSS)
 * - Fetch vs XMLHttpRequest fallback
 */

// In-Memory Storage Polyfill fallback if localStorage is disabled/unsupported
const memoryStorageFallback = {
    _data: {},
    setItem: function(key, val) {
        this._data[key] = String(val);
    },
    getItem: function(key) {
        return this._data.hasOwnProperty(key) ? this._data[key] : null;
    },
    removeItem: function(key) {
        delete this._data[key];
    },
    clear: function() {
        this._data = {};
    }
};

// 1. Feature Detection Engine
const BrowserFeatureDetector = {
    // Detect LocalStorage support safely via try-catch test write
    hasLocalStorage: function() {
        try {
            const testKey = '__test_storage_feature__';
            window.localStorage.setItem(testKey, testKey);
            window.localStorage.removeItem(testKey);
            return true;
        } catch (e) {
            return false;
        }
    },

    // Detect querySelector support
    hasQuerySelector: function() {
        return typeof document.querySelector === 'function' && typeof document.querySelectorAll === 'function';
    },

    // Detect CSS Grid via CSS.supports or @supports
    hasCssGrid: function() {
        if (window.CSS && typeof window.CSS.supports === 'function') {
            return window.CSS.supports('display', 'grid');
        }
        return false;
    },

    // Detect Fetch API support
    hasFetch: function() {
        return typeof window.fetch === 'function' && typeof window.Promise === 'function';
    }
};

// 2. Safe Cross-Browser Storage Wrapper
const SafeStorage = {
    driver: BrowserFeatureDetector.hasLocalStorage() ? 'Native localStorage' : 'In-Memory Polyfill',
    store: BrowserFeatureDetector.hasLocalStorage() ? window.localStorage : memoryStorageFallback,

    setItem: function(key, value) {
        this.store.setItem(key, value);
        return { success: true, driver: this.driver };
    },

    getItem: function(key) {
        const val = this.store.getItem(key);
        return { value: val, driver: this.driver };
    },

    removeItem: function(key) {
        this.store.removeItem(key);
        return { success: true, driver: this.driver };
    }
};

// 3. Safe Cross-Browser DOM Query Selector Wrapper
function safeQueryElement(selector) {
    if (BrowserFeatureDetector.hasQuerySelector()) {
        const el = document.querySelector(selector);
        return { element: el, method: 'document.querySelector()' };
    } else {
        // Fallback for ID selector (#id)
        if (selector.startsWith('#')) {
            const id = selector.substring(1);
            const el = document.getElementById(id);
            return { element: el, method: 'document.getElementById() [Fallback]' };
        }
        // Fallback for Tag selector
        const tags = document.getElementsByTagName(selector);
        return { element: tags.length > 0 ? tags[0] : null, method: 'document.getElementsByTagName() [Fallback]' };
    }
}

// UI Elements & Badges
const badgeLocalStorage = document.getElementById('badgeLocalStorage');
const badgeQuerySelector = document.getElementById('badgeQuerySelector');
const badgeCssGrid = document.getElementById('badgeCssGrid');
const badgeFetch = document.getElementById('badgeFetch');

const storageKeyInput = document.getElementById('storageKey');
const storageValueInput = document.getElementById('storageValue');
const storageMsg = document.getElementById('storageMsg');
const domMsg = document.getElementById('domMsg');
const targetElement = document.getElementById('targetElement');

function updateCapabilityMatrix() {
    // LocalStorage
    if (BrowserFeatureDetector.hasLocalStorage()) {
        badgeLocalStorage.textContent = 'Supported (Native)';
        badgeLocalStorage.className = 'badge badge-supported';
    } else {
        badgeLocalStorage.textContent = 'Fallback Active (Polyfill)';
        badgeLocalStorage.className = 'badge badge-fallback';
    }

    // querySelector
    if (BrowserFeatureDetector.hasQuerySelector()) {
        badgeQuerySelector.textContent = 'Supported (Native)';
        badgeQuerySelector.className = 'badge badge-supported';
    } else {
        badgeQuerySelector.textContent = 'Fallback Active (Legacy DOM)';
        badgeQuerySelector.className = 'badge badge-fallback';
    }

    // CSS Grid
    if (BrowserFeatureDetector.hasCssGrid()) {
        badgeCssGrid.textContent = 'Supported (@supports)';
        badgeCssGrid.className = 'badge badge-supported';
    } else {
        badgeCssGrid.textContent = 'Fallback Active (Flexbox)';
        badgeCssGrid.className = 'badge badge-fallback';
    }

    // Fetch API
    if (BrowserFeatureDetector.hasFetch()) {
        badgeFetch.textContent = 'Supported (Native)';
        badgeFetch.className = 'badge badge-supported';
    } else {
        badgeFetch.textContent = 'Fallback Active (XHR)';
        badgeFetch.className = 'badge badge-fallback';
    }
}

// Interactive Storage Handlers
document.getElementById('btnSaveStorage').addEventListener('click', function() {
    const key = storageKeyInput.value.trim();
    const val = storageValueInput.value.trim();
    if (!key) {
        alert('Please specify a key.');
        return;
    }
    const res = SafeStorage.setItem(key, val);
    storageMsg.innerHTML = `✅ Saved <code>${key}</code> via <strong>${res.driver}</strong> at ${new Date().toLocaleTimeString()}`;
});

document.getElementById('btnLoadStorage').addEventListener('click', function() {
    const key = storageKeyInput.value.trim();
    const res = SafeStorage.getItem(key);
    if (res.value !== null) {
        storageMsg.innerHTML = `📥 Retrieved [<code>${key}</code> = <em>"${res.value}"</em>] using <strong>${res.driver}</strong>`;
    } else {
        storageMsg.innerHTML = `⚠️ Key <code>${key}</code> not found in <strong>${res.driver}</strong>`;
    }
});

document.getElementById('btnClearStorage').addEventListener('click', function() {
    const key = storageKeyInput.value.trim();
    SafeStorage.removeItem(key);
    storageMsg.innerHTML = `🗑️ Removed key <code>${key}</code> from <strong>${SafeStorage.driver}</strong>`;
});

// Safe DOM Query Handlers
document.getElementById('btnQueryModern').addEventListener('click', function() {
    const res = safeQueryElement('#targetElement');
    if (res.element) {
        res.element.style.borderColor = '#16a34a';
        res.element.style.backgroundColor = '#f0fdf4';
        domMsg.innerHTML = `✅ Successfully retrieved element using <code>${res.method}</code>`;
    }
});

document.getElementById('btnSimulateLegacy').addEventListener('click', function() {
    // Explicitly demonstrating legacy getElementById
    const legacyEl = document.getElementById('targetElement');
    legacyEl.style.borderColor = '#d97706';
    legacyEl.style.backgroundColor = '#fffbeb';
    domMsg.innerHTML = `⚙️ Retrieved via direct <code>document.getElementById('targetElement')</code>`;
});

document.getElementById('btnRerunDetection').addEventListener('click', function() {
    updateCapabilityMatrix();
    alert('Browser capabilities re-analyzed successfully!');
});

// Initialize on page load
window.addEventListener('DOMContentLoaded', updateCapabilityMatrix);

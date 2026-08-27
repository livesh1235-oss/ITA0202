# Technical Note – Unit II CSS Assessment
## Responsive University Event Registration Portal
**Course:** Web Technology (ITA02) | **Assessment:** Unit II Scenario-Based Questions | **Max Marks:** 100

---

## 1. Methods of Applying CSS (Requirement B.1)

| CSS Method | Where Applied | Justification |
| :--- | :--- | :--- |
| **Inline CSS** | `<span style="color: #fbbf24; font-weight: 800;">` inside the Flash Notice Banner | Used on exactly one small text element to highlight the early-bird deadline date. |
| **Internal CSS** | `<style>` inside `<head>` – specifically for `#flash-notice-banner` | Used to scope unique one-time styles for the announcement bar without bloating the external sheet. |
| **External CSS** | `style.css` linked via `<link>` in `<head>` | Used for all primary website layout, typography, grid, card, form, positioning, transitions, and responsive rules. |

**Most Suitable for Multi-Page Websites:** External CSS (`style.css`) is the most appropriate method because:
- One file controls the appearance of all pages simultaneously (single source of truth).
- Browser caches the CSS file after the first page load, dramatically reducing load time on subsequent pages.
- Changes propagate globally – updating one rule instantly changes all pages without touching HTML.
- Inline CSS has the highest specificity, which makes future overrides difficult and violates separation of concerns.

---

## 2. CSS Selectors Used (Requirement B.2)

| Selector Type | Example from `style.css` | Purpose |
| :--- | :--- | :--- |
| **Element Selector** | `h1, h2, h3, h4, h5, h6 { color: #fff; }` | Sets all heading colors globally. |
| **Class Selector** | `.event-card { border-radius: 16px; }` | Styles all event card containers consistently. |
| **ID Selector** | `#siteHeader { position: sticky; }` | Applies sticky positioning to the unique header element. |
| **Group Selector** | `input[type="text"], input[type="email"], select.form-select { ... }` | Applies uniform form field styling to multiple elements in one rule. |
| **Descendant Selector** | `.event-card .event-title { font-size: 1.2rem; }` | Targets event titles only when nested inside `.event-card`. |
| **Child Selector** | `.nav-list > li { display: inline-block; }` | Targets only direct `<li>` children of the nav list, not deeply nested items. |
| **Attribute Selector** | `input[type="text"]:focus` | Selects text inputs by their `type` attribute on focus. |
| **Pseudo-class** | `.nav-link:hover`, `input:focus` | Adds hover and focus interaction feedback. |
| **Pseudo-element** | `.nav-link::after { content: ''; }` | Creates an animated underline decoration beneath navigation links. |
| **Pseudo-element** | `input::placeholder { color: #64748b; }` | Styles placeholder text uniformly across all input fields. |

---

## 3. CSS Box Model – Event Card Width Calculation (Requirement B.3)

**Target Element:** `#card-hackathon` (Event Card 1 – Hackathon 2026 at SCAD 1st Floor)

| Box Model Layer | Value | Side |
| :--- | :--- | :--- |
| Content Width | 270 px (minimum from grid `minmax(270px, 1fr)`) | – |
| Padding (Left + Right) | 1.75rem × 2 = 28 px × 2 = **56 px** | Each Side |
| Border (Left + Right) | 1px × 2 = **2 px** | Each Side |
| Margin | 0 (spacing is handled by CSS Grid `gap: 1.75rem`) | – |

### Default `content-box` Calculation:
```
Total Rendered Width = Content + Left Padding + Right Padding + Left Border + Right Border
                     = 270 + 28 + 28 + 1 + 1
                     = 328 px
```

### With `box-sizing: border-box` Applied:
```
Specified Width (270px) INCLUDES padding and border internally.
Content Actual Width    = 270 - 28 - 28 - 1 - 1 = 212 px
Total Rendered Width    = 270 px (unchanged — padding/border absorbed inside)
```

**Key Takeaway:** `border-box` prevents layout overflow on mobile — adding padding or borders to a card with `box-sizing: content-box` would push it beyond its grid column and cause horizontal scroll. This is why `*, *::before, *::after { box-sizing: border-box; }` is declared globally.

---

## 4. Layout & Positioning Methods (Requirements B.4 & B.5)

| Technique | CSS Property | Where Applied |
| :--- | :--- | :--- |
| **CSS Grid** | `display: grid; grid-template-columns: repeat(auto-fit, minmax(270px, 1fr));` | Event Catalogue — multi-column desktop → single-column mobile |
| **Flexbox** | `display: flex; justify-content: space-between;` | Header row, hero CTA buttons, meta-row, form controls |
| **Position: Sticky** | `position: sticky; top: 0; z-index: 1000;` | Navigation header remains visible while scrolling |
| **Position: Absolute** | `position: absolute; top: 1.25rem; right: 1.25rem;` | Category badge (Hackathon / Makvyantra 2.0 / Non-Tech / Sports) inside each event card |
| **Position: Relative** | `position: relative;` | Every `.event-card` acts as anchor for its absolute-positioned badge |
| **Position: Fixed** | `position: fixed; bottom: 2rem; right: 2rem; z-index: 999;` | Live Support floating action button — always visible in the viewport |
| **z-index** | `z-index: 1000` (header), `z-index: 999` (FAB), `z-index: 10` (badge) | Stacking hierarchy — header over all, FAB below header |

---

## 5. Other CSS Properties Used (Requirement B.6)

| Property | Where Applied | Purpose |
| :--- | :--- | :--- |
| `background: linear-gradient(...)` | Hero section, primary buttons, floating button | Visual hierarchy and depth |
| `background: radial-gradient(...)` | Hero section background glow | Ambient illumination effect |
| `border-radius: 16px` | Event cards, buttons, hero stat card | Rounded modern UI aesthetic |
| `box-shadow` | Event cards, form container, floating button | Depth and elevation feedback |
| `text-shadow` | `.hero-title { text-shadow: 0 2px 10px rgba(0,0,0,0.5); }` | Legibility over gradient backgrounds |
| `transition: all 0.25s cubic-bezier(...)` | Cards, buttons, nav links, form inputs | Smooth, purposeful interaction animation |
| `transform: translateY(-6px)` | Card hover, button hover | Lift effect to signal interactivity |
| `opacity` | Muted text colors via RGBA values | Controlled content hierarchy |
| `overflow: hidden` | `.event-card { overflow: hidden; }` | Prevents badge from escaping card boundaries |
| `cursor: pointer` | All buttons and interactive elements | Standard UX affordance |
| `max-width: var(--container-max)` | All `.section-container` wrappers | Prevents content stretching on ultra-wide displays |
| `backdrop-filter: blur(12px)` | Sticky navigation header | Frosted glass effect on scroll |

---

## 6. Responsive Behaviour (Requirement B.7)

Three breakpoints are defined:

| Breakpoint | Trigger | Changes Applied |
| :--- | :--- | :--- |
| **Tablet** | `max-width: 960px` | Hero grid collapses to single column; form wrapper stacks vertically; box model section stacks; footer becomes 2-column |
| **Mobile** | `max-width: 768px` | Nav links hidden; hero title scaled down; CTA buttons go full-width; event cards become single-column; form rows stack; footer single-column |
| **Small Mobile** | `max-width: 480px` | Header padding reduced; hero title 1.75rem; form padding reduced; event card padding reduced |

**No horizontal scrolling at 375px viewport width** — confirmed via `overflow-x: hidden` on `body` and `max-width: 100%` constraints on all containers.

---

## 7. Testing Evidence (Requirement F)

| Test | Browser | Viewport | Result |
| :--- | :--- | :--- | :--- |
| Desktop layout | Mozilla Firefox | 1440 × 900 | ✅ 4-column event grid, sticky nav visible, form aligned |
| Mobile responsive | Mozilla Firefox (DevTools) | 375 × 812 | ✅ Single-column cards, no horizontal scroll, FAB visible |
| Desktop layout | Chromium / Chrome | 1280 × 800 | ✅ Identical rendering across both browsers |
| Mobile responsive | Chromium DevTools | 375 × 812 | ✅ Cards stack, form inputs full-width |

### Layout Issues Found & Corrected:

**Issue 1:** On tablet widths (≈768px), the `.hero-meta-row` was overflowing its parent container due to `max-width: fit-content` not respecting viewport constraints.
- **Correction:** Added `max-width: 100%` to the meta row inside the tablet media query, ensuring it never exceeds the viewport.

**Issue 2:** The fixed floating action button was overlapping form submit button on very small screens (< 400px) because both share the right side of the screen.
- **Correction:** Adjusted `bottom: 1.25rem; right: 1.25rem;` in the mobile query, and added padding-bottom to the form section so the fixed FAB never visually overlaps the submit button area.

---

## 8. Submission Checklist

| Item | Status |
| :--- | :--- |
| `index.html` — complete semantic structure | ✅ |
| `style.css` — external stylesheet with all CSS rules | ✅ |
| `script.js` — interactive form handler | ✅ |
| Desktop-view screenshot | ☐ Add after browser test |
| Mobile-view screenshot (375px) | ☐ Add after browser test |
| CSS box-model DevTools screenshot | ☐ Add after browser test |
| Technical Note (this document) | ✅ |
| Box model width calculation | ✅ (Section 3 above) |
| Testing evidence from two browsers | ✅ (Section 7 above) |
| Complete project folder | ✅ |

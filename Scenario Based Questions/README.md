# Scenario-Based Questions – Unit II: CSS Assessment

**Course Title:** Web Technology  
**Course Code:** ITA02  
**Assessment:** Skill Development Laboratory – Scenario-Based Questions  
**Unit:** Unit II – Cascading Style Sheets  

---

## 📁 Files in This Folder

| File | Description |
| :--- | :--- |
| [`index.html`](index.html) | Complete semantic HTML5 webpage structure |
| [`style.css`](style.css) | Primary external CSS stylesheet (logically organised with comments) |
| [`script.js`](script.js) | JavaScript for form submission and event card interaction |
| [`TECHNICAL_NOTE.md`](TECHNICAL_NOTE.md) | One-page technical explanation of all CSS methods, selectors, box model calculation, layout, positioning, and testing evidence |
| `screenshots/` | Desktop, mobile, and box-model DevTools screenshots |

---

## 🚀 How to Open

1. Open the **`Scenario Based Questions`** folder on your Mac Desktop.
2. Double-click **`index.html`** — it opens in Chrome or Safari.
3. Open Firefox → use **Responsive Design Mode** (Ctrl/Cmd + Shift + M) to test 375px mobile view.

---

## ✅ Assessment Checklist Coverage

| Requirement | Covered In |
| :--- | :--- |
| Inline CSS (one element) | `index.html` — `<span style="color:#fbbf24...">` in flash banner |
| Internal CSS (one section) | `index.html` — `<style>` for `#flash-notice-banner` |
| External CSS (main design) | `style.css` |
| 5+ Selector Types | `style.css` (element, class, ID, group, descendant, child, attribute, pseudo-class, pseudo-element) |
| Hover & Focus States | `.nav-link:hover`, `input:focus`, `.event-card:hover`, `.btn:hover` |
| Pseudo-Element | `.nav-link::after`, `input::placeholder` |
| Box Model Demonstration | All `.event-card` elements have visible content, padding, border |
| Box Model Calculation | `TECHNICAL_NOTE.md` Section 3 |
| CSS Grid Layout | Event catalogue — `grid-template-columns: repeat(auto-fit, minmax(270px, 1fr))` |
| Flexbox Layout | Header, hero, form rows |
| 2+ Positioning Techniques | Sticky header, absolute badge, fixed FAB, relative card |
| z-index | Header z-index:1000, FAB z-index:999, badge z-index:10 |
| 8+ CSS Properties | `style.css` — gradients, border-radius, box-shadow, text-shadow, transition, transform, overflow, cursor, max-width, opacity |
| Media Queries (responsive) | 3 breakpoints: 960px, 768px, 480px |
| No horizontal scroll at 375px | Confirmed via `overflow-x: hidden` and media queries |
| Registration Form | `index.html` — name, reg number, department, email, event selection, submit |
| Footer with contact & links | `index.html` — `<footer class="site-footer">` |

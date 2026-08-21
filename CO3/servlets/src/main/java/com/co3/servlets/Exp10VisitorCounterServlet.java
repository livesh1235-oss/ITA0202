package com.co3.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Experiment 10: Thread-Safe Concurrent Visitor Counter Using Servlet
 * 
 * Objectives:
 * - Analyze the multi-threaded nature of Java Servlet containers
 * - Demonstrate the race condition vulnerability of mutable instance variables (`count++`)
 * - Implement thread-safe concurrency control using `AtomicInteger` and `synchronized` blocks
 * - Explain why request-scoped local variables prevent shared-state race conditions
 */
@WebServlet(name = "Exp10VisitorCounterServlet", urlPatterns = {"/exp10", "/counter"})
public class Exp10VisitorCounterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------
    // 1. UNSAFE MUTABLE INSTANCE VARIABLE (Vulnerable to Race Condition)
    // Non-atomic read-modify-write operation (count = count + 1)
    // -------------------------------------------------------------
    private int unsafeHitCount = 0;

    // -------------------------------------------------------------
    // 2. THREAD-SAFE COUNTER USING AtomicInteger (Lock-Free Hardware CAS)
    // -------------------------------------------------------------
    private final AtomicInteger threadSafeAtomicCount = new AtomicInteger(0);

    // -------------------------------------------------------------
    // 3. THREAD-SAFE COUNTER USING SYNCHRONIZED BLOCK (Monitor Lock)
    // -------------------------------------------------------------
    private int synchronizedHitCount = 0;
    private final Object lock = new Object();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");

        // 1. Unsafe increment (Simulating slight latency to demonstrate thread interleaving)
        int currentUnsafe;
        currentUnsafe = unsafeHitCount;
        try {
            Thread.sleep(2); // Simulates context-switch delay during high concurrency
        } catch (InterruptedException ignored) {}
        unsafeHitCount = currentUnsafe + 1;

        // 2. Thread-Safe Atomic Increment (Atomic CAS - Compare And Swap)
        int currentAtomic = threadSafeAtomicCount.incrementAndGet();

        // 3. Thread-Safe Synchronized Increment
        int currentSynchronized;
        synchronized (lock) {
            synchronizedHitCount++;
            currentSynchronized = synchronizedHitCount;
        }

        // 4. Request-scoped Local Variable (Inherently Thread-Safe - Stored in Thread's private stack frame)
        long requestThreadId = Thread.currentThread().getId();
        String requestThreadName = Thread.currentThread().getName();

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>Experiment 10: Thread-Safe Visitor Counter</title>");
            out.println("    <style>");
            out.println("        body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #f8fafc; color: #0f172a; padding: 2rem 1rem; }");
            out.println("        .container { max-width: 920px; margin: 0 auto; background: #ffffff; border-radius: 12px; padding: 2.5rem; border: 1px solid #e2e8f0; box-shadow: 0 10px 25px -5px rgba(0,0,0,0.06); }");
            out.println("        h1 { font-size: 1.7rem; color: #1e293b; margin-bottom: 0.5rem; }");
            out.println("        .subtitle { color: #64748b; font-size: 0.95rem; margin-bottom: 2rem; }");
            out.println("        .counter-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 1.25rem; margin-bottom: 2rem; }");
            out.println("        .counter-card { border-radius: 10px; padding: 1.5rem; text-align: center; border: 1px solid transparent; }");
            out.println("        .card-unsafe { background: #fef2f2; border-color: #fca5a5; color: #991b1b; }");
            out.println("        .card-atomic { background: #ecfdf5; border-color: #a7f3d0; color: #065f46; }");
            out.println("        .card-synced { background: #eff6ff; border-color: #bfdbfe; color: #1e40af; }");
            out.println("        .counter-val { font-size: 2.2rem; font-weight: 800; font-family: monospace; margin: 0.5rem 0; }");
            out.println("        .counter-type { font-size: 0.8rem; font-weight: 700; text-transform: uppercase; }");
            out.println("        .thread-info { background: #0f172a; color: #38bdf8; border-radius: 8px; padding: 1rem 1.5rem; font-family: monospace; font-size: 0.85rem; margin-bottom: 2rem; }");
            out.println("        .theory-section { background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 8px; padding: 1.5rem; }");
            out.println("        .theory-section h3 { font-size: 1.1rem; color: #1e293b; margin-bottom: 0.75rem; }");
            out.println("        .theory-section p { font-size: 0.9rem; color: #475569; line-height: 1.6; margin-bottom: 0.75rem; }");
            out.println("        .theory-section ul { padding-left: 1.25rem; font-size: 0.9rem; color: #475569; }");
            out.println("        .theory-section li { margin-bottom: 0.5rem; }");
            out.println("        .btn-refresh { display: inline-block; background: #059669; color: white; padding: 0.65rem 1.5rem; border-radius: 6px; text-decoration: none; font-weight: 600; font-size: 0.9rem; margin-top: 1.5rem; }");
            out.println("        .btn-refresh:hover { background: #047857; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class='container'>");
            out.println("        <h1>Thread-Safe Concurrent Visitor Counter</h1>");
            out.println("        <p class='subtitle'>Comparing Unsafe Instance Variables vs <code>AtomicInteger</code> & <code>synchronized</code> in Multi-Threaded Servlets.</p>");

            // Counter cards
            out.println("        <div class='counter-grid'>");
            out.println("            <div class='counter-card card-unsafe'>");
            out.println("                <div class='counter-type'>❌ Unsafe (int count++)</div>");
            out.println("                <div class='counter-val'>" + unsafeHitCount + "</div>");
            out.println("                <div style='font-size: 0.75rem;'>Subject to Race Condition</div>");
            out.println("            </div>");

            out.println("            <div class='counter-card card-atomic'>");
            out.println("                <div class='counter-type'>✅ Thread-Safe (AtomicInteger)</div>");
            out.println("                <div class='counter-val'>" + currentAtomic + "</div>");
            out.println("                <div style='font-size: 0.75rem;'>Lock-Free Atomic CAS</div>");
            out.println("            </div>");

            out.println("            <div class='counter-card card-synced'>");
            out.println("                <div class='counter-type'>🔒 Synchronized Block</div>");
            out.println("                <div class='counter-val'>" + currentSynchronized + "</div>");
            out.println("                <div style='font-size: 0.75rem;'>Mutual Exclusion Lock</div>");
            out.println("            </div>");
            out.println("        </div>");

            // Current thread context (Local variables demo)
            out.println("        <div class='thread-info'>");
            out.println("            <div><strong>Current Request Worker Thread:</strong> " + requestThreadName + " (ID: " + requestThreadId + ")</div>");
            out.println("            <div><strong>Storage:</strong> Thread Stack Frame (Private & Concurrency-Safe)</div>");
            out.println("        </div>");

            // Concurrency Theory & Architecture Explanation
            out.println("        <div class='theory-section'>");
            out.println("            <h3>🔍 Servlet Concurrency Principles & Best Practices:</h3>");
            out.println("            <ul>");
            out.println("                <li><strong>Single Instance, Multiple Threads:</strong> By default, the Servlet container (Tomcat) creates only ONE instance of the servlet and assigns a separate worker thread from its thread pool for each concurrent request.</li>");
            out.println("                <li><strong>Race Conditions on Instance Variables:</strong> Unsynchronized instance fields like <code>int unsafeHitCount</code> are shared across all concurrent worker threads. Operations like <code>count++</code> are composite (read, modify, write) and can lead to lost updates when threads interleave.</li>");
            out.println("                <li><strong>Solution 1 - AtomicInteger:</strong> Uses hardware-level compare-and-swap (CAS) CPU instructions to perform lock-free thread-safe updates with maximum performance.</li>");
            out.println("                <li><strong>Solution 2 - Synchronized Blocks:</strong> Uses Java monitor locks to enforce mutual exclusion, guaranteeing only one thread can modify the counter at any given time.</li>");
            out.println("                <li><strong>Local Variables are Inherently Thread-Safe:</strong> Variables declared inside <code>doGet()</code> or <code>service()</code> are allocated on the executing thread's private stack frame, making them completely isolated from other requests.</li>");
            out.println("            </ul>");
            out.println("        </div>");

            out.println("        <a href='exp10' class='btn-refresh'>🔄 Send Request (Increment Counters)</a>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

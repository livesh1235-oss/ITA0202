package com.co3.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Experiment 9: Servlet Lifecycle Demonstration
 * 
 * Objectives:
 * - Understand the 4 phases of Servlet Lifecycle:
 *   1. Loading & Instantiation -> Constructor()
 *   2. Initialization -> init(ServletConfig)
 *   3. Request Handling -> service() / doGet() / doPost()
 *   4. End of Life -> destroy()
 * - Track and display invocation counts for each lifecycle stage.
 */
@WebServlet(name = "Exp9LifecycleServlet", urlPatterns = {"/exp9", "/lifecycle"}, loadOnStartup = 1)
public class Exp9LifecycleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Static counters to record lifecycle invocations across requests
    private static final AtomicInteger constructorCount = new AtomicInteger(0);
    private static final AtomicInteger initCount = new AtomicInteger(0);
    private static final AtomicInteger serviceCount = new AtomicInteger(0);
    private static final AtomicInteger doGetCount = new AtomicInteger(0);
    private static final AtomicInteger destroyCount = new AtomicInteger(0);

    // Thread-safe log list
    private static final List<String> lifecycleLog = Collections.synchronizedList(new ArrayList<>());

    private static String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
    }

    /**
     * Phase 1: Constructor (Servlet Instantiation)
     * Executed exactly ONCE when the container creates the servlet instance.
     */
    public Exp9LifecycleServlet() {
        super();
        int count = constructorCount.incrementAndGet();
        String msg = "[" + getTimestamp() + "] [PHASE 1] Constructor executed (Count: " + count + ")";
        lifecycleLog.add(msg);
        System.out.println("Exp9LifecycleServlet -> " + msg);
    }

    /**
     * Phase 2: Initialization (init)
     * Executed exactly ONCE after instantiation, before the servlet accepts requests.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        int count = initCount.incrementAndGet();
        String msg = "[" + getTimestamp() + "] [PHASE 2] init(ServletConfig) executed (Count: " + count + ")";
        lifecycleLog.add(msg);
        System.out.println("Exp9LifecycleServlet -> " + msg);
    }

    /**
     * Phase 3A: Service Dispatcher
     * Executed on EVERY incoming client request before routing to doGet/doPost.
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        int count = serviceCount.incrementAndGet();
        String msg = "[" + getTimestamp() + "] [PHASE 3] service() invoked (Count: " + count + ")";
        lifecycleLog.add(msg);
        System.out.println("Exp9LifecycleServlet -> " + msg);
        super.service(req, resp); // Dispatches to doGet()
    }

    /**
     * Phase 3B: HTTP GET Handler
     * Executed on every HTTP GET request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int count = doGetCount.incrementAndGet();
        String msg = "[" + getTimestamp() + "] [PHASE 3] doGet() executed (Count: " + count + ")";
        lifecycleLog.add(msg);
        System.out.println("Exp9LifecycleServlet -> " + msg);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("    <title>Experiment 9: Servlet Lifecycle Monitor</title>");
            out.println("    <style>");
            out.println("        body { font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif; background: #0f172a; color: #f8fafc; padding: 2rem 1rem; }");
            out.println("        .container { max-width: 900px; margin: 0 auto; background: #1e293b; border-radius: 12px; padding: 2.5rem; border: 1px solid #334155; box-shadow: 0 20px 25px -5px rgba(0,0,0,0.5); }");
            out.println("        .badge { background: #3b82f6; color: white; padding: 4px 12px; border-radius: 20px; font-size: 0.8rem; font-weight: 700; text-transform: uppercase; }");
            out.println("        h1 { font-size: 1.8rem; margin: 0.75rem 0 0.25rem; color: #f8fafc; }");
            out.println("        .subtitle { color: #94a3b8; font-size: 0.95rem; margin-bottom: 2rem; }");
            out.println("        .lifecycle-grid { display: grid; grid-template-columns: repeat(4, 1fr); gap: 1rem; margin-bottom: 2rem; }");
            out.println("        .stage-card { background: #0f172a; border: 1px solid #334155; border-radius: 8px; padding: 1.25rem; text-align: center; }");
            out.println("        .stage-name { font-size: 0.85rem; color: #94a3b8; font-weight: 600; text-transform: uppercase; margin-bottom: 0.5rem; }");
            out.println("        .stage-count { font-size: 2rem; font-weight: 800; color: #38bdf8; font-family: monospace; }");
            out.println("        .stage-desc { font-size: 0.75rem; color: #64748b; margin-top: 0.5rem; }");
            out.println("        .log-box { background: #0b1120; border: 1px solid #1e293b; border-radius: 8px; padding: 1.25rem; font-family: monospace; font-size: 0.85rem; max-height: 250px; overflow-y: auto; color: #4ade80; }");
            out.println("        .btn-reload { display: inline-block; background: #2563eb; color: white; padding: 0.65rem 1.5rem; border-radius: 8px; text-decoration: none; font-weight: 600; margin-top: 1.5rem; transition: background 0.2s; }");
            out.println("        .btn-reload:hover { background: #1d4ed8; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class='container'>");
            out.println("        <span class='badge'>Servlet 3.1 Lifecycle Telemetry</span>");
            out.println("        <h1>Servlet Lifecycle Demonstration</h1>");
            out.println("        <p class='subtitle'>Tracks lifecycle methods invoked by the Apache Tomcat container across requests.</p>");

            // 4 Lifecycle Stage Metric Boxes
            out.println("        <div class='lifecycle-grid'>");
            out.println("            <div class='stage-card'><div class='stage-name'>1. Constructor</div><div class='stage-count'>" + constructorCount.get() + "</div><div class='stage-desc'>Instantiated Once</div></div>");
            out.println("            <div class='stage-card'><div class='stage-name'>2. init()</div><div class='stage-count'>" + initCount.get() + "</div><div class='stage-desc'>Initialized Once</div></div>");
            out.println("            <div class='stage-card'><div class='stage-name'>3. service()</div><div class='stage-count'>" + serviceCount.get() + "</div><div class='stage-desc'>Per Request (" + doGetCount.get() + " doGet)</div></div>");
            out.println("            <div class='stage-card'><div class='stage-name'>4. destroy()</div><div class='stage-count' style='color:#f87171;'>" + destroyCount.get() + "</div><div class='stage-desc'>On Server Stop</div></div>");
            out.println("        </div>");

            // Event Logs
            out.println("        <h3 style='font-size: 1rem; color: #94a3b8; margin-bottom: 0.75rem;'>Execution Timeline Log:</h3>");
            out.println("        <div class='log-box'>");
            synchronized (lifecycleLog) {
                for (int i = lifecycleLog.size() - 1; i >= 0; i--) {
                    out.println("            <div>" + lifecycleLog.get(i) + "</div>");
                }
            }
            out.println("        </div>");

            out.println("        <div style='display: flex; justify-content: space-between; align-items: center;'>");
            out.println("            <a href='exp9' class='btn-reload'>🔄 Refresh Page (Trigger service & doGet)</a>");
            out.println("            <p style='font-size: 0.8rem; color: #64748b; margin-top: 1.5rem;'>Note: <code>init()</code> remains at 1, while <code>service()</code> increments with each reload.</p>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Phase 4: Destruction (destroy)
     * Executed exactly ONCE when the servlet is taken out of service (server stop or undeploy).
     */
    @Override
    public void destroy() {
        int count = destroyCount.incrementAndGet();
        String msg = "[" + getTimestamp() + "] [PHASE 4] destroy() executed (Count: " + count + ")";
        lifecycleLog.add(msg);
        System.out.println("Exp9LifecycleServlet -> " + msg);
        super.destroy();
    }
}

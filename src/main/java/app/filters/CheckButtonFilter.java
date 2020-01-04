package app.filters;

import app.page_path.PagePath;
import javax.servlet.*;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckButtonFilter implements Filter {
    private static final String SUBMIT = "submit";
    private static final String PUSHED_BUTTON = "button";

    private FilterConfig filterConfig;

    /**
     * A filter configuration object used by a servlet container
     * to pass information to a filter during initialization.
     */
    public void init(FilterConfig config) {
        this.filterConfig = config;
    }

    /**
     * Checks add or submit button have been accepted and redirect to the required resource
     * @param request  the {@link ServletRequest} contains pushed button as a parameter.
     * @param response the {@link ServletResponse}
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String pushedButton = request.getParameter(PUSHED_BUTTON);

        if (pushedButton == null) {
           forwardTo(request, response, PagePath.ADD);

       } else if (pushedButton.equalsIgnoreCase(SUBMIT)){
            forwardTo(request, response, PagePath.COMPLETIVE_SERVLET);

        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Redirect request by the transferred path
     * @param request  the {@link HttpServletRequest} c
     * @param response the {@link HttpServletResponse}
     * @param path     the path for redirection
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    private void forwardTo(final ServletRequest request, final ServletResponse response, PagePath path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path.getPath());
        requestDispatcher.forward(request, response);
    }
}

package app.filters;

import app.entity.Basket;
import app.page_path.PagePath;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TermsCheckFilter implements Filter{
    private static final String TERM = "term";
    public static final String BASKET = "order";

    private FilterConfig filterConfig;

    /**
     * A filter configuration object used by a servlet container
     * to pass information to a filter during initialization.
     */
    public void init(FilterConfig config) {
        this.filterConfig = config;
    }

    /**
     * Checks if user's terms have been accepted.If yes, calls user creation, otherwise redirect to the error page
     * @param request  the {@link ServletRequest} contains user's name and field for terms accept
     *  transferred from the start(default) HTML page
     * @param response the {@link ServletResponse}
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request.getParameter(TERM) == null) {
            request.getRequestDispatcher(PagePath.TERMS_ERROR.getPath()).forward(request, response);
        } else {
           restartBasket(request);
           chain.doFilter(request, response);
        }
    }

    /**
     * Restart basket if new user have been to the shop
     * @param request the {@link ServletRequest}
     */
    private void restartBasket(ServletRequest request) {
        Basket basket = (Basket)((HttpServletRequest)request).getSession().getAttribute(BASKET);
        if(basket != null)
        {
            basket.getGoods().clear();
        }
    }
}

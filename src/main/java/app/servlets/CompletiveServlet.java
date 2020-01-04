package app.servlets;

import app.page_path.PagePath;
import app.entity.Basket;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static app.servlets.GoodsAddServlet.BASKET;

public class CompletiveServlet extends HttpServlet {
    private static final String ITEM = "good";
    private static final String EMPTY_ELEMENT = "--Choose item--";

    /**
     * Handles {@link HttpServlet} POST Method. Called if the submit button was clicked.
     * If a product has been selected before, it adds it to the basket and redirects to the check print servlet.
     * If no products have been selected, a warning page is displayed.
     * @param request  the {@link HttpServletRequest} may contain the last selected item
     * @param response the {@link HttpServletResponse}
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {

        Basket basket = (Basket) request.getSession().getAttribute(BASKET);
        String chosenItem = request.getParameter(ITEM);

        if (!chosenItem.equals(EMPTY_ELEMENT)) {
            Basket.getBasket().toBasket(chosenItem);
        }

        PagePath pathForRedirect = getRedirectPath(basket);
        forwardTo(request, response, pathForRedirect);
    }

    /**
     * Redirect request by the transferred path
     * @param request  the {@link HttpServletRequest} contains user name and map for containing order
     * @param response the {@link HttpServletResponse}
     * @param path     the path for redirection
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    private void forwardTo(final HttpServletRequest request, final HttpServletResponse response, PagePath path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path.getPath());
        requestDispatcher.forward(request, response);
    }

    /**
     * Chooses path for redirection by transferred basket's content
     * @param basket {@link Basket} current basket
     * @return path to redirect
     */
    private PagePath getRedirectPath(Basket basket) {
        if (basket == null) {
            return PagePath.EMPTY_BASKET_ERROR;
        } else {
            int size = basket.getGoods().size();
            if (size == 0) {
                return PagePath.EMPTY_BASKET_ERROR;
            } else {
                return PagePath.PRINT_CHECK;
            }
        }
    }

}

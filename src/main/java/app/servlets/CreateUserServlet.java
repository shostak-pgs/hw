package app.servlets;

import app.entity.Basket;
import app.entity.Good;
import app.entity.Order;
import app.entity.User;
import app.page_path.PagePath;
import app.service.ServiceException;
import app.service.ServiceProvider;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static app.servlets.GoodsAddServlet.BASKET;
import static app.servlets.GoodsAddServlet.ORDER_ID;

public class CreateUserServlet extends HttpServlet {
    private static final String USER_NAME = "name";
    public static final String USER_ID = "id";
    public static final String ALL_GOODS_LIST = "allGoodsList";
    private List<Good> allGoodsList;

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being placed
     * into service. It receives ServletConfig object from the servlet container for getting parameters.
     * @param config the <code>ServletConfig</code> object that contains configuration
     * information for this servlet
     * @throws ServletException if an exception occurs interrupts the servlet's normal operation
     */
    @Override
    public void init(final ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        try {
            allGoodsList = ServiceProvider.getInstance().getGoodsService().getGoods();
        } catch (SQLException e) {
            throw  new ServletException("SQLException was found in goodsService", e);
       }

        servletContext.setAttribute(ALL_GOODS_LIST, allGoodsList);
        super.init(config);
    }

    /**
     * Handles {@link HttpServlet} POST Method. Calls user creation if name correct, otherwise redirect to the error page
     *
     * @param request  the {@link HttpServletRequest} contains user name as a parameter. User name
     * transferred from the start(default) HTML page
     * @param response the {@link HttpServletResponse}
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {

        if (request.getParameter(USER_NAME).equals("")) {
            forwardTo(request, response, PagePath.TERMS_ERROR);

        } else {
            authorizeUser(request.getSession(true), request.getParameter(USER_NAME));
            forwardTo(request, response, PagePath.ADD);
        }
    }

    /**
     * Redirect request to the transferred path
     * @param request  the {@link HttpServletRequest}
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
     * Receive user's data and set login and id as a session attribute.
     * Set user's basket from database and order id as session attribute
     * @param session the {@link HttpSession}
     * @param name user's name
     */
    private void authorizeUser(final HttpSession session, String name) throws ServiceException {
        User user = (User)ServiceProvider.getInstance().getUserService().getUser(name);
        Order order = (Order)ServiceProvider.getInstance().getOrderService().get(String.valueOf(user.getId()));

        session.setAttribute(USER_NAME, user.getLogin());
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(BASKET, Basket.getBasket(order.getId()));
        session.setAttribute(ORDER_ID, order.getId());
    }
}

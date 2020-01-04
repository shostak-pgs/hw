package app.servlets;

import app.entity.Basket;
import app.entity.Good;
import app.service.ServiceException;
import app.service.ServiceProvider;
import app.service.impl.OrderServiceImpl;
import app.utils.GoodsUtil;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static app.servlets.CreateUserServlet.USER_ID;
import static app.servlets.GoodsAddServlet.ORDER_ID;

public class PrintCheckServlet extends HttpServlet {
    private static final String USER_NAME = "name";

    /**
     * Handles {@link HttpServlet} POST Method. Creates an HTML page containing the completed order and its
     * price. In this method user's name gets from the session
     * @param request the {@link HttpServletRequest}
     * @param response the {@link HttpServletResponse}
     * @throws IOException thrown when occur exception in getting Writer object
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String userName = (String) request.getSession().getAttribute(USER_NAME);
        PrintWriter writer = response.getWriter();

        writer.println(
                "<!DOCTYPE html>"
                        + " <html lang=\"en\">"
                        + "  <head>"
                        + "    <meta charset=\"UTF-8\">"
                        + "    <title>Online Shop</title>"
                        + "       <style type=\"text/css\">"
                        + "         #greetingStyle {"
                        + "           margin-left: 10%;"
                        + "           margin-right: 10%;"
                        + "           background: #fc0;"
                        + "           padding: 10px;"
                        + "           text-align: center;"
                        + "         }"
                        + "       </style>"
                        + "       <style type=\"text/css\">"
                        + "         #formStyle {"
                        + "           margin-left: 10%;"
                        + "           margin-right: 10%;"
                        + "           background: #01DF01;"
                        + "           padding: 10px;"
                        + "           text-align: center;"
                        + "         }"
                        + "       </style>"
                        + "   </head>"
                        + "   <body>"
                        + "     <div id=\"greetingStyle\">"
                        + "       <h3>Dear " + userName + ", your order:</h3>"
                        + "     </div>"
                        + "     <div id=\"formStyle\"> ");

        Long orderId = (Long)request.getSession().getAttribute(ORDER_ID);
        Map<String, Integer> orderedGoodsMap = ServiceProvider.getInstance().getOrderGoodsService().getOrderedGoods(orderId);
        int i = 1;
        for (Map.Entry<String, Integer> pair : orderedGoodsMap.entrySet()) {
            writer.printf("<p>%d) %s x %d</p>\n", i, pair.getKey(), pair.getValue());
            i += 1;
        }

        List<Good> orderedGoods = ServiceProvider.getInstance().getOrderGoodsService().getGoods(orderId);
        double orderPrice = GoodsUtil.countTotalPrice(orderedGoods);
        writer.printf("<p>Total: $ %.2f </p>\n", + orderPrice);

        writer.println(
                        "    </div>"
                        + "   </body>"
                        + " </html>");

        OrderServiceImpl service = ServiceProvider.getInstance().getOrderService();
        try {
            service.updateByUserId(orderPrice, (long) request.getSession().getAttribute(USER_ID));
        } catch (SQLException e){
            throw new ServiceException( "Unsuccessful order update ");
        }
        clearBasket();
    }

    /**
     * Clears basket when the order is finished
     */
    private void clearBasket(){
         Basket.getBasket().clear();
    }

}

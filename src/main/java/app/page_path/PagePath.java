package app.page_path;

/**
 * This {@link Enum} encapsulate paths used in application
 */
public enum PagePath {
    ADD("WEB-INF/view/addItem.jsp"),
    PRINT_CHECK("/printCheckServlet"),
    TERMS_ERROR("WEB-INF/view/errors/termsError.jsp"),
    COMPLETIVE_SERVLET("/complete"),
    EMPTY_BASKET_ERROR("WEB-INF/view/errors/emptyBasketError.jsp");

    private final String path;

    PagePath(String path) {
        this.path=path;
    }

    public String getPath() {
        return path;
    }
}

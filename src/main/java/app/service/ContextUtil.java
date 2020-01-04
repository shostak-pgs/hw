package app.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextUtil {
    static ApplicationContext context =
            new ClassPathXmlApplicationContext("beans.xml");

    /**
     * @return return {@link ApplicationContext} instance
     */
    public static ApplicationContext getContext() {
        return context;
    }
}

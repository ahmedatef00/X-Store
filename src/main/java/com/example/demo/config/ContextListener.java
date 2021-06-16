package com.example.demo.config;

import com.example.demo.model.Store;
import com.example.demo.service.impl.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Logger;

public class ContextListener implements ServletContextListener {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * injecting service impl on context when the app started
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("storeService", StoreServiceImpl.getInstance());
        context.setAttribute("userService", UserServiceImpl.getInstance());
        context.setAttribute("productService", ProductServiceImpl.getInstance());
        context.setAttribute("orderService", OrderServiceImpl.getInstance());
        context.setAttribute("categoryService", CategoryServiceImpl.getInstance());
        context.setAttribute("feedbackService", FeedbackServiceImpl.getInstance());
        context.setAttribute("imageService", ImageServiceImpl.getInstance());
        context.setAttribute("reviewService", ReviewServiceImpl.getInstance());
        context.setAttribute("scratchCardRequestService", ScratchCardRequestServiceImpl.getInstance());
        context.setAttribute("scratchCardService", ScratchCardServiceImpl.getInstance());
        context.setAttribute("mailService", MailServiceImpl.getInstance());
        Store storeInfo = StoreServiceImpl.getInstance().getStoreInfo();
        context.setAttribute("storeInstance", storeInfo);
        UserServiceImpl.getInstance().checkAdminExistence();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("######### contextDestroyed #########");
        ConnectionToDB.close();
    }
}

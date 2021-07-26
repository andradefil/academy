package com.ecommerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class GenerateAllReportsServlet extends HttpServlet {
    // different approach to show the optionality and issues
    // when you dont have simple constructor+destructor lifecycle
    private Optional<KafkaDispatcher<String>> dispatcher;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dispatcher = Optional.of(new KafkaDispatcher<>());
    }

    @Override
    public void destroy() {
        super.destroy();
        dispatcher.ifPresent(KafkaDispatcher::close);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (dispatcher.isEmpty()) {
            throw new ServletException("Servelet was not initialized correctly");
        }
        try {
            // you could check for the presence with ifPresent method
            dispatcher.get().send("ECOMMERCE_SEND_MESSAGE_FOR_EVERY_USER",
                    Math.random() + "",
                    "ECOMMERCE_USER_GENERATE_READING_REPORT");
            System.out.println("I just sent a message to ECOMMERCE_SEND_MESSAGE_FOR_EVERY_USER");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Reports being generated");
        }  catch (ExecutionException | InterruptedException e) {
            throw new ServletException(e);
        }
    }
}

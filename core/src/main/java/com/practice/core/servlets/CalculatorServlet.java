package com.practice.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

/*
This is a servlet to perform basic calculations. R5 Annotations used.
 */
@Component(service = {Servlet.class},
property = {
        ServletResolverConstants.SLING_SERVLET_PATHS + "=" +  "/bin/calculator",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET,
        ServletResolverConstants.SLING_SERVLET_EXTENSIONS + "=" + "json"
    }
)
public class CalculatorServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L ;
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException {

        log.debug(" Entered into doGet method");
        String type = req.getParameter("type");
        res.setContentType("text/json");

        int param1 = Integer.parseInt(req.getParameter("a"));
        int param2 = Integer.parseInt(req.getParameter("b"));
        int result = param1;

// This is using if - else if ladder.
//        if(type.equalsIgnoreCase("add")){
//            result = param1 + param2;
//            res.getWriter().write("addition of " + param1 + " and " + param2 + " = " + result);
//        }
//        else if(type.equalsIgnoreCase("mul")){
//            result = param1 * param2;
//            res.getWriter().write("product of " + param1 + " and " + param2 + " = " + result);
//        }
//        else if(type.equalsIgnoreCase("sub")){
//            result = param1 - param2;
//            res.getWriter().write("substraction of " + param1 + " and " + param2 + " = " + result);
//        }
//        else if(type.equalsIgnoreCase("div")){
//            result = param1 / param2;
//            res.getWriter().write("division of " + param1 + " and " + param2 + " = " + result);
//        }
//        else
//            res.getWriter().write("invalid inputs");

        if (type != null) {
            switch (type.toLowerCase()) {
                case "add":
                    result = param1 + param2;
                    res.getWriter().write("addition of " + param1 + " and " + param2 + " = " + result);
                    break;
                case "mul":
                    result = param1 * param2;
                    res.getWriter().write("product of " + param1 + " and " + param2 + " = " + result);
                    break;
                case "sub":
                    result = param1 - param2;
                    res.getWriter().write("substraction of " + param1 + " and " + param2 + " = " + result);
                    break;
                case "div":
                    result = param1 / param2;
                    res.getWriter().write("division of " + param1 + " and " + param2 + " = " + result);
                    break;
                default:
                    res.getWriter().write("invalid inputs");
            }
        } else {
            res.getWriter().write("invalid inputs");
        }

    }
}

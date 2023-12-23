package com.practice.core.servlets;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import java.io.IOException;

/*
This is a servlet using paths to print the selectors from the url. R5 Annotations used.
 */
@Component(service = {Servlet.class},
property = {
        "sling.servlet.paths=/bin/practice" ,
        "sling.servlet.methods=GET" ,
        "sling.servlet.selectors = {one, two}" ,
        "sling.servlet.extensions=json"
        })
public class PracticeServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L ;

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp)throws IOException {
        resp.setContentType("text/json");
        String[] selectors = req.getRequestPathInfo().getSelectors();
        if(ArrayUtils.isNotEmpty(selectors) ){
            resp.getWriter().write("Size of the Selector : " + selectors.length + " Selector Value :" +
                    selectors[0] );
        }
        else{
            resp.getWriter().write("invalid data");
        }
    }
}

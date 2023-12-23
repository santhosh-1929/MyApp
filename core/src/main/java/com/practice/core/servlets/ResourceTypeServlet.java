package com.practice.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

/*
This is a servlet to print something when a page is accessed of same resource type mentioned in the servlet.
R7 Annotations used.
 */
@Component(service = {Servlet.class})
        @SlingServletResourceTypes (
                resourceTypes = "practice/components/page" ,
                selectors = "usingres" ,
                methods = HttpConstants.METHOD_GET ,
                extensions = "json")
public class ResourceTypeServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        resp.getWriter().write(" Accessed Servlet using resource type");
    }
}


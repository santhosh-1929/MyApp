package com.practice.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Iterator;

/*
This is a servlet to print the title and path of the child pages under a parent page.
R7 Annotations used.
 */
@Component(service = {Servlet.class})
@SlingServletPaths(
        value = "/bin/childpages"
)
public class ChildPageServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUid = 1L;
    private final Logger log = LoggerFactory.getLogger(ChildPageServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException{
        resp.setContentType("application/json");

        final ResourceResolver resourceResolver = req.getResourceResolver();
        Page page = (resourceResolver.adaptTo(PageManager.class)).getPage("/content/practice/us/en");
        JSONArray pagesArray = new JSONArray();

        try {
            Iterator<Page> childPages = page.listChildren();
            while (childPages.hasNext()) {
                Page childPage = childPages.next();
                JSONObject pageObject = new JSONObject();
                pageObject.put(childPage.getTitle(), childPage.getPath());
                pagesArray.put(pageObject);
            }
        }
        catch (JSONException e){
            log.info("error" + e.getMessage());
        }
        resp.getWriter().write(pagesArray.toString());

    }
}

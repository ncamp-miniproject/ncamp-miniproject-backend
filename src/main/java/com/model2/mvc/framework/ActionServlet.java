package com.model2.mvc.framework;

import com.model2.mvc.common.db.SQLContainer;
import com.model2.mvc.common.exception.UnsupportedPathException;
import com.model2.mvc.common.util.HttpUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionServlet extends HttpServlet {
    private static final long serialVersionUID = 1619349383015568639L;

    private RequestMapping mapper;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext servletContext = super.getServletContext();
        this.mapper = RequestMapping.getInstance(servletContext.getInitParameter("actionmapping"), servletContext);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = url.substring(contextPath.length());

        System.out.println("Path: " + path);
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Content-Type: " + request.getHeader("Content-Type"));
        System.out.println("Cookie: " + request.getHeader("Cookie"));

        try {
            Action action = mapper.getAction(path);

            String resultPage = action.execute(request, response);
            String result = resultPage.substring(resultPage.indexOf(":") + 1);

            if (resultPage.startsWith("forward:")) {
                HttpUtil.forward(request, response, result);
            } else {
                HttpUtil.redirect(response, result);
            }
        } catch (UnsupportedPathException e) {
            // TODO: throw 404 Not Found status
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        this.mapper.destroyAllActions();
    }
}
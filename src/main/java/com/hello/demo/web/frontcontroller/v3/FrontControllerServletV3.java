package com.hello.demo.web.frontcontroller.v3;

import com.hello.demo.web.frontcontroller.ModelView;
import com.hello.demo.web.frontcontroller.MyView;
import com.hello.demo.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.hello.demo.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.hello.demo.web.frontcontroller.v3.controller.MemberSaveControllerV3;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3",urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap=new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV3 controllerV3=controllerMap.get(requestURI);
        if(controllerV3==null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> params = createParamMap(req);
        ModelView modelView = controllerV3.process(params);

        String viewName=modelView.getViewName();
        MyView myView = viewResolver(viewName);
        myView.render(modelView.getModel(),req,resp);
    }

    private MyView viewResolver(String viewName) {
        MyView myView=new MyView("/WEB-INF/views/" + viewName + ".jsp");
        return myView;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String,String> params=new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramName->params.put(paramName, req.getParameter(paramName)));
        return params;
    }
}

package com.hello.demo.web.frontcontroller.v4;

import com.hello.demo.web.frontcontroller.MyView;
import com.hello.demo.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.hello.demo.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.hello.demo.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap=new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV4 controllerV3=controllerMap.get(requestURI);
        if(controllerV3==null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> params = createParamMap(req);
        Map<String,Object> model=new HashMap<>();
        String viewName = controllerV3.process(params,model);

        MyView myView = viewResolver(viewName);
        myView.render(model,req,resp);
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

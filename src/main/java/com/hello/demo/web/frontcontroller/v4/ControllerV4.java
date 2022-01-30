package com.hello.demo.web.frontcontroller.v4;

import com.hello.demo.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV4 {

    String process(Map<String,String> paramMap,Map<String,Object> model);
}

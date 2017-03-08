package com.controller;

import com.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IO on 24.11.2016.
 */
public class ControllerResolver {
    private RequestMethod requestMethod;
    private String method;
    private Object controller;
    private String view;


    public ControllerResolver() {
    }



    public ControllerResolver(RequestMethod requestMethod, String method, Object controller, String view) {
        this.requestMethod = requestMethod;
        this.method = method;
        this.controller = controller;
        this.view = view;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ControllerResolver that = (ControllerResolver) o;

        if (requestMethod != that.requestMethod) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        return controller != null ? controller.equals(that.controller) : that.controller == null;
    }

    @Override
    public int hashCode() {
        int result = requestMethod != null ? requestMethod.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (controller != null ? controller.hashCode() : 0);
        return result;
    }
}

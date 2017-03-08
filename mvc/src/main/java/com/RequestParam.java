package com;

/**
 * Created by IO on 25.11.2016.
 */
public class RequestParam {
    private String title;
    private Class aClass;

    public RequestParam() {
    }

    public RequestParam(String title, Class aClass) {
        this.title = title;
        this.aClass = aClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}

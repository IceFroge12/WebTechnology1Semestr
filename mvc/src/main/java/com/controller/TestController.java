package com.controller;

import com.annotation.MappingRequest;
import com.annotation.OwnController;
import com.annotation.RequestMethod;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IO on 25.11.2016.
 */
@OwnController()
public class TestController {

    private static TestController instance = null;

    protected TestController() {

    }

    public static TestController getInstance(SessionFactory sessionFactory){
        if (null == instance){
            synchronized (CityController.class){
                if (null == instance){
                    instance = new TestController();

                }
            }
        }
        return instance;
    }

    @MappingRequest(value = "/test", method = RequestMethod.GET, view = "test")
    public Map<String, String> testGet(Map<String, String> params){
        return new HashMap<String, String>() {{
            put("method","testGet");
        }};
    }

    @MappingRequest(value = "/test", method = RequestMethod.POST, view = "test")
    public Map<String, String> testPost(Map<String, String> params){
        return new HashMap<String, String>() {{
            put("method","testPost");
        }};
    }
}

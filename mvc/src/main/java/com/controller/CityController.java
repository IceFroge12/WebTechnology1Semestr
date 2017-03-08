package com.controller;

import com.annotation.MappingRequest;
import com.annotation.OwnController;
import com.annotation.Param;
import com.annotation.RequestMethod;
import com.model.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IO on 24.11.2016.
 */
@OwnController()
public class CityController {

    private static CityController instance = null;

    private SessionFactory sessionFactory;
    private final String ID = "id";

    protected CityController() {

    }

    public static CityController getInstance(SessionFactory sessionFactory){
        if (null == instance){
            synchronized (CityController.class){
                if (null == instance){
                    instance = new CityController();
                    instance.sessionFactory = sessionFactory;
                }
            }
        }
        return instance;
    }

    @MappingRequest(value = "/city", method = RequestMethod.GET, view = "city")
    public HashMap<String, String> testGet(Map<String, String[]> params){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query<City> query = session.createNativeQuery("select * from public.city where city.id = ?;", City.class);
        query.setParameter(1, Long.parseLong(params.get(ID)[0]));
        City city = query.getSingleResult();
        return new HashMap<String, String>() {{
            put("id", Long.toString(city.getId()));
            put("title", city.getTitle());
        }};
    }

    public static CityController getInstance() {
        return instance;
    }

    public static void setInstance(CityController instance) {
        CityController.instance = instance;
    }
}

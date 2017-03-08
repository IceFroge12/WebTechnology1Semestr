package com;

import com.annotation.*;
import com.controller.ControllerResolver;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.CriteriaImpl;
import org.reflections.Reflections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by IO on 24.11.2016.
 */
@Config(packageToScan = "com/controller", viewToScan = "template")
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {


    private Map<String, List<ControllerResolver>> mappingResolver;
    private Predicate<ControllerResolver> controllerResolverPredicateGET = controllerResolver -> controllerResolver.getRequestMethod().equals(RequestMethod.GET);
    private Predicate<ControllerResolver> controllerResolverPredicatePOST = controllerResolver -> controllerResolver.getRequestMethod().equals(RequestMethod.POST);
    private SessionFactory sessionFactory;
    private String viewPath;

    @Override
    public void init() throws ServletException {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        mappingResolver = new HashMap<>();
        Class<DispatcherServlet> dispatcherServletClass = DispatcherServlet.class;
        Config annotation = dispatcherServletClass.getAnnotation(Config.class);
        viewPath = annotation.viewToScan();
        Reflections reflections = new Reflections(annotation.packageToScan());
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(OwnController.class);
        annotated.forEach((aClass -> {
            List<Method> allMethods = new ArrayList<>(Arrays.asList(aClass.getDeclaredMethods()));
            allMethods.forEach((method -> {
                if (method.isAnnotationPresent(MappingRequest.class)) {
                    MappingRequest mappingRequest = method.getAnnotation(MappingRequest.class);
                    for (String request : mappingRequest.value()) {
                        for (RequestMethod requestMethod : mappingRequest.method()) {
                            ControllerResolver controllerResolver = new ControllerResolver();
                            controllerResolver.setRequestMethod(requestMethod);
                            controllerResolver.setMethod(method.getName());
                            controllerResolver.setView(mappingRequest.view());
                            try {
                                controllerResolver.setController(aClass.getMethod("getInstance", SessionFactory.class).invoke(null, sessionFactory));
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                sessionFactory.close();
                                e.printStackTrace();
                            }
                            if (mappingResolver.get(request) == null) {
                                List<ControllerResolver> controllerResolverList = new ArrayList<>();
                                controllerResolverList.add(controllerResolver);
                                mappingResolver.put(request, controllerResolverList);
                            } else {
                                List<ControllerResolver> controllerResolverList = mappingResolver.get(request);
                                controllerResolverList.add(controllerResolver);
                                mappingResolver.replace(request, controllerResolverList);
                            }
                        }
                    }

                }
            }));
        }));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ControllerResolver controllerResolver = null;
        try {
            controllerResolver = getControllerResolver(req, resp, controllerResolverPredicateGET);
        } catch (UnsatisfiedLinkError e) {
            out.print(e.getMessage());
        }
        if (null != controllerResolver) {
            Map<String, String> result = null;
            try {
                result = execute(controllerResolver, req, resp);
                out.print(replaceValue(result, controllerResolver));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        ControllerResolver controllerResolver = null;
        try {
            controllerResolver = getControllerResolver(req, resp, controllerResolverPredicatePOST);
        } catch (UnsatisfiedLinkError e) {
            out.print(e.getMessage());
        }
        if (null != controllerResolver) {
            try {
                Map<String, String> result = execute(controllerResolver, req, resp);
                out.print(replaceValue(result, controllerResolver));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private String replaceValue(Map<String, String> result, ControllerResolver controllerResolver) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("C:\\Users\\IO\\WT\\WTProject\\mvc\\src\\main\\resources\\" + viewPath + "\\" + controllerResolver.getView() + ".html")));
        for (String s : result.keySet()) {
            String v = result.get(s);
            contents = contents.replace("${" + s + "}", v);
        }


        return contents;
    }

    private ControllerResolver getControllerResolver(HttpServletRequest req, HttpServletResponse resp, Predicate<ControllerResolver> predicate) throws IOException, UnsatisfiedLinkError {
        List<ControllerResolver> controllerResolverList = mappingResolver.get(req.getRequestURI());
        if (null == controllerResolverList) {
            throw new UnsatisfiedLinkError("Controller for - " + req.getRequestURI() + " not fount");
        } else {
            Optional<ControllerResolver> cr = controllerResolverList
                    .stream()
                    .filter(predicate).findFirst();
            if (cr.isPresent()) {
                return cr.get();
            } else {
                throw new UnsatisfiedLinkError("Appropriate method to POST request not found");
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> execute(ControllerResolver controllerResolver, HttpServletRequest req, HttpServletResponse resp) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> objectObjectMap = new HashMap<>();
        Object object = controllerResolver.getController();
//        Method[] methodList = object.getClass().getMethods();
//        Method methodToInvoke = null;
//        for (Method method : methodList) {
//            if (method.isAnnotationPresent(MappingRequest.class)) {
//                MappingRequest mappingRequest = method.getAnnotation(MappingRequest.class);
//                for (RequestMethod requestMethod : mappingRequest.method()) {
//                    if (method.getName().equals(controllerResolver.getMethod()) & requestMethod.equals(controllerResolver.getRequestMethod())) {
//                        methodToInvoke = method;
//                    }
//                }
//            }
//        }
//        ArrayList<RequestParam> requestParams = new ArrayList<>();
//        Annotation[][] parameterAnnotations = methodToInvoke.getParameterAnnotations();
//        Class[] parameterTypes = methodToInvoke.getParameterTypes();
//        int i = 0;
//        for (Annotation[] annotations : parameterAnnotations) {
//            Class parameterType = parameterTypes[i++];
//
//            for (Annotation annotation : annotations) {
//                if (annotation instanceof Param) {
//                    Param myAnnotation = (Param) annotation;
//                    RequestParam requestParam = new RequestParam();
//                    requestParam.setTitle(myAnnotation.name());
//                    requestParam.setaClass(parameterType);
//                }
//            }
//        }
//        Object[] objects = new Object[requestParams.size()];
//        for (RequestParam requestParam : requestParams) {
//
//        }
        return (Map<String, String>) object.getClass().getMethod(controllerResolver.getMethod(), Map.class).invoke(object, req.getParameterMap());
    }

    @Override
    public void destroy() {
        sessionFactory.close();
        super.destroy();
    }
}

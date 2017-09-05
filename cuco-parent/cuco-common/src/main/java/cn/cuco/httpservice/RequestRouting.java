package cn.cuco.httpservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cn.cuco.annotation.API;

/**
 * Created by WangShuai on 2016/7/30.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRouting {
    private String uri;

    private String method;

    private String name;

    private boolean auth;

    public static RequestRouting getReqRouting(HandlerMethod handlerMethod){
        RequestRouting requestRouting = new RequestRouting();
        RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);
        String uri = RelyCollections3.first(requestMapping.value());
        RequestMethod method = RelyCollections3.first(requestMapping.method());
        requestRouting.setMethod(method==null?"":method.name());
        requestRouting.setUri(uri);

        API api = handlerMethod.getMethodAnnotation(API.class);
        if(api == null){
            requestRouting.setAuth(true);
            requestRouting.setName("--");
        }else{
            requestRouting.setAuth(api.auth());
            requestRouting.setName(api.value());
        }
        return requestRouting;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }
}
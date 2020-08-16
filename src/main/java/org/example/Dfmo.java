package org.example;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;

@Controller

public class Dfmo {
    @CrossOrigin( allowCredentials = "true",origins = "http://127.0.0.1:9091/mypost",maxAge = 3600)
    @RequestMapping(value = "/b")
    private static  String B(){
        Url u=new Url("http://15.72.158.72:8081/getway/api/Proxy/HandleByKey/8d83b5cd-23f1-4b85-8a02-4cec26eb5341");

        Body b=new Body("{\"apikey\""+":"+"\"201907020930468d83b5cd-23f\""+","+"\"paramjson\""+":"+"{"+"\"SFZH\""+":"+"\"370206197712043637\"}}");

        Type t=new Type("0");

        return "redirect:http://127.0.0.1:9090/mypost";
    }

    /*public static void main(String[] args) {
        String json = JSONObject.toJSONString("{\"apikey\":\"201907020930468d83b5cd-23f\",\"paramjson\":{\"SFZH\":\"370206197712043637\"}");
        System.out.println(json);
    }*/
}

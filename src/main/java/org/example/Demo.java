package org.example;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class Demo {

    @RequestMapping("/mypost")
    private static String mydear(Url u,Body b,Type t) throws IOException {


        if (t.getType().equals("0")) {
            return "接口地址:" + u + "<br>" + "入参:" + b + "<br>" + "类型:" + t + "<br>" + myPost(u.toString(), b.toString());
        } else {
            /* return "外网"+"接口地址:" + u +"<br>"+"入参:"+ b+"<br>"+"类型:"+t+"<br>"+myPost(u,b);*/
            return "";
        }
    }


    private static String myPost(String url, String body) throws IOException {
        //建立连接请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post请求
        HttpPost httpPost = new HttpPost(url);
        //创建参数队列

        httpPost.setHeader("Content-Type", "application/json");
        StringEntity s = new StringEntity(body, "UTF-8");
        s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httpPost.setEntity(s);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity == null) {

        }
        String S = "接收到的响应信息:--------" + EntityUtils.toString(entity, "UTF-8");
        //资源释放
        response.close();
        httpClient.close();
        return S;
    }

    private static void myGet() throws IOException {

        //建立连接请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建get请求
        HttpGet get = new HttpGet("http://15.72.177.48/");
        CloseableHttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        //响应成功在操作
        if (statusCode == 200) {
            //获取响应实体
            HttpEntity entity = response.getEntity();

            //打印响应内容长度
            System.out.println("响应文本长度:" + entity.getContentLength());
            //打印响应内容
            System.out.println("响应内容如下:\n" + EntityUtils.toString(entity, "UTF-8"));
        }
        System.out.println("--------------------------响应内容打印完毕");
        //释放资源
        response.close();
        httpClient.close();
    }
}
    /*public static void main(String[] args) {
        try {
            //  myGet();
            String u="http://15.72.158.72:8081/getway/api/Proxy/HandleByKey/cb1e6972-ebc8-4439-ac98-be48af564bd3";
            String b="{\"apikey\":\"20200506085531cb1e6972-ebc\",\"paramjson\":{}}";
            myPost(u,b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

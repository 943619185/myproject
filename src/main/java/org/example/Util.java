package org.example;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.*;



public class Util {



    /**
     * 发送get请求
     * @param url
     * @param decodeCharset
     * @return
     */
    public static String sendGetRequest(String url, String decodeCharset) {
        HttpClient httpclient = new DefaultHttpClient();
        String responseContent = null;
        HttpGet httpGet = new HttpGet(url);
        HttpEntity entity = null;
        try {
            HttpResponse response = httpclient.execute(httpGet);
            System.out.println(response);
            entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
            }
        } catch (Exception e) {

        } finally {
            try {
                EntityUtils.consume(entity);
                httpclient.getConnectionManager().shutdown();
            } catch (Exception ex) {

            }
        }
        return responseContent;
    }

    /**
     * post 请求
     * @param reqURL
     * @param  data  可以为param="key1=value1&key2=value2"的一串字符串,或者是jsonObject
     * @return
     */
    public static String sendHttpPostRequest(String reqURL, String data) {
        HttpClient httpclient = new DefaultHttpClient();
        String respStr = "";
        try {
            HttpPost httppost = new HttpPost(reqURL);
            StringEntity strEntity = new StringEntity(data, "UTF-8");
            strEntity.setContentType("application/x-www-form-urlencoded");
            httppost.setEntity(strEntity);
            System.out.println(EntityUtils.toString(strEntity));
            System.out.println("executing request " + httppost.getRequestLine());

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {
                System.out.println("返回数据长度: " + resEntity.getContentLength());
                respStr = EntityUtils.toString(resEntity);
                System.out.println("respStr " + respStr);
            }

        } catch (ClientProtocolException e) {

        } catch (IOException e) {

        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return respStr;
    }

    /**
     * 发送post请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendHttpPostRequest(String url, Map<String, String> params) {
        String respStr = "";
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
        httpclient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        System.out.println("url: " + url);
        System.out.println("params: " + params);
        try {
            HttpPost post = new HttpPost(url);
            List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                postData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData, "UTF-8");
            post.setEntity(entity);
            HttpResponse response = httpclient.execute(post);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                System.out.println("返回数据长度: " + resEntity.getContentLength());
                respStr = EntityUtils.toString(resEntity);
                System.out.println("respStr " + respStr);
            }
        } catch (ClientProtocolException e) {
            System.out.println("sendHttpPostRequest : " +e);
        } catch (IOException e) {
            System.out.println("sendHttpPostRequest : " +e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return respStr;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String params = "bankcard=6217856101018144878&key=316fcfd892e7e4d24ded8699f1f7d957";
        String resultstr = Util.sendHttpPostRequest("http://apis.juhe.cn/bankcardcore/query", params);
        System.out.println(resultstr);
       /* ObjectMapper mapper = new ObjectMapper();
        try {
            Map map = mapper.readValue(resultstr, Map.class);
            Set<Map.Entry> set = map.entrySet();
            for (Map.Entry entry : set) {
                System.out.println(entry.getKey() + "==" + entry.getValue());
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }*/
    }

}

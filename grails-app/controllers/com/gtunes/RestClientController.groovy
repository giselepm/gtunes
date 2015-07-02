package com.gtunes

import grails.converters.JSON
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.codehaus.groovy.grails.web.json.JSONObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


class RestClientController {

    private String cookies;
    private HttpClient client = HttpClientBuilder.create().build();
    private final String USER_AGENT = "Mozilla/5.0";

    def index() {


        String url = "https://api.spotify.com/v1/search?q='tim%20maia'&type=artist"

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        HttpEntity responseEntity = response.getEntity()
        String jsonAsString = EntityUtils.toString(responseEntity)
        def json = JSON.parse(jsonAsString)

        json.artists.items.eachWithIndex { item, count ->
            println "id = ${item.id}, count = ${count}"
        }

        json.artists.items.each { item ->
            println "id = ${item.id}, count = ${count}"
//            return
        }

        for(JSONObject item: json.artists.items){
            println "id = ${item.id}, count = ${count}"
//            break
        }

        render json

//        //buscar info sobre tim maia
//        String url = "https://api.spotify.com/v1/artists/0jOs0wnXCu1bGGP7kh5uIu"
//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(url);
//
//        HttpResponse response = client.execute(request);
//
//        HttpEntity responseEntity = response.getEntity()
//        String jsonAsString = EntityUtils.toString(responseEntity)
//        def json = JSON.parse(jsonAsString)
//
//        render json

//      main class do exemplo
//        String url = "https://accounts.google.com/ServiceLoginAuth";
//        String gmail = "https://mail.google.com/mail/";
//
//        // make sure cookies is turn on
//        CookieHandler.setDefault(new CookieManager());
//
//        String page = getPageContent(url);
//
//        List<NameValuePair> postParams =
//                getFormParams(page, "username","password");
//
//        sendPost(url, postParams);
//
//        String result = getPageContent(gmail);
//        render result
//
//        render "Done"

    }

    private String getPageContent(String url) throws Exception {

        HttpGet request = new HttpGet(url);

        request.setHeader("User-Agent", USER_AGENT);
        request.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.setHeader("Accept-Language", "en-US,en;q=0.5");

        HttpResponse response = client.execute(request);
        int responseCode = response.getStatusLine().getStatusCode();

        render("\nSending 'GET' request to URL : " + url);
        render("Response Code : " + responseCode);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
//httpcomponent entity utils / param response
        //JSON.element.parse
        // set cookies
        setCookies(response.getFirstHeader("Set-Cookie") == null ? "" :
                response.getFirstHeader("Set-Cookie").toString());

        return result.toString();

    }

    private void sendPost(String url, List<NameValuePair> postParams)
            throws Exception {

        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("Host", "accounts.google.com");
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        post.setHeader("Accept-Language", "en-US,en;q=0.5");
        post.setHeader("Cookie", getCookies());
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Referer", "https://accounts.google.com/ServiceLoginAuth");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        post.setEntity(new UrlEncodedFormEntity(postParams));

        HttpResponse response = client.execute(post);

        int responseCode = response.getStatusLine().getStatusCode();

        render("\nSending 'POST' request to URL : " + url);
        render("Post parameters : " + postParams);
        render("Response Code : " + responseCode);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        render(result);

    }



    public List<NameValuePair> getFormParams(
            String html, String username, String password)
            throws UnsupportedEncodingException {

        render("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
        Element loginform = doc.getElementById("gaia_loginform");
        Elements inputElements = loginform.getElementsByTag("input");

        List<NameValuePair> paramList = new ArrayList<NameValuePair>();

        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("Email"))
                value = username;
            else if (key.equals("Passwd"))
                value = password;

            paramList.add(new BasicNameValuePair(key, value));

        }

        return paramList;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    //        String url = "http://www.google.com/search?q=httpClient";
//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(url);
//
//        // add request header
//        request.addHeader("User-Agent", "USER_AGENT")
////        request.addHeader("User-Agent", USER_AGENT);
//        HttpResponse response = client.execute(request);
//
//        System.out.println("Response Code : "
//                + response.getStatusLine().getStatusCode());
//
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        render result

//        String url = "https://selfsolve.apple.com/wcResults.do";
//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpPost post = new HttpPost(url);
//
//        // add header
//        post.setHeader("User-Agent", "USER_AGENT");
//
//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
//        urlParameters.add(new BasicNameValuePair("cn", ""));
//        urlParameters.add(new BasicNameValuePair("locale", ""));
//        urlParameters.add(new BasicNameValuePair("caller", ""));
//        urlParameters.add(new BasicNameValuePair("num", "12345"));
//
//        post.setEntity(new UrlEncodedFormEntity(urlParameters));
//
//        HttpResponse response = client.execute(post);
//        System.out.println("Response Code : "
//                + response.getStatusLine().getStatusCode());
//
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        render result

    def googleFlights(){


        def json = """{
            "request": {
                "passengers": {
                    "adultCount": 1
                },
                "slice": [
                        {
                            "origin": "BOS",
                            "destination": "LAX",
                            "date": "2015-10-06"
                        },
                        {
                            "origin": "LAX",
                            "destination": "BOS",
                            "date": "2015-10-26"
                        }
                ]
            }
        }"""



    }


}

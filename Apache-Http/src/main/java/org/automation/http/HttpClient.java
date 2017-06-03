package org.automation.http;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
/*.tadh.dto.search.rest.response.SearchResponse;
.tadh.qa.distribution.util.SoapToolsException;
.tadh.qa.distribution.util.XMLUtils;*/
import org.apache.commons.httpclient.HttpMethod;
import org.apache.http.*;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.automation.http.exception.SoapException;
import org.automation.http.utils.XMLUtils;

import javax.net.ssl.*;

public class HttpClient {

    private String baseUrl;
    private List<Header> requestHeaders;
    private HttpMethod httpMethod;
    private String requestContent;
    private Request httpRequest;
    private HttpClientResponse httpClientResponse;
    private String requestType;
    private boolean isProxyOn = false;

    public HttpClient(String requestType) {
        this.requestType = requestType;
    }

    public HttpClient(String baseUrl, List<Header> requestHeaders, HttpMethod httpMethod, String requestContent, String requestType) {
        this.baseUrl = baseUrl;
        this.requestHeaders = requestHeaders;
        this.httpMethod = httpMethod;
        this.requestContent = requestContent;
        this.requestType = requestType;

    }

    public HttpClient setURL(String URL) {
        this.baseUrl = URL;
        return this;
    }

    public HttpClient setRequestHeaders(List<Header> requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }

    public HttpClient setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public HttpClient setRequestContent(String requestContent) {
        this.requestContent = requestContent;
        return this;
    }

    public HttpClientResponse getParcedXMLResponse() {
        try {
            HttpResponse httpResponse = callHttpService();
            httpClientResponse = new HttpClientResponse();
            httpClientResponse.setHttpStatusCode(httpResponse.getStatusLine().getStatusCode());
            httpClientResponse.setHttpResponseBody(XMLUtils.parseFileStream2XmlDocument(httpResponse.getEntity().getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SoapException e) {
            e.printStackTrace();
        }
        return httpClientResponse;
    }

    //TODO - move to JSON utils
    public SearchResponse getSearchResponse() {
        SearchResponse searchResponse = new SearchResponse();
        HttpResponse httpResponse = callHttpService();
        try {
            ObjectMapper mapper = new ObjectMapper();
            searchResponse = mapper.readValue(httpResponse.getEntity().getContent(), SearchResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    public HttpResponse callHttpService() {
        HttpResponse httpResponse = null;
        try {
            HttpClientBuilder b = HttpClientBuilder.create();
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
            b.setSslcontext(sslContext);
            HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslSocketFactory)
                    .build();
            PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            b.setConnectionManager(connMgr);
            b.setDefaultHeaders(requestHeaders);
            if (isProxyOn) {
                b.setProxy(new HttpHost("localhost", 8800));
            }
            CloseableHttpClient httpClient = b.build();

            Executor executor = Executor.newInstance(httpClient);

            httpRequest = Request
                    .Post(baseUrl)
                    .bodyString(requestContent, requestType.contains("JSON") ? contentTypeTextJsonUtf8() : contentTypeTextXmlUtf8());

            Response httpResponseContainer = executor.execute(httpRequest);
            httpResponse = httpResponseContainer.returnResponse();

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

    private ContentType contentTypeTextXmlUtf8() {
        return ContentType.create(ContentType.TEXT_XML.getMimeType(), Consts.UTF_8);
    }

    private ContentType contentTypeTextJsonUtf8() {
        return ContentType.create(ContentType.APPLICATION_JSON.getMimeType(), Consts.UTF_8);
    }
}

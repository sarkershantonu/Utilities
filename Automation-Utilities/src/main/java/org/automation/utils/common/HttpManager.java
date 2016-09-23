


import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.ssl.SSLContexts.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HttpManager {
    private static final String TRANSLATION_URL = Config.BASE_URL;

    public HttpManager() throws Exception {
        if (httpClient == null){
            generateHttpClient();
        }
    }

    public static final String HTTPS_RCAS_DEV = Config.RCAS_URL;
    protected static Logger logger = LoggerFactory.getLogger(HttpManager.class);
    private HttpClient httpClient;

	private void generateHttpClient() throws Exception {
		if (ConfigConstants.LOCAL.equals(Config.ENV)) {
			generateSimpleHttpClient();
			logger.info("Manager created for Local env, not include certificates");
		} else {
			generateAuthenticationNeedHttpClient();
			logger.info("Manager created for " + Config.ENV + "  include certificates");
		}
	}

	private void generateSimpleHttpClient() throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setMaxRedirects(100).setConnectionRequestTimeout(1000000).setCircularRedirectsAllowed(true)
				.setRedirectsEnabled(true).setCookieSpec(CookieSpecs.DEFAULT).build();
		final ConnectionConfig connectionConfig = ConnectionConfig.custom().setBufferSize(8176).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setDefaultMaxPerRoute(10);
		httpClient = HttpClients.custom().setMaxConnTotal(20).setDefaultRequestConfig(requestConfig).setDefaultConnectionConfig(connectionConfig).build();
	}

	private void generateAuthenticationNeedHttpClient() throws Exception {
		HttpClientContext context = HttpClientContext.create();
		httpClient = generateClientUsingCacerts(context);
		doDomainTranslation(context, httpClient);
	}

    private static HttpClient generateClientUsingCacerts(HttpClientContext context) throws Exception {
        SSLContext sslContext = custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
        RequestConfig requestConfig = RequestConfig.custom().setMaxRedirects(100).setConnectionRequestTimeout(60_000)
                .setCircularRedirectsAllowed(true).setRedirectsEnabled(true)
                .setCookieSpec(CookieSpecs.DEFAULT).build();
        final ConnectionConfig connectionConfig = ConnectionConfig.custom().setBufferSize(8176).build();
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//        connectionManager.setDefaultMaxPerRoute(10);

        HttpClient httpClient = HttpClients
                .custom()
                .setMaxConnTotal(20)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultConnectionConfig(connectionConfig)
                .setSSLSocketFactory(
                        new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE))
//                .setConnectionManager(connectionManager)
                .build();

        HttpGet rcasGet = new HttpGet(HTTPS_RCAS_DEV);
        rcasGet.addHeader(new BasicScheme().authenticate(new UsernamePasswordCredentials(Config.FIRST_USER.getName(),
                Config.FIRST_USER.getPassword()), rcasGet, context));
        HttpResponse resp = httpClient.execute(rcasGet, context);
        if (resp.getStatusLine().getStatusCode() == 200) {
            EntityUtils.consume(resp.getEntity());
        } else {
            throw new Exception("Failed authentication against RCAS");
        }
        return httpClient;
    }

    private static HttpClient doDomainTranslation(HttpClientContext context, HttpClient httpClient) throws Exception {
        logger.info("domain translation has been started");
        HttpGet dummyGet = new HttpGet(TRANSLATION_URL);

        HttpResponse dummyResp = httpClient.execute(dummyGet, context);
        if (dummyResp.getStatusLine().getStatusCode() == 200) {
            EntityUtils.consume(dummyResp.getEntity());
        } else {
            throw new Exception("Failed authentication against RCAS for *.swissbank.com doamin");
        }
        logger.info("domain translation has been successfully finished");
        return httpClient;
    }

    public HttpResponse doGet(String url) throws IOException {
        logger.info("GET url: " + url);

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);

        assertThat(response.getStatusLine().getStatusCode(), is(200));

        return response;
    }

    public HttpResponse doPost(String url){
        logger.info(String.format("Post with url: %s will be sent", url));
        HttpResponse httpResponse = null;
        try {
            HttpPost post = new HttpPost(url);
            StopWatch sw = new StopWatch();
            sw.start();
            httpResponse = httpClient.execute(post);
            sw.stop();
            logger.info("Post was executed for: " + sw.toString());
            int status = httpResponse.getStatusLine().getStatusCode();
            assertThat(status, is(200));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(httpResponse, Matchers.notNullValue());
        return httpResponse;
    }

    public HttpResponse doPost(String url, HttpEntity entity){
        logger.info(String.format("Post with url: %s will be sent", url));
        HttpResponse httpResponse = null;
        try {
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setEntity(entity);
            StopWatch sw = new StopWatch();
            sw.start();
            httpResponse = httpClient.execute(post);
            sw.stop();
            logger.info("Post was executed for: " + sw.toString());
            int status = httpResponse.getStatusLine().getStatusCode();
            assertThat(status, is(200));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(httpResponse, Matchers.notNullValue());
        return httpResponse;
    }

    public HttpResponse doPcsPost(String xml) throws IOException {
        List<NameValuePair> nameValuePairList = new ArrayList<>(1);
        nameValuePairList.add(new BasicNameValuePair("XML", xml));

        return doPost(Config.PCS_WS_URL, new UrlEncodedFormEntity(nameValuePairList));
    }

    public static HttpManager getInstance() throws Exception {
        return new HttpManager();
    }
}

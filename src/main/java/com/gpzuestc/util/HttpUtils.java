package com.gpzuestc.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpUtils {
    private static final Logger logger = LogManager.getLogger(HttpUtils.class);

    private static final int REQUEST_TIMEOUT = 5 * 1000; // 设置请求超时10秒钟
    private static final int TIMEOUT = 5 * 1000; // 连接超时时间
    private static final int SO_TIMEOUT = 5 * 1000; // 数据传输超时
    private static final String CHARSET = "UTF-8";

    private static PoolingHttpClientConnectionManager connManager = null;
    private static CloseableHttpClient httpClient = null;

    static {
        try {
            // SSLContext
            SSLContextBuilder sslContextbuilder = new SSLContextBuilder();
            sslContextbuilder.useTLS();
            SSLContext sslContext = sslContextbuilder.loadTrustMaterial(null,
                    new TrustStrategy() {
                        // 信任所有
                        public boolean isTrusted(X509Certificate[] chain,
                                                 String authType) throws CertificateException {
                            return true;
                        }

                    }).build();

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER))
                    .build();

            // Create ConnectionManager
            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            // Create socket configuration
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            connManager.setDefaultSocketConfig(socketConfig);

            // Create message constraints
            MessageConstraints messageConstraints = MessageConstraints.custom()
                    .setMaxHeaderCount(200).setMaxLineLength(2000).build();

            // Create connection configuration
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                    .setMalformedInputAction(CodingErrorAction.IGNORE)
                    .setUnmappableInputAction(CodingErrorAction.IGNORE)
                    .setCharset(Consts.UTF_8)
                    .setMessageConstraints(messageConstraints).build();

            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(200);
            connManager.setDefaultMaxPerRoute(20);

            // Create httpClient
            httpClient = HttpClients.custom().disableRedirectHandling()
                    .setConnectionManager(connManager).build();
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 指定参数名GET方式请求数据
     *
     * @param url
     * @param paramsMap QueryString
     * @return
     */
    public static String doGet(String url, Map<String, String> paramsMap) {
        return doGet(invokeUrl(url, paramsMap));
    }

    /**
     * GET方式请求数据
     *
     * @param url
     */
    public static String doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        logger.info("\r\nrequest url: {}", url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SO_TIMEOUT).setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(REQUEST_TIMEOUT).setStaleConnectionCheckEnabled(true).build();
        httpGet.setConfig(requestConfig);

        long responseLength = 0; // 响应长度
        String responseContent = null; // 响应内容
        String strRep = null;
        Long start = System.currentTimeMillis();
        try {
            // 执行get请求
            HttpResponse httpResponse = httpClient.execute(httpGet);

            // 获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                responseLength = entity.getContentLength();
                responseContent = EntityUtils.toString(entity, CHARSET);// 不能重复调用此方法，IO流已关闭。

                logger.info("\r\nrequest uri: {}\rnresponse status: {}\r\nresponse length: {}\r\nresponse content: {}\r\nspend time:{}ms",
                        httpGet.getURI(), httpResponse.getStatusLine(), responseLength, responseContent, System.currentTimeMillis() - start);

                // 获取HTTP响应的状态码
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    strRep = responseContent; // EntityUtils.toString(httpResponse.getEntity());
                } else if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
                        || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                        || (statusCode == HttpStatus.SC_SEE_OTHER)
                        || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                    // 重定向处理，获得跳转的网址
                    Header locationHeader = httpResponse
                            .getFirstHeader("Location");
                    if (locationHeader != null) {
                        String successUrl = locationHeader.getValue();
                        logger.warn("redirect url:{}", successUrl);
                    }
                }else{
                    throw new Exception(String.format("httpStatus is not ok; httpStatus=%s, url=%s", statusCode, url));
                }

                // Consume response content
                EntityUtils.consume(entity);
                // Do not need the rest
                httpGet.abort();
            }

            // 头信息
//			printRespHeaders(httpResponse);
        } catch (Exception e) {
            logger.error(e.getMessage() + ";spend time=" + (System.currentTimeMillis() - start) +  "ms;url=" + url, e);
            throw new RuntimeException(e);
        } finally {
            httpGet.releaseConnection();
        }

        return strRep;
    }

    /**
     * 不指定参数名的方式来POST数据
     *
     * @param url
     * @param jsonXMLString
     * @return
     */
    public static String doPost(String url, String jsonXMLString) {
        return doPost(url, null, jsonXMLString);
    }

    /**
     * 指定参数名POST方式请求数据
     *
     * @param url
     */
    public static String doPost(String url, Map<String, String> paramsMap) {
        return doPost(url, paramsMap, null);
    }

    private static String doPost(String url, Map<String, String> paramsMap,
                                 String jsonXMLString) {
        HttpPost httpPost = new HttpPost(url);
        logger.info("request url: {}", url);
//		httpPost.setHeader("Content-type", "text/xml; charset=utf-8");

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SO_TIMEOUT).setConnectTimeout(TIMEOUT)
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setStaleConnectionCheckEnabled(true)
                .setExpectContinueEnabled(false).build();

        httpPost.setConfig(requestConfig);// RequestConfig.DEFAULT

        long responseLength = 0; // 响应长度
        String responseContent = null; // 响应内容
        String strRep = null;
        Long start = System.currentTimeMillis();
        try {
            if (paramsMap != null && jsonXMLString == null) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                        getParamsList(paramsMap), CHARSET);
                httpPost.setEntity(entity);
            } else {
                httpPost.setEntity(new StringEntity(jsonXMLString, CHARSET));
            }

            // 执行post请求
            HttpResponse httpResponse = httpClient.execute(httpPost);

            // 获取响应消息实体
            HttpEntity entityRep = httpResponse.getEntity();
            if (entityRep != null) {
                responseLength = entityRep.getContentLength();
                responseContent = EntityUtils.toString(
                        httpResponse.getEntity(), CHARSET);

                // byte[] bytes = EntityUtils.toByteArray(entityRep);

                logger.info("request uri:{}; postData:{} response_status:{}; response_length:{}; response_content:{}; spend time:{}ms",
                        httpPost.getURI(), paramsMap != null ? JsonUtil.toJSONString(paramsMap) :jsonXMLString, httpResponse.getStatusLine(), responseLength, responseContent, System.currentTimeMillis() - start);

                // 获取HTTP响应的状态码
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    strRep = responseContent; // EntityUtils.toString(httpResponse.getEntity());
                } else if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
                        || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                        || (statusCode == HttpStatus.SC_SEE_OTHER)
                        || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                    // 重定向处理，获得跳转的网址
                    Header locationHeader = httpResponse
                            .getFirstHeader("Location");
                    if (locationHeader != null) {
                        String successUrl = locationHeader.getValue();
                        logger.info(successUrl);
                    }
                }else{
                    throw new Exception(String.format("httpStatus is not ok; httpStatus=%s, url=%s, param=%s", statusCode, url, paramsMap != null ? JsonUtil.toJSONString(paramsMap) :jsonXMLString));
                }

                // Consume response content
                EntityUtils.consume(entityRep);
                // Do not need the rest
                httpPost.abort();

                // 头信息
//				printRespHeaders(httpResponse);
            }
        } catch (Exception e) {
            logger.error(e.getMessage() + ";spend time=" + (System.currentTimeMillis() - start) + "ms;url=" + url + ";param=" + (paramsMap != null ? JsonUtil.toJSONString(paramsMap) :jsonXMLString), e);
            throw new RuntimeException(e);
        } finally {
            httpPost.releaseConnection();
        }

        return strRep;
    }

    // 打印头信息
    private static void printRespHeaders(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder("\r\n---------response header--------------\r\n");
        // 头信息
        HeaderIterator it = httpResponse.headerIterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("\r\n");
        }
        sb.append("------------------------------");
        logger.info(sb.toString());
    }

    // 读取内容
    protected static String readContent(InputStream in) throws Exception {
        BufferedInputStream buffer = new BufferedInputStream(in);
        StringBuilder builder = new StringBuilder();
        byte[] bytes = new byte[1024];
        int line = 0;
        while ((line = buffer.read(bytes)) != -1) {
            builder.append(new String(bytes, 0, line, CHARSET));
        }

        return builder.toString();
    }

    /**
     * GET方式传参
     *
     * @param url
     * @param paramsMap
     * @return
     */
    public static String invokeUrl(String url, Map<String, String> paramsMap) {
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        int i = 0;
        if (paramsMap != null && paramsMap.size() > 0) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                if (i == 0 && !url.contains("?")) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(entry.getKey());
                sb.append("=");
                String value = entry.getValue();
                try {
                    sb.append(URLEncoder.encode(value, CHARSET));
                } catch (UnsupportedEncodingException e) {
                    logger.error("encode http get params error, value is "+ value, e);
                }

                i++;
            }
        }

        return sb.toString();
    }

    /**
     * 将传入的键/值对参数转换为NameValuePair参数集
     *
     * @param paramsMap 参数集, 键/值对
     * @return NameValuePair参数集
     */
    private static List<NameValuePair> getParamsList(
            Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.size() == 0) {
            return null;
        }

        // 创建参数队列
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> map : paramsMap.entrySet()) {
            params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
        }

        return params;
    }

    /**
     * 格式化XML
     *
     * @param inputXML
     * @return
     * @throws Exception
     */
//	public static String formatXML(String inputXML) throws Exception {
//		Document doc = DocumentHelper.parseText(inputXML);
//		StringWriter out = null;
//		if (doc != null) {
//			try {
//				OutputFormat format = OutputFormat.createPrettyPrint();
//				out = new StringWriter();
//				XMLWriter writer = new XMLWriter(out, format);
//				writer.write(doc);
//				writer.flush();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				out.close();
//			}
//
//			return out.toString();
//		}
//
//		return inputXML;
//	 }

//    public static String send(String url, MultipartFile file) throws IOException {
//    	return null;
//    }

    /**
     * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
     *
     * @param url      请求地址 form表单url地址
     * @return String url的响应信息返回值
     * @throws java.io.IOException
     */
    public static String send(String url, String name, InputStream inputStream) throws IOException {
        String result = null;
        /**
         * 第一部分
         */
        URL urlObj = new URL(url);
        // 连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        /**
         * 设置关键值
         */
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post方式不能使用缓存

        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");

        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        // 请求正文信息

        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
                + name + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);

        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        InputStream in = inputStream;
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

        out.write(foot);

        out.flush();
        out.close();


        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
            throw new IOException("数据读取异常");
        } finally {
            if (reader != null) {
                reader.close();
            }

        }
        return result;
    }


    public static void main(String[] args) {
        //String jsonString = JSONUtils.toJSONString("<xml><ab>test</ab></xml>");
        //System.out.println(jsonString);
        //HttpUtils.doPost("http://localhost:8080/xianlaohu-admin-web/raw?id=12", jsonString);
        System.out.println(HttpUtils.doGet("http://www.xianlaohu.com"));
    }

}
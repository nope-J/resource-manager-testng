package ctiLink.resourceManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SipProxyTestng {
	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	private String url = "http://localhost:8089/interface/v1";

	public void sipProxyTestng(int i) {

		// Ìí¼Ó
		CtiLinkSipProxy createProxy = createSipProxy(i);
		assert createProxy != null : "Ìí¼ÓSipProxyÊ§°Ü";

		List<CtiLinkSipProxy> list = listSipProxy();
		assert list != null && list.size() > 0 : "²éÑ¯sipProxyÊ§°Ü";

		// ÐÞ¸Ä
		CtiLinkSipProxy updateProxy = updateSipProxy(createProxy);
		assert updateProxy != null : "ÐÞ¸ÄsipProxyÊ§°Ü";

		// É¾³ý
		int success = deleteSipProxy(updateProxy);
		assert success == 0 : "É¾³ýSipProxyÊ§°Ü";
	}

	public CtiLinkSipProxy createSipProxy(int i) {
		CtiLinkSipProxy sipProxy = new CtiLinkSipProxy();
		sipProxy.setName("proxy-" + i);
		sipProxy.setIpAddr("10.10.10." + i);
		sipProxy.setPort(5060);
		sipProxy.setDescription("proxy-" + i);
		sipProxy.setActive(1);
		JSONObject jsonObject0 = JSONObject.fromObject(sipProxy);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipProxy/create");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity(json0, "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if (status == 0) {
				JSONObject data = jsonObject.getJSONObject("data");
				return (CtiLinkSipProxy) JSONObject.toBean(data, CtiLinkSipProxy.class);
			}
		} catch (UnsupportedCharsetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public CtiLinkSipProxy updateSipProxy(CtiLinkSipProxy sipProxy) {
		CtiLinkSipProxy ctiLinkSipProxy = new CtiLinkSipProxy();
		ctiLinkSipProxy.setId(sipProxy.getId());
		ctiLinkSipProxy.setName(sipProxy.getName());
		ctiLinkSipProxy.setIpAddr(sipProxy.getIpAddr());
		ctiLinkSipProxy.setPort(sipProxy.getPort());
		ctiLinkSipProxy.setDescription(sipProxy.getDescription());
		ctiLinkSipProxy.setActive(sipProxy.getActive());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkSipProxy);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipProxy/update");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity(json0, "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if (status == 0) {
				JSONObject data = jsonObject.getJSONObject("data");
				return (CtiLinkSipProxy) JSONObject.toBean(data, CtiLinkSipProxy.class);
			}
		} catch (UnsupportedCharsetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int deleteSipProxy(CtiLinkSipProxy sipProxy) {
		CtiLinkSipProxy ctiLinkSipProxy = new CtiLinkSipProxy();
		ctiLinkSipProxy.setId(sipProxy.getId());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkSipProxy);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipProxy/delete");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity(json0, "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if (status == 0) {
				return 0;
			}
		} catch (UnsupportedCharsetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public List<CtiLinkSipProxy> listSipProxy() {
		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipProxy/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity("");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray data = jsonObject.getJSONArray("data");
			List<CtiLinkSipProxy> list = JSONArray.toList(data, CtiLinkSipProxy.class);
			return list;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

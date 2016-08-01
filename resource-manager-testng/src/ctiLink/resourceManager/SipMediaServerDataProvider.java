package ctiLink.resourceManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.testng.annotations.DataProvider;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SipMediaServerDataProvider {
	@DataProvider(name = "registrySipMediaServer")
	public static Object[][] registrySipMediaServer() {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipGroup/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity("");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			List list = JSONArray.toList(jsonArray, CtiLinkSipGroup.class);
			int index = (int) (Math.random() * list.size());
			CtiLinkSipGroup sipGroup = (CtiLinkSipGroup) list.get(index);
			int groupId = sipGroup.getId();

			CtiLinkSipMediaServer ctiLinkSipMediaServer = new CtiLinkSipMediaServer();
			ctiLinkSipMediaServer.setIpAddr("10.10.10" + "" + 10);
			ctiLinkSipMediaServer.setExternalIpAddr("54.223.151." + "" + 155);
			ctiLinkSipMediaServer.setMac("02:23:2f:53:bf:71");
			ctiLinkSipMediaServer.setInstanceId("i-3678" + "" + (int) (Math.random() * 1000));
			ctiLinkSipMediaServer.setGroupId(groupId);
			ctiLinkSipMediaServer.setStatus(1);
			return new Object[][] { { ctiLinkSipMediaServer } };
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

	@DataProvider(name = "deleteSipMediaServer")
	public static Object[][] deleteSipMediaServer() {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipMediaServer/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity("");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			List list = JSONArray.toList(jsonArray, CtiLinkSipMediaServer.class);
			int index = (int) (Math.random() * list.size());
			CtiLinkSipMediaServer dbSipMediaServer = (CtiLinkSipMediaServer) list.get(index);
			CtiLinkSipMediaServer sipMediaServer = new CtiLinkSipMediaServer();
			sipMediaServer.setInstanceId(dbSipMediaServer.getInstanceId());
			return new Object[][] { { sipMediaServer } };
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

	@DataProvider(name = "updateSipMediaServer")
	public static Object[][] updateSipMediaServer() {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipMediaServer/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity("");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);

			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			List list = JSONArray.toList(jsonArray, CtiLinkSipMediaServer.class);

			int index = (int) (Math.random() * list.size());
			CtiLinkSipMediaServer dbSipMediaServer = (CtiLinkSipMediaServer) list.get(index);
			CtiLinkSipMediaServer sipMediaServer = new CtiLinkSipMediaServer();
			sipMediaServer.setInstanceId(dbSipMediaServer.getInstanceId());
			sipMediaServer.setMac(dbSipMediaServer.getMac());
			sipMediaServer.setGroupId(dbSipMediaServer.getGroupId());
			sipMediaServer.setSipId(dbSipMediaServer.getSipId());
			sipMediaServer.setIpAddr(dbSipMediaServer.getIpAddr());
			sipMediaServer.setExternalIpAddr(dbSipMediaServer.getExternalIpAddr());
			sipMediaServer.setPort(dbSipMediaServer.getPort());
			sipMediaServer.setDescription(dbSipMediaServer.getDescription());
			sipMediaServer.setStatus(dbSipMediaServer.getStatus());
			sipMediaServer.setActive(dbSipMediaServer.getActive());

			return new Object[][] { { sipMediaServer } };
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

	@DataProvider(name = "listSipMediaServer")
	public static Object[][] listSipMediaServer() {
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipMediaServer/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity("");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);

			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			List list = JSONArray.toList(jsonArray, CtiLinkSipMediaServer.class);

			return new Object[][] { { list } };
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

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

public class SipMediaServerTestng {
	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	private String url = "http://localhost:8089/interface/v1";

	public void sipMediaServerTestng(int i) {	
			int sipId = createSipMediaServer(i);
			assert sipId!=-1 : "ÐÂÔösipMediaServerÊ§°Ü";
			
			List<CtiLinkSipMediaServer> list = listSipMediaServer();
			assert list!=null && list.size()>0 : "²éÑ¯SipMediaServerÊ§°Ü";

			CtiLinkSipMediaServer sipMediaServer = updateSipMediaServer(sipId);
			assert sipMediaServer!=null : "ÐÞ¸ÄsipMediaServerÊ§°Ü";

			CtiLinkSipMediaServer deleteSipMediaServer = new CtiLinkSipMediaServer();
			deleteSipMediaServer.setInstanceId(sipMediaServer.getInstanceId());
			int success = deleteSipMediaServer(deleteSipMediaServer);
			
			assert success==0 : "É¾³ýSipMediaServerÊ§°Ü";
	}

	public int createSipMediaServer(int i) {
		try {
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
			int sipGroupId = 0;
			for (int j = 0; j < list.size(); j++) {
				CtiLinkSipGroup sipGroup = (CtiLinkSipGroup) list.get(j);
				if (sipGroup.getId() > sipGroupId) {
					sipGroupId = sipGroup.getId();
				}
			}

			CtiLinkSipMediaServer sipMedia = new CtiLinkSipMediaServer();
			sipMedia.setIpAddr("10.10.10." + i);
			sipMedia.setExternalIpAddr("54.223.151." + i);
			sipMedia.setGroupId(sipGroupId);
			sipMedia.setMac("02:23:2f:53:bf:" + i);
			sipMedia.setInstanceId("i-36733o8e" + i);
			sipMedia.setStatus(1);
			sipMedia.setPort(5060);
			sipMedia.setActive(1);

			JSONObject jsonObject0 = JSONObject.fromObject(sipMedia);
			String json0 = jsonObject0.toString();
			HttpPost httpPost1 = new HttpPost("http://localhost:8089/interface/v1/sipMediaServer/registry");
			httpPost1.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se1 = new StringEntity(json0, "UTF-8");
			se1.setContentType("text/json");
			se1.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost1.setEntity(se1);
			CloseableHttpResponse response1 = httpClient.execute(httpPost1);
			String result1 = EntityUtils.toString(response1.getEntity(), "UTF-8");
			JSONObject jsonObject1 = JSONObject.fromObject(result1);
			int status = jsonObject1.getInt("result");
			if (status == 0) {
				JSONObject data = jsonObject1.getJSONObject("data");
				return data.getInt("sipId");
			}
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
		return -1;
	}

	public int deleteSipMediaServer(CtiLinkSipMediaServer sipMediaServer) {
		try {
			JSONObject jsonObject0 = JSONObject.fromObject(sipMediaServer);
			String json0 = jsonObject0.toString();
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipMediaServer/delete");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity(json0, "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if(status==0){
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

	public CtiLinkSipMediaServer updateSipMediaServer(Integer sipId) {
		List<CtiLinkSipMediaServer> list = listSipMediaServer();
		CtiLinkSipMediaServer sipMediaServer = new CtiLinkSipMediaServer();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getSipId().equals(sipId)) {
					sipMediaServer.setInstanceId(list.get(i).getInstanceId());
					sipMediaServer.setMac(list.get(i).getMac());
					sipMediaServer.setGroupId(list.get(i).getGroupId());
					sipMediaServer.setSipId(list.get(i).getSipId());
					sipMediaServer.setIpAddr(list.get(i).getIpAddr());
					sipMediaServer.setExternalIpAddr(list.get(i).getExternalIpAddr());
					sipMediaServer.setPort(list.get(i).getPort());
					sipMediaServer.setDescription(list.get(i).getDescription() + "update");
					sipMediaServer.setStatus(list.get(i).getStatus());
					sipMediaServer.setActive(list.get(i).getActive());
				}
			}
		}

		try {
			JSONObject jsonObject0 = JSONObject.fromObject(sipMediaServer);
			String json0 = jsonObject0.toString();
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipMediaServer/update");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity(json0, "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if(status==0){
				JSONObject data = jsonObject.getJSONObject("data");
				return (CtiLinkSipMediaServer) JSONObject.toBean(data, CtiLinkSipMediaServer.class);
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

	public List<CtiLinkSipMediaServer> listSipMediaServer() {
		try {
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
			return list;
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

}

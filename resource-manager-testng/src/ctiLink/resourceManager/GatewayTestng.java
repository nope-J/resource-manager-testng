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

public class GatewayTestng {
	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	private String url = "http://localhost:8089/interface/v1";

	public void gatewayTestng(int i) {
		CtiLinkGateway gateway = createGateway(i);
		assert gateway!=null:"ÃÌº”gateway ß∞‹";
		
		List<CtiLinkGateway> list = listGateway();
		assert list!=null && list.size()>0:"≤È—Øgateway ß∞‹";

		CtiLinkGateway updateGateway = updateGateway(gateway);
		assert updateGateway!=null:"–ﬁ∏ƒgateway ß∞‹";
		
		int success = deleteGateway(updateGateway);
		assert success==0:"…æ≥˝gateway ß∞‹";
	}

	public CtiLinkGateway createGateway(int i) {
		CtiLinkGateway ctiLinkGateway = new CtiLinkGateway();
		ctiLinkGateway.setName("gateway-" + i);
		ctiLinkGateway.setPrefix("1234");
		ctiLinkGateway.setIpAddr("10.10.11." + i);
		ctiLinkGateway.setPort(5060);
		ctiLinkGateway.setAreaCode("010");
		ctiLinkGateway.setDescription("gateway-" + i);
		ctiLinkGateway.setCallLimit(1000);
		ctiLinkGateway.setDisallow("all");
		ctiLinkGateway.setAllow("alaw,ulaw,g729");
		ctiLinkGateway.setDtmfMode("rfc2833");
		ctiLinkGateway.setType(1);
		ctiLinkGateway.setUrl("http://localhost:8089");
		ctiLinkGateway.setActive(1);
		ctiLinkGateway.setStatus(1);

		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkGateway);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/gateway/create");
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
				return (CtiLinkGateway) jsonObject.toBean(data, CtiLinkGateway.class);
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

	public CtiLinkGateway updateGateway(CtiLinkGateway gateway) {
		CtiLinkGateway ctiLinkGateway = new CtiLinkGateway();
		ctiLinkGateway.setId(gateway.getId());
		ctiLinkGateway.setName(gateway.getName());
		ctiLinkGateway.setPrefix(gateway.getPrefix());
		ctiLinkGateway.setIpAddr(gateway.getIpAddr());
		ctiLinkGateway.setPort(gateway.getPort());
		ctiLinkGateway.setAreaCode(gateway.getAreaCode());
		ctiLinkGateway.setDescription(gateway.getDescription());
		ctiLinkGateway.setCallLimit(gateway.getCallLimit());
		ctiLinkGateway.setDisallow(gateway.getDisallow());
		ctiLinkGateway.setAllow(gateway.getAllow());
		ctiLinkGateway.setDtmfMode(gateway.getDtmfMode());
		ctiLinkGateway.setType(gateway.getType());
		ctiLinkGateway.setUrl(gateway.getUrl());
		ctiLinkGateway.setActive(gateway.getActive());
		ctiLinkGateway.setStatus(gateway.getStatus());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkGateway);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/gateway/update");
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
				return (CtiLinkGateway) jsonObject.toBean(data, CtiLinkGateway.class);
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

	public int deleteGateway(CtiLinkGateway gateway) {
		CtiLinkGateway ctiLinkGateway = new CtiLinkGateway();
		ctiLinkGateway.setId(gateway.getId());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkGateway);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/gateway/delete");
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

	public List<CtiLinkGateway> listGateway() {
		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/gateway/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity("");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray data = jsonObject.getJSONArray("data");
			return JSONArray.toList(data, CtiLinkGateway.class);
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

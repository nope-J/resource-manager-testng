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

public class RoutersetTestng {
	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	private String url = "http://localhost:8089/interface/v1";

	public void routersetTestng(int i) {
		CtiLinkRouterset routerset = createRouterset(i);
		assert routerset != null : "ÃÌº”routerset ß∞‹";
		
		List<CtiLinkRouterset> list = listRouterset();
		assert list!=null && list.size()>0:"≤È—Ørouterset ß∞‹";

		CtiLinkRouterset updateRouterset = updateRouterset(routerset);
		assert updateRouterset != null : "–ﬁ∏ƒrouterset ß∞‹";

		int success = deleteRouterset(updateRouterset);
		assert success == 0 : "…æ≥˝routerset ß∞‹";
	}

	public CtiLinkRouterset createRouterset(int i) {
		CtiLinkRouterset ctiLinkRouterset = new CtiLinkRouterset();
		ctiLinkRouterset.setName("rs" + i);
		ctiLinkRouterset.setDescription("rs" + i);
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkRouterset);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/routerset/create");
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
				return (CtiLinkRouterset) JSONObject.toBean(data, CtiLinkRouterset.class);
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

	public CtiLinkRouterset updateRouterset(CtiLinkRouterset routerset) {
		CtiLinkRouterset ctiLinkRouterset = new CtiLinkRouterset();
		ctiLinkRouterset.setId(routerset.getId());
		ctiLinkRouterset.setName(routerset.getName());
		ctiLinkRouterset.setDescription(routerset.getDescription());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkRouterset);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/routerset/update");
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
				return (CtiLinkRouterset) JSONObject.toBean(data, CtiLinkRouterset.class);
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

	public int deleteRouterset(CtiLinkRouterset routerset) {
		CtiLinkRouterset ctiLinkRouterset = new CtiLinkRouterset();
		ctiLinkRouterset.setId(routerset.getId());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkRouterset);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/routerset/delete");
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

	public List<CtiLinkRouterset> listRouterset() {
		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/routerset/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity("");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray data = jsonObject.getJSONArray("data");
			return JSONArray.toList(data, CtiLinkRouterset.class);
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

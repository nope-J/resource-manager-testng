package ctiLink.resourceManager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class SipGroupDataProvider {
	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

	@DataProvider(name = "createSipGroup")
	public static Object[][] createSipGroup() {
		CtiLinkSipGroup ctiLinkSipGroup = new CtiLinkSipGroup();
		ctiLinkSipGroup.setDescription("sip-group");
		JSONObject json = JSONObject.fromObject(ctiLinkSipGroup);
		return new Object[][] { { json.toString() } };
	}

	@DataProvider(name = "deleteSipGroup")
	public static Object[][] deleteSipGroup() {
		try {
			//CloseableHttpClient httpClient = HttpClientBuilder.create().build();
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
			CtiLinkSipGroup ctiLinkSipGroup = (CtiLinkSipGroup) list.get(index);
			return new Object[][] { { ctiLinkSipGroup } };
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

	@DataProvider(name = "updateSipGroup")
	public static Object[][] updateSipGroup() {
		try {
			//CloseableHttpClient httpClient = HttpClientBuilder.create().build();
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
			int count = (int) (Math.random() * list.size());
			while (count == 0) {
				count = (int) (Math.random() * list.size());
			}
			Set<Integer> set = new HashSet();
			for (int i = 0; i < count; i++) {
				int index = (int) (Math.random() * list.size());
				if (index != list.size()) {
					set.add(index);
				}
			}
			List<CtiLinkSipGroup> updateList = new ArrayList<>();
			int total = 100;
			for (Integer index : set) {
				Integer last = index;
				CtiLinkSipGroup ctiLinkSipGroup = new CtiLinkSipGroup();
				CtiLinkSipGroup dbCtiLinkSipGroup = (CtiLinkSipGroup) list.get(index);
				ctiLinkSipGroup.setId(dbCtiLinkSipGroup.getId());
				ctiLinkSipGroup.setDescription(dbCtiLinkSipGroup.getDescription());
				if (total > 0) {
					int percent = (int) (Math.random() * 100);
					if (percent < total) {
						ctiLinkSipGroup.setPercent(percent);
						total -= percent;
					} else {
						ctiLinkSipGroup.setPercent(total);
						total = 0;
					}
				} else {
					ctiLinkSipGroup.setPercent(0);
				}
				updateList.add(ctiLinkSipGroup);
			}
			if (total > 0) {
				CtiLinkSipGroup ctiLinkSipGroup = updateList.get(count - 1);
				ctiLinkSipGroup.setPercent(ctiLinkSipGroup.getPercent() + total);
				updateList.set(count - 1, ctiLinkSipGroup);

			}
			return new Object[][] { { updateList } };
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

	@DataProvider(name = "listSipGroup")
	public static Object[][] listSipGroup() {
		try {
			//CloseableHttpClient httpClient = HttpClientBuilder.create().build();
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
			return new Object[][] { { list } };
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

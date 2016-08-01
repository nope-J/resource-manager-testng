package ctiLink.resourceManager;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
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

public class SipGroupTest {
	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	private String url = "http://localhost:8089/interface/v1";

	public void sipGroupTestng() {
		CtiLinkSipGroup sipGroupCreate1 = createSipGroup();
		CtiLinkSipGroup sipGroupCreate2 = createSipGroup();
		CtiLinkSipGroup sipGroupCreate3 = createSipGroup();
		assert sipGroupCreate1!=null && sipGroupCreate2!=null && sipGroupCreate3!=null:"ÃÌº”sipGroup ß∞‹";

		List<CtiLinkSipGroup> list = listSipGroup();
		assert list!= null && list.size()>0:"≤È—ØsipGroup ß∞‹";

		CtiLinkSipGroup sipGroupUpdate1 = new CtiLinkSipGroup();
		CtiLinkSipGroup sipGroupUpdate2 = new CtiLinkSipGroup();
		CtiLinkSipGroup sipGroupUpdate3 = new CtiLinkSipGroup();

		sipGroupUpdate1.setPercent(30);
		sipGroupUpdate1.setId(sipGroupCreate1.getId());
		sipGroupUpdate1.setDescription(sipGroupCreate1.getDescription());

		sipGroupUpdate2.setPercent(60);
		sipGroupUpdate2.setId(sipGroupCreate2.getId());
		sipGroupUpdate2.setDescription(sipGroupCreate2.getDescription());

		sipGroupUpdate3.setPercent(10);
		sipGroupUpdate3.setId(sipGroupCreate3.getId());
		sipGroupUpdate3.setDescription(sipGroupCreate3.getDescription());

		List<CtiLinkSipGroup> updateList = new ArrayList<>();
		updateList.add(sipGroupUpdate1);
		updateList.add(sipGroupUpdate2);
		updateList.add(sipGroupUpdate3);
		int success0 = updateSipGroup(updateList);
		assert success0==0:"–ﬁ∏ƒsipGroup ß∞‹";

		sipGroupUpdate3.setPercent(100);
		updateList.clear();
		updateList.add(sipGroupUpdate3);
		int success1 = updateSipGroup(updateList);
		assert success1==0:"‘Ÿ¥Œ–ﬁ∏ƒsipGroup ß∞‹";
		
		int success3 = deleteSipGroup(sipGroupUpdate1);
		int success4 = deleteSipGroup(sipGroupUpdate2);
		assert success3==0&&success4==0:"…æ≥˝sipGroup ß∞‹";
	}

	public CtiLinkSipGroup createSipGroup() {
		try {
			CtiLinkSipGroup ctiLinkSipGroup = new CtiLinkSipGroup();
			ctiLinkSipGroup.setDescription("sip-group");
			JSONObject jsonSipGroup = JSONObject.fromObject(ctiLinkSipGroup);
			String json = jsonSipGroup.toString();
			HttpPost httpPost = new HttpPost(url + "/sipGroup/create");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
			StringEntity se = new StringEntity(json, "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if (status == 0) {
				JSONObject data = jsonObject.getJSONObject("data");
				CtiLinkSipGroup ctiLinkSipGrou = (CtiLinkSipGroup) JSONObject.toBean(data, CtiLinkSipGroup.class);
				return ctiLinkSipGrou;
			}
		} catch (UnsupportedCharsetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int deleteSipGroup(CtiLinkSipGroup ctiLinkSipGroup) {
		try {
			CtiLinkSipGroup sipGroup = new CtiLinkSipGroup();
			sipGroup.setId(ctiLinkSipGroup.getId());
			JSONObject jsonObject0 = JSONObject.fromObject(sipGroup);
			String json0 = jsonObject0.toString();
			HttpPost httpPost = new HttpPost(url + "/sipGroup/delete");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public int updateSipGroup(List<CtiLinkSipGroup> list) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(list);
			String json = jsonArray.toString();
			HttpPost httpPost = new HttpPost(url + "/sipGroup/update");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8");
			StringEntity se = new StringEntity(json, "UTF-8");
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

	public List<CtiLinkSipGroup> listSipGroup() {
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
			int status = jsonObject.getInt("result");
			if (status == 0) {
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				List list = JSONArray.toList(jsonArray, CtiLinkSipGroup.class);
				return list;
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
}

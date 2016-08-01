package ctiLink.resourceManager;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PublicVoiceTestng {
	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	private String url = "http://localhost:8089/interface/v1";

	public void publicVoiceTestng(){
		CtiLinkPublicVoice publicVoice = createPublicVoice();
		assert publicVoice!=null:"ÃÌº”publicVoice ß∞‹";
		
		List<CtiLinkPublicVoice> list = listPublicVoice();
		assert list!=null && list.size()>0:"≤È—ØpublicVoice ß∞‹";
		
		CtiLinkPublicVoice updatePublicVoice = updatePublicVoice(publicVoice);
		assert updatePublicVoice != null :"–ﬁ∏ƒpublicVoice ß∞‹";
		
		int success = deletePublicVoice(updatePublicVoice);
		assert success==0:"…æ≥˝publicVoice ß∞‹";
		
	}
	
	public CtiLinkPublicVoice createPublicVoice() {
		try {
			HttpPost httpPost = new HttpPost("http://cti-link-resource-manager/interface/v1/publicVoice/create");
			FileBody file = new FileBody(new File("E:/1_minute_left.wav"));
			StringBody voiceName = new StringBody("test1.wav", ContentType.TEXT_PLAIN);
			StringBody description = new StringBody("test1.wav", ContentType.TEXT_PLAIN);
			HttpEntity reEntity = MultipartEntityBuilder.create()
					.addPart("file", file)
					.addPart("voiceName", voiceName)
					.addPart("description", description).build();
			httpPost.setEntity(reEntity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if (status == 0) {
				JSONObject data = jsonObject.getJSONObject("data");
				return (CtiLinkPublicVoice) JSONObject.toBean(data, CtiLinkPublicVoice.class);

			}
			System.out.println(result + "------------");
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

	public CtiLinkPublicVoice updatePublicVoice(CtiLinkPublicVoice publicVoice) {
		try {
			HttpPost httpPost = new HttpPost("http://cti-link-resource-manager:8089/interface/v1/publicVoice/update");
			FileBody file = new FileBody(new File("E:/1_minute_left.wav"));
			StringBody id = new StringBody("" + publicVoice.getId(), ContentType.TEXT_PLAIN);
			StringBody voiceName = new StringBody("test2.wav", ContentType.TEXT_PLAIN);
			StringBody description = new StringBody("test2.wav", ContentType.TEXT_PLAIN);
			HttpEntity reEntity = MultipartEntityBuilder.create()
					.addPart("id", id)
					.addPart("file", file)
					.addPart("voiceName", voiceName).
					addPart("description", description).build();
			httpPost.setEntity(reEntity);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if (status == 0) {
				JSONObject data = jsonObject.getJSONObject("data");
				return (CtiLinkPublicVoice) jsonObject.toBean(data, CtiLinkPublicVoice.class);
			}
			System.out.println(result + "------------");
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

	public int deletePublicVoice(CtiLinkPublicVoice publicVoice) {
		try {
			CtiLinkPublicVoice ctiLinkPublicVoice = new CtiLinkPublicVoice();
			ctiLinkPublicVoice.setId(publicVoice.getId());
			JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkPublicVoice);
			String json0 = jsonObject0.toString();
			HttpPost httpPost = new HttpPost("http://cti-link-resource-manager:8089/interface/v1/publicVoice/delete");
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
	
	public List<CtiLinkPublicVoice> listPublicVoice(){
		try {
			HttpPost httpPost = new HttpPost("http://cti-link-resource-manager:8089/interface/v1/publicVoice/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity("");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if(status==0){
				JSONArray data = jsonObject.getJSONArray("data");
				List<CtiLinkPublicVoice> list = JSONArray.toList(data,CtiLinkPublicVoice.class);
				return list;
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
		return null;
	}
}

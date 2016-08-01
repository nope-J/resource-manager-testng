package ctiLink.resourceManager;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class SipMediaServerTest {
	
	@Test(dataProvider="registrySipMediaServer",dataProviderClass=SipMediaServerDataProvider.class)
	public void registrySipMediaServer(CtiLinkSipMediaServer sipMediaServer){
		try {
			JSONObject jsonObject = JSONObject.fromObject(sipMediaServer);
			String json = jsonObject.toString();
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/sipMediaServer/registry");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity(json,"UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			System.out.println("sipMediaServerע�᣺"+result);
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
	}
}

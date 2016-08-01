package ctiLink.resourceManager;

import java.io.IOException;
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

public class RouterTestng {
	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	private String url = "http://localhost:8089/interface/v1";
	private RoutersetTestng routersetTestng = new RoutersetTestng();
	private GatewayTestng gatewayTestng = new GatewayTestng();

	public void routerTestng(int i) {
		CtiLinkRouterset routerset = routersetTestng.createRouterset(i);
		CtiLinkGateway gateway = gatewayTestng.createGateway(i);

		CtiLinkRouter router = createRouter(routerset.getId(), gateway.getId(), i);
		assert router!=null:"¥¥Ω®router ß∞‹";
		
		List<CtiLinkRouterResponse> list = listRouter(router);
		assert list!=null && list.size()>0:"≤È—Ø¡–±Ì ß∞‹";
		
		CtiLinkRouter updateRouter = updateRouter(router);
		assert router!=null:"–ﬁ∏ƒrouter ß∞‹";

		int success = deleteRouter(updateRouter);
		assert success==0:"…æ≥˝router ß∞‹";
		
		
	}

	public CtiLinkRouter createRouter(int routersetId, int gatewayId, int i) {
		CtiLinkRouter ctiLinkRouter = new CtiLinkRouter();
		ctiLinkRouter.setPrefix("1234");
		ctiLinkRouter.setGatewayId(gatewayId);
		ctiLinkRouter.setRoutersetId(routersetId);
		ctiLinkRouter.setPriority(1);
		ctiLinkRouter.setDescription("router-" + i);
		ctiLinkRouter.setType(1);
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkRouter);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/router/create");
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
				ctiLinkRouter.setId(data.getInt("id"));
				return ctiLinkRouter;
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

	public CtiLinkRouter updateRouter(CtiLinkRouter router) {
		CtiLinkRouter ctiLinkRouter = new CtiLinkRouter();
		ctiLinkRouter.setId(router.getId());
		ctiLinkRouter.setPrefix(router.getPrefix());
		ctiLinkRouter.setGatewayId(router.getGatewayId());
		ctiLinkRouter.setRoutersetId(router.getRoutersetId());
		ctiLinkRouter.setPriority(router.getPriority());
		ctiLinkRouter.setDescription(router.getDescription());
		ctiLinkRouter.setType(router.getType());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkRouter);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/router/create");
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
				return ctiLinkRouter;
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

	public int deleteRouter(CtiLinkRouter router) {
		CtiLinkRouter ctiLinkRouter = new CtiLinkRouter();
		ctiLinkRouter.setId(router.getId());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkRouter);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/router/delete");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity(json0, "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			int status = jsonObject.getInt("result");
			if(status == 0){
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

	public List<CtiLinkRouterResponse> listRouter(CtiLinkRouter router) {
		CtiLinkRouter ctiLinkRouter = new CtiLinkRouter();
		ctiLinkRouter.setRoutersetId(router.getRoutersetId());
		JSONObject jsonObject0 = JSONObject.fromObject(ctiLinkRouter);
		String json0 = jsonObject0.toString();

		try {
			HttpPost httpPost = new HttpPost("http://localhost:8089/interface/v1/router/list");
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json;cahrset=UTF-8");
			StringEntity se = new StringEntity(json0, "UTF-8");
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray data = jsonObject.getJSONArray("data");
			List<CtiLinkRouterResponse> list = JSONArray.toList(data, CtiLinkRouterResponse.class);
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

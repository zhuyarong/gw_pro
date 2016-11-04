//package com.jl.test.ask;
//
//import javax.swing.event.DocumentEvent.EventType;
//
//import com.fr.json.JSONException;
//import com.fr.json.JSONObject;
//import com.lidroid.xutils.HttpUtils;
//import com.lidroid.xutils.exception.HttpException;
//import com.lidroid.xutils.http.RequestParams;
//import com.lidroid.xutils.http.ResponseInfo;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.lidroid.xutils.http.client.HttpRequest;
//
//public class TestAsk {
//	public static void main(String[] args) {
//		String url ="";
//		HttpUtils httpUtils = new HttpUtils();
//	    RequestParams params = new RequestParams();
//	    params.addBodyParameter("com_id", "");
//	    params.addBodyParameter("flow_step", "4");
//	    params.addBodyParameter("cId", "");
//	    params.addBodyParameter("remark", "");
//	    httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
//	        @Override
//	        public void onSuccess(ResponseInfo<String> responseInfo) {
//	            String result = responseInfo.result;
//	         
//	            try {
//	                int code = new JSONObject(result).getInt("code");
//	                if (code == 0) {
//	                    System.out.println("发送成功");
//	                } else {
//	                 
//	                    System.out.println("发送失败");
//	                   
//	                }
//	            } catch (JSONException e) {
//	                e.printStackTrace();
//	                System.out.println("发送失败");
//	      
//	            }
//
//	        }
//
//	        @Override
//	        public void onFailure(HttpException e, String s) {
//	        	 System.out.println("发送失败");
//	        }
//	    });
//
//	}
//	
//}

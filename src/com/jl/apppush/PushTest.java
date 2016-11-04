package com.jl.apppush;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;

public class PushTest {
	 //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "5yle5Qhhlm6EUbCwC5bH03";
    private static String appKey = "M0ZKeMoDRv5fSpHx8Uy8d8";
    private static String appSecret ="0D1blSLGKP9BpflE0Bvz59";
    private static String masterSecret = "znxTvS7rUF7hdUv3mQ5lvA";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    static String CID = "df7408cc1d7a56bf85ee1b9f58238ca6";
    public static void main(String[] args) throws IOException {

        IGtPush push = new IGtPush(url, appKey, masterSecret);

        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        NotyPopLoadTemplate template = new NotyPopLoadTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setLoadTitle("1111111111");
        template.setNotyContent("2222222222222");
        template.setNotyTitle("333333333333");
        template.setPopContent("44444444444");
        template.setPopTitle("555555555");
        template.setPopContent("66666666");
        template.setNotyIcon("icon.png");
        template.setVibrationed(true);
        template.setPopImage("icon.png");
        template.setPopButton1("button1");
        template.setPopButton2("button2");
        template.setLoadIcon("");
        template.setLoadUrl("");
       // template.setLoadUrl("http://getui.com");
      /**
       * setAppId 	String 	— 	是 		设定接收的应用
setAppkey 	String 	— 	是 		用于鉴定身份是否合法
setNotyIcon 	String 	40 	是 		通知栏图标		
setNotyContent 	String 	600中/英字符 	是 		通知栏内容
setPopTitle 	String 	40中/英字符 	是 		弹出框标题
setPopContent 	String 	600中/英字符 	是 		弹出框内容
setPopImage 	String 	200中/英字符 	是 		弹出框图标
setPopButton1 	String 	4中/英字符 	是 		弹出框确定按钮文本显示
setPopButton2 	String 	4中/英字符 	是 		弹出框取消按钮文本显示
setLoadIcon 	String 	40中/英字符 	是 		下载图标，如果是本地需添加file://前缀，如果是网络图标，则直接填写网络地址
setLoadTitle 	String 	40中/英字符 	是 		下载标题
setLoadUrl 	String 	200中/英字符 	是 		下载地址
       */

        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);

        
        
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(CID);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
//        IPushResult ret = push.pushMessageToApp(message);
//        System.out.println(ret.getResponse().toString());
    }
}

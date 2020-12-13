package com.ruoyi.web.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


/*
* 短信发送服务
* */
public class SmsSend {
    public  void sendSms(String phone,String name) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FqBPzpcNbCX4CbrkKRG", "Y7xFKankkQau5v98TfCPlQUGwVOY3G");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "山西匹思泰信息公司");
        request.putQueryParameter("TemplateCode", "SMS_199197380");
        request.putQueryParameter("TemplateParam", name);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }



   


}

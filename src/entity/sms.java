/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hedia
 */
public class sms {

  
    
       public static void sms()
    {
       
        int num=50505512;
        
        
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("hedi.abdelli@esprit.tn");
        defaultClient.setPassword("Hedi_abdelli0000");
        SmsApi apiInstance = new SmsApi(defaultClient);
        System.out.println("TEEEST");
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("Offre effectue" );
        smsMessage.to("+216"+num);
        smsMessage.source("offre");
        

        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
    }
       
}

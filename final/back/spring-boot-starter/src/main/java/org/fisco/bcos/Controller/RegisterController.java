package org.fisco.bcos.Controller;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IRegisterService;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.precompile.config.SystemConfigService;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class RegisterController {
    @Autowired
    private IRegisterService registerService;



    @RequestMapping(value = "register")
    private JSONObject register(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        Service service = context.getBean(Service.class);
//        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
//        channelEthereumService.setChannelService(service);
//
//        //获取Web3j对象
//        Web3j web3j = Web3j.build(channelEthereumService, service.getGroupId());
//        //通过Web3j对象调用API接口getBlockNumber
//        BigInteger blockNumber;
//        try {
//            blockNumber = web3j.getBlockNumber().send().getBlockNumber();
//            System.out.println(blockNumber);
//        }
//        catch(Exception e){
//            System.out.println("Get Blocknumber failed.");
//        }

        return registerService.register();
    }
}

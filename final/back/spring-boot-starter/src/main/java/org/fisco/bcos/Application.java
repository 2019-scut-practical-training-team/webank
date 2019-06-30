package org.fisco.bcos;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.fisco.bcos.Contracts.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    @Autowired
    private static Web3j web3j;
    @Autowired
    private static Credentials credentials;

    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
        try {
            Market market = Market.deploy(
                    web3j,
                    credentials,
                    new StaticGasProvider(
                            GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();

            if (market != null) {
                System.out.println("HelloWorld address is: " + market.getContractAddress());
            }
        }
        catch (Exception e){
            final Writer result = new StringWriter();
            final PrintWriter print = new PrintWriter(result);
            e.printStackTrace(print);
            System.out.println("==============================="+result.toString());

        }



    }
}

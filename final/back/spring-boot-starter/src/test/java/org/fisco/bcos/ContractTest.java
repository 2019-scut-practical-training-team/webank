package org.fisco.bcos;

import static org.junit.Assert.assertTrue;

import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.temp.HelloWorld;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class ContractTest extends BaseTest {

    @Autowired private Web3j web3j;
    @Autowired private Variables variables;

    @Test
    public void deployAndCallHelloWorld() throws Exception {
        // deploy contract
//        HelloWorld helloWorld =
//                HelloWorld.deploy(
//                                web3j,
//                                credentials,
//                                new StaticGasProvider(
//                                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT))
//                        .send();
//        if (helloWorld != null) {
//            System.out.println("HelloWorld address is: " + helloWorld.getContractAddress());
//            // call set function
//            helloWorld.set("Hello, World!").send();
//            // call get function
//            String result = helloWorld.get().send();
//            System.out.println(result);
//            assertTrue("Hello, World!".equals(result));
//        }

            Credentials credentials = Credentials.create(variables.getAdmin());
            OrderContract orderContract = OrderContract.deploy(
                    web3j,
                    credentials,
                    new StaticGasProvider(
                            GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();

            if (orderContract != null) {
                System.out.println("OrderContract address is: " + orderContract.getContractAddress());
            }

        Market market = Market.deploy(
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT)).send();

        if (market != null) {
            System.out.println("Market address is: " + market.getContractAddress());
        }

        market.setOrderAddress(orderContract.getContractAddress()).send();
        orderContract.setMarketAddress(market.getContractAddress()).send();


    }
}

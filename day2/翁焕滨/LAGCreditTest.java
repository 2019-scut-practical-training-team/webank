package org.fisco.bcos;

import static org.junit.Assert.assertTrue;
import java.math.BigInteger;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.temp.LAGCredit;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LAGCreditTest extends BaseTest {

    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;

    @Test
    public void deployAndCallLAGCredit() throws Exception {
        // deploy contract
        LAGCredit lagcredit =
                LAGCredit.deploy(
                                web3j,
                                credentials,
                                new StaticGasProvider(
                                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT),
                                new BigInteger("500"),
                                "scut",
                                "whb")
                        .send();
        if (lagcredit != null) {
        	String contractAddress = lagcredit.getContractAddress();
            log.info("LAGCredit address is: " + contractAddress);
            System.out.println("LAGCredit address is: " + contractAddress);
            
            // call getTotalSupply function
            BigInteger totalSupply = lagcredit.getTotalSupply().send();
            log.info("Total creddit is: " + totalSupply);
            System.out.println("Total creddit is: " + totalSupply);
            
            lagcredit.transfer("0xe82f2170a132059199c9f0af31f428d4fd42fb75", new BigInteger("10")).send();
            
            BigInteger balanceOfAccount = lagcredit.balanceOf("0xe82f2170a132059199c9f0af31f428d4fd42fb75").send();
            log.info("The balance of  user accounnt is: " + balanceOfAccount);
            System.out.println("The balance of  user accounnt is: " + balanceOfAccount);
            
            BigInteger balanceOfMe = lagcredit.balanceOfMe().send();
            log.info("The balance of book shop is: " + balanceOfMe);
            System.out.println("The balance of book shop is: " + balanceOfMe);
        }
    }
}
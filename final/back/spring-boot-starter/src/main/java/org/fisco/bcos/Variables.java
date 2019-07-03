package org.fisco.bcos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "variables")
public class Variables {

<<<<<<< HEAD
    private String market = "0xfb03b6734eac21370981e2e65524b563403fae4a";
    private String order = "0xbb540d16d4f6413b74982170a5c961f2e93d56e9";
    private String admin = "2652b364efebbf7de39d0d28f30ff921a8fe18165fe1276e8990a9cb4ca1c8d6";  // can not use '0x' as prefix
=======
    private String market = "0x55417bef6149b3e62b8e75d2a107429512d4e220";
    private String order = "0x8728e9f920727c938abb11c7bc9b938c6a220800";
    private String admin = "579f45ac03ed9bfd9919c05dd8ae05989713f490f864bb58fb620db4e8e7affb";  // can not use '0x' as prefix
>>>>>>> dc6589f7e64ee58e7ed5d030a9114f57b748b6d6

    public String getMarket() {
        return market;
    }

    public void setMarket(String bumarket) {
        this.market = market;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

}

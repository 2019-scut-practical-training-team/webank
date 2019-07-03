package org.fisco.bcos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "variables")
public class Variables {

    private String market = "0x55417bef6149b3e62b8e75d2a107429512d4e220";
    private String order = "0x8728e9f920727c938abb11c7bc9b938c6a220800";
    private String admin = "579f45ac03ed9bfd9919c05dd8ae05989713f490f864bb58fb620db4e8e7affb";  // can not use '0x' as prefix

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

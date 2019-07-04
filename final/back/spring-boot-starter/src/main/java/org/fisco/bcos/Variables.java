package org.fisco.bcos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "variables")
public class Variables {

    private String market = "0xb91b8a127ec6f64cf1378d1f5e761e1a0e4895d7";
    private String order = "0x0cd67762ee2a45d71cff6d9ad69f5f7ecbaea1b0";
    private String admin = "2652b364efebbf7de39d0d28f30ff921a8fe18165fe1276e8990a9cb4ca1c8d6";  // can not use '0x' as prefix


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

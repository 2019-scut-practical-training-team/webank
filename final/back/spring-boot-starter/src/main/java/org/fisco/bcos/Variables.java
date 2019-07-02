package org.fisco.bcos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "variables")
public class Variables {

    private String market = "0x5bf9026b4f5e177e4f09f417dc69c9331b093c19";
    private String order = "0x325307d51634b15c8938ffc14c0dafd8aa495bd0";
    private String admin = "00d892c4cefa29abf0fe53593790bd172966e5025d5a7c903c8cbd2afab6ebd3cc";  // can not use '0x' as prefix

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

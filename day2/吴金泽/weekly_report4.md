# 第四周报告

## 已完成的工作

### 编写LAGCredit.sol合约文件：

```
pragma solidity ^0.4.19;
contract LAGCredit{
    string name = "LAGC";  
    string symbol = "LAG";  
    uint256 totalSupply;  

    mapping (address => uint256) public balances;

    event transferEvent(address from, address to, uint256 value);

    constructor(uint256 initialSupply, string creditName, string creditSymbol) public {
        totalSupply = initialSupply;
        balances[msg.sender] = totalSupply;
        name = creditName;
        symbol = creditSymbol;
    }
    function getTotalSupply() constant returns (uint256) {
        return totalSupply;
    }
    function _transfer(address _from, address _to, uint _value) internal {
        require(_to != 0x0);
        require(balances[_from] >= _value);
        require(balances[_to] + _value > balances[_to]);
        uint previousBalances = balances[_from] + balances[_to];
        balances[_from] -= _value;
        balances[_to] += _value;
        transferEvent(_from, _to, _value); 
        assert(balances[_from] + balances[_to] == previousBalances);
    }
    function transfer(address _to, uint256 _value) public {
        _transfer(msg.sender, _to, _value);
    }
    function balanceOf(address _owner) constant returns (uint256) {
        return balances[_owner];
    }
    function balanceOfMe() constant returns (uint256) {
        return balances[msg.sender];
    }
    function getAccount() constant returns (address) {
        return msg.sender;
    }
}
```

* 用控制台部署LAGCredit合约：

  * 运行getAccount.sh脚本以获取两个交易账户，第一个是商家账号，第二个是用户账号![1](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/吴金泽/img/1.png)

  * 用控制台分别登录两个账号![2](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/吴金泽/img/2.png)

  * 在控制台上部署LAGCredit合约，发行积分总量为500，可以看到商家现在有500积分，用户为0积分 ![3](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/吴金泽/img/3.png)

  * 进行交易 ![4](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/吴金泽/img/4.jpg)

  * 交易后两个账号的积分 ![5](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/吴金泽/img/5.png)![6](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/吴金泽/img/6.png)

### 用spring-boot-starter部署LAGCredit合约：

```
$ git clone https://github.com/FISCO-BCOS/spring-boot-starter.git
cd ~/fisco/nodes/127.0.0.1/sdk 
cp ca.crt node.crt node.key ~/spring-boot-starter/src/test/resources/
cd ~/spring-boot-starter/
chmod u+x gradlew
./gradlew build
```

运行结果
  
![7](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/吴金泽/img/7.png)

把LAGCredit.sol放到spring-boot-starter的src/test/resources/contract目录下，gradlew build一遍。

src/test/resources/solidity目录下会生成相应的.abi和.bin(二进制文件)
src/test/java/org/fisco/bcos/temp目录下下会产生.java文件

编写LAGCreditTest.java并运行
```
package org.fisco.bcos;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.temp.HelloWorld;
import org.fisco.bcos.temp.LAGCredit;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jline.internal.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LAGCreditTest extends BaseTest {

    @Autowired private Web3j web3j;
    @Autowired private Credentials credentials;

    @Test
    public void deloy() throws Exception {
        // deploy contract
        LAGCredit bookStore = LAGCredit.deploy(
                web3j, credentials, 
                new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT), 
                BigInteger.valueOf(1000), "scut", "simon")
                                        .send();
        if(bookStore != null) {
            log.info("deploy LAGCredit successful! contract address is {}", bookStore.getContractAddress());
            log.info("total supply is {}",bookStore.getTotalSupply().send());
        }
    }
}
```

运行后，在控制台可以查询到结果

![](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/吴金泽/img/8.png)


## 下一周的工作

- 继续熟悉*spring boot*项目的开发流程。
- 继续熟悉*FSICO-BCOS*的[官方文档](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/index.html)
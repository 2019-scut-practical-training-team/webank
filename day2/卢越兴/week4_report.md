# 第四周的个人周报





## 已完成的工作

### 实现智能合约

#### 根据PPT上的提示编写以下功能

- 总积分初始化
- 总积分发行量查询
- 积分转账
- 积分查询
- 积分转账明细记录

#### 在根据个人需要添加以下功能

- 查询自己的积分余额
- 获取自己的账号地址

#### 代码实现

```solidity
// LAGCredit.sol

pragma solidity ^0.4.25;

contract LAGCredit {
  string name = "LAGC";   // 积分名称
  string symbol = "LAG";   // 积分简称
  uint256 totalSupply;     //发行量


  // 地址对应的余额
  mapping (address => uint256) public balances;

  // 用来通知客户端发生了积分交易
  event transferEvent(address from, address to, uint256 value);

  // 构造函数，由积分创建者执行：书店
  constructor(uint256 initialSupply, string creditName, string creditSymbol) public {
    totalSupply = initialSupply;
    balances[msg.sender] = totalSupply;
    name = creditName;
    symbol = creditSymbol;
  }

  // 查询积分发放总额
  function getTotalSupply() public view  returns (uint256) {
    return totalSupply;
  }

  // 积分的发送函数，内部函数
  function _transfer(address _from, address _to, uint _value) internal {

    require(_to != 0x0, "Target address is invaild!");
    require(balances[_from] >= _value, "You don't have enough balance");
    require(balances[_to] + _value > balances[_to], "You can't transfer negative value"); //_value不能为负值
    
    uint previousBalances = balances[_from] + balances[_to];

    balances[_from] -= _value;
    balances[_to] += _value;

    emit transferEvent(_from, _to, _value); // 记录转账并通知客户端发生积分交易
    assert(balances[_from] + balances[_to] == previousBalances);
  }

  // 客户端调用的积分发送函数
  function transfer(address _to, uint256 _value) public {
    _transfer(msg.sender, _to, _value);
  }

  // 查询账户余额
  function balanceOf(address _owner) public view returns (uint256) {
    return balances[_owner];
  }

  // 查询自己的账号余额
  function balanceOfMine() public view  returns (uint256) {
    return balanceOf(msg.sender);
  }

  // 查看自己的地址
  function getMyAddress() public view returns (address) {
    return msg.sender;
  }

}
```



### 在*Remix*上部署和测试合约

#### 部署

- 输入测试需要的三个参数然后点击`Deploy`

  ![deploy](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/1_deploy.png)

- 部署成功

  ![deploy successful](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/2_deploy.png)

- 合约的状态

  ![contract status](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/3_deploy.png)



#### 测试

- 测试合同内的功能

  ![function](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/4_run.png)

- 测试查看发行量：`getTotalSupply`

  ![total supply](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/5_getTotalSupply.png)

- 测试获取自己的地址：`getMyAddress`

  ![my address](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/6_getMyAddress.png)

- 测试转账的功能，包含三个函数：`balanceOf`, `balanceOfMine`, `transfer`

  - 查看自己的余额

    ![my balance](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/7_balanceOfMine.png)

  - 使用`get_account.sh`脚本生成一个账号

    ```bash
    $ bash get_account.sh
    # Account Address   : 0x0c1f490580a6161d605660fd0081e4fd9206ba74
    # Private Key (pem) : accounts/0x0c1f490580a6161d605660fd0081e4fd9206ba74.pem
    # Private Key: 0xece7dfffe674f40c22ae24e06ec890b90277bec481890e2f1a6ac97d5dd02b2c
    ```
    

  

  - 查看这个账号的余额
    
    ![get balance](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/8_balanceOf.png)
    
  - 给这个账号转账

    ![](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/9_transfer.png)

  - 再次查看这个账号的余额

    ![](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/10_balanceOf.png)

  - 查看自己的余额

    ![my balance](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/11_balanceOfMine.png)



### 在*spring-boot-starter*中测试和部署

#### 配置

- 将项目导入IDEA
- 根据[说明](https://github.com/FISCO-BCOS/spring-boot-starter/blob/master/doc/README_CN.md)进行相关节点文件的拷贝，和节点信息的配置



#### 合约转换

- 删除`src/test/java/resources/contract`目录下的`HelloWorld.sol`合约，并拷贝`LAGCredit.sol`进去。

- 运行`src/test/java/org/fisco/bcos/solidity/`目录下的`SolidityFunctionWrapperGeneratorTest`测试类，将合约转换成java文件

- 转换完成后在`SolidityFunctionWrapperGeneratorTest`测试类中加上`@Ignore`

- 编写测试用例：修改`ContractTest`

  ```java
  @Test
  public  void deployAndCallLAGCredit() throws  Exception {
      // target address(generate by get_account.sh)
      String targetAddress = "0x0c1f490580a6161d605660fd0081e4fd9206ba74";
  
      //deploy contract
      LAGCredit lagCredit = LAGCredit.deploy(
              web3j,
              credentials,
              new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT),
              new BigInteger("10000"), "LAGC", "LAG"
      ).send();
  
      if (lagCredit != null) {
          // print the address
          System.out.println("LAGCredit address is: " + lagCredit.getContractAddress());
  
          // call getTotalSupply function
          long total = lagCredit.getTotalSupply().send().longValue();
          System.out.println("Total supply is: " + total);
          assertEquals(10000, total);
  
          // get my balance
          long myBalance = lagCredit.balanceOfMine().send().longValue();
          System.out.println("My balance is: " + myBalance);
          assertEquals(10000, myBalance);
  
          // get balance of target address
          long targetBalance = lagCredit.balanceOf(targetAddress).send().longValue();
          System.out.println("Target balance is: " + targetBalance);
          assertEquals(0, targetBalance);
  
          // transfer 100 to target address
          System.out.println("Transfer 100 to " + targetAddress);
          TransactionReceipt receipt = lagCredit.transfer(targetAddress, new BigInteger("100")).send();
          System.out.println("Receipt status: " + receipt.getStatus());
  
          // get balance of target address again
          targetBalance = lagCredit.balanceOf(targetAddress).send().longValue();
          System.out.println("After transfer, target balance is: " + targetBalance);
          assertEquals(100, targetBalance);
  
          // get my balance again
          myBalance = lagCredit.balanceOfMine().send().longValue();
          System.out.println("After transfer, my balance is: " + myBalance);
          assertEquals(9900, myBalance);
  
      }
  }
  ```

  



#### 测试运行

- 运行`BaseTest`，选择全部运行

  ![afterTest](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/12_afterTest.png)



#### 部署

- 其实在上面的测试过程就已经部署了`LAGCrdit`合约了

- 我们复制上面的信息

  - 合约地址：`0x1986c5539954e6700aec6d9c904ffb93375eab95`
  - 我的地址：`0xf1585b8d0e08a0a00fff662e24d67ba95a438256`
  - 目标地址：`0x0c1f490580a6161d605660fd0081e4fd9206ba74`

- 在控制台中调用合约的函数

  ![console](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week4_Report/13_callOnConsole.png)



### 编写SDK

```java
// LAGCredit.java

package org.fisco.bcos.LAGCreditSDK;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.autoconfigure.CredentialsConfig;
import org.fisco.bcos.autoconfigure.GroupChannelConnectionsPropertyConfig;
import org.fisco.bcos.autoconfigure.ServiceConfig;
import org.fisco.bcos.autoconfigure.Web3jConfig;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.channel.handler.GroupChannelConnectionsConfig;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Slf4j
public class LAGCreditSDK {
    private ServiceConfig serviceConfig;
    private Web3jConfig web3jConfig;
    private GroupChannelConnectionsPropertyConfig groupChannelConnectionsPropertyConfig;
    private CredentialsConfig credentialsConfig;

    private GroupChannelConnectionsConfig groupChannelConnectionsConfig;
    private Service service;
    private Web3j web3j;
    private Credentials credentials;


    // Constructor
    public LAGCreditSDK() {
        try {
            serviceConfig = new ServiceConfig();
            web3jConfig = new Web3jConfig();
            groupChannelConnectionsPropertyConfig = new GroupChannelConnectionsPropertyConfig();
            credentialsConfig = new CredentialsConfig();

            groupChannelConnectionsConfig = groupChannelConnectionsPropertyConfig.getGroupChannelConnections();
            service = serviceConfig.getService(groupChannelConnectionsConfig);
            web3j = web3jConfig.getWeb3j(service);         // Exception
            credentials = credentialsConfig.getCredentials();

        } catch (Exception e) {
            log.error("build lagcredit fail: {}",e.getMessage());
        }
    }

    // deploy a new LAGCredit contract
    public LAGCredit deploy() {
        LAGCredit lagcredit = null;
        try {
            lagcredit.deploy(web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT), new BigInteger("100000"), "LAGC", "LAG").send();
            log.info("LAGC address is {}", lagcredit.getContractAddress());
            return lagcredit;
        } catch (Exception e) {
            log.error("deploy lagc contract fail: {}", e.getMessage());
        }
        return lagcredit;
    }

    // get LAGCredit contract
    public LAGCredit load(String creditAddress) {
        LAGCredit lagCredit = LAGCredit.load(creditAddress, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        return  lagCredit;
    }

    // transfer
    public boolean transfer(String creditAddress, String to, BigInteger value) {
        try {
            LAGCredit lagCredit = load(creditAddress);
            TransactionReceipt receipt = lagCredit.transfer(to, value).send();
            log.info("status: {}", receipt.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public long getBalanceByOwner(String creditAddress, String owner) throws Exception {
        LAGCredit lagCredit = load(creditAddress);
        BigInteger balance = lagCredit.balanceOf(owner).send();
        return balance.longValue();
    }

    public long getBalanceOfMine(String creditAddress) throws Exception {
        LAGCredit lagCredit = load(creditAddress);
        BigInteger balance = lagCredit.balanceOfMine().send();
        return balance.longValue();
    }

    public long getTotalSupply(String creditAddress) throws Exception {
        LAGCredit lagCredit = load(creditAddress);
        BigInteger total = lagCredit.getTotalSupply().send();
        return total.longValue();
    }

    public String getMyAddress(String creditAddress) throws Exception {
        LAGCredit lagCredit = load(creditAddress);
        String address = lagCredit.getMyAddress().send();
        return address;
    }

}

```



```java
// UserKryUtils.java

package org.fisco.bcos.LAGCreditSDK;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.utils.Numeric;


@Slf4j
public class UserKryUtils {
    static final int PUBLIC_KEY_SIZE = 64;
    static final int PUBLIC_KEY_LENGTH_IN_HEX = PUBLIC_KEY_SIZE << 1;

    public static void createUserKey() {
        try {
            ECKeyPair keyPair = Keys.createEcKeyPair();
            String publicKey = Numeric.toHexStringWithPrefixZeroPadded(keyPair.getPublicKey(), PUBLIC_KEY_LENGTH_IN_HEX);
            String privateKey = Numeric.toHexStringNoPrefix(keyPair.getPrivateKey());
            String address = "0x" + Keys.getAddress(publicKey);

            log.info("public Key: {}",publicKey);
            log.info("private Key: {}", privateKey);
            log.info("address: {}", address);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```



## 下一周的工作

- 继续熟悉*spring boot*项目的开发流程。
- 继续熟悉*FSICO-BCOS*的[官方文档](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/index.html)。
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



## 下一周的工作

- 继续熟悉*spring boot*项目的开发流程。
- 继续熟悉*FSICO-BCOS*的[官方文档](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/index.html)。
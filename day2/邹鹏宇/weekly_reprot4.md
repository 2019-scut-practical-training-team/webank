# 第四周周报

## 已完成工作

* 僵尸游戏已经按照要求完成了相应单元的课程，初步了解了solidity语言的规则，和开发需要注意的一些事项，本周主要在上周的学习基础之上进一步复习，并且自己尝试开发智能合约。

* 开发LAGCredit智能合约，并使用spring-boot-starter部署。

  * LAGCredit智能合约的开发：

    ```javascript
    pragma solidity ^0.4.26;  //编译器的版本
    contract LAGCredit {
    
          //以下三个变量将会永久存储在区块链上
          string name = "LAGC";
          string symbol = "LAG";
          uint256 totalSupply;
          
          //地址到余额的映射，可用于查看对应账户地址里的积分余额
          mapping(address=>uint256) public balances;
        
          //可用来通知前段发生了积分的改变
          event transferEvent(address from, address to, uint256 value);
          
          //合约的构造函数，部署合约的时候应该传入三个参数
          constructor(uint256 initialSupply, string creditName, string creditSymbol) public{
              totalSupply = initialSupply;
              name = creditName;
              symbol = creditSymbol;
              //同时将商店的积分余额修改成totalSupply
              balances[msg.sender] = totalSupply;
          }
          
          //用于查看总的积分发行量
          function getTotalSupply() constant returns(uint256){
              return totalSupply;
          }
          
          //用于积分转账，internal说明只能由合约内的函数调用
          function _transfer(address _from, address _to, uint _value) internal{
              //收款方地址不能为0x0，require用于验证先验条件
              require( _to != 0x0 );
              //不能转出大于账户余额的积分数
              require( balances[_from] >= _value );
              //不能转负数的积分出去
              require(balances[_to] + _value > balances[ _to ]);
              
              uint previousBalances = balances[_from]+balances[_to];
              
              balances[_from] -= _value;
              balances[_to] += _value;
              
              transferEvent(_from,_to,_value);
              assert(balances[_from] + balances[_to] == previousBalances);
    
          }
          
          //可见性为public，说明可以被外部调用，通过此函数完成一次发送者到目的地址的积分交易
          function transfer(address _to, uint256 _value)public{
              _transfer(msg.sender, _to, _value);
          }
          
          //查看账户余额
          function balanceOf(address _owner) constant returns(uint256){
              return balances[_owner];
          }
    }
    
    ```

  * 将LAGCredit在remix上先进行测试。由于智能合约一旦在链上部署，就再也无法更改了，所以先在在线编译器里debug，没有问题后再部署。

    1.先用get_account.sh脚本生成账户的地址和私钥

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/2.png)

    2.在remix上编译智能合约，并且通过

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/1.png)

    3.输入三个参数并部署

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/3.png)

    部署后返回了相关信息

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/4.png)

    4.根据上面生成的账户地址，进行转账，并查看余额（注意将地址当做参数传入时应该和字符串一样加双引号）

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/5.png)

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/6.png)

  * 测试完毕后，从github上获取spring-boot-starter项目的源码

    ``` bash
    $ git clone https://github.com/FISCO-BCOS/spring-boot-starter.git
    ```

    ​       然后配置节点证书，将节点所在目录`nodes/${ip}/sdk`下的`ca.crt`、`node.crt`和`node.key`文件拷贝到项目的`src/test/resources`目录下供SDK使用。项目配置文件`application.yml`中可能有需要根据节点配置进行修改：

    ```python
    encryptType: 0 # 0：普通， 1：国密
    groupChannelConnectionsConfig:
      allChannelConnections:
      - groupId: 1 # 群组ID
        connectionsStr:
                        - 127.0.0.1:20200 # 节点，listen_ip:channel_listen_port
                        - 127.0.0.1:20201
      - groupId: 2
        connectionsStr:
                        - 127.0.0.1:20202
                        - 127.0.0.1:20203
    channelService:
      groupId: 1 # sdk实际连接的群组
      orgID: fisco # 机构名称
    ```

    ​         起四个节点，然后编译并运行测试案例，此处出现了问题

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/7.png)

    ​        一开始尝试解决java在ubuntu中环境变量的问题，但是在/etc/profile中加入了相关配置后也并没有作用，求助助教，助教使用的jdk版本为1.8且并没有出现任何错误，于是安装jdk1.8，在项目的根目录下重新运行，成功：

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/8.png)

  * 然后将spring-boot-starter项目导入eclipse中，运行ContractTest.java发现并没有得到相应的输出，合约可以部署成功，测试中HelloWorld的set操作也会执行，链上块的高度会增加2，但是没有得到合约的地址等信息，暂时未找到原因。后改成使用IntelliJ编译，在修改了一些项目设置和配置后可以成功运行，并且得到合约地址，利用该地址在控制台中查看totalsupply，且能够成功，至此通过spring-boot-starter部署合约完成。

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/9.png)

    ![img](https://github.com/2019-scut-practical-training-team/webank/blob/dev/day2/邹鹏宇/img/10.png)

    


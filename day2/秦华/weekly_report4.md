# 第四周周报——秦华 #
## 本周完成的任务 ##
1、完成僵尸游戏的相应课程，进一步熟悉solidity语言的使用。  
2、LAG合约的编写  
（1）实现的功能：  
存储账号与余额的对应关系、转账记录、初始化智能合约、积分发放总量查询、积分转账、积分查询   
（2）代码如下：  
 
    pragma solidity ^0.4.25;

    contract Integration{
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
            balances[_from] -= _value;
            balances[_to] += _value;
            emit transferEvent(_from, _to, _value); 
        }

        function transfer(address _to, uint256 _value) public {
            _transfer(msg.sender, _to, _value);
            emit transferEvent(msg.sender, _to, _value);
        }
    
        function balanceOf(address _owner) constant returns (uint256) { 
            return balances[_owner];
        } 
    } 
2、使用get_account.sh获得账户地址  

3、在remix上部署合约，并执行各函数  

4、使用spring-boot-starter部署合约  
（1）获取项目源码  
`git clone https://github.com/FISCO-BCOS/spring-boot-starter.git`   
（2）将节点所在目录nodes/${ip}/sdk下的ca.crt、node.crt和node.key文件拷贝到项目的src/test/resources目录下供SDK使用  
（3）在项目中添加自己编写的Integratino.sol合约，运行./sol2java.sh  

（4）编译并运行测试案例，在项目根目录下运行：  
`./gradlew build ` 

（5）在控制台部署并调用合约  

## 下周任务安排 ##
1、继续熟悉fisco-bcos的开发文档。  
2、进一步了解spring-boot项目的开发。
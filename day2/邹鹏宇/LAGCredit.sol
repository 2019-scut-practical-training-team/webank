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
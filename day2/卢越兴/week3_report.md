# 第三周的个人周报



### 完成的任务

- 完成[僵尸游戏](https://cryptozombies.io/)上的的关于使用使用*Solidity*编写智能合约的教程。

  ![结果](https://raw.githubusercontent.com/Max-Loo/ImageForMarkdown/master/For_enterprise_software_training/For_Day2/Week3_Report/1.png)





-----

### 笔记

#### 语法

- 在*sol*文件的开头要声明你要使用的*solidity*编译器的版本，并且新版本的编译器将会不支持对以往版本进行编译

  ```solidity
  pragma solidity ^0.4.25  // 表示使用0.4.25的编译器进行编译
  
  // 在这里编写合同代码
  ```

  

- 使用`contract`声明一个合约

  ```solidity
  contract HelloWorld{
    // 在这里编写合约的内容
  }
  ```



- 使用`uint`来声明无符号整数，（注意：`uint`默认为`uint256`表示256位的无符号整数，除此之外还有`uint8`，`uint16`，`uint32`等等）

  ```solidity
  contract HelloWorld {
    // 这里声明的函数会永久保存在区块链中
    uint num = 100;
  }
  ```



- 算术运算符
  - `x + y ` ：
  - `x - y`：
  - `x * y`：表示乘法
  - `x / y`：表示除法
  - `x % y`：表示取余
  - `x ** y`：表示x的y次方（类似*python*的写法）



- 使用`struct`来表示一些基本数据的集合

  ```solidity
  struct Person {
    uint age;
    string name;
  }
  
  // 创建一个新的实例
  Person person = Person(20, "Max");
  ```



- 使用数据类型+[]来声明数组

  ```solidity
  // 固定长度的数组
  uint[2] fixedArray;
  
  // 动态长度的数组
  uint[] dynamicArray;
  
  // 也可以使用自己创建的struct来创建数组，加上public关键词可以让其他的合约也读到这个数组，但不能写入。
  Person[] public myArray;
  
  // 使用push()来将值传入数组中
  myArray.push(Person(20,"Max"))
  ```



- 使用`function`来声明函数，如果该函数有返回值的话要使用`returns`声明返回值的类型

  ```solidity
  function show(string _str) returns (string, uint) {
    return (_str, 100);
  }
  
  // 当我们只需要一个返回值的时候可以忽略其他的返回值
  (,count) = show("hello");
  ```



- 可以使用`private`, `public`, `internal`, `external`来修饰`function`
  - `private`：表示此函数只能被同一合同下的其他函数调用。
  - `public`：表示所有人（或合同）都可以调用此函数。
  - `internal`：表示除了当前合同可以调用外，其子合同也可以调用。
  - `external`：表示只能由外部的合同来调用，内部的函数不可以调用（包括父合同以及子合同）



- 使用`event`来定义一个事件，并在其他函数中借助`emit`来使用它

  ```solidity
  // 定义事件
  event IntegersAdded(uint x, uint y, uint result);
  
  function add(uint _x, uint _y) public {
    uint result = _x + _y;
    // 调用事件
    emit IntegersAdded(_x, _y, result);
    return result;
  }
  ```

  ```javascript
  YourContract.IntegersAdded(function(error, result) { 
    // 使用JavaScript来决定事件发生后如何处理
  }
  ```



- 使用`address`来定义一个以太坊账号地址（为一串16进制数字）



- 使用`mapping`来表示键值对的映射

  ```solidity
  // 表示一个地址到其账号余额的映射
  mapping (address => uint) accountBalance;
  ```

  

- 使用`msg.sender`可以获取调用此合同的用户的地址



- 使用`require`来让函数在某些条件不满足是抛出错误并停止执行

  ```solidity
  function division(uint _num1, uint _num2) public returns (uint) {
    // 要求第二个参数不等于0
    require(_num2 != 0);
    return _num1 / _num2;
  }
  ```



- 使用`is`来表示继承关系，子类可以使用父类的`public`类型的变量和函数。如果两个合同分别在两个*sol*文件中，使用`import`来引入。

  ```solidity
  contract Animal{
     
  }
  
  contract Cat is Animal{
  
  }
  ```

  

- 使用`storage`声明的变量会永久的保存在区块链中，使用`memory`声明的变量会在函数结束后就删除。默认情况下，合同中函数外声明的变量为*storage*的，在函数内声明的变量为*memory* 的。



- 使用接口合同，

  ```solidity
  contract NumberInterface {
    function getNum(address _myAddress) public view returns (uint);
  }
  
  contract BigNumber {
    // 改变合同的地址
    function setInterface(address _address) external {
      NumberInterface number = NumberInterface(_address);
    }
  }
  ```

  

- *solidity*自带时间变量`1 days = 24 hours = 24 * 60 minutes = 24 * 60 * 60 secondes`



- 使用`View`来修饰一个函数表示这个函数只进行读取，不会对数据进行修改。并且此函数不会消耗*Gas*





#### 内置函数

- `keccak256`可以将输入装换成256位的16进制数字，一点小小的改变也导致结果有很大的变化。

  ```solidity
  //6e91ec6b618bb462a4a6ee5aa2cb0e9cf30f7a052bb467b0ba58b8748c00d2e5
  keccak256(abi.encodePacked("aaaab"));
  
  //b1f078126895a1424524de5321b339ab00408010b7cf0e6ed451514981e58aa9
  keccak256(abi.encodePacked("aaaac"));
  ```

  





#### 使用技巧

- 创建一个特殊的合同来保证某一个的合同的某些功能只能由特点的一个人使用

  ```solidity
  contract Ownable {
    // 当前合同的持有者
    address private _owner;
    
    // 合同归属改变事件
    event OwnershipTransferred(
      address indexed previousOwner,
      address indexed newOwner
    );
    
    //构造函数
    constructor() internal {
      _owner = msg.sender;
      emit OwnershipTransferred(address(0), _owner);
    }
    
    // 返回当前的拥有者
    function owner() public view returns(address) {
      return _owner;
    }
    
    // 检查调用者是否为拥有者
    modifier onlyOwner() {
      require(isOwner());
      _;
    }
    
    // 暴露给外部的移交权限函数，在函数后面加上使用modifier声明的onlyOwner，
    // 系统会先执行onlyOwner里面的函数
    function transferOwnership(address newOwner) public onlyOwner {
      _transferOwnership(newOwner);
    }
    
    
    // 真正的移交权限的步骤
    function _transferOwnership(address newOwner) internal {
      require(newOwner != address(0));
      emit OwnershipTransferred(_owner, newOwner);
      _owner = newOwner;
    }
  }
  ```

  
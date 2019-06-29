pragma solidity ^0.4.19;
import "./Factory.sol";
import "./DataProcess.sol";

contract Market is DataProcess{
    address private adminAddress;
    Factory.Pet[] public petOnSell;
    Factory.Order[] public orderList;
    
    //构造函数,设置合约部署人为管理员
    function Market() public {
        adminAddress = msg.sender;
    }
    //获取管理员地址，不需要
    // function getAdmin() constant returns (address){
    //     return adminAddress;
    // }
    
    //修饰符，判断是不是管理员
    modifier isAdmin {
        require (msg.sender == adminAddress);
        _;
    }
    
    //判断是否已创建过宠物
    mapping (address => int) createdPet;
    //地址与余额映射
    mapping (address => uint) Balance;
    //判断是否老用户
    mapping (address => int) oldUser;
    //用户地址到宠物列表的映射
    mapping (address => Factory.Pet[]) addressToPetList;
    //宠物id到用户地址的映射
    mapping (string => address) petIdToOwner;
    
    
    //用户相关：
    //创建用户函数，初始化余额和是否创建过宠物参数
    function createUser() public {
        require(oldUser[msg.sender] == 0);
        initUser();
        oldUser[msg.sender] = 1;
    }
    function initUser() private {
        createdPet[msg.sender] = 0;
        Balance[msg.sender] = 10000;
    }
    
    //获取调用者的余额
    function getBalanceOfMe() public view returns (uint) {
        return Balance[msg.sender];
    }
    //通过地址获取余额
    function getBalace(address _address) public view returns (uint) {
        return Balance[_address];
    }
    
    
    //宠物相关：
    //为用户配置一个宠物列表
    function setAddressToPetList() public {
        Factory.Pet[] storage tempPetList;
        addressToPetList[msg.sender] = tempPetList;
    }
    //返回用户对应的宠物列表
    function getPetListFromAddress() public view returns (string) {
        string memory result;
        for(uint i=0;i<addressToPetList[msg.sender].length;i++){
            result = strConcat(result,addressToPetList[msg.sender][i].petName);
            result = strConcat(result,",");
            result = strConcat(result,addressToPetList[msg.sender][i].petId);
            result = strConcat(result,",");
            result = strConcat(result,addressToPetList[msg.sender][i].petType);
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(addressToPetList[msg.sender][i].petPrice));
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(addressToPetList[msg.sender][i].petStatus));
            result = strConcat(result,",");
            result = strConcat(result,addressToPetList[msg.sender][i].petImg); 
            result = strConcat(result,",");
            result = strConcat(result,addressToPetList[msg.sender][i].petIntro);
            result = strConcat(result,",");
        }
        return result;
    }

    //
    function setPetOwner(string _id,address _owner) private {
        petIdToOwner[_id] = _owner;
    }

    //创建宠物，若没有创建过就先创建宠物列表再创建宠物
    function createPet(string _name, string _id, string _type, int _price, int _status, string _img, string _intro, address _owner) public {
        //判断是否已创建过宠物
        if(createdPet[msg.sender] == 0){
            setAddressToPetList();
            createdPet[msg.sender] = 1;
        }
        //这里创建了一个新的宠物，并放入用户对应的宠物列表
        addressToPetList[msg.sender].push(Factory.Pet(_name, _id, _type, _price, _status, _img, _intro, _owner));
        setPetOwner(_id,_owner);
    }
    
    
    //订单相关：
    //返回所有订单（管理员查看
    function adminGetOrderList() public view isAdmin returns (string, address[], address[]) {
        string memory result;
        address[] storage buyerAddress;
        address[] storage sellerAddress;
        for(uint i=0;i<orderList.length;i++){
            result = strConcat(result,orderList[i].orderId);
            result = strConcat(result,",");
            result = strConcat(result,orderList[i].orderTime);
            result = strConcat(result,",");
            result = strConcat(result,orderList[i].petId);
            result = strConcat(result,",");
            result = strConcat(result,orderList[i].petPrice);
            result = strConcat(result,",");
            result = strConcat(result,DataProcess.getIntToString(orderList[i].orderStatus));
            result = strConcat(result,",");
            result = strConcat(result,orderList[i].returnResult);
            result = strConcat(result,",");
        
            buyerAddress.push(orderList[i].orderBuyer);
            sellerAddress.push(orderList[i].orderSeller);
        }
        return (result, buyerAddress, sellerAddress);
    }
    //管理员获得请求仲裁的订单
    function adminGetReturnOrderList() public view isAdmin returns (string, address[], address[]) {
        string memory result;
        address[] storage buyerAddress;
        address[] storage sellerAddress;
        for(uint i = 0;i < orderList.length;i++){
            if(orderList[i].orderStatus == 1){
                result = strConcat(result,orderList[i].orderId);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].orderTime);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].petId);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].petPrice);
                result = strConcat(result,",");
                result = strConcat(result,DataProcess.getIntToString(orderList[i].orderStatus));
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].returnResult);
                result = strConcat(result,",");
            
                buyerAddress.push(orderList[i].orderBuyer);
                sellerAddress.push(orderList[i].orderSeller);
            }
        }
        return (result, buyerAddress, sellerAddress);
    }
    //用户获得自己的订单列表
    function userGetOrderList() public view returns (string, address[], address[]) {
        string memory result;
        address[] storage buyerAddress;
        address[] storage sellerAddress;
        for(uint i = 0;i < orderList.length;i++){
            if(orderList[i].orderBuyer == msg.sender || orderList[i].orderSeller == msg.sender){
                result = strConcat(result,orderList[i].orderId);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].orderTime);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].petId);
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].petPrice);
                result = strConcat(result,",");
                result = strConcat(result,DataProcess.getIntToString(orderList[i].orderStatus));
                result = strConcat(result,",");
                result = strConcat(result,orderList[i].returnResult);
                result = strConcat(result,",");
            
                buyerAddress.push(orderList[i].orderBuyer);
                sellerAddress.push(orderList[i].orderSeller);
            }
        }
        return (result, buyerAddress, sellerAddress);
    }
    //创建一个新订单，内部调用
    function createOrder(string _id, address _buyer, address _seller, string _time, string _petId, string _petPrice, int _status,string _result) private {
        orderList.push(Factory.Order(_id, _buyer, _seller, _time, _petId, _petPrice, _status, _result));
    }
    
    //支付对应金额
    function pay(address _from, address _to, uint _price) private {
        require(_to != 0x0);
        require(Balance[_from] >= _price);
        require(Balance[_to] + _price > Balance[_to]); //_value不能为负值
        uint previousBalances = Balance[_from] + Balance[_to];
        Balance[_from] -= _price;
	    Balance[_to] += _price;
        assert(Balance[_from] + Balance[_to] == previousBalances);
    }
    
    //转交宠物所有权
    function changePetOwner(address _from, address _to, string _petId) private {
        require(_from != 0x0);
        require(petIdToOwner[_petId] == _from);
        uint petIndex;
        //将宠物从卖家的宠物列表删除，加入买家的宠物列表
        for(uint i = 0;i < addressToPetList[_from].length;i++){
            if(keccak256(_petId) == keccak256(addressToPetList[_from][i].petId)){
                addressToPetList[_to].push(addressToPetList[_from][i]);
                delete addressToPetList[_from][i];
                petIndex = i;
            }
        }
        //手动将卖家的宠物列表移位，并将列表长度减一
        for(i = petIndex;i < addressToPetList[_from].length; i++){
            addressToPetList[_from][i] = addressToPetList[_from][i+1];
        }
        delete addressToPetList[_from][addressToPetList[_from].length-1];
        addressToPetList[_from].length--;
    }
    
    //购买宠物
    function buyPet(string _petId, uint _price, address _seller) public {
        pay(msg.sender, _seller, _price);
        changePetOwner(_seller, msg.sender, _petId);
    }

}

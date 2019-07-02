pragma solidity ^0.4.25;
import "./DataProcess.sol";
//import "./Market.sol";

interface Market {
    function changePetOwner(address _from, address _to, string _petId, address _caller) external;
    function payByAdmin(address _from,address _to, uint16 _price, address _caller) external;
    function getPetOwner(string _petId) external view returns(address);
}

contract OrderContract is DataProcess{
    //订单结构体
    struct Order {
        string orderId;
        address orderBuyer;
        address orderSeller;
        string orderTime;
        string petId;
        uint16 petPrice;
        uint8 orderStatus;
        string returnReason;
    }
    //声明变量：market合约地址，管理员地址，订单列表，订单id
    Market MK;
    address private adminAddress;
    Order[] public orderList;
    uint orderIdNum=1;

    

    //构造函数 设置管理员地址
    constructor() public {
        adminAddress = msg.sender;
    }
    //修饰符，身份是否管理员
    modifier isAdmin(address _caller) {
        require (_caller == adminAddress);
        _;
    }
    
    
    //内部管理员函数：
    //设置market合约地址
    function setMarketAddress(address _mkAddress) public isAdmin(msg.sender){
        MK = Market(_mkAddress);
    }


    //创建一个新订单，market管理员调用
    function createOrder(address _buyer, address _seller, string _time, string _petId, uint16 _petPrice, address _caller) public isAdmin(_caller) {
        orderList.push(Order(getIntToString(orderIdNum), _buyer, _seller, _time, _petId, _petPrice, 0, ""));
        orderIdNum++;
    }

    
    
    //外部可调用函数：

    //订单相关：
    //公用获得订单函数，买方，卖方，管理员可看
    //输入订单下标
    //返回 订单id，买家，卖家，时间，宠物id，宠物价格，订单状态
    function getOrderByIndex(uint _orderIndex) view public returns (string,address,address,string,string,uint16,uint8){
        require(orderList[_orderIndex].orderBuyer==msg.sender || orderList[_orderIndex].orderSeller==msg.sender || adminAddress==msg.sender);
        return (orderList[_orderIndex].orderId,orderList[_orderIndex].orderBuyer,orderList[_orderIndex].orderSeller,orderList[_orderIndex].orderTime,orderList[_orderIndex].petId,orderList[_orderIndex].petPrice,orderList[_orderIndex].orderStatus);
    }
    
    
    //管理员部分：
    //返回所有订单（管理员查看
    //返回订单下标数组
    function adminGetOrderIndex() view public isAdmin(msg.sender) returns(uint[]){
        uint[] memory temp = new uint[](orderIdNum - 1);
        for(uint i=0;i<orderList.length;i++){
            temp[i] = i;
        }
        return temp;
    }
    
    
    //管理员获得请求仲裁的订单
    //返回订单下标数组
    function adminGetReturnOrderIndex() view public isAdmin(msg.sender) returns(uint[]){
        uint count=0;
        for(uint i=0;i<orderList.length;i++){
            if(orderList[i].orderStatus == 1){
                count++;
            }
        }
        uint[] memory temp = new uint[](count);
        count=0;
        for(i=0;i<orderList.length;i++){
            if(orderList[i].orderStatus == 1){
                temp[count] = i;
                count++;
            }
        }
        return temp;
    }
    
    
    //退货相关：
    //管理员接受退货申请
    //输入 订单id
    function acceptReturn(string _orderId) public isAdmin(msg.sender) {
        for(uint i=0;i<orderList.length;i++) {
            if(keccak256(abi.encodePacked(_orderId)) == keccak256(abi.encodePacked(orderList[i].orderId))){
                require(orderList[i].orderStatus==1);
                orderList[i].orderStatus = 2;
                MK.changePetOwner(orderList[i].orderBuyer, orderList[i].orderSeller, orderList[i].petId, adminAddress);
                MK.payByAdmin(orderList[i].orderSeller,orderList[i].orderBuyer,orderList[i].petPrice, adminAddress);
                break;
            }
        }
    }
    
    //管理员拒绝退货申请
    //输入 订单id
    function rejectReturn(string _orderId) public isAdmin(msg.sender) {
        for(uint i=0;i<orderList.length;i++) {
            if(keccak256(abi.encodePacked(_orderId)) == keccak256(abi.encodePacked(orderList[i].orderId))){
                require(orderList[i].orderStatus==1);
                orderList[i].orderStatus = 3;
                break;
            }
        }
    }


    //用户部分：
    //用户获得自己的订单列表
    //返回订单下标数组
    function userGetOrderId() public view returns(uint[]){
        uint count=0;
        for(uint i=0;i<orderList.length;i++){
            if(orderList[i].orderBuyer == msg.sender || orderList[i].orderSeller == msg.sender){
                count++;
            }
        }
        uint[] memory temp = new uint[](count);
        count=0;
        for(i = 0;i < orderList.length;i++){
            if(orderList[i].orderBuyer == msg.sender || orderList[i].orderSeller == msg.sender){
                temp[count]=i;
                count++;
            }
        }
        return temp;
    }
    
    
    //申请退货
    //输入 订单id 退货原因
    function applyForReturn(string _orderId, string _reason) public {
        for(uint i=0;i<orderList.length;i++) {
            if(keccak256(abi.encodePacked(_orderId)) == keccak256(abi.encodePacked(orderList[i].orderId))){
                //判断申请者为买方且订单状态为可退货
                require(orderList[i].orderBuyer == msg.sender, "You are not the buyer of this order!");
                require(orderList[i].orderStatus == 0, "This order can't be return!");
                require(MK.getPetOwner(orderList[i].petId)==msg.sender);
                orderList[i].orderStatus = 1;
                orderList[i].returnReason = _reason;
            }
        }
        
    }
}

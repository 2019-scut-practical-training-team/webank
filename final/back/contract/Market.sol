pragma solidity ^0.4.25;
import "./DataProcess.sol";

//接口，调用order合约函数
interface OrderContract{
    function createOrder(address _buyer, address _seller, string _time, string _petId, uint16 _petPrice, address _caller) external;
    function getPetOnReturn(string _petId) external view returns(uint8);
}

contract Market is DataProcess{
    //pet结构体
    struct Pet {
        string petName;
        string petId;
        string petType;
        uint16 petPrice;
        uint8 petStatus;
        string petImg;
        string petIntro;
        address Owner;
    }

    //全局变量声明：管理员地址，order合约地址，宠物id计数器，宠物列表
    address private adminAddress;
    OrderContract OD;
    uint petIdNum=1;
    Pet[] petList;




    //构造函数,设置合约部署人为管理员，管理员身份为2
    constructor() public {
        adminAddress = msg.sender;
        userIden[adminAddress] = 2;
    }



    //修饰符，判断身份是不是管理员
    modifier isAdmin(address _caller) {
        require (_caller == adminAddress);
        _;
    }



    //映射：
    //判断是否已创建过宠物
    mapping (address => uint8) createdPet;
    
    //地址与余额映射
    mapping (address => uint) Balance;

    //判断是否老用户
    mapping (address => uint8) oldUser;

    //宠物id到用户地址的映射
    mapping (string => address) petIdToOwner;

    //用户地址与身份的映射,未注册用户是0,普通用户是1,管理员是2
    mapping(address => uint8) userIden;




    //内部调用函数 及 order合约调用的函数：
    //初始化用户余额以及是否创建过宠物参数
    function initUser() private {
        createdPet[msg.sender] = 0;
        Balance[msg.sender] = 10000;
    }


    //设置宠物主人
    function setPetOwner(string _id,address _owner) private {
        petIdToOwner[_id] = _owner;
    }
    
    //支付对应金额，内部调用
    function pay(address _from, address _to, uint16 _price) private {
        require(_to != 0x0);
        require(Balance[_from] >= _price);
        require(Balance[_to] + _price > Balance[_to]);
        uint previousBalances = Balance[_from] + Balance[_to];
        Balance[_from] -= _price;
        Balance[_to] += _price;
        assert(Balance[_from] + Balance[_to] == previousBalances);
    }
    
    //设置order合约地址，用于调用其中的函数，合约部署成功后调用
    function setOrderAddress(address _orderAddress) public isAdmin(msg.sender) {
        OD = OrderContract(_orderAddress);
    }

    //管理员使用的转账函数，用于退货方面的操作
    function payByAdmin(address _from,address _to, uint16 _price, address _caller) public isAdmin(_caller){
        require(_to != 0x0);
        require(Balance[_from] >= _price);
        require(Balance[_to] + _price > Balance[_to]);
        uint previousBalances = Balance[_from] + Balance[_to];
        Balance[_from] -= _price;
        Balance[_to] += _price;
        assert(Balance[_from] + Balance[_to] == previousBalances);
    }

    //转交宠物所有权，用于购买宠物和退货时使用
    function changePetOwner(address _from, address _to, string _petId, address _caller) public isAdmin(_caller) {
        require(_from != 0x0);
        require(petIdToOwner[_petId] == _from);
        setPetOwner(_petId, _to);
        for(uint i = 0;i < petList.length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(petList[i].petId))){
                petList[i].Owner=_to;
            }
        }
    }
    
    //获取宠物主人地址，用于判断退货时宠物是否在发起者手上，order合约调用
    function orderGetPetOwner(string _petId, address _caller) public view returns(address){
        for(uint i=0;i<petList.length;i++){
            if(keccak256(abi.encodePacked(petList[i].petId))==keccak256(abi.encodePacked(_petId))){
                //只有宠物拥有者可查看，宠物上架则其他用户也可查看宠物拥有者
                require(_caller==petList[i].Owner || petList[i].petStatus==1);
                return petList[i].Owner;
            }
        }
    }
    
    





    //外部可以调用的函数：
    
    //用户相关：

    //创建用户函数，初始化余额和是否创建过宠物参数
    function createUser() public {
        require(oldUser[msg.sender] == 0,"You are old user!");
        require(msg.sender != adminAddress, "You are administrator, you have account!");
        initUser();
        oldUser[msg.sender] = 1;
        userIden[msg.sender] = 1;
    }

    //获取用户身份，未注册用户是0,普通用户是1,管理员是2，同时返回公钥地址
    function getUserIden() public view returns (uint8, address){
        return (userIden[msg.sender], msg.sender);
    }

    //获取调用者的余额
    function getBalanceOfMe() public view returns (uint) {
        return Balance[msg.sender];
    }

    //通过地址获取余额
    function getBalance(address _address) public view returns (uint) {
        return Balance[_address];
    }
    //返回用户是否创建过宠物的信息
    function getCreatedPet() public view returns(uint8) {
        return createdPet[msg.sender];
    }
    //获取宠物主人地址
    function getPetOwner(string _petId) public view returns(address){
        for(uint i=0;i<petList.length;i++){
            if(keccak256(abi.encodePacked(petList[i].petId))==keccak256(abi.encodePacked(_petId))){
                //只有宠物拥有者可查看，宠物上架则其他用户也可查看宠物拥有者
                require(msg.sender==petList[i].Owner || petList[i].petStatus==1);
                return petList[i].Owner;
            }
        }
    }
    function getPetStatus(string _petId, address _caller) public view returns(uint8) {
        //只有宠物拥有者可查看
        require(petIdToOwner[_petId]==_caller, "You are not the own of this pet!");
        for(uint i=0;i<petList.length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(petList[i].petId))){
                return petList[i].petStatus;
            }
        }
    }
    
    

    //宠物相关：
    //输入 宠物下标
    //返回 id，类型， 价格， 名字， 宠物上架状态， 宠物图片， 宠物介绍
    function getPetByIndex(uint _index) public view returns (string, string, uint16, string,  uint8, string, string){
        require(_index<=petList.length-1, "This index is out of range!");
        require(msg.sender==petList[_index].Owner || petList[_index].petStatus==1, "You can not see information about this pet!");
        return (petList[_index].petId,petList[_index].petType,petList[_index].petPrice, petList[_index].petName, petList[_index].petStatus,petList[_index].petImg,petList[_index].petIntro);
    }

    //用户查看自己的宠物
    //返回宠物下标数组
    function getPetIndex() public view returns (uint[]) {
        uint count=0;
        for(uint i=0;i<petList.length;i++){
            if(petList[i].Owner==msg.sender){
                count++;
            }
        }
        uint[] memory temp = new uint[](count);
        count=0;
        for(i=0;i<petList.length;i++){
            if(petList[i].Owner==msg.sender){
                temp[count]=i;
                count++;
            }
        }
        return temp;
    }

    //展示市场在售宠物
    //返回宠物下标数组
    function showPetOnSell() public view returns(uint[]){
        uint count=0;
        for(uint i=0;i<petList.length;i++){
            if(petList[i].petStatus==1){
                count++;
            }
        }
        uint[] memory temp = new uint[](count);
        count=0;
        for(i=0;i<petList.length;i++){
            if(petList[i].petStatus==1){
                temp[count]=i;
                count++;
            }
        }
        return temp;
    }



    //创建宠物
    //输入 宠物类型，价格，名字，图片，介绍
    function createPet(string _type, uint16 _price, string _name,  string _img, string _intro) public {
        //判断是否已创建过宠物
        require(createdPet[msg.sender] == 0, "You have alredy created a pet!");
        createdPet[msg.sender] = 1;
        petList.push(Pet(_name, getIntToString(petIdNum), _type, _price, 0, _img, _intro, msg.sender));
        setPetOwner(getIntToString(petIdNum),msg.sender);
        petIdNum++;
    }

    //购买宠物
    //输入 宠物id，时间
    function buyPet(string _petId, string _time) public{
        for(uint i=0;i<petList.length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(petList[i].petId))){
                //判断宠物是否在售
		        require(petList[i].Owner!=msg.sender, "You can't buy your pet!");
                require(petList[i].petStatus==1, "This pet is not on sell!");
                pay(msg.sender, petList[i].Owner, petList[i].petPrice);
                OD.createOrder(msg.sender, petList[i].Owner, _time, _petId, petList[i].petPrice, adminAddress);
                changePetOwner(petList[i].Owner, msg.sender, _petId, adminAddress);
                petList[i].petStatus=0;
                break;
            }
        }
        
    }



    //出售宠物
    //输入 宠物id
    function sellPet(string _petId) public {
        require(petIdToOwner[_petId] == msg.sender, "You are not the owner of this pet!");
        require(OD.getPetOnReturn(_petId) == 0, "Your pet is on return!");
        for(uint i=0;i<petList.length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(petList[i].petId))){
                require(petList[i].petStatus==0, "This pet is on sell!");
                petList[i].petStatus=1;
            }
        }
    }

    //取消出售宠物
    function cancelSellPet(string _petId) public {
        require(petIdToOwner[_petId] == msg.sender, "You are not the owner of this pet!");
        for(uint i=0;i<petList.length;i++){
            if(keccak256(abi.encodePacked(_petId)) == keccak256(abi.encodePacked(petList[i].petId))){
                require(petList[i].petStatus==1, "This pet is not on sell!");
                petList[i].petStatus=0;
            }
        }
    }

    //修改宠物信息
    //可修改 宠物名字，宠物类型，宠物价格，宠物图片地址，宠物介绍
    function changePetInfo(string _petId, string _name, string _type, uint16 _price, string _img, string _intro) public {
        require(petIdToOwner[_petId] == msg.sender, "You are not the owner of this pet!");
        for(uint i=0;i<petList.length;i++){
            if(keccak256(abi.encodePacked(petList[i].petId)) == keccak256(abi.encodePacked(_petId))){
                require(petList[i].petStatus==0,"You can not change it's info because it's on sell");
                petList[i].petName = _name;
                petList[i].petType = _type;
                petList[i].petPrice = _price;
                petList[i].petImg = _img;
                petList[i].petIntro = _intro;
            }
        }
    }
    
}

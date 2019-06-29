pragma solidity ^0.4.19;

contract Factory {
    struct Order {
        string orderId;
        address orderBuyer;
        address orderSeller;
        string orderTime;
        string petId;
        string petPrice;
        int orderStatus;
        string returnResult;
    }
    
    struct Pet {
        string petName;
        string petId;
        string petType;
        int petPrice;
        int petStatus;
        string petImg;
        string petIntro;
        address Owner;
    }
}

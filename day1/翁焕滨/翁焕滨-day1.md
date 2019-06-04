# 第一次作业

## 步骤一：查看区块高度

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/1.png)

由于未进行任何操作，区块的数量为0.



## 步骤二：获取区块数据

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/2.png)

由于区块数量为0，控制台显示块数为1的区块不存在。



## 步骤三：部署HelloWorld智能合约

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/3-1.png)

部署HelloWorld智能合约，会得到一个合约地址，后面可以利用这个合约地址调用合约内的函数。

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/3-2.png)

查看区块的高度，此时已经有一个区块存在了。



## 步骤四：使用查看getDeployLog

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/4.png)

查看log可以看到合约的生成日期、时间、名字和地址。



## 步骤五：调用智能合约

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/5.jpg)

这里调用了合约内的get函数和set函数，get函数可以在屏幕上打印出预设的一条字符串，set函数可以修改预设的字符串的内容，使用set函数修改内容后再次调用get函数可以打印出修改后的内容。



## 步骤六：再次查看区块高度

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/6.png)

这里看到此时的区块高度为2，由于get函数不会生成新的块，所以新生成的块是由set函数的操作生成的。



## 步骤七：获取区块数据

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/7-1.jpg)

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/7-2.jpg)

上面两图是第一个区块和第二个区块的内容。



## 步骤八：按CNS方式部署HelloWorld智能合约

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/8.jpg)

使用CNS方式部署后可以不使用合约地址而调用合约中的函数。



## 步骤九：再次查看区块高度

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/9.jpg)

此时的区块高度为4.



## 步骤十：获取区块数据

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/10-1.png)

![img](https://github.com/2019-scut-practical-training-team/webank/blob/master/day1/%E7%BF%81%E7%84%95%E6%BB%A8/WHB-day1-img/10-2.jpg)

这两张图是第三个、第四个区块的内容。


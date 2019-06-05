# Day1 练习

##### 启动控制台

```bash
#先进入consle目录下
$ cd ~/fisco/console

#启动控制台
$ ./start.sh
```



![1559031106624](https://github.com/Max-Loo/ImageForMarkdown/blob/master/For%20enterprise%20software%20training/For%20Day1%20Exercise/1559031106624.png)



---

##### 查看区块高度

```bash
getBlockNumber
```

![1559031462413](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559031462413.png)



---

##### 查看区块数据

```bash
getBlockByNumber 0
```

![1559031698568](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559031698568.png)



---

##### 部署HelloWorld智能合约

```bash
deploy HelloWorld
```

![1559031812933](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559031812933.png)



---

##### 使用查看getDeployLog

```bash
#在命令行中输入
getDeployLog
```

![1559031969206](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559031969206.png)



---

##### 调用智能合约

```bash
#在控制台输入以下命令

#部署后马上查看区块高度
getBlockNumber

#调用get方法
call HelloWorld [部署时生产的hash值] get

#调用set方法
call helloWorld [部署时生产的hash值] set [要修改的字符串]

#再次调用get方法
call HelloWorld [部署时生产的hash值] get
```

![1559032495129](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559032495129.png)

![1559032331607](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559032331607.png)



---

##### 再次查看区块高度

```bash
getBlockNumber
```

![1559032709692](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559032709692.png)



---

##### 获取区块数据

```bash
#在控制台输入

#查看第一个区块
getBlockNumber 0

#查看第二个区块
getBlockNumber 1
```

![1559032783884](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559032783884.png)

![1559032806988](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559032806988.png)



---

##### 按CNS方式部署HelloWorld智能合约

```bash
#在控制台输入
deployByCNS HelloWorld scut
```

![1559033034402](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559033034402.png)



---

##### 再次查看区块高度

```bash
#在控制台输入
getBlockNumber
```

![1559033241426](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559033241426.png)



---

##### 获取区块数据

```bash
#在控制台输入

#查看第三个区块
getBlockByNumber 2

#查看第四个区块
getBlockByNumber 3
```



![1559033315935](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559033315935.png)

![1559033338968](C:\Users\MAX\AppData\Roaming\Typora\typora-user-images\1559033338968.png)


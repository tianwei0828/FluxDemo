### Android Flux 框架
####   参考facebook https://facebook.github.io/flux/ 提出的flux设计理念，将单向数据流动的思想应用在Android App框架中，采用kotlin语言并结合主流开源框架(1、Rxkotlin 2、Retrofit 3、Okhttp 4、EventBus 5、rxbinding等)打造出Android Flux框架。

#### 一、框架对比
##### 1、MVC
![](https://upload-images.jianshu.io/upload_images/3900981-b2162bc45647eea1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/249)
```
各层职责：
V：一般采用XML文件进行界面的描述
C：Android的控制层的重任通常落在了众多的Activity的肩上,控制V层和M层通信以此来达到分离视图显示和业务逻辑层
M：针对业务模型，建立的数据结构和相关的类，与View无关，而与业务相关的。对数据库的操作、对网络等的操作都应该在Model里面处理
优点：
1、易于理解
2、开发速度快
缺点：
1、XML作为V层，不能动态改变，这就需要Activity承担部分V层逻辑
2、Activity不仅仅承担C层，而且承担了V层的逻辑
3、随着界面及其逻辑的复杂度不断提升，Activity类的职责不断增加，以致变得庞大臃肿
4、V层和M层是相互可知的，这意味着两层之间存在耦合
```
##### 2、MVP
![](http://assets.tianmaying.com/md-image/ea995e88af236afbd8fdc4906a67e829)
```
各层职责：
V：XML+Activity+Fragment等
P：作为V层与M层交互的中间纽带，处理与用户交互的业务逻辑
M：同上
优点：
1、Activity承担的更多的是V层职责，相比于MVC，将C层的逻辑从Activity抽离到P层，使得Activity不再臃肿
2、M层与V层完全分离，我们可以修改V层而不影响M层
3、逻辑放在P层中，可以脱离用户接口来测试这些逻辑（单元测试）
4、我们可以将一个P用于多个V，而不需要改变P的逻辑。这个特性非常的有用，因为V的变化总是比M的变化频繁
缺点：
1、P层与V层是通过接口进行交互的，接口粒度不好控制。粒度太小，就会存在大量接口的情况，使代码太过碎片化；粒度太大，解耦效果不好
2、V层与P层还是有一定的耦合度。一旦V层某个UI元素更改，那么对应的接口就必须得改，数据如何映射到UI上、事件监听接口这些都需要转变，牵一发而动全身
3、复杂的业务同时也可能会导致P层太大，代码臃肿的问题依然不能解决
```
##### 3、MVC与MVP的区别
```
1、MVP中V与M解耦
2、MVP中V与P是交互是通过接口来实现的
3、通常V与P是一对一的，但复杂的V可能绑定多个P来处理逻辑。而C是基于行为的，并且可以被多个V共享，C可以负责决定显示哪个V
```
##### 4、MVVM
![](https://upload-images.jianshu.io/upload_images/3900981-37edcae5f44af894.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)
```
各层职责：
V：XML+Activity+Fragment等
VM：ViewModel的缩写，可以理解成是V的数据模型和P的合体
M：同上
优点：
1、DataBinding可以实现双向的交互，这就使得V和VM之间的耦合程度进一步降低，关注点分离更为彻底，同时减轻了Activity的压力
2、提高可维护性。解决了MVP大量的手动V和M同步的问题，提供双向绑定机制。提高了代码的可维护性
缺点：
1、去除了P层，会导致V层依然过重
2、xml的可读性非常差
3、数据绑定的声明是指令式地写在V的模版当中的，这些内容是没办法去打断点debug的
```
![](https://upload-images.jianshu.io/upload_images/3900981-5b21705fd7befebd.png)
##### 5、MVI

|类型|MVC|MVP|FLUX|
--|--|--|--|
优点|1、开发速度快<br>2、易于理解 | 1、将部分逻辑从Activity和Fragment抽取到P<br>2、部分解耦合 | 1、数据单向流动，易于理解和debug<br>2、彻底解放Activity和Fragment<br>3、所有的事件都是Action驱动<br>4、分层明确，解耦合 |
缺点| 1、紧耦合<br>2、Activity或者Fragment承载V和M的交互，业务复杂时会变得臃肿庞大| 1、接口定义多<br>2、P与P交互时需要通过Activity或者Fragment中转，P与P交互多了，Activity和Fragment又变得臃肿了      | 暂无(自我感觉良好)     |

#### 二、facebook flux结构以及数据流
![](https://facebook.github.io/flux/img/flux-simple-f8-diagram-explained-1300w.png)
##### 1、Action
```
在flux框架中，都是以事件也就是我们这里谈到的action为驱动，不论是用户的点击事件还是我们接收到某一个系统广播所需做的响应
```
##### 2、Dispatcher
```
事件的分发器，它将事件分发到注册在其中的每一个store
```
##### 3、Store
```
接收事件分发器发来的action，并响应对应的action
```
##### 4、View
```
负责展示UI以及响应用户事件并发送相应action
```
#### 三、Android flux结构以及数据流
![](https://ws1.sinaimg.cn/large/c02fa50agy1fswh0belokj21cj12a77h.jpg)

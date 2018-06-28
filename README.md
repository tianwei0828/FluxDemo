### Android Flux 框架
#####   参考facebook https://facebook.github.io/flux/ 提出的flux设计理念，将单向数据流动的思想应用在Android App框架中，采用kotlin语言并结合主流开源框架(1、Rxkotlin 2、Retrofit 3、Okhttp 4、EventBus 5、rxbinding等)打造出Android Flux框架。
#### 一、框架对比
| 类型 || MVC | MVP | FLUX |
| :--- | | :--- | :----: | ----: |
| 优点 || 1、开发速度快<br>2、易于理解 | 1、解放了Activity和Fragment<br>2、解耦合 | 1、数据单向流动，易于理解和debug<br>2、彻底解放Activity和Fragment<br>3、所有的事件都是Action驱动<br>4、分层明确，解耦合 |
| 缺点    || 1、紧耦合<br>2、Activity或者Fragment承载V和M的交互，业务复杂时会变得臃肿庞大| 1、接口定义多<br>2、P与P交互时需要通过Activity或者Fragment中转，P与P交互多了，Activity和Fragment又变得臃肿了      | 暂无(自我感觉良好)     |

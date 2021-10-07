# 美国新冠疫情数据可视化

疫情的爆发对全球都造成了极大的影响，本项目初衷是进行疫情拐点的预测，但现有数据量只有16万条，且预测有很多其它因素，只能展现数据的可视化界面，使数据以报表和折线图展现。

## 序言

我也是一个在校小菜鸡，如果大家有什么好的意见或批评，请务必`issue`下

如果想直接体验该项目，可以`clone`该项目

本项目使用前后端分离，使用vue作为前端框架，使用了element-ui,echarts等组件作为可视化和网站快速成型的工具，后端使用springboot+mybatis+redis+xxljob作为后端框架，本项目使用xxljob为单节点的定时任务搭配redis实现数据的快速展现。若直接使用mysql访问的数据将会变得特别慢，特别慢！！！

## 特性

- 几乎所有需要展现的数据都存入到了redis中，并且定时更新

- 前后端分离，后端对跨域提供支持

- xxljob的定时可直接在界面中操作定时时间

  

## 准备工作

在开始运行项目的过程前，请保证已经配置了一下几点

- 本项目使用redis作为缓存，需要用户在本机已经安装和已经启动了redis

- Maven配置好，更改在自己本机的setting.xml和仓库的地址

- 本项目中有两个启动项，一个为定时任务xxl-job,另一个为new-crown-outbreak

- 首先来说第二个，该模块内为项目的数据展示的后端，在配置文件中更改mysql的密码和xxljob的日志路径

- 再来说第一个，该模块为定时任务的模块，在子模块xxl-job-admin中也需要更改mysql的密码，其中mysql里面出现的一个新库为xxl_job该库为xxljob的多节点需要的库，本任务中不必管，可在xxl-job/doc/db中找到该.sql文件,回到xxl-job-admin中，配置文件下面有一个logback.xml其中有一个路径需要更改，而且该文件需要自己创建

  `<property name="log.path" value="/Users/wuzhengzhuo/Downloads/xxl-job/log/xxl-job-admin.log"/>`

- 最后项目就可以启动了，数据展示模块的启动项为XxljobSpringbooDemo,定时任务的启动项为XxlJobAdminApplication

- 注意，程序的端口号分别为8600和9000，端口占用程序会启动失败

- 傻瓜式讲解，大佬轻点喷

  

## 程序逻辑

1. 前端为独立启动，启动后会自动调用后端接口，程序启动请先运行后端，否则前端无数据显示

2. 前端调用后端接口，在service层中，会查询redis，此时redis中是没有数据的，只有启动的定时任务，让程序运行几分钟，键就会全部加载到redis中，这里没有采用显示和缓存同时进行，因为同时进行仍需要等待。所以先启动端口为9000的定时任务，打开界面账号为admin密码为123456

   ![截屏2021-10-06 下午6.42.28](https://github.com/coco-dog/Data-visualization-webapp/blob/master/images/%E6%88%AA%E5%B1%8F2021-10-06%20%E4%B8%8B%E5%8D%886.42.28.png)

   在该地方执行器为xxl-job-redisdemo![截屏2021-10-06 下午6.44.18](https://github.com/coco-dog/Data-visualization-webapp/blob/master/images/%E6%88%AA%E5%B1%8F2021-10-06%20%E4%B8%8B%E5%8D%886.44.18.png)

   在执行器中启动所有的任务，数据将导入到redis中，程序中大多数数据都是通过redis存储，根据定时任务定时更新。

3. 程序执行正常执行显示图如下

   ![截屏2021-10-06 下午6.57.10](https://github.com/coco-dog/Data-visualization-webapp/blob/master/images/%E6%88%AA%E5%B1%8F2021-10-06%20%E4%B8%8B%E5%8D%886.57.10.png)

## 总结

这个程序很多东西可以进行改进

- 首先就是一些死数据可以直接在程序中定义出来，比如全部州名，不需要再在redis中去取
- 程序的功能单一，没有特别注目的点。
- 有什么值得交流可以通过qq:1993441338交流

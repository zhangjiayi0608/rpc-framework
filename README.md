# rpc-framework

##运行项目

###前置准备
> 一个docker
````
➜ brew cask install docker
````
>一个zookeeper（使用docker安装）

下载：
````
➜ docker pull zookeeper:3.5.8
````
运行：
````
➜ docker run -d --name zookeeper -p 2181:2181 zookeeper:3.5.8
````
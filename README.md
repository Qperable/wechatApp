# wechatApp
用于微信小程序开发，目前为Java版

## 使用须知
### 后台挂载应用程序命令并开启debug端口
nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar wechat-app-0.0.1-SNAPSHOT.jar &
### 配置https访问
1. 生成证书
    如果配置了JAVA开发环境，可以使用keytool命令生成证书。我们打开控制台，输入：
keytool -genkey -alias tomcat -dname "CN=Qper,OU=learn,O=learn,L=FuZhou,ST=JiangXi,C=CN" -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 365
输入后会提示输入密码，这个密码在配置文件有用到。生成后，在家目录找到证书文件，复制到SpringBoot应用的src/main/resources下。
2. 在SpringBoot应用的application.properties增加ssl配置
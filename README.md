
先安装redis 服务器和 数据库

编辑host 文件 添加这两行

127.0.0.1 eurekaserver1
127.0.0.1 eurekaserver2

启动顺序
1.先启动注册中心(eureka项目打包成jar)
java -jar -Dspring.profiles.active=eurekaserver1 C:\Users\haoran1109\Desktop\shop-eureka-1.0-SNAPSHOT.jar
java -jar -Dspring.profiles.active=eurekaserver2 C:\Users\haoran1109\Desktop\shop-eureka-1.0-SNAPSHOT.jar

2.再启动用户服务(shop-provider-uac)main方法启动即可
3.启动网关服务(shop-gateway)main方法启动即可
4.启动订单服务(shop-provider-omc)main方法启动即可

##启动之后调用登录接口生成 jwt token

用户名密码登录生成token
http://localhost:8083/uaa/authentication/form?username=13052389239&password=123456
Headers 里面传参  Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=
                 deviceId:007
                 
##手机短信登录生成token
产生短信验证码
http://localhost:8083/uaa/code/sms?mobile=13052389239
利用控制台打印的短信验证码登录
http://localhost:8083/uaa/authentication/mobile?mobile=13052389239&smsCode=498467
Headers 里面传参  Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=
                 deviceId:007            
     
社交登录(QQ,微信)生成token

下面的地址是通过手机端调起微信授权传送到后端的授权码 获取微信的 providerId以及  openId (回调网站是在开放平台自己配置的域名)
http://www.****.com/qqLogin/weixin?code=011fb4Zf1PlNot0qxZXf1DZ2Zf1fb4Z1&state=8fbff73f-df99-4687-8a30-9734c7a4676b           


http://localhost:8083/uaa/authentication/openid?providerId=weixin&openId=od4PTw4pV1I3BMKxlXB7cipJUjvc
Headers 里面传参  Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=
              deviceId:007    
              
              
利用生成的token 访问资源服务器(用户资源服务器和订单服务器)

http://localhost:8083/uaa/user/me
Headers 里面传参 Authorization:Bearer 登录返回的token ,比如  Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIxMzA1MjM4OTIzOSIsInNjb3BlIjpbImFsbCJdLCJsb2dpbk5hbWUiOiIxMzA1MjM4OTIzOSIsImNvbXBhbnkiOiJzaG9wIiwiZXhwIjoxNTY4NzUwNzQxLCJhdXRob3JpdGllcyI6WyIvb3JkZXIvKioiLCIvcm9sZS8qKiIsIi9hZG1pbi8qKiIsIi91c2VyLyoqIiwiUk9MRV_otoXnuqfnrqHnkIblkZgiLCJhZG1pbk1hbmFnZSIsInJvbGVNYW5hZ2UiXSwianRpIjoiMjFiOWIwNWYtZmFkMi00NjNmLWJiNmUtM2RiYzc2NDQyYmZhIiwiY2xpZW50X2lkIjoiaW1vb2MifQ.RBPcejZxmQl4DCaRZdjj12nLahlNr2Moh1Ez-DLvPkE

http://localhost:8083/order/order/list
Headers 里面传参 Authorization:Bearer 登录返回的token ,比如  Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIxMzA1MjM4OTIzOSIsInNjb3BlIjpbImFsbCJdLCJsb2dpbk5hbWUiOiIxMzA1MjM4OTIzOSIsImNvbXBhbnkiOiJzaG9wIiwiZXhwIjoxNTY4NzUwNzQxLCJhdXRob3JpdGllcyI6WyIvb3JkZXIvKioiLCIvcm9sZS8qKiIsIi9hZG1pbi8qKiIsIi91c2VyLyoqIiwiUk9MRV_otoXnuqfnrqHnkIblkZgiLCJhZG1pbk1hbmFnZSIsInJvbGVNYW5hZ2UiXSwianRpIjoiMjFiOWIwNWYtZmFkMi00NjNmLWJiNmUtM2RiYzc2NDQyYmZhIiwiY2xpZW50X2lkIjoiaW1vb2MifQ.RBPcejZxmQl4DCaRZdjj12nLahlNr2Moh1Ez-DLvPkE


参考了  paascloud-master项目  https://github.com/paascloud/paascloud-master  
也看了慕课网的 springboot security 视频    https://coding.imooc.com/class/134.html  有兴趣的同学可以看看





                 



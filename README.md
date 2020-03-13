## 小陈社区

## 资料
[spring](https://spring.io/guides/)    
[Thymeleaf](https://spring.io/guides/gs/serving-web-content/)  
[github-ssh](https://help.github.com/en/github/authenticating-to-github/connecting-to-github-with-ssh)  
[community](https://github.com/tockchen/community/tree/6ed43775d08ec071d7e63ba4cbadba93bfbb9dc5)   
[IDEA配置GIT](https://blog.csdn.net/sundehui01/article/details/102567785)   
[w3cschool](https://www.w3cschool.cn/git/)  
[git API](https://developer.github.com/apps/)  
[git笔记](https://github.com/tockchen/notes/blob/master/git%E8%84%9A%E6%9C%AC.md)   
[Bootstrap](https://v4.bootcss.com/)
[okhttp](https://square.github.io/okhttp/)  
[hutool未用](https://hutool.cn/)  
[UML](https://mp.weixin.qq.com/s/KR2HCcCoIc-gSDLZ69azYw)   
[MySql](https://www.runoob.com/mysql/mysql-tutorial.html)  
[Spring Boot 整合 Mybatis](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)   
[flyway](https://flywaydb.org/getstarted/why)

## 工具
[git](https://git-scm.com/) 

# 命令

## Mysql脚本
```sql
create table USER
(
	ID INT auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);


```

## git命令
### 仓库初建
```cmd
git init 初始化本地 git 仓库
git status 用于查看项目的当前状态
git add . 将文件添加到缓存
git commit -m '' 提交
git remote add origin git@github.com:tockchen/community.git
git push -u origin master
```


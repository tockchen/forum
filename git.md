Git 同时与多个远程库互相同步
情形:有两个git服务器，比如github,gitosc,有个项目同时在两个服务器上，要互相同步

其实命令还是比较简单的，比如一个现有的git项目，在github,gitosc中分别创建好对应的项目。

1：移除现在旧有的远程服务器origin
```git
git remote rm origin
```

2:关联gitosc远程库
```git
git remote add gitosc https://gitee.com/hongdada/learngit.git
git push -u gitosc master
```
关联github远程库
```git
git remote add github https://github.com/hongdada/learngit.git
git push -u github master
```

3.查看远程库信息

λ git remote -v
```cmd
github  https://github.com/hongdada/learngit.git (fetch)
github  https://github.com/hongdada/learngit.git (push)
gitosc  https://gitee.com/hongdada/learngit.git (fetch)
gitosc  https://gitee.com/hongdada/learngit.git (push)
```
这样就ok了，就布置好了，下面就是操作
```cmd
D:\代码\Git\learngit
λ git push
Counting objects: 2, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (2/2), 223 bytes | 0 bytes/s, done.
Total 2 (delta 1), reused 0 (delta 0)
To https://gitee.com/hongdada/learngit.git
   a48d040..875d588  master -> master

D:\代码\Git\learngit
λ git push gitosc master
Everything up-to-date

D:\代码\Git\learngit
λ git push github master
Counting objects: 2, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (2/2), 223 bytes | 0 bytes/s, done.
Total 2 (delta 1), reused 0 (delta 0)
remote: Resolving deltas: 100% (1/1), completed with 1 local object.
To https://github.com/hongdada/learngit.git
   a48d040..875d588  master -> master
```

可以看出我第一次是直接git push，没有指定远程库名称，默认推送到了gitosc中，开始还以为一次性推送到了2个服务器呢，剩下的github需要指定名称推送。
如果一次性推送呢

方法一：
```cmd 
D:\代码\Git\learngit
λ git remote  rm github

D:\代码\Git\learngit
λ git remote rm gitosc

D:\代码\Git\learngit
λ git remote add all https://gitee.com/hongdada/learngit.git

D:\代码\Git\learngit
λ git remote set-url --add all https://github.com/hongdada/learngit.git
```

推送：
```cmd
D:\代码\Git\learngit
λ git push all --all
Counting objects: 2, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (2/2), 269 bytes | 0 bytes/s, done.
Total 2 (delta 0), reused 0 (delta 0)
To https://gitee.com/hongdada/learngit.git
   af6a587..48a0880  master -> master
Counting objects: 2, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (2/2), 269 bytes | 0 bytes/s, done.
Total 2 (delta 0), reused 0 (delta 0)
To https://github.com/hongdada/learngit.git
   af6a587..48a0880  master -> master
```

看到有2个推送说明

修改前打开项目.git文件夹内的config文件

```cmd
[core]
    repositoryformatversion = 0
    filemode = false
    bare = false
    logallrefupdates = true
    symlinks = false
    ignorecase = true
[branch "master"]
    remote = gitosc
    merge = refs/heads/master
[branch "dev"]
[remote "github"]
    url = https://github.com/hongdada/learngit.git
    fetch = +refs/heads/*:refs/remotes/github/*
[remote "gitosc"]
    url = https://gitee.com/hongdada/learngit.git
    fetch = +refs/heads/*:refs/remotes/gitosc/*

```
 修改后查看：
```cmd
[core]
    repositoryformatversion = 0
    filemode = false
    bare = false
    logallrefupdates = true
    symlinks = false
    ignorecase = true
[branch "master"]
[branch "dev"]
[remote "all"]
    url = https://gitee.com/hongdada/learngit.git
    fetch = +refs/heads/*:refs/remotes/all/*
    url = https://github.com/hongdada/learngit.git
```

 方法二：根据上面的配置可以引出第二种一起修改多远程的方式，直接修改配置文件.git/config

删除all
```git
git remote rm all
```
查看配置文件：
```文本
[core]
    repositoryformatversion = 0
    filemode = false
    bare = false
    logallrefupdates = true
    symlinks = false
    ignorecase = true
[branch "master"]
[branch "dev"]
```

修改配置文件为：
```文件
[core]
    repositoryformatversion = 0
    filemode = false
    bare = false
    logallrefupdates = true
    symlinks = false
    ignorecase = true
[branch "master"]
[branch "dev"]
[remote "all"]  
    url = https://github.com/hongdada/learngit.git 
    url = https://gitee.com/hongdada/learngit.git  

```

推送信息：
```git
D:\代码\Git\learngit
λ git push all --all
Counting objects: 3, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 290 bytes | 0 bytes/s, done.
Total 3 (delta 0), reused 0 (delta 0)
To https://github.com/hongdada/learngit.git
   48a0880..2dab796  master -> master
Counting objects: 3, done.
Delta compression using up to 4 threads.
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 290 bytes | 0 bytes/s, done.
Total 3 (delta 0), reused 0 (delta 0)
To https://gitee.com/hongdada/learngit.git
   48a0880..2dab796  master -> master
```
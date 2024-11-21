# 11月20日笔记

### Maven

* Maven的环境：Maven的本地仓库是在settings文件里面配置，项目对应的Maven和Maven对应的本地仓库都是独立的。不同项目使用的Maven不同。
  TODO: 不同项目使用不同的Maven如何配置？
```xml
<localRepository>xxx</localRepository>
```

* 在Idea中，自带Maven，所以我使用的Maven就是自带的Maven。

mac使用homebrew安装Maven（但是好像只有最新版本的？）







---

### Git的dev分支和工作流程

* 开发的git使用：本地创建dev分支，不与origin/master进行同步，这个分支只进行开发。当要提交代码时，origin/master和本地master进行同步，然后dev merge 本地master，并进行master的上传。
* 然后再次dev开发的时候，要保证版本的一致。这里直接把本地master merge到dev就行。因为master刚刚版本控制过（master=dev+其他），这里一定不会出现冲突。

问题：idea git log图看不懂！



---

### Docker 迁移






```shell
df 
# 查看存储空间
```

* Docker 迁移操作
* 把当前的一个Docker容器压缩成镜像，然后移动到另一个服务器的Docker中运行出来



```shell
docker commit 容器名称/容器的id 镜像名称			  # 把docker容器保存成一个镜像
docker save -o 镜像tar文件名称 镜像名称/镜像id		 # 把镜像保存为tar文件
docker load -i 镜像名称							  # 把tar文件恢复成为一个镜像
```

示例代码

```shell
docker commit redis01 myredis				     # 将redis01容器保存为一个镜像
docker save -o myredis.tar myredis 				 # 将myredis镜像保存为一个tar文件
docker rmi myredis								 # 删除之前的myredis镜像
docker load -i myredis.tar 						 # 将myredis.tar恢复成一个镜像
```

==测试了一下，出现的问题是，由于架构不一样导致==



==如果MySQL容器使用了镜像卷，那压缩成镜像的过程会出问题吗？==

（测试完了，好像镜像卷的内容确实不会保存进来）

==查看一个容器是否是用了数据卷==



---

### ubuntu清理磁盘空间

```
root@ubuntu:/home/jasper# docker load -i mydb.tar
ad6b69b54919: Loading layer [==================================================>]  72.55MB/72.55MB
fba7b131c5c3: Loading layer [==================================================>]  338.4kB/338.4kB
0798f2528e83: Loading layer [==================================================>]  9.556MB/9.556MB
a0c2a050fee2: Loading layer [==================================================>]  4.202MB/4.202MB
d7a777f6c3a4: Loading layer [==================================================>]  2.048kB/2.048kB
0d17fee8db40: Loading layer [==================================================>]  53.77MB/53.77MB
aad27784b762: Loading layer [==================================================>]  5.632kB/5.632kB
9b64bb048d04: Loading layer [==================================================>]  3.584kB/3.584kB
35ba198e64f5: Loading layer [============================================>      ]  279.6MB/313.2MB
write /usr/bin/myisamchk: no space left on device

```



```
root@ubuntu:/home/jasper# df -h
Filesystem                         Size  Used Avail Use% Mounted on
udev                               1.9G     0  1.9G   0% /dev
tmpfs                              392M  1.4M  391M   1% /run
/dev/mapper/ubuntu--vg-ubuntu--lv  9.8G  9.2G   85M 100% /
tmpfs                              2.0G     0  2.0G   0% /dev/shm
tmpfs                              5.0M     0  5.0M   0% /run/lock
tmpfs                              2.0G     0  2.0G   0% /sys/fs/cgroup
/dev/loop1                          92M   92M     0 100% /snap/lxd/29631
/dev/nvme0n1p2                     1.8G  206M  1.5G  13% /boot
/dev/nvme0n1p1                     952M  6.4M  945M   1% /boot/efi
/dev/loop0                          60M   60M     0 100% /snap/core20/2383
/dev/loop3                          62M   62M     0 100% /snap/lxd/22761
tmpfs                              392M     0  392M   0% /run/user/1000
/dev/loop5                          60M   60M     0 100% /snap/core20/2437
/dev/loop4                          34M   34M     0 100% /snap/snapd/21761
overlay                            9.8G  9.2G   85M 100% /var/lib/docker/overlay2/e95fc76e4cb888b577e614a1183e5a1f612fe7cfa3af4c54d7aafd42981e1483/merged
overlay                            9.8G  9.2G   85M 100% /var/lib/docker/overlay2/0e8475bc1f45506a754187cb9bc4a1e0d841316703974d02749d14202ac82900/merged

```

真的没有想到，我的ubuntu20虚拟机磁盘空间竟然用完了！



---

### Ubuntu虚拟机扩容



```
root@ubuntu:/home/jasper# sudo fdisk /dev/nvme0n1

Welcome to fdisk (util-linux 2.34).
Changes will remain in memory only, until you decide to write them.
Be careful before using the write command.

GPT PMBR size mismatch (41943039 != 83886079) will be corrected by write.

Command (m for help): p

Disk /dev/nvme0n1: 40 GiB, 42949672960 bytes, 83886080 sectors
Disk model: VMware Virtual NVMe Disk
Units: sectors of 1 * 512 = 512 bytes
Sector size (logical/physical): 512 bytes / 512 bytes
I/O size (minimum/optimal): 512 bytes / 512 bytes
Disklabel type: gpt
Disk identifier: 2495C3D7-088B-43B8-98FB-C283FBF89039

Device           Start      End  Sectors  Size Type
/dev/nvme0n1p1    2048  1953791  1951744  953M EFI System
/dev/nvme0n1p2 1953792  5668863  3715072  1.8G Linux filesystem
/dev/nvme0n1p3 5668864 41940991 36272128 17.3G Linux filesystem

Command (m for help): d
Partition number (1-3, default 3): 3

Partition 3 has been deleted.

Command (m for help): n
Partition number (3-128, default 3): 
First sector (5668864-83886046, default 5668864): 
Last sector, +/-sectors or +/-size{K,M,G,T,P} (5668864-83886046, default 83886046): 

Created a new partition 3 of type 'Linux filesystem' and of size 37.3 GiB.
Partition #3 contains a LVM2_member signature.

Do you want to remove the signature? [Y]es/[N]o: N

Command (m for help): w

The partition table has been altered.
Syncing disks.


```





---

### 制作 SpringBoot Docker 镜像



> 初始项目在maven导入依赖的时候总是报错，报错的原因是3.0.5版本的spring-boot-starter-parent似乎找不到。
>
> 不太清楚原因。我考虑下载一个单独的maven，不用idea自带的了。（自带的太不方便了，mvn指令也执行不了）
>
> 我换了maven库存在的3.2.3试了一下，同样报错：
>
> ```
> Unresolved plugin: 'org.springframework.boot:spring-boot-maven-plugin:3.0.5'
> ```
>
> **查找设置发现maven的路径竟然是错的，还有C盘！这个东西也能记录到项目中吗？**
>
> 然后我把maven的路径修改成正确的（idea自带的），然后就好使了。。。



参考博客：

* [centos7安装docker](https://cloud.tencent.com/developer/article/1701451)
* [ubuntu扩容](https://blog.csdn.net/qq_44339029/article/details/120597169)
* [ubuntu扩容](https://blog.csdn.net/qq_34160841/article/details/113058756)



# 11月21日笔记

## 前端项目

mock是模拟数据的意思，模拟后端给前端发送过来的数据

<img src="/Users/jmjin/Desktop/Snipaste_2024-11-21_14-03-25.png" alt="Snipaste_2024-11-21_14-03-25" style="zoom:50%;" />

* Local storage本地存储是干什么的？好像是用来前端登录记录用的。他和cookie有啥区别
* 需要下载和安装vue3？我好像没下载。。。
* TODO：弄清楚登录过程index.vue的相关代码，和登录过程的调用
* 啥是路由前置守卫？



userinfo不是通过登录的vue调用的，而是其他的组件调用的。

分析过程：在f12检查中发现了userinfo的request，url是/api/userinfo，然后api中找到/api/userinfo这个请求是getUserinfo的。然后搜索是谁调用了getUserinfo，找到了是permission.js。permission.js是路由的前置守卫，应该是入口文件main.js加载的。

vscode快捷键设置：

Code->首选项->键盘快捷方式

修改go back和go forward



### main.js

router

pinia



index.vue是干啥的



### router

**配置和管理前端路由的核心文件，它使用了 Vue Router 库来创建和控制页面路由**

* 当你在 JavaScript 或 TypeScript 项目中使用 `import router from './router'` 时，系统会自动查找 `router` 文件夹下的 `index.js` 或 `index.ts` 文件。这是因为 `index.js`（或 `index.ts`）被视为文件夹的默认入口文件。
* 如果 `import redirect from './modules/redirect'` 执行时，系统首先会查找一个名为 `redirect.js`（或 `.ts` 等）的文件。如果没有找到这样的文件，它才会查找名为 `redirect` 的目录，并尝试加载该目录下的 `index.js` 文件。
* `[...home]` 使用的是 JavaScript 的**扩展运算符**（spread operator），它用于将一个数组的所有元素展开到另一个数组中。在这个语境中，`home` 可能是一个包含多个路由对象的数组，使用扩展运算符可以将这些路由对象直接展开并合并到新的数组中，这种方式常用于组合和管理路由配置。
* **路由数组** 是一个数组，其中每个元素都是一个**路由对象**，定义了路径、组件和其他路由元数据。在 Vue Router 中，这个数组用于配置路由器实例，告诉 Vue 应用如何根据不同的 URL 地址加载不同的组件。
* `const Home = () => import('@/views/home/index.vue')` 的语法：这是一个**动态导入**的例子，也是一个利用箭头函数返回一个 Promise 对象的表达式，这个 Promise 将异步地解析为一个 Vue 组件。这种语法是实现路由懒加载的常见方式，在 Vue Router 中广泛使用。当路由被访问时，对应的组件才会被加载，有助于减少应用的初始加载时间。
* 编程式导航：指的是使用 JavaScript 代码（而非链接点击）来实现页面跳转。在 Vue Router 中，你可以使用 `router.push` 或 `router.replace` 方法来编程式地导航到不同的路由。





### promise对象

**Promise** 是 JavaScript 中用于处理异步操作的对象。它表示一个尚未完成但预计将来会完成的操作的结果。

```javascript
let myPromise = new Promise((resolve, reject) => {
  setTimeout(() => {
    resolve("Data received");
  }, 3000);
});

myPromise.then(data => {
  console.log(data);  // 输出: Data received
});

```



### 路由

[vue路由测试](https://play.vuejs.org/#eNqFVVtv2zYU/itn6gArmC05btEHTXXTFcWyYZeiLfYy7UGWji02EsmRlOPA8H/fIambnaRD4Fg61++c7yN9DJqc8eirDpKANVIoA0coFOYG30kJJ9gq0cBs3+Is412AEq1B1Xmi2L+ObpvX+3IpI5+b8aFqSJ+rjANErcbQp/v3RrTchLMXlDa7CuZBl07YUoONrCl/bQPT6np9i3UtbLPv0phenVm6L3rQRgm+W79vlULeIQaZmypJ484HxyN87xzRtq3rj+SE08mViX2dlOf7vuAnh/I3xu/AiDdZEGfB+mdBz3ArGkzj0f9sRr4hy5D2zr49ykvjvmdqeTmv9RfDe4i7uM6dxsNiaF9+l0+y+Ts2Qj3cMm3oa94Zfd0py4uBzYFPO6Br3ZPaGzpme9rtQGdxg2WUgOC6Y0PDG/jbjnL0vMAsnhEsQcU4UZaMbU/z8zC3x/PYsbcN/ueilaJW03nDoy1Y+VUkT+0nvHI9PVB6PJE8M44HN2iJ27yt+9q09ek+rFR1oZg0RM5FgmvboKlEqRP/BrATX4SDH171JgBD4CIvThXJVldhP7Y7J9DtxP4nxZKk+470cnFQVuseHh2TlTduWmMEh5uiZsUdSXPAcKlOH/hIZmfEjhODRtPaozNKjyiiGcqn75Ej0Pl3lMyHp2fFeMHnEB/SRia+ict6ep/GXBWV1UGHyGtgh5O1K0KvuC8T/duieoi6tLdvYUYg+rXTmKH3jLmeKoW0owLDI7h8IrnvfAKrIargxfQ/lA0LHjmr8w3W3X3w2dVMIGWchoH9ohEl1pFRrCE2fccsgCY/1Mh3piLjaknc+pujr3TOqedk0eSSrg/BiVU3WtY5dBYMks2CkRtrzoLKGKmTOG65vNtFtON4jLh5Fb2MlnFJJ2tijVA3i40S99rdV1ngNmtr31BQXOLeCFHrRS7Zcy0eBd68jl5H13HNNjFVjxkv8eBq94unMY0mQWzZ7mJIKwtWo/pTGkaCORs2p9+Z+1+dzagWB6BFhcXdE/av+uAhf1RI0+1xMpzJFWnOuz98/gMP9Dw4icW2puhvOD+hFnVrMfqwn1peEuxJnEP7i+OM8d0X/eFgkOt+KAt0FLIj8v03Rh/hvoxeTbaozUONOiq0/aGhX6w5aY1xn7cRqkSVwEoegMCyEl4sl8sf3d1H5RhfbATdKk0C10t5cHaZlyWBHSzUJeNUFtaQww/08Tenz65xSzf+NLJaTTuP5UcARVFMACSwpL9VVyE4/QesCg/V)

main.js

```javascript
import { createApp } from 'vue'
import router from './router'
import App from './app.vue'

createApp(App) // 创建一个新的 Vue 应用实例，将 App 作为根组件。
  .use(router) // 将 Vue Router 插件注册到这个应用中，这允许你在应用中使用路由功能。
  .mount('#app') // 将 Vue 应用挂载到 DOM 中的一个具体元素上（通常是一个 id 为 app 的 div 元素）
```

* 

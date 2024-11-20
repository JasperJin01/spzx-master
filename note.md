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

==测试了一下，出现的问题是，由于==



==如果MySQL容器使用了镜像卷，那压缩成镜像的过程会出问题吗？==



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




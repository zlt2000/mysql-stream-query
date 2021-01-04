## MySQL流式查询

程序访问 MySQL 数据库时，当查询出来的数据量特别大时，数据库驱动把加载到的数据全部加载到内存里，就有可能会导致内存溢出（OOM）。

其实在 MySQL 数据库中提供了流式查询，允许把符合条件的数据分批一部分一部分地加载到内存中，可以有效避免OOM



**文章地址**：[https://mp.weixin.qq.com/s/PStUk7LoHx64xacJnHp5ew](https://mp.weixin.qq.com/s/PStUk7LoHx64xacJnHp5ew)
# JVM内存溢出导致的CPU过高问题排查
在近期做压力测试过程中发现50个线程会导致应用层CPU居高不下。
分析：线程数很少，数据量也不多。初步怀疑是某些程序逻辑不够严谨。
初步怀疑是有些老年代的东西不会被GC回收，导致老年代占比一直很高，试GC不停地fullGC,导致CPU过高。

- 在linux上执行top命令发现CPU在100%
 ![](../../phone/k.png)
 
- 执行sudo ./jmap -heap pid(java线程的pid可以根据top命令查询)可以看出老年代在90%以上。
 ![](../../phone/l.png)
 
- 再看看fullGC的频率(可以看出full GC的频率是相当高的)这时我们基本可以判断是程序问题，或者存储的集合十分庞大没有分批处理导致。

 ![](../../phone/m.png)
 
- 用eclipse的mat堆栈分析,可以看出有一个对象占了1.7GB。点击1.7G的扇形区域点击List Object —>with outgoing references。一层一层的找。

 ![](../../phone/mat.png)

- 最后发现有一个list的对象有近80万数据。可以猜测是没有进行分批处理。然后找出代码，并修改代码为分批处理。

 ![](../../phone/mat2.png)
# OutOfMemoryError异常

## JAVA堆溢出

   java堆用于存储对象实例。只要不断的创建对象，并保证对象之间有可达的路径。这些对象就不会被GC给回收，当达到一定的量时就会跑出OutOfMemoryError
   以下代码是在Eclipse IDE上执行需要设置run/debug的jvm参数和MAT解析
   这里需要先设置以下jvm参数防止电脑CPU爆满导致电脑卡死
   ![](../../phone/b.png)
   


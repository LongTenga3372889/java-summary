## ArrayList
### 常用方法
- boolean add(E e)
将指定的元素添加到此列表的尾部。

- void add(int index, E element)
将指定的元素插入此列表中的指定位置。

- boolean addAll(Collection<? extends E> c)
按照指定 collection 的迭代器所返回的元素顺序，将该 collection 中的所有元素添加到此列表的尾部。

- boolean addAll(int index, Collection<? extends E> c)
从指定的位置开始，将指定 collection 中的所有元素插入到此列表中。

- void clear()
移除此列表中的所有元素。

- Object clone()
返回此 ArrayList 实例的浅表副本。

- boolean contains(Object o)
如果此列表中包含指定的元素，则返回 true。

- void ensureCapacity(int minCapacity)
如有必要，增加此 ArrayList 实例的容量，以确保它至少能够容纳最小容量参数所指定的元素数。

- E get(int index)
返回此列表中指定位置上的元素。
 
- int indexOf(Object o)
返回此列表中首次出现的指定元素的索引，或如果此列表不包含元素，则返回 -1。

- boolean isEmpty()
如果此列表中没有元素，则返回 true

- int lastIndexOf(Object o)
返回此列表中最后一次出现的指定元素的索引，或如果此列表不包含索引，则返回 -1。

- E remove(int index)
移除此列表中指定位置上的元素。

- boolean remove(Object o)
移除此列表中首次出现的指定元素（如果存在）。

- protected void removeRange(int fromIndex, int toIndex)
移除列表中索引在 fromIndex（包括）和 toIndex（不包括）之间的所有元素。

- E set(int index, E element)
用指定的元素替代此列表中指定位置上的元素。

- int size()
返回此列表中的元素数。

- Object[] toArray()
按适当顺序（从第一个到最后一个元素）返回包含此列表中所有元素的数组。

- <T> T[] toArray(T[] a)
按适当顺序（从第一个到最后一个元素）返回包含此列表中所有元素的数组；返回数组的运行时类型是指定数组的运行时类型。

- void trimToSize()
将此 ArrayList 实例的容量调整为列表的当前大小。

### 迭代

```java
    ArrayList<Integer> intList = new ArrayList<>();
    intList.add(123);
    intList.add(456);
    intList.add(789);
    for(Integer a:intList){
        System.out.println(a);
    }
```

使用foreach写法看起来更简洁，而且他适用于各种容器，更为通用。

这里要注意，当在迭代的过程中对容器结构发生变化会导致`java.util.ConcurrentModificationException`。这是因为ArrayList中的iterator实现的代码，他会对是否修改容器进行判断。
假如想要在迭代的时候修改必须要使用`Iterator.remove()`。

### ArrayList特点

- 可以随机访问任何一个位子，按照索引位置进行访问效率很高。O(1)
- 除非ArrayList已经排序否则按照内容查找元素效率低。O(N)
- 增加元素快。O(1)
- 插入和删除效率比较慢。O(N)
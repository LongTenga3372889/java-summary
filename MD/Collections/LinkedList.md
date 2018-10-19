## LinkedList
### 常用方法

- boolean       add(E object)
添加一个元素到此list的末尾返回成功还是失败

- void          add(int location, E object)
将指定的元素插入此list中的指定位置。

- boolean       addAll(Collection<? extends E> collection)
按照指定 collection 的迭代器所返回的元素顺序，将该 collection 中的所有元素添加到此列表的尾部。

- boolean       addAll(int location, Collection<? extends E> collection)
从指定的位置开始，将指定 collection 中的所有元素插入到此列表中。

- void          addFirst(E object)
将此元素添加到头位子

- void          addLast(E object)
将次元素添加到尾位子

- void          clear()
清除该list的所有内容

- Object        clone()
返回此 ArrayList 实例的浅表副本。

- boolean       contains(Object object)
判断是否存在对应的值。O(N)

- Iterator<E>   descendingIterator()
递减迭代器

- E             element()
返回list的第一个信息

- E             get(int location)
返回list的第location的信息

- E             getFirst()
返回list的第一个信息

- E             getLast()
返回list的最后一个信息

- int           indexOf(Object object)
返回等于object的正向位子信息

- int           lastIndexOf(Object object)
返回等于object的反向位子信息

- ListIterator<E>     listIterator(int location)
返回索引位子的迭代器

- E             peek()
判断list的第一个节点是否为空为空则返回null,不为空返回内容

- E             peekFirst()
判断list的第一个节点是否为空为空则返回null,不为空返回内容

- E             peekLast()
判断list的最后一个节点是否为空为空则返回null,不为空返回内容

- E             remove()
删除list的第一个元素

- E             remove(int location)
删除list的指定位子的元素

- boolean       remove(Object object)
删除指定内容的元素,只删除第一个

- E             removeFirst()
删除第一个元素

- boolean       removeFirstOccurrence(Object o)
删除指定内容的元素,只删除第一个

- E             removeLast()
删除最后一个元素

- boolean       removeLastOccurrence(Object o)
删除指定内容的元素,只删除第一个

### 特点

- 按实际情况分配空间，不需要预先分配空间。

- 可以随机访问，但是访问的效率很低复杂度为O(N/2)。

- 在两端删除插入元素效率很快复杂度为O(1)。

- 在中间插入删除元素效率低复杂度为O(N)
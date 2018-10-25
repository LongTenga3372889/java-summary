# spring事务

什么是事务: 
事务逻辑上的一组操作,组成这组操作的各个逻辑单元,要么一起成功,要么一起失败.

## 事务特性（4种）

- 原子性 （atomicity）:强调事务的不可分割. 

- 一致性 （consistency）:事务的执行的前后数据的完整性保持一致. 

- 隔离性 （isolation）:一个事务执行的过程中,不应该受到其他事务的干扰 

- 持久性（durability） :事务一旦结束,数据就持久到数据库

## 如果不考虑隔离性引发安全性问题

脏读 :一个事务读到了另一个事务的未提交的数据 

不可重复读 :一个事务读到了另一个事务已经提交的 update 的数据导致多次查询结果不一致. 

虚幻读 :一个事务读到了另一个事务已经提交的 insert 的数据导致多次查询结果不一致.

## 设置事务隔离级别（5种）

- DEFAULT 这是一个PlatfromTransactionManager默认的隔离级别，使用数据库默认的事务隔离级别. 

- 未提交读（read uncommited） :脏读，不可重复读，虚读都有可能发生 

- 已提交读 （read commited）:避免脏读。但是不可重复读和虚读有可能发生 

- 可重复读 （repeatable read） :避免脏读和不可重复读.但是虚读有可能发生. 

- 串行化的 （serializable） :避免以上所有读问题. 

说明

Mysql 默认:可重复读 

Oracle 默认:读已提交

## 事务的传播行为 

* 保证同一个事务中 
(1)PROPAGATION_REQUIRED 支持当前事务，如果不存在 就新建一个(默认) 

(2)PROPAGATION_SUPPORTS 支持当前事务，如果不存在，就不使用事务 

(3)PROPAGATION_MANDATORY 支持当前事务，如果不存在，抛出异常 

* 保证没有在同一个事务中 
(1)PROPAGATION_REQUIRES_NEW 如果有事务存在，挂起当前事务，创建一个新的事务 

(2)PROPAGATION_NOT_SUPPORTED 以非事务方式运行，如果有事务存在，挂起当前事务 

(3)PROPAGATION_NEVER 以非事务方式运行，如果有事务存在，抛出异常 

(4)PROPAGATION_NESTED 如果当前事务存在，则嵌套事务执行
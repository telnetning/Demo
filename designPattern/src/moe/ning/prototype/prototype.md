wikipedia prototype pattern: https://en.wikipedia.org/wiki/Prototype_pattern

使用场景：创建一个对象需要消耗比较长的时间，比如在创建对象的过程中，涉及到网络通信、数据库读写、磁盘 io 的。

需要注意的是，使用浅拷贝还是深拷贝。


#### spring中创建实例指定的prototype是原型模式吗？
stackoverflow 上的提问：https://stackoverflow.com/questions/26609980/spring-prototype-following-prototype-design-pattern

不是的，很明显，spring 中的 bean 并没有实现 Cloneable 接口。
实际上是使用的反射。

```java
/* AbstractBeanFactory.doGetBean */
if (mbd.isSingleton()) {
    //... 
} else if (mbd.isPrototytpe()) {
    //...
    Object prototypeInstance;
    prototypeInstance = this.createBean(beanName, mbd, args);
    //...
} else {
    //... 
}

/* createBean boils down to */
BeanUtisl.instantiateClass(constructToUse)
```


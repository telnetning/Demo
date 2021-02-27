wikipedia 双重检查：https://en.wikipedia.org/wiki/Double-checked_locking  
**volidate 的作用挺有意思，可以好好看下这里**：[The "Double-Checked Locking is Broken" Declaration](http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html)

#### spring 框架中使用单例模式
spring 中的 bean 默认情况下是单例模式。

```java
// 获取 bean， AbstractBeanFactory.doGetBean

if (mbd.isSingleton()) {
    sharedInstance = getSingleton(beanName, () -> {
        try {
            return createBean(beanName, mbd, args);
        } catch (BeansException ex) {
            destroySingleton(beanName);
            throw ex;
        }    
    });     
    beanInstance = getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
} else if (mbd.isPrototype()) {
   //... 
} else {
   //... 
}

// DefaultSingletonRegistry
// 存储 bean 的 Map
private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
    Assert.notNull(beanName, "Bean name must not be null");
    synchronized (this.singletonObjects) { // 要写 map，先上锁
        
        // 如果已经存在，那么直接返回
        Object singletonObject = this.singletonObjects.get(beanName);
        
        // 如果不存在，那么调用传入的回调方法创建
        if (singletonObject == null) {
            ...
            beforeSingletonCreation(beanName);
            boolean newSingleton = false;
            ...
            try {
                singletonObject = singletonFactory.getObject();
                newSingleton = true 
            } catch (IllegalStateException ex) {
                singleObject = this.singletonObjects.get(beanName);
                if (singletonObject == null) {
                    throw ex;
                }
            }
        }
        return singletonObject;
    }
}
```

注意下 ObjectFactory 的定义，是一个函数式接口，因此 doGetBean 调用 getSingleton 时传入的是一个 lambda 表达式。
```java
@FunctionalInterface
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}

```
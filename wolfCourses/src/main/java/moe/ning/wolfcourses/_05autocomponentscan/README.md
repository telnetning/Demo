使用 Java Config 的配置，相比传统 XML 的配置方式：  

+  不用来回切换编写 Java 文件和 XML 文件
+  Java 代码可以有编译检查
+  可以统一像管理代码的方式管理类

过程：

在主配置中标注 @ComponentScan，该配置定义了扫描路径，然后对应路径的所有的 bean 都会被扫描。  
其它的 bean 要想被扫描到，需要加上 @Component

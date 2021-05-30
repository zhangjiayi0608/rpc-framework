package github.zayn.spring;

import java.lang.annotation.Annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;


public class CustomScanner extends ClassPathBeanDefinitionScanner {

    /*
    这个 scanner 有两个比较重要的方法
    1. addExcludeFilter()添加一个排斥过滤器。假如我们添加了一个 @A, 那么所有被加了@A的类，都不会被扫描器扫描注入容器中
    2. addIncludeFilter()添加一个包含过滤器。加入我们添加了一个 @B, 那么所有加了@B的类，都会被扫描器扫描并注入到容器中
	Spring中的@Compoent就是被添加到包含过滤器中！
     */
    public CustomScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> annoType) {
        super(registry);
        super.addIncludeFilter(new AnnotationTypeFilter(annoType));
    }

    /*
    我们可以通过调用scanner.scan(String... basePackages)来对basePackages包下的类进行扫描
    这个方法会返回一个int值，用来表示扫描到的类的个数
    */
    @Override
    public int scan(String... basePackages) {
        return super.scan(basePackages);
    }
}
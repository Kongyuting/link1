package core.consumer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author:kongyuting
 * @Date:2019/3/13 19:56
 *
 * 给接口创建一个代理对象 当接口调用方法时会先来到代理对象里面
 */
public class ProxyFactoryUtil {
    //你给我一个接口 我给你一个对象
    public  static  Object  creatProxy(Class<?> clazz){
        ClassLoader classLoader =ProxyFactoryUtil.class.getClassLoader();

      return   Proxy.newProxyInstance(classLoader, new Class<?>[]{clazz}, new InvocationHandlerImpl(clazz));
    }
}

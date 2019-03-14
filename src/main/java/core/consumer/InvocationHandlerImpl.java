package core.consumer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/13 20:42
 */
public class InvocationHandlerImpl implements InvocationHandler {
    private  Class<?> clazz;
    public InvocationHandlerImpl(Class<?> clazz)
    {
        this.clazz=clazz;
    }

    /**
     *
     * @param proxy
     *   代理对象本身
     * @param method
     * 代理对象的哪个方法
     * @param args
     * 代理本次方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    //调用代理对象的任何方法都会先进此方法
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if(methodName.equals("toString")){
          return "我是代理对象本身";
        }
        Request request=new Request(this.clazz,methodName,args);
        DiscoveryServer discoveryServer=new DiscoveryServer();
        String ipPort = discoveryServer.discovery(this.clazz.getName());
        ipPort=ipPort.replaceAll("/","");
        System.out.println("我这次调用的端口是:"+ipPort);
        Object result = SendRequestandReviceResponceUtil.SendRequestandReviceResponce(ipPort, request);
        return result;
    }
}

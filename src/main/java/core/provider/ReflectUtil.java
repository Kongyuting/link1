package core.provider;

import core.consumer.Request;

import java.lang.reflect.Method;

/**
 * @Author:kongyuting
 * @Date:2019/3/13 22:04
 */
public class ReflectUtil {
    public  static  Object invoke(Request request){
        Object result=null;
        Class<?> interfaces = request.getClazz();
        String methodName = request.getMethodName();
        Object[] args = request.getArgs();

        //接口：com.sxt.test.service.ClassMateService
        //实现类：com.sxt.test.service.impl.ClassMateServiceImpl
        String name = interfaces.getName();
        name=name.replaceAll("service.","service.impl.")+"Impl";
        try {
            Class<?> clazz = Class.forName(name);

            Class<?>[] parameterTypes  = new Class<?>[args.length];
            for (int i=0;i<args.length;i++){
                parameterTypes[i]=args[i].getClass();
            }
            Method method = clazz.getMethod(methodName, parameterTypes);
            result= method.invoke(clazz.newInstance(), args);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;

    }
}

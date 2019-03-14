package core.consumer;

import java.io.Serializable;
import java.lang.reflect.Array;

/**
 * @Author:kongyuting
 * @Date:2019/3/13 19:46
 *
 * 题目的包装类
 */
public class Request implements Serializable {
    private  static  final  long serialVersionUID=1L;
    //接口的名称
    private  Class<?> clazz;
    //接口中的方法
    private  String methodName;
    //方法中的参数
    private  Object[] args;

    public Request() {
    }

    public Request(Class<?> clazz, String methodName, Object[] args) {
        this.clazz = clazz;
        this.methodName = methodName;
        this.args = args;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }


}

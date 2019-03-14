package core.provider;

/**
 * @Author:kongyuting
 * @Date:2019/3/14 17:23
 */
public class InitContext {

    public  static  void initContext(Integer port){
       //暴露自己
        RegisterService registerService=new RegisterService();
        registerService.register("service.ClassMateService","127.0.0.1:"+port);
        //监听
      while (true){
          ListenerServer.startListener(port);

      }
    }
}

package core.provider;

import core.util.ZkUtil;

/**
 * @Author:kongyuting
 * @Date:2019/3/14 16:53
 * 服务提供者的注册
 */
public class RegisterService {

public  void register(String serverName , String serverPath){
    //服务一启动就注册自己
    ZkUtil.addNode(serverName,serverPath);
}
}

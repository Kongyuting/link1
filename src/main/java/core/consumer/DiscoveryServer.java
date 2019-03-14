package core.consumer;

import core.util.ZkUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author:kongyuting
 * @Date:2019/3/14 18:45
 */
public class DiscoveryServer {

    //将服务地址存在缓存中
    private Map<String,List<String>>  cache=new HashMap<>();
    private static Random rdm=new Random();
    //通过服务名称发现服务
    public  String discovery(String serverName){

        if(!cache.containsKey(serverName)){
            //缓存中没有就加载到缓存中
            List<String> list = ZkUtil.getNodes(serverName);
            cache.put(serverName,list);
            //更新
            ZkUtil.updateServerPath(serverName,cache);
        }

        //得到所有的子节点 还要做负载均衡
        //之前有就从缓存中取
        List<String> nodes = ZkUtil.getNodes(serverName);
        return  nodes.get(rdm.nextInt(nodes.size()));
    }
}

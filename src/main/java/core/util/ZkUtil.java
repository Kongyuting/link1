package core.util;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.Map;

/**
 * @Author:kongyuting
 * @Date:2019/3/14 16:56
 * ZKutil工具类
 */
public class ZkUtil {

    private  static  final  String zkServer="192.168.221.133:2181";
    private  static ZkClient zkClient=null;
    static {
        zkClient=new ZkClient(zkServer);
    }

/**
 * 注册自己
 */

public static void addNode(String serverName,String serverPath){

    //先判断节点是否存在
    if(!zkClient.exists("/"+serverName)){
        //创造持久节点"/serverName"
        zkClient.createPersistent("/"+serverName);
    }
    //创造子节点 临时的
    zkClient.createEphemeral("/"+serverName+"/"+serverPath);


}
//通过服务名获得服务
    public  static List<String>  getNodes(String serverName){
//通过节点得到子节点（服务的发现，通过服务名）
        List<String> children = zkClient.getChildren("/" + serverName);
        return  children;
    }

    //服务的更新
    public static void updateServerPath(String serverName, Map<String, List<String>> cache) {
    zkClient.subscribeChildChanges("/" + serverName, new IZkChildListener() {
        @Override
        public void handleChildChange(String s, List<String> currentChilds) throws Exception {
            cache.put(serverName,currentChilds);
            System.out.println("有服务上线或者下线了"+serverName+"..."+currentChilds);
        }
    });

    }



}

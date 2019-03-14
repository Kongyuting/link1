package core.consumer;

import java.io.*;
import java.net.Socket;

/**
 * @Author:kongyuting
 * @Date:2019/3/13 20:57
 * 发送请求 并得到答案的工具类
 */
public class SendRequestandReviceResponceUtil {

    public static  Object SendRequestandReviceResponce(String serverPth,Request request){

        Socket socket=null;
        OutputStream outputStream=null;
        ObjectOutputStream objectOutputStream=null;
        InputStream inputStream=null;
        ObjectInputStream objectInputStream=null;
        Object result=null;

      String ip= serverPth.split(":")[0];
        Integer port= Integer.valueOf(serverPth.split(":")[1]);
        try {
            socket=new Socket(ip,port);
            //发送请求
              outputStream=socket.getOutputStream();
            objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(request);
            //接受答案
            inputStream=socket.getInputStream();
          objectInputStream=new ObjectInputStream(inputStream);
           result = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

           CloseUtil.close(objectInputStream,inputStream,objectOutputStream,outputStream,socket);
        }
        return  result;
    }


}

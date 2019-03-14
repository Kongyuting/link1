package core.provider;

import core.consumer.CloseUtil;
import core.consumer.Request;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author:kongyuting
 * @Date:2019/3/13 21:31
 *
 * 监听服务
 */
public class ListenerServer {

    public  static  void  startListener(Integer port){
        ObjectOutputStream objectOutputStream=null;
        OutputStream outputStream=null;
        ObjectInputStream objectInputStream=null;
        InputStream inputStream=null;
        Socket socket=null;
        ServerSocket serverSocket=null;

        try {
             serverSocket=new ServerSocket(port);
             socket=serverSocket.accept();//若该方法没有连接自动阻塞
             inputStream=socket.getInputStream();
             objectInputStream=new ObjectInputStream(inputStream);
            Request request = (Request) objectInputStream.readObject();

            //此时反射调用实现我们的类
             outputStream=socket.getOutputStream();
             objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(ReflectUtil.invoke(request));
            objectOutputStream.flush();

        }
       catch (Exception e){

       }
        finally {
            CloseUtil.close(objectOutputStream,outputStream,objectInputStream,inputStream,socket,serverSocket);
        }
    }



}

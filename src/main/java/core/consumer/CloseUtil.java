package core.consumer;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/3/13 21:12
 */
public class CloseUtil {
    public  static  void  close(Closeable... res){
        for (Closeable close:res)
            if (close != null) {
                try {
                    close.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    close=null;
                }
            }

    }

}

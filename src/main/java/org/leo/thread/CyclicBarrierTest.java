package org.leo.thread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
* @ClassName: CyclicBarrierTest 
* @Description: �߳�ͬ��������,CyclicBarrier����Ҫ�����ǵȴ������˶������,������һվ����,�ճ�Ӧ���н����漰
*/
public class CyclicBarrierTest {
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for(int i=0;i<3;i++){
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("�߳�"+Thread.currentThread().getName()+"�������Ｏ�ϵ�1"+",��ǰ����"+(cyclicBarrier.getNumberWaiting()==2?(cyclicBarrier.getNumberWaiting()+1)+"��,�����Ѿ��������,��������һվ����":(cyclicBarrier.getNumberWaiting()+1)+"��"));
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } 
                    
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("�߳�"+Thread.currentThread().getName()+"�������Ｏ�ϵ�2"+",��ǰ����"+(cyclicBarrier.getNumberWaiting()==2?(cyclicBarrier.getNumberWaiting()+1)+"��,�����Ѿ��������,��������һվ����":(cyclicBarrier.getNumberWaiting()+1)+"��"));
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } 
                    
                }
            };
            newCachedThreadPool.execute(runnable);
        }
        newCachedThreadPool.shutdown();
    }
}

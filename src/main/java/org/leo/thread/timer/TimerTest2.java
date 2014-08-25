package org.leo.thread.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 *  Timer����һ���������ڣ����TimerTask�׳�δ�����쳣��Timer��������޷�Ԥ�ϵ���Ϊ��
 *  Timer�̲߳��������쳣������ TimerTask�׳���δ�����쳣����ֹtimer�̡߳�
 *  ��������£�TimerҲ���������»ָ��̵߳�ִ����;���������Ϊ����Timer����ȡ���ˡ�
 *  ��ʱ���Ѿ������ŵ���δִ�е�TimerTask��Զ������ִ���ˣ��µ�����Ҳ���ܱ������ˡ�
 *  
 *  ���иó���Timer���׳�һ��RumtimeException��java.lang.IllegalStateException:Timer already cancelled.

 *  ���Ե������ǻ������У�Timer�����������⴫Ⱦ����һ����ù�ĵ����ߣ�
 * ���������ԭ����ͼ�ύһ��TimerTask�ģ������ϣ�������һֱ������ȥ��Ȼ��ʵ������������ʾ5���Ӻ����ֹ�ˣ���������һ���쳣���쳣����Ϣ��"Timer already cancelled"��
 *  ScheduledThreadPoolExector���Ƶش���������쳣����������˵��java5.0����ߵ�JDK�У�����û��������ʹ�� Timer�ˡ�
 *  
 * @author leo
 *
 */
public class TimerTest2 {
	private Timer timer = new Timer();

	// ������ʱ��
	public void lanuchTimer() {
		timer.schedule(new TimerTask() {
			public void run() {
				throw new RuntimeException();
			}
		}, 1000 * 3, 500);
	}

	// ���ʱ�����һ������
	public void addOneTask() {
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("hello world");
			}
		}, 1000 * 1, 1000 * 5);
	}

	public static void main(String[] args) throws Exception {
		TimerTest2 test = new TimerTest2();
		test.lanuchTimer();
		Thread.sleep(1000 * 5);// 5����֮�����һ��������
		test.addOneTask();
	}
}

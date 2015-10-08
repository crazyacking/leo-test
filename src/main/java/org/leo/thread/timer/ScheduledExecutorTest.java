package org.leo.thread.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {
	// �̳߳��ܰ�ʱ��ƻ���ִ�����������û��趨�ƻ�ִ�������ʱ�䣬int���͵Ĳ������趨
	// �̳߳����̵߳���С��Ŀ��������϶�ʱ���̳߳ؿ��ܻ��Զ���������Ĺ����߳���ִ������
	public ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(5);

	// ������ʱ��
	public void lanuchTimer() {
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("RuntimeException.........");
				throw new RuntimeException();
			}
		};
		scheduExec.scheduleWithFixedDelay(task, 1000 * 5, 1000 * 10, TimeUnit.MILLISECONDS);
	}

	// ���������
	public void addOneTask() {
		Runnable task = new Runnable() {
			public void run() {
				System.out.println("welcome to china");
			}
		};
		//scheduExec.scheduleWithFixedDelay(task, 1000 * 1, 1000, TimeUnit.MILLISECONDS);
		scheduExec.schedule(task, 5, TimeUnit.SECONDS);
	}
	
	// ���������
	public void addLoopTask() {
		Runnable task = new Runnable() {
			int i = 0;
			public void run() {
				System.out.println("loop : " + i++);
				throw new RuntimeException();
			}
		};
		//scheduExec.scheduleWithFixedDelay(task, 1000 * 1, 1000, TimeUnit.MILLISECONDS);
		scheduExec.scheduleAtFixedRate(task, 3, 3, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("-----------start------");
		ScheduledExecutorTest test = new ScheduledExecutorTest();
		test.lanuchTimer();
		System.out.println("-----------1------");
		Thread.sleep(1000 * 5);// 5����֮�����������
		test.addOneTask();
		test.addLoopTask();
		System.out.println("-----------end------");
	}
}

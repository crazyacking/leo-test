//package org.leo.thread.pool;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * �ڲ���Ϣ�����Ͷ���,��������. ע�⣺����Ƕ��߳��ڴ�����ô������������ȷ�Ⱥ���������Ϣ����ʹ�á�
// * */
//public class InnerMsgTransfer {
//	private static InnerMsgTransfer instance;
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//
//	/**
//	 * �����߻���
//	 * */
//	private final ConcurrentHashMap<InnerMsgTypeEnum, Set<InnerMsgListener>> listenerMap = new ConcurrentHashMap<InnerMsgTypeEnum, Set<InnerMsgListener>>();
//	
//	/**
//	 * �̳߳� ����1���������̳߳ش�С.���ϣ���ǵ��̣߳����Ϊ1 ����2���̳߳ص�����С ����3�������߳̽����ĳ�ʱʱ�� ����4��
//	 * �����߳̽����ĳ�ʱʱ��ĵ�λ ����5���������Ķ���
//	 * */
//	private final ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1,
//			TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
//
//	private InnerMsgTransfer() {
//	}
//
//	public static InnerMsgTransfer getInstance() {
//		if (instance == null) {
//			synchronized (InnerMsgTransfer.class) {
//				if (instance == null) {
//					instance = new InnerMsgTransfer();
//				}
//			}
//		}
//		return instance;
//	}
//
//	/**
//	 * ע���ڲ���Ϣ�������������ߣ�<br />
//	 * 
//	 * @param listener
//	 *            ��Ϣ��������<br />
//	 * @param msgType
//	 *            ��Ϣ����
//	 * */
//	public void registerInnerMsgListener(InnerMsgListener listener, InnerMsgTypeEnum msgType) {
//		if (msgType == null) {
//			return;
//		}
//		// ���еļ�����
//		Set<InnerMsgListener> listeners = listenerMap.get(msgType);
//		if (listeners == null) {
//			synchronized (this) {
//				if (listeners == null) {
//					listeners = new HashSet<InnerMsgListener>();
//					listenerMap.put(msgType, listeners);
//				}
//			}
//		}
//		listeners.add(listener);
//	}
//
//	/**
//	 * �����ڲ���Ϣ
//	 * 
//	 * @param msgType
//	 *            ��Ϣ����
//	 * @param msg
//	 *            ���͵���Ϣ
//	 * */
//	public void transferInnerMsg(InnerMsgTypeEnum msgType, Object msg) {
//		if (msgType == null) {
//			return;
//		}
//		executor.execute(new InnerMsgTask(msgType, msg));
//	}
//
//	/**
//	 * �ڲ��࣬���ڻ����ڲ���Ϣ
//	 * */
//	private class InnerMsgTask implements Runnable {
//		public InnerMsgTypeEnum msgType;
//		public Object msg;
//
//		public InnerMsgTask(InnerMsgTypeEnum msgType, Object msg) {
//			this.msgType = msgType;
//			this.msg = msg;
//		}
//
//		@Override
//		public void run() {
//			// ���еļ�����
//			Set<InnerMsgListener> listeners = listenerMap.get(msgType);
//			if (listeners == null) {
//				return;
//			}
//			for (InnerMsgListener listener : listeners) {
//				listener.onInnerMsg(msgType, msg);
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		InnerMsgTransfer transfer = InnerMsgTransfer.getInstance();
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "1");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "2");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "3");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "4");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "5");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "6");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "7");
//	}
//}

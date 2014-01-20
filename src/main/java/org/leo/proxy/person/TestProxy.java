package org.leo.proxy.person;

public class TestProxy {

	/**
	 * 
	 * @param args
	 *
	 * Modify Time Dec 24, 2013 4:31:50 PM
	 */
	public static void main(String[] args) {
		Person zhangsan = new PersonImpl();// ������Ķ���

        zhangsan.say();// ����ô���Ŀ��ֱ��ִ��Ŀ�귽�����Ͳ�����Ŀ�귽��ǰ�����
        System.out.println();

        PersonProxy proxy = new PersonProxy(zhangsan);// ��������Ķ��󴫵�һ��������
        proxy.say();// �ô�����ȥִ��Ŀ�귽�������ʱ����������Ŀ�귽��ִ��ǰ���Ҹ���

        PersonProxyJdk proxyJdk = new PersonProxyJdk();// ����һ���������
        Person zhangsanJdk = (Person) proxyJdk.createProxyInstance(zhangsan);// ����������󴫵ݸ�������󣬲��ҷ��ر�����ӿ�
        zhangsanJdk.say();// ���˱��������Ľӿڣ����ܶ�̬��ȥִ�д��������Ҫִ�еĲ���

        PersonProxyCglib proxyCglib = new PersonProxyCglib();// ����Cglib�������
        Person zhansanCglib = (Person) proxyCglib.createProxyInstance(zhangsan);// ����������󴫵ݸ�������󣬲��ҷ��ر�����ӿ�
        zhansanCglib.say();// ���˱��������Ľӿڣ����ܶ�̬��ȥִ�д��������Ҫִ�еĲ���
        
        // ʹ��Cglib��ʱ��Ŀ�������Բ�ʵ���κνӿڣ�����ʹ��JDK�����ʱ��Ͳ�����
        PersonNoImpl lisi = new PersonNoImpl();
        PersonNoImpl lisiCglib = (PersonNoImpl) proxyCglib.createProxyInstance(lisi);
        lisiCglib.say();

	}

}
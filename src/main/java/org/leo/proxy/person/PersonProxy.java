package org.leo.proxy.person;

/**
 * ��̬�����ڳ�������ǰ���������.class�ļ����Ѿ�������
 * ���ַ�ʽ�����ȱ�����ÿ�����Ƕ���Ҫ������ͬ�Ĵ����������ԺͿɸ����Զ��ܲ����������Ҫʹ�õ���̬��������
 * 
 * Modify Time Dec 24, 2013 4:29:37 PM
 */
public class PersonProxy implements Person {
    private Person person;// ���������

    public PersonProxy(Person p) {
        this.person = p;
    }

    @Override
    public void say() {
        System.out.print("����-");
        person.say();// ��Ŀ�귽��ǰ��ֱ���Ӳ���
        System.out.println("-��");
    }

}

package org.leo.java.meiju;

import org.junit.Test;

public class WeekDayTest {

	//@Test
	public void test1() {
		WeekDay day = WeekDay.MON;
		System.out.println(day); // �Զ�������toString()����
		System.out.println(day.name());// ���ö�ٶ��������
		System.out.println(day.ordinal());// �����ö���е�˳�����0
		System.out.println(WeekDay.valueOf("SAT").toString()); // ͨ������һ�������ַ���������һ������̬����
		// ��������ҳ�ύ���ݵ�ʱ��,�ύ�����ַ������ǿ����ڱ���
		System.out.println(WeekDay.values().length);// ��̬�������һ��ö�ٵĳ���
	}
	
	@Test
	public void test2() {
		WeekDay2 day = WeekDay2.MON;
		System.out.println(day); // �Զ�������toString()����
		System.out.println(day.name());// ���ö�ٶ��������
		System.out.println(day.ordinal());// �����ö���е�˳�����0
		System.out.println(WeekDay.valueOf("SAT").toString()); // ͨ������һ�������ַ���������һ������̬����
		// ��������ҳ�ύ���ݵ�ʱ��,�ύ�����ַ������ǿ����ڱ���
		System.out.println(WeekDay.values().length);// ��̬�������һ��ö�ٵĳ���
	}
}

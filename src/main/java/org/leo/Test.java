package org.leo;

import java.text.MessageFormat;

public class Test {

	public static void main(String[] args) {
		//��MessageFormat.format��������װjason�����ַ�����{code:"w1",des:"w2"}����ֹ�ֱ���������ź��Ҵ����š������ǽ������ŰѴ����Ű������������£�
        String responseTemplate = "'{'code:\"{0}\",des:\"{1}\"'}'";
        System.out.println(MessageFormat.format(responseTemplate, "w1","w2"));

        //�����ʽ���ַ����а��������ţ�����������2�������Ž���ת�壺

        responseTemplate = "'{'code:''{0}'',des:''{1}'''}'";
        System.out.println(MessageFormat.format(responseTemplate, new Object[]{"w1","w2"}));
        System.out.println(MessageFormat.format(responseTemplate, new Object[]{"w1"}));
        System.out.println(MessageFormat.format(responseTemplate, new Object[]{"w1","w3","wxx"}));
        
        


	}

}

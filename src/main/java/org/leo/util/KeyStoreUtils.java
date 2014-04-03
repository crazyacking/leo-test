package org.leo.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

public class KeyStoreUtils {

	/**
	 * ��ȡKeyStore
	 * 
	 * @param keystorePath
	 * @param password
	 * @return
	 * @throws Exception
	 *
	 * @author leo.li
	 * Modify Time 2014��4��3�� ����2:39:30
	 */
	public static KeyStore getKeyStore(String keystorePath, String password) throws Exception{
		//�����Կ���ļ�������
		FileInputStream is = null;
		try{
			is = new FileInputStream(keystorePath);
			//ʵ������Կ��
			KeyStore ks = KeyStore.getInstance("JKS");
			//������Կ��
			ks.load(is,password.toCharArray());
			return ks;
		} catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally{
			//�ر���
			try {
				if(is != null) is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ����KeyStore
	 * 
	 * @param ks
	 * @param password
	 * @param keystorePath
	 *
	 * @author leo.li
	 * Modify Time 2014��4��3�� ����2:46:48
	 * @throws Exception 
	 */
	public static void store(KeyStore ks, String keystorePath, String storepass) throws Exception{
		FileOutputStream out = null;
		try {                      
            out = new FileOutputStream(keystorePath);
            ks.store(out, storepass.toCharArray());            
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
                out = null;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * ��֤�鱣���jsk
	 * 
	 * @param chain
	 * @param keystorePath
	 * @param alias
	 * @param storepass
	 * @throws Exception
	 *
	 * @author leo.li
	 * Modify Time 2014��4��3�� ����3:34:23
	 */
	public static void store(X509Certificate[] chain, String keystorePath, String alias, String storepass) throws Exception{
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(null, null);
		int len = chain.length;
		for(int i = 0 ; i < len ; i++){
            String aliasName = alias + (i > 0 ? i + "" : "");
            ks.setCertificateEntry(aliasName, chain[i]);
		}
		store(ks, keystorePath, storepass);
	}
}

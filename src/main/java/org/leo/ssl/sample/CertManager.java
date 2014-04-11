package org.leo.ssl.sample;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERBoolean;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509CertificateStructure;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.util.encoders.Hex;
import org.leo.util.CertificateUtils;

public class CertManager {
	String eoid[][] = {
			{ new String("Subject Key Identifier"), new String("2.5.29.14") },
			{ new String("Key Usage"), new String("2.5.29.15") },
			{ new String("Private Key Usage Period"), new String("2.5.29.16") },
			{ new String("Subject Alternative Name"), new String("2.5.29.17") },
			{ new String("Issuer Alternative Name"), new String("2.5.29.18") },
			{ new String("Basic Constraints"), new String("2.5.29.19") },
			{ new String("CRL Number"), new String("2.5.29.20") },
			{ new String("Reason code"), new String("2.5.29.21") },
			{ new String("Hold Instruction Code"), new String("2.5.29.23") },
			{ new String("Invalidity Date"), new String("2.5.29.24") },
			{ new String("Delta CRL indicator"), new String("2.5.29.27") },
			{ new String("Issuing Distribution Point"), new String("2.5.29.28") },
			{ new String("Certificate Issuer"), new String("2.5.29.29") },
			{ new String("Name Constraints"), new String("2.5.29.30") },
			{ new String("CRL Distribution Points"), new String("2.5.29.31") },
			{ new String("Certificate Policies"), new String("2.5.29.32") },
			{ new String("Policy Mappings"), new String("2.5.29.33") },
			{ new String("Authority Key Identifier"), new String("2.5.29.35") },
			{ new String("Policy Constraints"), new String("2.5.29.36") },
			{ new String("Extended Key Usage"), new String("2.5.29.37") } };
	byte buf[];

	public CertManager() {
		int fLength = 0;
		try {
			FileInputStream fis = new FileInputStream("D:/temp/certest/www.google.com.hk.00.base64.cer");
			fLength = fis.available();
			buf = new byte[fLength];
			fis.read(buf, 0, fLength);
		} catch (Exception ex) {
			System.out.println("��֤���ļ�����!");
			return;
		}
	}

	public byte[] getExtensionBytes(String oid, X509Extensions exts) {
		if (exts != null) {
			X509Extension ext = exts.getExtension(new DERObjectIdentifier(oid));
			if (ext != null) {
				return ext.getValue().getOctets();
			}
		}
		return null;
	}

	public void getCert() {

		ByteArrayInputStream bIn;
		ASN1InputStream dIn;
		String dump = "";

		try {
			//bIn = new ByteArrayInputStream(buf);
			//dIn = new ASN1InputStream(bIn);

			//ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
			// dump = DERDump.dumpAsString(seq);
			// ����������
			// System.out.println(dump);
			// ֤��Ļ�����Ϣ
			System.out.println("<<=============֤��Ļ�����Ϣ===============>>");
			//X509CertificateStructure cert = new X509CertificateStructure(seq);
			X509Certificate cer = CertificateUtils.getCertificate("D:/temp/certest/www.google.com.hk.00.base64.cer");
			X509CertificateStructure cert = X509CertificateStructure.getInstance(ASN1Primitive.fromByteArray(cer.getEncoded()));

			
			System.out.println("֤��汾:\t" + cert.getVersion());
			System.out.println("���к�:\t\t"
					+ cert.getSerialNumber().getValue().toString(16));
			System.out.println("�㷨��ʶ:\t"
					+ cert.getSignatureAlgorithm().getObjectId().getId());
			System.out.println("ǩ����:\t\t" + cert.getIssuer());
			System.out.println("��ʼʱ��:\t" + cert.getStartDate().getTime());
			System.out.println("����ʱ��:\t" + cert.getEndDate().getTime());
			System.out.println("������:\t\t" + cert.getSubject());
			System.out.print("ǩ��ֵ:\t");
			DERBitString signature = cert.getSignature();
			String strSign = new String(Hex.encode(signature.getBytes()));
			System.out.println(strSign);
			System.out.println("���幫Կ:\t");
			SubjectPublicKeyInfo pukinfo = cert.getSubjectPublicKeyInfo();
			System.out.println("\t��ʶ��:\t"
					+ pukinfo.getAlgorithmId().getObjectId().getId());
			byte[] byPuk = pukinfo.getPublicKeyData().getBytes();
			String strPuk = new String(Hex.encode(byPuk));
			System.out.println("\t��Կֵ:\t" + strPuk);
			// ֤�����չ��Ϣ
			System.out.println("<<===========֤�����չ��Ϣ==============>>");
			X509Extensions ext = cert.getTBSCertificate().getExtensions();
			// 15 --key usage 19 ---basic constrains
			// 31-- crl point 32 ---certificate policy
			getKeyUsage(ext);
			getBasicConstrains(ext);
			getCRLPoint(ext);
			getCertPolicy(ext);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	// ȡ��Կ��ʹ��

	public void getKeyUsage(X509Extensions ext) {
		DERObjectIdentifier derOid = new DERObjectIdentifier("2.5.29.15");
		X509Extension item = null;
		boolean isCritical;
		ASN1OctetString value;
		try {
			item = ext.getExtension(derOid);
			isCritical = item.isCritical();
			value = item.getValue();
		} catch (Exception ex) {
			return;
		}
		System.out.println(new String(Hex.encode(value.getOctets())));
	}

	// ȡ��������
	public void getBasicConstrains(X509Extensions ext) {
		byte[] bytes = getExtensionBytes("2.5.29.19", ext);
		
		if (bytes != null) {
			try {
				ASN1InputStream dIn = new ASN1InputStream(
						new ByteArrayInputStream(bytes));
				ASN1Sequence seq = (ASN1Sequence) dIn.readObject();

				if (seq.size() == 2) {
					if (((DERBoolean) seq.getObjectAt(0)).isTrue()) {
						int pathlen = ((DERInteger) seq.getObjectAt(1))
								.getValue().intValue();
						System.out.println("��CA֤��\t" + "max path len="
								+ pathlen);
					} else {
						System.out.println("����ca֤��!");
					}
				} else if (seq.size() == 1) {
					if (seq.getObjectAt(0) instanceof DERBoolean) {
						if (((DERBoolean) seq.getObjectAt(0)).isTrue()) {
							System.out.println(Integer.MAX_VALUE);
						}
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(
						"error processing key usage extension");
			}
		}
	}

	// ȡcrl�ֲ���
	public void getCRLPoint(X509Extensions ext) {
		byte[] byContent = getExtensionBytes("2.5.29.31", ext);

		if (byContent != null) {
			try {
				ASN1InputStream dIn = new ASN1InputStream(
						new ByteArrayInputStream(byContent));
				ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
				int dpCount = seq.size();
				for (int i = 0; i < dpCount; i++) {
					// ��һ���ֲ���(DistributionPoint)
					ASN1Sequence point1 = (ASN1Sequence) seq.getObjectAt(i);
					ASN1Object tobj = (DERTaggedObject) point1.getObjectAt(0);
					System.out.println("CRL�ֲ���" + (i + 1) + ":");
					while (tobj instanceof DERTaggedObject
							&& !((DERTaggedObject) tobj).isEmpty()) {
						System.out.println("\ttagNo:"
								+ ((DERTaggedObject) tobj).getTagNo());
						if (tobj instanceof DERTaggedObject)
							tobj = ((DERTaggedObject) tobj).getObject();
					}
					ASN1Primitive os = tobj.toASN1Object();
					String str = new String(os.getEncoded());
					System.out.println("\t" + str);
				}
			} catch (Exception e) {
				System.out.println("crl�ֲ��㴦�������!");
			}
		}
	}

	// ȡ֤�����
	public void getCertPolicy(X509Extensions ext) throws UnsupportedEncodingException {
		byte[] byContent = getExtensionBytes("2.5.29.32", ext);
		System.out.println(new String(Hex.encode(byContent), "utf-8"));
		System.out.println(org.apache.commons.codec.binary.Hex.encodeHexString(byContent));
		if (byContent != null) {
			try {
				ASN1InputStream dIn = new ASN1InputStream(
						new ByteArrayInputStream(byContent));
				ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
				// String dump = DERDump.dumpAsString(seq);
				// ����������
				// System.out.println("֤�����:"+dump);
				for (int i = 0; i < seq.size(); i++) {
					getPolicyInfo((ASN1Sequence) seq.getObjectAt(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getPolicyInfo(ASN1Sequence seq) {
		System.out.println(seq.size());
		DERObjectIdentifier objID = (DERObjectIdentifier) seq.getObjectAt(0);
		System.out.println("֤����Ա�ʶ:" + objID.getId());
		if (seq.size() == 2) {
			objID = (DERObjectIdentifier) seq.getObjectAt(0);
			System.out.println("֤����Ա�ʶ:" + objID.getId());
			ASN1Sequence seqQualifier = (ASN1Sequence) seq.getObjectAt(1);
			for (int i = 0; i < seqQualifier.size(); i++)
				getPolicyQualifierInfo((ASN1Sequence) seqQualifier
						.getObjectAt(i));
		} else
			System.out.println("������������ʱ����!");
	}

	private void getPolicyQualifierInfo(ASN1Sequence seq) {
		if (seq.size() == 2) {
			DERObjectIdentifier objID = (DERObjectIdentifier) seq
					.getObjectAt(0);
			System.out.println("����������ʶ:" + objID.getId());
			DERIA5String ia5 = (DERIA5String) seq.getObjectAt(1);
			System.out.println("��������:" + ia5.getString());
		}
	}

	public static void main(String[] args) {
		CertManager cm = new CertManager();
		cm.getCert();
	}
}

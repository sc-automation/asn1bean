/**
 * This class file was automatically generated by jASN1 v1.8.2-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.pkix1implicit88;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;

import org.openmuc.jasn1.compiler.pkix1explicit88.Attribute;
import org.openmuc.jasn1.compiler.pkix1explicit88.CertificateSerialNumber;
import org.openmuc.jasn1.compiler.pkix1explicit88.DirectoryString;
import org.openmuc.jasn1.compiler.pkix1explicit88.Name;
import org.openmuc.jasn1.compiler.pkix1explicit88.ORAddress;
import org.openmuc.jasn1.compiler.pkix1explicit88.RelativeDistinguishedName;

public class DistributionPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	public DistributionPointName distributionPoint = null;
	public ReasonFlags reasons = null;
	public GeneralNames cRLIssuer = null;
	
	public DistributionPoint() {
	}

	public DistributionPoint(byte[] code) {
		this.code = code;
	}

	public DistributionPoint(DistributionPointName distributionPoint, ReasonFlags reasons, GeneralNames cRLIssuer) {
		this.distributionPoint = distributionPoint;
		this.reasons = reasons;
		this.cRLIssuer = cRLIssuer;
	}

	public int encode(BerByteArrayOutputStream os) throws IOException {
		return encode(os, true);
	}

	public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
			if (withTag) {
				return tag.encode(os) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		int sublength;

		if (cRLIssuer != null) {
			codeLength += cRLIssuer.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
			os.write(0xA2);
			codeLength += 1;
		}
		
		if (reasons != null) {
			codeLength += reasons.encode(os, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 1
			os.write(0x81);
			codeLength += 1;
		}
		
		if (distributionPoint != null) {
			sublength = distributionPoint.encode(os);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(os, sublength);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
			os.write(0xA0);
			codeLength += 1;
		}
		
		codeLength += BerLength.encodeLength(os, codeLength);

		if (withTag) {
			codeLength += tag.encode(os);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		if (totalLength == -1) {
			subCodeLength += berTag.decode(is);

			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
				subCodeLength += length.decode(is);
				distributionPoint = new DistributionPointName();
				int choiceDecodeLength = distributionPoint.decode(is, null);
				if (choiceDecodeLength != 0) {
					subCodeLength += choiceDecodeLength;
					subCodeLength += berTag.decode(is);
				}
				else {
					distributionPoint = null;
				}

			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
				reasons = new ReasonFlags();
				subCodeLength += reasons.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
				cRLIssuer = new GeneralNames();
				subCodeLength += cRLIssuer.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			int nextByte = is.read();
			if (berTag.tagNumber != 0 || berTag.tagClass != 0 || berTag.primitive != 0
			|| nextByte != 0) {
				if (nextByte == -1) {
					throw new EOFException("Unexpected end of input stream.");
				}
				throw new IOException("Decoded sequence has wrong end of contents octets");
			}
			codeLength += subCodeLength + 1;
			return codeLength;
		}

		codeLength += totalLength;

		if (totalLength == 0) {
			return codeLength;
		}
		subCodeLength += berTag.decode(is);
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
			subCodeLength += length.decode(is);
			distributionPoint = new DistributionPointName();
			subCodeLength += distributionPoint.decode(is, null);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
			reasons = new ReasonFlags();
			subCodeLength += reasons.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
			cRLIssuer = new GeneralNames();
			subCodeLength += cRLIssuer.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		boolean firstSelectedElement = true;
		if (distributionPoint != null) {
			sb.append("\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("distributionPoint: ");
			distributionPoint.appendAsString(sb, indentLevel + 1);
			firstSelectedElement = false;
		}
		
		if (reasons != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("reasons: ").append(reasons);
			firstSelectedElement = false;
		}
		
		if (cRLIssuer != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("cRLIssuer: ");
			cRLIssuer.appendAsString(sb, indentLevel + 1);
			firstSelectedElement = false;
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}


/**
 * This class file was automatically generated by jASN1 v1.9.1-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.rspdefinitions;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;

import org.openmuc.jasn1.compiler.pkix1explicit88.Certificate;
import org.openmuc.jasn1.compiler.pkix1explicit88.CertificateList;
import org.openmuc.jasn1.compiler.pkix1explicit88.Time;
import org.openmuc.jasn1.compiler.pkix1implicit88.SubjectKeyIdentifier;

public class CancelSessionResponseEs9 implements Serializable {

	private static final long serialVersionUID = 1L;

	public byte[] code = null;
	public static final BerTag tag = new BerTag(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 65);

	public CancelSessionOk cancelSessionOk = null;
	public BerInteger cancelSessionError = null;
	
	public CancelSessionResponseEs9() {
	}

	public CancelSessionResponseEs9(byte[] code) {
		this.code = code;
	}

	public CancelSessionResponseEs9(CancelSessionOk cancelSessionOk, BerInteger cancelSessionError) {
		this.cancelSessionOk = cancelSessionOk;
		this.cancelSessionError = cancelSessionError;
	}

	public int encode(OutputStream reverseOS) throws IOException {
		return encode(reverseOS, true);
	}

	public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				reverseOS.write(code[i]);
			}
			if (withTag) {
				return tag.encode(reverseOS) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		if (cancelSessionError != null) {
			codeLength += cancelSessionError.encode(reverseOS, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 1
			reverseOS.write(0x81);
			codeLength += 1;
			codeLength += BerLength.encodeLength(reverseOS, codeLength);
			if (withTag) {
				codeLength += tag.encode(reverseOS);
			}
			return codeLength;
		}
		
		if (cancelSessionOk != null) {
			codeLength += cancelSessionOk.encode(reverseOS, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
			reverseOS.write(0xA0);
			codeLength += 1;
			codeLength += BerLength.encodeLength(reverseOS, codeLength);
			if (withTag) {
				codeLength += tag.encode(reverseOS);
			}
			return codeLength;
		}
		
		throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		BerLength length = new BerLength();
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		codeLength += length.decode(is);
		codeLength += berTag.decode(is);

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
			cancelSessionOk = new CancelSessionOk();
			codeLength += cancelSessionOk.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
			cancelSessionError = new BerInteger();
			codeLength += cancelSessionError.decode(is, false);
			return codeLength;
		}

		throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(reverseOS, false);
		code = reverseOS.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		if (cancelSessionOk != null) {
			sb.append("cancelSessionOk: ");
			cancelSessionOk.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (cancelSessionError != null) {
			sb.append("cancelSessionError: ").append(cancelSessionError);
			return;
		}

		sb.append("<none>");
	}

}


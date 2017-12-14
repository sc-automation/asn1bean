/**
 * This class file was automatically generated by jASN1 v1.9.1-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.pkix1explicit88;

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


public class ExtendedNetworkAddress implements Serializable {

	private static final long serialVersionUID = 1L;

	public byte[] code = null;
	public static class E1634Address implements Serializable {

		private static final long serialVersionUID = 1L;

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

		public byte[] code = null;
		public BerNumericString number = null;
		public BerNumericString subAddress = null;
		
		public E1634Address() {
		}

		public E1634Address(byte[] code) {
			this.code = code;
		}

		public E1634Address(BerNumericString number, BerNumericString subAddress) {
			this.number = number;
			this.subAddress = subAddress;
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
			if (subAddress != null) {
				codeLength += subAddress.encode(reverseOS, false);
				// write tag: CONTEXT_CLASS, PRIMITIVE, 1
				reverseOS.write(0x81);
				codeLength += 1;
			}
			
			codeLength += number.encode(reverseOS, false);
			// write tag: CONTEXT_CLASS, PRIMITIVE, 0
			reverseOS.write(0x80);
			codeLength += 1;
			
			codeLength += BerLength.encodeLength(reverseOS, codeLength);

			if (withTag) {
				codeLength += tag.encode(reverseOS);
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
				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
					number = new BerNumericString();
					subCodeLength += number.decode(is, false);
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
				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
					subAddress = new BerNumericString();
					subCodeLength += subAddress.decode(is, false);
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

			subCodeLength += berTag.decode(is);
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
				number = new BerNumericString();
				subCodeLength += number.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berTag.decode(is);
			}
			else {
				throw new IOException("Tag does not match the mandatory sequence element tag.");
			}
			
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
				subAddress = new BerNumericString();
				subCodeLength += subAddress.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
			}
			throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

			
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

			sb.append("{");
			sb.append("\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (number != null) {
				sb.append("number: ").append(number);
			}
			else {
				sb.append("number: <empty-required-field>");
			}
			
			if (subAddress != null) {
				sb.append(",\n");
				for (int i = 0; i < indentLevel + 1; i++) {
					sb.append("\t");
				}
				sb.append("subAddress: ").append(subAddress);
			}
			
			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	public E1634Address e1634Address = null;
	public PresentationAddress psapAddress = null;
	
	public ExtendedNetworkAddress() {
	}

	public ExtendedNetworkAddress(byte[] code) {
		this.code = code;
	}

	public ExtendedNetworkAddress(E1634Address e1634Address, PresentationAddress psapAddress) {
		this.e1634Address = e1634Address;
		this.psapAddress = psapAddress;
	}

	public int encode(OutputStream reverseOS) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				reverseOS.write(code[i]);
			}
			return code.length;
		}

		int codeLength = 0;
		if (psapAddress != null) {
			codeLength += psapAddress.encode(reverseOS, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
			reverseOS.write(0xA0);
			codeLength += 1;
			return codeLength;
		}
		
		if (e1634Address != null) {
			codeLength += e1634Address.encode(reverseOS, true);
			return codeLength;
		}
		
		throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
	}

	public int decode(InputStream is) throws IOException {
		return decode(is, null);
	}

	public int decode(InputStream is, BerTag berTag) throws IOException {

		int codeLength = 0;
		BerTag passedTag = berTag;

		if (berTag == null) {
			berTag = new BerTag();
			codeLength += berTag.decode(is);
		}

		if (berTag.equals(E1634Address.tag)) {
			e1634Address = new E1634Address();
			codeLength += e1634Address.decode(is, false);
			return codeLength;
		}

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
			psapAddress = new PresentationAddress();
			codeLength += psapAddress.decode(is, false);
			return codeLength;
		}

		if (passedTag != null) {
			return 0;
		}

		throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(reverseOS);
		code = reverseOS.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		if (e1634Address != null) {
			sb.append("e1634Address: ");
			e1634Address.appendAsString(sb, indentLevel + 1);
			return;
		}

		if (psapAddress != null) {
			sb.append("psapAddress: ");
			psapAddress.appendAsString(sb, indentLevel + 1);
			return;
		}

		sb.append("<none>");
	}

}


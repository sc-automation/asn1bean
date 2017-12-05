/**
 * This class file was automatically generated by jASN1 v1.8.3-SNAPSHOT (http://www.openmuc.org)
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


public class TBSCertList implements Serializable {

	private static final long serialVersionUID = 1L;

	public static class RevokedCertificates implements Serializable {

		private static final long serialVersionUID = 1L;

		public static class SEQUENCE implements Serializable {

			private static final long serialVersionUID = 1L;

			public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

			public byte[] code = null;
			public CertificateSerialNumber userCertificate = null;
			public Time revocationDate = null;
			public Extensions crlEntryExtensions = null;
			
			public SEQUENCE() {
			}

			public SEQUENCE(byte[] code) {
				this.code = code;
			}

			public SEQUENCE(CertificateSerialNumber userCertificate, Time revocationDate, Extensions crlEntryExtensions) {
				this.userCertificate = userCertificate;
				this.revocationDate = revocationDate;
				this.crlEntryExtensions = crlEntryExtensions;
			}

			public int encode(OutputStream os) throws IOException {
				return encode(os, true);
			}

			public int encode(OutputStream os, boolean withTag) throws IOException {

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
				if (crlEntryExtensions != null) {
					codeLength += crlEntryExtensions.encode(os, true);
				}
				
				codeLength += revocationDate.encode(os);
				
				codeLength += userCertificate.encode(os, true);
				
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
					if (berTag.equals(CertificateSerialNumber.tag)) {
						userCertificate = new CertificateSerialNumber();
						subCodeLength += userCertificate.decode(is, false);
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
					revocationDate = new Time();
					int choiceDecodeLength = revocationDate.decode(is, berTag);
					if (choiceDecodeLength != 0) {
						subCodeLength += choiceDecodeLength;
						subCodeLength += berTag.decode(is);
					}
					else {
						revocationDate = null;
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
					if (berTag.equals(Extensions.tag)) {
						crlEntryExtensions = new Extensions();
						subCodeLength += crlEntryExtensions.decode(is, false);
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
				if (berTag.equals(CertificateSerialNumber.tag)) {
					userCertificate = new CertificateSerialNumber();
					subCodeLength += userCertificate.decode(is, false);
					subCodeLength += berTag.decode(is);
				}
				else {
					throw new IOException("Tag does not match the mandatory sequence element tag.");
				}
				
				revocationDate = new Time();
				subCodeLength += revocationDate.decode(is, berTag);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berTag.decode(is);
				
				if (berTag.equals(Extensions.tag)) {
					crlEntryExtensions = new Extensions();
					subCodeLength += crlEntryExtensions.decode(is, false);
					if (subCodeLength == totalLength) {
						return codeLength;
					}
				}
				throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

				
			}

			public void encodeAndSave(int encodingSizeGuess) throws IOException {
				OutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
				encode(os, false);
				code = ((BerByteArrayOutputStream) os).getArray();
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
				if (userCertificate != null) {
					sb.append("userCertificate: ").append(userCertificate);
				}
				else {
					sb.append("userCertificate: <empty-required-field>");
				}
				
				sb.append(",\n");
				for (int i = 0; i < indentLevel + 1; i++) {
					sb.append("\t");
				}
				if (revocationDate != null) {
					sb.append("revocationDate: ");
					revocationDate.appendAsString(sb, indentLevel + 1);
				}
				else {
					sb.append("revocationDate: <empty-required-field>");
				}
				
				if (crlEntryExtensions != null) {
					sb.append(",\n");
					for (int i = 0; i < indentLevel + 1; i++) {
						sb.append("\t");
					}
					sb.append("crlEntryExtensions: ");
					crlEntryExtensions.appendAsString(sb, indentLevel + 1);
				}
				
				sb.append("\n");
				for (int i = 0; i < indentLevel; i++) {
					sb.append("\t");
				}
				sb.append("}");
			}

		}

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
		public byte[] code = null;
		public List<SEQUENCE> seqOf = null;

		public RevokedCertificates() {
			seqOf = new ArrayList<SEQUENCE>();
		}

		public RevokedCertificates(byte[] code) {
			this.code = code;
		}

		public RevokedCertificates(List<SEQUENCE> seqOf) {
			this.seqOf = seqOf;
		}

		public int encode(OutputStream os) throws IOException {
			return encode(os, true);
		}

		public int encode(OutputStream os, boolean withTag) throws IOException {

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
			for (int i = (seqOf.size() - 1); i >= 0; i--) {
				codeLength += seqOf.get(i).encode(os, true);
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

			if (length.val == -1) {
				while (true) {
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

					SEQUENCE element = new SEQUENCE();
					subCodeLength += element.decode(is, false);
					seqOf.add(element);
				}
			}
			while (subCodeLength < totalLength) {
				SEQUENCE element = new SEQUENCE();
				subCodeLength += element.decode(is, true);
				seqOf.add(element);
			}
			if (subCodeLength != totalLength) {
				throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

			}
			codeLength += subCodeLength;

			return codeLength;
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			OutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
			encode(os, false);
			code = ((BerByteArrayOutputStream) os).getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			sb.append("{\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (seqOf == null) {
				sb.append("null");
			}
			else {
				Iterator<SEQUENCE> it = seqOf.iterator();
				if (it.hasNext()) {
					it.next().appendAsString(sb, indentLevel + 1);
					while (it.hasNext()) {
						sb.append(",\n");
						for (int i = 0; i < indentLevel + 1; i++) {
							sb.append("\t");
						}
						it.next().appendAsString(sb, indentLevel + 1);
					}
				}
			}

			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	public Version version = null;
	public AlgorithmIdentifier signature = null;
	public Name issuer = null;
	public Time thisUpdate = null;
	public Time nextUpdate = null;
	public RevokedCertificates revokedCertificates = null;
	public Extensions crlExtensions = null;
	
	public TBSCertList() {
	}

	public TBSCertList(byte[] code) {
		this.code = code;
	}

	public TBSCertList(Version version, AlgorithmIdentifier signature, Name issuer, Time thisUpdate, Time nextUpdate, RevokedCertificates revokedCertificates, Extensions crlExtensions) {
		this.version = version;
		this.signature = signature;
		this.issuer = issuer;
		this.thisUpdate = thisUpdate;
		this.nextUpdate = nextUpdate;
		this.revokedCertificates = revokedCertificates;
		this.crlExtensions = crlExtensions;
	}

	public int encode(OutputStream os) throws IOException {
		return encode(os, true);
	}

	public int encode(OutputStream os, boolean withTag) throws IOException {

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

		if (crlExtensions != null) {
			sublength = crlExtensions.encode(os, true);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(os, sublength);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
			os.write(0xA0);
			codeLength += 1;
		}
		
		if (revokedCertificates != null) {
			codeLength += revokedCertificates.encode(os, true);
		}
		
		if (nextUpdate != null) {
			codeLength += nextUpdate.encode(os);
		}
		
		codeLength += thisUpdate.encode(os);
		
		codeLength += issuer.encode(os);
		
		codeLength += signature.encode(os, true);
		
		if (version != null) {
			codeLength += version.encode(os, true);
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
			if (berTag.equals(Version.tag)) {
				version = new Version();
				subCodeLength += version.decode(is, false);
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
			if (berTag.equals(AlgorithmIdentifier.tag)) {
				signature = new AlgorithmIdentifier();
				subCodeLength += signature.decode(is, false);
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
			issuer = new Name();
			int choiceDecodeLength = issuer.decode(is, berTag);
			if (choiceDecodeLength != 0) {
				subCodeLength += choiceDecodeLength;
				subCodeLength += berTag.decode(is);
			}
			else {
				issuer = null;
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
			thisUpdate = new Time();
			choiceDecodeLength = thisUpdate.decode(is, berTag);
			if (choiceDecodeLength != 0) {
				subCodeLength += choiceDecodeLength;
				subCodeLength += berTag.decode(is);
			}
			else {
				thisUpdate = null;
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
			nextUpdate = new Time();
			choiceDecodeLength = nextUpdate.decode(is, berTag);
			if (choiceDecodeLength != 0) {
				subCodeLength += choiceDecodeLength;
				subCodeLength += berTag.decode(is);
			}
			else {
				nextUpdate = null;
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
			if (berTag.equals(RevokedCertificates.tag)) {
				revokedCertificates = new RevokedCertificates();
				subCodeLength += revokedCertificates.decode(is, false);
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
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
				codeLength += length.decode(is);
				crlExtensions = new Extensions();
				subCodeLength += crlExtensions.decode(is, true);
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
		if (berTag.equals(Version.tag)) {
			version = new Version();
			subCodeLength += version.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(AlgorithmIdentifier.tag)) {
			signature = new AlgorithmIdentifier();
			subCodeLength += signature.decode(is, false);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		issuer = new Name();
		subCodeLength += issuer.decode(is, berTag);
		subCodeLength += berTag.decode(is);
		
		thisUpdate = new Time();
		subCodeLength += thisUpdate.decode(is, berTag);
		if (subCodeLength == totalLength) {
			return codeLength;
		}
		subCodeLength += berTag.decode(is);
		
		nextUpdate = new Time();
		int choiceDecodeLength = nextUpdate.decode(is, berTag);
		if (choiceDecodeLength != 0) {
			subCodeLength += choiceDecodeLength;
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		else {
			nextUpdate = null;
		}
		
		if (berTag.equals(RevokedCertificates.tag)) {
			revokedCertificates = new RevokedCertificates();
			subCodeLength += revokedCertificates.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
			subCodeLength += length.decode(is);
			crlExtensions = new Extensions();
			subCodeLength += crlExtensions.decode(is, true);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		OutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = ((BerByteArrayOutputStream) os).getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		boolean firstSelectedElement = true;
		if (version != null) {
			sb.append("\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("version: ").append(version);
			firstSelectedElement = false;
		}
		
		if (!firstSelectedElement) {
			sb.append(",\n");
		}
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (signature != null) {
			sb.append("signature: ");
			signature.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("signature: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (issuer != null) {
			sb.append("issuer: ");
			issuer.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("issuer: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (thisUpdate != null) {
			sb.append("thisUpdate: ");
			thisUpdate.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("thisUpdate: <empty-required-field>");
		}
		
		if (nextUpdate != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("nextUpdate: ");
			nextUpdate.appendAsString(sb, indentLevel + 1);
		}
		
		if (revokedCertificates != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("revokedCertificates: ");
			revokedCertificates.appendAsString(sb, indentLevel + 1);
		}
		
		if (crlExtensions != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("crlExtensions: ");
			crlExtensions.appendAsString(sb, indentLevel + 1);
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}


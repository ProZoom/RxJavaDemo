package com.corewise.chenguangpim.utils;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class DataUtils {

	private char[] getChar(int position) {
		String str = String.valueOf(position);
		if (str.length() == 1) {
			str = "0" + str;
		}
		char[] c = { str.charAt(0), str.charAt(1) };
		return c;
	}

	/**
	 * 16进制字符串转换成数组
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexStringTobyte(String hex) {
		if (TextUtils.isEmpty(hex)) {
			return null;
		}
		hex = hex.toUpperCase();
		int len = hex.length() / 2;
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		String temp = "";
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
			temp += result[i] + ",";
		}
		// uiHandler.obtainMessage(206, hex + "=read=" + new String(result))
		// .sendToTarget();
		return result;
	}

	public static int toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 数组转成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String toHexString(byte[] b) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			buffer.append(toHexString1(b[i]));
		}
		return buffer.toString();
	}

	public static String toHexString1(byte b) {
		String s = Integer.toHexString(b & 0xFF);
		if (s.length() == 1) {
			return "0" + s;
		} else {
			return s;
		}
	}

	/**
	 * 十六进制字符串转换成字符串
	 */
	public static String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}

	/**
	 * 字符串转换成十六进制字符串
	 */
	public static String str2Hexstr(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
		}
		return sb.toString();
	}

	public static String byte2Hexstr(byte b) {
		String temp = Integer.toHexString(0xFF & b);
		if (temp.length() < 2) {
			temp = "0" + temp;
		}
		temp = temp.toUpperCase();
		return temp;
	}

	public static String str2Hexstr(String str, int size) {
		byte[] byteStr = str.getBytes();
		byte[] temp = new byte[size];
		System.arraycopy(byteStr, 0, temp, 0, byteStr.length);
		temp[size - 1] = (byte) byteStr.length;
		String hexStr = toHexString(temp);
		return hexStr;
	}

	public static byte[] str2HexByte(String str, int size) {
		byte[] byteStr = str.getBytes();
		byte[] temp = new byte[size];
		System.arraycopy(byteStr, 0, temp, 0, byteStr.length);
		return temp;
	}

	/**
	 * 16进制字符串分割成若干块，每块32个16进制字符，即16字节
	 * 
	 * @param str
	 * @return
	 */
	public static String[] hexStr2StrArray(String str) {
		// 32个十六进制字符串表示16字节
		int len = 32;
		int size = str.length() % len == 0 ? str.length() / len : str.length() / len + 1;
		String[] strs = new String[size];
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				String temp = str.substring(i * len);
				for (int j = 0; j < len - temp.length(); j++) {
					temp = temp + "0";
				}
				strs[i] = temp;
			} else {
				strs[i] = str.substring(i * len, (i + 1) * len);
			}
		}
		return strs;
	}

	/**
	 * 把16进制字符串压缩成字节数组，在把字节数组转换成16进制字符串
	 * 
	 * @param hexstr
	 * @return
	 * @throws IOException
	 */
	public static byte[] compress(byte[] data) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(data);
		gzip.close();
		return out.toByteArray();
	}

	/**
	 * 把16进制字符串解压缩压缩成字节数组，在把字节数组转换成16进制字符串
	 * 
	 * @param hexstr
	 * @return
	 * @throws IOException
	 */
	public static byte[] uncompress(byte[] data) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		return out.toByteArray();
	}

	public static byte[] short2byte(short s) {
		byte[] size = new byte[2];
		size[0] = (byte) (s >>> 8);
		short temp = (short) (s << 8);
		size[1] = (byte) (temp >>> 8);

		// size[0] = (byte) ((s >> 8) & 0xff);
		// size[1] = (byte) (s & 0x00ff);
		return size;
	}

	public static short[] hexStr2short(String hexStr) {
		byte[] data = hexStringTobyte(hexStr);
		short[] size = new short[4];
		for (int i = 0; i < size.length; i++) {
			size[i] = getShort(data[i * 2], data[i * 2 + 1]);
		}
		return size;
	}

	// 字符序列转换为16进制字符串
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		char[] buffer = new char[2];
		for (int i = 0; i < src.length; i++) {
			buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
			buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
			// System.out.println(buffer);
			stringBuilder.append(buffer);
		}
		return stringBuilder.toString();
	}

	public static byte[] getSelectCommand(byte[] aid) {
		final ByteBuffer cmd_pse = ByteBuffer.allocate(aid.length + 6);
		cmd_pse.put((byte) 0x00) // CLA Class
				.put((byte) 0xA4) // INS Instruction
				.put((byte) 0x04) // P1 Parameter 1
				.put((byte) 0x00) // P2 Parameter 2
				.put((byte) aid.length) // Lc
				.put(aid).put((byte) 0x00); // Le
		return cmd_pse.array();
	}

	public static short getShort(byte b1, byte b2) {
		short temp = 0;
		temp |= (b1 & 0xff);
		temp <<= 8;
		temp |= (b2 & 0xff);
		return temp;
	}

	public static int getInt(byte[] data) {
		int temp = 0;
		if (data == null) {
			return 0;
		}
		int length = data.length;
		for (int i = 0; i < length; i++) {
			temp |= (data[i] & 0xff);
			if (i != length - 1) {
				temp <<= 8;
			}
		}
		return temp;
	}

	/**
	 * int to a byte array of 2 length.
	 * 
	 * @param len
	 *            data of int type
	 * @return byte array of 2 length.
	 */
	public static byte[] int2Byte(int len) {
		byte[] data = new byte[2];
		data[1] = (byte) (len >> 8);
		data[0] = (byte) (len >> 0);
		return data;
	}

	/**
	 * 异或校验
	 * 
	 * @param data
	 * @return
	 */
	public static byte xorCheck(byte[] data) {
		byte s = 0;
		for (int i = 0; i < data.length; i++) {
			s = (byte) (s ^ data[i]);
		}
		return s;
	}

	/**
	 * int to a byte array of 2 length.
	 * 
	 * @param len
	 *            data of int type
	 * @return byte array of 2 length.
	 */
	public static byte[] int2Byte2(int len) {
		byte[] data = new byte[2];
		data[0] = (byte) (len >> 8);
		data[1] = (byte) (len >> 0);
		return data;
	}
	/**
	 * Get low byte from byte
	 * @param buffer, 
	 * @return byte low.
	 */
	public static byte splitByte(byte buffer){
		byte low =(byte)(buffer & 0x0f);
		return low;
	}
    public static byte splitByte2(byte buffer){
    	byte new1 =(byte)(buffer | 0x30);
    	return new1;
    }
}

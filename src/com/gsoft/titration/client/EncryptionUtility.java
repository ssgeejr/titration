package com.gsoft.titration.client;
/*************************************************************************
 Copyright (C) 2005  Steve Gee
 stevesgee@cox.net
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*************************************************************************/

/** Algorythm used for encryption and decryption of String variables.
  * It is a quick hack, and needs a lot of work. */
public class EncryptionUtility{

	/** Encryption Algorythm used to encrypt and decrypt */
	private EncryptionAlgorithm Algorithm = null;

	/** Sets the EncryptionAlgorythm for the Utility */
	public void setEncryptionAlgorithm(EncryptionAlgorithm alg){
		Algorithm = alg;
	}

	/** Returns the EncryptionAlgorythm used by the Utility */
	public EncryptionAlgorithm getEncryptionAlgorithm(){
		if(Algorithm == null)
			Algorithm = new DefaultEncryptionAlgorithm();
		return Algorithm;
	}

	/** Will return the encrypted String clear
	  * using the currently set algorythm.  */
	public String decrypt(String cryptogram){
		EncryptionAlgorithm e = this.getEncryptionAlgorithm();
		int len = cryptogram.length();
		int seed = 0;
		char ret[] = new char[len];
		for(int i = 0; i < len; i++){
			seed = e.getSeed(cryptogram, i);
			ret[i] = e.getDecryptedChar(cryptogram.charAt(i), seed);
		}
		return new String(ret);
	}

	/** Will encrypt and return the clear String
	  * Using the currently set algorythm.  */
	public String encrypt(String word){
		EncryptionAlgorithm e = this.getEncryptionAlgorithm();
		int len = word.length();
		int seed = 0;
		char ret[] = new char[len];
		for(int i = 0; i < len; i++){
			seed = e.getSeed(word, i);
			ret[i] = e.getEncryptedChar(word.charAt(i), seed);
		}
		return new String(ret);
	}

	public static void main(String args[]){
		EncryptionUtility eu = new EncryptionUtility();
		String crypto = args[0];
		System.out.println(crypto);
		crypto = eu.encrypt(crypto);
		System.out.println(crypto);
		crypto = eu.decrypt(crypto);
		System.out.println(crypto);
	}


	class DefaultEncryptionAlgorithm implements EncryptionAlgorithm
	{
		/** This algorithm adds 23 to the character, and then
		  * subtracts the amount of the seed which is the length of
		  * the string, unless the length of the string is 23, then
		  * it will subtract the length of the string + 3.
		  *
		  * If the result is less than 33, the result is subtracted
		  * from 126.
		  * If the result is greater than 126, the result less 126 is
		  * added to 33.
		  */

		public char getEncryptedChar(char base, int seed){
			int increment = (seed == 23)?seed + 3:seed;
			int result = (base + 23) - increment;
			if(result > 126){
				result = (result - 126) + 33;
			}
			else if(result < 33){
				result = 126 - (33 - result);
			}
			return (char)result;
		}

		public char getDecryptedChar(char crypt, int seed){
			int increment = (seed == 23)?seed + 3:seed;
			int result = (crypt + increment) - 23;
			if(result > 126){
				result = (result - 126) + 33;
			}
			else if(result < 33){
				result = (result + 126) - 33;
			}
			return (char)result;
		}

		public int getSeed(String base, int current){
			return base.length() + current;
		}
	}

}
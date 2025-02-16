package com.exception;

public class PoorPasswordStrengthException extends Exception {
	
	 private String msg;
		
		public PoorPasswordStrengthException(String msg) {
			this.msg=msg;
		}
		
	  public String toString() {
		  return "Problem is ..."+msg ;
		  }
}

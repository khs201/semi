package edu.kh.ib.board.model.exception;

public class ImageUpdateException extends RuntimeException {
	
	public ImageUpdateException() {
		super("이미지 수정 중 예외 발생");
	}
	
	public ImageUpdateException(String message) {
		super(message);
	}

}

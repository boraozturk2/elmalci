package com.bozturk.idle.dto;

public class BaseResponseDto {

	private Boolean success;

	public BaseResponseDto() {
	}

	public BaseResponseDto(Boolean success) {
		this.success = success;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "BaseResponseDto [success=" + success + "]";
	}

}

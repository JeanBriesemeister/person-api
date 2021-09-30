package com.jean.springframework.utils;

import com.jean.springframework.domain.Phone;
import com.jean.springframework.domain.enums.PhoneType;
import com.jean.springframework.dto.PhoneDTO;

public class PhoneUtils {

	private static final String PHONE_NUMBER = "1199999-9999";
	private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
	private static final long PHONE_ID = 1L;

	public static PhoneDTO createFakeDTO() {
		return PhoneDTO.builder().number(PHONE_NUMBER).type(PHONE_TYPE).build();
	}

	public static Phone createFakeEntity() {
		return Phone.builder().id(PHONE_ID).number(PHONE_NUMBER).type(PHONE_TYPE).build();
	}
}

package com.authentication.autentication.common;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CoreConstant {

    public static class Exception {

        public static final String INVALID_FIRST_NAME = "{message.exception.invalid.firstname}";
        public static final String INVALID_LAST_NAME = "{message.exception.invalid.lastname}";
        public static final String INVALID_EMAIL = "{message.exception.invalid.email}";
        public static final String INVALID_PHONE_NUMBER = "{message.exception.invalid.phoneNumber}";
        public static final String INVALID_PASSWORD = "{message.exception.invalid.password}";
        public static final String INVALID_GENDER = "{message.exception.invalid.gender}";
        public static final String INVALID_REFRESH_TOKEN ="message.exception.invalid.refreshToken";

        public static final String NO_SUCH_ELEMENT_FOUND ="message.execption.element.not.found";



    }
}

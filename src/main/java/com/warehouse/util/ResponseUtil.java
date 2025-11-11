package com.warehouse.util;

import com.warehouse.response.ApiResponse;
import org.springframework.http.HttpStatus;

public class ResponseUtil {
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, HttpStatus.OK.name(), data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}

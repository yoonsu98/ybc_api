package com.yoonsu.ybc.config.response;

/**
 * packageName    : com.yoonsu.ybc.config.response
 * fileName       : BaseResponseUtils
 * author         : yoons
 * date           : 2023-12-15
 * description    : BaseResponse return util
 */
public class BaseResponseUtils {
    public static <T> BaseResponse<T> success(T response) {
        return new BaseResponse<>(true, response, null);
    }

    public static BaseResponse<?> error(ErrorMap error) {
        return new BaseResponse<>(false, null, error);
    }
}

package cholog.supp.api.common;

public record APISuccessResponse<T>(
    int status,
    String message,
    T data
) {

    public static <T> APISuccessResponse<T> of(int status, String message, T data) {
        return new APISuccessResponse<T>(status, message, data);
    }
}

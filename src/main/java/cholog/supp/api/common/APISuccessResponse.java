package cholog.supp.api.common;

public record APISuccessResponse<T>(
    int status,
    String message,
    T date
) {

    public static <T> APISuccessResponse<T> of(int status, String message, T date) {
        return new APISuccessResponse<T>(status, message, date);
    }
}

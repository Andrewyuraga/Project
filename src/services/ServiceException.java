package services;

/**
 * Class ServiceException
 * <p>
 * Created by Yuraga on 15.10.2018
 */

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

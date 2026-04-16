package mx.com.skill.swep.user_service.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class Validation {

    private Validation() {
        throw new IllegalStateException("Utility class");
    }

    public static void requestObjectShouldBeNotNull(Object object, String errorMessage) {
        if (object == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }

}

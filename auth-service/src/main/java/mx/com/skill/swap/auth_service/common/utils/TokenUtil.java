package mx.com.skill.swap.auth_service.common.utils;

public class TokenUtil {

    public static String extractTokenFromHeader(String authHeader) {

        return authHeader.substring(7);
    }

}

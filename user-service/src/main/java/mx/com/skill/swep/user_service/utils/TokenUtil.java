package mx.com.skill.swep.user_service.utils;

public class TokenUtil {

    public static String extractTokenFromHeader(String authHeader) {
        return authHeader.substring(7);
    }


}

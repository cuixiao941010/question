package com.cx.question.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cx.question.entity.user.enums.RoleEnum;
import com.cx.question.jwt.UserContext.AuthorizedUser;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtToken {

    public static final String SECRET = "941010";

    public static final String TOKEN = "question-token";

    public static void main(String[] args) throws Exception {
        String createToken = createToken(1L, 1L,"哈哈");
        System.out.println(createToken);
    }

    public static String createToken(Long userId, Long departmentId, String userName) {
        Date cur = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 100);
        Date expireDate = calendar.getTime();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create().withHeader(map).withExpiresAt(expireDate).withIssuedAt(cur).withClaim("departmentId", departmentId)
                .withClaim("userId", userId).withClaim("userName", userName).sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解密token
     * @param token
     * @return
     * @throws Exception
     */
    public static AuthorizedUser verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = verifier.verify(token);
        AuthorizedUser authorizedUser = new AuthorizedUser();
        authorizedUser.setId(jwt.getClaim("userId").asLong());
        authorizedUser.setDepartmentId(jwt.getClaim("departmentId").asLong());
        authorizedUser.setUserName(jwt.getClaim("userName").asString());
//        System.out.println("depart:"+jwt.getClaim("departmentId").asLong());
//        System.out.println("userName:"+jwt.getClaim("userName").asString());
//        System.out.println("userId:"+jwt.getClaim("userId").asLong());
        return authorizedUser;
    }

}

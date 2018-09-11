package device.management.demo.service;

import device.management.demo.entity.TokenVerifition;

public interface TokenVerificationService {
	TokenVerifition addToken(TokenVerifition tokenVerifition);
	TokenVerifition findTokenByTokenCode(String token);
	TokenVerifition editToken(TokenVerifition tokenVerifition);
	boolean deleteTokenById(Long id);

}

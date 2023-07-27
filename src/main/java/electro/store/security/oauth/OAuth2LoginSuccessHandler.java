package electro.store.security.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import electro.store.entity.Account;
import electro.store.service.AccountService;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
		String oauth2ClientName = oAuth2User.getOauth2ClientName();
		String email = oAuth2User.getEmail();
		String name = oAuth2User.getName();
		
		System.out.println(request.getRemoteUser());
		authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		System.out.println(currentPrincipalName);
		
		Account account = accountService.getAccountByEmail(email);

		if (account == null) {
			//register as a new account
			accountService.createNewAccountAfterOAuthLoginSuccess(email, name, oauth2ClientName);
		} else {
			// update existing account 
			accountService.updateAccountAfterOAuthLoginSuccess(account, name, oauth2ClientName);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

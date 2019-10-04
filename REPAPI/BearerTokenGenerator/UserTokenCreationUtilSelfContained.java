
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cisco.collab.token4j.TokenAuthImpl;
import com.cisco.collab.token4j.TokenScope;
import com.cisco.collab.token4j.config.defaults.UserClientConfigDefaults;
import com.cisco.collab.token4j.exceptions.Token4jException;
import com.cisco.collab.token4j.generator.UserTokenConfig;
import com.cisco.collab.token4j.generator.UserTokenConfig.SelfContained;
import com.cisco.collab.token4j.generator.UserTokenGenerator;
import com.cisco.collab.token4j.validator.TokenInfo;
import com.cisco.collab.token4j.validator.TokenValidator;
import com.cisco.collab.token4j.validator.config.TokenValidatorConfig;
import com.cisco.collab.token4j.validator.exceptions.Token4jValidationException;

import org.apache.commons.lang3.StringUtils;


public class UserTokenCreationUtil {
	private static final String CLIENT_ID = "Cb110ffda7a21d3b4176136c4e707126c9e18c4ef4c3dafb2a97ab022bc00a96a";
	private static final String CLIENT_SECRET = "723742d4cce7e9fab44b6ad1ca97bd6430f916414bf73aa6b500ad33572644bc";
	private static final String serviceURL = "https://idbrokerbts.webex.com/idb";
	private static final String redirectURL = "https://proxy-int.broadcloudpbx.net/rep/";
	private UserTokenConfig tokenConfig;
	private UserTokenGenerator userTokenGenerator;
	private TokenValidator userTokenValidator;
	
	
	public static void main(String[] args) {
		String username = args[0];
		String password = args[1];
		UserTokenCreationUtil tokenCreation = new UserTokenCreationUtil();
		//String bearerToken = tokenCreation.getBearerToken("rep.phx303@gmail.com", "Cisco!2345");
		String bearerToken = tokenCreation.getBearerToken(username, password);
		System.out.println("bearerToken: " + bearerToken);
		
		TokenInfo userTokenInfo = tokenCreation.getTokenInfo(bearerToken);
		tokenCreation.printTokenInfo(userTokenInfo);
		
	}
	
	public UserTokenCreationUtil() {

		tokenConfig = UserTokenConfig.newBuilder()
				.clientId(CLIENT_ID)
				.clientSecret(CLIENT_SECRET)
				.redirectUrl(redirectURL)
				.scope(TokenScope.IDENTITY_SCIM)
				.selfContained(SelfContained.SELF_CONTAINED)
				.cisIdbApiServiceUrl(serviceURL)
				.build();

		userTokenGenerator = new UserTokenGenerator(tokenConfig);
		userTokenValidator = new TokenValidator(this.new DefaultTokenValidatorConfig());
	}
	
	public String getBearerToken(String username, String password) {
		TokenAuthImpl tokenAuth = new TokenAuthImpl(username, password);

		String userToken = null;
		try {
			userToken = userTokenGenerator.getToken(tokenAuth);
		} catch (Token4jException e) {
			e.printStackTrace();
		}
		return userToken;
	}
	
	public TokenInfo getTokenInfo(String userToken) {
		try {
			return userTokenValidator.getTokenInfo(userToken, true);
		} catch (Token4jValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void printTokenInfo(TokenInfo userTokenInfo) {
		System.out.println("realm: " + userTokenInfo.getRealm());
		System.out.println("roles: " + userTokenInfo.getRoles().toString());
		System.out.println("entitlements: " + userTokenInfo.getEntitlements().toString());
		System.out.println("scopes: " + userTokenInfo.getScopes().toString());
	}
	

	public class DefaultTokenValidatorConfig implements TokenValidatorConfig {
	    private static final String DEFAULT_TOKEN_INFO_URL = "https://idbrokerbts.webex.com/idb";
	    //private static final String DEFAULT_TOKEN_INFO_URL = "https://idbroker.webex.com/idb/oauth2/v1/tokeninfo";

	    private static final String DEFAULT_RESOURCE_ID = "Rd5f57d6b41e7185845025293cb0009787c0a8f6472e3e7f4994457066ebd8c9e";
	    private static final String DEFAULT_RESOURCE_SECRET = "b3a189c80afc705486eda9353d20a2ec5c9deff13cce7eb185e6f71875c3f447";
	    
		@Override
		public String getIdbApiServiceUrl() {
			return DEFAULT_TOKEN_INFO_URL;
		}

		@Override
		public String getResourceId() {
			return DEFAULT_RESOURCE_ID;
		}

		@Override
		public String getResourceSecret() {
			return DEFAULT_RESOURCE_SECRET;
		}
	}

	
}



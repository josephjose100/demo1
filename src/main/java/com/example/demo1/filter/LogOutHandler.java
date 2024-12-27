package com.example.demo1.filter;

import static java.util.Objects.nonNull;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//import com.ma.spoton.api.services.UserDeviceService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SuppressWarnings("deprecation")
@RestController
public class LogOutHandler {
	
//	@Autowired
//	private UserDeviceService userdeviceservice;
//
//  @Autowired
//  private TokenStore tokenStore;

  @Autowired
  private JwtBlacklistedTokenService tokenService;

  @DeleteMapping("/v1/oauth/token")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Transactional
  public void revokeToken(HttpServletRequest request,@RequestBody(required = false) Map<String, Object> requestBody)// @RequestParam(name = "deviceUuid") String deviceUuid//
  {    
  if (requestBody != null) 
  {  
//	  String deviceUuid = requestBody.containsKey("deviceUuid") ? (String) requestBody.get("deviceUuid") : null;
//	  userdeviceservice.deleteDeviceRegistrationId(deviceUuid); 
  }  
	  
	 // String deviceUuid = (String) requestBody.get("deviceUuid"); 
	 // String deviceUuid = (String) request.getAttribute("deviceUuid");
	// log.info("request({})",request);
	 // log.info(">> deviceUuid({})", deviceUuid);
	  
	 
	  String authorization = request.getHeader("Authorization");
      if (nonNull(authorization) && authorization.contains("Bearer")) {
      String tokenId = authorization.substring("Bearer".length() + 1);
//      OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(tokenId);
//      tokenStore.removeAccessToken(oAuth2AccessToken);
//      if (nonNull(oAuth2AccessToken.getRefreshToken())) {
//        tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
//      }
      tokenService.createBlacklistedToken(tokenId);
    }
    request.getSession().invalidate();
   
    
    
  }
}
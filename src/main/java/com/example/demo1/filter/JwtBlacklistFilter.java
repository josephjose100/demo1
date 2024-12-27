package com.example.demo1.filter;

import java.io.IOException;
import java.util.Map;


import org.springframework.http.HttpStatus;

import org.springframework.web.filter.GenericFilterBean;

import com.example.demo1.repositories.JwtBlacklistedTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@RequiredArgsConstructor
public class JwtBlacklistFilter extends GenericFilterBean{

	
	private final JwtBlacklistedTokenRepository blacklistedTokenRepository;

	  ObjectMapper objectMapper = new ObjectMapper();

	  @Override
	  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	      throws IOException, ServletException {
	    log.info(">> JwtBlacklistFilter - doFilter");
	    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	    String accessToken = resolveToken(httpServletRequest);

	    if (blacklistedTokenRepository.findByAccessToken(accessToken).isEmpty()) {
	      chain.doFilter(request, response);
	    } else {
	      Map<String, String> responseData = Map.of("error", "invalid_token", "error_description",
	          "Access token is expired or invalidated");
	      String responseMsg = objectMapper.writeValueAsString(responseData);
	      HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	      httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
	      httpServletResponse.setContentType("application/json");
	      httpServletResponse.getWriter().write(responseMsg);
	    }

	  }

	  private String resolveToken(HttpServletRequest request) {
	    String bearerToken = request.getHeader("Authorization");
	    if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
	      return bearerToken.substring(7, bearerToken.length());
	    }
	    return null;
	  }

	
}

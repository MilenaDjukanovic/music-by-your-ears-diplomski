package com.miggie.musicbyyourears.security.filters;

import com.miggie.musicbyyourears.repo.UserRepository;
import com.miggie.musicbyyourears.security.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private static final String HEADER_BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.startsWith(header, HEADER_BEARER)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if(!this.jwtTokenUtil.validate(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        UserDetails userDetails = this.userRepository.findByUsername(this.jwtTokenUtil.getUsername(token)).orElse(null);
        UsernamePasswordAuthenticationToken authentication = null;
        if(userDetails != null) {
            authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null,
                    userDetails.getAuthorities()
            );

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

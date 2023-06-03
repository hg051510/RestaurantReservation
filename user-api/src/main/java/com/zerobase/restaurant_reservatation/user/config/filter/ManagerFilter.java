package com.zerobase.restaurant_reservatation.user.config.filter;

import com.zerobase.domain.config.JwtAuthenticationProvider;
import com.zerobase.domain.domain.common.UserVo;
import com.zerobase.restaurant_reservatation.user.service.customer.CustomerService;
import com.zerobase.restaurant_reservatation.user.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/manager/*")
@RequiredArgsConstructor
public class ManagerFilter implements Filter {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final ManagerService managerService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("X-AUTH-TOKEN");
        if(!jwtAuthenticationProvider.validDateToken(token)){
            throw new ServletException("Invalid Access");
        }
        UserVo vo = jwtAuthenticationProvider.getUserVo(token);
        managerService.findByIdAndEmail(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new ServletException("Invalid Access"));
        chain.doFilter(request, response);
    }
}

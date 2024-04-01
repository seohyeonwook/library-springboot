package com.study.library.config;

import com.study.library.security.exception.AuthEntryPoint;
import com.study.library.security.filter.JwtAuthenticationFilter;
import com.study.library.security.filter.PermitAllFilter;
import com.study.library.security.handler.OAuth2SuccessHandler;
import com.study.library.service.OAuth2PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;


@EnableWebSecurity // 기존시큐리티 말고 밑에 빌더 세팅해논거 따라가라
@Configuration

// 애플리케이션의 설정 정보를 담고 있습니다. 데이터베이스 연결 설정, 보안 설정 등이 여기에 포함

public class SecurityConfig extends WebSecurityConfigurerAdapter {//0313-1
// 시큐리티  이미 완성 되어있는데 우리한테 맞게끔 상속해서 오버라이드 해서 (c + o) thhpsecurity 가져와야함
    // 보안설정 구성하는 클래쑤
    // Spring Security 구성을 설정하는 데 사용되는 기본 클래스입니다.
    // 이 클래스를 상속하고 필요한 메서드를 재정의하여 사용자 지정 보안 구성을 정의할 수 있습니다.
    // 이 클래스는 Spring Security의 강력한 기능을 사용하여 웹 응용 프로그램의 인증 및 권한 부여를 구성할 수 있도록 도와줍니다.

    @Autowired
    private PermitAllFilter permitAllFilter;
    // PermitAllFilter는 스프링 시큐리티에서 사용되는 필터 중 하나입니다. 이 필터는 특정한 요청 경로에 대한 접근을 허용하는 역할을 합니다.
    // 보통 보안 설정에서 특정 요청 경로에 대해 권한 검사를 수행하지 않고 모든 사용자에게 허용하고 싶을 때 사용됩니다.
    // 주로 antMatchers() 메서드와 함께 사용되며, antMatchers() 메서드에서 지정한 경로에 해당하는 요청이 들어오면 해당 필터가 적용됩니다.
    // 이 필터는 권한 검사를 건너뛰고 모든 요청을 허용하므로, 해당 경로에 대한 모든 요청이 통과할 수 있도록 합니다.
    // 일반적으로 로그인 페이지, 회원가입 페이지 등의 경로에 대해 인증이 필요하지 않을 때 사용됩니다.

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    //    JwtAuthenticationFilter는 JWT (JSON Web Token) 기반의 인증을 처리하는 필터입니다. 일반적으로 Spring Security에서 사용되며,
    //    클라이언트가 제공한 JWT 토큰을 검증하고 유효한 경우 사용자를 인증합니다.

    //    주요 역할은 다음과 같습니다:
    //    1. Request에서 JWT 토큰을 추출합니다.
    //    2. 추출한 토큰의 유효성을 확인합니다. 토큰의 서명을 검증하고 만료 여부를 확인합니다.
    //    3. 유효한 경우, 토큰에서 사용자 정보를 추출합니다.
    //    4. 추출한 사용자 정보를 기반으로 Spring Security의 Authentication 객체를 생성합니다.
    //    5. 생성된 Authentication 객체를 SecurityContext에 설정하여 현재 사용자를 인증합니다.
    //    이렇게 함으로써 JwtAuthenticationFilter는 클라이언트가 제공한 JWT를 사용하여 사용자를 인증하고,
    //    이후의 요청에 대해 권한 부여와 접근 제어를 수행할 수 있도록 합니다.

    @Autowired
    private AuthEntryPoint authEntryPoint;
    // AuthEntryPoint는 Spring Security에서 사용되는 인증 진입점(Entry Point)입니다.
    // 인증되지 않은 사용자가 보호된 엔드포인트에 접근하려고 할 때 호출됩니다. 이것은 사용자가 로그인되지 않은 경우에 대한 처리를 정의하는 데 사용됩니다.

    // 일반적으로 AuthEntryPoint는 다음과 같은 기능을 수행합니다:
    // 1. 인증되지 않은 사용자의 요청이 보호된 엔드포인트에 도달하면, 사용자를 로그인 페이지로 리디렉션하거나 인증 오류 메시지를 반환합니다.
    // 2. 사용자가 로그인 페이지로 리디렉션되도록 설정할 수 있습니다.
    // 3. 인증 오류 메시지를 포함한 HTTP 응답을 생성하여 클라이언트에 반환합니다.
    // 일반적으로 인증이 필요한 엔드포인트에 접근하려는 사용자에게 보내는 메시지나 동작을 정의하는 데 사용됩니다.
    // 이를 통해 인증되지 않은 사용자의 요청을 적절하게 처리하여 보안을 유지할 수 있습니다.

    @Autowired
    private OAuth2PrincipalUserService oAuth2PrincipalUserService;
    // OAuth2PrincipalUserService는 Spring Security에서 OAuth 2.0 프로토콜을 통해 인증된 사용자의 정보를
    // 가져오는 데 사용되는 사용자 서비스입니다. 이 서비스는 OAuth 2.0 토큰에 포함된
    // 사용자 정보를 기반으로 사용자를 인증하고 필요한 사용자 정보를 제공합니다.
    // 일반적으로 OAuth 2.0 인증 프로세스는 사용자가 인증 서버에 로그인하여 인증 코드 또는 액세스 토큰을 받아오는 과정을 포함합니다.
    // OAuth2PrincipalUserService는 이러한 토큰을 사용하여 해당 사용자의 정보를 가져와서 Spring Security에 제공합니다.
    // 이 서비스는 OAuth 2.0 클라이언트가 제공하는 사용자 정보 엔드포인트를 호출하거나 사용자 정보를 디코딩하여
    // 필요한 정보를 추출하는 방식으로 동작할 수 있습니다. 그 결과로, Spring Security는 해당
    // 사용자의 권한을 설정하고 보호된 엔드포인트에 대한 액세스 권한을 부여할 수 있습니다.

    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;
    // OAuth2SuccessHandler는 OAuth 2.0 로그인 과정에서 인증이 성공했을 때 호출되는 핸들러입니다.
    // 주로 OAuth 2.0 토큰을 성공적으로 받아온 후에 사용자를 로그인한 후의 페이지로 리디렉션하는 등의 작업을 수행합니다.
    // 일반적으로 OAuth 2.0 인증이 성공하면 사용자 정보가 포함된 토큰이 발급되고,
    // 이 토큰을 사용하여 사용자의 정보를 가져올 수 있습니다. OAuth2SuccessHandler는 이러한 토큰을 처리하고,
    // 필요한 사용자 정보를 가져온 다음에 로그인 후의 작업을 처리합니다. 이 핸들러를 통해 사용자가 성공적으로 로그인했음을 애플리케이션에 알릴 수 있습니다.

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }//org.이라서 클래스에 컴포넌트 못단다
    // Spring Security에서 제공하는 비밀번호 암호화 도구입니다. 이 도구를 사용하면 사용자의 비밀번호를 안전하게 해싱하여 저장할 수 있습니다.
    // BCryptPasswordEncoder를 사용하면 사용자가 제공한 비밀번호를 안전하게 해싱하여 데이터베이스에 저장할 수 있습니다.
    // 또한 사용자가 로그인할 때 입력한 비밀번호를 암호화된 비밀번호와 비교하여 인증할 수 있습니다. 이를 통해 보안성을 높일 수 있습니다.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // configure 메서드는 WebSecurityConfigurerAdapter 클래스의 하위 클래스에서 오버라이딩하여 보안 구성을 정의하는 메서드입니다.
        // Spring Security의 구성을 사용자 정의할 때 많이 사용됩니다
        // 일반적으로 configure 메서드는 HTTP 보안 설정을 구성합니다. 이 메서드 내에서는 다양한 보안 관련 기능을 설정하고 사용자 정의할 수 있습니다.
        // 예를 들어, 인증 및 권한 부여 규칙, CORS(Cross-Origin Resource Sharing) 설정, CSRF(Cross-Site Request Forgery) 설정,
        // HTTP 기반의 요청 및 응답 처리 등을 구성할 수 있습니다.
        // configure 메서드는 다양한 오버로드 형식을 가지고 있으며, 이를 통해 다양한 보안 설정을 다룰 수 있습니다.
        // 주요한 형식으로는 configure(HttpSecurity http)가 있으며, 이를 사용하여 HTTP 보안 설정을 구성할 수 있습니다.
        http.cors();
        http.csrf().disable();//csrf 검사하지마  //서버사이드랜더링/ 클라이언트 알아보기

        http.authorizeRequests() //빌더 패턴으러 - 요청이들어오면 아래 절차를 거쳐라 // . . . 찍는건 빌더패턴
                .antMatchers("/server/**", "/auth/**")// "/server/**" 서버라는 요청주소에 뒤에 머가 들어오든
                // (여기에 들어오면 인증이 필요없다 = 얘들은 허용해줘라)
                .permitAll()  //permit(권한)허용해라
                .antMatchers("/mail/authenticate")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .anyRequest() // 나머지 모든요청은
                .authenticated() //인증받아라
                .and()
                .addFilterAfter(permitAllFilter, LogoutFilter.class)// 위에 보다 얘들이 먼저다 매개변수 순서대로 간다 1하고 2
                // 요청들어왔을떄 url분석해서 바로보낼건지 jwtAuthenticationFilter거칠지 결정한다
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .oauth2Login()// 로그인 시작 이거 다는순간에 oauth2 정보들을 가지고와서 필터 거칠거다
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                // OAuth2로그인 토큰검사
                .userService(oAuth2PrincipalUserService);
    }

}
// 시큐리티 - 필터 덩어리 자료가 다들어가면 안된다 들어가기 전에 걸러줘야지

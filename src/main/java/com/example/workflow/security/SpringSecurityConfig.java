package com.example.workflow.security;

//import com.example.workflow.security.filter.JwtAuthenticationFilter;
import com.example.workflow.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableMethodSecurity(prePostEnabled = true)

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioService usuarioService) {
        return usuarioService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }



//    @Autowired
//    private AuthenticationConfiguration authenticationConfiguration;
//
//    @Bean
//    AuthenticationManager authenticationManager() throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests((auth) -> auth
//                        // Primero defines todas las rutas específicas que quieres permitir sin autenticación
//                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/logout").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/usuarios").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/usuarios/registrar").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/backup").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/enviar-correo").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/bitacora/listarbitacora").permitAll()
//                        // Luego, permites el acceso a cualquier otra ruta sin autenticación
//                        .anyRequest().permitAll())
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
//                .addFilter(new JwtValidationFilter(authenticationManager()))
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of("http://localhost:4200"));
//        config.setAllowedOriginPatterns(List.of("*"));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
//        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//        config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
//
//    @Bean
//    FilterRegistrationBean<CorsFilter> corsFilter() {
//        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
//                new CorsFilter(corsConfigurationSource()));
//        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return corsBean;
//    }
}

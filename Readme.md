# Spring Security

### [In process](https://youtu.be/b9O9NI-RJ3o?list=RDCMUC2KfmYEM4KCuA1ZurravgYw&t=1344)

---

1. add dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

2. `@EnableWebSecurity` + `@Bean public SecurityFilterChain`
```java
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests()
      .anyRequest()
      .authenticated();
    http.formLogin();
    http.httpBasic();
    return http.build();
  }
}
```

3. Added JWT dependency
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
```

4. Create `JwtAuthFilter` and `JwtUtils`
```java
@Component
public class JwtAuthFilter extends OncePerRequestFilter(){
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
      //...
    }
}
```

5. Add (`addFilterBefore`) `JwtAuthFilter` to `SecurityFilterChain`
```java
http
  .authorizeHttpRequests()
  .anyRequest()
  .authenticated()
    .and()
  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
```

6. Create `@Bean` or `Class` with implementation `UserDetailsService` (`UserDao`)
```java
  @Bean
  public UserDetailsService userDetailsService(){
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { ... }
    };
  }
```

7. Create `AuthenticationProvider authenticationProvider(){...}` and add to `SecurityFilterChain`
Also, add `userDetailsService()` to `authenticationProvider()`
```java
http
  .authorizeHttpRequests()
  .anyRequest()
  .authenticated()
    .and()
  .authenticationProvider(authenticationProvider())
  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
```

8. Create `public PasswordEncoder passwordEncoder()`and add to `authenticationProvider(){...}`

9. `SessionCreationPolicy`
```java
http
  .authorizeHttpRequests()
  .anyRequest()
  .authenticated()
    .and()
  .sessionManagement()
  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
  .authenticationProvider(authenticationProvider())
  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
```

10. Create `AuthenticationController`

---

### Solved

---

Issue with `AuthenticationManager` solved by including `@Configuration` above the class declaration

---

```xml
<dependency>
  <groupId>jakarta.xml.bind</groupId>
  <artifactId>jakarta.xml.bind-api</artifactId>
  <version>2.3.2</version>
</dependency>
<dependency>
  <groupId>org.glassfish.jaxb</groupId>
  <artifactId>jaxb-runtime</artifactId>
</dependency>
```
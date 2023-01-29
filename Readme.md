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
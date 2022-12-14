package dev.mainconde.identity.config.security

import dev.mainconde.identity.repository.UserRepository
import dev.mainconde.identity.security.AuthenticationFilter
import dev.mainconde.identity.security.JwtUtil
import dev.mainconde.identity.service.UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(private val userRepository: UserRepository,
                     private val userDetails: UserDetailsService,
                     private val jwtUtil: JwtUtil
): WebSecurityConfigurerAdapter(){

    private val PUBLIC_MATCHERS = arrayOf(
        "/api/v1/partner/**",
        "/api/v1/user/**"
    )

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeHttpRequests()
            .antMatchers(*PUBLIC_MATCHERS).permitAll()
            .anyRequest().authenticated()

        http.addFilter(AuthenticationFilter(authenticationManager(), userRepository, jwtUtil))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
}
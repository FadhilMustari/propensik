package asik.propensik.trainnas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
            http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(requests -> requests
                .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/trainee/add")).permitAll()
                // .requestMatchers(new AntPathRequestMatcher("/user/add")).hasAuthority("admin")
                // .requestMatchers(new AntPathRequestMatcher("/pelatihan/viewall")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/**")).hasAuthority("admin")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/daftar")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/searchTrainee")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/searchDaftarSaya")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/daftarPelatihanSaya")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/cancel/{id}")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/detail")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/taka")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/taba")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/takaSaya")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/tabaSaya")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/filterpelatihanTrainee")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/filterDaftarPelatihanSaya")).hasAuthority("trainee")
                .requestMatchers(new AntPathRequestMatcher("/silabus/viewall")).hasAuthority("trainee")
                // .requestMatchers(new AntPathRequestMatcher("/pelatihan/viewall")).hasAuthority("community")
                // .requestMatchers(new AntPathRequestMatcher("/pelatihan/daftar")).hasAuthority("community")
                // .requestMatchers(new AntPathRequestMatcher("/pelatihan/searchTrainee")).hasAuthority("community")
                // .requestMatchers(new AntPathRequestMatcher("/pelatihan/cancel{id}")).hasAuthority("community")
                // .requestMatchers(new AntPathRequestMatcher("/pelatihan/detail")).hasAuthority("community")
                // .requestMatchers(new AntPathRequestMatcher("/pelatihan/viewall")).hasAuthority("community")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/add")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/viewall-trainer")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/search-trainer")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/detail-trainer")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/update")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/delete")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/takaTrainer")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/tabaTrainer")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/pelatihan/filterPelatihantrainer")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/silabus/viewall-trainer")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/silabus/tambah")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/silabus/uploadToGoogleDrive")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/silabus/detail-trainer")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/silabus/update")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/silabus/delete")).hasAuthority("trainer")
                .requestMatchers(new AntPathRequestMatcher("/silabus/searchSilabus")).hasAuthority("trainer")

                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/")
                )
                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login"))
            ;
            return http.build();
        }

    @Bean
    public BCryptPasswordEncoder encoder(){return new BCryptPasswordEncoder();}

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
    //     auth.inMemoryAuthentication()
    //         .passwordEncoder(encoder())
    //         .withUser("admin")
    //         .password(encoder().encode("admin123"))
    //         .roles("admin");
    // }
}

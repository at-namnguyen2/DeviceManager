package device.management.demo.security;





//@Configuration
//
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//	
////	@Autowired
////	private PasswordEncoder encoder;
//	
//	@Autowired
//	private DataSource dataSource;
//	
//	@Autowired
//	private UnBlockUserFilter unBlockUserFilter;
//	
//	@Value("${spring.queries.users-query}")
//	private String usersQuery;
//	
//	@Value("${spring.queries.roles-query}")
//	private String rolesQuery;
//	
////	@Bean
////	public BCryptPasswordEncoder passwordEncoder() {
////		return new BCryptPasswordEncoder();
////	}
////	@Override
////	public void configure(AuthenticationManagerBuilder auth) throws Exception {
////		try {
//////			auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
////			auth.jdbcAuthentication()
////				.usersByUsernameQuery(usersQuery)
////				.authoritiesByUsernameQuery(rolesQuery)
////				.dataSource(dataSource)
////				.passwordEncoder();
////			
////		}catch(Exception e) {
////			System.out.println("loi pass" + e.getMessage());
////		}
////	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				.antMatchers("/login").permitAll()
//				.antMatchers("/registration").permitAll()
//				.antMatchers("/user/**").hasAuthority("USER")
//				.antMatchers("/admin/**").permitAll()
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.usernameParameter("email")
//				.passwordParameter("password")
//				.defaultSuccessUrl("/user/home")
//				.failureUrl("/login?errors")
//				.and()
//			.exceptionHandling()
//				.accessDeniedPage("/403");
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web
//	       .ignoring()
//	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/templates/**");
//	}
//
//	
//}


    // LocaleChangeInterceptor : used to change the locale based on HTTP request attribute value
    // SessionLocalResolver
    
    @Bean
    public AcceptHeaderLocaleResolver getLocaleResolver() {
        AcceptHeaderLocaleResolver rslt = new AcceptHeaderLocaleResolver();
        rslt.setSupportedLocales(Arrays.asList(Locale.US, Locale.FRENCH));
        rslt.setDefaultLocale(Locale.US);
        return rslt;
    }
    
    // Attention use a method name so that the bean name is "messageSource"
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rslt = new ResourceBundleMessageSource();
        rslt.setBasename("messages");
        return rslt;
    }


    @Autowired
    private MessageSource messageSource;
    
    @GetMapping("/interceptor")
    public List<ParagraphElement> getInterceptor() throws Exception {
        System.out.println("######  "+ messageSource.getMessage("hello", null, Locale.FRENCH));
        
        return null;
    }

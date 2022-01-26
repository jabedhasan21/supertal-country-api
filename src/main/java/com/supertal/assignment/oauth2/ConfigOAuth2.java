package com.supertal.assignment.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 26/01/2022 - 19:11
 */
@Configuration
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter {
    private String clientId = "pixeltrice";
    private String clientSecret = "pixeltrice-secret-key";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEogIBAAKCAQEArldvPHHuq7yQo+egIJGTeR1TAR4qM6qcFHdwggNLA5pM9i5Y\n" +
            "IqF10r93CybYSEp5ER6NrD4zmxZ02yMh4gtnnU/U70Ca+1s00i5r3xtnJFcQCx25\n" +
            "V3rZbgpl2N4PLHle54g7gj3YtQdM6HyqZ8v65qpjU2mWXtNRftvIoSgtAO6cOTOp\n" +
            "rkPJHjsA30VfJPh2ostGVA2pOKZr6zsN9HIcg0nnS6a23DamleQCtmUWmcSRoldL\n" +
            "YQg/J6crestDaC+KA6WAA5KKZ9TaUoMshdtHEjvyBhV42N+ybJnYvP8Thi8lPUCU\n" +
            "wj/U9XRvJp/HnmE5rwwWwloqy6jNJVTAlEkWTQIDAQABAoIBAGBVdeSre9svfajj\n" +
            "0ypL5nY1CumCNoWID2iLuwJMOYYLG92oLBEusIE8RNZHp5kCdbVM8bGsSS24EegI\n" +
            "mK9j5+QX+aweQcUEV8KP3hFQ/gQpi3bo/MHm4wAYBMD5qt7DTfqX33IPJJCbMRcB\n" +
            "09M49jHBEtNDc9B/6fc0EL9kCMILLVwb1Wvrl1FliXUB6qz5Bu2XNsHs6CSgm/lw\n" +
            "+RV+e0Ld2tcfy5X6fQK3wspgrnZVL5CEXHRvaqAsTIMA8nLb9TP52nug8G1DNzI6\n" +
            "xs2rE3IVjmq7OK13XEZqt/KPYS5UVyVwjwJyU6nfbu8M+pNSq29BfXVmsG4+GHyP\n" +
            "Rj9Mze0CgYEA3KviuPl6J9LpTJ0Dz9kryQmOzrOQSYM9ZdgyjteZnOcrY1/mKwev\n" +
            "YRMzEHCkVxD/nZEykVfqwbNvX8AsjAeZn4mBDcI62ZRuK2wyWMT9OvxoKWQzbq18\n" +
            "U3WI1l3czEUMYwfhTNPoYxTtLSu987H1/Hch1VvjFcKaq9VBVZemg6sCgYEAykC9\n" +
            "hU3msoA9qBwkl5FkQ/cHzHUUYy7VgwQ7ALtVxmYxofrKhBV3YT3qbiHmt+uoMG8G\n" +
            "KpgdFGEITnzGTuUGmZHS9mXhdOB3uVhNErkys3pLwpQ6SF/T7hAplirMNF4Lo4IV\n" +
            "RPur//CjeP1g0rZoZ/PouTRFruYZ1roqkjlx1ecCgYB3bAIfzst//9FIJ/UnJ8Vd\n" +
            "X/HMBTtBvWed1TnPOfilBCnj9sMuaFNOOsC41ezqPYXINQrjw2uFdmEAtZSmF3rY\n" +
            "7uZLluCYXY6dq0k35yuQH4cTV2nKLNMKq6HpORTD614/vcT8+fVaobmVZ4W9+4I/\n" +
            "Jg3GVJr5bL6d3aQk+RQhTwKBgDjYgWwaPSEmki3R2ycOolPH9tUsgxJtc21jnwHR\n" +
            "pYYh6wlP5+O/9NHMCTvzI1nGTntueRk3r9SugRsuTr6V+IjpR5YhhIDJJl1dyCvf\n" +
            "CbUQRoLmDouRA4wB1u5yEPsXA8ZtJXVCF1HUXN4AuVoAEtBE6cBEROGnotOLd7Op\n" +
            "mak5AoGAHZ+NzPEevP6Tdj9z4+Ow7NvOryJnAg124Hv2oOPYjqcp95VRVTRrvsGE\n" +
            "lwbYhilYSziOGp4XVINoxdHQN/wGEvKewDRc1bEuHghRP+f0CAPDuIMCj8ydBl4D\n" +
            "uZvI1CFXWQiL8pxb2lMm5b6KY7R9w9zzSCVn4YNTjB2SjebAaiw=\n" +
            "-----END RSA PRIVATE KEY-----\n";//paste your private key here

    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArldvPHHuq7yQo+egIJGT\n" +
            "eR1TAR4qM6qcFHdwggNLA5pM9i5YIqF10r93CybYSEp5ER6NrD4zmxZ02yMh4gtn\n" +
            "nU/U70Ca+1s00i5r3xtnJFcQCx25V3rZbgpl2N4PLHle54g7gj3YtQdM6HyqZ8v6\n" +
            "5qpjU2mWXtNRftvIoSgtAO6cOTOprkPJHjsA30VfJPh2ostGVA2pOKZr6zsN9HIc\n" +
            "g0nnS6a23DamleQCtmUWmcSRoldLYQg/J6crestDaC+KA6WAA5KKZ9TaUoMshdtH\n" +
            "EjvyBhV42N+ybJnYvP8Thi8lPUCUwj/U9XRvJp/HnmE5rwwWwloqy6jNJVTAlEkW\n" +
            "TQIDAQAB\n" +
            "-----END PUBLIC KEY-----";//paste your public key here


    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}

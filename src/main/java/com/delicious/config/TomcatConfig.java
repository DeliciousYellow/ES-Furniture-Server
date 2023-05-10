/**
 * 这个是拿来弄HTTPS的SSL加密的
 */
//package com.delicious.config;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 参考https://www.baidu.com/link?url=zrv6nmAx5CAPRseAxWZ8nBuTJwZ0sWpnCu_zo2aHVdU2h8xvqNm_dojn5c92i70J&wd=&eqid=a52739ca000271a1000000056412cbc9
// * 在这里，我们配置了 Http 的请求端口为 8081，所有来自 8081 的请求，将被自动重定向到 8080 这个 https 的端口上。
// * 如此之后，我们再去访问 http 请求，就会自动重定向到 https。
// */
//@Configuration
//public class TomcatConfig {
//    @Bean
//    TomcatServletWebServerFactory tomcatServletWebServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory(){
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint constraint = new SecurityConstraint();
//                constraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                constraint.addCollection(collection);
//                context.addConstraint(constraint);
//            }
//        };
//        factory.addAdditionalTomcatConnectors(createTomcatConnector());
//        return factory;
//    }
//    private Connector createTomcatConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8081);
//        connector.setSecure(false);
//        connector.setRedirectPort(8080);
//        return connector;
//    }
//}
package org.apache.cayenne.demo;

import org.apache.cayenne.commitlog.CommitLogModule;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.configuration.web.CayenneFilter;
import org.apache.cayenne.configuration.web.WebModule;
import org.apache.cayenne.configuration.web.WebUtil;
import org.apache.cayenne.crypto.CryptoModule;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

public class CayFilter extends CayenneFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        ServerRuntime runtime = ServerRuntime.builder()
                .addConfig("cayenne-project.xml")
                .addModule(new WebModule())
                .addModule(CryptoModule.extend()
                        .keyStore(Application.class.getResource("/ks1.jceks"), "keystore".toCharArray(), "keystore")
                        .module())
                .addModule(CommitLogModule.extend()
                         .addListener(MyCommitLogListner.class)
                         .module())
                .build();

        WebUtil.setCayenneRuntime(filterConfig.getServletContext(), runtime);
    }
}
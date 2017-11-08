package org.apache.cayenne.demo;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;
import io.bootique.jetty.JettyModule;
import io.bootique.jetty.MappedFilter;
import io.bootique.jetty.MappedServlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Application implements Module {

    public static final void main(String[] args) {
        Bootique.app(args).module(Application.class).autoLoadModules().run();
    }

    @Override
    public void configure(Binder binder) {

        //wrap not annotated servlet into a special metadata object MappedServlet
        MappedServlet mappedServlet = new MappedServlet<>(
                new StartPage(),
                Collections.singleton(""),
                "startPage", new HashMap<String, String>() {
        });

        MappedServlet mappedShowServlet = new MappedServlet<>(
                new ShowServlet(),
                Collections.singleton("/show"),
                "showServlet", new HashMap<String, String>() {
        });

        MappedServlet mappedAddArtistServlet = new MappedServlet<>(
                new AddArtistServlet(),
                Collections.singleton("/addArtist"),
                "addArtistServlet", new HashMap<String, String>() {
        });

        MappedServlet mappedAddPaintingServlet = new MappedServlet<>(
                new AddPaintingServlet(),
                Collections.singleton("/addPainting"),
                "addPaintingServlet", new HashMap<String, String>() {
        });

        MappedFilter<CayFilter> mappedFilter = new MappedFilter<>(new CayFilter(), new HashSet<String>() {{
            add("/s1/*");
        }}, 0);

        JettyModule.extend(binder).addMappedServlet(mappedServlet)
                .addMappedServlet(mappedShowServlet)
                .addMappedServlet(mappedAddArtistServlet)
                .addMappedServlet(mappedAddPaintingServlet)
                .addMappedFilter(mappedFilter);
    }
}
package br.com.thiagomagdalena.coursesservice.adapter;


public interface Adapter<Source, Destination> {

    Destination adapt(Source source);

    Destination adapt(Source source, Destination destination);

}

package nl.tudelft.tbm.eeni.owl2java.model.jenautils;

import com.hp.hpl.jena.util.iterator.Filter;

public class NullFilter<T> extends Filter<T> {

    @Override
    public boolean accept(T obj) {
        return (obj == null);
    }

}

package com.domain.drapps.firebaseimplementation;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by diogo.rosa on 21/03/2018.
 */

public class PlacesResponse {
    @ElementList(required = false,inline = true)
    List<PlaceSingleResponse> results;

    public List<PlaceSingleResponse> getResults() {
        return results;
    }
}



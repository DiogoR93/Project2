package com.domain.drapps.firebaseimplementation;

import org.simpleframework.xml.Element;

/**
 * Created by diogo.rosa on 21/03/2018.
 */

public class PlaceSingleResponse {
    @Element(name = "name")
    String name;
    @Element(name = "id")
    String id;

}

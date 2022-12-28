package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String,Movie> movieDB = new HashMap<>();
    Map<String,Director> directorDB = new HashMap<>();
    Map<String, List<String>> dirMovPair = new HashMap<>();

}

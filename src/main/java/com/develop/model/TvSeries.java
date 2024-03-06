package com.develop.model;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class TvSeries {
    int id;
    URL url;
    String name;
    String type;
    String language;
}

package com.springboot.titletech.util;

import java.io.InputStream;

public class insertTestDataIntoDB {

    public insertTestDataIntoDB() {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("names_test_data.json");

    }
}

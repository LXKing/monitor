package com.rayton;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MyPortlet {

    public static void main(String[] args) {}

    public static String shit() {

        String string = null;

        if (string != null) {
            String id = string.toUpperCase();
            if (id == null)
                id = "";
            List<String> vos = Arrays.asList(new String(id));
            if (vos != null) {
                for (String v : vos) {
                    if (v != null) {
                        return v;
                    }
                    continue;
                }
                return new String();
            } else
                return new String();
        } else
            return new String();


    }


    public static String shit2() {

        String string = null;


        return Optional.ofNullable(string).map(v -> {
            List<String> vos = Arrays.asList(new String(Optional.ofNullable(v.toUpperCase()).orElse("")));


            v = Optional.ofNullable(vos).orElse(new ArrayList<String>()).stream().filter(vo -> vo != null).findFirst
                    ().orElse(new String());
            return v;
        }).orElse(new String());
    }
}

package com.example.dell.cc_task.model.pojo;

/**
 * Created by DELL on 12/6/2016.
 */

// This AndroidVersion model class stores the JSON object.
// The Retrofit GSON converter takes care of converting the JSON objects and store it in our model class.
// Then generate getter methods.

public class AndroidVersion {

        private String ver;
        private String name;
        private String api;

        public String getVer() {
            return ver;
        }

        public String getName() {
            return name;
        }

        public String getApi() {
            return api;
        }
    }


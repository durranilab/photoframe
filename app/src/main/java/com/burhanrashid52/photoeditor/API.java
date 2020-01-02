package com.burhanrashid52.photoeditor;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String BASE_URL = "https://raw.githubusercontent.com/";


    @GET("/altafshkh/Altafshaikh/master/getFrames.json")
    Call<List<DataModelFrames>> getFrames();


}

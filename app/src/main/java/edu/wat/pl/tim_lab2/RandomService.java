package edu.wat.pl.tim_lab2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RandomService {
    @GET("/random/list/{amount}")
    Call<List<Integer>> randomValues(@Path("amount") String amount);
}

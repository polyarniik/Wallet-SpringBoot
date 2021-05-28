package ru.kpfu.itis.safiullin.walletspringboot.services;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Override
    public Float getCurrency() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://api.currencylayer.com/live?access_key=1e92afcac2eb1fd9a9227593a9981a04")
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            return jsonObject.getJSONObject("quotes").getFloat("USDRUB");
        } catch (IOException e) {
            return null;
        }
    }
}

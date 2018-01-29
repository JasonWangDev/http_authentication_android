package idv.jasonwangdev.http_authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String basic = Credentials.basic("userName", "password");

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.authenticator(new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                return response.request().newBuilder().header("Authorization", basic).build();
            }
        });
        OkHttpClient client = builder.build();

        Request request = new Request.Builder().url("url").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "onFailure");
                Log.d("TAG", call.toString());
                Log.d("TAG", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG", "onResponse");

//                String filePath = MainActivity.this.getCacheDir() + File.separator + String.format("FILE_%tF", new Date());
//                File file = new File(filePath);
//                FileOutputStream fos = new FileOutputStream(file);
//                InputStream inputStream = response.body().byteStream();
//
//                byte[] buffer = new byte[2048];
//                long total = response.body().contentLength();
//                long sum = 0;
//                int len;
//
//                while ((len = inputStream.read(buffer)) != -1) {
//                    fos.write(buffer, 0, len);
//                    sum += len;
//                    Log.d("TAG", "Sum " + sum);
//                }
//
//                inputStream.close();
//                fos.flush();
//                fos.close();
            }
        });
    }

}

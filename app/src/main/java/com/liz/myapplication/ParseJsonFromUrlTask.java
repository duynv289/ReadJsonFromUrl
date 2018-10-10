package com.liz.myapplication;

import android.os.AsyncTask;

import com.liz.myapplication.enity.Lists;
import com.liz.myapplication.enity.Main;
import com.liz.myapplication.enity.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ParseJsonFromUrlTask extends AsyncTask<String, Void, ArrayList<Lists>> {

    private static final String KEY_LIST = "list";
    private static final String KEY_DT = "dt";
    private static final String KEY_TEMP = "temp";
    private static final String KEY_TEMP_MIN = "temp_min";
    private static final String KEY_TEMP_MAX = "temp_max";
    private static final String KEY_PRESSURE = "pressure";
    private static final String KEY_SEA_LEVEL = "sea_level";
    private static final String KEY_GRND_LEVEL = "grnd_level";
    private static final String KEY_HUMIDITY = "humidity";
    private static final String KEY_TEMP_KF = "temp_kf";
    private static final String KEY_WEATHER = "weather";
    private static final String KEY_ID = "id";
    private static final String KEY_MAIN = "main";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_ICON = "icon";
    private static final String REQUEST_METHOD = "GET";

    private OnFetchDataListener listener;

    public ParseJsonFromUrlTask(OnFetchDataListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Lists> doInBackground(String... strings) {
        StringBuffer sb = new StringBuffer();
        String api_url = strings[0];
        try {
            URL url = new URL(api_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(REQUEST_METHOD);

            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fetchData(sb.toString());
    }

    private ArrayList<Lists> fetchData(String s) {
        ArrayList<Lists> lists = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.optJSONArray(KEY_LIST);
            for (int i = 0; i < 5; i++) {
                JSONObject listObject = jsonArray.optJSONObject(i);
                int dt = listObject.optInt(KEY_DT);
                JSONObject mainObject = listObject.optJSONObject(KEY_MAIN);
                double temp = mainObject.optDouble(KEY_TEMP);
                double temp_min = mainObject.optDouble(KEY_TEMP_MIN);
                double temp_max = mainObject.optDouble(KEY_TEMP_MAX);
                double pressure = mainObject.optDouble(KEY_PRESSURE);
                double sea_level = mainObject.optDouble(KEY_SEA_LEVEL);
                double grnd_level = mainObject.optDouble(KEY_GRND_LEVEL);
                int humidity = mainObject.optInt(KEY_HUMIDITY);
                double temp_kf = mainObject.optDouble(KEY_TEMP_KF);
                Main mainInstance = new Main(temp, temp_min, temp_max, pressure, sea_level,
                        grnd_level,
                        humidity, temp_kf);
                JSONArray weatherArray = listObject.optJSONArray(KEY_WEATHER);
                JSONObject weatherObject = weatherArray.optJSONObject(0);
                int id = weatherObject.optInt(KEY_ID);
                String main = weatherObject.optString(KEY_MAIN);
                String description = weatherObject.optString(KEY_DESCRIPTION);
                String icon = weatherObject.optString(KEY_ICON);
                Weather weatherInstance = new Weather(id, main, description, icon);

                Lists listsInstance = new Lists(dt, mainInstance, weatherInstance);
                lists.add(listsInstance);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lists;
    }

    @Override
    protected void onPostExecute(ArrayList<Lists> lists) {
        super.onPostExecute(lists);
        listener.onFetchData(lists);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    interface OnFetchDataListener {
        void onFetchData(ArrayList<Lists> lists);
    }

}

package com.examen.android.WebServicies;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.examen.android.AppStatusExamen;
import com.examen.android.Models.ItemData;
import com.examen.android.Models.ItemUbicaciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GetRequestAllUserInfo extends AsyncTask<String, String, ItemData> {
    @SuppressLint("StaticFieldLeak")
    private Activity activity;
    private int code = 200;
    private ItemData itemData;

    public GetRequestAllUserInfo(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ItemData doInBackground(String... params) {

        String os_O = AppStatusExamen.os;
        String env = AppStatusExamen.en;
        String userId = AppStatusExamen.userId;
        itemData = new ItemData();

        HttpURLConnection conn;
        String input;

        try {

            URL url = new URL("https://https://apisls.upaxdev.com/task/initial_load");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");


            input = "{"
                    + "\"userId\":\"" + userId + "\","
                    + "\"env\": \"" + env
                    + "\"os_O\": \"" + os_O
                    + "\"\r\n" + "}" + "";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            int responseCode = conn.getResponseCode();
            code = conn.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            if (responseCode == HttpURLConnection.HTTP_OK) {

                JSONObject objectCategory = new JSONObject(in.readLine());
                itemData.setCode(objectCategory.getString("code"));
                itemData.setMessage(objectCategory.getString("message"));

                try {
                    unzip(new File("/sdcard/cargaInicial_flat_2020-0521_107458.Zip"), new File("/sdcard"));
                    try {
                        String mFileContent = loadFileContent("/sdcard/cargaInicial_flat_2020-0521_107458.Zip");

                        JSONArray mJsonObj = objectCategory.getJSONArray(mFileContent);
                        List<ItemUbicaciones> listProducts = new ArrayList<>();
                        for (int j = 0; j < mJsonObj.length(); j++) {
                            JSONObject objectProducts = mJsonObj.getJSONObject(j);
                            ItemUbicaciones itemUbicaciones = new ItemUbicaciones();
                            itemUbicaciones.setLat(objectProducts.getString(""));
                            itemUbicaciones.setLog(objectProducts.getString(""));
                            itemData.setItemUbicaciones(itemUbicaciones);
                            listProducts.add(itemUbicaciones);
                        }

                    } catch (Throwable t) {
                        //Log.d("TAG", "Could not parse malformed JSON: \n" + mFileContent);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return itemData;
    }

    @Override
    protected void onPostExecute(ItemData listClases) {
        super.onPostExecute(listClases);

        if (code == 200) {

        } else if (code == 400) {
            Toast.makeText(activity, "No se encontraron datos", Toast.LENGTH_SHORT).show();
        } else if (code == 500) {
            Toast.makeText(activity, "error de servidor", Toast.LENGTH_SHORT).show();
        }
    }

    public void unzip(File zipFile, File targetDirectory) throws IOException {
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
        try {
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[8192];
            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(targetDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " + dir.getAbsolutePath());
                if (ze.isDirectory())
                    continue;
                FileOutputStream fout = new FileOutputStream(file);
                try {
                    while ((count = zis.read(buffer)) != -1) fout.write(buffer, 0, count);
                } finally {
                    fout.close();
                }
            }
        } finally {
            zis.close();
        }
    }


    private String loadFileContent(String mFilePath) {
        mFilePath = "yourFilePath";
        FileInputStream fos = null;
        String fileContent = "";
        try {
            fos = new FileInputStream(mFilePath);
            int fileSize = fos.available();
            byte[] bytes = new byte[(int) fileSize];
            int offset = 0;
            int count = 0;
            while (offset < fileSize) {
                count = fos.read(bytes, offset, fileSize - offset);
                if (count >= 0)
                    offset += count;
            }
            fos.close();

            if (fileSize == 0)
                return null;

            fileContent = new String(fileContent, "UTF-8");

        } catch (Exception e) {
            return null;
        }
        return fileContent;
    }
}

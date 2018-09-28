package com.irfansyed.VAS.VASMonitring.Upload;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import Global.A.A4401_A4473;
import Global.GS.Q1101_Q1610;
import data.LocalDataManager;
import utils.MyPreferences;
import utils.PostRequestData;
import utils.QuestionModel;

/**
 * Created by Umeed-e-Nau on 12/28/2016.
 */
public class Upload_A4401_A4473 extends AsyncTask {

    Context mContext;
    public static ProgressDialog dialog;
    HashMap<String, String> param;
    String[] interviewLogData;
    String mUserMsg;

    public Upload_A4401_A4473(Context context) {

        //stop interview
       // if(InterviewUploadingStatus.status<2)
         //   this.cancel(false);

        mContext = context;
        dialog = new ProgressDialog(context);
        param = new HashMap<>();

    }

    @Override
    protected void onPreExecute() {

        dialog.setMessage("Uploading interview Please wait ....");
        dialog.setCancelable(false);
        dialog.show();

        HashMap<String, List<String>> map = QuestionModel.quest.get("a");

        //region Query
        String query = "select * from A4401_A4473 where study_id = '%s' order by id  desc LIMIT 1";

        query = String.format(query, Q1101_Q1610.study_id);

        LocalDataManager Lm = new LocalDataManager(mContext);

        Cursor c = LocalDataManager.database.rawQuery(query, null);

        if (c != null) {
            if (c.moveToFirst()) {
                param.put("tableName", "a4401_a4473");
                param.put(Q1101_Q1610.interviewType, String.valueOf(Q1101_Q1610.interviewType_upload));
                param.put(A4401_A4473.study_id, c.getString(c.getColumnIndex("study_id")));
                param.put(A4401_A4473.A4401, c.getString(c.getColumnIndex("A4401")));
                param.put(A4401_A4473.A4402, c.getString(c.getColumnIndex("A4402")));
                param.put(A4401_A4473.A4402_5_OT, c.getString(c.getColumnIndex("A4402_5_OT")));
                param.put(A4401_A4473.A4403_province, c.getString(c.getColumnIndex("A4403_provi")));
                param.put(A4401_A4473.A4403_district, c.getString(c.getColumnIndex("A4403_distr")));
                param.put(A4401_A4473.A4404_years, c.getString(c.getColumnIndex("A4404_years")));
                param.put(A4401_A4473.A4405_hours, c.getString(c.getColumnIndex("A4405_hours")));
                param.put(A4401_A4473.A4405_minutes, c.getString(c.getColumnIndex("A4405_minut")));
                param.put(A4401_A4473.A4451_1, c.getString(c.getColumnIndex("A4451_1")));
                param.put(A4401_A4473.A4451_2, c.getString(c.getColumnIndex("A4451_2")));
                param.put(A4401_A4473.A4451_3, c.getString(c.getColumnIndex("A4451_3")));
                param.put(A4401_A4473.A4451_4, c.getString(c.getColumnIndex("A4451_4")));
                param.put(A4401_A4473.A4451_5, c.getString(c.getColumnIndex("A4451_5")));
                param.put(A4401_A4473.A4451_6, c.getString(c.getColumnIndex("A4451_6")));
                param.put(A4401_A4473.A4451_7, c.getString(c.getColumnIndex("A4451_7")));
                param.put(A4401_A4473.A4451_8, c.getString(c.getColumnIndex("A4451_8")));
                param.put(A4401_A4473.A4451_9, c.getString(c.getColumnIndex("A4451_9")));
                param.put(A4401_A4473.A4451_10, c.getString(c.getColumnIndex("A4451_10")));
                param.put(A4401_A4473.A4451_11, c.getString(c.getColumnIndex("A4451_11")));
                param.put(A4401_A4473.A4451_12, c.getString(c.getColumnIndex("A4451_12")));
                param.put(A4401_A4473.A4451_13, c.getString(c.getColumnIndex("A4451_13")));
                param.put(A4401_A4473.A4451_13_OT, c.getString(c.getColumnIndex("A4451_13_OT")));
                param.put(A4401_A4473.A4451_code, c.getString(c.getColumnIndex("A4451_code")));
                param.put(A4401_A4473.A4452_1, c.getString(c.getColumnIndex("A4452_1")));
                param.put(A4401_A4473.A4452_2, c.getString(c.getColumnIndex("A4452_2")));
                param.put(A4401_A4473.A4452_3, c.getString(c.getColumnIndex("A4452_3")));
                param.put(A4401_A4473.A4452_4, c.getString(c.getColumnIndex("A4452_4")));
                param.put(A4401_A4473.A4452_5, c.getString(c.getColumnIndex("A4452_5")));
                param.put(A4401_A4473.A4452_6, c.getString(c.getColumnIndex("A4452_6")));
                param.put(A4401_A4473.A4452_7, c.getString(c.getColumnIndex("A4452_7")));
                param.put(A4401_A4473.A4452_8, c.getString(c.getColumnIndex("A4452_8")));
                param.put(A4401_A4473.A4452_9, c.getString(c.getColumnIndex("A4452_9")));
                param.put(A4401_A4473.A4452_9_OT, c.getString(c.getColumnIndex("A4452_9_OT")));
                param.put(A4401_A4473.A4452_code, c.getString(c.getColumnIndex("A4452_code")));
                param.put(A4401_A4473.A4453_1, c.getString(c.getColumnIndex("A4453_1")));
                param.put(A4401_A4473.A4453_2, c.getString(c.getColumnIndex("A4453_2")));
                param.put(A4401_A4473.A4453_3, c.getString(c.getColumnIndex("A4453_3")));
                param.put(A4401_A4473.A4453_4, c.getString(c.getColumnIndex("A4453_4")));
                param.put(A4401_A4473.A4453_5, c.getString(c.getColumnIndex("A4453_5")));
                param.put(A4401_A4473.A4453_6, c.getString(c.getColumnIndex("A4453_6")));
                param.put(A4401_A4473.A4453_7, c.getString(c.getColumnIndex("A4453_7")));
                param.put(A4401_A4473.A4453_8, c.getString(c.getColumnIndex("A4453_8")));
                param.put(A4401_A4473.A4453_9, c.getString(c.getColumnIndex("A4453_9")));
                param.put(A4401_A4473.A4453_10, c.getString(c.getColumnIndex("A4453_10")));
                param.put(A4401_A4473.A4453_11, c.getString(c.getColumnIndex("A4453_11")));
                param.put(A4401_A4473.A4453_12, c.getString(c.getColumnIndex("A4453_12")));
                param.put(A4401_A4473.A4453_12_OT, c.getString(c.getColumnIndex("A4453_12_OT")));
                param.put(A4401_A4473.A4453_code, c.getString(c.getColumnIndex("A4453_code")));
                param.put(A4401_A4473.A4454, c.getString(c.getColumnIndex("A4454")));
                param.put(A4401_A4473.A4455, c.getString(c.getColumnIndex("A4455")));
                param.put(A4401_A4473.A4456, c.getString(c.getColumnIndex("A4456")));
                param.put(A4401_A4473.A4457, c.getString(c.getColumnIndex("A4457")));
                param.put(A4401_A4473.A4471, c.getString(c.getColumnIndex("A4471")));
                param.put(A4401_A4473.A4472_1, c.getString(c.getColumnIndex("A4472_1")));
                param.put(A4401_A4473.A4472_2, c.getString(c.getColumnIndex("A4472_2")));
                param.put(A4401_A4473.A4472_3, c.getString(c.getColumnIndex("A4472_3")));
                param.put(A4401_A4473.A4472_4, c.getString(c.getColumnIndex("A4472_4")));
                param.put(A4401_A4473.A4472_5, c.getString(c.getColumnIndex("A4472_5")));
                param.put(A4401_A4473.A4472_6, c.getString(c.getColumnIndex("A4472_6")));
                param.put(A4401_A4473.A4472_7, c.getString(c.getColumnIndex("A4472_7")));
                param.put(A4401_A4473.A4472_8, c.getString(c.getColumnIndex("A4472_8")));
                param.put(A4401_A4473.A4472_9, c.getString(c.getColumnIndex("A4472_9")));
                param.put(A4401_A4473.A4472_10, c.getString(c.getColumnIndex("A4472_10")));
                param.put(A4401_A4473.A4472_11, c.getString(c.getColumnIndex("A4472_11")));
                param.put(A4401_A4473.A4472_12, c.getString(c.getColumnIndex("A4472_12")));
                param.put(A4401_A4473.A4472_DK, c.getString(c.getColumnIndex("A4472_DK")));
                param.put(A4401_A4473.A4473, c.getString(c.getColumnIndex("A4473")));
                param.put(A4401_A4473.study_id, c.getString(c.getColumnIndex("STATUS")));

            }
        }

        //endregion
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects)
    {

        String urlString = new MyPreferences(mContext).getReq1();

        URL url;
        HttpURLConnection connection;

        try
        {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
           // connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);

           OutputStream os = connection.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

            bw.write(PostRequestData.getData(param));
            bw.flush();


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String st ="", line;
                while ((line = br.readLine()) != null) {
                    st = st + line;
                }
                return st;
            }
            else
            {
                mUserMsg ="Server Couldn't process the request";
            }
        }
        catch (MalformedURLException e) {
            Toast.makeText(mContext,e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {


            mUserMsg ="Please make sure that Internet connection is available," +
                 " and server IP is inserted in settings";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        try {
            dialog.dismiss();

          if(mUserMsg != null)
                throw  new IOException();



            //int houseId = Integer.parseInt(((String) o).replace("\"",""));

            String result=(((String) o).replace("\"",""));


            Toast.makeText(mContext, "A4401_A4473 section Uploaded", Toast.LENGTH_SHORT).show();
            // new LocalDataManager(mContext).uploadInterview();

            // LogtableUpdates.UpdateLogStatusUpload(mContext,Validation.hfauploadid);

            //update_status(param.get("id"));
            thread.start();
        }

        catch (IOException e) {
            //if connection was available via connecting but
            //we can't get data from server..
            if(mUserMsg == null)
                mUserMsg ="Please check connection";
            dialog.dismiss();
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            mUserMsg = e.getMessage();
            dialog.dismiss();
        }

        catch (Exception e) {
            Toast.makeText(mContext,"Uploading failed at request A4401_A4473 section", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            return;
        }
        finally {
            if (mUserMsg != null)
                Toast.makeText(mContext, mUserMsg, Toast.LENGTH_SHORT).show();
        }


        super.onPostExecute(o);
    }


    void update_status(String id) {
        String query = "Update C3001_C3011 set STATUS = '1' where id='" + id + "'";

        query = String.format(query);

        LocalDataManager Lm = new LocalDataManager(mContext);

        LocalDataManager.database.execSQL(query);

        Toast.makeText(mContext, "Status updated", Toast.LENGTH_SHORT).show();
    }

   // wait for Toast then kill app
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(800); // As I am using LENGTH_LONG in Toast
                // Your_Activity.this.finish();


                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                // getActivity().finish();


                System.exit(0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}

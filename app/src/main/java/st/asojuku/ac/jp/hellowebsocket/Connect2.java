package st.asojuku.ac.jp.hellowebsocket;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * Created by Itchy on 2016/09/28.
 */
@ClientEndpoint
public class Connect2  extends AsyncTask<Void,Void,Void> {

    Session session;

    CallBack callBack;

    Handler handler;

    Runnable send;



    public Connect2(){}

    public void run(){

    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("doin2","開始");

        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();

        //URI uri = URI.create("ws://ebyoneserver.cs1cloud.internal:8080/WebSocket/message");
        URI uri = URI.create("ws://153.122.101.38:8080/ws/message2");

        try {
            session = webSocketContainer.connectToServer(new Connect(),uri);
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("doin2","ここまできた");

        handler = new Handler();
        send = new Runnable() {

            @Override
            public void run() {
                try {
                    session.getBasicRemote().sendText("ボタン押した");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callBack.finish();

//        try {
//            session.getBasicRemote().sendText("kkkkkkkk");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void setFinish(CallBack callBack){
        this.callBack = callBack;
    }


    public static class CallBack{
        public void finish(){

        }

        public void setTextView(String stg){

        }
    }

    @OnMessage
    public void onMessage(String message){
        Log.d("Message2",message);
        callBack.setTextView(message);
    }

    public void sendMessage(String message){
        send.run();
    }


}

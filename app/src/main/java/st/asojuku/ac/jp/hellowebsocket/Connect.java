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
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * Created by Itchy on 2016/09/27.
 */
@ClientEndpoint
public class Connect extends AsyncTask<Void,Void,Void> {

    Session session;
    MainActivity ma;

    CallBack callBack;

    AsyncTask<Session,Void,Void> send;

    Handler handler;



    public Connect(){}

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("doin","開始");

        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();

        //URI uri = URI.create("ws://ebyoneserver.cs1cloud.internal:8080/WebSocket/message");
        URI uri = URI.create("ws://153.122.101.38:8080/ws/message");

        try {
            session = webSocketContainer.connectToServer(this,uri);
            System.out.println("1"+this.callBack);
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("doin","ここまできた");





        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //callBack.finish();
        System.out.println("2"+this.callBack);
//        try {
//            session.getBasicRemote().sendText("kkkkkkkk");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public Session getSession() {
        return session;
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
        Log.d("Message",message);
        System.out.println("0"+callBack);
        System.out.println("3"+session);
        System.out.println("4"+this);


        callBack.setTextView("aaaaaaa");

        AsyncTask<Connect,Void,Void> onme = new AsyncTask<Connect, Void, Void>() {
            @Override
            protected Void doInBackground(Connect... params) {
                params[0].callBack.setTextView("aaaaaaa");
                return null;
            }
        };

        onme.execute(this);
    }
    @OnOpen
    public void onOpen(){
        System.out.println("onOpen"+session);
    }


    public void onm(String message){

    }

    public void sendMessage(String message){




    }

}

package st.asojuku.ac.jp.hellowebsocket;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class MainActivity extends AppCompatActivity {

    Session session;

    Button button,button2;

    TextView textView;

    Connect connect;

    Connect2 connect2;

    AsyncTask<Session,Void,Void> send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.btn);
        button2= (Button)findViewById(R.id.btn2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connect.sendMessage("Click Button!");
                //connect2.sendMessage("Click Button!");
                connect.onMessage("テスト");
            }
        });

        textView = (TextView)findViewById(R.id.text);

        connect = new Connect();

        connect.setFinish(new Connect.CallBack(){
            @Override
            public void finish(){
                textView.setText("通信接続完了");
            }

            @Override
            public void setTextView(String stg){
                textView.setText("メッセージ取得:"+ stg);
            }

        });

        connect.execute();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send = new AsyncTask<Session, Void, Void>() {

                    @Override
                    protected Void doInBackground(Session... params) {

                        try {
                            params[0].getBasicRemote().sendText("ボタン押した");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };



                send.execute(connect.getSession());
                System.out.println(connect.callBack);



//                connect2=new Connect2();
//                connect2.setFinish(new Connect2.CallBack(){
//                    @Override
//                    public void finish(){
//                        textView.setText("message2:通信接続完了");
//                    }
//
//                    @Override
//                    public void setTextView(String stg){
//                        textView.setText("メッセージ取得:message2:"+stg);
//                    }
//
//                });
//
//                connect2.execute();
            }
        });

    }

//    private void connect() throws IOException,DeploymentException,RuntimeException{
//
//        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
//
//        //URI uri = URI.create("ws://ebyoneserver.cs1cloud.internal:8080/WebSocket/message");
//        URI uri = URI.create("ws://153.122.101.38:8080/WebSocket/message");
//
//        session = webSocketContainer.connectToServer(new MainActivity(),uri);
//    }



}

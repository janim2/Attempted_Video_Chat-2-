package com.vidyo.vidyo_videochat.other_code;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.vidyo.VidyoClient.Connector.Connector;
import com.vidyo.VidyoClient.Connector.ConnectorPkg;
import com.vidyo.vidyo_videochat.R;
import com.vidyo.vidyo_videochat.VideoFrameLayout;

public class MainActivity extends AppCompatActivity implements Connector.IConnect{

    Connector vc;
    FrameLayout videoFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity_main);

        /* Initialize VidyoConnector */
        ConnectorPkg.setApplicationUIContext(this);
        ConnectorPkg.initialize();

        videoFrame = findViewById(R.id.video_frame);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start(v);
            }
        });
        findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect(v);
            }
        });
        findViewById(R.id.disconnect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect(v);
            }
        });


    }

    public void Start(View v){
        vc = new Connector(videoFrame, Connector.ConnectorViewStyle.VIDYO_CONNECTORVIEWSTYLE_Default, 16, "", "",0);
        vc.showViewAt(videoFrame, 0,0, videoFrame.getWidth(), videoFrame.getHeight());
    }

    public void Connect(View v){
        String token = "cHJvdmlzaW9uAHVzZXIxQGJlNTlhMC52aWR5by5pbwA2Mzc1NTYwNjIxOQAANWY1ZWUxYTY0MWFlYzBlYzc3MmEzMjhmNjhiZDgyOTE2YjVjNjgwOTlmMzU3OThkNTZiODZiMTg3ZjQ4ODU3MTYyZjYxZTFlMDQ0MTUzZjczN2I2YTE4NDdmOTYzOTZm";
        vc.connect("prod.vidyo.io", token, "DemoUser", "DemoRoom", this);
    }

    public void Disconnect(View v){
        vc.disconnect();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(Connector.ConnectorFailReason connectorFailReason) {

    }

    @Override
    public void onDisconnected(Connector.ConnectorDisconnectReason connectorDisconnectReason) {

    }
}
